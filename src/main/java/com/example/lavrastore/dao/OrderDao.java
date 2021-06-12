package com.example.lavrastore.dao;

import org.springframework.dao.DataAccessException;

import com.example.lavrastore.domain.Order;



public interface OrderDao {
	
	void insertOrder(Order order) throws DataAccessException;

}
