package com.clearnight.oa.filemanage.action;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.clearnight.oa.base.util.LoginInfo;
import com.clearnight.oa.filemanage.bean.FileBean;
import com.clearnight.oa.filemanage.consts.FileManageConsts;
import com.clearnight.oa.filemanage.service.IFileManageService;
import com.clearnight.oa.login.bean.Account;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;

/**
 * 文件管理控制层
 * @author 陈钊
 * @Date 2015-5-17
 */
@Controller
@RequestMapping(value="/fileManageAction")
public class FileManageAction {
	
	@Autowired
	private IFileManageService fileManageService;
	
	/**
	 * 获得DateGrid所需的JSON数据
	 * @return String
	 */
	@RequestMapping(value="/getDataGridJSONString",method={RequestMethod.POST,RequestMethod.GET},produces={"application/json;charset=utf-8"})
	@ResponseBody
	public String getDataGridJSONString(String fileId,String parentId){
		Account account = LoginInfo.getLoginUser();
		String returnJSON = this.fileManageService.getFileStructureDataGridJSONString(fileId,account);
		return returnJSON;
	}
	
	/**
	 * 转向文件管理页面
	 * @return
	 */
	@RequestMapping(value="/toFileManagePage",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toFileManagePage(String fileId,String parentId){
		ModelAndView modelAndView = new ModelAndView("/filemanage/fileManagePage");
		modelAndView.addObject("fileId", fileId);
		modelAndView.addObject("parentId",parentId);
		modelAndView.addObject("dirStr", FileManageConsts.FOLDERSTR);
		return modelAndView;
	}
	
	@RequestMapping(value="/saveFolder",method={RequestMethod.POST},produces={"applicaion/json;charset=utf-8"})
	@ResponseBody
	public String saveFolder(FileBean fileBean,String parentId){
		Account account = LoginInfo.getLoginUser();
		boolean flag = false;
		JSONObject jsonObject = new JSONObject();
		try {
			this.fileManageService.saveFolder(fileBean, parentId, account.getId());			
			flag = true;
			jsonObject.put("info", "添加成功！");
		} catch (Exception e) {
			flag = false;
			jsonObject.put("info", "添加失败！");
		}
		jsonObject.put("flag", flag);
		return jsonObject.toJSONString();
	}
	@RequestMapping(value="/canAddFolder",method={RequestMethod.GET},produces={"applicaion/json;charset=utf-8"})
	@ResponseBody
	public String canAddFolder(String fileName,String id){
		JSONObject jsonObject = new JSONObject();
		boolean flag = false;
		jsonObject.put("flag", flag);
		flag = this.fileManageService.canAddFolder(fileName,id);
		jsonObject.put("flag", flag);
		if(!flag){
			jsonObject.put("info", "存在同文件夹");
		}
		return jsonObject.toJSONString();
	}
	
	/**
	 * 根据类型获得图片
	 * @param imgType
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/getImageByType",method={RequestMethod.GET},produces="image/jpeg;charset=UTF-8")
	@ResponseBody
	public byte[] getImageByType(String imgType) throws IOException{
		imgType = imgType.toUpperCase();
		byte[] b = null;
		try {
			b = this.fileManageService.getFileByteByFileTypeStr(imgType);
		} catch (JSchException | SftpException e) {
			e.printStackTrace();
		}
		return b;
	}
	
	/**
	 * 删除文件
	 * @param ids 文件id组数
	 * @return String
	 */
	@RequestMapping(value="/deleteFile",method={RequestMethod.POST},produces="applicaion/json;charset=UTF-8")
	@ResponseBody
	public String deleteFile(String [] ids){
		boolean flag = false;
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("flag", flag);
		try {
			for(String id : ids){
					this.fileManageService.deleteFileBean(id);
					flag = true;
					jsonObject.put("info", "删除成功");
			}
		} catch (JSchException | SftpException e) {
			flag = false;
			jsonObject.put("info", "删除失败");
		}
		return jsonObject.toJSONString();
	}

}
