package com.example.lavrastore.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.example.lavrastore.service.OrderValidator;
import com.example.lavrastore.service.PetStoreFacade;

@Controller
@SessionAttributes("myOrder")
public class groupOrderController {
	
	private PetStoreFacade petStore;
	UserSession userSession;
	
	@Autowired
	private OrderValidator orderValidator;
	
	@ModelAttribute
	protected void checkLogin(HttpServletRequest request) {
		userSession = (UserSession) request.getSession().getAttribute("userSession");
	}
	
	@ModelAttribute("creditCardTypes")
	public List<String> referenceData() {
		ArrayList<String> creditCardTypes = new ArrayList<String>();
		creditCardTypes.add("Visa");
		creditCardTypes.add("MasterCard");
		creditCardTypes.add("American Express");
		return creditCardTypes;			
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
	
	@RequestMapping("/group/newOrder.do")
	public String initNewGroupOrder(
			@RequestParam(value="itemId") int itemid,
			@RequestParam(value="amount") int amount,
			@ModelAttribute("myOrder") Order myOrder,
			Model model) { 
		
		Member member;
		if(userSession == null) {
			return "LoginForm";
		} else {
			member = userSession.getMember();
		}
		
		GroupItem gitem = petStore.getGItem(itemid);
		gitem.setPercent();
		
		myOrder.initGroupOrder(gitem, member, amount);
		
		model.addAttribute("gitem", gitem);
		
		return "groupOrderForm";
	}
	
	@RequestMapping("/group/newOrderSubmitted.do")
	public ModelAndView validateAndConfirmNewGroupOrder(
			@RequestParam(value="itemId") int itemid,
			@ModelAttribute("myOrder") Order myOrder,
			BindingResult result,
			SessionStatus status) {
		System.out.println("newOrder에서 넘어온 값 확인 :" + myOrder.getCardNum() + ", " + myOrder.getPayType() +
				"," + myOrder.getGroupOrder().getStatus() + "," + myOrder.getShipAddr1());
		
		
		orderValidator.validateCreditCard(myOrder, result);
		orderValidator.validateShippingAddress(myOrder, result);
		
		if (result.hasErrors()) {
			ModelAndView mav = new ModelAndView("groupOrderForm");
			mav.addObject("gitem", myOrder.getGroupOrder().getGroupItem());
			return mav; 
		}
		
		petStore.insertOrder(myOrder);
		
		ModelAndView mav = new ModelAndView("groupOrderConfirm");
		mav.addObject("myOrder", myOrder);
		mav.addObject("success", true);
		mav.addObject("groupOrder", myOrder.getGroupOrder());
		mav.addObject("gItem", myOrder.getGroupOrder().getGroupItem());
		
		status.setComplete();  // remove sessionCart and orderForm from session
		return mav;
	}
}
