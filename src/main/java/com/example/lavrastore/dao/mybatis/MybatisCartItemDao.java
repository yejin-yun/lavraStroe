package com.example.lavrastore.dao.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.example.lavrastore.dao.CartItemDao;
import com.example.lavrastore.dao.mybatis.mapper.CartItemMapper;
import com.example.lavrastore.domain.CartItem;

@Repository
public class MybatisCartItemDao implements CartItemDao {
	@Autowired
	private CartItemMapper cartItemMapper;
	
	@Override
	public List<CartItem> getAllCartList() throws DataAccessException {
		return cartItemMapper.getAllCartList();
	}

	@Override
	public List<CartItem> getCartListByGeneralCategory(int categoryId) throws DataAccessException {
		return cartItemMapper.getCartListByGeneralCategory(categoryId);
	}

	@Override
	public List<CartItem> getCartListByGroupCategory(int categoryId) throws DataAccessException {
		return cartItemMapper.getCartListByGroupCategory(categoryId);
	}

	@Override
	public List<CartItem> getCartListByPTPCategory(int categoryId) throws DataAccessException {
		return cartItemMapper.getCartListByPTPCategory(categoryId);
	}

	@Override
	public List<CartItem> getItemByCartItemId(int cartItemId) throws DataAccessException {
		return cartItemMapper.getItemByCartItemId(cartItemId);
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
