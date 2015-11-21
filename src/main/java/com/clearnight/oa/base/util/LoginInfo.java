package com.clearnight.oa.base.util;

import javax.servlet.http.HttpServletRequest;

import com.clearnight.oa.user.bean.UserBasic;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.clearnight.oa.login.bean.Account;


/**
 * 获得登陆用户
 * @author 陈钊
 *
 */
public class LoginInfo {
	/**
	 * 获得登陆账户
	 * @return
	 */
	public static Account getLoginUser(){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		Account account = (Account) request.getSession().getAttribute("loginInfo");
		return account;
	}

}
