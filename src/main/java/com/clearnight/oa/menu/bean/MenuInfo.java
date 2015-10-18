package com.clearnight.oa.menu.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
@Entity
@Table(name="T_MENUINFO")
public class MenuInfo {
	// Fields

	private String id;
	private String parentId;
	private String parentName;
	private String menuName;
	private String menuUrl;
	private Boolean visibility;
	private String menuType;
	private String mark;

	// Constructors

	/** default constructor */
	public MenuInfo() {
	}

	/** minimal constructor */
	public MenuInfo(Boolean visibility) {
		this.visibility = visibility;
	}

	/** full constructor */
	public MenuInfo(String parentId, String menuName, String menuUrl,
			Boolean visibility, String menuType, String mark) {
		this.parentId = parentId;
		this.menuName = menuName;
		this.menuUrl = menuUrl;
		this.visibility = visibility;
		this.menuType = menuType;
		this.mark = mark;
	}
	
	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 50)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "PARENT_ID", length = 50)
	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@Column(name = "MENU_NAME", length = 30)
	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	@Column(name = "MENU_URL", length = 200)
	public String getMenuUrl() {
		return this.menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	@Column(name = "VISIBILITY", nullable = false)
	@Type(type="yes_no")
	public Boolean getVisibility() {
		return this.visibility;
	}

	public void setVisibility(Boolean visibility) {
		this.visibility = visibility;
	}

	@Column(name = "MENU_TYPE", length = 20)
	public String getMenuType() {
		return this.menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	@Column(name = "MARK", length = 500)
	public String getMark() {
		return this.mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}
	@Column(name = "PARENT_NAME", length = 500)
	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}


	
	
}
