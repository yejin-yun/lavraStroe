package com.example.lavrastore.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.lavrastore.domain.PTPItem;

public interface PTPItemDao {

	PTPItem getPItem(int itemid, String sellerId) throws DataAccessException;
	PTPItem getPItem2(int itemId) throws DataAccessException;
	
	List<PTPItem> getPItemListByProduct(int productId) throws DataAccessException;
	List<PTPItem> getPItemListByMemberAndProduct(String memberId, int productId) throws DataAccessException;
	
	// 가격순
	List<PTPItem> getPItemListByHighPrice() throws DataAccessException;
	List<PTPItem> getPItemListByLowPrice() throws DataAccessException;
	
	/* CREATE */
	int insertPItem(PTPItem pitem) throws DataAccessException;
	
	/* UPDATE */
	int updatePItem(PTPItem pitem) throws DataAccessException;
	
	/* DELETE */
	int deletePItem(int itemId) throws DataAccessException;
}
