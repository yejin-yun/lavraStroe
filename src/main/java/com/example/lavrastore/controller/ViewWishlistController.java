package com.example.lavrastore.controller;

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
	private int perPageSize = 6;
	
	@Autowired
	public void setPetStore(PetStoreFacade petStore) {
		this.petStore = petStore;
	}

	protected UserSession checkLogin(HttpServletRequest request) {
		return userSession = (UserSession) request.getSession().getAttribute("userSession");
	}
	

	  @GetMapping
	  @RequestMapping("/shop/wishList.do") 
	  public String viewWishList(HttpServletRequest request,
			  @RequestParam(value="page", defaultValue="1") int page,
			  Model model) {

			 Member member;
			 userSession = checkLogin(request);
			 if(userSession == null) { return "LoginForm"; }
			 else {
				member = userSession.getMember(); 
			 }
			
			  List<WishList> wishItemList = null;
			  
			  wishItemList = this.petStore.findByMemberId(userSession.getMember().getMemberId());
			  PagedListHolder<WishList> itemListPage = new PagedListHolder<WishList>(wishItemList);
			  itemListPage.setPageSize(perPageSize);
			  itemListPage.setPage(page - 1);
			  
			  List<WishList> WishItemListPerPage = itemListPage.getPageList();
			  totalPageSize = wishItemList.size() / perPageSize; //나눌때는 전체 아이템 개수에 나눠야 페이지 수를 구할 수 있음. 
			  System.out.println("carttest = " + wishItemList.size());
			  if(wishItemList.size() % perPageSize != 0) {
				  totalPageSize++;
			  }
			  System.out.println("totalPageSize = " + totalPageSize);
			  for(WishList wishlist : WishItemListPerPage) {
				  wishlist.setItem(petStore.getItemByWishListId(wishlist.getWishListId()));
				  //cartItem.setItem(lavraStore.findItemByCategoryIdAndMemberId(cartItem.getCartItemId(), member.getMemberId())); //에러남
			  }
			  
			  model.addAttribute("wishlist", WishItemListPerPage);
			  model.addAttribute("totalPageSize", totalPageSize);
			  model.addAttribute("curPage", page);
				
			  return "WishList";
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
		 
		 return "redirect:/shop/wishList.do";
	  }
	  
}
