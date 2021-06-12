package com.example.lavrastore.domain;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="ITEM")
@SuppressWarnings("serial")
public class Item implements Serializable {
	/* Private Fields */
	@Id
	private int itemId;
	private int productId;
	private String image;
	private String description;
	private String title;
	private int price;
	@Transient //데이터 베이스에 저장되지 않는 비영속적 필드 
	private int isSoldout;
	private int likeCnt;
	@Transient
	private int isInWishlist; //wishistId가 들어옴. 
	@Transient
	private int isInCart; //cartItemId가 들어옴.
	@Transient
	private Product product; //여기 OneToOne, @JoinColumn(name="productId")하니까 에러남... productId 관련해서 에러나는 듯.
	private int quantity;

	public Item() {}
	
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