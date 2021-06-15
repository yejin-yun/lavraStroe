package com.example.lavrastore.dao.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.example.lavrastore.dao.GroupItemDao;
import com.example.lavrastore.dao.mybatis.mapper.GroupItemMapper;
import com.example.lavrastore.domain.GroupItem;
import com.example.lavrastore.domain.Order;

@Repository
public class MybatisGroupItemDao implements GroupItemDao{

	@Autowired
	private GroupItemMapper groupItemMapper;
	
	public GroupItem getGItem(int itemId) throws DataAccessException{
		return groupItemMapper.getGItem(itemId);
	}
	
	public List<GroupItem> getAllGItemList() throws DataAccessException{
		return groupItemMapper.getAllGItemList();
	}
	
	public List<GroupItem> getGItemListByProduct(int productId) throws DataAccessException {
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
	
	public List<GroupItem> getGItmeListByPercent() throws DataAccessException{
		return groupItemMapper.getGItmeListByPercent();
	}
	
	public int insertGItem() throws DataAccessException{
		return groupItemMapper.insertGItem();
	}
	
	public int updateGItem(GroupItem gitem) throws DataAccessException{
		return groupItemMapper.updateGItem(gitem);
	}
	
	public int deleteGItem(int itemId) throws DataAccessException{
		return groupItemMapper.deleteGItem(itemId);
	}

	public int updateNowBalance(int itemId, int total) throws DataAccessException {
		return groupItemMapper.updateNowBalance(itemId, total);
	}

	
}
