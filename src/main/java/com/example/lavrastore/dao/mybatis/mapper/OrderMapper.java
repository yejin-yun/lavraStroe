package com.example.lavrastore.dao.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.lavrastore.domain.GroupItem;
import com.example.lavrastore.domain.GroupOrder;
import com.example.lavrastore.domain.Order;

@Mapper
public interface OrderMapper {

	void insertOrder(Order order);

	void insertPayment(Order order);
	void insertCreditCard(Order order);

	void insertGroupOrder(GroupOrder go);

	void updateGroupOrderStatus();

	List<Order> getItemOrderListByMember(String memberId);

	List<Order> getPTPOrderListByMember(String memberId) ;

	List<Order> getGroupOrderListByMember(String memberId); 
	
	Order getOrderById(int orderId);
}
