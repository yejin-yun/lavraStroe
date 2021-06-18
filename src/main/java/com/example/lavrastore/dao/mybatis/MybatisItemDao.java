package com.example.lavrastore.dao.mybatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.example.lavrastore.dao.ItemDao;
import com.example.lavrastore.dao.mybatis.mapper.ItemMapper;
import com.example.lavrastore.domain.Item;

@Repository
public class MybatisItemDao implements ItemDao {
	@Autowired
	private ItemMapper itemMapper;

	public List<Item> searchItemList(String keywords){
		return itemMapper.searchItemList(keywords);
	}
	
	public List<Item> getItemListByProduct(int productId) throws DataAccessException {
		return itemMapper.getItemListByProduct(productId);
	}

	public Item getItem(int itemId) throws DataAccessException {
		return itemMapper.getItem(itemId);
	}

	public List<Item> getItemForNotUser(int productId) throws DataAccessException {
		return itemMapper.getItemForNotUser(productId);
	}

	public List<Item> getItemForUser(String memberId, int productId) throws DataAccessException {
		return itemMapper.getItemForUser(memberId, productId);
	}

	public List<Item> getItemOrderByHighPriceForUser(String memberId, int productId) throws DataAccessException {
		return itemMapper.getItemOrderByHighPriceForUser(memberId, productId);
	}

	public List<Item> getItemOrderByHighPriceForNotUser(int productId) throws DataAccessException {
		return itemMapper.getItemOrderByHighPriceForNotUser(productId);
	}

	public List<Item> getItemOrderByLowPriceForUser(String memberId, int productId) throws DataAccessException {
		return itemMapper.getItemOrderByLowPriceForUser(memberId, productId);
	}

	public List<Item> getItemOrderByLowPriceForNotUser(int productId) throws DataAccessException {
		return itemMapper.getItemOrderByLowPriceForNotUser(productId);
	}

	public int insertItem(int itemId) throws DataAccessException {
		return itemMapper.insertItem(itemId);
	}

	public int updateItem(Item item) throws DataAccessException {
		return itemMapper.updateItem(item);
	}

	public int deleteItem(int itemId) throws DataAccessException {
		return itemMapper.deleteItem(itemId);
	}

	public List<Item> getItemListTopKByLikeCnt(int categoryId, int number) throws DataAccessException {
		return itemMapper.getItemListTopKByLikeCnt(categoryId, number);
	}
	
	public int updateDescAndPriceItem(String description, int price, int itemId) throws DataAccessException {
		HashMap<String, String> hm = new HashMap<String, String>(); 
		hm.put("itemId", String.valueOf(itemId));
		hm.put("price", String.valueOf(price));
		hm.put("description", description);
		
		return itemMapper.updateDescAndPriceItem(hm);
		
	}

}
