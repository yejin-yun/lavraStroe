package com.example.lavrastore.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="WishList")
@SuppressWarnings("serial")
public class WishList implements Serializable  {
	@Id
	private int wishListId;
	@OneToOne
	@JoinColumn(name="itemId")
	private Item item;
	private String memberId;
	
	
	public int getWishListId() {
		return wishListId;
	}
	public void setWishListId(int wishListId) {
		this.wishListId = wishListId;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberID(String MemberId) {
		memberId = MemberId;
	}
}
