package com.clearnight.oa.menu.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.clearnight.oa.menu.bean.MenuInfo;
import com.clearnight.oa.menu.dao.IMenuDao;
import com.clearnight.oa.menu.service.IMenuService;

/**
 * 菜单service实现类
 * @author ChenZhao
 *
 */
@Service
@Transactional
public class MenuServiceImpl implements IMenuService {
	
	/*------------spring注入-------------------------*/
	private IMenuDao menuDao;

	@Override
	public void addMenu(MenuInfo menuInfo) {
		this.menuDao.addMenu(menuInfo);
		
	}
	@Override
	public void updateMenu(MenuInfo menuInfo) {
		this.menuDao.updateMenu(menuInfo);
	}
	@Override
	public void deleteMenuById(String id) {
		this.menuDao.deleteMenuById(id);
		
	}
	@Override
	public MenuInfo getMenuInfoById(String id) {
		MenuInfo menuInfo = this.menuDao.getMenuInfoById(id);
		return menuInfo;
	}
	@Override
	public List<MenuInfo> getMenuInfoListByMenuName(String menuName) {
		Map<String,Object> queryMap = new HashMap<String,Object>();
		queryMap.put("menuName", menuName);
		String hql = "from MenuInfo where menuName = :menuName";
		List<MenuInfo> menuInfoList = this.menuDao.getMenuInfoList(hql, queryMap);
		return menuInfoList;
	}
	
	@Override
	public List<MenuInfo> getMenuInfoListByfid(String fid) {
		Map<String,Object> queryMap = new HashMap<String,Object>();
		queryMap.put("fid", fid);
		String hql;
		List<MenuInfo> menuInfoList;
		/*如果fid为空，直接查询fid为空的菜单对象*/
		if(null==fid){			
			hql = "FROM MenuInfo WHERE parentId IS NULL";
			menuInfoList = this.menuDao.getMenuInfoList(hql);
		}
		else{			
			hql = "FROM MenuInfo WHERE parentId = :fid";
			menuInfoList = this.menuDao.getMenuInfoList(hql, queryMap);
		}
		return menuInfoList;
	}
	
	@Override
	public List<MenuInfo> getAllMenuInfo() {
		return this.menuDao.getAllMenuInfo();
	}

	@Override
	public List<MenuInfo> getMenuInfoListPagenation(String fid,int rows,int page) {
		String hql;
		if("0".equals(fid))
			hql = "FROM MenuInfo WHERE parentId IS NULL";
		else
			hql = "FROM MenuInfo WHERE parentId='"+fid+"'";
		return this.menuDao.getMenuInfoListPagenation(hql, rows, page);
	}
	
	@Override
	public String getRootMenuJSON(List<MenuInfo> menuInfoList) {
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		for(int i=0;i<menuInfoList.size();i++){
			JSONObject jsonO = new JSONObject();
			jsonO.put("id", menuInfoList.get(i).getId());
			jsonO.put("parentId", menuInfoList.get(i).getParentId());
			jsonO.put("menuName", menuInfoList.get(i).getMenuName());
			jsonO.put("visibility", menuInfoList.get(i).getVisibility());
			jsonO.put("menuUrl", menuInfoList.get(i).getMenuUrl());
			jsonO.put("mark", menuInfoList.get(i).getMark());
			jsonO.put("state", "closed");
			jsonArray.add(jsonO);
		}
		jsonObject.put("rows", jsonArray.toArray());
		long total = this.getCount();
		jsonObject.put("total", ""+total+"");
		return jsonObject.toJSONString();
	}
	
	@Override
	public String getChildMenuJSON(List<MenuInfo> menuInfoList) {
		JSONArray jsonArray = new JSONArray();
		for(MenuInfo menuInfo : menuInfoList){
			JSONObject jsonO = new JSONObject();
			jsonO.put("id", menuInfo.getId());
			jsonO.put("parentId", menuInfo.getParentId());
			/*查询出上级菜单名称*/
			String parentName = 
					this.getMenuInfoById(menuInfo.getParentId()).getMenuName();
			jsonO.put("parentName",parentName);
			jsonO.put("menuName", menuInfo.getMenuName());
			jsonO.put("visibility", menuInfo.getVisibility());
			jsonO.put("menuUrl", menuInfo.getMenuUrl());
			jsonO.put("mark", menuInfo.getMark());
			jsonO.put("state", "closed");
			jsonArray.add(jsonO);
		}
		return jsonArray.toJSONString();
	}
	
	@Override
	public long getCount() {
		String hql = "SELECT COUNT(*) FROM MenuInfo WHERE parentId IS NULL";
		long count = this.menuDao.getCount(hql);
		return count;
	}
	
	
	/*------------setter、getter方法-----------------*/
	public IMenuDao getMenuDao() {
		return menuDao;
	}
	@Autowired
	public void setMenuDao(IMenuDao menuDao) {
		this.menuDao = menuDao;
	}





}
