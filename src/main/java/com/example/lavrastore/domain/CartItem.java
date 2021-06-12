package com.example.lavrastore.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="CartItem")
@SuppressWarnings("serial")
public class CartItem implements Serializable {

	/* Private Fields */

	/*
	 * private PetStoreFacade lavraStore; //주입이 안되었을 것. CartItem이 bean 아니니까..? 왜
	 * 안돼지...?
	 * componentscan이 기본적으로 되는 곳은 메인 클래스가 있는 패키지임. 
	 */	 
	@Id
	private int cartItemId;
	private int categoryId;
	private String memberId;
	@OneToOne
	@JoinColumn(name="itemId")
	private Item item;
	private int quantity;
	
	public CartItem() {};
	

	public int getCartItemId() {
		return cartItemId;
	}
	public void setCartItemId(int cartItemId) {
		this.cartItemId = cartItemId;
	}

	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


}
