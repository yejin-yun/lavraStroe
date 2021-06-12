package com.example.lavrastore.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class LineItem implements Serializable {
	  
	  private int lineitemId;
	  private int orderId;
	  private int quantity;
	  private int itemId;
	  private Item item;	
}
