package com.example.lavrastore.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.lavrastore.domain.PTPItem;

public interface PTPItemDao {

	PTPItem getPItem(int itemid, String sellerId) throws DataAccessException;
	
	List<PTPItem> getPItemListByProduct(int productId) throws DataAccessException;
	List<PTPItem> getPItemListByMemberAndProduct(String memberId, int productId) throws DataAccessException;
	
	// 가격순
	List<PTPItem> getPItemListByHighPrice() throws DataAccessException;
	List<PTPItem> getPItemListByLowPrice() throws DataAccessException;
	
	/* CREATE */
	int insertPItem() throws DataAccessException;
	
	/* UPDATE */
	int updatePItem(int itemId) throws DataAccessException;
	
	/* DELETE */
	int deletePItem(int itemId) throws DataAccessException;
}
