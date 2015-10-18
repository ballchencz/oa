<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>组织结构管理</title>
</head>
<body>
	<table id="orgTable" data-options="
				fit:true,
				border:false,
				rownumbers: true,
				animate: true,
				collapsible: true,
				fitColumns: true,
				url: '<%=path%>/organizationalAction/getOrganizationalPagenationAction.action',
				method: 'get',
				idField: 'id',
				treeField: 'orgName',
				pagination: true,
				pageSize: 15,
				pageList: [15,20,25,30,35,40],
				toolbar: [{
					iconCls: 'icon-add',
					text: '添加',
					handler: newOrg
				},'-',{
					iconCls: 'icon-edit',
					text: '修改',
					handler: editOrg
				},'-',{
					iconCls: 'icon-remove',
					text: '删除',
					handler: deleteOrg
				}],
				onBeforeLoad: function(row,param){
                    if (!row) {    
                        param.id = 0;
                    }
                },
                onLoadSuccess: function(row,data){
  					var roots = $('#orgTable').treegrid('getRoots');
  					console.info(roots);
  					$.each(roots,function(index,value){
  						console.info(value);
  					});
                }
			">
		<thead>
			<tr>
				<th data-options="field:'orgName',width:180">组织名称</th>
				<th data-options="field:'orgType',width:60,formatter:formatOrgType">组织类型</th>
				<th data-options="field:'addTime',width:80">添加时间</th>
			</tr>
		</thead>
	</table>
		<div id="amOrgFormDiv" class="easyui-dialog" data-options="
			closed:true,
			modal:true,
			buttons:[{
				text:'保存',
				handler:amOrg
			},{
				text:'关闭',
				handler:function(){
					$('#amOrgFormDiv').dialog('close');
				}
			}]" 
			style="width:400px;height:230px">
			<form id="orgManager_fm" class="easyui-form" data-options="novalidate:true" method="post">
				<table border="0">
					<tr>
						<td>组织名称：</td>
						<td>
							<input type="text" class="easyui-textbox" name="orgName"/>
							<input type="hidden" name="id"/>
						</td>
					</tr>
					<tr>
						<td>组织类型：</td>
						<td>
							<input class="easyui-combobox" id="orgType" name="orgType" data-options="
								valueField: 'typeValue',
								textField: 'typeName',
								url:'<%=path%>/organizationalAction/getOrgTypeJSONStr.action',
								with:50,
								panelHeight:'auto'
								" />
						</td>
					</tr>
					<tr id="orgDis">
						<td>上级组织：</td>
						<td>
							<input name="parentId" id="parentId" class="easyui-combotree" data-options="
									url:'<%=path%>/organizationalAction/getOrgTreeDateByParetnId.action',
									method:'get',
									panelHeight:'auto'
									" /> <a href="javascript:void(0)" onclick="changeParentIdIsNull()">置为空</a>
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
		var contextPath = '<%=path%>';
		$(function(){
			$('#orgTable').treegrid();
		});
		function formatOrgType(value){
			var result = [];
			var returnValue = "";
			$.ajax({
				url:contextPath+"/organizationalAction/getOrgTypeJSONStr.action",
				type:"get",
				dataType : "json",
				async: false,
				success : function(data){
					result = data;
				}
			});
			$.each(result,function(i,v){
				if(value==v.typeValue){
					returnValue = v.typeName;
					return false;
				}
			});
			return returnValue;
		}
		
		/*添加组织结构*/
		function newOrg(){
			var amOrgFormDiv = $("#amOrgFormDiv");
			amOrgFormDiv.dialog('open').dialog('setTitle','添加组织结构');
			$("#orgManager_fm").form("clear");
			$("#orgType").combobox("reload");
			$("#parentId").combotree("reload");
			url = '<%=path%>/organizationalAction/addOrganizational.action';	
		}
		
		/*修改组织结构*/
		function editOrg(){
			var row = $("#orgTable").treegrid("getSelected");
			if(row){				
				var amOrgFormDiv = $("#amOrgFormDiv");
				amOrgFormDiv.dialog('open').dialog('setTitle','修改组织结构');
				$("#orgManager_fm").form("clear");
				$("#orgManager_fm").form('load',row);
				$("#parentId").combotree("reload");
				url = '<%=path%>/organizationalAction/updateOrganizational.action';
			}else{
				$.messager.show({
            		title:'提示',
            		msg:'请选择要编辑的行'
            	});
			}
		}
		
		function deleteOrg(){
			var row = $("#orgTable").treegrid("getSelected");
			if (row){
                $.messager.confirm('提示信息','确认删除？',function(r){
                    if (r){
                    	var idArray = [];
                    	idArray.push(row.id);
                        $.post('<%=path%>/organizationalAction/deleteOrganizational.action',{ids:row.id},function(result){
                            if (result.flag){
                                $('#orgTable').treegrid('reload');    // reload the user data
                            } else {
                                $.messager.show({    // show error message
                                    title: '信息',
                                    msg: result.info
                                });
                            }
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
		
		
		function amOrg(){
			$("#orgManager_fm").form("submit",{
                url: url,
                onSubmit: function(){
                    return $(this).form('enableValidation').form('validate');
                },
                success:function(data){
                	data = $.parseJSON(data);
                	$.messager.show({
                		title:'提示',
                		msg:data.info
                	});
                	$('#amOrgFormDiv').dialog('close');
                	$('#orgTable').treegrid('reload');
                }
			});
		}
		
		/*当组织类型改变时*/
		function comboOnChange(newValue,oldValue){
			if(+newValue===1){
				$("#orgDis").removeAttr("style");
				$("#parentId").combobox("reload");
			}else{
				$("#orgDis").attr("style","display:none");
				$("#parentId").combobox("clear");
			}
		}
		
		function changeParentIdIsNull(){
			$("#parentId").combotree("setValue","");
		}
	</script>
</body>
</html>