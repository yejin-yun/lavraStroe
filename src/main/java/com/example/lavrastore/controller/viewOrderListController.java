package com.example.lavrastore.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.lavrastore.domain.GroupItem;
import com.example.lavrastore.domain.LineItem;
import com.example.lavrastore.domain.Order;
import com.example.lavrastore.domain.PTPItem;
import com.example.lavrastore.service.PetStoreFacade;

@Controller
public class viewOrderListController {
	private PetStoreFacade petStore;
	UserSession userSession;
	
	@Autowired
	public void setPetStore(PetStoreFacade petStore) {
		this.petStore = petStore;
	}
	
	@ModelAttribute
	protected void checkLogin(HttpServletRequest request) {
		userSession = (UserSession) request.getSession().getAttribute("userSession");
	}
	
	@RequestMapping("/order/view/1") //쇼핑몰 item인 경우 (기본)
	@Transactional
	public String viewItemOrder(Model model) {
		List<Order> orderList = null;
		
		orderList = petStore.getItemOrderListByMember(
				userSession.getMember().getMemberId());
		
		
 		
		for(Order order : orderList) {
			List<LineItem> lineItems = petStore.getLineItemByOrderId(order.getOrderId());
			
			for(LineItem lineItem : lineItems) {
				lineItem.setItem(petStore.getItem(lineItem.getItemId()));
			}
			
			order.setLineItems(lineItems);
		}
		
		
		
		model.addAttribute("view" , 1);
		model.addAttribute("orderList", orderList);
		model.addAttribute("member", userSession.getMember() );
		return "order";
	}
	
	@RequestMapping("/order/view/2") //공동구매 item인 경우
	public String viewPTPOrder(Model model) {
		List<Order> orderList = null;
		
		orderList = petStore.getPTPOrderListByMember(
				userSession.getMember().getMemberId());
		
		for(Order odr : orderList) {
			PTPItem tmp = petStore.getPItem(odr.getPtpOrder().getItemId(), odr.getPtpOrder().getSellerId());
			odr.getPtpOrder().setPtpItem(tmp);
		}
		
		model.addAttribute("view" , 2);
		model.addAttribute("orderList", orderList);
		model.addAttribute("member", userSession.getMember() );
		return "order";
	}
	
	@RequestMapping("/order/view/3") //공동구매 item인 경우
	public String viewGroupOrder(Model model) {
		List<Order> orderList = null;
		
		orderList = petStore.getGroupOrderListByMember(
				userSession.getMember().getMemberId());
		
		for(Order odr : orderList) {
			GroupItem tmp = petStore.getGItem(odr.getGroupOrder().getItemId());
			odr.getGroupOrder().setGroupItem(tmp);
			
			System.out.println(odr.getGroupOrder().getGroupItem().getItem().getTitle());
		}
		
		model.addAttribute("view" , 3);
		model.addAttribute("orderList", orderList);
		model.addAttribute("member", userSession.getMember());
		return "order";
	}
	
}
