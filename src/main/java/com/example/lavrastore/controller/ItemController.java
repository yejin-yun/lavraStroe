package com.example.lavrastore.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.example.lavrastore.controller.UserSession;
import com.example.lavrastore.domain.CartItem;
import com.example.lavrastore.domain.Category;
import com.example.lavrastore.domain.DetailItem;
import com.example.lavrastore.domain.Item;
import com.example.lavrastore.domain.Member;
import com.example.lavrastore.domain.Product;
import com.example.lavrastore.domain.Review;
import com.example.lavrastore.domain.WishList;
import com.example.lavrastore.service.PetStoreFacade;

// 귀걸이, 목걸이, 반지, 팔찌, 헤어장식 Item Controlls
/**
 * @author Juergen Hoeller
 * @since 30.11.2003
 * @modified-by Changsup Park
 */
@Controller
@SessionAttributes("itemListPage")
public class ItemController {

	private PetStoreFacade lavraStore;
	private int perPageSize = 12;
	private int totalPageSize;
	UserSession userSession;

	@Autowired
	public void setPetStore(PetStoreFacade petStore) {
		this.lavraStore = petStore;
	}

	@ModelAttribute
	protected void checkLogin(HttpServletRequest request) {
		userSession = (UserSession) request.getSession().getAttribute("userSession");
	}

	@ModelAttribute("sortData") // 객체 이름 설정안하면 메소드 이름이랑 동일.
	public List<String> sortData() {
		List<String> list = new ArrayList<String>();
		list.add("popularity");
		list.add("highPrice");
		list.add("lowPrice");

		return list;
	}

	// getItemListByProduct 대신 로그인 여부 확인하는 거 사용하기 로그인 세션 이용해섷
	@GetMapping
	@RequestMapping("/accessory/{productName}/{categoryId}") // 아예 productName도 view로 넘겨서 사용할 것.
	@Transactional
	public String viewEarringItem(@PathVariable int categoryId, @PathVariable String productName,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "sort", defaultValue = "popularity") String sort, Model model) { // @RequestParam(value="page",required=false) String page //https://pythonq.com/so/spring/1003345

		Product prd = lavraStore.getProductByName(productName, categoryId);
		// System.out.println("rests = " + prd.getName() + ", test = " +
		// prd.getProductId());

		List<Item> tmpList = null;
		switch (sort) {
		case "popularity":
			if (userSession == null) {
				tmpList = lavraStore.getItemForNotUser(prd.getProductId());
				
			} else {
				tmpList = lavraStore.getItemForUser(userSession.getMember().getMemberId(), prd.getProductId());
				for(Item item : tmpList) {
					System.out.println(item.getIsInWishlist());
				}
			}
			break;
		case "highPrice":
			if (userSession == null) {//
				tmpList = lavraStore.getItemOrderByHighPriceForNotUser(prd.getProductId());
			} else {
				tmpList = lavraStore.getItemOrderByHighPriceForUser(userSession.getMember().getMemberId(),
						prd.getProductId());
			}
			break;
		case "lowPrice":
			if (userSession == null) {
				tmpList = lavraStore.getItemOrderByLowPriceForNotUser(prd.getProductId());
			} else {
				tmpList = lavraStore.getItemOrderByLowPriceForUser(userSession.getMember().getMemberId(),
						prd.getProductId());
			}
			break;
		}

		PagedListHolder<Item> itemListPage = new PagedListHolder<Item>(tmpList);
		itemListPage.setPageSize(perPageSize);

		itemListPage.setPage(page - 1);
		totalPageSize = tmpList.size() / perPageSize;
		if (tmpList.size() % perPageSize != 0) {
			totalPageSize++;
		}

		List<Item> itemList = itemListPage.getPageList();
		model.addAttribute("itemList", itemList);
		model.addAttribute("totalPageSize", totalPageSize);
		model.addAttribute("sort", sort);
		model.addAttribute("productName", productName);
		return "ViewItems";
	}

	@PostMapping("/accessory/wish")
	@Transactional
	@ResponseBody
	public String wishAccessory(@RequestBody Item item) {
		// if(itemId == -1) return "error";

		int itemId = item.getItemId();
		int wish = item.getIsInWishlist();

		Member member;
		if (userSession == null) {
			return "LoginForm";
		} else {
			member = userSession.getMember();
		}

		System.out.println("wish test = " + wish);
		WishList wishList = new WishList();
		Item realItem = lavraStore.getItem(itemId);
		if (wish == 0) { // wish 추가
			wishList.setMemberID(member.getMemberId());
			wishList.setItem(realItem);
			
			int cnt = lavraStore.getWishListByItemIdAndMemberId(itemId, member.getMemberId());
			System.out.println("ccnt += " + cnt);
			int rst;
			if(cnt == 0) 
			{
				rst = lavraStore.insertWishList(wishList);
				realItem.setLikeCnt(realItem.getLikeCnt() + 1);
			}else {
				rst = lavraStore.deleteWishListByItemIdAndMemberId(itemId, member.getMemberId());
				if(realItem.getLikeCnt() != 0) {
					realItem.setLikeCnt(realItem.getLikeCnt() - 1);
				}
			}
			
			
			if (rst == 0) {
				return "falid";
			} else {
				lavraStore.updateItem(realItem);
				return "success";
			}
		} else {
			int rst = lavraStore.deleteWishListByItemIdAndMemberId(itemId, member.getMemberId());
			realItem.setLikeCnt(realItem.getLikeCnt() + 1);

			if (rst == 0) {
				return "falid";
			} else {
				lavraStore.updateItem(realItem);
				return "success";
			}
		}

	}

	/*
	 * @GetMapping("/accessory/{product}/wish") public String wishAccessory(
	 * 
	 * @RequestParam(value="no", defaultValue="-1") int no,
	 * 
	 * @RequestParam(value="isInWish", defaultValue="-1") int isInWish) {
	 * if(isInWish == -1) return "redirect:error"; if(no == -1) return
	 * "redirect:error";
	 * 
	 * Member member; if(userSession == null) { return "LoginForm"; } else { member
	 * = userSession.getMember(); }
	 * 
	 * System.out.println("wish test = " + isInWish); WishList wishList = new
	 * WishList(); if(isInWish == 0) { // wish 추가 Item item =
	 * lavraStore.getItem(no);
	 * 
	 * wishList.setMemberID(member.getMemberId()); wishList.setItem(item);
	 * 
	 * 
	 * int rst = lavraStore.insertWishList(wishList); if(rst == 0) {
	 * System.out.println("insertError"); return "redirect:error"; } else { return
	 * "" } } else {
	 * 
	 * } }
	 */
	@GetMapping("/accessory/detail")
	public String viewDetailPage(@RequestParam(value = "no", defaultValue = "-1") int no, Model model) {
		if (no == -1)
			return "redirect:error";

		Member member = null;
		
		Item item = lavraStore.getItem(no);
		CartItem cartItem;
		if (userSession != null) {
			member = userSession.getMember();
			model.addAttribute("memberId", member.getMemberId());
			cartItem = lavraStore.findCartItemByItemItemIdAndMemberId(no, member.getMemberId());
			if (cartItem != null) {
				item.setIsInCart(1);
				model.addAttribute("quantityInCart", cartItem.getQuantity());
			} else {
				item.setIsInCart(0);
			}
		}
		
		
		List<Review> reviewList = null;
		reviewList = lavraStore.getReviewByItem(no);
		model.addAttribute("reviewList", reviewList);
		

		DetailItem dItem = new DetailItem();

		dItem.setItem(item);
		dItem.setQuantity(1);
		dItem.setItemTotalCost(item.getPrice());
		dItem.setPrice(item.getPrice());

		model.addAttribute("dItem", dItem);

		return "ItemPage";
	}

	@PostMapping("/item/cart")
	@Transactional
	@ResponseBody
	public String moveCart(@RequestBody CartItem cartItem) { // int itemId
		Member member;
		if (userSession == null) {
			return "LoginForm";
		} else {
			member = userSession.getMember();
		}

		System.out.println(cartItem.getItem().getItemId());
		// itemId = Integer.parseInt(itemId);
		Item item = lavraStore.getItem(cartItem.getItem().getItemId());
		Category cat = lavraStore.getCategoryByProId(item.getProductId());

		cartItem.setItem(item);
		cartItem.setCategoryId(cat.getCategoryId());
		cartItem.setMemberId(member.getMemberId());

		System.out.println("test");

		int rst = lavraStore.insertCartItem(cartItem);
		if (rst == 0) {
			return "falid";
		} else {
			return "success";
		}
	}

	@Transactional
	@PostMapping("/item/cart_uq")
	public String updateCart(@RequestParam(value = "no", defaultValue = "-1") int no,
			@RequestParam(value = "q", defaultValue = "-1") int q) {
		// System.out.println("testtest" + no + q);
		if (no == -1)
			return "redirect:error";

		Member member;
		if (userSession == null) {
			return "LoginForm";
		}

		member = userSession.getMember();
		CartItem cartItem = lavraStore.findCartItemByItemItemIdAndMemberId(no, member.getMemberId());
		lavraStore.updateQuantity(cartItem.getCartItemId(), cartItem.getQuantity() + q);

		return "redirect:/accessory/detail?no=" + no;
	}

	@Transactional
	@PostMapping("/item/wish")
	@ResponseBody
	public String moveItemToWish(@RequestBody int no) {
		if (no == -1)
			return "redirect:error";

		if (userSession == null) {
			return "LoginForm";
		}
		
		WishList wishList = new WishList();	
		Item item = lavraStore.getItem(no);
		Member member = userSession.getMember();
		
		wishList.setMemberID(member.getMemberId());
		wishList.setItem(item);
		
		int cnt = lavraStore.getWishListByItemIdAndMemberId(no, member.getMemberId()); //해당 위시리스트가 존재하는지 확인하는 쿼리. 
		System.out.println(no + " " + member.getMemberId());
		System.out.println("ccnt += " + cnt);
		int rst = 0;
		if(cnt == 0) 
		{
			rst = lavraStore.insertWishList(wishList);
			item.setLikeCnt(item.getLikeCnt() + 1);

		}
		
		if(rst == 0 && cnt != 0) { //이미 위시리스트에 존재하는 경우 
			return "exist";
		}
		
		if (rst == 0) { // 여기 왔다는 건 기존에 위시리스트에 존재하지는 않았다는 거
			return "falid";
		} else {
			lavraStore.updateItem(item);
			return "success";
		}
	}
	
	/*
	 * @GetMapping
	 * 
	 * @RequestMapping("/accessory/earring/detail") public String
	 * earringItemDetail()
	 */

	/*
	 * @RequestMapping("/shop/viewItem.do") public String handleRequest(
	 * 
	 * @RequestParam("itemId") String itemId, ModelMap model) throws Exception {
	 * Item item = this.petStore.getItem(itemId); model.put("item", item);
	 * model.put("product", item.getProduct()); return "Item"; }
	 */

}
