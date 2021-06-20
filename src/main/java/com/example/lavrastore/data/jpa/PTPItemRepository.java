package com.example.lavrastore.data.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lavrastore.domain.PTPItem;

public interface PTPItemRepository extends JpaRepository<PTPItem, Integer> {
	List<PTPItem> findPItemListBySellerId(String sellerId);
}
