package com.example.lavrastore.controller;

import java.io.Serializable;
import com.example.lavrastore.domain.Member;


@SuppressWarnings("serial")
public class UserSession implements Serializable {

	private Member member;

	public UserSession(Member member) {
		this.member = member;
	}

	public Member getMember() {
		return member;
	}

}
