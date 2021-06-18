package com.example.lavrastore.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
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
	private int totalPageSize;
	private int perPageSize = 30;
	
	@Autowired
	public void setPetStore(PetStoreFacade petStore) {
		this.petStore = petStore;
	}

	protected UserSession checkLogin(HttpServletRequest request) {
		return userSession = (UserSession) request.getSession().getAttribute("userSession");
	}
	

	  @GetMapping
	  @RequestMapping("/wishlist/view/{categoryId}") 
	  public String viewWishList(HttpServletRequest request,
			  @PathVariable int categoryId,
			  @RequestParam(value="page", defaultValue="1") int page,
			  Model model) {

			 Member member;
			 userSession = checkLogin(request);
			 if(userSession == null) { return "LoginForm"; }
			 else {
				member = userSession.getMember(); 
			 }
			
			  List<WishList> wishItemList = null;
			  List<WishList> wishItemList2 = new ArrayList<WishList>();
			  wishItemList = this.petStore.findByMemberId(userSession.getMember().getMemberId());
				 
			  for(WishList wishlist : wishItemList) {
				  Item citem = petStore.getItemByWishListId(wishlist.getWishListId());
				  if(citem.getItemId() >= 1000 && citem.getItemId() < 10000 && categoryId == 1)
					 wishItemList2.add(wishlist);
				  else if(citem.getItemId() >= 10000 && citem.getItemId() < 20000 && categoryId == 3)
					  wishItemList2.add(wishlist);
				  else if(citem.getItemId() >= 20000 && citem.getItemId() < 30000 && categoryId == 2){
					  wishItemList2.add(wishlist);
				  }
			  }
			  
			  
			  PagedListHolder<WishList> itemListPage = new PagedListHolder<WishList>(wishItemList2);
			  itemListPage.setPageSize(perPageSize);
			  itemListPage.setPage(page - 1);
			  
			  List<WishList> WishItemListPerPage = itemListPage.getPageList();
			  totalPageSize = wishItemList2.size() / perPageSize; //나눌때는 전체 아이템 개수에 나눠야 페이지 수를 구할 수 있음. 
			  System.out.println("carttest = " + wishItemList.size());
			  if(wishItemList2.size() % perPageSize != 0) {
				  totalPageSize++;
			  }
			  System.out.println("totalPageSize = " + totalPageSize);
			  for(WishList wishlist : WishItemListPerPage) {
				  wishlist.setItem(petStore.getItemByWishListId(wishlist.getWishListId()));
			  }
			  
			  model.addAttribute("wishlist", WishItemListPerPage);
			  model.addAttribute("totalPageSize", totalPageSize);
			  model.addAttribute("curPage", page);
				
			  return "WishList";
		  }
	  
	  @Transactional
	  @PostMapping("/wishlit/handling/del")
	  public String handleCart(
			  HttpServletRequest request) {
		  
		 Member member;
		 if(userSession == null) { return "LoginForm"; }
		 else {
			 member = userSession.getMember(); 
		 }
		 
		 String memberId = member.getMemberId();
		 
		 String[] checkCartItem = request.getParameterValues("checkCartItem");
		 
		 if(checkCartItem == null) {
			 return "redirect:error";
		 }
		 for(int i = 0; i < checkCartItem.length; i++) {
			int wishListId = Integer.parseInt(checkCartItem[i]);
			petStore.deleteWishList(wishListId);
		}
		 
		 return "redirect:/shop/wishList/1";
	  }
	  
}
