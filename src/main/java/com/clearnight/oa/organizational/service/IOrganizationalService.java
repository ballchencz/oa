package com.clearnight.oa.organizational.service;

import java.util.List;

import com.clearnight.oa.base.bean.PageHelper;
import com.clearnight.oa.organizational.bean.Organizational;
import com.clearnight.oa.organizational.bean.OrganizationalType;



public interface IOrganizationalService {
	
	/**
	 * 添加组织结构数据
	 * @param organizational 组织结构对象
	 * @return boolean 添加成功与否的标志
	 */
	public boolean addOrganizational(Organizational organizational);

	/**
	 * 修改组织结构数据
	 * @param organizational 组织结构对象
	 * @return boolean 修改成功与否的标志
	 */
	public boolean updateOrganizational(Organizational organizational);
	
	/**
	 * 根据id数组删除组织结构数据
	 * @param ids 组织结构对象ID集合
	 * @return boolean 删除成功与否的标志
	 */
	public boolean deleteOrganizational(String[] ids);
	
	/**
	 * 根据参数查询组织结构集合
	 * @param organizational 组织结构对象
	 * @param pageHelper 分页参数对象
	 * @return List<Organizational> 组织结构对象集合
	 */
	public List<Organizational> getOrganizationalListByQueryParam(Organizational organizational, PageHelper pageHelper);
	
	/**
	 * 根据参数查询组织结构对象集合
	 * @param hql 查询语句
	 * @param queryParam 查询参数
	 * @return List<Organizational> 组织结构对象集合
	 */
	public List<Organizational> getOrganizationalListByQureyParam(Organizational organizational);
	
	/**
	 * 根据父ID查询组织结构集合
	 * @param parentId 父ID
	 * @return List<Organizational> 组织结构对象集合
	 */
	public List<Organizational> getOrganizationalListByParentId(String parentId);
	
	/**
	 * 根据参数查询组织结构总数
	 * @param organizational 组织机结构对象
	 * @param pageHelper 分页参数对象
	 * @return Long 总数
	 */
	public Long getOrganizationalListCountByQureyParam(Organizational organizational, PageHelper pageHelper);
	
	/**
	 * 判断是否有子节点
	 * @return boolean
	 */
	public boolean judjedHaveChildById(String id);
	
	
	/**
	 * 根据参数查询组织结构
	 * @param organizational
	 * @return Organizational
	 */
	public Organizational getOrganizationalByQureyParam(Organizational organizational);
	
	/**
	 * 根据组织结构对象集合和总数来获得分页所需要的JSON格式的数据
	 * @param orgList 组织结构对象集合
	 * @param count 总数
	 * @return String
	 */
	public String getJSONStrByListAndCount(List<Organizational> orgList, Long count);
	
	/**
	 * 根据组织结构对象集合来获得JSON字符串
	 * @param orgList 组织结构对象集合
	 * @return String
	 */
	public String getJSONStrByList(List<Organizational> orgList);
	
	/*-----------------------------------------组织结构类型维护-----------------------------------------------*/
	/**
	 * 根据参数查询组织结构类型对象集合
	 * @param orgType 组织结构类型对象
	 * @return List<OrganizationalType> 组织结构类型对象集合
	 */
	public List<OrganizationalType> getOrganizationalTypeListByQureyParam(OrganizationalType orgType, PageHelper pageHelper);
	
	/**
	 * 根据参数查询记录数
	 * @param hql 查询语句
	 * @param queryParam 查询参数
	 * @return Long 总数
	 */
	public Long getOrganizationalTypeCountByQueryParam(OrganizationalType orgType);
	
	/**
	 * 根据参数查询组织结构类型对象
	 * @param orgType 组织结构类型对象
	 * @return OrganizationalType 组织结构类型对象
	 */
	public OrganizationalType getOrganizationalTypeByQureyParam(OrganizationalType orgType);
	
	/**
	 * 添加组织结构类型
	 * @param orgType 组织结构类型对象
	 * @return boolean
	 */
	public boolean addOrganizationalType(OrganizationalType orgType);
	
	/**
	 * 修改组织结构类型
	 * @param orgType 组织结构类型
	 * @return boolean
	 */
	public boolean updateOrganizationaType(OrganizationalType orgType);
	
	/**
	 * 删除组织结构类型
	 * @param ids 组织结构类型id集合
	 * @return boolen
	 */
	public boolean deleteOrganizationaType(String[] ids);
	
	/**
	 * 获得组织结构类型表格json数据
	 * @param orgTypeList 组织结构类型对象集合
	 * @param count 总数
	 * @return String json字符串
	 */
	public String getOrganizationalTypeJSONStr(List<OrganizationalType> orgTypeList, Long count);
	
	/**
	 * 获得组织结构类型json数据
	 * @param orgTypeList 组织结构类型对象集合
	 * @return String json字符串
	 */
	public String getOrganizationalTypeJSONStr(List<OrganizationalType> orgTypeList);
	
}
