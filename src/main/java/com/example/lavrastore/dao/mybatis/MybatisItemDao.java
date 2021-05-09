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
	


	public List<Item> getItemListByProduct(int productId) 
			throws DataAccessException {
		return itemMapper.getItemListByProduct(productId);
	}

	public Item getItem(int itemId) throws DataAccessException {
		return itemMapper.getItem(itemId);
	}

	public List<Item> getItemForNotUser() throws DataAccessException {
		return itemMapper.getItemForNotUser();
	}

	public List<Item> getItemForUser(String memberId) throws DataAccessException {
		return itemMapper.getItemForUser(memberId);
	}

	public List<Item> getItemOrderByHighPriceForUser(String memberId) throws DataAccessException {
		return itemMapper.getItemOrderByHighPriceForUser(memberId);
	}

	public List<Item> getItemOrderByHighPriceForNotUser() throws DataAccessException {
		return itemMapper.getItemOrderByHighPriceForNotUser();
	}

	public List<Item> getItemOrderByLowPriceForUser(String memberId) throws DataAccessException {
		return itemMapper.getItemOrderByLowPriceForUser(memberId);
	}

	public List<Item> getItemOrderByLowPriceForNotUser() throws DataAccessException {
		return itemMapper.getItemOrderByLowPriceForNotUser();
	}

	public int insertItem(int itemId) throws DataAccessException {
		return itemMapper.insertItem(itemId);
	}

	public int updateItem(int itemId) throws DataAccessException {
		return itemMapper.updateItem(itemId);
	}

	public int deleteItem(int itemId) throws DataAccessException {
		return itemMapper.deleteItem(itemId);
	}
	 
}
