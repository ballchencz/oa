package com.clearnight.oa.filemanage.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.clearnight.oa.base.consts.BaseConsts;
import com.clearnight.oa.filemanage.bean.FileBean;
import com.clearnight.oa.filemanage.bean.FileType;
import com.clearnight.oa.filemanage.consts.FileManageConsts;
import com.clearnight.oa.filemanage.dao.IFileManageDao;
import com.clearnight.oa.filemanage.service.IFileManageService;
import com.clearnight.oa.filemanage.service.IFileTypeManageService;
import com.clearnight.oa.login.bean.Account;
import com.clearnight.oa.sftp.bean.Sftp;
import com.clearnight.oa.sftp.consts.SftpConsts;
import com.clearnight.oa.sftp.service.ISftpService;
import com.clearnight.oa.sftp.util.SftpUtils;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;

@Service
@Transactional
public class FileManageServiceImpl implements IFileManageService {
	@Autowired
	private ISftpService sftpService;
	@Autowired
	private IFileManageDao fileManageDao;
	@Autowired
	private IFileTypeManageService fileTypeManageService;
	
	
	@Override
	public String getFileStructureDataGridJSONString(String fileId,Account account) {
		/*返回的JSON字符串*/
		String returnJSON = null;
		/*获得开启的文件服务器信息*/
		Sftp sftp = this.getStartSftpServiceInfo();
		/*如果有开启的文件服务器，则执行获得数据的代码*/
		if(sftp!=null){
			/*获得所有文件对象集合*/
			List<FileBean> fileBeanList = this.fileManageDao.getFileBeanByParentId(fileId,account);
			/*将文件对象集合转换成JSONArray的字符串*/
			String fileBeanJSONArrayString = 
					JSONArray.toJSONStringWithDateFormat(fileBeanList, BaseConsts.dateTimeStringFormat, SerializerFeature.WriteDateUseDateFormat);
			/*将文件对象集合转换成DateGrid所需要的JSONArray数据*/
			JSONArray jsonArray = JSONArray.parseArray(fileBeanJSONArrayString);
			/*最终DateGrid所需要的JSON数据*/
			JSONObject jsonObject = new JSONObject();
			jsonObject.put(BaseConsts.DATAGRIDTOTALCONST, 1);
			jsonObject.put(BaseConsts.DATAGRIDROWSCONST, jsonArray.toArray());
			returnJSON = jsonObject.toJSONString();
		}else{
			/*如果没有开启的文件服务器，返回空JSON字符串*/
			returnJSON = FileManageConsts.NULLJSONSTRING;
		}
		return returnJSON;
	}
	/**
	 * 获得开启的服务器信息
	 * @return Sftp
	 */
	private Sftp getStartSftpServiceInfo(){
		Sftp tempSftp = new Sftp();
		tempSftp.setStatus(SftpConsts.SFTPSERVER_START);
		Sftp sftp = this.sftpService.getSftpByQureyParams(tempSftp);
		return sftp;
	}
	
	@Override
	public void saveFolder(FileBean fileBean,String parentId,String userId) {
		fileBean.setId(UUID.randomUUID().toString());
		fileBean.setFileType("folder");
		fileBean.setLastModifyTime(new Date());
		fileBean.setParentId(parentId);
		fileBean.setUserId(userId);
		fileBean.setFileUrl("");
		fileBean.setFileSize(0L);
		this.fileManageDao.saveFileBean(fileBean);
	}
	@Override
	public byte[] getFileByteByFileTypeStr(String fileType) throws JSchException, SftpException, IOException {
		FileType fileTypeObject = this.fileTypeManageService.getFileTypeByType(fileType);
		byte[] b = this.fileTypeManageService.getImg(fileTypeObject.getImgURL());
		return b;
	}
	@Override
	public boolean canAddFolder(String fileName,String id) {
		String hql = "FROM FileBean t WHERE t.fileName=:fileName AND t.parentId=:parentId";
		Map<String,Object> queryParams = new HashMap<String,Object>();
		queryParams.put("fileName", fileName);
		queryParams.put("parentId", id);
		List<FileBean> fileBeans = this.fileManageDao.getFileBeanListByParam(hql, queryParams);
		if(fileBeans!=null && fileBeans.size()>0){
			return false;
		}else{
			return true;
		}
	}
	@Override
	public void deleteFileBean(String id) throws JSchException, SftpException {
		String hql = "FROM FileBean t WHERE t.parentId = :parentId";
		FileBean fileBean = this.getFileBeanById(id);
		String fileType = fileBean.getFileType();
		if(!fileType.equals(FileManageConsts.FOLDERSTR)){
			Sftp sftp = this.getStartSftpServiceInfo();
			if(sftp!=null){
				SftpUtils sftpUtils = new SftpUtils(sftp.getUserName(), sftp.getPassword(), sftp.getHost(), sftp.getPort());
					sftpUtils.removeFile(fileBean.getUserId());
			}
		}
		this.fileManageDao.deleteFileBean(fileBean);
		Map<String,Object> queryParams = new HashMap<String,Object>();
		queryParams.put("parentId", id);
		List<FileBean> fileBeans = this.fileManageDao.getFileBeanListByParam(hql, queryParams);
		for(FileBean file : fileBeans){
			this.deleteFileBean(file.getId());
		}
		
	}
	@Override
	public FileBean getFileBeanById(String id) {
		String hql = "FROM FileBean t WHERE t.id='"+id+"'";
		FileBean fileBean = this.fileManageDao.getFileBeanById(hql);
		return fileBean;
	}
	
	
	


}
