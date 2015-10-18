package com.clearnight.oa.user.service.impl;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clearnight.oa.base.bean.PageHelper;
import com.clearnight.oa.user.bean.UserBasic;
import com.clearnight.oa.user.dao.IUserDao;
import com.clearnight.oa.user.service.IUserService;
@Service
@Transactional
public class UserServiceImpl implements IUserService {

	/*------------------spring注入---------------------*/
	private IUserDao userDao;
	
	
	@Override
	public void addUserBasic(UserBasic userBasic) {
		this.userDao.addUserBasic(userBasic);
	}
	
	@Override
	public void updateUserBasic(UserBasic userBasic) {
		this.userDao.updateUserBasic(userBasic);
	}

	@Override
	public void deleteUserBasic(String [] ids) {
		String hql = "DELETE FROM UserBasic t WHERE t.id in (:ids) ";
		Map<String,Object> parames = new HashMap<String,Object>();
		parames.put("ids", ids);
		this.userDao.deleteUserBasic(hql,parames);
		
	}
	
	
	@Override
	public List<UserBasic> getUsersPagenation(UserBasic user,PageHelper pageHelper) {
		String hql = "FROM UserBasic t";
		Map<String,Object> queryMap = new HashMap<String,Object>();
		
		hql += whereHql(user,queryMap);
		hql += orderHql(pageHelper);
		List<UserBasic> users = userDao.getUsersPagenation(hql, queryMap, pageHelper.getRows(), pageHelper.getPage());
		return users;
	}
	
	@Override
	  public Map<String, Object> transBeanToMap(Object obj) {  
		  
	        if(obj == null){  
	            return null;  
	        }          
	        Map<String, Object> map = new HashMap<String, Object>();  
	        try {  
	            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());  
	            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();  
	            for (PropertyDescriptor property : propertyDescriptors) {  
	                String key = property.getName();  
	  
	                // 过滤class属性  
	                if (!key.equals("class")) {  
	                    // 得到property对应的getter方法  
	                    Method getter = property.getReadMethod();  
	                    Object value = getter.invoke(obj);  
	                    if(null!=value)
	                    	map.put(key, value);  
	                }  
	  
	            }  
	        } catch (Exception e) {  
	            System.out.println("transBean2Map Error " + e);  
	        }  
	  
	        return map;  
	  
	    }  
	
	/**
	 * 查询hql语句组装
	 * @param user
	 * @param params
	 * @return String
	 */
	private String whereHql(UserBasic user, Map<String, Object> params) {
		String hql = "";
		if (user != null) {
			hql += " where 1=1 ";
			if (user.getUserName() != null) {
				hql += " and t.userName like :userName";
				params.put("userName", "%%" + user.getUserName() + "%%");
			}
			if (user.getBirthday() != null) {
				hql += " and t.bithday = :birthday";
				params.put("birthday", user.getBirthday());
			}
			if (user.getUserSex() != null) {
				hql += " and t.userSex = :userSex";
				params.put("userSex", user.getUserSex());
			}
			if(user.getUserAge()!=null){
				hql += " and t.userAge = :userAge";
				params.put("userAge", user.getUserAge());
			}
			if(user.getNation()!=null){
				hql += " and t.nation = :nation";
				params.put("nation", user.getNation());
			}
	
		}
		return hql;
	}
	
	/**
	 * 排序字段的查询语句组装
	 * @param ph
	 * @return String
	 */
	private String orderHql(PageHelper ph) {
		String orderString = "";
		if (ph.getSort() != null && ph.getOrder() != null) {
			orderString = " order by t." + ph.getSort() + " " + ph.getOrder();
		}
		return orderString;
	}
	
	@Override
	public long getUsersTotal(UserBasic userBasic,PageHelper pageHelper) {
		String hql = "SELECT COUNT(*) FROM UserBasic t";
		Map<String,Object> queryMap = new HashMap<String,Object>();
		hql += whereHql(userBasic,queryMap);
		hql += orderHql(pageHelper);
		return userDao.getUsersTotal(hql,queryMap);
	}
	/*-----------------setter、getter方法--------------*/
	public IUserDao getUserDao() {
		return userDao;
	}
	@Autowired
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}










	
}
