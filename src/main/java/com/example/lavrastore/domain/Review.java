package com.example.lavrastore.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Review implements Serializable {
	int reviewId;
	String reviewContent;
	int preference;
	String reviewDate;
	String memberId;
	int itemId;
	
	public Review() {}
	
	public Review(String content, int preference, String reviewDate, String memberId, int itemId) {
		this.itemId = itemId;
		this.reviewContent = content;
		this.preference = preference;
		this.reviewDate = reviewDate;
		this.memberId = memberId;
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public String getReviewContent() {
		return reviewContent;
	}

	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}

	public int getPreference() {
		return preference;
	}

	public void setPreference(int preference) {
		this.preference = preference;
	}

	public String getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	
	
}
