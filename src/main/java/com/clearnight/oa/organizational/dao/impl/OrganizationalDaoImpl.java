package com.clearnight.oa.organizational.dao.impl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.clearnight.oa.organizational.bean.Organizational;
import com.clearnight.oa.organizational.bean.OrganizationalType;
import com.clearnight.oa.organizational.dao.IOrganizationalDao;


@Repository
@Transactional
public class OrganizationalDaoImpl implements IOrganizationalDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	private Session session;
	private Query q;
	
	@Override
	public void addOrganizational(Organizational organizational) {
		session = sessionFactory.getCurrentSession();
		session.save(organizational);
	}

	@Override
	public void updateOrganizational(Organizational organizational) {
		session = sessionFactory.getCurrentSession();
		session.update(organizational);
	}


	@Override
	public List<Organizational> getOrganizationalListByQureyParam(
			String hql, Map<String, Object> queryParam, long page, long rows) {
		
		q = this.getCurrentSession().createQuery(hql);
		/*添加查询参数*/
		if (queryParam != null && !queryParam.isEmpty()) {
			for (String key : queryParam.keySet()) {
				q.setParameter(key, queryParam.get(key));
			}
		}
		
		/*根据页数和每页记录条数来查询组织结构对象集合*/
		List<Organizational> orgList = 
				q.setFirstResult((int) ((page - 1) * rows)).setMaxResults((int) rows).list();
		
		return orgList;
	}

	@Override
	public Long getOrganizationalListCountByQureyParam(String hql,
			Map<String, Object> queryParam) {
		
		q = this.getCurrentSession().createQuery(hql);
		/*加入查询参数*/
		if (queryParam != null && !queryParam.isEmpty()) {
			for (String key : queryParam.keySet()) {
				q.setParameter(key, queryParam.get(key));
			}
		}
	
		/*获得查询记录*/
		Long result = (Long) q.uniqueResult();
		
		return result;
	}
	
	@Override
	public Organizational getOrganizationalByQureyParam(String hql,
			Map<String, Object> queryParam) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (queryParam != null && !queryParam.isEmpty()) {
			for (String key : queryParam.keySet()) {
				q.setParameter(key, queryParam.get(key));
			}
		}
		List<Organizational> l = q.list();
		if (l != null && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}

	@Override
	public void deleteOrganizational(String hql) {
		this.getCurrentSession().createQuery(hql).executeUpdate();
	}
	
	@Override
	public List<Organizational> getOrganizationalListByQureyParam(String hql,
			Map<String, Object> queryParam) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (queryParam != null && !queryParam.isEmpty()) {
			for (String key : queryParam.keySet()) {
				q.setParameter(key, queryParam.get(key));
			}
		}
		List<Organizational> l = q.list();
		return l;
	}
	
	@Override
	public List<OrganizationalType> getOrganizationalTypeListByParam(
			String hql, Map<String, Object> queryParam, Integer page, Integer rows) {
		q = this.getCurrentSession().createQuery(hql);
		/*添加查询参数*/
		if (queryParam != null && !queryParam.isEmpty()) {
			for (String key : queryParam.keySet()) {
				q.setParameter(key, queryParam.get(key));
			}
		}
		
		List<OrganizationalType> orgTypeList = null;
		if(page==null && rows==null){
			orgTypeList = q.list();
		}else{			
			/*根据页数和每页记录条数来查询组织结构对象集合*/
			orgTypeList = 
					q.setFirstResult((int) ((page - 1) * rows)).setMaxResults(rows).list();
		}
		return orgTypeList;
	
	}
	
	@Override
	public Long getOrganizationalTypeCountByQueryParam(String hql,
			Map<String, Object> queryParam) {
		q = this.getCurrentSession().createQuery(hql);
		/*加入查询参数*/
		if (queryParam != null && !queryParam.isEmpty()) {
			for (String key : queryParam.keySet()) {
				q.setParameter(key, queryParam.get(key));
			}
		}
	
		/*获得查询记录*/
		Long result = (Long) q.uniqueResult();
		
		return result;
	}

	
	@Override
	public OrganizationalType getOrganizationalTypeByParam(String hql,
			Map<String, Object> queryParam) {
		q = this.getCurrentSession().createQuery(hql);
		/*添加查询参数*/
		if (queryParam != null && !queryParam.isEmpty()) {
			for (String key : queryParam.keySet()) {
				q.setParameter(key, queryParam.get(key));
			}
		}
		
		List<OrganizationalType> orgTypeList = q.list();
		if(orgTypeList!=null && orgTypeList.size()>0){
			return orgTypeList.get(0);
		}else{			
			return null;
		}
	}
	
	@Override
	public void addOrganizationalType(OrganizationalType orgType) {
		session  = this.getCurrentSession();
		session.save(orgType);
	}
	
	@Override
	public void updateOrganizationalType(OrganizationalType orgType) {
		
		session = this.getCurrentSession();
		session.update(orgType);
		
	}
	
	@Override
	public void deleteOrganizationalType(String hql) {
		this.getCurrentSession().createQuery(hql).executeUpdate();
	}


	private Session getCurrentSession(){
		session = sessionFactory.getCurrentSession();
		return session;
	}







}
