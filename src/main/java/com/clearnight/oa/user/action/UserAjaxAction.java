package com.clearnight.oa.user.action;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.clearnight.oa.base.bean.PageHelper;
import com.clearnight.oa.base.consts.BaseConsts;
import com.clearnight.oa.user.bean.UserBasic;
import com.clearnight.oa.user.service.IUserService;


@Controller
@RequestMapping("/userAjaxAction")
public class UserAjaxAction {

	/*------------------spring注入------------------------*/
	private IUserService userService;
	
	/*------------------公用常量---------------------------*/
	private HttpHeaders headers = new HttpHeaders();     
    private MediaType mediaType=new MediaType("text","html",Charset.forName("UTF-8"));
    private ResponseEntity<String> responseEntity;   
    /*------------------方法区----------------------------*/
    /**
     * 获得用户对象集合的json字符串，用于列表显示
     * @param user 用户对象
     * @param rows 每页一共有几行
     * @param page 第几页
     * @return ResponseEntity<String> 用户对象集合的json字符串 
     */
    @RequestMapping(value="/getUserPagenation",method=RequestMethod.POST)
    public ResponseEntity<String> getUserPagenation(UserBasic user,PageHelper pageHelper){
    	
    	headers.setContentType(mediaType);
    	/*根据参数获得用户对象集合*/
    	List<UserBasic> users = userService.getUsersPagenation(user,pageHelper);
    	JSONObject jsonObject = new JSONObject();
    	
    	JSONArray jsonArray = JSONArray.parseArray(JSONArray.toJSONStringWithDateFormat(users, BaseConsts.dateStringFormat));
    	jsonObject.put("rows", jsonArray.toArray());
    	jsonObject.put("total", userService.getUsersTotal(user,pageHelper));
    	
    	responseEntity = new ResponseEntity<String>(jsonObject.toJSONString(), headers, HttpStatus.OK);
    	return responseEntity;
    }
    
    @RequestMapping(value="/aMUser",method=RequestMethod.POST)
    public ResponseEntity<String> aMUser(UserBasic user){
    	
    	headers.setContentType(mediaType);
    	/*创建返回对象*/
    	JSONObject responseJson = new JSONObject();
    	//UserBasic user = new UserBasic();
    	if(user.getId().equals("")){/*如果用户ID等于null,说明为添加*/    
    		try {				
    			user.setId(UUID.randomUUID().toString());
    			userService.addUserBasic(user);
    			responseJson.put("status", true);
    			responseJson.put("info", "添加成功！");
			} catch (Exception e) {
    			responseJson.put("status", false);
    			responseJson.put("info", "添加失败！");
			}
    	}else{/*如果用户ID不等于null，说明为修改*/
    		try {				
    			userService.updateUserBasic(user);
    			responseJson.put("status", true);
    			responseJson.put("info", "修改成功！");
			} catch (Exception e) {
    			responseJson.put("status", true);
    			responseJson.put("info", "修改失败！");
			}
    	}
    	headers.setContentType(mediaType);	
    	responseEntity = new ResponseEntity<String>(responseJson.toJSONString(), headers, HttpStatus.OK);
    	return responseEntity;
    }
    
    @RequestMapping(value="/destoryUserByIds",method=RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String destoryUserByIds(String [] ids){
    	try{    		
    		userService.deleteUserBasic(ids);
    	}catch(Exception e){
    		return "{\"flag\":false}";
    	}
    	return "{\"flag\":true}";
    }
    
    @InitBinder
    protected void initBinder(HttpServletRequest request, 
    	    ServletRequestDataBinder binder) throws Exception { 
    	    DateFormat fmt = new SimpleDateFormat("yyyy-M-d"); 
    	    CustomDateEditor dateEditor = new CustomDateEditor(fmt, true); 
    	    binder.registerCustomEditor(Date.class, dateEditor); 
    	}
    
    /*------------------setter、getter方法区---------------*/
	public IUserService getUserService() {
		return userService;
	}
	@Autowired
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

    
    
    
}
