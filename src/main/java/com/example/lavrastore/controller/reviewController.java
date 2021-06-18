package com.example.lavrastore.controller;

import java.text.SimpleDateFormat;
import java.util.Date;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.lavrastore.domain.Member;
import com.example.lavrastore.domain.Review;
import com.example.lavrastore.service.PetStoreFacade;

@Controller
public class reviewController {

	private PetStoreFacade petStore;
	UserSession userSession;
	
	@ModelAttribute
	protected void checkLogin(HttpServletRequest request) {
		userSession = (UserSession) request.getSession().getAttribute("userSession");
	}
	
	@Autowired
	public void setPetStore(PetStoreFacade petStore) {
		this.petStore = petStore;
	}
	
	@RequestMapping("/group/insertReview.do")
	public String inserGroupReview(
			@RequestParam(value="itemId") int itemid,
			@RequestParam(value="rating", defaultValue="1") int rating,
			@RequestParam(value="content") String content,
			RedirectAttributes redirectAttribute) { 
		
		Member member;
		if(userSession == null) {
			return "LoginForm";
		} else {
			member = userSession.getMember();
		}
		
		if(StringUtils.isEmpty(content) || content.length() < 10) {
			redirectAttribute.addFlashAttribute("errReview", true);
			redirectAttribute.addFlashAttribute("errMsg", "최소 10글자 이상 기록해주세요!");
			return "redirect:/group/viewItem?itemNo=" + itemid;
		}
		
		int shopping = 0;
		shopping = petStore.getCntOfGroupItemByMember(itemid, member.getMemberId());
		
		if(shopping > 0) {
			System.out.println("물건 산 횟수 : " + shopping);
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			Date today = new Date();
			String date = dateFormat.format(today);
			Review review = new Review(content, rating, date,member.getMemberId(), itemid);
			
			//insert 진행.
			petStore.insertReview(review);
		} else {
			redirectAttribute.addFlashAttribute("errReview", true);
			redirectAttribute.addFlashAttribute("errMsg", "해당 아이템을 산 고객만 리뷰를 작성하실 수 있습니다!");
		}
		
		return "redirect:/group/viewItem?itemNo=" + itemid;
	}
	
	@RequestMapping("/group/deleteReview.do")
	public String deleteGroupReview(
			@RequestParam(value="reviewId") int reviewId,
			@RequestParam(value="itemId") int itemid,
			RedirectAttributes redirectAttribute) { 
	
		System.out.println("리뷰 " + reviewId);
		petStore.deleteReview(reviewId);
		
		return "redirect:/group/viewItem?" + itemid;
	}
	
	
	@RequestMapping("/accessory/insertReview.do")
	public String insertLineReview(
			@RequestParam(value="itemId") int itemid,
			@RequestParam(value="rating", defaultValue="1") int rating,
			@RequestParam(value="content") String content,
			RedirectAttributes redirectAttribute) { 
		
		Member member;
		if(userSession == null) {
			return "LoginForm";
		} else {
			member = userSession.getMember();
		}
		
		System.out.println("review" + rating + ", " + content);
		if(StringUtils.isEmpty(content) || content.length() < 10) {
			redirectAttribute.addFlashAttribute("errReview", true);
			redirectAttribute.addFlashAttribute("errMsg", "최소 10글자 이상 기록해주세요!");
			return "redirect:/accessory/detail?no=" + itemid;
		}
		
		int shopping = 0;
		shopping = petStore.getCntOfItemByMember(itemid, member.getMemberId());
		System.out.println(itemid + ", " + shopping + ","+ member.getMemberId() +"shopping");
		
		if(shopping > 0) {
			System.out.println("물건 산 횟수 : " + shopping);
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			Date today = new Date();
			String date = dateFormat.format(today);
			Review review = new Review(content, rating, date,member.getMemberId(), itemid);
			
			//insert 진행.
			petStore.insertReview(review);
		} else {
			System.out.println("물건 산 적 없음");
			redirectAttribute.addFlashAttribute("errReview", true);
			redirectAttribute.addFlashAttribute("errMsg", "해당 아이템을 산 고객만 리뷰를 작성하실 수 있습니다!");
		}
		
		return "redirect:/accessory/detail?no=" + itemid;
	}
	
	@RequestMapping("/accessory/deleteReview.do")
	public String deleteLineReview(
			@RequestParam(value="reviewId") int reviewId,
			@RequestParam(value="itemId") int itemid,
			RedirectAttributes redirectAttribute) { 
	
		System.out.println("리뷰 " + reviewId);
		petStore.deleteReview(reviewId);
		
		return "redirect:/accessory/detail?no=" + itemid;
	}
}
