package com.example.lavrastore.data.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lavrastore.domain.WishList;

public interface WishListRepository extends JpaRepository<WishList, Integer> {
	List<WishList> findByMemberId(String memberId);
	WishList findBywishListId(int wishListId);
}
