package com.example.lavrastore.dao.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import com.example.lavrastore.domain.LineItem;

@Mapper
public interface LineItemMapper {
	List<LineItem> getLineItemByOrderId(int orderId);
	int insertLineItem(LineItem lineItem);
}
