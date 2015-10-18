package com.clearnight.oa.user.dao;

import java.util.List;
import java.util.Map;

import com.clearnight.oa.user.bean.UserBasic;

public interface IUserDao {

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
	 * 根据ID批量删除用户
	 * @param hql 删除语句
	 */
	public void deleteUserBasic(String hql, Map<String, Object> parames);
	
	/**
	 * 用户信息分页查询
	 * @param hql 查询语句
	 * @param rows 每页显示几行
	 * @param page 第几页
	 * @return ArrayList<UserBasic> 用户基本信息对象集合
	 */
	public List<UserBasic> getUsersPagenation(String hql, Map<String, Object> queryMap, int rows, int page);
	
	/**
	 * 根据查询参数获得总记录数
	 * @param hql 查询语句
	 * @return long 记录数
	 */
	public long getUsersTotal(String hql, Map<String, Object> queryMap);
}
