package com.example.lavrastore.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.lavrastore.domain.GroupItem;


public interface GroupItemDao {
	
	/* Read */
	GroupItem getGItem(int itemId) throws DataAccessException;
	
	//groupItem용 - read
	List<GroupItem> getAllGItemList() throws DataAccessException;
	List<GroupItem> getGItemListByProduct(String productId) throws DataAccessException;
	
	//내 주문목록 조회용(login)
	List<GroupItem> getGItemListByMember(String memberId) throws DataAccessException; 
	
	//group- 마감일 임박순 
	List<GroupItem> getGItemListByDeadline() throws DataAccessException;
	//group - 마감된 것
	List<GroupItem> getGItemListByWindUp() throws DataAccessException;
	
	//group - 인기순
	List<GroupItem> getGItmeListByPercent() throws DataAccessException;
		
	/* Create */
	int insertGItem() throws DataAccessException;
	
	/* Update */
	int updateGItem(int itemId) throws DataAccessException; 
	
	/* Delete */
	int deleteGItem(int itemId) throws DataAccessException;
	
}
