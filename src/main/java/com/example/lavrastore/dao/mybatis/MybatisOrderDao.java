package com.example.lavrastore.dao.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.lavrastore.dao.OrderDao;
import com.example.lavrastore.dao.SequenceDao;
import com.example.lavrastore.dao.mybatis.mapper.GroupItemMapper;
import com.example.lavrastore.dao.mybatis.mapper.OrderMapper;
import com.example.lavrastore.domain.GroupOrder;
import com.example.lavrastore.domain.Order;

@Repository
public class MybatisOrderDao implements OrderDao{

	@Autowired
	protected OrderMapper orderMapper;
	
	@Autowired
	protected GroupItemMapper groupItemMapper;
	
	@Autowired
	private SequenceDao sequenceDao;
	
	@Transactional
	public void insertOrder(Order order) throws DataAccessException {
		
		//orderid setting 및 order와 payment 테이블 추가. 
		order.setOrderId(sequenceDao.getNextId("seq_orderid"));
		orderMapper.insertOrder(order); 
    	orderMapper.insertPayment(order);
    	
    	//카드결제일 경우 creditcard insert 추가
    	if(order.getPayType() == 1) {
    		orderMapper.insertCreditCard(order);
    	}
  
		//아직 구현이 안됨. _ 틀 짜둔거 
		//groupOrder용 추가 _ order의 카테고리가 공동구매(2)일 경우
    	if(order.getCategoryId() == 2) {
    		
    		GroupOrder go = order.getGroupOrder();
    		go.setOrderId(order.getOrderId());
    		
    		orderMapper.insertGroupOrder(go);
    		groupItemMapper.updateNowBalance(go.getItemId(), order.getTotalPrice());
    	}
    	
    	//쇼핑몰일 경우 카테고리가 1일 경우로 if문 묶어서, 개인은 카테고리가 3일 경우로 if문 묶어서 작업.
    	
	}

}
