package com.example.lavrastore.dao.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.example.lavrastore.domain.WishList;

@Mapper
public interface WishListMapper {
	/* Read */
	List<WishList> getAllWishList();
	List<WishList> getItemByWishListId(int wishListId); //cartItemId로 cartItem에 있는 Item 가져오기
	
	/* Create */
	int insertWishList(WishList wishList);
	
	/* Update */
	int updateWishList(WishList wishList); // ppt에 있는 메소드들 합침. 
	
	/* Delete */
	int deleteWishList(WishList wishList);
}
