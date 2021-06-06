package com.example.lavrastore.dao.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.example.lavrastore.dao.CartItemDao;
import com.example.lavrastore.dao.mybatis.mapper.CartItemMapper;
import com.example.lavrastore.domain.CartItem;
import com.example.lavrastore.domain.Item;

@Repository
public class MybatisCartItemDao implements CartItemDao {
	@Autowired
	private CartItemMapper cartItemMapper;
	
	@Override
	public List<CartItem> getAllCartList() throws DataAccessException {
		return cartItemMapper.getAllCartList();
	}

	@Override
	public List<CartItem> getCartListByGeneralCategory(int categoryId, String memberId) throws DataAccessException {
		return cartItemMapper.getCartListByGeneralCategory(categoryId, memberId);
	}

	@Override
	public List<CartItem> getCartListByGroupCategory(int categoryId, String memberId) throws DataAccessException {
		return cartItemMapper.getCartListByGroupCategory(categoryId, memberId);
	}

	@Override
	public List<CartItem> getCartListByPTPCategory(int categoryId, String memberId) throws DataAccessException {
		return cartItemMapper.getCartListByPTPCategory(categoryId, memberId);
	}

	@Override
	public Item getItemByCartItemId(int cartItemId, String memberId) throws DataAccessException {
		return cartItemMapper.getItemByCartItemId(cartItemId, memberId);
	}

	@Override
	public int insertCartItem(CartItem cartItem) throws DataAccessException {
		return cartItemMapper.insertCartItem(cartItem);
	}

	@Override
	public int updateCartItem(CartItem cartItem) throws DataAccessException {
		return cartItemMapper.updateCartItem(cartItem);
	}

	@Override
	public int deleteCartItem(int cartItemId) throws DataAccessException {
		return cartItemMapper.deleteCartItem(cartItemId);
	}

}
