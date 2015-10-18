package com.clearnight.oa.organizational.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="T_ORGANIZATIONAL_TYPE")
public class OrganizationalType {

	@Id
	@Column(name="ID",length=50)
	private String id;
	
	@Column(name="TYPE_NAME",length=50)
	private String typeName;
	
	@Column(name="TYPE_VALUE",length=50)
	private String typeValue;
	
	@Column(name="MARK",length=500)
	private String mark;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getTypeValue() {
		return typeValue;
	}
	public void setTypeValue(String typeValue) {
		this.typeValue = typeValue;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	
	
}
