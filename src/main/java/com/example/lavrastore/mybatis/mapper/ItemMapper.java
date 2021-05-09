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

package com.example.lavrastore.mybatis.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.lavrastore.domain.Item;


/**
 * @author Eduardo Macarron
 *
 */
@Mapper
public interface ItemMapper {
	/* Read */
	
	List<Item> getItemListByProduct(int productId);
	Item getItem(int itemId);
	List<Item> getItemForNotUser(); // 인기순으로 되어 있음. 비로그인 용
	List<Item> getItemForUser(String memberId);
	
	/*
	 * //인기순 List<Item> getItemOrderByHighLikeCntForUser(String memberId);
	 * List<Item> getItemOrderByHighLikeCntForNotUser();
	 */
	//높은 가격순
	List<Item> getItemOrderByHighPriceForUser(String memberId);
	List<Item> getItemOrderByHighPriceForNotUser();
	
	// 낮은 가격순
	List<Item> getItemOrderByLowPriceForUser(String memberId);
	List<Item> getItemOrderByLowPriceForNotUser();
	
	/* Create */
	
	int insertItem(int itemId);
	
	/* Update */
	
	int updateItem(int itemId); // ppt ItemDao의 update 부분의 기능들을 다 합침.
	
	/* Delete */
	
	int deleteItem(int itemId);

}
