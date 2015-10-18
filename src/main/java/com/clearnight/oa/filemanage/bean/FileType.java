package com.clearnight.oa.filemanage.bean;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="T_FILETYPEMANAGE")
public class FileType {
	/**
	 * 主键ID
	 */
	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 50)
	private String id;
	/**
	 * 文件类型名称
	 */
	@Column(name = "FILE_TYPENAME", nullable = false, length = 255)
	private String fileTypeName;
	/**
	 * 文件类型代表图片地址
	 */
	@Column(name = "IMG_URL", nullable = false, length = 4000)
	private String imgURL;
	/**
	 * 文件类型代表图片大小
	 */
	@Column(name = "IMG_SIZE", precision = 2, scale = 0)
	private Long imgSize;

	@Column(name = "IMG_NAME", nullable = false, length = 4000)
	private String imgName;
	/**
	 * 文件类型代码
	 */
	@Column(name = "TYPE_CODE", nullable = false, length = 255)
	private String typeCode;
	/**
	 * 备注
	 */
	@Column(name = "mark", nullable = true, length = 4000)
	private String mark;
	
	
	
	/*----------------------------setter、getter-------------------------*/
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFileTypeName() {
		return fileTypeName;
	}
	public void setFileTypeName(String fileTypeName) {
		this.fileTypeName = fileTypeName;
	}
	public String getImgURL() {
		return imgURL;
	}
	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}
	public Long getImgSize() {
		return imgSize;
	}
	public void setImgSize(Long imgSize) {
		this.imgSize = imgSize;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public String getImgName() {
		return imgName;
	}
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	
	
	
}
