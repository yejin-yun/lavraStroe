package com.example.lavrastore.dao.mybatis;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.example.lavrastore.dao.ProductDao;
import com.example.lavrastore.dao.mybatis.mapper.ProductMapper;
import com.example.lavrastore.domain.Category;
import com.example.lavrastore.domain.Product;

@Repository
public class MybatisProductDao implements ProductDao {
	@Autowired
	private ProductMapper productMapper;

	public List<Product> getProductListByCategory(int categoryId) throws DataAccessException {
		return productMapper.getProductListByCategory(categoryId);
	}

	public Product getProductById(int productId) throws DataAccessException {
		return productMapper.getProductById(productId);
	}

	public int insertProduct(Product product) throws DataAccessException {
		return productMapper.insertProduct(product);
	}

	public int updateProduct(Product product) throws DataAccessException {
		return productMapper.updateProduct(product);
	}

	public int deleteProduct(Product product) throws DataAccessException {
		return productMapper.deleteProduct(product);
	}

	public Category getCategoryByProId(int productId) throws DataAccessException {
		return productMapper.getCategoryByProId(productId);
	}

	@Override
	public Product getProductByName(String name, int categoryId) throws DataAccessException {
		return productMapper.getProductByName(name, categoryId);			
	}
	
	/* Inner Classes */

	public static class ProductSearch {

		private List<String> keywordList = new ArrayList<String>();

		public ProductSearch(String keywords) {
			StringTokenizer splitter = new StringTokenizer(keywords, " ", false);
			while (splitter.hasMoreTokens()) {
				this.keywordList.add("%" + splitter.nextToken() + "%");
			}
		}

		public List<String> getKeywordList() {
			return keywordList;
		}
	}



}
