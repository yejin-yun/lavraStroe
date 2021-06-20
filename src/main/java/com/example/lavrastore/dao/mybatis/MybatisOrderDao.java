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
import com.example.lavrastore.domain.PtpOrder;

import java.util.List;
import java.util.Random;

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
    	} else if (order.getCategoryId() == 3) {
    			//PtpOrder 객체를 초기화 해준다. order.getPtPItem....
    			// 이때 초기화 전에 order.setPtpOrder().set송장번호, set택배사를 해준다. 
    		// 1. orderMapper.insertPtpOrder(....)을 추가해준다. 
    		// 2. ptpItemMapper에 state 를 update 하는 쿼리를 만들어서. state를 2로 update해준다.
    		PtpOrder po = order.getPtpOrder();
    		po.setOrderId(order.getOrderId());
    		po.setShipCompany("CJ");
    		
    		Random r = new Random();
    		String s =  "";
    		for (int i = 0; i < 10; i++)
    			s += Integer.toString(r.nextInt(9));
    		
    		po.setTrackNum(s);
    		
    		orderMapper.insertPtpOrder(po);
    		
    	}
    	
    	//쇼핑몰일 경우 카테고리가 1일 경우로 if문 묶어서, 개인은 카테고리가 3일 경우로 if문 묶어서 작업.
    	
	}

	public List<Order> getItemOrderListByMember(String memberId) throws DataAccessException {
		return orderMapper.getItemOrderListByMember(memberId);
	}

	public List<Order> getPTPOrderListByMember(String memberId) throws DataAccessException {
		return orderMapper.getPTPOrderListByMember(memberId);
	}

	public List<Order> getGroupOrderListByMember(String memberId) throws DataAccessException {
		return orderMapper.getGroupOrderListByMember(memberId);
	}
	
	public Order getOrderById(int orderId) throws DataAccessException {
		return orderMapper.getOrderById(orderId);
	}

}
