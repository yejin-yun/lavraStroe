package com.example.lavrastore.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class WishList {
	private int wishListId;
	private int ItemId;
	private String MemberID;
	
	public int getItemId() {
		return ItemId;
	}
	public void setItemId(int itemId) {
		ItemId = itemId;
	}
	public int getWishListId() {
		return wishListId;
	}
	public void setWishListId(int wishListId) {
		this.wishListId = wishListId;
	}
	public String getMemberID() {
		return MemberID;
	}
	public void setMemberID(String memberID) {
		MemberID = memberID;
	}
}
