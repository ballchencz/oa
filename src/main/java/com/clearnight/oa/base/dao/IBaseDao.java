package com.clearnight.oa.base.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface IBaseDao<T> {	
	/**
	 * 根据id查询
	 * @return 查询的的结果
	 */
	public T get(Class<T> c, Serializable id);

	/**
	 * 根据Hql语句查询
	 * @param hql 查询语句
	 * @return 查询的结果
	 */
	public T get(String hql);

	/**
	 * 根据Hql语句和传入参数查询
	 * @param hql 查询语句
	 * @param params 查询参数
	 * @return 查询结果
	 */
	public T get(String hql, Map<String, Object> params);
	
	/**
	 * 根据Hql语句查询集合
	 * @param hql 查询语句
	 * @return 结果集
	 */
	public List<T> find(String hql);
	
	/**
	 * 根据hql语句和查询条件查询结果集
	 * @param hql 查询语句
	 * @param params 查询条件
	 * @return 结果集
	 */
	public List<T> find(String hql, Map<String, Object> params);

	/**
	 * 分页查询
	 * @param hql 查询语句
	 * @param page 第几页
	 * @param rows 总条数
	 * @return 分页结查询果集
	 */
	public List<T> find(String hql, int page, int rows);
	
	/**
	 * 分页带参查询
	 * @param hql 查询语句
	 * @param params 查询所带的参数
	 * @param page 第几页
	 * @param rows 每页总条数
	 * @return 分页带参查询的结果集
	 */
	public List<T> find(String hql, Map<String, Object> params, int page, int rows);
	
	/**
	 * 根据查询语句查询记录的条数
	 * @param hql 查询语句
	 * @return 记录的条数
	 */
	public Long count(String hql);

	/**
	 * 根据查询语句和 传入参数查询记录的条数
	 * @param hql 查询语句
	 * @param params 传入参数
	 * @return 记录的条数
	 */
	public Long count(String hql, Map<String, Object> params);
	
	/**
	 * 添加的方法
	 * @param t 对象
	 * @return 序列化对象
	 */
	public Serializable save(T t) ;
	
	/**
	 * 删除的方法
	 * @param o 所要删除的记录的对象
	 */
	public void delete(T o);
	
	/**
	 * 根据参数批量删除
	 * @param
	 * @param parames
	 */
	public int delete(String hql, Map<String, Object> parames);
	


	/**
	 * 修改的方法
	 * @param o 所要修改的记录的对象
	 */
	public void update(T o);

	/**
	 * 删除或者修改
	 * @param o 要操作的记录的对象
	 */
	public void saveOrUpdate(T o);
	
	/**
	 * 批量删除
	 * @param hql 批量删除的sql语句
	 * @return 删除的条数
	 */
	public int executeDeleteHql(String hql);
}
