package com.clearnight.oa.filemanage.dao;

import java.util.List;
import java.util.Map;

import com.clearnight.oa.filemanage.bean.FileType;

/**
 * 文件类型管理Dao接口
 * @author 陈钊
 * 2015-5-24
 */
public interface IFileTypeManageDao {
	/**
	 * 获得文件类型对象（分页）
	 * @param hql hql语句
	 * @param queryParams 查询参数
	 * @param page 第几页
	 * @param rows 每页几行
	 * @return List<FileType>
	 */
	public List<FileType> getFileTypeListByQueryParams(String hql,
													   Map<String, Object> queryParams, Integer page, Integer rows);
	/**
	 * 根据查询参数获得文件类型总数
	 * @param hql 查询语句
	 * @param queryParams 查询参数
	 * @return Long
	 */
	public Long getFileTypeCountByQueryParams(String hql, Map<String, Object> queryParams);
	
	/**
	 * 添加文件类型
	 * @param fileType 文件类型对象
	 */
	public void addFileType(FileType fileType);
	
	/**
	 * 修改文件类型
	 * @param fileType 文件类型对象
	 */
	public void updateFileType(FileType fileType);
	
	/**
	 * 根据id数组删除文件类型
	 * @param fileTypeIds
	 */
	public void deleteFileType(String hql, Map<String, Object> parames);
	
	/**
	 * 根据id获得文件类型对象
	 * @param id 文件类型对象id
	 * @return FileType 文件类型对象
	 */
	public FileType getFileTypeById(String id);
	
	/**
	 * 根据文件类型获得文件类型对象
	 * @param hql 查询语句
	 * @return queryParam 查询参数
	 */
	public FileType getFileTypeByQueryParam(String hql, Map<String, Object> queryParam);
}
