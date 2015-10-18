package com.clearnight.oa.filemanage.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.clearnight.oa.base.bean.PageHelper;
import com.clearnight.oa.base.consts.BaseConsts;
import com.clearnight.oa.filemanage.bean.FileType;
import com.clearnight.oa.filemanage.consts.FileTypeManageConsts;
import com.clearnight.oa.filemanage.dao.IFileTypeManageDao;
import com.clearnight.oa.filemanage.service.IFileTypeManageService;
import com.clearnight.oa.sftp.bean.Sftp;
import com.clearnight.oa.sftp.service.ISftpService;
import com.clearnight.oa.sftp.util.SftpUtils;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
/**
 * 文件类型管理service实现类
 * @author 陈钊
 * 2015-5-24
 */
@Service
@Transactional
public class FileTypeManageServiceImpl implements IFileTypeManageService {

	@Autowired
	private IFileTypeManageDao fileTypeManageDao;
	@Autowired
	private ISftpService sftpService;
	
	@Override
	public String getFileTypeDataGridJSON(FileType fileType, PageHelper ph) {
		String  hql = "FROM FileType t";
		Map<String,Object> params = new HashMap<String,Object>();
		this.whereHqlForfileType(fileType, params);
		List<FileType> fileTypeList = this.fileTypeManageDao.getFileTypeListByQueryParams(hql, params, ph.getPage(), ph.getRows());
		Long total = this.getFileTypeCountByParams(fileType);
		String fileTypeListJSON = this.getFileTypeListDataGridJOSN(fileTypeList, total);
		return fileTypeListJSON;
	}
	
	@Override
	public Long getFileTypeCountByParams(FileType fileType) {
		String hql = "SELECT COUNT(*) FROM FileType t";
		Map<String,Object> queryParams = new HashMap<String,Object>();
		hql += this.whereHqlForfileType(fileType, queryParams);
		Long count = fileTypeManageDao.getFileTypeCountByQueryParams(hql, queryParams);
		return count;
	} 
	
	@Override
	public void amFileType(FileType fileType) {
		if(fileType.getId()==null || fileType.getId().equals("")){//id为空，为添加
			fileType.setId(UUID.randomUUID().toString());
			this.fileTypeManageDao.addFileType(fileType);
		}else{//id不为空，为修改
			this.fileTypeManageDao.updateFileType(fileType);
		}
	}
	
	/**
	 * 根据FileType对象集合获得FileType对象集合的JSON数据
	 * @param fileTypeList  文件对象集合
	 * @param total 总数
	 * @return String
	 */
	private String getFileTypeListDataGridJOSN(List<FileType> fileTypeList,Long total){
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = JSONArray.parseArray(JSONArray.toJSONStringWithDateFormat(fileTypeList, BaseConsts.dateTimeStringFormat, SerializerFeature.WriteDateUseDateFormat));
		jsonObject.put(BaseConsts.DATAGRIDROWSCONST, jsonArray.toArray());
		jsonObject.put(BaseConsts.DATAGRIDTOTALCONST, total);
		return jsonObject.toString();
	}
	


	
	/**
	 * 拼装查询sql语句,获得参数Map集合
	 * @param fileType 文件类型对象
	 * @param params 查询参数集合
	 * @return String 查询语句
	 */
	private String whereHqlForfileType(FileType fileType, Map<String, Object> params) {
		String hql = "";
		if (fileType != null) {
			hql += " where 1=1";
			if (fileType.getId() != null) {
				hql += " and t.id = :id";
				params.put("id",fileType.getId());
			}
			if (fileType.getFileTypeName() != null) {
				hql += " and t.fileTypeName like :fileTypeName";
				params.put("fileTypeName", "%%"+fileType.getFileTypeName()+"%%");
			}
			if (fileType.getImgSize()!=null) {
				hql += " and t.imgSize = :imgSize";
				params.put("imgSize", fileType.getImgSize());
			}

		}
		return hql;
	}

	@Override
	public void uploadFile(FileType fileType,MultipartFile uploadFile) throws SftpException, JSchException, NumberFormatException, IOException {
		
		Sftp sftp = sftpService.getStartSftpServer();
				if(sftp!=null){						
						/*获得文件名*/
						String fileName = uploadFile.getOriginalFilename();
						/*获得文件类型和文件对象的*/
						int pointIndex = fileName.lastIndexOf(".");
						/*获得文件类型*/
						String fileTypeStr = "";
						if(pointIndex>0){							
							fileTypeStr = fileName.substring(pointIndex, fileName.length());
						}
						/*给文件服务器上的文件起一个新的名字*/
						String newFileName = UUID.randomUUID().toString()+fileTypeStr;
						/*文件服务器上的文件路径（带有文件名称）*/
						String filePath = FileTypeManageConsts.SYSIMAGEURL+newFileName;
						/*若文件类型的id不为空,再判断上传的文件名是否为空，若文件名为空，则保持原文件，若不为空，则说明文件改变，删除原文件，添加新文件*/
						if(fileType.getId()!=null && !fileType.getId().equals("")){
							if(!fileName.equals("")){
								SftpUtils sftpUtils = new SftpUtils(sftp.getUserName(), sftp.getPassword(), sftp.getHost(), sftp.getPort());
								sftpUtils.connect();
								sftpUtils.removeFile(fileType.getImgURL());
								fileType.setImgSize(uploadFile.getSize());
								fileType.setImgURL(filePath);
								fileType.setImgSize(Long.valueOf(String.valueOf(uploadFile.getInputStream().available())));
								fileType.setImgName(fileName);
								sftpUtils.uploadFile(uploadFile.getInputStream(), filePath);
								sftpUtils.disconnect();
							}
						}else{
							SftpUtils sftpUtils = new SftpUtils(sftp.getUserName(), sftp.getPassword(), sftp.getHost(), sftp.getPort());
							sftpUtils.connect();
							/*设置FileType对象参数*/
							fileType.setImgSize(uploadFile.getSize());
							fileType.setImgURL(filePath);
							fileType.setImgSize(Long.valueOf(String.valueOf(uploadFile.getInputStream().available())));
							fileType.setImgName(fileName);
							sftpUtils.uploadFile(uploadFile.getInputStream(), filePath);
							sftpUtils.disconnect();
						}
			}
		}

	@Override
	public byte[] getImg(String filePath) throws JSchException, SftpException, IOException {
		Sftp sftp = sftpService.getStartSftpServer();
		SftpUtils sftpUtils = new SftpUtils(sftp.getUserName(), sftp.getPassword(), sftp.getHost(), sftp.getPort());
		sftpUtils.connect();
		byte[] b = sftpUtils.getFileByteArrayByFileArray(filePath);
		return b;
	}

	@Override
	public void deleteFileType(String[] ids) {
		String hql = "DELETE FROM FileType t WHERE t.id in (:ids) ";
		Map<String,Object> parames = new HashMap<String,Object>();
		parames.put("ids", ids);
		/*删除文件服务器上所对应的图片*/
		boolean flag = this.deleteFileTypeImg(ids);
		if(flag){			
			this.fileTypeManageDao.deleteFileType(hql, parames);
		}
	}
	
	private boolean deleteFileTypeImg(String [] ids){
		boolean flag = false;
		Sftp sftp = sftpService.getStartSftpServer();
		SftpUtils sftpUtils = new SftpUtils(sftp.getUserName(), sftp.getPassword(), sftp.getHost(), sftp.getPort());
		try {
			sftpUtils.connect();
			for(String id : ids){
				FileType fileType = this.fileTypeManageDao.getFileTypeById(id);
				/*删除文件类型图片*/
				sftpUtils.removeFile(fileType.getImgURL());
				flag = true;
			}
		}catch (JSchException e1) {
			flag = false;
			e1.printStackTrace();
		}catch (SftpException e) {
			flag = false;
			e.printStackTrace();
		}finally{
			sftpUtils.disconnect();
		}
		return flag;
	}

	@Override
	public FileType getFileTypeByType(String fileType) {
		String hql = "FROM FileType t where t.typeCode = :fileType";
		Map<String,Object> queryParam = new HashMap<String,Object>();
		queryParam.put("fileType", fileType);
		FileType fileTypeObject = this.fileTypeManageDao.getFileTypeByQueryParam(hql, queryParam);
		return  fileTypeObject;
	}




}
