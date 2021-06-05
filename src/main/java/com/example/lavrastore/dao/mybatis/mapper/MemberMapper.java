package com.example.lavrastore.dao.mybatis.mapper;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Mapper;

import com.example.lavrastore.domain.Member;

@Mapper
public interface MemberMapper {
	Member getMemberByMemberId(String memberId);

	Member getMemberByMemberIdAndPassword(String memberId, String password);

	List<String> getUsernameList();
	  
	void insertMember(Member member);
	  
	void insertProfile(Member member);
	  
	void insertSignon(Member member);

	void updateMember(Member member);

	void updateProfile(Member member);

	void updateSignon(Member member);
	
	
	/*
	boolean loginCheck(Member member);
	
	Member viewMember(Member member);
	
	void logout(HttpSession session);
	*/
}
