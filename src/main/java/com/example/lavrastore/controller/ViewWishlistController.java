package com.example.lavrastore.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.lavrastore.domain.CartItem;
import com.example.lavrastore.domain.Item;
import com.example.lavrastore.domain.Member;
import com.example.lavrastore.domain.Product;
import com.example.lavrastore.domain.WishList;
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
			PagedListHolder<WishList> wishList = new PagedListHolder<WishList>(this.petStore.findByMemberId(userSession.getMember().getMemberId()));
			wishList.setPageSize(12);
			request.getSession().setAttribute("ViewWishlistController_productList", wishList);
			return new ModelAndView("WishList", "wishList", wishList);
		}
		else {
			@SuppressWarnings("unchecked")
			PagedListHolder<WishList> wishList = (PagedListHolder<WishList>)request.getSession().getAttribute("ViewWishlistController_productList");
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
	 @Transactional
	  @PostMapping("/wishlist/handling/{kind}")
	  public String handleCart(
			  @PathVariable String kind,
			  HttpServletRequest request) {
		  
		 Member member;
		 if(userSession == null) { return "LoginForm"; }
		 else {
			 member = userSession.getMember(); 
		 }
		 
		 String memberId = member.getMemberId();
		 
		 String[] checkWistListItem = request.getParameterValues("checkWistListItem");
		 
		 if(checkWistListItem == null) {
			 return "redirect:error";
		 }
		 
		 switch(kind) {
		 	case "del":
		 		for(int i = 0; i < checkWistListItem.length; i++) {
					 int wishListId = Integer.parseInt(checkWistListItem[i]);
					 petStore.deleteWishList(wishListId);
				}
		 		break;
		 	default:
		 		return "redirect:error";
		 }
		 
		 return "redirect:/cart/view/1";
	  }
	  
}
