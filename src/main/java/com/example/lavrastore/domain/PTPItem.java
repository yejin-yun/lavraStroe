package com.example.lavrastore.domain;

import java.io.Serializable;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.springframework.web.multipart.MultipartFile;

@SuppressWarnings("serial")
public class PTPItem implements Serializable {


	@OneToOne
	@JoinColumn(name="itemId")
	private Item item;
	int state; // 0: 진행중, 1: 거래중, 2: 거래 완료
	int devP;
	String sellerId;
	String bank;
	String account;
	private MultipartFile productPhoto;
	
	public PTPItem() {}
	
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getDevP() {
		return devP;
	}
	public void setDevP(int devP) {
		this.devP = devP;
	}
	public String getSellerId() {
		return sellerId;
	}
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	public MultipartFile getProductPhoto() {
		return productPhoto;
	}

	public void setProductPhoto(MultipartFile productPhoto) {
		this.productPhoto = productPhoto;
	}
	
}
