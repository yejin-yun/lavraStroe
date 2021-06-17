package com.example.lavrastore.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.lavrastore.domain.GroupItem;
import com.example.lavrastore.domain.Order;



public interface OrderDao {
	
	void insertOrder(Order order) throws DataAccessException;

	List<Order> getItemOrderListByMember(String memberId)  throws DataAccessException;

	List<Order> getPTPOrderListByMember(String memberId) throws DataAccessException;

	List<Order> getGroupOrderListByMember(String memberId) throws DataAccessException;

}
