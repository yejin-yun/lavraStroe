package com.example.lavrastore.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.example.lavrastore.domain.GroupItem;
import com.example.lavrastore.domain.Member;
import com.example.lavrastore.domain.Order;
import com.example.lavrastore.domain.PTPItem;
import com.example.lavrastore.service.OrderValidator;
import com.example.lavrastore.service.PetStoreFacade;

@Controller
@SessionAttributes("myOrder")
public class ptpOrderController {
	
	private PetStoreFacade petStore;
	UserSession userSession;
	
	@Autowired
	private OrderValidator orderValidator;
	
	@ModelAttribute
	protected void checkLogin(HttpServletRequest request) {
		userSession = (UserSession) request.getSession().getAttribute("userSession");
	}
	//판매자가 올린 글에 대한 주문
	@ModelAttribute("myOrder")
	public Order createNewOrder(HttpSession session) {
		Order myOrder = (Order)session.getAttribute("myOrder");
		if (myOrder == null) myOrder = new Order();
		return myOrder;
	}
	
	@Autowired
	public void setPetStore(PetStoreFacade petStore) {
		this.petStore = petStore;
	}
	
	@RequestMapping("/accessory/newPtpOrder.do")
	public String initNewPtPOrder(
			@RequestParam(value="itemId") int itemid,
			@RequestParam(value="sellerId") String sellerid,
			@ModelAttribute("myOrder") Order myOrder,
			Model model) { 
		
		Member member;
		if(userSession == null) {
			return "LoginForm";
		} else {
			member = userSession.getMember();
		}
		
		
		System.out.println("ptpitemd new order" + itemid + ", " + sellerid);
		
		PTPItem pitem = petStore.getPItem(itemid, sellerid);
		
		System.out.println("new ptp order form :" + pitem.getPTPItemId());
		
		myOrder.initPtPOrder(pitem, member);
		
		model.addAttribute("pitem", pitem);
		
		return "ptpOrderForm";
	}
	
	@RequestMapping("/accessory/newPtpOrderSubmitted.do")
	public ModelAndView validateAndConfirmNewPtpOrder(
				@RequestParam(value="itemId") int itemid,
				@RequestParam(value="depositor") String depositor,
				@ModelAttribute("myOrder") Order myOrder,
				BindingResult result,
				SessionStatus status) {
		
		myOrder.getPtpOrder().setDepositor(depositor);
		myOrder.getPtpOrder().setPtpItem(petStore.getPItem(itemid, myOrder.getPtpOrder().getSellerId()));
		
		petStore.insertOrder(myOrder);
		
		ModelAndView mav = new ModelAndView("ptpOrderConfirm");
		mav.addObject("myOrder", myOrder);
		mav.addObject("success", true);
		mav.addObject("ptpOrder", myOrder.getPtpOrder());
		
		status.setComplete();
		
		return mav;
	}

}
