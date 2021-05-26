/*
 *    Copyright 2010-2013 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.example.lavrastore.dao.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.lavrastore.domain.Category;
import com.example.lavrastore.domain.Product;

/**
 * @author Eduardo Macarron
 *
 */
@Mapper
public interface ProductMapper {
	// Read
	List<Product> getProductListByCategory(int categoryId);

	Product getProductById(int productId);
	Product getProductByName(String name, int categoryId); //유정이 item name이랑 겹쳐서 categoryId가 필요함. 
	List<Product> searchProductList(String keywords); // 검색
	
	Category getCategoryByProId(int productId); //ppt에는 반환값을 Product라고 했으나 Category가 맞음.
	
	//Create
	int insertProduct(Product product);
	
	//Update
	int updateProduct(Product product); // 기존에 설계한 것을 합쳐버림(ppt 참고)
	
	// Delete
	int deleteProduct(Product product);
	

}
