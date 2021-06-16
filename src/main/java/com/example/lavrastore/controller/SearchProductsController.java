package com.example.lavrastore.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.lavrastore.domain.Item;
import com.example.lavrastore.service.PetStoreFacade;

@Controller
public class SearchProductsController { 

	private PetStoreFacade petStore;

	@Autowired
	public void setPetStore(PetStoreFacade petStore) {
		this.petStore = petStore;
	}

	@RequestMapping("/shop/searchProducts.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			@RequestParam(value="searchKey", required=false) String keyword,
			@RequestParam(value="page", required=false) String page
			) throws Exception {
		if (keyword != null) {
			if (!StringUtils.hasLength(keyword)) {
				return new ModelAndView("Error", "message", "Please enter a keyword to search for, then press the search button.");
			}
			PagedListHolder<Item> itemList = new PagedListHolder<Item>(this.petStore.searchItemList(keyword.toLowerCase()));
			itemList.setPageSize(12);
			request.getSession().setAttribute("SearchProductsController_productList", itemList);
			return new ModelAndView("SearchProducts", "itemList", itemList);
		}
		else {
			@SuppressWarnings("unchecked")
			PagedListHolder<Item> itemList = (PagedListHolder<Item>)request.getSession().getAttribute("SearchProductsController_productList");
			/*if (productList == null) {
				return new ModelAndView("Error", "message", "Your session has timed out. Please start over again.");
			}*/
			if ("next".equals(page)) {
				itemList.nextPage();
			}
			else if ("previous".equals(page)) {
				itemList.previousPage();
			}
			return new ModelAndView("SearchProducts", "itemList", itemList);
		}
	}
}
