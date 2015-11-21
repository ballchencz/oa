package com.clearnight.oa.user.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.clearnight.oa.base.bean.PageHelper;
import com.clearnight.oa.user.bean.UserBasic;

public interface IUserService {
	
	/**
	 * 添加用用户基本信息
	 * @param userBasic 用户基本信息对象
	 */
	public void addUserBasic(UserBasic userBasic);
	
	/**
	 * 修改用户基本信息
	 * @param userBasic 用户基本信息对象
	 */
	public void updateUserBasic(UserBasic userBasic);
	
	/**
	 * 删除用户基本信息
	 * @param userBasic
	 */
	public void deleteUserBasic(String[] ids);
	
	/**
	 * 用户信息分页查询
	 * @param rows 每页显示几行
	 * @param page 第几页
	 * @return ArrayList<UserBasic> 用户基本信息对象集合
	 */
	public List<UserBasic> getUsersPagenation(UserBasic user, PageHelper pageHelper);
	
	/**
	 * 根据查询参数获得总记录数
	 * @param userBasic 用户对象
	 * @return long 总记录数
	 */
	public long getUsersTotal(UserBasic userBasic, PageHelper pageHelper);
	
	/**
	 * 将bean类转换为Map集合
	 * @param obj 需要转换的bean累
	 * @return Map<String,Object>
	 */
	public Map<String, Object> transBeanToMap(Object obj);

	/**
	 * 根据用户ID获得用户
	 * @param id 用户ID
	 * @return UserBasic
	 */
	public UserBasic getUserBasicById(String id);
	
}
