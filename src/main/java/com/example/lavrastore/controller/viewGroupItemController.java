package com.example.lavrastore.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
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

import com.example.lavrastore.domain.GroupItem;
import com.example.lavrastore.domain.Item;
import com.example.lavrastore.domain.Member;
import com.example.lavrastore.domain.Product;
import com.example.lavrastore.domain.Review;
import com.example.lavrastore.domain.WishList;
import com.example.lavrastore.service.PetStoreFacade;

@Controller
@SessionAttributes("gitemListPage")
public class viewGroupItemController {
	private PetStoreFacade petStore;
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
	
	@RequestMapping("/group")
	public String viewAllGroupItem(
			@RequestParam(value="page", defaultValue="1") int page,
			Model model) { //@RequestParam(value="page", required=false) String page // https://pythonq.com/so/spring/1003345
		
		List<GroupItem> gList = null;
		
		
		if(userSession == null) {
			gList = petStore.getAllGItemList();
		} else {
			gList = petStore.getGItemListByMember(userSession.getMember().getMemberId());
		}
		PagedListHolder<GroupItem> gitemListPage;
		gitemListPage = new PagedListHolder<GroupItem>(gList);

		gitemListPage.setPageSize(12);

		gitemListPage.setPage(page -1 );
		totalPageSize = gList.size() / 12;
		if (gList.size() % 12 != 0) {
			totalPageSize++;
		}
		
		List<GroupItem> gitemList = gitemListPage.getPageList();
		for(GroupItem g : gitemList) {
			g.setPercent();
			System.out.println("itemid : " + g.getItem().getItemId() + " 진행도 : " + g.getPercent() + g.getItem().getIsInWishlist());
		}
		
		model.addAttribute("gitemList", gitemList);
		model.addAttribute("totalPageSize", totalPageSize);
		
		return "groupPage"; 
	}
	
	
	
	@GetMapping
	@RequestMapping("/group/viewItem")
	public String viewGroupItem(
			@RequestParam(value="itemNo", defaultValue="11024") int itemid, Model model
			) {
		
		Member member = null;
		
		List<Review> reviewList = null;
		reviewList = petStore.getReviewByItem(itemid);
		model.addAttribute("reviewList", reviewList);
		
		GroupItem gitem = petStore.getGItem(itemid);
		gitem.setPercent();
		
		if(userSession != null) {
			member = userSession.getMember();
			model.addAttribute("memberId", member.getMemberId());
		} 
		
		model.addAttribute("gitem", gitem);
		System.out.println("itemid : " + gitem.getItem().getItemId() + " 등록일 : " + gitem.getRegiDate() + " 마감일 : " + gitem.getPaymentDate());
		return "groupItemPage";
	
	}
	
	@PostMapping("/group/wish")
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
