package com.example.lavrastore.domain;
import java.io.Serializable;
import com.example.lavrastore.domain.Item;

@SuppressWarnings("serial")
public class GroupItem implements Serializable {
	
	Item item;
	String regiDate;
	String paymentDate;
	int nowBalance;
	int targetBalance;
	double percent; // nowB / targetB
	int status; //0 : 진행 1 : 마감-성공 2: 마감-실패
	
	
	public Item getItem() {
		return item;
	}
	public void setGitem(Item item) {
		this.item = item;
	}
	public String getRegiDate() {
		return regiDate;
	}
	public void setRegiDate(String regiDate) {
		this.regiDate = regiDate;
	}
	public String getPaymentDate() {
		return paymentDate;
	}
	public void setPayDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
	public int getNowBalance() {
		return nowBalance;
	}
	public void setNowBalance(int nowBalance) {
		this.nowBalance = nowBalance;
		setPercent();
	}
	public int getTargetBalance() {
		return targetBalance;
	}
	public void setTargetBalance(int targetBalance) {
		this.targetBalance = targetBalance;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public double getPercent() {
		return percent;
	}
	public void setPercent() {
		if(nowBalance == 0 ) {
			this.percent = 0;
		} else if (targetBalance != 0) {
			double p = 100 * nowBalance / (double)targetBalance;
			this.percent = Math.round(p*100)/100.0;
		}
	}
	
}
