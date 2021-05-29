package com.example.lavrastore.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Item implements Serializable {
	/* Private Fields */
	private int itemId;
	private int productId;
	private String image;
	private String description;
	private String title;
	private int price;
	private int isSoldout;
	private int likeCnt;
	private int isInWishlist; //wishistId가 들어옴. 
	private int isInCart; //cartItemId가 들어옴.
	private Product product;
	private int quantity;

	/* JavaBeans Properties */

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getIsSoldout() {
		return isSoldout;
	}

	public void setIsSoldout(int isSoldout) {
		this.isSoldout = isSoldout;
	}

	public int getLikeCnt() {
		return likeCnt;
	}

	public void setLikeCnt(int likeCnt) {
		this.likeCnt = likeCnt;
	}

	public int getIsInWishlist() {
		return isInWishlist;
	}

	public void setIsInWishlist(int isInWishlist) {
		this.isInWishlist = isInWishlist;
	}

	public int getIsInCart() {
		return isInCart;
	}

	public void setIsInCart(int isInCart) {
		this.isInCart = isInCart;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String toString() {
		return "(" + getItemId() + "-" + getProductId() + ")";
	}
}