package com.clearnight.oa.login.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.clearnight.oa.base.bean.PageHelper;
import com.clearnight.oa.base.consts.BaseConsts;
import com.clearnight.oa.base.service.IBaseService;
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

	/**
	 * 基础service
	 */
	@Autowired
	private IBaseService baseService;



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


	@Override
	public String getAccountPagenationData(Account account, PageHelper pageHelper) {
		String hql = "FROM Account t";
		String countHql = "SELECT COUNT(*) FROM Account t";
		Map<String,Object> queryParams = new HashMap<String,Object>();
		hql += this.baseService.whereHQL(account,queryParams);
		hql += this.baseService.orderHQL(pageHelper);
		countHql += this.baseService.whereHQL(account,queryParams);
		countHql += this.baseService.orderHQL(pageHelper);
		List<Account> accounts = this.loginDao.getAccountPagenationDate(hql,queryParams,pageHelper);
		long count = this.loginDao.getAccountCount(countHql,queryParams);
		JSONObject jsonObject = new JSONObject();
		String returnValue = JSONArray.toJSONStringWithDateFormat(accounts, BaseConsts.dateTimeStringFormat);
		jsonObject.put(BaseConsts.DATAGRIDROWSCONST,JSONArray.parseArray(returnValue).toArray());
		jsonObject.put(BaseConsts.DATAGRIDTOTALCONST,count);
		return jsonObject.toJSONString();
	}

	@Override
	public void deleteAccount(String[] ids) {
		String hql = "DELETE FROM Account t WHERE t.id in (:ids)";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("ids",ids);
		this.loginDao.deleteAcccount(hql,params);
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
