package com.example.lavrastore.dao.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.example.lavrastore.dao.LineItemDao;
import com.example.lavrastore.dao.mybatis.mapper.LineItemMapper;
import com.example.lavrastore.domain.LineItem;


@Repository
public class MybatisLineItemDao implements LineItemDao{
	@Autowired
	private LineItemMapper lineItemMapper;

	@Override
	public List<LineItem> getLineItemByOrderId(int orderId) throws DataAccessException {
		return lineItemMapper.getLineItemByOrderId(orderId);
	}

	@Override
	public int insertLineItem(LineItem lineItem) throws DataAccessException {
		return lineItemMapper.insertLineItem(lineItem);
	}
	
}
