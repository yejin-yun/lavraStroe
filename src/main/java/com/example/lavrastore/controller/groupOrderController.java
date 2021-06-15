package com.example.lavrastore.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.lavrastore.domain.GroupItem;
import com.example.lavrastore.domain.Member;
import com.example.lavrastore.domain.Order;
import com.example.lavrastore.service.PetStoreFacade;

@Controller
@SessionAttributes("myOrder")
public class groupOrderController {
	
	private PetStoreFacade petStore;
	UserSession userSession;
	
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
	public String validateAndConfirmNewGroupOrder(
			@RequestParam(value="itemId") int itemid,
			@ModelAttribute("myOrder") Order myOrder,
			@RequestParam(value="page", defaultValue="1") int page,
			Model model) {
		System.out.println("newOrder에서 넘어온 값 확인 :" + myOrder.getCardNum() + ", " + myOrder.getPayType() +
				"," + myOrder.getGroupOrder().getStatus() + "," + myOrder.getShipAddr1());
		
		
		petStore.insertOrder(myOrder);
		
		model.addAttribute("success", true);
		return "groupOrderConfirm";
	}
}
