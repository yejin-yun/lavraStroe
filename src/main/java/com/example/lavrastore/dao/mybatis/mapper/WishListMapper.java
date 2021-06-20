package com.example.lavrastore.dao.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.lavrastore.domain.Item;
import com.example.lavrastore.domain.WishList;

@Mapper
public interface WishListMapper {
	/* Read */
	List<WishList> getAllWishList();
	Item getItemByWishListId(int wishListId);
	int getWishListByItemIdAndMemberId(int itemId, String memberId);
	
	/* Create */
	int insertWishList(WishList wishList);
	
	/* Delete */
	int deleteWishList(int wishListId);
	int deleteWishList2(int itemId);
	int deleteWishListByItemIdAndMemberId(int itemId, String memberId);
}
