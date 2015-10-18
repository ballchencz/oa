package com.clearnight.oa.user.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.clearnight.oa.user.service.IUserService;

@Controller
@RequestMapping("/userAction")
public class UserAction {
	/*----------------spring注入-------------------*/
	private IUserService userService;
	private HttpServletRequest request;
	/*----------------方法区------------------------*/
	
	/**
	 * 转向用户管理页面
	 * @return String
	 */
	@RequestMapping(value="/toUserManagerPage",method=RequestMethod.GET)
	public String toUserManagerPage(){
		return "/user/userManagerPage";
	}
	
	/*----------------setter、getter方法------------*/
	public IUserService getUserService() {
		return userService;
	}
	@Autowired
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	@Autowired
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	
	
}
