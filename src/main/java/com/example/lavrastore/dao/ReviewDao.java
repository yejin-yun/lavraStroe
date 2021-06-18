package com.example.lavrastore.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.lavrastore.domain.Review;

public interface ReviewDao {
	
	
	//member가 해당 아이템 샀는지 확인용
	int getCntOfGroupItemByMember(int itemid, String memberId) throws DataAccessException;
	int getCntOfItemByMember(int itemid, String memberId) throws DataAccessException;

	//insert랑 delete
	int insertReview(Review review) throws DataAccessException;
		
	int deleteReview(int reviewId) throws DataAccessException;

	//read
	List<Review> getReviewByItem(int itemId) throws DataAccessException;
}
