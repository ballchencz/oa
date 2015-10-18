package com.clearnight.oa.menu.service;

import java.util.List;

import com.clearnight.oa.menu.bean.MenuInfo;

/**
 * 菜单service接口
 * @author ChenZhao
 *
 */
public interface IMenuService {
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
	 * 
	 * @param menuName 菜单名称
	 * @return 菜单对象集合 
	 */
	public List<MenuInfo> getMenuInfoListByMenuName(String menuName);
	
	/**
	 * 根据fid获得菜单对象
	 * @param fid
	 * @return
	 */
	public List<MenuInfo> getMenuInfoListByfid(String fid);

	/**
	 * 获得所有的菜单
	 * @return 菜单对象集合
	 */
	public List<MenuInfo> getAllMenuInfo();
	
	/**
	 * @param hql 查询语句
	 * @param rows 每页有几条
	 * @param page 第几页
	 * @return 分页后的菜单集合
	 */
	public List<MenuInfo> getMenuInfoListPagenation(String fid, int rows, int page);
	
	/**
	 * 获得id为0的根节点菜单对象的
	 * @param menuInfoList 菜单对象集合
	 * @return json字符串
	 */
	public String getRootMenuJSON(List<MenuInfo> menuInfoList);
	
	/**
	 * 获得id不为0
	 * @param menuInfoList
	 * @return
	 */
	public String getChildMenuJSON(List<MenuInfo> menuInfoList);
	
	/**
	 * 获得fid为空的菜单总数
	 * @return 数量
	 */
	public long getCount();
}
