<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理</title>
</head>
<body>
    <script type="text/javascript">
    	var contextPath = '<%=path%>';
    </script>
    <link rel="stylesheet" href="<%=path%>/css/dialog.css"" type="text/css"></link>
    <link rel="stylesheet" href="<%=path%>/css/searchTable.css"" type="text/css"></link>
	<div class="easyui-layout" data-options="fit:true,border:false">
	    <div title="查询" data-options="region:'north',split:false,border:false,collapsed:false" style="height:130px;">
	    	<form id="userManagerPage_search_form" method="post" class="easyui-form">
	    		<table class="searchTable">
	    			<tr>
	    				<td rowspan="2"><img src="<%=path%>/images/search.jpg" width="50px" height="50px"></td>
	    				<td class="searchTdLabel">用户名：</td>
	    				<td><input name="userName" type="text" class="easyui-textbox"/></td>
	    				<td class="searchTdLabel">性别：</td>
	    				<td>
	            			<input class="easyui-combobox" name="userSex" data-options="
										valueField: 'value',
										textField: 'text',
										panelHeight: 'auto',
										url:'<%=path%>/data/sexWithPlase.json',
										with:100
										" style="width:100px;" />
	    				</td>
	    				<td class="searchTdLabel">民族：</td>
	    				<td>
	    					<input class="easyui-combobox" name="nation" data-options="
									valueField: 'text',
									textField: 'value',
									url:'<%=path%>/data/nationWithPlase.json',
									with:100
									" style="width:100px;" />	
	    				</td>
	    			</tr>
	    			<tr>
	    				<td class="searchTdLabel">出生日期：</td>
	    				<td><input name="birthday" type="text" class="easyui-datebox"/></td>
	    				<td class="searchTdLabel">年龄：</td>
	    				<td><input name="userAge" style="width: 100px;" type="text" class="easyui-numberspinner" data-options="min:0,max:100"></td>
	    				<td colspan="3" align="right"><a onclick="submitSearchForm()" class="easyui-linkbutton" style="width:80px;">查询</a></td>
	    			</tr>
	    		</table>

	    	</form>
	    </div>
		<div data-options="region:'center',border:false">
			<table class="easyui-datagrid" id="userManagerPage_table" data-options="
					fit:true,
					border:false,
					url:'<%=path%>/userAjaxAction/getUserPagenation.action',
					pagination: true,
					rownumbers:true,
			        pageSize: 15,
			        pageList: [15,30,45,60],
					fitColumns: true,
					idField: 'id',
					striped:true,
					onLoadSuccess:onloadSuccess,
					toolbar: [{
						iconCls: 'icon-add',
						text: '添加',
						handler: function(){
								addUser();
						}
					},'-',{
						iconCls: 'icon-edit',
						text: '修改',
						handler: function(){
								editUser();
						}
					},'-',{
						iconCls: 'icon-remove',
						text: '删除',
						handler: function(){
								destroyUser();
						}
					}],
					columns:[[
						 {field:'id',checkbox:true,width:20},
					     {field:'phoneForAccount',formatter:showImg,title:'头像',width:20},
					     {field:'userName',title:'用户名',width:50},
					     {field:'userSex',title:'性别',width:50,formatter:formatSex},
					     {field:'nation',title:'民族',formatter:formatNation,width:50},					     
					     {field:'birthday',title:'出生日期',width:50,sortable:'true'},					     
					     {field:'userAge',title:'年龄',width:50,sortable:'true'},
					     {field:'phone',title:'电话',width:50},
					     {field:'email',title:'电子邮箱',width:50}
					]]		
					"
			></table>
		</div>

    </div>
   	<div id="userManagerPage_AMDialog" class="easyui-dialog" style="width:600px;height:400px;padding:10px 20px;"
            data-options="
	           	closed:true,
	   			modal:true,
				buttons:[{
					iconCls: 'icon-add',
					text: '保存',
					handler: amUser
				},{
					iconCls: 'icon-remove',
					text: '取消',
					handler:function(){
						$('#userManagerPage_AMDialog').dialog('close');
					}
				}]
            ">
            <div class="ftitle">用户信息</div>
            <form id="userManagerPage_AMForm" method="post" class="easyui-form">
	            <table style="float: left;">
	            	<tr>
	            		<td><img style='width:60px;height:60px' src="<%=path%>/images/img8.jpg"></td>
	            	</tr>
	            </table>
	            <table style="padding-left:10px;">
	            	<tr>         		
	            		<td class="amTdLabel">用户名：</td>
	            		<td class="amTdContent">
	            			<input type="hidden" name="id"/>
	            			<input type="text" name="userName" style="width: 150px;" class="easyui-textbox" data-options="validType:'length[0,50]',novalidate:true,required:true,prompt:'用户名应小于50个字符',missingMessage:'用户名不能为空'"/>
	            		</td>
	            		<td class="amTdRight">性别：</td>
	            		<td class="amTdContent">
	            			<input class="easyui-combobox" name="userSex" id="userSex" data-options="
	            				        novalidate:true,
	            						required:true,prompt:'性别不能为空',
	            						missingMessage:'性别不能为空',
										valueField: 'value',
										textField: 'text',
										panelHeight: 'auto',
										url:'<%=path%>/data/sex.json',
										with:100
										" style="width:100px;" />
	            		</td>
	            	</tr>
	            	<tr>
	            		<td class="amTdLabel">出生日期：</td>
	            		<td class="amTdContent">
	            			<input name="birthday" style="width: 150px;" type="text" class="easyui-datebox"/>
	            		</td>
	            		<td class="amTdRight">民族：</td>
	            		<td class="amTdContent">
	            			<input class="easyui-combobox" name="nation" id="nation" data-options="
	            				        novalidate:true,
	            						required:true,prompt:'民族不能为空',
	            						missingMessage:'民族不能为空',
										valueField: 'text',
										textField: 'value',
										url:'<%=path%>/data/nation.json',
										with:100
										" style="width:100px;" />
	            		</td>
	            	</tr>
	            	<tr>
	            		<td class="amTdLabel">电话：</td>
	            		<td class="amTdContent">
	            			<input name="phone" style="width: 150px;" type="text" class="easyui-textbox">
	            		</td>
	            		<td class="amTdRight">年龄：</td>
	            		<td class="amTdContent">
	            			<input name="userAge" style="width: 100px;" type="text" class="easyui-numberspinner" data-options="min:0,max:100,novalidate:true">
	            		</td>
	            	</tr>
	
	            	<tr>
	            		<td class="amTdLabel">电子邮箱：</td>
	            		<td class="amTdContent">
	            			<input name="email" style="width: 150px;" type="text" class="easyui-textbox">
	            		</td>
	            		<td class="amTdRight">政治面貌：</td>
	            		<td class="amTdContent">
	            			<input class="easyui-combobox" name="politicalStatus" id="politicalStatus" data-options="
	            						novalidate:true,
	            						required:true,prompt:'政治面貌不能为空',
	            						missingMessage:'政治面貌不能为空',
										valueField: 'value',
										textField: 'text',
										url:'<%=path%>/data/politicalStatus.json',
										with:100
										" style="width:100px;" />
	            		</td>
	            	</tr>
	            	<tr>
	            		<td class="amTdLabel">身份证号：</td>
	            		<td class="amTdContent"><input type="text" style="width: 150px;" class="easyui-textbox" name="idNum"></td>
	            	</tr>
	            	<tr>
	            		<td class="amTdLabel">籍贯：</td>
	            		<td class="amTdContent" colspan="3"><input type="text"  data-options="validType:'length[0,200]',multiline:true,prompt:'籍贯应小于200个字符'" class="easyui-textbox" name="nativePlace" style="width:180px;height:50px"></td>
	            	</tr>
	            </table>
	        </form>
    </div>
	<script type="text/javascript">
	//drawTopBorderForDataGridTools($("#userManagerPage_table"));
	//console.info(panel);
		/**
		 *查询form的提交
		 */
		function submitSearchForm(){
			var value = serializeObject($('#userManagerPage_search_form'));
			$('#userManagerPage_table').datagrid('load',value);
		}

		function onloadSuccess(){
			drawTopBorderForDataGridTools($('#userManagerPage_table'));
		}
		
		/**
		 * 添加用户
		 */
		function addUser(){
			$("#userManagerPage_AMForm").form("clear");
			$("#userManagerPage_AMForm").form({
				novalidate:true
			});
			$('#userManagerPage_AMDialog').dialog('open').dialog('setTitle','添加用户');
			$("#userSex").combobox("reload");
			$("#nation").combobox("reload");
			$("#politicalStatus").combobox("reload");
		}
		
		function editUser(){
			/*获得dialog对象*/
			var userManagerAMDialog = $('#userManagerPage_AMDialog');
			var rows = $('#userManagerPage_table').datagrid('getSelected');
			if(rows){				
				//$('#userManagerPage_AMForm').form('clear');
				$('#userManagerPage_AMForm').form('load',rows);
				userManagerAMDialog.dialog('open').dialog('setTitle','修改用户');
			}
		}
		/**
		 * 显示头像
		 */
		function showImg(value,row,index){
			return "<img align='center' style='width:50px;height:50px' src="+contextPath+"/images/img8.jpg"+" />";
		};
		
		/**
	     *格式化性别，如果性别的值为1，则格式化为男，若为0，则格式化为女
		 */
		function formatSex(value,row,index){
			if(value==1){
				return '男';
			} else{
				return '女';
			}
		}

		
		/**
		 * 民族格式化
		 */
		function formatNation(value,row,index){
			var nationName = null;
			var result = null;
			$.ajax({
				url:contextPath+"/data/nation.json",
				type:"get",
				dataType : "json",
				async: false,
				success : function(data){
					result = data;
				}
			});
	
			$.each(result,function(index,val){
				if(value === +val.text){
					nationName = val.value;
					return false;
				}
			});
			return nationName;
		}
		
		/*添加和删除的方法*/
		function amUser(){
			$("#userManagerPage_AMForm").form("submit",{
	            url: contextPath+'/userAjaxAction/aMUser.action',
	            onSubmit: function(){
	                return $(this).form('enableValidation').form('validate');
	            },
	            success:function(data){
	            	data = $.parseJSON(data);
	            	$.messager.show({
	            		title:'提示',
	            		msg:data.info
	            	});
	            	$('#userManagerPage_AMDialog').dialog('close');
	            	$('#userManagerPage_table').datagrid('reload');
	            }
			});
		}
		/*
		 *	删除用户的方法
		*/
		function destroyUser(){
			var rows = $('#userManagerPage_table').datagrid('getSelections');
            if (rows.length>0){
            	var ids = [];
            	for(var i=0;i<rows.length;i++){
            		ids.push(rows[i].id);
            	}
                $.messager.confirm('提示信息','是否删除选中的行？',function(r){
                    if (r){
                         $.post('<%=path%>/userAjaxAction/destoryUserByIds.action',
                        	{"ids":""+ids+""},
                        	function(result){
                     		console.info(result);
                            if (result.flag){
                            	$('#userManagerPage_table').datagrid('reload');    // reload the user data
                            } else {
                                $.messager.show({    // show error message
                                    title: '信息',
                                    msg: '删除失败'
                                });
                            }
                        },'json');

                    }
                });
            }else{
                $.messager.show({    // show error message
                    title: '信息',
                    msg: '请选择要删除的行'
                });
            }
		}
	</script>
</body>
</html>