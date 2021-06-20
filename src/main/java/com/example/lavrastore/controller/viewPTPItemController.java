package com.example.lavrastore.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.example.lavrastore.domain.Item;
import com.example.lavrastore.domain.Member;
import com.example.lavrastore.domain.PTPItem;
import com.example.lavrastore.domain.Product;
import com.example.lavrastore.domain.WishList;
import com.example.lavrastore.service.PtPFormValidator;
import com.example.lavrastore.service.PetStoreFacade;

@Controller
@SessionAttributes("pitemListPage")
public class viewPTPItemController {
	@Value("PTPItemWrite")
	private String formViewName;
	private PetStoreFacade petStore;
	private int perPageSize = 12;
	private int totalPageSize;
	UserSession userSession;
	
	
	@Autowired
	private PtPFormValidator ptpFormValidator;
	
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
	
	@RequestMapping("/shop/productinsert.do")
	public String insert(
			@ModelAttribute("ptp")PTPItemForm ptp,
			BindingResult result){
		
		MultipartFile file = ptp.getProductPhoto();
		String fn = file.getName();
		
		PTPItem pi = ptp.getPtpitem();
		Item ii = ptp.getItem();
		
	
		
		if(pi != null) {
			System.out.println("PI" + pi.getBank());
		}
		if(ii != null) {
			System.out.println("II" + ii.getTitle());
		}
		
		ptpFormValidator.validateItem(ii, result);
		ptpFormValidator.validatePTPItem(pi, result);
		
		
		if (result.hasErrors()) { return formViewName; }
		
		String imageUrl = "/images/gitem/gold_brac.png";
		imageUrl = ptp.getProductPhoto().getOriginalFilename();
		String path = "/images/upload/";
		String path1 = "C:\\Users\\00\\Documents\\GitHub\\JPetStore\\lavraStroe\\src\\main\\resources\\static\\images\\upload\\";
		
		try {
				new File(path1).mkdirs(); // 디렉토리 생성
				// 임시디렉토리(서버)에 저장된 파일을 지정된 디렉토리로 전송
				ptp.getProductPhoto().transferTo(new File(path1+imageUrl));
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		//fileImage를 고정하고 개발을 진행합니다. image 넣는 부분만 처리해주세요.
		
		path += imageUrl;
		ii.setImage(path);
		
		//작업한 부분. insert 처리 해놨으니까 아래는 건들이지 말아요.
		pi.setItem(ii);
		pi.setSellerId(userSession.getMember().getMemberId());
		petStore.insertPItem(pi);
		
		return "redirect:/sellList/view/0";
	}
	
	@RequestMapping("/item/ptpwrite.do")
	public String write(Model model){
		PTPItemForm ptp = new PTPItemForm();
		model.addAttribute("ptp",ptp);
		return "PTPItemWrite";
	}
	
	@RequestMapping("/shop/productupdate.do")
	public String update(
			@RequestParam(value="itemId", defaultValue="-1")int itemid,
			@ModelAttribute("pitem")PTPItem edPitem,
			BindingResult result) {
		System.out.println("edit pitem : " + edPitem.getItem().getDescription() + ", " + edPitem.getBank() + ", " + edPitem.getItem().getPrice());
		
		ptpFormValidator.validateItem(edPitem.getItem(), result);
		ptpFormValidator.validatePTPItemUpdate(edPitem, result);
		
		edPitem.setPTPItemId(itemid);
		edPitem.getItem().setItemId(itemid);
		edPitem.setSellerId(userSession.getMember().getMemberId());
		petStore.updatePItem(edPitem);
		
		return "redirect:/sellList/view/0";
	}
	
	@RequestMapping("/item/ptpedit.do")
	public String ptpEdit(
			@RequestParam(value="itemNo", defaultValue="20000") int itemid,
			Model model) {
		
		if (userSession == null) {
			return "LoginForm";
		} 
			
		String sellerid = userSession.getMember().getMemberId();
		PTPItem pitem = petStore.getPItem(itemid, sellerid);
		
		model.addAttribute("sellerid", sellerid);
		model.addAttribute("pitem", pitem);
		
		return "PTPItemUpdate";
	}
	
	@RequestMapping("/shop/productdelete.do")
	public String delete(@RequestParam("PTPItemId") int PTPItemId) {
		petStore.deletePItem(PTPItemId);
		
		return "redirect:/sellList/view/0";
	}
}	
	

