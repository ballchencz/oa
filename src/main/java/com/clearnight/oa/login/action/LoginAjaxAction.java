package com.clearnight.oa.login.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.clearnight.oa.base.bean.PageHelper;
import com.clearnight.oa.base.consts.BaseConsts;
import com.clearnight.oa.base.service.IBaseService;
import com.sun.xml.internal.rngom.parse.host.Base;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import com.clearnight.oa.base.util.MD5Util;
import com.clearnight.oa.login.bean.Account;
import com.clearnight.oa.login.service.ILoginService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

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
	@Autowired
	private IBaseService baseService;
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

	/**
	 * 根据查询参数获得账户表格数据
	 * @param account 查询参数
	 * @param pageHelper 分页参数
	 * @return String 表格数据
	 */
	@RequestMapping(value = "/getAccountPagenation",method = {RequestMethod.POST},produces = {BaseConsts.JSON_UTF8_CHARSET})
	@ResponseBody
	public String getAccountPagenation(Account account,PageHelper pageHelper){
		String returnValue = this.loginService.getAccountPagenationData(account,pageHelper);
		return returnValue;
	}

	/**
	 * 添加或修改账户信息
	 * @param account 账户信息
	 * @return
	 */
	@RequestMapping(value = "/amAccount",method = {RequestMethod.POST},produces = {BaseConsts.JSON_UTF8_CHARSET})
	@ResponseBody
	public String amAccount(Account account){
		String returnValue;
		if(account.getId()!=null&&!account.getId().equals("")){//修改
			try {
				account.setAccountPwd(MD5Util.md5(account.getAccountPwd()));
				this.loginService.updateAccount(account);
				returnValue = this.baseService.returnValue(BaseConsts.UPDATE_INFO_SUCCESS,true);
			} catch (Exception e) {
				e.printStackTrace();
				returnValue = this.baseService.returnValue(BaseConsts.UPDATE_INFO_FAILD,false);
			}
		}else{//添加
			account.setId(UUID.randomUUID().toString());
			account.setInsertDate(new Date());
			account.setAccountPwd(MD5Util.md5(account.getAccountPwd()));
			try {
				this.loginService.addAccount(account);
				returnValue = this.baseService.returnValue(BaseConsts.INSERT_INFO_SUCCESS,true);
			} catch (Exception e) {
				e.printStackTrace();
				returnValue = this.baseService.returnValue(BaseConsts.INSERT_INFO_FAILD,false);
			}
		}
		return returnValue;
	}

	@RequestMapping(value = "/deleteAccount",method = {RequestMethod.POST},produces = {BaseConsts.JSON_UTF8_CHARSET})
	@ResponseBody
	public String deleteAccount(String [] ids){
		String returnValue = "";
		try {
			this.loginService.deleteAccount(ids);
			returnValue = this.baseService.returnValue("删除成功",true);
		} catch (Exception e) {
			e.printStackTrace();
			returnValue = this.baseService.returnValue("删除失败",false);
		}
		return returnValue;
	}

	@InitBinder
	protected void initBinder(HttpServletRequest request,
							  ServletRequestDataBinder binder) throws Exception {
		baseService.bindDateParameter(request,binder);
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
