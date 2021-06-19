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
	
	//groupItem용 (login) 
	List<GroupItem> getGItemListByMember(@Param("memberId")String memberId); 
	
	//group- 마감일 임박순 
	//List<GroupItem> getGItemListByDeadline();
	//group - 달성율 높은 순
	//group - 달성율 낮은순
	List<GroupItem> getGItmeListByLowPercent();
	//group - 달성율 낮은순
	List<GroupItem>getGItemListByLowPercentAndMember(@Param("memberId")String memberId);
		//group - 달성율 낮은순
	List<GroupItem> getGItmeListByPercent();
	//group - 달성율 낮은순
	List<GroupItem>getGItemListByPercentAndMember(@Param("memberId")String memberId);
		
	//공구 주문 시 현재 모인 금액 update
	int updateNowBalance(@Param("itemId") int itemId, @Param("total") int total);
	
	// 마감 시간이 되면, 공구 진행 상태 update
	void closeEvent(Date curTime);

	
}
