package com.example.lavrastore.dao.mybatis;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.example.lavrastore.dao.ReviewDao;
import com.example.lavrastore.dao.mybatis.mapper.ProductMapper;
import com.example.lavrastore.dao.mybatis.mapper.ReviewMapper;
import com.example.lavrastore.domain.Review;

@Repository
public class MybatisReviewDao implements ReviewDao {

	@Autowired
	private ReviewMapper reviewMapper;

	public int getCntOfGroupItemByMember(int itemid, String memberId) throws DataAccessException {
		HashMap<String, String> checkOrder = new HashMap<String, String>(); 
		checkOrder.put("itemId", String.valueOf(itemid));
		checkOrder.put("memberId", memberId);
		
		return reviewMapper.getCntOfGroupItemByMember(checkOrder);
	}

	@Override
	public int getCntOfItemByMember(int itemid, String memberId) throws DataAccessException {
		HashMap<String, String> checkOrder = new HashMap<String, String>(); 
		checkOrder.put("itemId", String.valueOf(itemid));
		checkOrder.put("memberId", memberId);
		
		return reviewMapper.getCntOfItemByMember(checkOrder);
	}

	
	@Override
	public int insertReview(Review review) throws DataAccessException {
		return reviewMapper.insertReview(review);
	}

	@Override
	public int deleteReview(int reviewId) throws DataAccessException {
		// TODO Auto-generated method stub
		return reviewMapper.deleteReview(reviewId);
	}

	@Override
	public List<Review> getReviewByItem(int itemId) throws DataAccessException {
		// TODO Auto-generated method stub
		return reviewMapper.getReviewByItem(itemId);
	}

}
