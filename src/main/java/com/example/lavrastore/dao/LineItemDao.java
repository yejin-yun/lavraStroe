package com.example.lavrastore.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.lavrastore.domain.LineItem;

public interface LineItemDao {
	List<LineItem> getLineItemByOrderId(int orderId) throws DataAccessException;
	int insertLineItem(LineItem lineItem) throws DataAccessException;
}
