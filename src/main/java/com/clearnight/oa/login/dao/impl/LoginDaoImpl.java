package com.clearnight.oa.login.dao.impl;

import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.clearnight.oa.base.dao.IBaseDao;
import com.clearnight.oa.login.bean.Account;
import com.clearnight.oa.login.dao.ILoginDao;

@Repository
public class LoginDaoImpl implements ILoginDao {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(LoginDaoImpl.class);

	/*---------------spring注入-------------------*/
	private SessionFactory sessionFactory;
	private IBaseDao<Account> baseDao;
	
	@Override
	public void addAccount(Account account) {
		sessionFactory.getCurrentSession().save(account);
	}

	@Override
	public void updateAccount(Account account) {
		sessionFactory.getCurrentSession().update(account);

	}

	@Override
	public void deleteAccount(Account account) {
		sessionFactory.getCurrentSession().delete(account);

	}

	@Override
	public Account getAccountById(String id) {
		String hql = "from Account where id = ?";
		return (Account)sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, id).uniqueResult();
	}

	@Override
	public List<Account> getAccountList() {
		String hql = "from Account";
		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}

	@Override
	public Account validLogin(String hql,Map<String, Object> queryMap) {
		Account account = this.baseDao.get(hql, queryMap);
		return account;
	}

	
	/*---------------setter、getter方法----------------------------*/
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public IBaseDao<Account> getBaseDao() {
		return baseDao;
	}
	@Autowired
	public void setBaseDao(IBaseDao<Account> baseDao) {
		this.baseDao = baseDao;
	}

	
	
}
