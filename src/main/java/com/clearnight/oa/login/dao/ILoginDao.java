package com.clearnight.oa.login.dao;

import java.util.List;
import java.util.Map;

import com.clearnight.oa.base.bean.PageHelper;
import com.clearnight.oa.login.bean.Account;

public interface ILoginDao {
	
	/**
	 * 添加账户
	 * @param account 账户对象
	 */
	public void addAccount(Account account);

	
	/**
	 * 修改账户
	 * @param account 账户对象
	 */
	public void updateAccount(Account account);
	
	
	/**
	 * 删除账户
	 * @param account 删除账户
	 */
	public void deleteAccount(Account account);
	
	/**
	 * 根据id查询账户
	 * @param id 账户id
	 * @return 账户对象
	 */
	public Account getAccountById(String id);
	
	/**
	 *查询所有账户 
	 * @return 账户对象集合
	 */
	public List<Account> getAccountList();
	
	/**
	 * 根据用户名和密码来验证登陆
	 * @param hql 验证登陆的hql语句
	 * @param queryMap 查询参数
	 * @return 查询的结果
	 */
	public Account validLogin(String hql, Map<String, Object> queryMap);

	/**
	 * 根据查询语句和参数获得账户集合
	 * @param hql 查询语句
	 * @param queryParams 查询参数
	 * @return List<Account> 查询的结果集
	 */
	public List<Account> getAccountPagenationDate(String hql,Map<String,Object> queryParams,PageHelper ph);

	/**
	 * 根据查询语句和查询参数获得数量
	 * @param hql 查询语句
	 * @param queryParams 查询参数
	 * @return Long 查询的结果
	 */
	public Long getAccountCount(String hql,Map<String,Object> queryParams);

	/**
	 * 删除用户
	 * @param hql
	 * @param params
	 */
	public void deleteAcccount(String hql,Map<String,Object> params);

}
