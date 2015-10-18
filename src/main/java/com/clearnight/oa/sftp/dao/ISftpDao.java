package com.clearnight.oa.sftp.dao;

import java.util.List;
import java.util.Map;

import com.clearnight.oa.sftp.bean.Sftp;

public interface ISftpDao {
	
	/**
	 * 添加sftp服务器
	 * @param sftp sftp服务器对象
	 */
	public void saveSftp(Sftp sftp);
	
	/**
	 * 修改sftp服务器
	 * @param sftp sftp服务器对象
	 */
	public void update(Sftp sftp);
	
	/**
	 * 根据参数查询sftp服务器对象集合（分页）
	 * @param hql 查询语句
	 * @param queryParams 查询参数
	 * @param page 第几页
	 * @param rows 每页几行
	 * @return List<Sftp> sftp服务器对象集合
	 */
	public List<Sftp> getSftpListByQueryParams(String hql, Map<String, Object> queryParams, Integer page, Integer rows);
	
	/**
	 * 根据参数查询sftp文件服务器对象总数
	 * @param hql 查询语句
	 * @param queryParams 查询参数
	 * @return Long 查询结果
	 */
	public Long getSftpListCountByQureyParams(String hql, Map<String, Object> queryParams);
	
	/**
	 * 根据参数查询sftp服务器对象集合
	 * @param hql  查询语句
	 * @param queryParams 查询参数
	 * @return List<Sftp> sftp服务器对象集合
	 */
	public List<Sftp> getSftpListByQueryParams(String hql, Map<String, Object> queryParams);
	
	/**
	 * 根据参数查询sftp服务器对象
	 * @param hql 查询语句
	 * @param queryParams 查询参数
	 * @return Sftp sftp服务器对象
	 */
	public Sftp getSftpByQueryParams(String hql, Map<String, Object> queryParams);
	
	/**
	 * 删除sftp服务器 
	 * @param hql 删除语句
	 */
	public void deleteSftp(String hql);
	

}
