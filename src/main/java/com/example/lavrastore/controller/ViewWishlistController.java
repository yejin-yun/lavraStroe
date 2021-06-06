package com.example.lavrastore.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.lavrastore.domain.Item;
import com.example.lavrastore.domain.Product;
import com.example.lavrastore.service.PetStoreFacade;

@Controller
public class ViewWishlistController {
	private PetStoreFacade petStore;
	UserSession userSession;

	@Autowired
	public void setPetStore(PetStoreFacade petStore) {
		this.petStore = petStore;
	}

	protected void checkLogin(HttpServletRequest request) {
		userSession = (UserSession) request.getSession().getAttribute("userSession");
	}
	
	@RequestMapping("/shop/wishList.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			@RequestParam(value="page", required=false) String page
			) throws Exception {
		if (userSession != null) {
			if (userSession == null) {
				return new ModelAndView("Error", "message", "Please enter a keyword to search for, then press the search button.");
			}
			PagedListHolder<Item> wishList = new PagedListHolder<Item>(this.petStore.getItemByWishListId(userSession.getMember().getMemberId()));
			wishList.setPageSize(12);
			request.getSession().setAttribute("ViewWishlistController_productList", wishList);
			return new ModelAndView("WishList", "wishList", wishList);
		}
		else {
			@SuppressWarnings("unchecked")
			PagedListHolder<Item> wishList = (PagedListHolder<Item>)request.getSession().getAttribute("ViewWishlistController_productList");
			/*if (productList == null) {
				return new ModelAndView("Error", "message", "Your session has timed out. Please start over again.");
			}*/
			if ("next".equals(page)) {
				 wishList.nextPage();
			}
			else if ("previous".equals(page)) {
				 wishList.previousPage();
			}
			return new ModelAndView("WishList", "wishList", wishList);
		}
	}
}
