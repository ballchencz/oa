package com.clearnight.oa.menu.dao;

import java.util.List;
import java.util.Map;

import com.clearnight.oa.menu.bean.MenuInfo;

/**
 * 菜单dao接口
 * @author ChenZhao
 *
 */
public interface IMenuDao {
	
	/**
	 * 添加菜单
	 * @param menuInfo 菜单对象
	 */
	public void addMenu(MenuInfo menuInfo);
	
	/**
	 * 修改菜单
	 * @param menuInfo 菜单对象
	 */
	public void updateMenu(MenuInfo menuInfo);
	
	/**
	 * 根据id删除菜单
	 * @param id 菜单id
	 */
	public void deleteMenuById(String id);
	
	/**
	 * 根据id查询菜单
	 * @param hql 查询语句
	 * @param queryMap 菜单Id
	 * @return 菜单对象
	 */
	public MenuInfo getMenuInfoById(String id);
	
	/**
	 * 根据菜单属性查询菜单列表
	 * @param hql 查询语句
	 * @param queryMap 菜单属性
	 * @return 菜单对象集合
	 */
	public List<MenuInfo> getMenuInfoList(String hql, Map<String, Object> queryMap);
	
	/**
	 * 根据hql语句获得菜单对象集合
	 * @param hql 查询语句
	 * @return 菜单对象集合
	 */
	public List<MenuInfo> getMenuInfoList(String hql);
	
	/**
	 * 获得所有的菜单
	 * @return 菜单对象集合
	 */
	public List<MenuInfo> getAllMenuInfo();
	

	/**
	 * 
	 * @param hql 查询语句
	 * @param rows 每页有几条
	 * @param page 第几页
	 * @return 分页后的菜单集合
	 */
	public List<MenuInfo> getMenuInfoListPagenation(String hql, int rows, int page);
	
	/**
	 * 获得fid为空的菜单总数
	 * @return 菜单总数
	 */
	public long getCount(String hql);
	
}
