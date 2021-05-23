package com.example.lavrastore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.lavrastore.domain.GroupItem;
import com.example.lavrastore.domain.Item;
import com.example.lavrastore.domain.Product;
import com.example.lavrastore.service.PetStoreFacade;

@Controller
@SessionAttributes("groupItemListPage")
public class viewGroupItemController {
	private PetStoreFacade petStore;

	@Autowired
	public void setPetStore(PetStoreFacade petStore) {
		this.petStore = petStore;
	}
	
	@GetMapping
	@RequestMapping("/group")
	public String viewAllGroupItem(
			@RequestParam(value="page", defaultValue="1") int page,
			Model model) { //@RequestParam(value="page", required=false) String page // https://pythonq.com/so/spring/1003345
		
		PagedListHolder<GroupItem> gitemListPage;
		gitemListPage = new PagedListHolder<GroupItem>(petStore.getAllGItemList());
//		if(productName.equals("all")) {
//			gitemListPage = new PagedListHolder<GroupItem>(petStore.getAllGItemList());
//		}else {
//			Product prd = petStore.getProductByName(productName, 2);
//			System.out.println("rests = " + prd.getName() + ", test = " + prd.getProductId());
//			gitemListPage = new PagedListHolder<GroupItem>(petStore.getGItemListByProduct(prd.getProductId()));
//		}
//		이 부분은 따로 request mapping해서 product랑 sort 받도록 변경.
		gitemListPage.setPageSize(12);

		gitemListPage.setPage(page);

		
		List<GroupItem> gitemList = gitemListPage.getPageList();
		for(GroupItem g : gitemList) {
			g.setPercent();
			System.out.println("itemid : " + g.getItem().getItemId() + " 진행도 : " + g.getPercent());
		}
		model.addAttribute("gitemList", gitemList);
		
		return "groupPage";
	}
	
	@GetMapping
	@RequestMapping("/group/viewItem")
	public String viewGroupItem(
			@RequestParam(value="itemNo", defaultValue="11024") int itemid, Model model
			) {
		
		GroupItem gitem = petStore.getGItem(itemid);
		model.addAttribute("gitem", gitem);
		System.out.println("itemid : " + gitem.getItem().getItemId() + " 진행도 : " + gitem.getRegiDate());
		return "groupItemPage";
	
	}
	
	
}
