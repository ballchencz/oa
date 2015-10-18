<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>组织结构类型管理</title>
</head>
<body>
		<table id="orgTypeTable" data-options="				
				fit:true,
				border:false,
				nowrap:false,
				rownumbers: true,
				collapsible: true,
				fitColumns: true,
				url: '<%=path%>/organizationalTypeAction/getOrgTypeDateGridData.action',
				method: 'get',
				pagination: true,
				pageSize: 15,
				pageList: [15,20,25,30,35,40],
				toolbar: [{
					iconCls: 'icon-add',
					text: '添加',
					handler: newOrgType
				},'-',{
					iconCls: 'icon-edit',
					text: '修改',
					handler: editOrgType
				},'-',{
					iconCls: 'icon-remove',
					text: '删除',
					handler: deleteOrgType
				}]">
		<thead>
			<tr>
				<th data-options="field:'id',checkbox:true"></th>
				<th data-options="field:'typeName',width:50">组织类型名称</th>
				<th data-options="field:'typeValue',width:50">组织类型值</th>
				<th data-options="field:'mark',width:50">备注</th>
			</tr>
		</thead>
	</table>
		<div id="amOrgTypeFormDiv" class="easyui-dialog" data-options="
				closed:true,
				modal:true,
				buttons:[{
					text:'保存',
					handler:amOrgType
				},{
					text:'关闭',
					handler:function(){
						$('#amOrgTypeFormDiv').dialog('close');
					}
				}]
		" style="width:400px;height:230px">
			<form id="orgTypeManager_fm" class="easyui-form" data-options="novalidate:true" method="post">
				<table border="0">
					<tr>
						<td>组织类型名称：</td>
						<td>
							<input type="text" class="easyui-textbox" name="typeName"/>
							<input type="hidden" name="id"/>
						</td>
					</tr>
					<tr>
						<td>组织类型值：</td>
						<td>
							<input type="text" class="easyui-textbox" name="typeValue"/>
						</td>
					</tr>
					<tr>
						<td>备注：</td>
						<td>
							<input name="mark" data-options="validType:'length[0,500]',multiline:true,prompt:'备注内容应小于500个字符'" class="easyui-textbox"  style="width:200px;height:50px"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
	<script type="text/javascript">
		var url = "";
		$(function(){
			$("#orgTypeTable").datagrid();
		});
		
		function newOrgType(){
			var amOrgTypeFormDiv = $("#amOrgTypeFormDiv");
			amOrgTypeFormDiv.dialog('open').dialog('setTitle','添加组织类型');
			$("#orgTypeManager_fm").form("clear");
			url = '<%=path%>/organizationalTypeAction/addOrgType.action';
		}
		
		function editOrgType(){
			var row = $("#orgTypeTable").datagrid("getSelected");
			if(row){				
				var amOrgTypeFormDiv = $("#amOrgTypeFormDiv");
				amOrgTypeFormDiv.dialog('open').dialog('setTitle','修改组织类型');
				$("#orgTypeManager_fm").form("clear");
				$("#orgTypeManager_fm").form('load',row);
				url = '<%=path%>/organizationalTypeAction/updateOrgType.action';
			}else{
				$.messager.show({
            		title:'提示',
            		msg:'请选择要编辑的行'
            	});
				url = "";
			}
		}
		
		function deleteOrgType(){
			var rows = $("#orgTypeTable").treegrid("getChecked");
			if (rows.length>0){
                $.messager.confirm('提示信息','确认删除？',function(r){
                    if (r){
                    	var idArray = "";
                    	$.each(rows,function(index,value){
                    		idArray += value.id+((index+1)==rows.length?"":",");
                    	});
                        $.post('<%=path%>/organizationalTypeAction/deleteOrgType.action',{ids:idArray},function(result){
                            $.messager.show({
                                title: '信息',
                                msg: result.info
                            });
                            $('#orgTypeTable').datagrid('reload');                           
                        },'json');
                    }
                });
            }else{
            	$.messager.show({
            		title:'提示',
            		msg:'请选择要删除的行'
            	});
            }
		}
		
		function amOrgType(){
			$("#orgTypeManager_fm").form("submit",{
                url: url,
                onSubmit: function(){
                    return $(this).form('enableValidation').form('validate');
                },
                success:function(data){
                	data = $.parseJSON(data);
                	//console.info(data);
                 	$.messager.show({
                		title:'提示',
                		msg:data.info
                	}); 
                	$('#amOrgTypeFormDiv').dialog('close');
                	$('#orgTypeTable').datagrid('reload');
                }
			});
		}
	</script>
</body>
</html>