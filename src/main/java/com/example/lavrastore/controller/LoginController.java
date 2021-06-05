package com.example.lavrastore.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.example.lavrastore.domain.Member;
import com.example.lavrastore.domain.Product;
import com.example.lavrastore.service.PetStoreFacade;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.ui.Model;

@Controller
@SessionAttributes("userSession")
public class LoginController { 

	private PetStoreFacade petStore;
	@Autowired
	public void setPetStore(PetStoreFacade petStore) {
		this.petStore = petStore;
	}
	
	@RequestMapping("/shop/loginForm.do")
	public String login() {
		return "LoginForm";
	}

	@RequestMapping("/shop/login.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			@RequestParam("memberId") String username,
			@RequestParam("password") String password,
			@RequestParam(value="forwardAction", required=false) String forwardAction,
			Model model) throws Exception {
		Member member= petStore.getMember(username, password);
		if (member == null) {
			return new ModelAndView("LoginForm", "message", 
					"Invalid username or password.  Signon failed.");
		}
		else {
			UserSession userSession = new UserSession(member);
			//PagedListHolder<Product> myList = new PagedListHolder<Product>(this.petStore.getProductListByCategory(account.getFavouriteCategoryId()));
			//myList.setPageSize(4);
			//userSession.setMyList(myList);
			model.addAttribute("userSession", userSession);
			if (forwardAction != null) 
				return new ModelAndView("redirect:" + forwardAction);
			else 
				return new ModelAndView("index");
		}
	}
	
	
}

