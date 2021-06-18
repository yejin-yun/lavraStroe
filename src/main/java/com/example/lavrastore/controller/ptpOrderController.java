package com.example.lavrastore.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

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
		
		myOrder.initPtPOrder(pitem, member);
		
		model.addAttribute("pitem", pitem);
		
		return "ptpOrderForm";
	}
	

}
