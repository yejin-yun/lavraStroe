package com.example.lavrastore.dao.mybatis.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.lavrastore.domain.PTPItem;

@Mapper
public interface PTPItemMapper {

	/* READ */
	PTPItem getPItem(HashMap<String, String> hm); //sellerid랑 itemid
	PTPItem getPItem2(int itemId);
	
	List<PTPItem> getPItemListByProduct(int productId);
	List<PTPItem> getPItemListByMemberAndProduct(HashMap<String, String> hm);
	
	List<PTPItem> getPItemListByHighPrice(); // 높은 가격순 정렬
	List<PTPItem> getPItemListByLowPrice(); // 낮은 가격순 정렬
	
	int updateState(int itemId);
	
	/* CREATE */
	int insertPItem(PTPItem pItem);
	
	/* UPDATE */
	int updatePItem(PTPItem pItem);
	
	/* DELETE */
	int deletePItem(int itemId);

	
	
}
