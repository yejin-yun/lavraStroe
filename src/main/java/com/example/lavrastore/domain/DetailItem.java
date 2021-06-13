package com.example.lavrastore.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class DetailItem implements Serializable{
	private Item item;
	private int quantity;
	private int itemTotalCost;
	private int price;
	
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
	public int getItemTotalCost() {
		return itemTotalCost;
	}
	public void setItemTotalCost(int itemTotalCost) {
		this.itemTotalCost = itemTotalCost;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
}
