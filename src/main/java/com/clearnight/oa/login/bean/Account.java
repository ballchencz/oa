package com.clearnight.oa.login.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="T_ACCOUNT")
public class Account {

	// Fields

	private String id;
	private String accountName;
	private String accountPwd;


	
	public Account() {
	}

	public Account(String id, String accountName, String accountPwd) {
		this.id = id;
		this.accountName = accountName;
		this.accountPwd = accountPwd;
	}

	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false, length = 50)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "account_name", nullable = false, length = 35)
	public String getAccountName() {
		return this.accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	@Column(name = "account_pwd", nullable = false, length = 35)
	public String getAccountPwd() {
		return this.accountPwd;
	}

	public void setAccountPwd(String accountPwd) {
		this.accountPwd = accountPwd;
	}

}