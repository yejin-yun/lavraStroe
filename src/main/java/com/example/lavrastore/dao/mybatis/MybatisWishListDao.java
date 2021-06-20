package com.example.lavrastore.dao.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.example.lavrastore.dao.WishListDao;
import com.example.lavrastore.dao.mybatis.mapper.WishListMapper;
import com.example.lavrastore.domain.Item;
import com.example.lavrastore.domain.WishList;

@Repository
public class MybatisWishListDao implements WishListDao {
	@Autowired
	private WishListMapper wishListMapper;
	
	@Override
	public List<WishList> getAllWishList() throws DataAccessException {
		return wishListMapper.getAllWishList();
	}
	
	public int getWishListByItemIdAndMemberId(int itemId, String memberId) throws DataAccessException {
		return wishListMapper.getWishListByItemIdAndMemberId(itemId, memberId);
	}


	@Override
	public Item getItemByWishListId(int wishListId) throws DataAccessException {
		return (Item) wishListMapper.getItemByWishListId(wishListId);
	}

	@Override
	public int insertWishList(WishList wishList) throws DataAccessException {
		return wishListMapper.insertWishList(wishList);
	}


	@Override
	public int deleteWishList(int wishListId) throws DataAccessException {
		return wishListMapper.deleteWishList(wishListId);
	}
	/*
	 * @Override public int deleteWishList2(int itemId) throws DataAccessException {
	 * return wishListMapper.deleteWishList2(itemId); }
	 */
	
	public int deleteWishListByItemIdAndMemberId(int itemId, String memberId) throws DataAccessException {
		return wishListMapper.deleteWishListByItemIdAndMemberId(itemId, memberId);
	}

}
