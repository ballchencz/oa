package com.clearnight.oa.user.dao.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.clearnight.oa.base.dao.IBaseDao;
import com.clearnight.oa.user.bean.UserBasic;
import com.clearnight.oa.user.dao.IUserDao;

@Repository
public class UserDaoImpl implements IUserDao{

	/*------------------spring注入--------------------*/
	private IBaseDao<UserBasic> baseDao;
	
	/*------------------方法区-------------------------*/
	@Override
	public void addUserBasic(UserBasic userBasic) {
		this.baseDao.save(userBasic);
	}
	
	@Override
	public void updateUserBasic(UserBasic userBasic) {
		this.baseDao.update(userBasic);
		
	}
	
	@Override
	public void deleteUserBasic(String hql,Map<String,Object> parames) {
		
		this.baseDao.delete(hql, parames);
	}
	
	
	@Override
	public List<UserBasic> getUsersPagenation(String hql,Map<String,Object> queryMap, int rows,
			int page) {
		List<UserBasic> array = null;
		if(null!=queryMap && !queryMap.isEmpty()){			
			array =  baseDao.find(hql,queryMap, page, rows);
		}else{
			array = baseDao.find(hql,page,rows);
		}
		return array;
	}

	@Override
	public long getUsersTotal(String hql,Map<String,Object> queryMap) {
		long count = 0;
		if(!queryMap.isEmpty()){			
			count = baseDao.count(hql,queryMap);
		}else{
			count = baseDao.count(hql);
		}
		return count;
	}

	@Override
	public UserBasic getUserBasicById(String id){
		return this.baseDao.get(UserBasic.class,id);
	}
	/*-----------------setter、getter方法--------------*/
	public IBaseDao<UserBasic> getBaseDao() {
		return baseDao;
	}
	@Autowired
	public void setBaseDao(IBaseDao<UserBasic> baseDao) {
		this.baseDao = baseDao;
	}




	
}
