package com.example.lavrastore.dao.mybatis;
import java.util.List;

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
		memberMapper.insertProfile(member);
		memberMapper.insertSignon(member);
	}

	public void updateMember(Member member) throws DataAccessException {
		memberMapper.updateMember(member);
		memberMapper.updateProfile(member);
		if (member.getPassword() != null && member.getPassword().length() > 0) 
		{
			memberMapper.updateSignon(member);
		}
	}
 
	public List<String> getUsernameList() throws DataAccessException {
		return memberMapper.getUsernameList();
	}
}
