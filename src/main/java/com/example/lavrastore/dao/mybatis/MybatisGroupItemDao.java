package com.example.lavrastore.dao.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.example.lavrastore.dao.mybatis.mapper.GroupItemMapper;
import com.example.lavrastore.domain.GroupItem;

@Repository
public class MybatisGroupItemDao {

	@Autowired
	private GroupItemMapper groupItemMapper;
	
	public GroupItem getGItem(int itemId) throws DataAccessException{
		return groupItemMapper.getGItem(itemId);
	}
	
	public List<GroupItem> getAllGItemList() throws DataAccessException{
		return groupItemMapper.getAllGItemList();
	}
	
	public List<GroupItem> getGItemListByProduct(String productId) throws DataAccessException{
		return groupItemMapper.getGItemListByProduct(productId);
	}
	
	public List<GroupItem> getGItemListByMember(String memberId) throws DataAccessException{
		return groupItemMapper.getGItemListByMember(memberId);
	}
	
	public List<GroupItem> getGItemListByDeadline() throws DataAccessException{
		return groupItemMapper.getGItemListByDeadline();
	}
	
	public List<GroupItem> getGItemListByWindUp() throws DataAccessException{
		return groupItemMapper.getGItemListByWindUp();
	}
	
	public List<GroupItem> getGItemListByPercent() throws DataAccessException{
		return groupItemMapper.getGItmeListByPercent();
	}
	
	public int insertGItem() throws DataAccessException{
		return groupItemMapper.insertGItem();
	}
	
	public int updateGItem(int itemId) throws DataAccessException{
		return groupItemMapper.updateGItem(itemId);
	}
	
	public int deleteGItem(int itemId) throws DataAccessException{
		return groupItemMapper.deleteGItem(itemId);
	}
	
}
