package com.example.lavrastore.dao.mybatis;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.example.lavrastore.dao.CategoryDao;
import com.example.lavrastore.domain.Category;
import com.example.lavrastore.mybatis.mapper.CategoryMapper;


@Repository
public class MybatisCategoryDao implements CategoryDao {
	@Autowired
	private CategoryMapper categoryMapper;
	
	public List<Category> getCategoryList() throws DataAccessException {
		return categoryMapper.getCategoryList();
	}

	public Category getCategoryById(int categoryId) throws DataAccessException {
		return categoryMapper.getCategoryById(categoryId);
	}

	public int insertCategory(Category category) throws DataAccessException {
		return categoryMapper.insertCategory(category);
	}

	public int updateCategory(Category category) throws DataAccessException {
		return categoryMapper.updateCategory(category);
	}

	public int deleteCategory(String categoryId) throws DataAccessException {
		return categoryMapper.deleteCategory(categoryId);
	}
}
