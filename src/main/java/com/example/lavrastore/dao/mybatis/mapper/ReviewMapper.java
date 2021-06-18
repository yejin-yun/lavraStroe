package com.example.lavrastore.dao.mybatis.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.lavrastore.domain.Review;

@Mapper
public interface ReviewMapper {

	 int getCntOfGroupItemByMember(HashMap<String, String> checkOrder);
	 int getCntOfItemByMember(HashMap<String, String> checkOrder);
	 
	 int insertReview(Review review);
	 int deleteReview(int reviewId);
	 List<Review> getReviewByItem(int itemId);
}
