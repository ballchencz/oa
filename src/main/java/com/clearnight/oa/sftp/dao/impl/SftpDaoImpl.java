package com.clearnight.oa.sftp.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.clearnight.oa.sftp.bean.Sftp;
import com.clearnight.oa.sftp.dao.ISftpDao;


@Repository
public class SftpDaoImpl implements ISftpDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	private Session getCurrentSession(){
		return this.sessionFactory.getCurrentSession();
	}


	@Override
	public void saveSftp(Sftp sftp) {
		this.getCurrentSession().save(sftp);
		
	}


	@Override
	public void update(Sftp sftp) {
		this.getCurrentSession().update(sftp);	
	}


	@Override
	public List<Sftp> getSftpListByQueryParams(String hql,
			Map<String, Object> queryParams, Integer page, Integer rows) {
		
		Query q = this.getCurrentSession().createQuery(hql);
	
		if (queryParams != null && !queryParams.isEmpty()) {
			for (String key : queryParams.keySet()) {
				q.setParameter(key, queryParams.get(key));
			}
		}
		
	
		List<Sftp> sftpList = 
				q.setFirstResult((int) ((page - 1) * rows)).setMaxResults((int) rows).list();
		return sftpList;
	}


	@Override
	public List<Sftp> getSftpListByQueryParams(String hql,
			Map<String, Object> queryParams) {
		Query q = this.getCurrentSession().createQuery(hql);
		
		if (queryParams != null && !queryParams.isEmpty()) {
			for (String key : queryParams.keySet()) {
				q.setParameter(key, queryParams.get(key));
			}
		}
		
	
		List<Sftp> sftpList = q.list();
		return sftpList;
	}


	@Override
	public Sftp getSftpByQueryParams(String hql, Map<String, Object> queryParams) {
		Query q = this.getCurrentSession().createQuery(hql);
		
		if (queryParams != null && !queryParams.isEmpty()) {
			for (String key : queryParams.keySet()) {
				q.setParameter(key, queryParams.get(key));
			}
		}
		
	
		List<Sftp> sftpList = q.list();
		Sftp sftp = null;
		if(sftpList!=null&&sftpList.size()>0){
			sftp = sftpList.get(0);
		}
		return sftp;
	}

	@Override
	public Long getSftpListCountByQureyParams(String hql,
			Map<String, Object> queryParams) {
		Query q = this.getCurrentSession().createQuery(hql);
		
		if (queryParams != null && !queryParams.isEmpty()) {
			for (String key : queryParams.keySet()) {
				q.setParameter(key, queryParams.get(key));
			}
		}
		Long rs = (Long) q.uniqueResult();
		return rs;
	}
	
	@Override
	public void deleteSftp(String hql) {
		this.getCurrentSession().createQuery(hql).executeUpdate();
	}



}
