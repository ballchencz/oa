package com.clearnight.oa.login.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import com.clearnight.oa.base.util.MD5Util;
import com.clearnight.oa.login.bean.Account;
import com.clearnight.oa.login.service.ILoginService;

@Controller
@RequestMapping("/loginAjaxAction")
public class LoginAjaxAction {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(LoginAjaxAction.class);

	/*-------------spring注入------------------*/
	private ILoginService loginService;
	private HttpServletRequest request;
	/*-------------方法区-----------------------*/
	/**
	 * 验证登陆
	 * @return 验证结果（JSON格式）
	 */
	@RequestMapping(value="/validLogin",method=RequestMethod.POST)
	@ResponseBody
	public String login(Account account){
		logger.info("------------------验证登陆---------------");
		boolean flag = false;
		account.setAccountPwd(MD5Util.md5(account.getAccountPwd()));
		account = loginService.validLogin(account);
		JSONObject jsonObject = new JSONObject();
		if(account!=null){
			flag = true;
			HttpSession session = this.request.getSession();
			//session.setAttribute("accountName", account);
			request.getSession().setAttribute("loginInfo", account);
		}
		jsonObject.put("flag", flag);
		logger.info(jsonObject.toJSONString());
		logger.info("------------------验证登陆结束------------");
		return jsonObject.toJSONString();
	}
	
	
	/*-------------setter、getter方法-----------*/
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
