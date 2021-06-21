package com.example.lavrastore.dao.mybatis;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.lavrastore.dao.PTPItemDao;
import com.example.lavrastore.dao.SequenceDao;
import com.example.lavrastore.dao.mybatis.mapper.ItemMapper;
import com.example.lavrastore.dao.mybatis.mapper.PTPItemMapper;
import com.example.lavrastore.dao.mybatis.mapper.WishListMapper;
import com.example.lavrastore.domain.PTPItem;

@Repository
public class MybatisPTPItemDao implements PTPItemDao {

	@Autowired
	private PTPItemMapper PTPItemMapper;
	
	@Autowired
	private ItemMapper itemMapper;
	
	@Autowired
	private WishListMapper wishListMapper;
	
	@Autowired
	private SequenceDao sequenceDao;
	
	
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
	
	public int updateState(int itemId) throws DataAccessException {
		return PTPItemMapper.updateState(itemId);
	}
	
	@Transactional
	public int insertPItem(PTPItem pitem) throws DataAccessException {
		pitem.setPTPItemId(sequenceDao.getNextId("seq_pk_ptp"));
		pitem.getItem().setItemId(pitem.getPTPItemId());
		System.out.println("[[ptpItemId : " + pitem.getPTPItemId());
		itemMapper.insertPTPItem(pitem.getItem());
		PTPItemMapper.insertPItem(pitem);
		
		return 1;
	}
	
	@Transactional
	public int updatePItem(PTPItem pitem) throws DataAccessException {
		
		itemMapper.updateItem2(pitem.getItem());
		System.out.println("pierr");
		PTPItemMapper.updatePItem(pitem);
		return 1;
	}
	
	public int deletePItem(int itemId) throws DataAccessException {
		wishListMapper.deleteWishList2(itemId);
		PTPItemMapper.deletePItem(itemId);
		itemMapper.deleteItem(itemId);
		return 1;
	}

	@Override
	public List<PTPItem> getPItemListByMemberAndProduct(String memberId, int productId) throws DataAccessException {
		HashMap<String, String> hm = new HashMap<String, String>(); 
		hm.put("productId", String.valueOf(productId));
		hm.put("memberId", memberId);
		
		return PTPItemMapper.getPItemListByMemberAndProduct(hm);
	}

}
