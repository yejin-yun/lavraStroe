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
import com.example.lavrastore.domain.Member;
import com.example.lavrastore.service.PetStoreFacade;

@Controller
@SessionAttributes("itemListPage")
public class CartController {
	private int totalPageSize;
	private int perPageSize = 6;
	private PetStoreFacade lavraStore;
	private UserSession userSession;

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
	
		 Member member;
		 if(userSession == null) { return "LoginForm"; }
		 else {
			member = userSession.getMember(); 
		 }
		
		  List<CartItem> cartItemList = null;
		  
		  switch(categoryId) {
		  	case 1:	  		
		  		cartItemList = lavraStore.getCartListByGeneralCategory(categoryId, member.getMemberId());
		  		break;
		  		
		  	default: //사용자가 categoryId 부분을 지울 수 있음. 
		  		cartItemList = lavraStore.getCartListByGeneralCategory(categoryId, member.getMemberId());
		  		break;
		  }
		 
		  PagedListHolder<CartItem> itemListPage = new PagedListHolder<CartItem>(cartItemList);
		  itemListPage.setPageSize(perPageSize);
		  itemListPage.setPage(page - 1);
		  
		  List<CartItem> CartItemListPerPage = itemListPage.getPageList();
		  totalPageSize = cartItemList.size() / perPageSize; //나눌때는 전체 아이템 개수에 나눠야 페이지 수를 구할 수 있음. 
		  System.out.println("carttest = " + cartItemList.size());
		  if(cartItemList.size() % perPageSize != 0) {
			  totalPageSize++;
		  }
		  System.out.println("totalPageSize = " + totalPageSize);
		  for(CartItem cartItem : CartItemListPerPage) {
			  cartItem.setItem(lavraStore.getItemByCartItemId(cartItem.getCartItemId(), member.getMemberId()));
		  }
		  
		  model.addAttribute("cartItemList", CartItemListPerPage);
		  model.addAttribute("totalPageSize", totalPageSize);
			
		  return "cart";
	  }
	 
}
