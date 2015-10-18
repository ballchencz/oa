<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件类型管理</title>
</head>
<body>
<link rel="stylesheet" href="<%=path%>/css/dialog.css"" type="text/css"></link>
<link rel="stylesheet" href="<%=path%>/css/table.css"" type="text/css"></link>
			<table class="easyui-datagrid" id="fileTypeManagePage_table" data-options="				
				fit:true,
				border:false,
				nowrap:false,
				rownumbers: true,
				collapsible: true,
				fitColumns: true,
				url: '<%=path%>/fileTypeManageAction/getFileTypeDataGirdJSON.action',
				method: 'get',
				striped:true,
				pagination: true,
				pageSize: 15,
				pageList: [15,20,25,30,35,40],
				toolbar: [{
					iconCls: 'icon-save',
					text: '添加',
					handler:fileTypeManage.newFileType
				},'-',{
					iconCls: 'icon-edit',
					text: '修改',
					handler:fileTypeManage.editFileType
				},'-',{
					iconCls: 'icon-remove',
					text: '删除',
					handler:fileTypeManage.removeFileType
				}]
		">
		<thead>
			<tr>
				<th data-options="field:'id',checkbox:true"></th>
				<th data-options="field:'imgURL',width:5,formatter:fileTypeManage.formatImgURL"></th>
				<th data-options="field:'fileTypeName',width:50">文件类型名称</th>
				<th data-options="field:'imgName',width:50">图片名称</th>
				<th data-options="field:'typeCode',width:50">文件类型代码</th>
			</tr>
		</thead>
		</table>
   	<div id="fileTypeManage_amDialog" class="easyui-dialog" style="width:600px;height:400px;padding:10px 20px;"
            data-options="
	           	closed:true,
	   			modal:true,
				buttons:[{
					iconCls: 'icon-add',
					text: '保存',
					handler: fileTypeManage.amFileType
				},{
					iconCls: 'icon-remove',
					text: '取消',
					handler:function(){
						$('#fileTypeManage_amDialog').dialog('close');
					}
				}]
            ">
           <div class="ftitle">文件类型</div>
           <form id="fileTypeManage_AMForm" method="post" class="easyui-form" enctype="multipart/form-data">
           		<table class="amTable">
           			<tr>
           				<td>文件类型名称：</td>
           				<td>
           					<input type="hidden" id="id" name="id"/>
           					<input type="hidden" id="imgURL" name="imgURL"/>
           					<input type="hidden" id="imgSize" name="imgSize"/>
           					<input type="hidden" id="imgName" name="imgName"/>
           					<input type="text" id="fileTypeName" name="fileTypeName" style="width: 150px;" class="easyui-textbox" data-options="validType:'length[0,50]',novalidate:true,required:true,prompt:'文件类型名称应小于50个字符',missingMessage:'文件类型名称不能为空'"/>
           				</td>

           			</tr>
           			<tr>
           				<td class="rightTd">文件类型代码：</td>
           				<td class="rightTd">
           					<input type="text" id="typeCode" name="typeCode" style="width: 150px;" class="easyui-textbox" data-options="validType:'length[0,50]',novalidate:true,required:true,prompt:'文件类型代码应小于50个字符',missingMessage:'文件类型名称不能为空'"/>
           				</td>
           			</tr>
           			<tr>
           				<td class="rightTd">文件类型图片：</td>
           				<td class="rightTd">
           					<input id="fileTypeImg" id="fileTypeImg" name="fileTypeImg" class="easyui-filebox" data-options="
           					buttonText:'选择文件',
           					onChange:function(newValue, oldValue){
           						//预览代码，支持 IE6、IE7。
							    var newPreview = document.getElementById('preview1');
							  	var imgFile = document.getElementsByName('fileTypeImg')[0];
							    var t ;
							    if(document.all){//IE
									t = imgFile.value;
								} else {
									var t = window.URL.createObjectURL( imgFile.files.item(0));
									//t =.getAsDataURL();
				
									newPreview.style.backgroundImage = 'url(' + t + ')';
									newPreview.style.width = '16px';
									newPreview.style.height = '16px';
									newPreview.style.display = 'block';
								}
           					}
           					" style="width:200px" data-options="prompt:'选择文件'">
           				</td>
           				<td class="rightTd">
           					<div id="preview1" style="border:1px solid #FFCC00; width:16px; height:16px;display:block" ></div>
           				</td>
           			</tr>
           			<tr>
           				<td valign="top" align="right" class="rightTd">备注：</td>
           				<td colspan="2" class="rightTd">
           					 <input id="mark" name="mark" data-options="validType:'length[0,500]',multiline:true,prompt:'备注内容应小于500个字符'" class="easyui-textbox"  style="width:300px;height:100px"/>
           				</td>
           			</tr>
           		</table>
           </form>
     </div>
		<script type="text/javascript">
			var fileTypeManage = {};
			var contextPath = '<%=path%>';
			fileTypeManage.amFileType = function(){
				$("#fileTypeManage_AMForm").form("submit",{
		            url: contextPath+'/fileTypeManageAction/amFileType.action',
		            onSubmit: function(){
		                return $(this).form('enableValidation').form('validate');
		            },
		            success:function(data){
		            	data = $.parseJSON(data);
		            	$.messager.show({
		            		title:'提示',
		            		msg:data.info
		            	});
		            	$('#fileTypeManage_amDialog').dialog('close');
		            	$('#fileTypeManagePage_table').datagrid('reload');
		            }
				});
			}
			/*保存*/
			fileTypeManage.newFileType = function(){
				$("#fileTypeName").textbox("clear");
				$("#typeCode").textbox("clear");
				$("#mark").textbox("clear");
				$("#fileTypeImg").textbox("clear");
				$("#id").val("");
				$("#imgURL").val("");
				$("#imgSize").val("");
				$("#fileTypeManage_AMForm").form({
					novalidate:true
				});
				$("#fileTypeManage_amDialog").dialog('open').dialog('setTitle','添加文件类型');
				
			}
			/*修改*/
			fileTypeManage.editFileType = function(){
				var fileTypeManageAMDialog = $('#fileTypeManage_amDialog');
				$("#fileTypeName").textbox("clear");
				$("#typeCode").textbox("clear");
				$("#mark").textbox("clear");
				$("#fileTypeImg").textbox("clear");
				$("#fileTypeManage_AMForm").form({
					novalidate:true
				});
				var rows = $('#fileTypeManagePage_table').datagrid('getSelected');
				if(rows){				
					fileTypeManageAMDialog.dialog('open').dialog('setTitle','修改文件类型');
					console.info(rows);
					$('#fileTypeManage_AMForm').form('load',rows);
				} 
			}
			/*删除*/
			fileTypeManage.removeFileType = function(){
				var rows = $('#fileTypeManagePage_table').datagrid('getSelections');
	            if (rows.length>0){
	            	var ids = [];
	            	for(var i=0;i<rows.length;i++){
	            		ids.push(rows[i].id);
	            	}
	                $.messager.confirm('提示信息','是否删除选中的行？',function(r){
	                    if (r){
	                         $.post(contextPath+'/fileTypeManageAction/deleteFileType.action',
	                        	{"ids":""+ids+""},
	                        	function(result){
		                            if (result.flag){
		                            	$('#fileTypeManagePage_table').datagrid('reload');    // reload the user data
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
			fileTypeManage.formatImgURL = function(value,row,indexs){
				var data = {"filePath":value};
				var imgSrc = "<%=path%>/fileTypeManageAction/getImg.action?filePath="+value;
				var img = "<img src='"+imgSrc+"'/>";
				return img;
			}
		</script>
</body>
</html>