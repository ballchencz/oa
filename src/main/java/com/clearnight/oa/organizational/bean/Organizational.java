package com.clearnight.oa.organizational.bean;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="T_ORGANIZATIONAL")
public class Organizational {
	
	/*---主键ID---*/
	@Id
	@Column(name="ID",length=50)
	private String id;
	
	/*---组织名称---*/
	@Column(name="ORG_NAME",length=50)
	private String orgName;
	
	/*---组织类型---*/
	@Column(name="ORG_TYPE",length=50)
	private String orgType;
	
	/*---添加时间---*/
	@Temporal(TemporalType.DATE)
	@Column(name="ADD_TIME",length=7)
	private Date addTime;
	
	/*---父节点ID---*/
	@Column(name="PARENT_ID",length=50)
	private String parentId;
	
	/*---备注---*/
	@Column(name="MARK",length=4000)
	private String mark;

	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}
	
	
	

}
