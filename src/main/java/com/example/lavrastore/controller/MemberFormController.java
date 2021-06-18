package com.example.lavrastore.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.WebUtils;

import com.example.lavrastore.service.MemberFormValidator;
import com.example.lavrastore.service.PetStoreFacade;

@Controller
@RequestMapping({"/shop/newMember.do","/shop/editMember.do"})
public class MemberFormController {

	@Value("EditMemberForm")
	private String formViewName;
	@Value("index")
	private String successViewName;
	
	@Autowired
	private PetStoreFacade petStore;
	public void setPetStore(PetStoreFacade petStore) {
		this.petStore = petStore;
	}

	@Autowired
	private MemberFormValidator validator;
	public void setValidator(MemberFormValidator validator) {
		this.validator = validator;
	}
		
	@ModelAttribute("memberForm")
	public MemberForm formBackingObject(HttpServletRequest request) 
			throws Exception {
		UserSession userSession = 
			(UserSession) WebUtils.getSessionAttribute(request, "userSession");
		if (userSession != null) {	// edit an existing account
			if(userSession.getMember() == null)
				return new MemberForm();
			return new MemberForm(petStore.getMember(userSession.getMember().getMemberId()));
		}
		else {	// create a new account
			return new MemberForm();
		}
	}

	
	@RequestMapping(method = RequestMethod.GET)
	public String showForm() {
		return formViewName;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String onSubmit(
			HttpServletRequest request, HttpSession session,
			@ModelAttribute("memberForm") MemberForm memberForm,
			BindingResult result) throws Exception {

		
		validator.validate(memberForm, result);
		
		if (result.hasErrors()) return formViewName;
		try {
			if (memberForm.isNewMember()) {
				petStore.insertMember(memberForm.getMember());
			}
			else {
				petStore.updateMember(memberForm.getMember());
			}
		}
		catch (DataIntegrityViolationException ex) {
			result.rejectValue("member.memberId", "USER_ID_ALREADY_EXISTS",
					"User ID already exists: choose a different ID.");
			return formViewName; 
		}
		
		UserSession userSession = new UserSession(
			petStore.getMember(memberForm.getMember().getUsername()));
		session.setAttribute("userSession", userSession);
		return successViewName;  
	}
}
