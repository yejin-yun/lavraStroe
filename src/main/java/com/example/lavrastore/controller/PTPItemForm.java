package com.example.lavrastore.controller;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import com.example.lavrastore.domain.Item;
import com.example.lavrastore.domain.PTPItem;


@SuppressWarnings("serial")
public class PTPItemForm implements Serializable {

	private MultipartFile productPhoto;
	private Item item;
	private PTPItem ptpitem;

	public MultipartFile getProductPhoto() {
		return productPhoto;
	}

	public void setProductPhoto(MultipartFile productPhoto) {
		this.productPhoto = productPhoto;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public PTPItem getPtpitem() {
		return ptpitem;
	}

	public void setPtpitem(PTPItem ptpitem) {
		this.ptpitem = ptpitem;
	}
	
}
