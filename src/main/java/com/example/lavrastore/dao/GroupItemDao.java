package com.example.lavrastore.dao;

import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.lavrastore.domain.GroupItem;
import com.example.lavrastore.domain.Order;


public interface GroupItemDao {
	
	/* Read */
	GroupItem getGItem(int itemId) throws DataAccessException;
	
	//groupItem용 - read
	List<GroupItem> getAllGItemList() throws DataAccessException;

	//(login)용 read
	List<GroupItem> getGItemListByMember(String memberId) throws DataAccessException; 
	
	//group- 마감일 임박순 
	//List<GroupItem> getGItemListByDeadline() throws DataAccessException;
	//group - 마감된 것
	List<GroupItem> getGItemListByLowPercent() throws DataAccessException;
	
	List<GroupItem> getGItemListByLowPercentAndMember(String memberId) throws DataAccessException;
	
	List<GroupItem> getGItmeListByPercent() throws DataAccessException;
	//group - 인기순
	List<GroupItem> getGItmeListByPercentAndMember(String memberId) throws DataAccessException; 
		

	// 시간이 되면 마감된 그룹 아이템의 상태와 관련 오더들의 상태를 update
	void closeEvent(Date curTime);
	
}
