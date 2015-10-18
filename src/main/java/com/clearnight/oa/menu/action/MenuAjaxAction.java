package com.clearnight.oa.menu.action;

import org.apache.log4j.Logger;

import java.nio.charset.Charset;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.clearnight.oa.menu.bean.MenuInfo;
import com.clearnight.oa.menu.consts.MenuConsts;
import com.clearnight.oa.menu.service.IMenuService;

@Controller
@RequestMapping("/menuAjaxAction")
public class MenuAjaxAction {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(MenuAjaxAction.class);

	/*------------------spring注入------------------------*/
	private IMenuService menuService;
	
	/*------------------公用常量---------------------------*/
	private HttpHeaders headers = new HttpHeaders();     
    private MediaType mediaType=new MediaType("text","html",Charset.forName("UTF-8"));
    private ResponseEntity<String> responseEntity;   
    /*------------------方法区----------------------------*/
    

    /**
     * 获得左侧菜单
     * @param menuInfo 菜单对象
     * @return ResponseEntity<String> 菜单对象的json字符串
     */
	@RequestMapping(value="/getTreeData",method=RequestMethod.POST)
	public ResponseEntity<String> getTreeData(MenuInfo menuInfo){
		if(menuInfo.getId()==null){
			menuInfo.setId(menuInfo.getParentId());
		}
		headers.setContentType(mediaType);
		List<MenuInfo> menuInfoList = this.menuService.getMenuInfoListByfid(menuInfo.getId());
		JSONArray jsonArray = new JSONArray();
		for(MenuInfo menu : menuInfoList){			
			JSONObject jsonObject = new JSONObject();
			JSONObject attributesJson = new JSONObject();
			jsonObject.put("id", menu.getId());
			jsonObject.put("text", menu.getMenuName());
			attributesJson.put("url", menu.getMenuUrl());
			attributesJson.put("id", menu.getId());
			jsonObject.put("attributes", attributesJson);
			jsonObject.put("dnd", true);
			jsonObject.put("state", "closed");
			jsonArray.add(jsonObject);
		}
		logger.info(jsonArray.toString());
		responseEntity = new ResponseEntity<String>(jsonArray.toString(),headers,HttpStatus.OK);
		return responseEntity;
	}
	
	/**
	 *获得整个菜单的树结构数据
	 * @return ResponseEntity<String> 菜单树结构的json字符串
	 */
	@RequestMapping(value="/getAllTreeData",method=RequestMethod.POST)
	public ResponseEntity<String> getAllTreeDate(){
		headers.setContentType(mediaType);
		JSONArray jsonArray = new JSONArray();
		List<MenuInfo> menuInfoList = this.menuService.getAllMenuInfo();
		for(MenuInfo menuInfo : menuInfoList){
			JSONObject jsonObject = new JSONObject();
			JSONObject attributesJson = new JSONObject();
			jsonObject.put("id", menuInfo.getId());
			jsonObject.put("text", menuInfo.getMenuName());
			attributesJson.put("url", menuInfo.getMenuUrl());
			attributesJson.put("parentId", menuInfo.getParentId());
			jsonObject.put("attributes", attributesJson);
			jsonObject.put("dnd", true);
			jsonObject.put("state", "closed");
			jsonArray.add(jsonObject);
		}
		
		responseEntity = new ResponseEntity<String>(jsonArray.toString(),headers,HttpStatus.OK);
		return responseEntity;
	}
	
	/**
	 * 获得所哟菜单的json字符串
	 * @param menuInfo 菜单对象
	 * @return ResponseEntity<String> 所有菜单对象字符串集合
	 */
	@RequestMapping(value="/getMenuTreeGridData",method=RequestMethod.POST)
	public ResponseEntity<String> getMenuTreeGridData(MenuInfo menuInfo,int rows,int page){
		
		logger.info("menuInfo::::::::::::::::::::::::::::::"+JSON.toJSONString(menuInfo));
		headers.setContentType(mediaType);
		List<MenuInfo> menuInfoList;
		String returnJson = null;
		menuInfoList = this.menuService.getMenuInfoListPagenation(menuInfo.getId(), rows, page);
		if("0".equals(menuInfo.getId())){			
			returnJson = this.menuService.getRootMenuJSON(menuInfoList);
		}			
		else{
			returnJson = this.menuService.getChildMenuJSON(menuInfoList);
		}
		
		logger.info("id::::::::::::::::::::::::::"+menuInfo.getId());
		logger.info("returnJson::::::::::::::::::"+returnJson);
		responseEntity = new ResponseEntity<String>(returnJson,headers,HttpStatus.OK);
		return responseEntity;
	}
	
	/**
	 * 菜单的添加和修改
	 * @param menuInfo
	 * @return ResponseEntity<String> 添加或者修改的后的JSON字符串
	 */
	@RequestMapping(value="/amMenuInfo",method=RequestMethod.POST)
	public ResponseEntity<String> amMenuInfo(MenuInfo menuInfo){
		JSONObject jsonObject = new JSONObject();
		/*如果id为空，说明为添加，否则，说明为修改*/
		if(null==menuInfo.getId()){
			menuInfo.setId(UUID.randomUUID().toString());
			try{
				this.menuService.addMenu(menuInfo);
				jsonObject.put("flag", true);
			}catch(Exception e){
				jsonObject.put("flag", false);
			}
		}else{
			try{	
				if(!menuInfo.getId().equals(menuInfo.getParentId())){					
					this.menuService.updateMenu(menuInfo);
				}
				jsonObject.put("flag", true);
			}catch(Exception e){
				jsonObject.put("flag", false);
			}
		}
		responseEntity = new ResponseEntity<String>(jsonObject.toString(),headers,HttpStatus.OK);
		return responseEntity;
	}
	
	@RequestMapping(value="/deleteMenuInfoById",method=RequestMethod.POST)
	public ResponseEntity<String> deleteMenuInfoById(String id){
		headers.setContentType(mediaType);
		JSONObject jsonObject = new JSONObject();
		MenuInfo menuInfo = this.menuService.getMenuInfoById(id);
		/*当id等于菜单维护的id，不能删除*/
		if(menuInfo.getMenuUrl()!=null){			
			if(!menuInfo.getMenuUrl().equals(MenuConsts.MENUMANAGERURL)){	
				try{				
					this.menuService.deleteMenuById(id);
					jsonObject.put("flag", true);
				}catch(Exception e){				
					jsonObject.put("flag", false);
				}
			}
		}else{
			try{				
				this.menuService.deleteMenuById(id);
				jsonObject.put("flag", true);
			}catch(Exception e){				
				jsonObject.put("flag", false);
			}
		}
		
		responseEntity = new ResponseEntity<String>(jsonObject.toString(),headers,HttpStatus.OK);
		return responseEntity;
	}
	
	/*------------------setter、getter方法-----------------*/
	public IMenuService getMenuService() {
		return menuService;
	}
	@Autowired
	public void setMenuService(IMenuService menuService) {
		this.menuService = menuService;
	}
	
	
}
