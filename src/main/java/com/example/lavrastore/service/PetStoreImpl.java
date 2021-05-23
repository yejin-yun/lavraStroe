package com.example.lavrastore.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.lavrastore.dao.CartItemDao;
import com.example.lavrastore.dao.CategoryDao;
import com.example.lavrastore.dao.ItemDao;
import com.example.lavrastore.dao.ProductDao;
import com.example.lavrastore.domain.CartItem;
import com.example.lavrastore.domain.Category;
import com.example.lavrastore.domain.Item;
import com.example.lavrastore.domain.Product;

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

	// -------------------------------------------------------------------------
	// Operation methods, implementing the PetStoreFacade interface
	// -------------------------------------------------------------------------



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

	public List<Item> getItemListByProduct(int productId) {
		return itemDao.getItemListByProduct(productId);
	}

	public Item getItem(int itemId) {
		return itemDao.getItem(itemId);
	}

	@Override
	public List<Item> getItemForNotUser() {
		return itemDao.getItemForNotUser();
	}

	@Override
	public List<Item> getItemForUser(String memberId) {
		return itemDao.getItemForUser(memberId);
	}

	@Override
	public List<Item> getItemOrderByHighPriceForUser(String memberId) {
		return itemDao.getItemOrderByHighPriceForUser(memberId);
	}

	@Override
	public List<Item> getItemOrderByHighPriceForNotUser() {
		return itemDao.getItemOrderByHighPriceForNotUser();
	}

	@Override
	public List<Item> getItemOrderByLowPriceForUser(String memberId) {
		return itemDao.getItemOrderByLowPriceForUser(memberId);
	}

	@Override
	public List<Item> getItemOrderByLowPriceForNotUser() {
		return itemDao.getItemOrderByLowPriceForNotUser();
	}

	@Override
	public int insertItem(int itemId) {
		return itemDao.insertItem(itemId);
	}

	@Override
	public int updateItem(int itemId) {
		return itemDao.updateItem(itemId);
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
	public List<CartItem> getCartListByGeneralCategory(int categoryId) {
		return cartItemDao.getCartListByGeneralCategory(categoryId);
	}

	@Override
	public List<CartItem> getCartListByGroupCategory(int categoryId) {
		return cartItemDao.getCartListByGroupCategory(categoryId);
	}

	@Override
	public List<CartItem> getCartListByPTPCategory(int categoryId) {
		return cartItemDao.getCartListByPTPCategory(categoryId);
	}

	@Override
	public List<CartItem> getItemByCartItemId(int cartItemId) {
		return cartItemDao.getItemByCartItemId(cartItemId);
	}

	@Override
	public int insertCartItem(CartItem cartItem) {
		return cartItemDao.insertCartItem(cartItem);
	}

	@Override
	public int updateCartItem(CartItem cartItem) {
		return cartItemDao.updateCartItem(cartItem);
	}

	@Override
	public int deleteCartItem(int cartItemId) {
		return cartItemDao.deleteCartItem(cartItemId);
	}

	


	

}