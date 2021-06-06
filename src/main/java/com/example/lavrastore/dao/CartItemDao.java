package com.example.lavrastore.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.lavrastore.domain.CartItem;
import com.example.lavrastore.domain.Item;


public interface CartItemDao {
	/* Read */
	List<CartItem> getAllCartList() throws DataAccessException;
	List<CartItem> getCartListByGeneralCategory(int categoryId, String memberId) throws DataAccessException;
	List<CartItem> getCartListByGroupCategory(int categoryId, String memberId) throws DataAccessException;
	List<CartItem> getCartListByPTPCategory(int categoryId, String memberId) throws DataAccessException;
	Item getItemByCartItemId(int cartItemId, String memberId) throws DataAccessException; //cartItemId로 cartItem에 있는 Item 가져오기
	
	/* Create */
	int insertCartItem(CartItem cartItem) throws DataAccessException;
	
	/* Update */
	int updateCartItem(CartItem cartItem) throws DataAccessException; // ppt에 있는 메소드들 합침. 
	
	/* Delete */
	int deleteCartItem(int cartItemId) throws DataAccessException;
}
