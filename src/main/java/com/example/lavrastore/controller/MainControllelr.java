package com.example.lavrastore.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.lavrastore.domain.Item;
import com.example.lavrastore.service.PetStoreFacade;

@Controller
public class MainControllelr {
	private PetStoreFacade lavraStore;
	private UserSession userSession;
	final private int topK = 12;
	final private int categoryId = 1;

	@Autowired
	public void setLavraStore(PetStoreFacade lavraStore) {
		this.lavraStore = lavraStore;
	}
	
	@ModelAttribute
	protected void checkLogin(HttpServletRequest request) {
		userSession = (UserSession) request.getSession().getAttribute("userSession");
	}
	
	
	@RequestMapping("/")
	public String mainPage(Model model) {
		List<Item> itemList = lavraStore.getItemListTopKByLikeCnt(categoryId, topK);
		
		model.addAttribute("itemList", itemList);
		
		return "index";
	}
}
