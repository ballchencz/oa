<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
<link rel="stylesheet" href="<%=path%>/js/easyui1.4/themes/default/easyui.css" type="text/css"></link>
<link rel="stylesheet" href="<%=path%>/js/easyui1.4/themes/icon.css" type="text/css"></link>
<script type="text/javascript">
    var contextPath = '<%=path%>';
</script>
<title>登陆</title>
</head>
<body background="">

	<div id="temp" style="height: 500px">
		<div id="login_loginDialog" title="登陆">
			<form id="login_form" method="post">
				<div align="center" style="margin-top: 70px">
					<table>
						<tr>
							<td>用户名：</td>
							<td><input type="text" id="accountName" name="accountName" style="width:200px;height:30px" />
							</td>
						</tr>
						<tr>
							<td>密&nbsp;&nbsp;码：</td>
							<td><input type="password" id="accountPwd" name="accountPwd" style="width:200px;height:30px" />
							</td>
						</tr>
						<tr>
							<td align="left"><a id="login_form_loginBtn" onclick="loginBtnClick()" style="width:80px;">登陆</a>
							</td>
							<td align="center"><a id="login_form_resBtn" onClick="resBtnClick()" style="width:80px;">重置</a>
							</td>
						</tr>
					</table>
				</div>
			</form>
		</div>
	</div>
</body>
</html>
<script type="text/javascript" src="<%=path%>/js/easyui1.4/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/easyui1.4/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/easyui1.4/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=path%>/js/com/clearnight/oa/login/login.js"></script>
