package com.example.lavrastore.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class LineItem implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int lineItemId;
	private int orderId;
	private int quantity;
	private int itemId;
	private Item item;
	
	public LineItem() {
		
	}
	
	public LineItem(int quantity, int itemId, Item item) {
		this.quantity = quantity;
		this.itemId = itemId;
		this.item = item;
	}

	

	public int getLineItemId() {
		return lineItemId;
	}

	public void setLineItemId(int lineItemId) {
		this.lineItemId = lineItemId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

}
