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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.lavrastore.domain.PTPItem;
import com.example.lavrastore.domain.Product;
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
		list.add("id");
		list.add("highPrice");
		list.add("lowPrice");

		return list;
	}
	
	@GetMapping
	@RequestMapping("/accessory/{productName}/2")
	@Transactional
	public String ViewEarringPItem(@PathVariable String productName,
			@RequestParam(value="page", defaultValue="1") int page,
			@RequestParam(value="sort", defaultValue="id") String sort, Model model) {
		
		Product prd = petStore.getProductByName(productName, 2);
		
		List<PTPItem> pList = null;
		switch (sort) {
		case "id":
			pList = petStore.getPItemListByProduct(prd.getProductId());
			break;
		case "highPrice":
			pList = petStore.getPItemListByHighPrice();
			break;
		case "lowPrice":
			pList = petStore.getPItemListByLowPrice();
			break;
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
	
	@GetMapping("/accessory/viewItem")
	public String viewPTPItem(@RequestParam(value="ItemNo", defaultValue="-1") int itemid, Model model) {
		PTPItem pitem = petStore.getPItem(itemid);
		
		model.addAttribute("pitem", pitem);
		
		return "ptpPage";
	}
	
}
