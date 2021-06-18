package com.example.lavrastore.dao.mybatis;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.example.lavrastore.dao.PTPItemDao;
import com.example.lavrastore.dao.mybatis.mapper.PTPItemMapper;
import com.example.lavrastore.domain.PTPItem;

@Repository
public class MybatisPTPItemDao implements PTPItemDao {

	@Autowired
	private PTPItemMapper PTPItemMapper;
	
	public PTPItem getPItem(int itemId, String sellerId) throws DataAccessException {
		HashMap<String, String> hm = new HashMap<String, String>(); 
		hm.put("itemId", String.valueOf(itemId));
		hm.put("sellerId", sellerId);
		
		return PTPItemMapper.getPItem(hm);
	}
	
	public List<PTPItem> getPItemListByProduct(int productId) throws DataAccessException {
		return PTPItemMapper.getPItemListByProduct(productId);
	}
	
	public List<PTPItem> getPItemListByHighPrice() throws DataAccessException {
		return PTPItemMapper.getPItemListByHighPrice();
	}
	
	public List<PTPItem> getPItemListByLowPrice() throws DataAccessException {
		return PTPItemMapper.getPItemListByLowPrice();
	}
	
	public int insertPItem() throws DataAccessException {
		return PTPItemMapper.insertPItem();
	}
	
	public int updatePItem(int itemId) throws DataAccessException {
		return PTPItemMapper.updatePItem(itemId);
	}
	
	public int deletePItem(int itemId) throws DataAccessException {
		return PTPItemMapper.deletePItem(itemId);
	}

	@Override
	public List<PTPItem> getPItemListByMemberAndProduct(String memberId, int productId) throws DataAccessException {
		HashMap<String, String> hm = new HashMap<String, String>(); 
		hm.put("productId", String.valueOf(productId));
		hm.put("memberId", memberId);
		
		return PTPItemMapper.getPItemListByMemberAndProduct(hm);
	}

}
