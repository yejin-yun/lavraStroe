package com.example.lavrastore.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.lavrastore.domain.Item;
import com.example.lavrastore.domain.Member;
import com.example.lavrastore.domain.PTPItem;
import com.example.lavrastore.domain.Product;
import com.example.lavrastore.domain.WishList;
import com.example.lavrastore.service.PetStoreFacade;

@Controller
@SessionAttributes("pitemListPage")
public class viewPTPItemController {

	private PetStoreFacade petStore;
	private int perPageSize = 12;
	private int totalPageSize;
	UserSession userSession;
	
	@Autowired
	public void setPetStore(PetStoreFacade petStore) {
		this.petStore = petStore;
	}
	
	@ModelAttribute
	protected void checkLogin(HttpServletRequest request) {
		userSession = (UserSession) request.getSession().getAttribute("userSession");
	}
	
	@ModelAttribute("sortData") // 객체 이름 설정안하면 메소드 이름이랑 동일.
	public List<String> sortData() {
		List<String> list = new ArrayList<String>();
		list.add("basic");
		list.add("highPrice");
		list.add("lowPrice");

		return list;
	}
	
	@GetMapping
	@RequestMapping("/accessory/{productName}/3")
	@Transactional
	public String ViewEarringPItem(@PathVariable String productName,
			@RequestParam(value="page", defaultValue="1") int page,
			@RequestParam(value="sort", defaultValue="id") String sort,
			Model model) {
		
		Product prd = petStore.getProductByName(productName, 3);
		System.out.println("product name _ ptp : " + prd.getName());
		List<PTPItem> pList = null;
		
//		switch (sort) {
//		case "id":
//			pList = petStore.getPItemListByProduct(prd.getProductId());
//			break;
//		case "highPrice":
//			pList = petStore.getPItemListByHighPrice();
//			break;
//		case "lowPrice":
//			pList = petStore.getPItemListByLowPrice();
//			break;
//		}
		
		//sort basic에 해당, 나중에 시간 나면, 나머지 정렬 추가하기. 
		if(userSession == null) {
			pList = petStore.getPItemListByProduct(prd.getProductId());
		} else {
			pList = petStore.getPItemListByMemberAndProduct(userSession.getMember().getMemberId(), prd.getProductId());
		}
		
		PagedListHolder<PTPItem> pitemListPage = new PagedListHolder<PTPItem>(pList);
		pitemListPage.setPageSize(perPageSize);
		
		pitemListPage.setPage(page - 1);
		totalPageSize = pList.size() / perPageSize;
		if (pList.size() % perPageSize != 0) {
			totalPageSize++;
		}
		
		List<PTPItem> pitemList = pitemListPage.getPageList();
		model.addAttribute("pitemList", pitemList);
		model.addAttribute("totalPageSize", totalPageSize);
		model.addAttribute("sort", sort);
		model.addAttribute("productName", productName);
		
		return "EarringPItem";
	}
	
	@GetMapping("/accessory/viewPItem")
	public String viewPTPItem(
			@RequestParam(value="itemId", defaultValue="-1")int itemid,
			@RequestParam(value="sellerId", defaultValue="-1")String sellerid,
			Model model) {
		
		System.out.println("itemid" + itemid + "sellerID" + sellerid);
		
		PTPItem pitem = petStore.getPItem(itemid, sellerid);
		
		System.out.println("title :" + pitem.getItem().getTitle());
		model.addAttribute("pitem", pitem);
		
		return "ptpPage";
	}
	@PostMapping("/ptop/wish")
	@ResponseBody
	public String wishGroup(@RequestBody Item item) {
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
		Item realItem = petStore.getItem(itemId);
		if (wish == 0) { // wish 추가
			wishList.setMemberID(member.getMemberId());
			wishList.setItem(realItem);
			
			int cnt = petStore.getWishListByItemIdAndMemberId(itemId, member.getMemberId());
			System.out.println("cnt += " + cnt);
			int rst;
			if(cnt == 0) 
			{
				rst = petStore.insertWishList(wishList);
				realItem.setLikeCnt(realItem.getLikeCnt() + 1);
			} else {
				rst = petStore.deleteWishListByItemIdAndMemberId(itemId, member.getMemberId());
				if(realItem.getLikeCnt() != 0) {
					realItem.setLikeCnt(realItem.getLikeCnt() - 1);
				}
			}
			
			if (rst == 0) {
				return "falid";
			} else {
				petStore.updateItem(realItem);
				return "success";
			}
		} else {
			int rst = petStore.deleteWishListByItemIdAndMemberId(itemId, member.getMemberId());
			realItem.setLikeCnt(realItem.getLikeCnt() + 1);

			if (rst == 0) {
				return "falid";
			} else {
				petStore.updateItem(realItem);
				return "success";
			}
		}
		
	}
	
}
