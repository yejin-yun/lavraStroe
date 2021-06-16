package com.example.lavrastore.dao.mybatis.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.query.Param;

import com.example.lavrastore.domain.GroupItem;
import com.example.lavrastore.domain.Order;


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
		
	//공구 주문 시 현재 모인 금액 update
	int updateNowBalance(@Param("itemId") int itemId, @Param("total") int total);
	
	// 마감 시간이 되면, 공구 진행 상태 update
	void closeEvent(Date curTime);

	
}
