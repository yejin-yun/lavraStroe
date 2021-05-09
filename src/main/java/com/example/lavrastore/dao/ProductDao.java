package com.example.lavrastore.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.lavrastore.domain.Category;
import com.example.lavrastore.domain.Product;


public interface ProductDao {

	List<Product> getProductListByCategory(int categoryId) throws DataAccessException;

	Product getProductById(int productId) throws DataAccessException;
	
	Category getCategoryByProId(int productId) throws DataAccessException;
	
	int insertProduct(Product product) throws DataAccessException;
	
	int updateProduct(Product product) throws DataAccessException; // 기존에 설계한 것을 합쳐버림(ppt 참고)
	
	int deleteProduct(Product product) throws DataAccessException;
	
	Product getProductByName(String name, int categoryId) throws DataAccessException;
	

}
