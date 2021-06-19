package com.example.lavrastore.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.lavrastore.domain.Member;
import com.example.lavrastore.domain.PTPItem;
import com.example.lavrastore.service.PetStoreFacade;

@Controller
public class ViewSellListController {
	private PetStoreFacade petStore;
	UserSession userSession;
	private int totalPageSize;
	private int perPageSize = 6;
	
	@Autowired
	public void setPetStore(PetStoreFacade petStore) {
		this.petStore = petStore;
	}
	
	protected UserSession checkLogin(HttpServletRequest request) {
		return userSession = (UserSession) request.getSession().getAttribute("userSession");
	}
	
	@GetMapping
	@RequestMapping("/sellList/view/{state}")
	public String viewSellList(HttpServletRequest request, @PathVariable int state,
			@RequestParam(value="page", defaultValue="1") int page, Model model) {
		
		Member member;
		userSession = checkLogin(request);
		if (userSession == null) { return "LoginForm"; }
		else {
			member = userSession.getMember();
		}
		
		
		
		return "SellList";
	}
	
	@RequestMapping("/sellList/view/write.do")
	public String writePItem() {
		return "/sellList/view/pItemWirte";
	}
	
	@RequestMapping("sellList/view/insert.do")
	public String insertPItem(PTPItem pItem) {
		
		
		return "SellList";
	}
	
	@RequestMapping("sellList/view/update.do")
	public String updatePItem(PTPItem pItem) {
		
		return "SellList";
	}
	
	@RequestMapping("sellList/view/delete.do")
	public String deletePItem(@RequestParam int itemId) {
		
		return "SellList";
	}
}
