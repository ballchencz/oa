package com.clearnight.oa.menu.action;

import org.apache.log4j.Logger;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.clearnight.oa.menu.bean.MenuInfo;
import com.clearnight.oa.menu.service.IMenuService;

@Controller
@RequestMapping("/menuAction")
public class MenuAction {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(MenuAction.class);
	
	/*-----------------spring注入-------------------*/
	private IMenuService menuService;
	private HttpServletRequest request;
	/*-----------------方法区------------------------*/
	/**
	 * 转向右侧菜单显示页面
	 * @return 右侧菜单页面
	 */
	@RequestMapping(value="/toLeftMenuPage",method=RequestMethod.GET)
	public String toLeftMenu(){
		List<MenuInfo> menuInfoList = this.menuService.getMenuInfoListByfid(null);
		logger.info(JSON.toJSONString(menuInfoList));
		request.setAttribute("menuInfoList", menuInfoList);
		return "/menu/leftMenu";
	}

	/**
	 * 转向菜单管理页面
	 * @param menuInfo 菜单对象
	 * @return 菜单管理页面
	 */
	@RequestMapping(value="/toMenuManagerTabContent",method=RequestMethod.GET)
	public String toTabContent(MenuInfo menuInfo){
		return "/menu/menuManager";
	}
	
	@RequestMapping(value="/toMenuTreePage")
	public String toMenuTreePage(MenuInfo menuInfo){
		menuInfo = this.menuService.getMenuInfoById(menuInfo.getId());
		request.setAttribute("menuInfo", menuInfo);
		return "/menu/menuTree";
	}
	

	
	/*-----------------setter、getter方法-------------------*/
	public IMenuService getMenuService() {
		return menuService;
	}
	@Autowired
	public void setMenuService(IMenuService menuService) {
		this.menuService = menuService;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	@Autowired
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	
	
	
}
