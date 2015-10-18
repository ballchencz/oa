package com.clearnight.oa.organizational.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.clearnight.oa.base.bean.PageHelper;
import com.clearnight.oa.organizational.bean.Organizational;
import com.clearnight.oa.organizational.bean.OrganizationalType;
import com.clearnight.oa.organizational.dao.IOrganizationalDao;
import com.clearnight.oa.organizational.service.IOrganizationalService;

@Service
@Transactional
public class OrganizationalServiceImpl implements IOrganizationalService {
	
	@Autowired
	private IOrganizationalDao organizationalDao;
	private boolean flag = false;

	@Override
	public boolean addOrganizational(Organizational organizational) {
		try{
			organizational.setId(UUID.randomUUID().toString());
			organizational.setAddTime(new Date());
			organizationalDao.addOrganizational(organizational);
			flag = true;
		}catch(Exception e){
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	@Override
	public boolean updateOrganizational(Organizational organizational) {
		try {
			organizationalDao.updateOrganizational(organizational);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	@Override
	public boolean deleteOrganizational(String[] ids) {
		
		String idStr = "";
		for(String id : ids){
			idStr +="'"+id+"',";
		}
		idStr = idStr.substring(0, idStr.lastIndexOf(","));
		String hql = "DELETE FROM Organizational t WHERE t.id in("+idStr+") OR t.parentId in("+idStr+")";
		
		try {
			organizationalDao.deleteOrganizational(hql);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	@Override
	public List<Organizational> getOrganizationalListByQueryParam(
			Organizational organizational, PageHelper pageHelper) {
		String hql = "FROM Organizational t";
		Map<String,Object> queryMap = new HashMap<String,Object>();
		hql += whereHqlForOrg(organizational,queryMap);
		hql += orderHql(pageHelper);
		List<Organizational> orgList = 
			organizationalDao.getOrganizationalListByQureyParam(hql, queryMap, pageHelper.getPage(), pageHelper.getRows());
		return orgList;
	}
	
	@Override
	public Long getOrganizationalListCountByQureyParam(
			Organizational organizational, PageHelper pageHelper) {
		String hql = "SELECT COUNT(*) FROM Organizational t";
		Map<String,Object> queryMap = new HashMap<String,Object>();
		hql += this.whereHqlForOrg(organizational, queryMap);
		Long total = this.organizationalDao.getOrganizationalListCountByQureyParam(hql, queryMap);
		return total;
	}
	
	@Override
	public Organizational getOrganizationalByQureyParam(
			Organizational organizational) {
		String hql = "FROM Organizational t";
		Map<String,Object> queryMap = new HashMap<String,Object>();
		hql += this.whereHqlForOrg(organizational, queryMap);
		Organizational org = this.organizationalDao.getOrganizationalByQureyParam(hql, queryMap);
		return org;
	}
	
	@Override
	public List<Organizational> getOrganizationalListByQureyParam(
			Organizational organizational) {
		String hql = "FROM Organizational t";
		Map<String,Object> queryMap = new HashMap<String,Object>();
		hql += this.whereHqlForOrg(organizational, queryMap);
		List<Organizational> orgList = this.organizationalDao.getOrganizationalListByQureyParam(hql, queryMap);
		return orgList;
	}
	
	@Override
	public List<Organizational> getOrganizationalListByParentId(String parentId) {
		List<Organizational> orgList = null;
		String hql = null;
		if(null!=parentId){			
			hql = "FROM Organizational t WHERE t.parentId = :parentId";
			Map<String,Object> queryMap = new HashMap<String,Object>();
			queryMap.put("parentId", parentId);
			orgList = this.organizationalDao.getOrganizationalListByQureyParam(hql, queryMap);
		}else{
			hql = "FROM Organizational t WHERE t.parentId IS NULL";
			orgList = this.organizationalDao.getOrganizationalListByQureyParam(hql, null);
		}
		return orgList;
	}

	
	@Override
	public boolean judjedHaveChildById(String id) {
		Organizational organizational = new Organizational();
		organizational.setParentId(id);
		Organizational org = this.getOrganizationalByQureyParam(organizational);
		if(org!=null){
			return true;			
		}else{
			return false;
		}
	}
	
	@Override
	public String getJSONStrByListAndCount(List<Organizational> orgList,
			Long count) {
		JSONObject finalObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		for(Organizational org : orgList){
			
			String jsonStr = JSON.toJSONStringWithDateFormat(org,"yyyy-MM-dd");
			
			JSONObject jsonObject = JSON.parseObject(jsonStr);
			jsonObject.put("iconCls", "icon-dept");
			boolean flag = this.judjedHaveChildById(org.getId());
			if(flag){
				jsonObject.put("state", "closed");
			}
			if(org.getParentId()==null){
				jsonObject.put("parentId", "");
				jsonObject.put("state", "closed");
			}
			jsonArray.add(jsonObject);
		}
		finalObject.put("total",count);
		finalObject.put("rows", jsonArray.toArray());
		
		return finalObject.toJSONString();
	}	
	
	@Override
	public String getJSONStrByList(List<Organizational> orgList) {
		JSONArray jsonArray = new JSONArray();
		for(Organizational org : orgList){
			
			String jsonStr = JSON.toJSONStringWithDateFormat(org,"yyyy-MM-dd");
			
			JSONObject jsonObject = JSON.parseObject(jsonStr);
			jsonObject.put("iconCls", "icon-dept");
			boolean flag = this.judjedHaveChildById(org.getId());
			if(flag){
				jsonObject.put("state", "closed");
			}
			if(org.getParentId()==null){
				jsonObject.put("parentId", "");
				jsonObject.put("state", "closed");
			}
			jsonArray.add(jsonObject);
		}
		return jsonArray.toJSONString();
	}
	
	/**
	 * 查询hql语句组装
	 * @param user
	 * @param params
	 * @return String
	 */
	private String whereHqlForOrg(Organizational org, Map<String, Object> params) {
		String hql = "";
		if (org != null) {
			hql += " where 1=1 ";
			if (org.getOrgName() != null) {
				hql += " and t.orgName like :orgName";
				params.put("orgName", "%%" + org.getOrgName() + "%%");
			}
			if (org.getAddTime() != null) {
				hql += " and t.addTime = :addTime";
				params.put("addTime", org.getAddTime());
			}
			if (org.getOrgType()!=null) {
				hql += " and t.orgType = :orgType";
				params.put("orgType", org.getOrgType());
			}
			if(org.getParentId()!=null){
				hql += " and t.parentId = :parentId";
				params.put("parentId", org.getParentId());
			}else{
				hql += " and t.parentId IS NULL";
				//params.put("", value);
			}
		}
		return hql;
	}
/*-----------------------------------------------组织结构类型维护-------------------------------------------------------*/
	@Override
	public List<OrganizationalType> getOrganizationalTypeListByQureyParam(
			OrganizationalType orgType,PageHelper pageHelper) {
		String hql = "FROM OrganizationalType t";
		Map<String,Object> queryMap = new HashMap<String,Object>();
		hql += whereHqlForOrgType(orgType,queryMap);
		hql += orderHql(pageHelper);
		List<OrganizationalType> orgTypeList = 
			organizationalDao.getOrganizationalTypeListByParam(hql, queryMap, pageHelper.getPage(),pageHelper.getRows());
		return orgTypeList;
	}
	
	@Override
	public Long getOrganizationalTypeCountByQueryParam(OrganizationalType orgType) {
		String hql = "SELECT COUNT(*) FROM OrganizationalType t";
		Map<String,Object> queryParams = new HashMap<String,Object>();
		hql += whereHqlForOrgType(orgType, queryParams);
		return this.organizationalDao.getOrganizationalTypeCountByQueryParam(hql,queryParams);
	}

	@Override
	public OrganizationalType getOrganizationalTypeByQureyParam(
			OrganizationalType orgType) {
		String hql = "FROM OrganizationalType t";
		Map<String,Object> queryMap = new HashMap<String,Object>();
		hql += whereHqlForOrgType(orgType,queryMap);
		OrganizationalType orgTypeRs = organizationalDao.getOrganizationalTypeByParam(hql, queryMap);
		return orgTypeRs;
	}

	@Override
	public boolean addOrganizationalType(OrganizationalType orgType) {
		orgType.setId(UUID.randomUUID().toString());
		try {
			this.organizationalDao.addOrganizationalType(orgType);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	@Override
	public boolean updateOrganizationaType(OrganizationalType orgType) {
		try {
			this.organizationalDao.updateOrganizationalType(orgType);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	@Override
	public boolean deleteOrganizationaType(String[] ids) {
		String idStr = "";
		for(String id : ids){
			idStr +="'"+id+"',";
		}
		idStr = idStr.substring(0, idStr.lastIndexOf(","));
		String hql = "DELETE FROM OrganizationalType t WHERE t.id in("+idStr+")";
		
		try {
			organizationalDao.deleteOrganizationalType(hql);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	
	@Override
	public String getOrganizationalTypeJSONStr(
			List<OrganizationalType> orgTypeList, Long count) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("total", count);
		JSONArray jsonArray = new JSONArray();
		for(OrganizationalType orgType : orgTypeList){
			String jsonStr = JSON.toJSONString(orgType);
			JSONObject jsonO = JSON.parseObject(jsonStr);
			jsonArray.add(jsonO);
		}
		jsonObject.put("rows", jsonArray.toArray());
		return jsonObject.toJSONString();
	}
	
	@Override
	public String getOrganizationalTypeJSONStr(
			List<OrganizationalType> orgTypeList) {
		JSONArray jsonArray = new JSONArray();
		for(int i=0;i<orgTypeList.size();i++){
			OrganizationalType orgType = orgTypeList.get(i);
			String jsonStr = JSON.toJSONString(orgType);
			if(i==0){
				JSONObject jsonO = JSONObject.parseObject(jsonStr);
				jsonO.put("selected", true);
				jsonStr = jsonO.toJSONString();
			}
			JSONObject jsonO = JSON.parseObject(jsonStr);
			jsonArray.add(jsonO);
		}
		return jsonArray.toJSONString();
	}
	
	
	private String whereHqlForOrgType(OrganizationalType orgType, Map<String, Object> params) {
		String hql = "";
		if (orgType != null) {
			hql += " where 1=1 ";
			if(orgType.getId() != null){
				hql += " and t.id = :id";
				params.put("id", orgType.getId());
			}
			if (orgType.getTypeName() != null) {
				hql += " and t.typeName like :typeName";
				params.put("typeName", "%%" + orgType.getTypeName() + "%%");
			}
			if(orgType.getTypeValue() != null){
				hql += " and t.typeValue = :typeValue";
				params.put("typeValue", orgType.getTypeValue());
			}
		}
		return hql;
	}
	/*-------------------------------------------------------公用方法------------------------------------------------*/	
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


}
