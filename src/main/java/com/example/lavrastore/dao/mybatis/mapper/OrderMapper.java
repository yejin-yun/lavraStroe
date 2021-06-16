package com.example.lavrastore.dao.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.lavrastore.domain.GroupOrder;
import com.example.lavrastore.domain.Order;

@Mapper
public interface OrderMapper {

	void insertOrder(Order order);

	void insertPayment(Order order);
	void insertCreditCard(Order order);

	void insertGroupOrder(GroupOrder go);

	void updateGroupOrderStatus();
}
