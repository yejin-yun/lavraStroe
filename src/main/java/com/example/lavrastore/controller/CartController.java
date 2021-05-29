package com.example.lavrastore.controller;

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
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.lavrastore.domain.CartItem;
import com.example.lavrastore.service.PetStoreFacade;

@Controller
@SessionAttributes("itemListPage")
public class CartController {
	private int totalPageSize;
	private int perPageSize = 6;
	private PetStoreFacade lavraStore;
	UserSession userSession;

	@Autowired
	public void setLavraStore(PetStoreFacade lavraStore) {
		this.lavraStore = lavraStore;
	}
	
	@ModelAttribute
	protected void checkLogin(HttpServletRequest request) {
		userSession = (UserSession) request.getSession().getAttribute("userSession");
	}
	
	
	  @GetMapping
	  @RequestMapping("/cart/{categoryId}") 
	  public String viewCartList(
			  @PathVariable int categoryId,
			  @RequestParam(value="page", defaultValue="1") int page,
			  Model model) {
		/*
		 * if(userSession != null) { return "LoginForm"; }
		 */
		  List<CartItem> cartItemList = null;
		  
		  switch(categoryId) {
		  	case 1:	  		
		  		cartItemList = lavraStore.getCartListByGeneralCategory(categoryId);
		  		break;
		  		
		  	default: //사용자가 categoryId 부분을 지울 수 있음. 
		  		cartItemList = lavraStore.getCartListByGeneralCategory(categoryId);
		  		break;
		  }
		 
		  PagedListHolder<CartItem> itemListPage = new PagedListHolder<CartItem>(cartItemList);
		  itemListPage.setPageSize(perPageSize);
		  itemListPage.setPage(page - 1);
		  
		  List<CartItem> CartItemListPerPage = itemListPage.getPageList();
		  totalPageSize = CartItemListPerPage.size() / perPageSize;
		  if(CartItemListPerPage.size() % perPageSize != 0) {
			  totalPageSize++;
		  }
		  
		  for(CartItem cartItem : CartItemListPerPage) {
			  cartItem.setItem(lavraStore.getItemByCartItemId(cartItem.getCartItemId()));
		  }
		  
		  model.addAttribute("cartItemList", CartItemListPerPage);
		  model.addAttribute("totalPageSize", totalPageSize);
			
		  return "cart";
	  }
	 
}
