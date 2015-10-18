package com.clearnight.oa.filemanage.dao;

import java.util.List;
import java.util.Map;

import com.clearnight.oa.filemanage.bean.FileBean;
import com.clearnight.oa.login.bean.Account;

public interface IFileManageDao {
	
	/**
	 * 获得数据库中所有的FileBean数据
	 * @return List<FileBean>
	 */
	public List<FileBean> getFileBeanByParentId(String fileId, Account account);
	
	/**
	 * 保存文件
	 * @param fileBean 保存文件
	 */
	public void saveFileBean(FileBean fileBean);
	
	/**
	 * 根据参数查询
	 * @param hql 查询语句
	 * @param queryParams 查询条件
	 * @return List<FileBean>
	 */
	public List<FileBean> getFileBeanListByParam(String hql, Map<String, Object> queryParams);
	
	/**
	 * 删除文件
	 * @param fileBean 文件对象
	 */
	public void deleteFileBean(FileBean fileBean);
	
	/**
	 * 根据id查询文件
	 * @param hql 查询语句
	 * @return FileBean 文件对象
	 */
	public FileBean getFileBeanById(String hql);
}
