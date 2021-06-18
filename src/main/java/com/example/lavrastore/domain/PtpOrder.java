package com.example.lavrastore.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class PtpOrder implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int orderId;
	private String sellerId; //판매자
	private String buyerId; //구매자
	private String shipCompany; //배송회사
	private String trackNum; //송장번호
	private int state; //0이면 구매가능 이 보다 크면 구매진행중이거나 거래 완료
	private String depositor; //입금자명
	
	public PtpOrder(PTPItem pitem, String memberId) {
		this.sellerId = pitem.sellerId;
		this.buyerId = memberId;
		this.state = 1;
		this.shipCompany = "CJ"; //기본값
		this.trackNum = "00000000000"; //기본값
		this.depositor = "홍길동";
	}

	public void PtpOrder() {}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	public String getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}

	public String getShipCompany() {
		return shipCompany;
	}

	public void setShipCompany(String shipCompany) {
		this.shipCompany = shipCompany;
	}

	public String getTrackNum() {
		return trackNum;
	}

	public void setTrackNum(String trackNum) {
		this.trackNum = trackNum;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getDepositor() {
		return depositor;
	}

	public void setDepositor(String depositor) {
		this.depositor = depositor;
	}

	
	
}
