package com.clearnight.oa.login.action;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.clearnight.oa.login.service.ILoginService;

@Controller
@RequestMapping("/loginAction")
public class LoginAction {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(LoginAction.class);
	
	/*----------------spring注入-------------------*/
	private ILoginService loginService;
	private HttpServletRequest request;
	/*----------------方法区------------------------*/
	/**
	 * 转向登陆页面
	 * @return 登陆页面
	 */
	@RequestMapping(value="/toLoginPage",method = RequestMethod.GET)
	public String toLoginPage(){
		logger.info("------------------登陆action------------------");
		String s = request.getContentType();
		return "/login/login";
	}
	
	/**
	 * 转向framework页面
	 * @return 布局页面
	 */
	@RequestMapping(value="/toFrameWorkPageAction",method=RequestMethod.GET)
	public String toFrameWorkPageAction(){
		
		return "/login/framework";
	}
	
	/**
	 * 转向中间页面
	 * @return 中间面板页面
	 */
	@RequestMapping(value="/toCenterPage",method=RequestMethod.GET)
	public String toCenterPage(){
		return "/login/center";
	}

	/**
	 * 转向时钟页面
	 * @return String
	 */
	@RequestMapping(value="/toLockPage",method=RequestMethod.GET)
	public String toLockPage(){
		return "/login/lock";
	}

	/**
	 * 转向账户管理容器页面
	 * @return String
	 */
	@RequestMapping(value = "/toAccountManageContentPage")
	public String toAccountManageContentPage(){
		return "/login/accountManageContentPage";
	}

	/**
	 * 转向账户管理页面
	 * @return String
	 */
	@RequestMapping(value="/toAccountManagePage")
	public String toAccountManagePage(){
		return "/login/accountManagePage";
	}
	/*-------------------setter、getter方法------------------------*/
	public ILoginService getLoginService() {
		return loginService;
	}
	@Autowired
	public void setLoginService(ILoginService loginService) {
		this.loginService = loginService;
	}

	public HttpServletRequest getRequest() {
		return request;
	}
	@Autowired
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	
	
	
}
