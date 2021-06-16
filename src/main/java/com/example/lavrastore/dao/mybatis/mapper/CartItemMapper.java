package com.example.lavrastore.dao.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.lavrastore.domain.CartItem;
import com.example.lavrastore.domain.Item;


@Mapper //이거 붙여줘야 함. MyBatis~Dao에는 @Repository 붙여줘야 함.
public interface CartItemMapper {
	/* Read */
	List<CartItem> getAllCartList();
	List<CartItem> getCartListByGeneralCategory(int categoryId, String memberId);
	List<CartItem> getCartListByGroupCategory(int categoryId, String memberId);
	List<CartItem> getCartListByPTPCategory(int categoryId, String memberId);
	Item getItemByCartItemId(int cartItemId, String memberId); //cartItemId로 cartItem에 있는 Item 가져오기
	CartItem getCartItemById(int cartItemId);
	
	/* Create */
	int insertCartItem(CartItem cartItem);
	
	/* Update */
	int updateCartItem(CartItem cartItem); // ppt에 있는 메소드들 합침. 
	int updateQuantity(int cartItemId, int quantity);
	
	/* Delete */
	int deleteCartItem(int cartItemId);
}
