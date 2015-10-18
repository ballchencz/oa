<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <body>
    <ul id="leftMenu_tree${requestScope.menuInfo.id}" class="easyui-tree" data-options="
    									fit:true,
    									border:false,
					        		 	queryParams:{parentId:'${requestScope.menuInfo.id}'},
					        		 	onClick : function(node){
					        		 		var url = node.attributes.url;
					        		 		var tabsObject = $('#framework_tabs');
					        		 		if(url){
					    						if(tabsObject.tabs('exists',node.text)){
					    							tabsObject.tabs('select',node.text);
					    						}else{
					    						
						        		 			$('#framework_tabs').tabs('add',{
						        		 				title : node.text,
						        		 				selected: true,
						        		 				border:false,
						        		 				closable:true,
						        		 				href:'<%=path%>'+url,
						        		 				tools:[{
		        											iconCls:'icon-mini-refresh',
		        											handler:function(){
		            											var tab = $('#framework_tabs').tabs('getSelected');
		            											tab.panel('refresh','<%=path%>'+url);
		        											}
		    											}]
						        		 			});
					        		 			}
					        		 		}
					        		 		
					        		 	},
					        		 	url:'<%=path%>/menuAjaxAction/getTreeData.action'">
	</ul>
  </body>
</html>
