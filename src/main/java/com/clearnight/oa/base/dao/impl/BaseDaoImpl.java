package com.clearnight.oa.base.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.clearnight.oa.base.dao.IBaseDao;
/**
 * 基础Dao层，用来存储基本的增、删、改、查的方法
 * @author ChenZhao
 *
 */
@Repository("baseDao")
public class BaseDaoImpl<T> implements IBaseDao<T> {
	/**
	 * spring注入
	 */
	private SessionFactory sessionFactory;

	@Override
	public T get(Class<T> c, Serializable id) {
	
		return (T) this.getCurrentSession().get(c, id);
	}
	
	@Override
	public T get(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		List<T> l = q.list();
		if (l != null && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}
	
	
	@Override
	public T get(String hql, Map<String, Object> params) {
		
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		List<T> l = q.list();
		if (l != null && l.size() > 0) {
			return l.get(0);
		}
		return null;
		
	}
	
	
	@Override
	public List<T> find(String hql) {
		return this.getCurrentSession().createQuery(hql).list();
	}
	
	
	@Override
	public List<T> find(String hql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.list();
	}
	
	
	@Override
	public List<T> find(String hql, int page, int rows) {
		Query q = this.getCurrentSession().createQuery(hql);
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}
	
	
	@Override
	public List<T> find(String hql, Map<String, Object> params, int page, int rows) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}
	
	
	@Override
	public Long count(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		return (Long) q.uniqueResult();
	}
	
	
	@Override
	public Long count(String hql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return (Long) q.uniqueResult();
	}
	
	
	@Override
	public Serializable save(T t) {
		return this.getCurrentSession().save(t);
	}
	
	
	@Override
	public void delete(T o) {
		this.getCurrentSession().delete(o);
	}
	
	@Override
	public int delete(String hql, Map<String,Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				String [] s = (String[]) params.get(key);
				q.setParameterList(key, s);
			}
		}
		return q.executeUpdate();
	}
	
	@Override
	public void update(T o) {
		this.getCurrentSession().update(o);
	}
	
	
	@Override
	public void saveOrUpdate(T o) {
		this.getCurrentSession().saveOrUpdate(o);
		
	}
	
	@Override
	public int executeDeleteHql(String hql) {
		return this.getCurrentSession().createQuery(hql).executeUpdate();
	}
	
	/*---------------setter、getter方法------------------------*/
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/*-------------------------私有方法-------------------------*/
	private Session getCurrentSession(){
		return this.sessionFactory.getCurrentSession();
	}




	

}
