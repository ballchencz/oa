package com.clearnight.oa.menu.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.clearnight.oa.base.dao.IBaseDao;
import com.clearnight.oa.menu.bean.MenuInfo;
import com.clearnight.oa.menu.dao.IMenuDao;


/**
 * 菜单Dao实现类
 * @author ChenZhao
 *
 */
@Repository
public class MenuDaoImpl implements IMenuDao {

	/*--------------------spring注入---------------------*/
	private SessionFactory sessionFactory;
	private IBaseDao<MenuInfo> baseDao;
	
	/*--------------------公用变量------------------------*/
	private List<MenuInfo> menuInfoList;
	private MenuInfo menuInfo;
	
	@Override
	public void addMenu(MenuInfo menuInfo) {
		this.sessionFactory.getCurrentSession().save(menuInfo);
		
	}
	@Override
	public void updateMenu(MenuInfo menuInfo) {
		this.baseDao.update(menuInfo);
	}
	@Override
	public void deleteMenuById(String id) {
		menuInfo = this.baseDao.get(MenuInfo.class, id);
		if(null!=menuInfo){
			this.baseDao.delete(menuInfo);
		}
	}
	@Override
	public MenuInfo getMenuInfoById(String id) {
		menuInfo = this.baseDao.get(MenuInfo.class, id);
		return menuInfo;
	}
	@Override
	public List<MenuInfo> getMenuInfoList(String hql,Map<String,Object> queryMap) {
		menuInfoList = this.baseDao.find(hql, queryMap);
		return menuInfoList;
	}
	@Override
	public List<MenuInfo> getMenuInfoList(String hql) {
		menuInfoList = this.baseDao.find(hql);
		return menuInfoList;
	}
	@Override
	public List<MenuInfo> getAllMenuInfo() {
		String hql = "from MenuInfo";
		menuInfoList = this.baseDao.find(hql);
		return menuInfoList;
	}
	
	@Override
	public List<MenuInfo> getMenuInfoListPagenation(String hql, int rows,
			int page) {
		menuInfoList = this.baseDao.find(hql, page, rows);
		return menuInfoList;
	}

	@Override
	public long getCount(String hql) {
		long count = this.baseDao.count(hql);
		return count;
	}
	
	/*--------------------setter、getter方法-------------*/
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public IBaseDao<MenuInfo> getBaseDao() {
		return baseDao;
	}
	@Autowired
	public void setBaseDao(IBaseDao<MenuInfo> baseDao) {
		this.baseDao = baseDao;
	}



}
