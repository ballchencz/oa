package com.clearnight.oa.organizational.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.clearnight.oa.base.bean.PageHelper;
import com.clearnight.oa.organizational.bean.OrganizationalType;
import com.clearnight.oa.organizational.service.IOrganizationalService;

@Controller
@RequestMapping("/organizationalTypeAction")
public class OrganizationalTypeAction {
	
	@Autowired
	private IOrganizationalService organizationalService;
	
	/**
	 * 转向组织结构类型管理页面
	 * @return String
	 */
	@RequestMapping(value="/toOrgTypeManagerPage",method={RequestMethod.GET,RequestMethod.POST})
	public String toOrgTypeManagerPage(){
		return "/organizational/organizationalTypeManagerPage";
	}
	
	/**
	 * 获得组织类型表格数据
	 * @param orgType 组织类型对象
	 * @param pageHelper 分页参数对象
	 * @return String
	 */
	@RequestMapping(value="/getOrgTypeDateGridData",method={RequestMethod.GET,RequestMethod.POST},produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public String getOrgTypeDateGridData(OrganizationalType orgType,PageHelper pageHelper){
		
		/*根据分页参数查询出组织结构类型对象集合*/
		List<OrganizationalType> orgTypeList = 
				this.organizationalService.getOrganizationalTypeListByQureyParam(orgType, pageHelper);
		/*根据分页参数查询出组织结构类型总数*/
		Long count = this.organizationalService.getOrganizationalTypeCountByQueryParam(orgType);
		
		String returnStr = this.organizationalService.getOrganizationalTypeJSONStr(orgTypeList, count);
		return returnStr;
	}
	
	/**
	 * 添加组织类型
	 * @return String
	 */
	@RequestMapping(value="/addOrgType",method={RequestMethod.GET,RequestMethod.POST},produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public String addOrgType(OrganizationalType orgType){
		boolean flag = this.organizationalService.addOrganizationalType(orgType);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("flag", flag);
		if(flag){
			jsonObject.put("info", "添加成功");
		}else{
			jsonObject.put("info", "添加失败");
		}
		return jsonObject.toJSONString();
	}
	
	/**
	 * 修改组织类型
	 * @param orgType 组织类型对象
	 * @return String
	 */
	@RequestMapping(value="/updateOrgType",method={RequestMethod.GET,RequestMethod.POST},produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public String updateOrgType(OrganizationalType orgType){
		boolean flag = this.organizationalService.updateOrganizationaType(orgType);
		JSONObject jsonObject = new JSONObject();
		if(flag){
			jsonObject.put("info", "修改成功");
		}else{
			jsonObject.put("info", "修改失败");
		}
		return jsonObject.toJSONString();
	}
	
	/**
	 * 删除组织类型
	 * @param ids id数组
	 * @return String
	 */
	@RequestMapping(value="/deleteOrgType",method={RequestMethod.GET,RequestMethod.POST},produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public String deleteOrgType(String [] ids){
		boolean flag = this.organizationalService.deleteOrganizationaType(ids);
		JSONObject jsonObject = new JSONObject();
		if(flag){
			jsonObject.put("info", "删除成功");
		}else{
			jsonObject.put("info", "删除失败");
		}
		return jsonObject.toJSONString();
	}
	
}
