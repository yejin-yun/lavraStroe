package com.example.lavrastore.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class GroupOrder implements Serializable {
	
	private int orderId;
	private int itemId;
	private int status;
	private int quantity;
	private GroupItem groupItem;
	
	public GroupOrder() {}
	
	public GroupOrder(int q, GroupItem gitem) {
		this.itemId = gitem.getItem().getItemId();
		this.status = 0;
		this.quantity = q;
		this.groupItem = gitem;
	}
	
	
	public int getOrderId() {
		return orderId;
	}
 

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}


	public int getItemId() {
		return itemId;
	}


	public void setItemId(int itemId) {
		this.itemId = itemId;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public GroupItem getGroupItem() {
		return groupItem;
	}


	public void setGroupItem(GroupItem groupItem) {
		this.groupItem = groupItem;
	}
	
}
