package com.clearnight.oa.sftp.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="T_SFTP")
public class Sftp {

	/**
	 * 主键ID
	 */
	@Id
	@Column(name="ID",length=50)
	private String id;
	
	/**
	 * 服务器名称
	 */
	@Column(name="SFTP_NAME",length=100)
	private String sftpName;
	
	/**
	 * 用户名
	 */
	@Column(name="USER_NAME",length=100)
	private String userName;
	
	/**
	 * 密码
	 */
	@Column(name="PASSWORD",length=100)
	private String password;
	
	/**
	 * 地址
	 */
	@Column(name="HOST",length=100)
	private String host;
	
	/**
	 * 端口号
	 */
	@Column(name="PORT")
	private Integer port;
	
	/**
	 * 状态
	 */
	@Column(name="STATUS")
	private Integer status;
	
	/**
	 * 备注
	 */
	@Column(name="MARK",length=2000)
	private String mark;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSftpName() {
		return sftpName;
	}

	public void setSftpName(String sftpName) {
		this.sftpName = sftpName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}
	
	
}
