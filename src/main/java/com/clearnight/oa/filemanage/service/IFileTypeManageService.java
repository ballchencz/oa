package com.clearnight.oa.filemanage.service;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;
import com.clearnight.oa.base.bean.PageHelper;
import com.clearnight.oa.filemanage.bean.FileType;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;

/**
 * 文件类型管理service接口
 * @author 陈钊
 * 2015-5-24
 */
public interface IFileTypeManageService {
	
	/**
	 * 根据查询参数获得文件类型管理表格所需要的JSON数据
	 * @param fileType 文件类型对象
	 * @param ph 分页对象
	 * @return String
	 */
	public String getFileTypeDataGridJSON(FileType fileType, PageHelper ph);
	
	/**
	 * 根据查询参数获得文件类型的总数
	 * @param fileType 文件类型对象
	 * @return Long
	 */
	public Long getFileTypeCountByParams(FileType fileType);
	
	/**
	 * 添加或删除文件类型
	 * @param fileType 文件类型对象
	 */
	public void amFileType(FileType fileType);
	
	/**
	 * 上传文件
	 * @param uploadFile 上传文件对象
	 */
	public void uploadFile(FileType fileType, MultipartFile uploadFile)throws SftpException, JSchException, NumberFormatException, IOException;
	
	/**
	 * 删除文件类型
	 * @param ids 文件类型id数组
	 */
	public void deleteFileType(String[] ids);
	
	/**
	 * 获得文件输出字节数组
	 * @param filePath 文件路径
	 * @return byte[] 文件字节数组
	 */
	public byte[] getImg(String filePath) throws JSchException, SftpException, IOException; 
	
	/**
	 * 根据文件类型获得文件类型对象
	 * @param fileType 文件类型
	 * @return FileType 文件类型对象
	 */
	public FileType getFileTypeByType(String fileType);
}
