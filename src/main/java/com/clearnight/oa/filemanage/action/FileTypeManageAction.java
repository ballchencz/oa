package com.clearnight.oa.filemanage.action;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.clearnight.oa.base.bean.PageHelper;
import com.clearnight.oa.filemanage.bean.FileType;
import com.clearnight.oa.filemanage.service.IFileTypeManageService;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
/**
 * 文件类型管理action
 * @author 陈钊
 * 2015-5-24
 */
@Controller
@RequestMapping(value="/fileTypeManageAction")
public class FileTypeManageAction {
	@Autowired
	private IFileTypeManageService fileTypeManageService;
	/**
	 * 转向文件类型管理页面
	 * @return String
	 */
	@RequestMapping(value="/toFileTypeManage",method={RequestMethod.GET,RequestMethod.POST})
	public String toFileTypeManage(){
		return "/filemanage/fileTypeManage";
	}
	
	/**
	 * 获得文件类型管理数据表格所需要的JSON数据
	 * @param fileType 文件类型
	 * @param ph 分页对象
	 * @return String
	 */
	@RequestMapping(value="/getFileTypeDataGirdJSON",method={RequestMethod.GET},produces={"application/json;charset=utf-8"})
	@ResponseBody
	public String getFileTypeDataGridJSON(FileType fileType,PageHelper ph){
		String fileTypeManageJSON = fileTypeManageService.getFileTypeDataGridJSON(fileType, ph);
		return fileTypeManageJSON;
	}
	
	/**
	 * 图片文件类型上传
	 * @param fileType 文件类型对象
	 * @param fileTypeImg 文件类型文件
	 * @return String
	 */
	@RequestMapping(value="/amFileType",method={RequestMethod.POST},produces={"application/json;charset=utf-8"})
	@ResponseBody
	public String amFileType(FileType fileType,MultipartFile fileTypeImg){
		 
		boolean flag = false;
		JSONObject jsonObject = new JSONObject();
		try {
			this.fileTypeManageService.uploadFile(fileType, fileTypeImg);
			this.fileTypeManageService.amFileType(fileType);
			jsonObject.put("info", "保存成功！");
		} catch (Exception e) {
			e.printStackTrace();			
			jsonObject.put("info", "保存失败！");
		}
		jsonObject.put("flag", flag);
		return jsonObject.toJSONString();
	}
	
	@RequestMapping(value="/deleteFileType",method={RequestMethod.POST},produces={"application/json;charset=utf-8"})
	@ResponseBody
	public String deleteFileType(String [] ids){
		boolean flag = false;
		JSONObject jsonObject = new JSONObject();
		try {
			this.fileTypeManageService.deleteFileType(ids);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		jsonObject.put("flag", flag);
		return jsonObject.toJSONString();
	}
	
	/**
	 * 获得图片的字节数组
	 * @param filePath 文件路径
	 * @return String
	 */
	@RequestMapping(value="/getImg",method={RequestMethod.GET,RequestMethod.POST},produces="image/jpeg;charset=UTF-8")
	@ResponseBody
	public byte[] getImg(String filePath){
		byte[] b = new byte[1024];
		try {
			b = this.fileTypeManageService.getImg(filePath);
		} catch (JSchException | SftpException | IOException e) {
			e.printStackTrace();
		}
		return b;
	}
}
