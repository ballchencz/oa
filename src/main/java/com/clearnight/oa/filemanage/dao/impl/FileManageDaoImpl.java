package com.clearnight.oa.filemanage.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.clearnight.oa.base.dao.IBaseDao;
import com.clearnight.oa.filemanage.bean.FileBean;
import com.clearnight.oa.filemanage.dao.IFileManageDao;
import com.clearnight.oa.login.bean.Account;

@Repository
public class FileManageDaoImpl implements IFileManageDao {
	@Autowired
	private IBaseDao<FileBean> baseDao;
	
	
	@Override
	public List<FileBean> getFileBeanByParentId(String fileId,Account account) {
		Map<String,Object> queryParam = new HashMap<String,Object>();
		String hql = "FROM FileBean t WHERE 1=1";
		if(account != null){
			queryParam.put("userId", account.getId());
			hql+= " AND t.userId= :userId";
		}
		if(fileId!=null && !fileId.equals("")){
			queryParam.put("fileId", fileId);
			hql+= " AND t.parentId= :fileId";
		}else{
			hql+= " AND t.parentId = ''";
		}
		List<FileBean> fileBean = baseDao.find(hql, queryParam);
		return fileBean;
	}


	@Override
	public void saveFileBean(FileBean fileBean) {
		baseDao.save(fileBean);
	}


	@Override
	public List<FileBean> getFileBeanListByParam(String hql,
			Map<String, Object> queryParams) {
		List<FileBean> fileBeans = baseDao.find(hql, queryParams);
		return fileBeans;
	}


	@Override
	public void deleteFileBean(FileBean fileBean) {
		baseDao.delete(fileBean);
	}


	@Override
	public FileBean getFileBeanById(String hql) {
		FileBean fileBean = this.baseDao.get(hql);
		return fileBean;
	}



	
}
