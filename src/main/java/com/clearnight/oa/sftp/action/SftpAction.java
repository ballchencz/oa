package com.clearnight.oa.sftp.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.clearnight.oa.base.bean.PageHelper;
import com.clearnight.oa.sftp.bean.Sftp;
import com.clearnight.oa.sftp.service.ISftpService;

@Controller
@RequestMapping(value="/sftpAction")
public class SftpAction {
	
	@Autowired
	private ISftpService sftpService;
	
	/**
	 * 转向sftp服务器管理页面
	 * @return ModelAndView 视图
	 */
	@RequestMapping(value="/toSftpManagePage",method={RequestMethod.GET,RequestMethod.POST})
	public String toSftpManagePage(){
		return "/sftp/sftpManagePage";
	}
	
	/**
	 * 分页数据
	 * @param sftp sftp文件服务器对象
	 * @param ph 分页参数对象
	 * @return String
	 */
	@RequestMapping(value="/getSftpManagePageData",method={RequestMethod.GET},produces={"application/json;charset=utf-8"})
	@ResponseBody
	public String getSftpManagePageData(Sftp sftp,PageHelper ph){
		List<Sftp> sftpList = this.sftpService.getSftpListByQureyParams(sftp, ph);
		Long count = this.sftpService.getSftpListCountByQureyParams(sftp);
		String jsonStr = this.sftpService.getPagenationSftpListJsonStr(sftpList, count);
		return jsonStr;
	}
	
	/**
	 * 添加sftp文件服务器
	 * @param sftp sftp文件服务器对象
	 * @return String
	 */
	@RequestMapping(value="/addSftp",method={RequestMethod.POST},produces={"application/json;charset=utf-8"})
	@ResponseBody
	public String addSftp(Sftp sftp){
		boolean flag = false;
		JSONObject jsonObject = new JSONObject();
		if(sftp.getStatus()==0){
			flag = this.sftpService.judjedIfHaveStartSftp(null);
			if(flag){				
				flag = this.sftpService.saveSftp(sftp);
				if(flag){					
					jsonObject.put("info", "添加成功");
				}else{					
					jsonObject.put("info", "添加失败");
				}
			}else{
				jsonObject.put("info", "已有开启的文件服务器");
			}
		}else{
			flag = this.sftpService.saveSftp(sftp);
			if(flag){
				jsonObject.put("info", "添加成功");
			}else{
				jsonObject.put("info", "添加失败");			
			}
		}
		jsonObject.put("flag", flag);
		return jsonObject.toJSONString();
	}
	
	@RequestMapping(value="/deleteSftpServer",method={RequestMethod.POST},produces={"application/json;charset=utf-8"})
	@ResponseBody
	public String deleteSftpServer(Sftp sftp){
		return null;
	}
	
	/**
	 * 修改sftp文件服务器
	 * @param sftp sftp文件服务器
	 * @return String
	 */
	@RequestMapping(value="/updateSftp",method={RequestMethod.POST},produces={"application/json;charset=utf-8"})
	@ResponseBody
	public String updateSftp(Sftp sftp){
		boolean flag = false;
		JSONObject jsonObject = new JSONObject();
		if(sftp.getStatus()==0){
			flag = this.sftpService.judjedIfHaveStartSftp(sftp.getId());
			if(flag){				
				flag = this.sftpService.updateSftp(sftp);
				if(flag){					
					jsonObject.put("info", "修改成功");
				}else{
					jsonObject.put("info", "修改失败");					
				}
			}else{
				jsonObject.put("info", "已有开启的文件服务器");
			}
		}else{			
			flag = this.sftpService.updateSftp(sftp);			
			if(flag){
				jsonObject.put("info", "修改成功");
			}else{
				jsonObject.put("info", "修改失败");			
			}
		}
		jsonObject.put("flag", flag);
		return jsonObject.toJSONString();
	}
	
	@RequestMapping(value="/updataSftpStatus",method={RequestMethod.POST},produces={"application/json;charset=utf-8"})
	@ResponseBody
	public String updataSftpStatus(String id,Integer status){
		JSONObject jsonObject = new JSONObject();
		boolean flag = false;
		if(status==0){
			boolean judjedFlag = this.sftpService.judjedIfHaveStartSftp(id);
			jsonObject.put("flag", judjedFlag);
			if(judjedFlag){
				Sftp sftp = new Sftp();
				sftp.setId(id);
				sftp = this.sftpService.getSftpByQureyParams(sftp);
				if(sftp!=null){
					sftp.setStatus(status);					
					flag = this.sftpService.updateSftp(sftp);
				}
				if(flag){					
					jsonObject.put("info", "开启成功");
				}else{
					jsonObject.put("info", "开启失败");
				}
			}else{
				jsonObject.put("info", "开启失败，只能开启一个文件服务器");
			}
		}else{
			Sftp sftp = new Sftp();
			sftp.setId(id);
			sftp = this.sftpService.getSftpByQureyParams(sftp);
			sftp.setStatus(status);
			if(sftp!=null){				
				flag = this.sftpService.updateSftp(sftp);
			}
			jsonObject.put("flag", flag);
			if(flag){
				jsonObject.put("info", "关闭成功");
			}else{
				jsonObject.put("info", "关闭失败");
			}
			
		}
		return jsonObject.toJSONString();
		
	}
	
}
