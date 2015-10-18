package com.clearnight.oa.sftp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.clearnight.oa.base.bean.PageHelper;
import com.clearnight.oa.sftp.bean.Sftp;
import com.clearnight.oa.sftp.consts.SftpConsts;
import com.clearnight.oa.sftp.dao.ISftpDao;
import com.clearnight.oa.sftp.service.ISftpService;

@Service
@Transactional
public class SftpServiceImpl implements ISftpService {
	
	@Autowired
	private ISftpDao sftpDao;

	@Override
	public boolean saveSftp(Sftp sftp) {
		try {
			sftp.setId(UUID.randomUUID().toString());
			sftpDao.saveSftp(sftp);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean updateSftp(Sftp sftp) {
		try {
			sftpDao.update(sftp);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean deleteSftp(String[] ids) {
		String idStr = "";
		for(String id : ids){
			idStr +="'"+id+"',";
		}
		idStr = idStr.substring(0, idStr.lastIndexOf(","));
		String hql = "DELETE FROM Sftp t WHERE t.id in("+idStr+")";
		try {
			sftpDao.deleteSftp(hql);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Sftp> getSftpListByQureyParams(Sftp sftp, PageHelper ph) {
		String hql = "FROM Sftp t";
		Map<String,Object> queryParams = new HashMap<String,Object>();
		hql += whereHqlForSftp(sftp, queryParams);
		hql += orderHql(ph);
		List<Sftp> sftpList = this.sftpDao.getSftpListByQueryParams(hql, queryParams,ph.getPage(),ph.getRows());
		return sftpList;
	}
	
	@Override
	public Long getSftpListCountByQureyParams(Sftp sftp) {
		String hql = "SELECT COUNT(*) FROM Sftp t";
		Map<String,Object> queryParams = new HashMap<String,Object>();
		hql += whereHqlForSftp(sftp, queryParams);		
		Long longRs = this.sftpDao.getSftpListCountByQureyParams(hql, queryParams);
		return longRs;
	}


	@Override
	public List<Sftp> getSftpListByQureyParams(Sftp sftp) {
		String hql = "FROM Sftp t";
		Map<String,Object> queryParams = new HashMap<String,Object>();
		hql += whereHqlForSftp(sftp, queryParams);
		List<Sftp> sftpList = this.sftpDao.getSftpListByQueryParams(hql, queryParams);
		return sftpList;
	}

	@Override
	public Sftp getSftpByQureyParams(Sftp sftp) {
		String hql = "FROM Sftp t";
		Map<String,Object> queryParams = new HashMap<String,Object>();
		hql += whereHqlForSftp(sftp, queryParams);
		Sftp sftpRs = this.sftpDao.getSftpByQueryParams(hql, queryParams);
		return sftpRs;
	}
	
	@Override
	public String getPagenationSftpListJsonStr(List<Sftp> sftpList, Long total) {
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		for(Sftp sftp : sftpList){
			String jsonStr = JSON.toJSONString(sftp);
			JSONObject jsonO = JSON.parseObject(jsonStr);
			jsonO.put("operation", "");
			jsonArray.add(jsonO);
		}
		jsonObject.put("rows", jsonArray.toArray());
		jsonObject.put("total", total);
		return jsonObject.toJSONString();
	}
	
	@Override
	public boolean judjedIfHaveStartSftp(String id) {
		String hql = "FROM Sftp t WHERE t.status=:status";
		Map<String,Object> queryMap = new HashMap<String,Object>();
		queryMap.put("status", 0);
		if(id!=null){			
			hql += " and id!=:id";
			queryMap.put("id",id);
		}
		List<Sftp> sftpList = this.sftpDao.getSftpListByQueryParams(hql, queryMap);
		if(sftpList==null){			
			return true;
		}else if(sftpList!=null&&sftpList.size()==0){
			return true;
		}else{			
			return false;
		}
	}
	
	
	/**
	 * hql语句拼接
	 * @param sftp sftp文件服务器对象
	 * @param params 
	 * @return String 拼接好的hql语句
	 */
	private String whereHqlForSftp(Sftp sftp, Map<String, Object> params) {
		String hql = "";
		if (sftp != null) {
			hql += " where 1=1";
			if (sftp.getId() != null) {
				hql += " and t.id = :id";
				params.put("id",sftp.getId());
			}
			if (sftp.getSftpName() != null) {
				hql += " and t.sftpName like :sftpName";
				params.put("sftpName", "%%"+sftp.getSftpName()+"%%");
			}
			if (sftp.getHost()!=null) {
				hql += " and t.host = :host";
				params.put("host", sftp.getHost());
			}
			if(sftp.getStatus()!=null){
				hql += " and t.status = :status";
				params.put("status", sftp.getStatus());
			}
		}
		return hql;
	} 
	
	/**
	 * 排序字段的查询语句组装
	 * @param ph
	 * @return String
	 */
	private String orderHql(PageHelper ph) {
		String orderString = "";
		if (ph.getSort() != null && ph.getOrder() != null) {
			orderString = " order by t." + ph.getSort() + " " + ph.getOrder();
		}
		return orderString;
	}

	@Override
	public Sftp getStartSftpServer() {
		Sftp tempSftp = new Sftp();
		tempSftp.setStatus(SftpConsts.SFTPSERVER_START);
		Sftp sftp = this.getSftpByQureyParams(tempSftp);
		return sftp;
	}


}
