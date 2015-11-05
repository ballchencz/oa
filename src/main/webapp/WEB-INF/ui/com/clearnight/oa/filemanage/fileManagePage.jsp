<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件管理</title>
</head>
<body>
		<table class="easyui-datagrid" id="fileManagePageTable" data-options="				
				fit:true,
				border:false,
				nowrap:false,
				rownumbers: true,
				collapsible: true,
				fitColumns: true,
				url: '<%=path%>/fileManageAction/getDataGridJSONString.action?fileId=${fileId}',
				method: 'get',
				striped:true,
				onAfterEdit:dataGridOnAfterEdit,
				toolbar: [{
					iconCls: 'icon-update',
					text: '上传文件',
					handler: uploadFile
				},'-',{
					iconCls: 'icon-folder-add',
					text: '新建文件夹',
					handler: newFolder
				},'-',{
					iconCls: 'icon-save',
					text: '保存',
					handler:saveFolder
				},'-',{
					iconCls: 'icon-undo',
					text: '取消',
					handler: cancleEdit
				},'-',{
					iconCls: 'icon-back',
					text: '返回上一层',
					handler: backParentPage
				},'-',{
					iconCls: 'icon-remove',
					text: '删除',
					handler: deleteDirOrFile
				}]
		">
		<thead>
			<tr>
				<th data-options="field:'id',checkbox:true"></th>
				<th data-options="
					field:'fileName',
					width:50,
					formatter:formatFileName,
					editor:{
							type:'textbox',
							options:{
								prompt:'请输入文件夹名称',
								required:true,
								missingMessage:'文件夹名称不能为空'
							}
						}
					">名称</th>
				<th data-options="field:'lastModifyTime',width:50,styler:formatRowStyle">修改日期</th>
				<th data-options="field:'fileType',formatter:formatFileType,width:50,styler:formatRowStyle">类型</th>
				<th data-options="field:'fileSize',formatter:formatFileSize,width:50,styler:formatRowStyle">大小</th>
			</tr>
		</thead>
		</table>

 	    <div id="filemanager_upload" class="easyui-dialog" title="文件上传" style="width:600px;height:400px;"
	            data-options="
	                iconCls: 'icon-save',
	                closed:true,
	                modal:true,
	                toolbar: '#dlg-toolbar',
                	buttons: '#dlg-buttons',
                	onClose:function(){
                		var rows = $('#file_content_table').datagrid('getRows');
						if(rows.length){
							$.each(rows,function(index,value){
								var valueIndex = $('#file_content_table').datagrid('getRowIndex',value);
								$('#file_content_table').datagrid('deleteRow',valueIndex);
							});
						}
                	},
                	onOpen:function(){
					var properties = {
						runtimes: 'html5,flash,silverlight,html4',
						browse_button: $('#chooseFile').get(0), // you can pass an id...
							container: $('#uploadFile_container').get(0), // ... or DOM Element itself
							url: contextPath + '/fileManageAction/uploadFile.action?parentId='+filedId,
							flash_swf_url: contextPath + '/js/plupload-2.1.8/js/Moxie.swf',
							silverlight_xap_url: contextPath + '/js/plupload-2.1.8/js/Moxie.xap',
							filters: {
								max_file_size: '3000mb'
							},
							init: {
								PostInit: function () {
								<%--$('#fileManagePage_save_btn').unbind('click');
								$('#fileManagePage_save_btn').bind('click',function(){
									uploader.start();
								});--%>
							},

							FilesAdded: function (up, files) {
								plupload.each(files, function (file) {
									var data = {
										'id': file.id,
										'fileName': file.name,
										'fileSize': plupload.formatSize(file.size),
										'progress': '0'
									};
									$('#file_content_table').datagrid('appendRow', data)
								});
							},
							UploadProgress: function (up, file) {//文件上传进度
								var rows = $('#file_content_table').datagrid('getRows');

								$.each(rows,function(index,value){
									$('#'+file.id).progressbar({
										value:file.percent
									});
									//$('#'+file.id).progressbar('setValue',file.percent);
								});
								//document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = '<span>' + file.percent + '%</span>';
							},
							Error: function (up, err) {//文件上传进度
								// document.getElementById('console').appendChild(document.createTextNode('\nError #' + err.code + ': ' + err.message));
							},
							FileUploaded:function(up, file, info){//文件上传完成
								var title = '文件上传';
								info = $.parseJSON(info.response);
								var msg = info.info;
								var flag = info.flag;
								if(flag){
									$.messager.show({
										title:title,
										msg:msg,
										showType:'slide'
									});
								}else{
									$.messager.show({
										title:title,
										msg:msg,
										showType:'silde'
									});
								}
								uploader.removeFile(file.id);
								if(uploader.files.length==0){
									$('#filemanager_upload').dialog('close');
									uploader.destroy();
								}
								$('#fileManagePageTable').datagrid('reload');
							}
							}
							}
						uploader = new plupload.Uploader(properties);
						uploader.init();
                	}
	            ">
			<div id="uploadFile_container">
				<table id="file_content_table" class="easyui-datagrid" data-options="
						singleSelect:false,
						collapsible:true,
						fitColumns:true,
						border:false,
						striped:true">
					<thead>
						<tr>
							<th data-options="field:'id',checkbox:true"></th>
							<th data-options="field:'fileName',width:100">文件名称</th>
							<th data-options="field:'fileSize',width:80">文件大小</th>
							<th data-options="field:'progress',width:30,formatter:formatterPrgress"></th>
						</tr>
					</thead>
				</table>
			</div>
	    </div>
	    <div id="dlg-toolbar" style="padding:2px 0">
	        <table cellpadding="0" cellspacing="0" style="width:100%">
	            <tr>
	                <td style="padding-left:2px">
	                    <a href="javascript:void(0)" id="chooseFile" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">选择文件</a>
						<a href="javascript:void(0)" id="removeFile" onclick="removeFile()" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
	                </td>
	            </tr>
	        </table>
	    </div>
	    <div id="dlg-buttons">
	        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="uploader.start()"  id="fileManagePage_save_btn">保存</a>
	        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#filemanager_upload').dialog('close')">取消</a>
	    </div>
		<script type="text/javascript">
			var rowEdit = null;
			var contextPath = "<%=path%>";
			var uploader;
			var filedId = '${fileId}';
		</script>
		<script type="text/javascript" src="<%=path%>/js/plupload-2.1.8/js/plupload.full.min.js"></script>
		<script type="text/javascript" src="<%=path%>/js/plupload-2.1.8/js/i18n/zh_CN.js"></script>
		<script type="text/javascript" src="<%=path%>/js/com/clearnight/oa/filemanage/FileManagePage.js"></script>
		<script type="text/javascript">
		 	//.removeClass('lines-both lines-no lines-right')
			//uploader.init();
			function formatFileName(value,row,index){
					var v = "";					
					var onClick = "";
					if(row.fileType==='${dirStr}'){
						onClick="getFolderList('"+row.id+"','"+row.parentId+"')";
					}else{
						onClick="";
					}
					if(value){
						if(value.length>30){
							v = value.substring(0, 20);
							v+="…";
						}else{
							v = value;
						}
						value = "<img title=\""+value+"\" class=\"imgClass\" src=\"<%=path%>/fileManageAction/getImageByType.action?imgType="+row.fileType+"\"/>"+"<a class=\"aClass\" title=\""+value+"\" href=\"javascript:void(0)\" onclick=\""+onClick+"\">"+v+"</a>";
						return value;
					}	
			}
			
			function formatFileType(value,row,index){
				if(value==="${dirStr}"){
					return "";
				}else{
					return value;
				}
			}
			
			function formatFileSize(value,row,index){
				if(value===0 && row.fileType==="${dirStr}"){
					return "";
				}else{
					var size = value;
					function round(num, precision) {
						return Math.round(num * Math.pow(10, precision)) / Math.pow(10, precision);
					}

					var boundary = Math.pow(1024, 4);

					// TB
					if (size > boundary) {
						return round(size / boundary, 1) + " tb";
					}

					// GB
					if (size > (boundary/=1024)) {
						return round(size / boundary, 1) + " gb";
					}

					// MB
					if (size > (boundary/=1024)) {
						return round(size / boundary, 1) + " mb";
					}

					// KB
					if (size > 1024) {
						return Math.round(size / 1024) + " kb";
					}
					return size + " b";
					//return uploader.formatSize(value);
				}
			}
			
			function formatRowStyle(value,row,index){
				return "font-family:'Microsoft Yahei'";
			}
			
			/*新建文件夹*/
			function newFolder(){
				if(!rowEdit){					
	 				$("#fileManagePageTable").datagrid("insertRow",{
	 					index:0,
	 					row:{fileType:"${dirStr}"}
					});
	 				$("#fileManagePageTable").datagrid("beginEdit",0);
	 				rowEdit = 1;
				}
			}
			
			/*取消编辑状态*/
			function cancleEdit(){
				if(rowEdit){					
					$("#fileManagePageTable").datagrid("deleteRow",0);
					rowEdit = null;
				}
			}
			
			/*保存编辑状态*/
			function saveFolder(){
				if(rowEdit){
					$("#fileManagePageTable").datagrid("endEdit",0);
					rowEdit = null;
				}
			}
			
			/*当结束编辑以后*/
			function dataGridOnAfterEdit(index,row,changes){
				var flag = true;			
				var folderPath = "${fileId}";
				if(folderPath)
					changes.parentId = folderPath;
				changes.parentId = folderPath;
				$.get("<%=path%>/fileManageAction/canAddFolder.action",{fileName:row.fileName,id:folderPath},function(data){
					flag = data.flag;
					if(flag){
						$.post("<%=path%>/fileManageAction/saveFolder.action",changes,function(data){
							$.messager.show({
		                		title:'提示',
		                		msg:data.info
		                	});
		                 	$("#fileManagePageTable").datagrid("reload");
						});
					}else{
						$.messager.show({
	                		title:'提示',
	                		msg:data.info
	                	});
					}
					$("#fileManagePageTable").datagrid("reload");
				});
					
				
			}
			/*转向该文件夹下的文件列表*/
			function getFolderList(fileId,parentId){
				var folderPath = "${folderPath}";
				var row = $('#fileManagePageTable').datagrid('getSelected');
				var selectTab = $('#framework_tabs').tabs("getSelected");
				var url = "<%=path%>/fileManageAction/toFileManagePage.action?parentId="+parentId+"&fileId="+fileId;
				selectTab.panel('refresh',url);
			}
			
			function backParentPage(){
				var folderPath = "${parentId}";
				var selectTab = $('#framework_tabs').tabs("getSelected");
				var url = "<%=path%>/fileManageAction/toFileManagePage.action?fileId="+folderPath;
				selectTab.panel('refresh',url);
			}
			
			function deleteDirOrFile(){
				
				var selectData = $("#fileManagePageTable").datagrid("getSelections");
				var folderPath = "${folderPath}";
				if(selectData.length>0){
					var ids = [];
					$.each(selectData,function(index,value){
						ids.push(value.id);
					});
					var data = {"ids" : ""+ids+""};
					$.post("<%=path%>/fileManageAction/deleteFile.action",data,function(data){
	                 	$.messager.show({
	                		title:'提示',
	                		msg:data.info
	                	});
	                 	$("#fileManagePageTable").datagrid("reload");
					});
				}
			}
			function uploadFile(){
				var fileUplaodDialog = $("#filemanager_upload");
				fileUplaodDialog.dialog('open');
			}
			function removeFile(){
				var rows = $("#file_content_table").datagrid("getSelections");
				if(rows.length){
					$.each(rows,function(index,value){
						var valueIndex = $("#file_content_table").datagrid("getRowIndex",value);
						$("#file_content_table").datagrid("deleteRow",valueIndex);
						uploader.removeFile(value.id);
					});
				}else{
					$.messager.show({
						title:"文件上传",
						msg:"请选择要删除的文件",
						showType:'slide'
					});
				}
			}
			function formatterPrgress(value,row,index){
				var progress = row.progress;
				return "<div id=\""+row.id+"\" style=\"width:50px;\"/>";
			}
		</script>
		<style type="text/css">
			.aClass:link,.aClass:visited{ 
			 text-decoration:none;  /*超链接无下划线*/
			 color: black; 
			} 
			.aClass:hover{ 
			 text-decoration:underline;  /*鼠标放上去有下划线*/ 
			} 
			.aClass{
				display:inline;	
				float:left;
				padding-left: 5px;
			}
			.imgClass{
				width:16px;
				height:16px;
				float: left;
				display:inline;
			}
		</style>
</body>
</html>