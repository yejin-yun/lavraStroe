package com.example.lavrastore.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.dao.DataAccessException;
import com.example.lavrastore.domain.Member;

public interface MemberDao {
	Member getMember(String memberId) throws DataAccessException;

	Member getMember(String memberId, String password) throws DataAccessException;

	void insertMember(Member Member) throws DataAccessException;

	void updateMember(Member Member) throws DataAccessException;

	List<String> getUsernameList() throws DataAccessException;
	
	/*
	public boolean loginCheck(Member member);
	
	public Member viewMember(Member member);
	
	public void logout(HttpSession session);
	*/
}
