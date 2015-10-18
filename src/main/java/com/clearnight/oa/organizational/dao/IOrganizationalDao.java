package com.clearnight.oa.organizational.dao;

import java.util.List;
import java.util.Map;

import com.clearnight.oa.organizational.bean.Organizational;
import com.clearnight.oa.organizational.bean.OrganizationalType;

/**
 * 组织结构Dao接口
 * @author 陈钊
 */
public interface IOrganizationalDao {

	/**
	 * 添加组织结构数据
	 * @param organizational 组织结构对象
	 */
	public void addOrganizational(Organizational organizational);
	
	/**
	 * 修改组织结构数据
	 * @param organizational 组织结构对象
	 */
	public void updateOrganizational(Organizational organizational);
	
	/**
	 * 根据参数查询组织结构对象
	 * @param hql 查询语句
	 * @param queryParam 查询参数
	 * @return Organizational 组织结构对象
	 */
	public Organizational getOrganizationalByQureyParam(String hql, Map<String, Object> queryParam);
	
	/**
	 * 根据参数查询组织结构对象集合
	 * @param hql 查询语句
	 * @param queryParam 查询参数
	 * @return List<Organizational> 组织结构对象集合
	 */
	public List<Organizational> getOrganizationalListByQureyParam(String hql, Map<String, Object> queryParam);
	
	
	/**
	 * 分页查询
	 * @param hql 查询语句
	 * @param queryParam 查询参数
	 * @param page 第几页
	 * @param rows 每页共几行
	 * @return List<Organizational> 组织结构对象集合
	 */
	public List<Organizational> getOrganizationalListByQureyParam(String hql, Map<String, Object> queryParam, long page, long rows);
	
	/**
	 * 分页总数查询
	 * @param hql 查询语句
	 * @param queryParam 查询条件
	 * @return Long 总数
	 */
	public Long getOrganizationalListCountByQureyParam(String hql, Map<String, Object> queryParam);
	
	/**
	 * 删除组织结构数据
	 * @param hql 删除语句
	 */
	public void deleteOrganizational(String hql);
	
	
	
	/*------------------------------------------------------------组织结构类型维护--------------------------------------------------------------------------------------*/
	/**
	 * 组织结构类型维护分页查询
	 * @param hql 查询语句
	 * @param queryParam 参数
	 * @param page 第几页
	 * @param rows 每页几行
	 * @return List<OrganizationalType> 组织结构类型对象集合
	 */
	public List<OrganizationalType> getOrganizationalTypeListByParam(String hql, Map<String, Object> queryParam, Integer page, Integer rows);
	
	/**
	 * 根据参数查询记录数
	 * @param hql 查询语句
	 * @param queryParam 查询参数
	 * @return Long 总数
	 */
	public Long getOrganizationalTypeCountByQueryParam(String hql, Map<String, Object> queryParam);
	
	/**
	 * 根据参数查询组织结构分类对象
	 * @param hql 查询语句
	 * @param queryParam 查询参数
	 * @return OrganizationalType 组织结构对象
	 */
	public OrganizationalType getOrganizationalTypeByParam(String hql, Map<String, Object> queryParam);
	
	/**
	 * 添加组织结构分类
	 * @param orgType 组织结构分类对象
	 */
	public void addOrganizationalType(OrganizationalType orgType);
	
	/**
	 * 修改组织结构分类
	 * @param orgType 组织结构分类对象
	 */
	public void updateOrganizationalType(OrganizationalType orgType);
	
	/**
	 * 删除组织结构分类
	 * @param hql 删除语句
	 */
	public void deleteOrganizationalType(String hql);

}
