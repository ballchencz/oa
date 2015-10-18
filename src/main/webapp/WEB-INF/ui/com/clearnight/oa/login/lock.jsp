<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>CSS3 Digital Clock with jQuery</title>
<style type="text/css">
body{background:#FFFF;font:bold 12px Arial, Helvetica, sans-serif;margin:0;padding:0;color:#bbbbbb;}
a{text-decoration:none;color:#00c6ff;}

/* clock */
.clock{width:116px;height:31px;margin:0 auto;padding:2px;border:0px solid #333;color:#000;}
.clock #Date{width:150px;;font-family:'BebasNeueRegular', Arial, Helvetica, sans-serif;font-size:12px;text-shadow:0 0 1px #00c6ff;margin-bottom:0px;}
.clock ul{width:116px;margin:0 auto;padding:0px;list-style:none;text-align:center;}
.clock ul li{display:inline;font-size:12px;text-align:center;font-family:'BebasNeueRegular', Arial, Helvetica, sans-serif;text-shadow:0 0 1px #00c6ff;}
#point{position:relative;-moz-animation:mymove 1s ease infinite;-webkit-animation:mymove 1s ease infinite;padding:0px 5px;}
@-webkit-keyframes mymove{
	0%{opacity:1.0;text-shadow:0 0 20px #00c6ff;}
	50%{opacity:0;text-shadow:none;}
	100%{opacity:1.0;text-shadow:0 0 20px #00c6ff;}	
}


@-moz-keyframes mymove{
	0%{opacity:1.0;text-shadow:0 0 20px #00c6ff;}
	50%{opacity:0;text-shadow:none;}
	100%{opacity:1.0;text-shadow:0 0 20px #00c6ff;}	
}
</style>

<script type="text/javascript" src="<%=path%>/js/easyui1.4/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {

	var monthNames = [ "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" ]; 
	var dayNames= ["星期日","星期一","星期二","星期三","星期四","星期五","星期六"];

	// 创建一个对象newDate（）
	var newDate = new Date();
	// 提取当前的日期从日期对象
	newDate.setDate(newDate.getDate());
	//输出的日子，日期，月和年
	$('#Date').html(newDate.getFullYear() + "-" + monthNames[newDate.getMonth()]+ '-' + newDate.getDate() + ' ' + dayNames[newDate.getDay()]);

	setInterval( function() {
		// 创建一个对象，并提取newDate（）在访问者的当前时间的秒
		var seconds = new Date().getSeconds();
		//添加前导零秒值
		$("#sec").html(( seconds < 10 ? "0" : "" ) + seconds);
	},1000);
	
	setInterval( function() {
		// 创建一个对象，并提取newDate（）在访问者的当前时间的分钟
		var minutes = new Date().getMinutes();
		// 添加前导零的分钟值
		$("#min").html(( minutes < 10 ? "0" : "" ) + minutes);
    },1000);
	
	setInterval( function() {
		// 创建一个对象，并提取newDate（）在访问者的当前时间的小时
		var hours = new Date().getHours();
		// 添加前导零的小时值
		$("#hours").html(( hours < 10 ? "0" : "" ) + hours);
    }, 1000);
	
}); 
</script>
</head>
<body>


<div class="clock">

	<div id="Date"></div>
	
	<ul>
		<li id="hours"> </li>
		<li id="point">:</li>
		<li id="min"> </li>
		<li id="point">:</li>
		<li id="sec"> </li>
	</ul>

</div>

</body>
</html> 