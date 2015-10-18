package com.clearnight.oa.organizational.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.clearnight.oa.base.bean.PageHelper;
import com.clearnight.oa.organizational.bean.Organizational;
import com.clearnight.oa.organizational.bean.OrganizationalType;
import com.clearnight.oa.organizational.service.IOrganizationalService;


@Controller
@RequestMapping("/organizationalAction")
public class OrganizationalAction {
	
	/*-------------------------------spring注入---------------------------*/
	@Autowired
	private IOrganizationalService organizationalService;
	
	
	/*-------------------------------方法区--------------------------------*/
	/**
	 * 转向组织结构管理页面
	 * @return String
	 */
	@RequestMapping(value="/toOrganizationalManagePage",method={RequestMethod.GET,RequestMethod.POST})
	public String toOrganizationalManagePage(){
		return "/organizational/organizationalManagePage";
	}
	
	/**
	 * 获得组织结构分页数据
	 * @return String
	 */
	@RequestMapping(value="/getOrganizationalPagenationAction",method={RequestMethod.GET,RequestMethod.POST},produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public String getOrganizationalPagenation(Organizational organizational,PageHelper pageHelper){
		String returnStr = "";
		List<Organizational> orgList;
		Long count;
		if(organizational.getId().equals("0")){			
			/*获得组织结构对象集合*/
			orgList = 
					this.organizationalService.getOrganizationalListByQueryParam(organizational, pageHelper);
			/*获得总数*/
			count = 
					this.organizationalService.getOrganizationalListCountByQureyParam(organizational, pageHelper);		
			returnStr = this.organizationalService.getJSONStrByListAndCount(orgList, count);
		}else{
			orgList = 
					this.organizationalService.getOrganizationalListByParentId(organizational.getId());
			returnStr = this.organizationalService.getJSONStrByList(orgList);
		}
		return returnStr;
	}
	
	/**
	 * 添加组织结构数据
	 * @param organizational 组织结构对象
	 * @return String
	 */
	@RequestMapping(value="/addOrganizational",method={RequestMethod.GET,RequestMethod.POST},produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public String addOrganizational(Organizational organizational){
		boolean flag = false;
		flag =	this.organizationalService.addOrganizational(organizational);
		JSONObject jsonObject = new JSONObject();
		if(flag){
			jsonObject.put("flag", true);
			jsonObject.put("info", "添加成功");
		}else{
			jsonObject.put("flag", false);
			jsonObject.put("info", "添加失败");
		}
		return jsonObject.toJSONString();
	}
	
	/**
	 * 修改组织结构
	 * @param orgainzational 组织结构对象
	 * @return String
	 */
	@RequestMapping(value="/updateOrganizational",method={RequestMethod.GET,RequestMethod.POST},produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public String updateOrganizational(Organizational orgainzational){
		boolean flag = false;
		flag = this.organizationalService.updateOrganizational(orgainzational);
		JSONObject jsonObject = new JSONObject();
		if(flag){
			jsonObject.put("flag", true);
			jsonObject.put("info", "修改成功");
		}else{
			jsonObject.put("flag", false);
			jsonObject.put("info", "修改失败");
		}
		return jsonObject.toJSONString();
	}
	
	/**
	 * 删除组织结构
	 * @param ids 组织结构id数组
	 * @return String
	 */
	@RequestMapping(value="/deleteOrganizational",method={RequestMethod.GET,RequestMethod.POST},produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public String deleteOrganizational(String [] ids){
		
		boolean flag = false;
		flag = this.organizationalService.deleteOrganizational(ids);
		JSONObject jsonObject = new JSONObject();
		if(flag){
			jsonObject.put("flag", true);
			jsonObject.put("info", "删除成功");
		}else{			
			jsonObject.put("flag", false);
			jsonObject.put("info", "删除失败");
		}
		return jsonObject.toJSONString();
	}
	
	@RequestMapping(value="/getOrgTreeDateByParetnId",method={RequestMethod.GET,RequestMethod.POST},produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public String getOrgTreeDateByParetnId(String id){
		List<Organizational> orgList = this.organizationalService.getOrganizationalListByParentId(id);
		JSONArray jsonArray = new JSONArray();
		for(int i=0;i<orgList.size();i++){
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", orgList.get(i).getId());
			jsonObject.put("text", orgList.get(i).getOrgName());
			jsonObject.put("iconCls", "icon-dept");
			jsonObject.put("state", "closed");
			jsonArray.add(jsonObject);
		}
		return jsonArray.toJSONString();
	}
	
	@RequestMapping(value="/getOrgTypeJSONStr",method={RequestMethod.GET,RequestMethod.POST},produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public String getOrgTypeJSONStr(){
		List<OrganizationalType> orgTypeList = this.organizationalService.getOrganizationalTypeListByQureyParam(null, new PageHelper());
		String returnStr = this.organizationalService.getOrganizationalTypeJSONStr(orgTypeList);
		return returnStr;
	}
}
