package com.example.lavrastore.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.example.lavrastore.domain.CartItem;
import com.example.lavrastore.domain.Category;
import com.example.lavrastore.domain.GroupItem;
import com.example.lavrastore.domain.Item;
import com.example.lavrastore.domain.LineItem;
import com.example.lavrastore.domain.Member;
import com.example.lavrastore.domain.Order;
import com.example.lavrastore.domain.PTPItem;
import com.example.lavrastore.domain.Product;
import com.example.lavrastore.domain.Review;
import com.example.lavrastore.domain.WishList;

/**
 * JPetStore's central business interface.
 *
 * @author Juergen Hoeller
 * @since 30.11.2003
 */
public interface PetStoreFacade {


	/* Category */

	List<Category> getCategoryList();
	Category getCategoryById(int categoryId);
	int insertCategory(Category category); // 0이면 실패, 0 초과는 성공
	int updateCategory(Category category);
	int deleteCategory(String categoryId);
	
	
	/* Member */
	
	Member getMember(String memberid);

	Member getMember(String memberid, String password);

	void insertMember(Member member);

	void updateMember(Member member);

	List<String> getUsernameList();

	/* Product */
	
	List<Product> getProductListByCategory(int categoryId);
	Product getProductById(int productId);
	Product getProductByName(String name, int categoryId);
	Category getCategoryByProId(int productId);
	int insertProduct(Product product);
	int updateProduct(Product product); // 기존에 설계한 것을 합쳐버림(ppt 참고)
	int deleteProduct(Product product);
	List<Product> searchProductList(String keywords);
	

	/* Item */
	List<Item> getItemListTopKByLikeCnt(int categoryId, int number);
	List<Item> searchItemList(String keywords);
	List<Item> getItemListByProduct(int productId);
	Item getItem(int itemId);
	List<Item> getItemForNotUser(int productId); // 인기순으로 되어 있음. 비로그인 용
	List<Item> getItemForUser(String memberId, int productId);

	//높은 가격순
	List<Item> getItemOrderByHighPriceForUser(String memberId, int productId);
	List<Item> getItemOrderByHighPriceForNotUser(int productId);
	
	// 낮은 가격순
	List<Item> getItemOrderByLowPriceForUser(String memberId, int productId);
	List<Item> getItemOrderByLowPriceForNotUser(int productId);

	int insertItem(int itemId);
	int updateItem(Item item); // ppt ItemDao의 update 부분의 기능들을 다 합침.
	int deleteItem(int itemId);
	
	
	
	/* groupItem */
	
	//groupItem용 - read
	
	GroupItem getGItem(int itemid);
	
	List<GroupItem> getAllGItemList();
	
	//groupItem - login용)
	List<GroupItem> getGItemListByMember(String memberId);
	
	//group- 마감일 임박순 
	List<GroupItem> getGItemListByDeadline();
	//group - 마감된 것
	List<GroupItem> getGItemListByWindUp();
	
	//group - 인기순
	List<GroupItem> getGItmeListByPercent();
	
	/* ptpItem */
	PTPItem getPItem(int itemid);
	
	List<PTPItem> getPItemListByProduct(int productId);
	
	// 가격순
	List<PTPItem> getPItemListByHighPrice();
	List<PTPItem> getPItemListByLowPrice();
	
	int insertPItem();
	int updatePItem(int itemId);
	int deletePItem(int itemId);
	
	//order
	void insertOrder(Order myOrder);
	 // -- 해야할 일 정리 : 먼저 Order랑 groupOrder 테이블에 정보 insert
	// payment insert, payment가 카드 일 경우 creditcard 정보 insert
	 // total price만큼 groupItem의 nowBalance update; -> nowBalance update
	
	/* CartItem */
	
	List<CartItem> getAllCartList();
	List<CartItem> getCartListByGeneralCategory(int categoryId, String memberId);
	List<CartItem> getCartListByGroupCategory(int categoryId, String memberId);
	List<CartItem> getCartListByPTPCategory(int categoryId, String memberId);
	Item getItemByCartItemId(int cartItemId, String memberId); //cartItemId로 cartItem에 있는 Item 가져오기
	CartItem getCartItemById(int cartItemId);
	
	int insertCartItem(CartItem cartItem);
	int updateCartItem(CartItem cartItem); // ppt에 있는 메소드들 합침. 
	int updateQuantity(int cartItemId, int quantity);
	int deleteCartItem(int cartItemId);
	
	/*wishlist */
	Item getItemByWishListId(int wishListId);
	WishList findBywishListId(int wishListId);
	/* Read */
	List<WishList> getAllWishList();
	int getWishListByItemIdAndMemberId(int itemId, String memberId);
	
	/* Create */
	int insertWishList(WishList wishList);
	
	/* Delete */
	int deleteWishListByItemIdAndMemberId(int itemId, String memberId);
	int deleteWishList(int wishListId);
	List<WishList> findByMemberId(String memberId);
	
	
	/* cartItem Repository */
	List<CartItem> findByCategoryIdAndMemberId(int categoryId, String memberId);
	Item findItemByCategoryIdAndMemberId(int cartItemId, String memberId);
	void deleteCartItemById(int cartItemId);
	CartItem findCartItemByItemItemIdAndMemberId(int itemId, String memberId);

	/*LineItem*/
	
	List<LineItem> getLineItemByOrderId(int orderId);
	int insertLineItem(LineItem lineItem);
	
	/*Order*/
	List<Order> getItemOrderListByMember(String memberId);
	List<Order> getPTPOrderListByMember(String memberId);
	List<Order> getGroupOrderListByMember(String memberId);
	
	/*review*/
	//멤버가 그룹 아이템을 샀는지 확인.
	int getCntOfGroupItemByMember(int itemid, String memberId);
	//멤버가 쇼핑 아이템을 샀는지 확인.
	int getCntOfItemByMember(int itemid, String memberId);
	
	int insertReview(Review review);
	int deleteReview(int reviewId);
	List<Review> getReviewByItem(int itemid);
}