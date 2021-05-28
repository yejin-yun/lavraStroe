package com.example.lavrastore.controller;

import java.io.Serializable;
import org.springframework.beans.support.PagedListHolder;

import com.example.lavrastore.domain.Member;
import com.example.lavrastore.domain.Product;


/**
 * @author Juergen Hoeller
 * @since 30.11.2003
 */
@SuppressWarnings("serial")
public class UserSession implements Serializable {

	private Member member;

	private PagedListHolder<Product> myList;

	public UserSession(Member member) {
		this.member = member;
	}

	public Member getMember() {
		return member;
	}

	public void setMyList(PagedListHolder<Product> myList) {
		this.myList = myList;
	}

	public PagedListHolder<Product> getMyList() {
		return myList;
	}
}
