package com.example.lavrastore.dao.mybatis;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.example.lavrastore.dao.MemberDao;
import com.example.lavrastore.dao.mybatis.mapper.MemberMapper;
import com.example.lavrastore.domain.Member;

@Repository
public class MybatisMemberDao implements MemberDao {
	
	@Autowired
	private MemberMapper memberMapper;
	
	public Member getMember(String memberId) throws DataAccessException {
		return memberMapper.getMemberByMemberId(memberId);
	}

	public Member getMember(String memberId, String password) 
			throws DataAccessException {
		return memberMapper.getMemberByMemberIdAndPassword(memberId, password);
	}

	public void insertMember(Member member) throws DataAccessException {
		memberMapper.insertMember(member);
	}

	public void updateMember(Member member) throws DataAccessException {
		memberMapper.updateMember(member);
	}
 
	public List<String> getUsernameList() throws DataAccessException {
		return memberMapper.getUsernameList();
	}
	
	
	
	/*
	public boolean loginCheck(Member member) throws DataAccessException {
		return memberMapper.loginCheck(member);
	};
	
	public Member viewMember(Member member) {
		return memberMapper.viewMember(member);
	};
	
	public void logout(HttpSession session) {
		memberMapper.logout(session);
	};
	*/
}
