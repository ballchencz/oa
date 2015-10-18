package com.clearnight.oa.login.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clearnight.oa.login.bean.Account;
import com.clearnight.oa.login.dao.ILoginDao;
import com.clearnight.oa.login.service.ILoginService;

@Service
@Transactional
public class LoginServiceImpl implements ILoginService{

	/*-------------------spring注入---------------*/
	/**
	 * 登陆dao层
	 */
	private ILoginDao loginDao;

	@Override
	public void addAccount(Account account) {
		this.loginDao.addAccount(account);
	}
	@Override
	public void updateAccount(Account account) {
		this.loginDao.updateAccount(account);
	}
	@Override
	public void deleteAccount(Account account) {
		this.loginDao.deleteAccount(account);
		
	}
	@Override
	public Account getAccountById(String id) {
		return this.loginDao.getAccountById(id);
	}
	@Override
	public List<Account> getAccountList() {
		return this.loginDao.getAccountList();
	}

	@Override
	public Account validLogin(Account account) {
		Map<String,Object> queryMap = new HashMap<String,Object>();
		queryMap.put("accountName", account.getAccountName());
		queryMap.put("accountPwd", account.getAccountPwd());
		String hql = "from Account t where t.accountName = :accountName and t.accountPwd = :accountPwd";
		return this.loginDao.validLogin(hql, queryMap);
	}
	
	
	/*-------------------setter、getter方法-----------*/
	public ILoginDao getLoginDao() {
		return loginDao;
	}
	@Autowired
	public void setLoginDao(ILoginDao loginDao) {
		this.loginDao = loginDao;
	}

	
}
