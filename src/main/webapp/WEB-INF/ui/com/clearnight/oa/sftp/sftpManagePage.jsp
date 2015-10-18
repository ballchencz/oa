<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>sftp文件服务器管理</title>
</head>
<body>
		<table id="sftpManageTable" class="easyui-datagrid" data-options="				
				fit:true,
				border:false,
				nowrap:false,
				rownumbers: true,
				collapsible: true,
				fitColumns: true,
				striped:true,
				url: '<%=path%>/sftpAction/getSftpManagePageData.action',
				method: 'get',
				pagination: true,
				pageSize: 15,
				pageList: [15,20,25,30,35,40],
				toolbar: [{
					iconCls: 'icon-add',
					text: '添加',
					handler: newSftp
				},'-',{
					iconCls: 'icon-edit',
					text: '修改',
					handler: editSftp
				},'-',{
					iconCls: 'icon-remove',
					text: '删除',
					handler: function(){
					}
				}]">
		<thead>
			<tr>
				<th data-options="field:'id',checkbox:true"></th>
				<th data-options="field:'sftpName',width:50">服务器名称</th>
				<th data-options="field:'host',width:50">服务器地址</th>
				<th data-options="field:'port',width:50">端口号</th>
				<th data-options="field:'status',width:50,formatter:formatStatus">状态</th>
				<th data-options="field:'operation',width:50,formatter:formatOperation">操作</th>				
			</tr>
		</thead>
	</table>
	<div id="sftpManageDiv" class="easyui-dialog" data-options="
				closed:true,
				modal:true,
				buttons:[{
					text:'保存',
					handler:amSftp
				},{
					text:'关闭',
					handler:function(){
						$('#sftpManageDiv').dialog('close');
					}
				}]
		" style="width:500px;height:300px">
			<form id="sftpManage_fm" class="easyui-form" data-options="novalidate:true" method="post">
				<table border="0">
					<tr>
						<td>服务器名称：</td>
						<td>
							<input type="text" class="easyui-textbox" data-options="required:true,missingMessage:'服务器名称不能为空'" name="sftpName"/>
							<input type="hidden" name="id"/>
						</td>
					</tr>
					<tr>
						<td>用户名：</td>
						<td>
							<input type="text" class="easyui-textbox" data-options="required:true,missingMessage:'用户名不能为空'" name="userName"/>
						</td>
					</tr>
					<tr>
						<td>密码：</td>
						<td>
							<input type="password" class="easyui-textbox" data-options="required:true,missingMessage:'密码不能为空'" name="password"/>
						</td>
					</tr>
					<tr>
						<td>IP地址：</td>
						<td>
							<input type="text" class="easyui-textbox" data-options="required:true,missingMessage:'IP地址不能为空'" name="host"/>
						</td>
					</tr>
					<tr>
						<td>端口号：</td>
						<td>
							<input type="text" class="easyui-numberspinner" data-options="required:true,missingMessage:'端口号不能为空'" name="port"/>
						</td>
					</tr>
					<tr>
						<td>状态：</td>
						
						<td>
							<input id="statusSelect" class="easyui-combobox" name="status" data-options="
									valueField: 'value',
									textField: 'label',
									panelHeight: 'auto',
									editable:false,
									data: [{
										label: '开启',
										value: '0'						
									},{
										label: '关闭',
										value: '1',
										selected:true
									}]" />
						</td>
					</tr>
					<tr>
						<td>备注：</td>
						<td>
							<input name="mark" data-options="validType:'length[0,2000]',multiline:true,prompt:'备注内容应小于2000个字符'" class="easyui-textbox"  style="width:200px;height:50px"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
	<script type="text/javascript">
		var contextPath = '<%=path%>';
		var url;
		function newSftp(){
			$("#sftpManage_fm").form("clear");
			$("#sftpManage_fm").form({
				novalidate:true
			});
			$("#statusSelect").combobox("select","1");
			$('#sftpManageDiv').dialog('open').dialog('setTitle','添加服务器');
			url = contextPath+"/sftpAction/addSftp.action";
		}
		
		function editSftp(){
			var row = $("#sftpManageTable").datagrid("getSelected");
			if(row){
				$("#sftpManage_fm").form("clear");
				$("#sftpManage_fm").form({
					novalidate:true
				});
				$("#sftpManage_fm").form('load',row);
				$('#sftpManageDiv').dialog('open').dialog('setTitle','修改服务器');
				url = contextPath+'/sftpAction/updateSftp.action';
			}else{
				$.messager.show({
            		title:'提示',
            		msg:'请选择要修改的行'
            	});
				url = "";
			}
		}
		
		function amSftp(){
			$("#sftpManage_fm").form("submit",{
                url: url,
                onSubmit: function(){
                    return $(this).form('enableValidation').form('validate');
                },
                success:function(data){
                	data = $.parseJSON(data);
                	console.info(data);
                 	$.messager.show({
                		title:'提示',
                		msg:data.info
                	}); 
                 	if(data.flag){
	                	$('#sftpManageDiv').dialog('close');                 		
                		$('#sftpManageTable').datagrid('reload');
                 	}
                }
			});
		}
		
		function formatStatus(value,row,index){
			if(+value===0){
				return "<font color=\"green\">开启</font>";
			}else if(+value===1){
				return "<font color=\"red\">关闭</font>";
			}else{
				return value;
			}
		}
		
		function formatOperation(value,row,index){
			return "<a href=\"javascript:void(0)\" onclick=\"changeSftpServerStatus('"+row.id+"',0)\">开启</a>"+
				   "&nbsp;&nbsp;&nbsp;&nbsp;"+
				   "<a href=\"javascript:void(0)\" onclick=\"changeSftpServerStatus('"+row.id+"',1)\">关闭</a>";
		}
		
		function changeSftpServerStatus(rowId,status){
				 $.ajax({
					   type: "POST",
					   url: contextPath+"/sftpAction/updataSftpStatus.action",
					   data: "id="+rowId+"&status="+status,
					   success: function(data){
		                 $.messager.show({title: '信息',msg: data.info});
					     $('#sftpManageTable').datagrid('reload');
					   }

				}); 
			
		}
		
	</script>
</body>
</html>