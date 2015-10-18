package com.clearnight.oa.sftp.service;

import java.util.List;

import com.clearnight.oa.base.bean.PageHelper;
import com.clearnight.oa.sftp.bean.Sftp;


public interface ISftpService {

	/**
	 * 添加sftp文件服务器
	 * @param sftp sftp文件服务器对象
	 * @return boolean 成功与否
	 */
	public boolean saveSftp(Sftp sftp);
	
	/**
	 * 修改sftp文件服务器
	 * @param sftp sftp文件服务器
	 * @return boolean 成功与否
	 */
	public boolean updateSftp(Sftp sftp);
	
	/**
	 * 根据id删除sftp文件服务器对象
	 * @param ids 主键id集合
	 * @return boolean 成功与否的标志
	 */
	public boolean deleteSftp(String[] ids);

	/**
	 * 根据查询参数获得sftp文件服务器对象集合（分页）
	 * @param sftp sftp文件服务器对象
	 * @param ph 分页参数对象
	 * @return List<Sftp> sftp对象集合
	 */
	public List<Sftp> getSftpListByQureyParams(Sftp sftp, PageHelper ph);
	
	/**
	 * 根据参数查询sftp文件服务器对象总数
	 * @param hql 查询语句
	 * @param queryParams 查询参数
	 * @return Long 查询结果
	 */
	public Long getSftpListCountByQureyParams(Sftp sftp);
	
	/**
	 * 根据查询参数获得sftp文件服务器对象集合
	 * @param sftp sftp文件服务器对象
	 * @return List<Sftp> sftp对象集合
	 */
	public List<Sftp> getSftpListByQureyParams(Sftp sftp);
	
	/**
	 * 根据查询参数获得sft文件服务器对象
	 * @param sftp sftp文件服务器对象
	 * @return Sftp sftp文件服务器对象
	 */
	public Sftp getSftpByQureyParams(Sftp sftp);
	
	/**
	 * 获得sftp文件服务器分页集合的json字符串
	 * @param sftpList sftp文件服务器对象集合
	 * @param total 总数
	 * @return String JSON字符串
	 */
	public String getPagenationSftpListJsonStr(List<Sftp> sftpList, Long total);
	
	/**
	 * 判断是否有开启的sftp服务器
	 * @return boolean
	 */
	public boolean judjedIfHaveStartSftp(String id);
	
	/**
	 * 获得开启的文件服务器对象 
	 * @return Sftp
	 */
	public Sftp getStartSftpServer();
	
}
