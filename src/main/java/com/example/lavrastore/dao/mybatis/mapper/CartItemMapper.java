package com.example.lavrastore.dao.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.lavrastore.domain.CartItem;
import com.example.lavrastore.domain.Item;


@Mapper //이거 붙여줘야 함. MyBatis~Dao에는 @Repository 붙여줘야 함.
public interface CartItemMapper {
	/* Read */
	List<CartItem> getAllCartList();
	List<CartItem> getCartListByGeneralCategory(int categoryId);
	List<CartItem> getCartListByGroupCategory(int categoryId);
	List<CartItem> getCartListByPTPCategory(int categoryId);
	Item getItemByCartItemId(int cartItemId); //cartItemId로 cartItem에 있는 Item 가져오기
	
	/* Create */
	int insertCartItem(CartItem cartItem);
	
	/* Update */
	int updateCartItem(CartItem cartItem); // ppt에 있는 메소드들 합침. 
	
	/* Delete */
	int deleteCartItem(int cartItemId);
}
