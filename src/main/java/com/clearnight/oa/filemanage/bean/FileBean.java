package com.clearnight.oa.filemanage.bean;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 * 文件管理数据封装类
 * @author 陈钊
 * 2015-5-17
 */
@Entity
@Table(name="T_FILEMANAGE")
public class FileBean {
	/**
	 * 主键ID
	 */
	private String id;
	/**
	 * 文件名称
	 */
	private String fileName;
	/**
	 * 文件类型
	 */
	private String fileType;
	/**
	 *文件大小 
	 */
	private Long fileSize;
	/**
	 * 最后修改时间
	 */
	private Date lastModifyTime;
	/**
	 * 上级ID
	 */
	private String parentId;
	/**
	 * 用户ID
	 */
	private String userId;
	/**
	 * 备注
	 */
	private String mark;
	
	/**
	 * 文件路径
	 */
	private String fileUrl;
	
    /**
	 * 上传时间
	 */
    private Date uploadTime;
	
	
	
	/*---------------setter、getter---------------------------------------*/
	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 50)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name = "FILE_NAME", nullable = false, length = 255)
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	@Column(name = "FILE_TYPE", nullable = false, length = 255)
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
	@Column(name = "FILE_SIZE", precision = 2, scale = 0)
	public Long getFileSize() {
		return fileSize;
	}
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LAST_MODIFY_TIME", length = 19)
	public Date getLastModifyTime() {
		return lastModifyTime;
	}
	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}
	@Column(name = "PARENT_ID", nullable = false, length = 50)
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	@Column(name = "USER_ID", nullable = false, length = 50)
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Column(name = "MARK", length = 4000)
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	@Column(name = "FILE_URL", nullable = false, length = 4000)
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPLOAD_TIME", length = 19)
	public Date getUploadTime(){
        return uploadTime;   
	}
	
    public void setUploadTime(Date uploadTime){
        this.uploadTime = uploadTime;
    }
	
	
}
