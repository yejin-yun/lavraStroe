package com.example.lavrastore.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.lavrastore.domain.Member;
import com.example.lavrastore.domain.PTPItem;
import com.example.lavrastore.domain.Product;
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
		
		List<PTPItem> sellItemList = null;
		List<PTPItem> sellItemList2 = new ArrayList<PTPItem>();
		sellItemList = this.petStore.findBySellerId(userSession.getMember().getMemberId());
		
		for (PTPItem sellList : sellItemList) {
			if (sellList.getState() == 0)
				sellItemList2.add(sellList);
			else if (sellList.getState() == 1)
				sellItemList2.add(sellList);
		}
		
		PagedListHolder<PTPItem> sellListPage = new PagedListHolder<PTPItem>(sellItemList2);
		sellListPage.setPageSize(perPageSize);
		sellListPage.setPage(page - 1);
		
		List<PTPItem> sellItemListPerPage = sellListPage.getPageList();
		totalPageSize = sellItemList2.size() / perPageSize;
		System.out.println("carttest = " + sellItemList.size());
		if (sellItemList2.size() % perPageSize != 0) {
			totalPageSize++;
		}
		System.out.println("totalPageSize = " + totalPageSize);
		for (PTPItem sellList : sellItemListPerPage) {
			sellList.setItem(petStore.getItem(sellList.getItem().getItemId()));
		}
		
		model.addAttribute("sellItemList2", sellItemListPerPage);
		model.addAttribute("totalPageSize", totalPageSize);
		model.addAttribute("curPage", page);
		
		return "SellList";
	}
}