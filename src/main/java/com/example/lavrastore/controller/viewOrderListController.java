package com.example.lavrastore.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.lavrastore.domain.GroupItem;
import com.example.lavrastore.domain.Order;
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
	public String viewItemOrder(Model model) {
		List<Order> orderList = null;
		
		orderList = petStore.getItemOrderListByMember(
				userSession.getMember().getMemberId());
		
		model.addAttribute("view" , 1);
		model.addAttribute("orderList", orderList);
		model.addAttribute("member", userSession.getMember() );
		return "order";
	}
	
	@RequestMapping("/order/view/2") //개인구매 item인 경우
	public String viewPTPOrder(Model model) {
		List<Order> orderList = null;
		
		orderList = petStore.getPTPOrderListByMember(
				userSession.getMember().getMemberId());
		
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
