package com.example.lavrastore.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.lavrastore.domain.Category;


public interface CategoryDao {
	List<Category> getCategoryList() throws DataAccessException;

	Category getCategoryById(int categoryId) throws DataAccessException;

	// Create
	int insertCategory(Category category) throws DataAccessException; // 0이면 실패, 0 초과는 성공

	// Update
	int updateCategory(Category category) throws DataAccessException;

	// Delete

	int deleteCategory(String categoryId) throws DataAccessException;
}
