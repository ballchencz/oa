package com.clearnight.oa.user.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;




@Entity
@Table(name = "T_USER_BASIC")
public class UserBasic {
	/**
	 * 主键ID
	 */
	@Id
	@Column(name = "id", unique = true, nullable = false, length = 50)
	private String id;
	/**
	 * 工号
	 */
	@Column(name = "WORK_ID", length = 50)
	private String workId;
	/**
	 * 身份证号
	 */
	@Column(name = "ID_NUM", length = 30)
	private String idNum;
	/**
	 * 用户名
	 */
	@Column(name = "USER_NAME", nullable = false, length = 50)
	private String userName;
	/**
	 * 性别 1、男；2、女
	 */
	@Column(name = "USER_SEX", nullable = false, precision = 2, scale = 0)
	private Integer userSex;
	/**
	 * 年龄
	 */
	@Column(name = "USER_AGE", precision = 3, scale = 0)
	private Integer userAge;
	/**
	 * 出生日期
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "BIRTHDAY", length = 19)
	private Date birthday;
	/**
	 * 婚姻状况 1、未婚；2、已婚
	 */
	@Column(name = "MARITAL_STATUS", precision = 2, scale = 0)
	private Integer maritalStatus;
	
	/**
	 * 民族（代码配置在nation.json文件里）
	 */
	@Column(name = "NATION", precision = 2, scale = 0)
	private Integer nation;
	/**
	 * 籍贯
	 */
	@Column(name = "NATIVE_PLACE", length = 200)
	private String nativePlace;
	/**
	 * 政治面貌 1。群众；2.团员；3.党员；4.其它
	 */
	@Column(name = "POLITICAL_STATUS", precision = 2, scale = 0)
	private Integer politicalStatus;
	/**
	 * 个人电话
	 */
	@Column(name = "PHONE", length = 30)
	private String phone;
	/**
	 * 电子邮箱
	 */
	@Column(name = "EMAIL", length = 200)
	private String email;


	public UserBasic() {
	}

	public UserBasic(String id,String userName, Integer userSex) {
		this.id = id;
		this.userName = userName;
		this.userSex = userSex;
	}

	public UserBasic(String id,String workId,String idNum, String userName, Integer userSex, Integer userAge,
			Date birthday, Integer maritalStatus, Integer nation, String nativePlace,
			Integer politicalStatus, String phone, String email) {
		this.id = id;
		this.workId = workId;
		this.idNum = idNum;
		this.userName = userName;
		this.userSex = userSex;
		this.userAge = userAge;
		this.birthday = birthday;
		this.maritalStatus = maritalStatus;
		this.nation = nation;
		this.nativePlace = nativePlace;
		this.politicalStatus = politicalStatus;
		this.phone = phone;
		this.email = email;
	}


	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getWorkId() {
		return this.workId;
	}

	public void setWorkId(String workId) {
		this.workId = workId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	public Integer getUserSex() {
		return this.userSex;
	}

	public void setUserSex(Integer userSex) {
		this.userSex = userSex;
	}

	
	public Integer getUserAge() {
		return this.userAge;
	}

	public void setUserAge(Integer userAge) {
		this.userAge = userAge;
	}

	public Date getBirthday(){
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getMaritalStatus() {
		return this.maritalStatus;
	}

	public void setMaritalStatus(Integer maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public Integer getNation() {
		return this.nation;
	}

	public void setNation(Integer nation) {
		this.nation = nation;
	}

	public String getNativePlace() {
		return this.nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	public Integer getPoliticalStatus() {
		return this.politicalStatus;
	}

	public void setPoliticalStatus(Integer politicalStatus) {
		this.politicalStatus = politicalStatus;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}
	
}
