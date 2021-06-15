package com.example.lavrastore.domain;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class Order implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int orderId;
	private String orderDate;
	private String shipAddr1;
	private String shipAddr2;
	private String shipZip;
	private int totalPrice;
	private String shipname;
	private String memberId;
	private int categoryId;
	
	private int paymentId; 
	private int payType; // 0 : 무통장입금, 1 : 카드결제
	
	//payType이 1일 경우 채워져야하는 변수들
	private String cardNum; 
	private String expiryDate;
	private String cardType;
	
	//각 주문별 선택 필드들...
	private List<LineItem> lineItems = new ArrayList<LineItem>();
	private GroupOrder groupOrder;
	
	//생성자
	public Order() {}
	
	public void initGroupOrder(GroupItem gItem, Member member, int amount) {
		this.categoryId = 2;
		this.memberId = member.getMemberId();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");     
		this.orderDate = dateFormat.format(new Date());
		
		this.shipAddr1 = member.getAddr1();
		this.shipAddr2 = member.getAddr2();
		this.shipZip = member.getZip();
		
		this.totalPrice = gItem.getItem().getPrice() * amount;
		this.shipname = member.getUsername();
		
		this.payType = 1;
		this.cardNum = ""; 
		this.expiryDate ="";
		this.cardType = "Visa";
		
		this.groupOrder = new GroupOrder(amount, gItem);
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getShipAddr1() {
		return shipAddr1;
	}

	public void setShipAddr1(String shipAddr1) {
		this.shipAddr1 = shipAddr1;
	}

	public String getShipAddr2() {
		return shipAddr2;
	}

	public void setShipAddr2(String shipAddr2) {
		this.shipAddr2 = shipAddr2;
	}

	public String getShipZip() {
		return shipZip;
	}

	public void setShipZip(String shipZip) {
		this.shipZip = shipZip;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getShipname() {
		return shipname;
	}

	public void setShipname(String shipname) {
		this.shipname = shipname;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberid) {
		this.memberId = memberid;
	}

	public List<LineItem> getLineItems() {
		return lineItems;
	}

	public void setLineItems(List<LineItem> lineItems) {
		this.lineItems = lineItems;
	}

	public GroupOrder getGroupOrder() {
		return groupOrder;
	}

	public void setGroupOrder(GroupOrder groupOrder) {
		this.groupOrder = groupOrder;
	}

	public int getPayType() {
		return payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}
	
	
}
