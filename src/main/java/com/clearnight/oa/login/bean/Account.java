package com.clearnight.oa.login.bean;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name="T_ACCOUNT")
public class Account {

	// Fields

	private String id;
	private String accountName;
	private String accountPwd;
	private Date insertDate;
	private String userId;

	
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "INSERT_DATE", length = 19)
	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	@Column(name = "USER_ID", length = 50)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}