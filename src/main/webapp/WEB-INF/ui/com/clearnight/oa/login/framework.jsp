<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>主页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="<%=path%>/js/easyui1.4/themes/default/easyui.css" type="text/css"></link>
	<link rel="stylesheet" href="<%=path%>/js/easyui1.4/themes/icon.css" type="text/css"></link>
	<script type="text/javascript" charset="UTF-8" src="<%=path%>/js/easyui1.4/jquery.min.js"></script>
	<script type="text/javascript" charset="UTF-8" src="<%=path%>/js/easyui1.4/jquery.easyui.min.js"></script>
	<script type="text/javascript" charset="UTF-8" src="<%=path%>/js/easyui1.4/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" charset="UTF-8" src="<%=path%>/js/com/clearnight/oa/login/login.js"></script>
	<script type="text/javascript" charset="UTF-8" src="<%=path%>/js/com/clearnight/oa/login/framework.js"></script>
	<script type="text/javascript">
		var contextPath = '<%=path%>';
		var accountName = '${sessionScope.loginInfo}';
		console.info(accountName);
		if(!accountName){
			window.location = contextPath+"/loginAction/toLoginPage.action";
		}
	</script>
  </head>
  
  <body id="framework_layout" class="easyui-layout">
	    <div id="framework_northPanel" data-options="region:'north',split:false,border:true" style="height:90px;">
	    	<iframe frameborder="0" src="<%=path%>/loginAction/toLockPage.action" height="40px" width="200px" style="position: absolute;margin-left: 1100px;"></iframe>
	    	<h1 style="margin-left: 10px; margin-top: 10px;">jEasyUI Extensions</h1>
	    </div>
	    <div id="framework_westPanel" data-options="href:'<%=path%>/menuAction/toLeftMenuPage.action',region:'west',split:true,border:true" style="width:200px;"></div>
	    <div id="framework_centerPanel" data-options="region:'center',border:true,split:true" >

	    	<div id="framework_tabs" class="easyui-tabs" data-options="fit:true,border:false" style="width:500px;height:250px;">
    			<div title="主页"  style="padding:20px;display:none;">
        			tab1
    			</div>
			</div>
			 
	    </div>
  </body>
</html>
