package com.clearnight.oa.user.service.impl;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

import com.clearnight.oa.base.consts.BaseConsts;
import com.clearnight.oa.base.dao.IBaseDao;
import com.clearnight.oa.base.service.IBaseService;
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
	@Autowired
	private IBaseService baseService;
	@Autowired
	private IBaseDao baseDao;
	
	
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
	public List<UserBasic> getUsersPagenation(UserBasic user,PageHelper pageHelper,String modelFlag,String beanName) {
		String hql = "FROM UserBasic t";
		Map<String,Object> queryMap = new HashMap<String,Object>();
		hql += this.baseService.whereHQL(user,queryMap);
		if(modelFlag!=null && !modelFlag.equals("") && beanName!=null&&!beanName.equals("")){
			String className = BaseConsts.PROJECT_NAME+"."+modelFlag+".bean."+beanName;
			List<String> userIdList = baseDao.find("SELECT t.userId FROM "+className+" t WHERE t.userId IS NOT NULL AND t.userId !=''");
			if(userIdList.size()>0){
				String s = "";
				for(String id : userIdList){
					s += "'"+id+"',";
				}
				s = s.substring(0,s.length()-1);
				hql += " AND t.id not in("+s+")";
			}
		}

		hql += this.baseService.orderHQL(pageHelper);
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

	@Override
	public long getUsersTotal(UserBasic userBasic,PageHelper pageHelper) {
		String hql = "SELECT COUNT(*) FROM UserBasic t";
		Map<String,Object> queryMap = new HashMap<String,Object>();
		hql += this.baseService.whereHQL(userBasic,queryMap);
		hql += this.baseService.orderHQL(pageHelper);
		return userDao.getUsersTotal(hql,queryMap);
	}

	@Override
	public UserBasic getUserBasicById(String id){
		return this.userDao.getUserBasicById(id);
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
