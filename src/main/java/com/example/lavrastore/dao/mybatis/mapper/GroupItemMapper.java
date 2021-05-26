package com.example.lavrastore.dao.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import com.example.lavrastore.domain.GroupItem;


@Mapper
public interface GroupItemMapper {

	/* Read */
	GroupItem getGItem(int itemId);
	
	//groupItem용 - read
	List<GroupItem> getAllGItemList();
	List<GroupItem> getGItemListByProduct(int productId);
	
	//내 주문목록 조회용(login)
	List<GroupItem> getGItemListByMember(String memberId); 
	
	//group- 마감일 임박순 
	List<GroupItem> getGItemListByDeadline();
	//group - 마감된 것
	List<GroupItem> getGItemListByWindUp();
	
	//group - 인기순
	List<GroupItem> getGItmeListByPercent();
		
	/* Create */
	int insertGItem();
	
	/* Update */
	int updateGItem(int itemId); 
	
	/* Delete */
	int deleteGItem(int itemId);
	
}
