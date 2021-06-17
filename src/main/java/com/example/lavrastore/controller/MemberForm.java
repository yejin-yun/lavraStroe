package com.example.lavrastore.controller;

import java.io.Serializable;

import com.example.lavrastore.domain.Member;

/**
 * @author Juergen Hoeller
 * @since 01.12.2003
 */
@SuppressWarnings("serial")
public class MemberForm implements Serializable {

	private Member member;

	private boolean newMember;

	private String repeatedPassword;

	public MemberForm(Member member) {
		this.member = member;
		this.newMember = false;
	}

	public MemberForm() {
		this.member = new Member();
		this.newMember = true;
	}

	public Member getMember() {
		return member;
	}
	

	public boolean isNewMember() {
		return newMember;
	}

	public void setRepeatedPassword(String repeatedPassword) {
		this.repeatedPassword = repeatedPassword;
	}

	public String getRepeatedPassword() {
		return repeatedPassword;
	}
}
