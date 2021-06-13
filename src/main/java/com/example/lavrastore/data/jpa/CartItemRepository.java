package com.example.lavrastore.data.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.lavrastore.domain.CartItem;
import com.example.lavrastore.domain.Item;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
	List<CartItem> findByCategoryIdAndMemberId(int categoryId, String memberId);
	Item findItemByCategoryIdAndMemberId(int cartItemId, String memberId);
	CartItem findCartItemByItemItemIdAndMemberId(int itemId, String memberId);
}
