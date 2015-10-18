<%@page import="java.util.Date"%>
<%@page import="com.clearnight.oa.menu.bean.MenuInfo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	List<MenuInfo> menuInfoList = (List<MenuInfo>)request.getAttribute("menuInfoList");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>左侧菜单</title>
</head>
<body>
<script type="text/javascript" src="<%=path%>/js/com/clearnight/oa/leftMenu/leftMenu.js"></script>
			<div class="easyui-layout" data-options="fit:true,border:false">	
			    <div data-options="region:'north',title:'日历',split:false,border:false" style="height:200px;">
			    	<div class="easyui-calendar" data-options="fit:true,border:false" style="width:180px;height:180px;"></div>
			    </div>
			    <div data-options="region:'center',title:'菜单',border:false">
			    	
				    	<div id="leftMenu_accordion" class="easyui-accordion" data-options="border:false,selected:<%=menuInfoList.size()-1%>,title:'菜单',fit:true,height:600">
							
							<c:forEach items="${requestScope.menuInfoList}" var="menuInfo">
				        		<div title="${menuInfo.menuName}" data-options="tools:[{
					                    iconCls:'icon-reload',
					                    handler:function(){
					                    
					                        var a = $('#leftMenu_accordion').accordion('getSelected');
					                    	if(a){
						                    	a.panel('refresh','<%=path%>/menuAction/toMenuTreePage.action?id=${menuInfo.id}');				                    	
					                    	}
					                    }
					                }],border:false" style="overflow:auto;padding:10px;">
					                
					 				<div id="leftMenu_panel${menuInfo.id}" class="easyui-panel" data-options="border:false,href:'<%=path%>/menuAction/toMenuTreePage.action?id=${menuInfo.id}'">
					        		</div>
				        		</div>
				       
							</c:forEach>
					   	</div> 
				   	</div>
			    
			 </div>

</body>
</html>