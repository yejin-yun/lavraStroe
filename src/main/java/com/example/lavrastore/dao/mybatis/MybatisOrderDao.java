package com.example.lavrastore.dao.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.lavrastore.dao.OrderDao;
import com.example.lavrastore.dao.SequenceDao;
import com.example.lavrastore.dao.mybatis.mapper.GroupItemMapper;
import com.example.lavrastore.dao.mybatis.mapper.ItemMapper;
import com.example.lavrastore.dao.mybatis.mapper.LineItemMapper;
import com.example.lavrastore.dao.mybatis.mapper.OrderMapper;
import com.example.lavrastore.domain.GroupOrder;
import com.example.lavrastore.domain.Item;
import com.example.lavrastore.domain.LineItem;
import com.example.lavrastore.domain.Order;
import java.util.List;

@Repository
public class MybatisOrderDao implements OrderDao{

	private static final String List = null;

	@Autowired
	protected OrderMapper orderMapper;
	
	@Autowired
	protected GroupItemMapper groupItemMapper;
	
	@Autowired
	protected LineItemMapper lineItemMapper;
	
	@Autowired
	protected ItemMapper itemMapper;
	
	@Autowired
	private SequenceDao sequenceDao;
	
	@Transactional
	public void insertOrder(Order order) throws DataAccessException {
		
		/*
		 * System.out.println("order에서 넘어온 값 확인 :" + order.getCardNum() + ", " +
		 * order.getPayType() + "," + order.getLineItems().get(0).getItemId() + "," +
		 * order.getShipAddr1());
		 */
		
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
    	} else if(order.getCategoryId() == 1) {
    		List<LineItem> lineItems = order.getLineItems();
    		//order의 isInCart는 굳이 필요 없는 듯...? 어차피 카트가 아닌 곳에서 와도 리스트로 묶이니까...
    		
    		for(LineItem line : lineItems) {
    			line.setOrderId(order.getOrderId()); //itemId, quantity는 이미 line에 있음. Order의 init method
    			lineItemMapper.insertLineItem(line);
    			Item item = line.getItem();
    			int stock = item.getQuantity() - line.getQuantity();
    			item.setQuantity(stock);
    			if(stock > 0) {
    				itemMapper.updateItem(item); //재고를 줄임.
    			} else if(stock == 0) {
    				item.setIsSoldout(1);
    				itemMapper.updateItem(item);
    			}
    		}
    	}
    	
    	//쇼핑몰일 경우 카테고리가 1일 경우로 if문 묶어서, 개인은 카테고리가 3일 경우로 if문 묶어서 작업.
    	
	}

}
