package com.clearnight.oa.filemanage.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.clearnight.oa.base.dao.IBaseDao;
import com.clearnight.oa.filemanage.bean.FileType;
import com.clearnight.oa.filemanage.dao.IFileTypeManageDao;
/**
 * 文件类型管理Dao实现类
 * @author 陈钊
 * 2015-5-24
 */
@Repository
public class FileTypeManageDaoImpl implements IFileTypeManageDao {
	
	@Autowired
	private  IBaseDao<FileType> baseDao;
	
	@Override
	public List<FileType> getFileTypeListByQueryParams(String hql,
			Map<String, Object> queryParams, Integer page, Integer rows) {
		List<FileType> sftpList = baseDao.find(hql, queryParams, page, rows);
		return sftpList;
	}

	@Override
	public Long getFileTypeCountByQueryParams(String hql,
			Map<String, Object> queryParams) {
		Long count = baseDao.count(hql, queryParams);
		return count;
	}

	@Override
	public void addFileType(FileType fileType) {
		this.baseDao.save(fileType);
		
	}

	@Override
	public void updateFileType(FileType fileType) {
		this.baseDao.update(fileType);
		
	}

	@Override
	public void deleteFileType(String hql,Map<String,Object> parames) {
		this.baseDao.delete(hql, parames);
		
	}

	@Override
	public FileType getFileTypeById(String id) {
		FileType fileType = baseDao.get(FileType.class, id);
		return fileType;
	}

	@Override
	public FileType getFileTypeByQueryParam(String hql,
			Map<String, Object> queryParam) {
		FileType fileType = baseDao.get(hql, queryParam);
		return fileType;
	}


}
