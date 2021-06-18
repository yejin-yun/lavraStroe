package com.example.lavrastore.dao.mybatis.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.lavrastore.domain.PTPItem;

@Mapper
public interface PTPItemMapper {

	/* READ */
	PTPItem getPItem(int itemid);
	
	List<PTPItem> getPItemListByProduct(int productId);
	List<PTPItem> getPItemListByMemberAndProduct(HashMap<String, String> hm);
	
	List<PTPItem> getPItemListByHighPrice(); // 높은 가격순 정렬
	List<PTPItem> getPItemListByLowPrice(); // 낮은 가격순 정렬
	
	/* CREATE */
	int insertPItem();
	
	/* UPDATE */
	int updatePItem(int itemId);
	
	/* DELETE */
	int deletePItem(int itemId);

	
	
}
