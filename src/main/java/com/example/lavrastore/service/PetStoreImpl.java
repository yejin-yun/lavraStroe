package com.example.lavrastore.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.lavrastore.dao.LineItemDao;
import com.example.lavrastore.dao.CartItemDao;
import com.example.lavrastore.dao.CategoryDao;
import com.example.lavrastore.dao.GroupItemDao;
import com.example.lavrastore.dao.ItemDao;
import com.example.lavrastore.dao.MemberDao;
import com.example.lavrastore.dao.OrderDao;
import com.example.lavrastore.dao.ProductDao;
import com.example.lavrastore.dao.ReviewDao;
import com.example.lavrastore.dao.WishListDao;
import com.example.lavrastore.data.jpa.CartItemRepository;
import com.example.lavrastore.data.jpa.WishListRepository;
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

@Service
@Transactional
public class PetStoreImpl implements PetStoreFacade {
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ItemDao itemDao;
	@Autowired
	private CartItemDao cartItemDao;
	@Autowired
	private GroupItemDao groupItemDao;
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private WishListDao wishlistDao;
	@Autowired
	private LineItemDao lineItemDao;
	
	@Autowired
	private CartItemRepository cartItemRepository;
	@Autowired
	private WishListRepository wishListRepository;
	
	@Autowired
	private OrderDao orderDao;

	@Autowired
	private ReviewDao reviewDao;
	// -------------------------------------------------------------------------
	// Operation methods, implementing the PetStoreFacade interface
	// -------------------------------------------------------------------------


	/* Member */
	
	public Member getMember(String memberId) {
		return memberDao.getMember(memberId);
	}

	public Member getMember(String memberId, String password) {
		return memberDao.getMember(memberId, password);
	}

	public void insertMember(Member member) {
		memberDao.insertMember(member);
	}

	public void updateMember(Member member) {
		memberDao.updateMember(member);
	}

	public List<String> getUsernameList() {
		return memberDao.getUsernameList();
	}

	/* Category */

	public List<Category> getCategoryList() {
		return categoryDao.getCategoryList();
	}

	public Category getCategoryById(int categoryId) {
		return categoryDao.getCategoryById(categoryId);
	}

	public int insertCategory(Category category) {
		return categoryDao.insertCategory(category);
	}

	public int updateCategory(Category category) {
		return categoryDao.updateCategory(category);
	}

	public int deleteCategory(String categoryId) {
		return categoryDao.deleteCategory(categoryId);
	}
	

	

	/* Product */

	public List<Product> getProductListByCategory(int categoryId) {
		return productDao.getProductListByCategory(categoryId);
	}

	public Product getProductById(int productId) {
		return productDao.getProductById(productId);
	}
	
	@Override
	public Product getProductByName(String name, int categoryId) {
		return productDao.getProductByName(name, categoryId);
	}

	public Category getCategoryByProId(int productId) {
		return productDao.getCategoryByProId(productId);
	}
	
	public List<Product> searchProductList(String keywords){
		return productDao.searchProductList(keywords);
	}

	public int insertProduct(Product product) {
		return productDao.insertProduct(product);
	}

	public int updateProduct(Product product) {
		return productDao.updateProduct(product);
	}

	public int deleteProduct(Product product) {
		return productDao.deleteProduct(product);
	}

	/* Item */
	
	public List<Item> getItemListTopKByLikeCnt(int categoryId, int number)  {
		return itemDao.getItemListTopKByLikeCnt(categoryId, number);
	}

	public List<Item> searchItemList(String keywords){
		return itemDao.searchItemList(keywords);
	}
	
	public List<Item> getItemListByProduct(int productId) {
		return itemDao.getItemListByProduct(productId);
	}

	public Item getItem(int itemId) {
		return itemDao.getItem(itemId);
	}

	@Override
	public List<Item> getItemForNotUser(int productId) {
		return itemDao.getItemForNotUser(productId);
	}

	@Override
	public List<Item> getItemForUser(String memberId, int productId) {
		return itemDao.getItemForUser(memberId, productId);
	}

	@Override
	public List<Item> getItemOrderByHighPriceForUser(String memberId, int productId) {
		return itemDao.getItemOrderByHighPriceForUser(memberId, productId);
	}

	@Override
	public List<Item> getItemOrderByHighPriceForNotUser(int productId) {
		return itemDao.getItemOrderByHighPriceForNotUser(productId);
	}

	@Override
	public List<Item> getItemOrderByLowPriceForUser(String memberId, int productId) {
		return itemDao.getItemOrderByLowPriceForUser(memberId, productId);
	}

	@Override
	public List<Item> getItemOrderByLowPriceForNotUser(int productId) {
		return itemDao.getItemOrderByLowPriceForNotUser(productId);
	}

	@Override
	public int insertItem(int itemId) {
		return itemDao.insertItem(itemId);
	}

	@Override
	public int updateItem(Item item) {
		return itemDao.updateItem(item);
	}

	@Override
	public int deleteItem(int itemId) {
		return itemDao.deleteItem(itemId);
	}

	/* CartItem */
	
	@Override
	public List<CartItem> getAllCartList() {
		return cartItemDao.getAllCartList();
	}

	@Override
	public List<CartItem> getCartListByGeneralCategory(int categoryId, String memberId) {
		return cartItemDao.getCartListByGeneralCategory(categoryId, memberId);
	}

	@Override
	public List<CartItem> getCartListByGroupCategory(int categoryId, String memberId) {
		return cartItemDao.getCartListByGroupCategory(categoryId, memberId);
	}

	@Override
	public List<CartItem> getCartListByPTPCategory(int categoryId, String memberId) {
		return cartItemDao.getCartListByPTPCategory(categoryId, memberId);
	}

	@Override
	public Item getItemByCartItemId(int cartItemId, String memberId) {
		return cartItemDao.getItemByCartItemId(cartItemId, memberId);
	}

	public CartItem getCartItemById(int cartItemId) {
		return cartItemDao.getCartItemById(cartItemId);
	}
	
	@Override
	public int insertCartItem(CartItem cartItem) {
		return cartItemDao.insertCartItem(cartItem);
	}

	@Override
	public int updateCartItem(CartItem cartItem) {
		return cartItemDao.updateCartItem(cartItem);
	}
	
	public int updateQuantity(int cartItemId, int quantity) {
		return cartItemDao.updateQuantity(cartItemId, quantity);
	}

	@Override
	public int deleteCartItem(int cartItemId) {
		return cartItemDao.deleteCartItem(cartItemId);
	}
	
	/* cartItem Repository */
	public List<CartItem> findByCategoryIdAndMemberId(int categoryId, String memberId) {
		return cartItemRepository.findByCategoryIdAndMemberId(categoryId, memberId);
	}
	
	public Item findItemByCategoryIdAndMemberId(int cartItemId, String memberId) {
		return cartItemRepository.findItemByCategoryIdAndMemberId(cartItemId, memberId);
	}
	
	public void deleteCartItemById(int cartItemId) {
		cartItemRepository.deleteById(cartItemId);
	}
	
	public CartItem findCartItemByItemItemIdAndMemberId(int itemId, String memberId) {
		return cartItemRepository.findCartItemByItemItemIdAndMemberId(itemId, memberId);
	}
	
	/*group Item */
	@Override
	public List<GroupItem> getAllGItemList() {
		return groupItemDao.getAllGItemList();
	}

	@Override
	public List<GroupItem> getGItemListByMember(String memberId) {
		return groupItemDao.getGItemListByMember(memberId);
	}

	@Override
	public List<GroupItem> getGItemListByDeadline() {
		return groupItemDao.getGItemListByDeadline();
	}

	@Override
	public List<GroupItem> getGItemListByWindUp() {
		return groupItemDao.getGItemListByWindUp();
	}

	@Override
	public List<GroupItem> getGItmeListByPercent() {
		return groupItemDao.getGItmeListByPercent();
	}


	@Override
	public GroupItem getGItem(int itemid) {
		return groupItemDao.getGItem(itemid);
	}
	
	/* ptp Item */
	@Override
	public PTPItem getPItem(int itemid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PTPItem> getPItemListByProduct(int productId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PTPItem> getPItemListByHighPrice() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PTPItem> getPItemListByLowPrice() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertPItem() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updatePItem(int itemId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deletePItem(int itemId) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public List<WishList> getAllWishList() throws DataAccessException{
		return wishlistDao.getAllWishList();
	}


	@Override
	public int insertWishList(WishList wishList) throws DataAccessException{
		return wishlistDao.insertWishList(wishList); // 0이면 실패, 0 초과는 성공
	}
	public List<WishList> findByMemberId(String MemberId){
		return wishListRepository.findByMemberId(MemberId);
	}

	@Override
	public Item getItemByWishListId(int wisilistId) throws DataAccessException{
		return wishlistDao.getItemByWishListId(wisilistId);
	}
	
	public int getWishListByItemIdAndMemberId(int itemId, String memberId) throws DataAccessException{ 
		return wishlistDao.getWishListByItemIdAndMemberId(itemId, memberId);
	}
	
	public WishList findBywishListId(int wishListId) {
		return wishListRepository.findBywishListId(wishListId);
	}
	
	// Delete

	@Override
	public int deleteWishList(int wishListId) throws DataAccessException{
		return wishlistDao.deleteWishList(wishListId);
	}
	
	public int deleteWishListByItemIdAndMemberId(int itemId, String memberId) throws DataAccessException {
		return wishlistDao.deleteWishListByItemIdAndMemberId(itemId, memberId);
	}

	@Override
	public void insertOrder(Order myOrder) {
		orderDao.insertOrder(myOrder);
	}
	
	@Override
	public List<LineItem> getLineItemByOrderId(int orderId) {
		return lineItemDao.getLineItemByOrderId(orderId);
	}

	@Override
	public int insertLineItem(LineItem lineItem) {
		return lineItemDao.insertLineItem(lineItem);
	}

	@Override
	public List<Order> getItemOrderListByMember(String memberId) {
		return orderDao.getItemOrderListByMember(memberId);
	}

	@Override
	public List<Order> getPTPOrderListByMember(String memberId) {
		return orderDao.getPTPOrderListByMember(memberId);
	}

	@Override
	public List<Order> getGroupOrderListByMember(String memberId) {
		return orderDao.getGroupOrderListByMember(memberId);
	}

	@Override
	public int getCntOfGroupItemByMember(int itemid, String memberId) {
		// TODO Auto-generated method stub
		return reviewDao.getCntOfGroupItemByMember(itemid, memberId);
	}

	@Override
	public int getCntOfItemByMember(int itemid, String memberId) {
		// TODO Auto-generated method stub
		return reviewDao.getCntOfItemByMember(itemid, memberId);
	}

	@Override
	public int insertReview(Review review) {
		// TODO Auto-generated method stub
		return reviewDao.insertReview(review);
	}

	@Override
	public List<Review> getReviewByItem(int itemid) {
		// TODO Auto-generated method stub
		return reviewDao.getReviewByItem(itemid);
	}

	@Override
	public int deleteReview(int reviewId) {
		// TODO Auto-generated method stub
		return reviewDao.deleteReview(reviewId);
	}

	@Override
	public Order getOrderById(int orderId) {
		return orderDao.getOrderById(orderId);
	}

}