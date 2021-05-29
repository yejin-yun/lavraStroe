package com.example.lavrastore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.example.lavrastore.domain.Item;
import com.example.lavrastore.domain.Product;
import com.example.lavrastore.service.PetStoreFacade;

// 귀걸이, 목걸이, 반지, 팔찌, 헤어장식 Item Controlls
/**
 * @author Juergen Hoeller
 * @since 30.11.2003
 * @modified-by Changsup Park
 */
@Controller
@SessionAttributes("itemListPage")
public class ViewItemController { 

	private PetStoreFacade petStore;
	private int totalPageSize;

	@Autowired
	public void setPetStore(PetStoreFacade petStore) {
		this.petStore = petStore;
	}
	
	//getItemListByProduct 대신 로그인 여부 확인하는 거 사용하기 로그인 세션 이용해섷 
	@GetMapping
	@RequestMapping("/accessory/{productName}/{categoryId}")
	public String viewEarringItem(
			@PathVariable int categoryId,
			@PathVariable String productName, 
			@RequestParam(value="page", defaultValue="1") int page,
			Model model) { //@RequestParam(value="page", required=false) String page // https://pythonq.com/so/spring/1003345
		Product prd = petStore.getProductByName(productName, categoryId);
		//System.out.println("rests = " + prd.getName() + ", test = " + prd.getProductId());
		List<Item> tmpList = petStore.getItemListByProduct(prd.getProductId());
		PagedListHolder<Item> itemListPage = new PagedListHolder<Item>(tmpList);
		itemListPage.setPageSize(12);

		itemListPage.setPage(page - 1);
		totalPageSize = tmpList.size() / 12;
		if(tmpList.size() % 12 != 0) {
			totalPageSize++;
		}
		
		List<Item> itemList = itemListPage.getPageList();
		for(Item i : itemList) {
			System.out.println(i.getTitle());
		}
		model.addAttribute("itemList", itemList);
		model.addAttribute("totalPageSize", totalPageSize);
		
		return "EarringItem";
	}

	/*
	 * @RequestMapping("/shop/viewItem.do") public String handleRequest(
	 * 
	 * @RequestParam("itemId") String itemId, ModelMap model) throws Exception {
	 * Item item = this.petStore.getItem(itemId); model.put("item", item);
	 * model.put("product", item.getProduct()); return "Item"; }
	 */

}
