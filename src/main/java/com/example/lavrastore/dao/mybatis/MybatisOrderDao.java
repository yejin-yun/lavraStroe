package com.example.lavrastore.dao.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.lavrastore.dao.OrderDao;
import com.example.lavrastore.dao.mybatis.mapper.GroupItemMapper;
import com.example.lavrastore.dao.mybatis.mapper.GroupOrderMapper;
import com.example.lavrastore.dao.mybatis.mapper.OrderMapper;
import com.example.lavrastore.domain.GroupOrder;
import com.example.lavrastore.domain.Order;

@Repository
public class MybatisOrderDao implements OrderDao{

	@Autowired
	protected OrderMapper orderMapper;
//	@Autowired
//	protected GroupOrderMapper groupOrderMapper;
	
	@Autowired
	protected GroupItemMapper groupItemMapper;
	
	@Transactional
	public void insertOrder(Order order) throws DataAccessException {
		/* 작업중이었던 부분. _ 여기랑 ~mapper.xml 하고 있었음. 
		// 현재 insertPayment에서 현재 오류가 납니다. 
		orderMapper.insertOrder(order); 
    	orderMapper.insertPayment(order.getPayType());
    	
    	//카드결제일 경우 creditcard insert 추가
    	if(order.getPayType() == 1) {
    		orderMapper.insertCreditCard(order);
    	}
    	*/

//		아직 구현이 안됨. _ 틀 짜둔거 
//		//groupOrder용 추가 _ order의 카테고리가 공동구매(2)일 경우
//    	if(order.getCategoryId() == 2) {
//    		
//    		GroupOrder go = order.getGroupOrder();
//    		groupOrderMapper.insertGroupOrder(go);
//    		
//    		groupItemMapper.updateNowBalance(order.getTotalPrice());
//    	}
//    	
    	//쇼핑몰일 경우 카테고리가 1일 경우로 if문 묶어서, 개인은 카테고리가 3일 경우로 if문 묶어서 작업.
    	
    	
    	
    	
		
	}

}
