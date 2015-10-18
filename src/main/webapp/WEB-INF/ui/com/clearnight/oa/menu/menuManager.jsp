<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<body>
	 <div id="menuManager_layout" class="easyui-layout" data-options="fit:true">
	    <div data-options="region:'center',border:false">
	    	<table id="menuManager_treegrid" class="easyui-treegrid" data-options="
	    		url:'<%=path%>/menuAjaxAction/getMenuTreeGridData.action',
	    		border:false,
	    		fit:true,
		        rownumbers: true,
		        pagination: true,
		        animate:true,
		        pageSize: 15,
		        pageList: [15,30,45,60],
		        idField: 'id',
		        treeField: 'menuName',
		        fitColumns: true,
		        onBeforeLoad: function(row,param){
		            if (!row) {    
		                param.id = 0;
		            }
		        },
		        onLoadSuccess: function(row,data){
  					var roots = $('#menuManager_treegrid').treegrid('getRoots');
  					$.each(roots,function(index,value){
  						$('#menuManager_treegrid').treegrid('expandAll', value.id);
  					});
                },
			    toolbar: [{
					iconCls: 'icon-add',
					text: '添加',
					handler: function(){
							newMenu();
					}
				},'-',{
					iconCls: 'icon-edit',
					text: '修改',
					handler: function(){
							editMenu();
					}
				},'-',{
					iconCls: 'icon-remove',
					text: '删除',
					handler: function(){
							destroyMenu();
					}
				}],
			    columns:[[
			        {title:'菜单名称',field:'menuName',width:180},
			        {title:'可见性',field:'visibility',width:60},
			        {title:'菜单路径',field:'menuUrl',width:80},
			        {title:'备注',field:'mark',width:80}
			    ]]">
	    	</table>
	  </div>
	</div>

 	<div id="menuManager_AMDialog" class="easyui-dialog" style="width:600px;height:480px;padding:10px 20px"
            closed="true" modal="true" buttons="#menuManager_dlg_buttons">
        <div class="ftitle">菜单信息</div>
        <form id="menuManager_fm" class="easyui-form" data-options="novalidate:true" method="post">
            <div class="fitem">
                <label>菜单名称：</label>
                <input type="text" name="menuName" class="easyui-textbox" data-options="validType:'length[0,50]',novalidate:true,required:true,prompt:'菜单名应小于50个字符',missingMessage:'菜单名称不能为空'"/>
            </div>
            
            <div class="fitem">
                <label>菜单路径：</label>
                <input type="text" name="menuUrl" class="easyui-textbox" data-options="validType:'length[0,200]',novalidate:true,missingMessage:'菜单路径不能为空'"/>
            </div>
            <div class="fitem">
            	<label>上级菜单：</label>
            	<!-- <input type="text" name="parentId" class="easyui-textbox"/> -->
            	<input  name="parentId" class="easyui-combotree" style="width:200px;" onchange="combonTreeonClick()"
        					data-options="
        					panelHeight:'auto',
        					url:'<%=path%>/menuAjaxAction/getTreeData.action',
        					onChange:function(){
        
        					}
        					
        		"/>
            </div>
            <div class="fitem">
                <label>可见性：</label>
                
            	<select id="visibility" class="easyui-combobox" data-options="panelHeight:'auto'" name="visibility" style="width:100px;">
				    <option value="true" selected="selected">显示</option>
				    <option value="false">不显示</option>				    
				</select>
            </div>
            <div class="fitem">
                <label>备注：</label>
                <input name="mark" data-options="validType:'length[0,500]',multiline:true,prompt:'备注内容应小于500个字符'" class="easyui-textbox"  style="width:300px;height:100px"/>
            </div>
        </form>
    </div>
    <div id="menuManager_dlg_buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveMenu()" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#menuManager_AMDialog').dialog('close')" style="width:90px">取消</a>
    </div>
    
      <script type="text/javascript">
      $('#parentId').combotree('reload','<%=path%>/menuAjaxAction/getTreeData.action');
        var url;
        function newMenu(){
            $('#menuManager_AMDialog').dialog('open').dialog('setTitle','添加菜单');
            $('#menuManager_fm').form('clear');
            $('#visibility').combobox('setValue', 'true');
            url = '<%=path%>/menuAjaxAction/amMenuInfo.action';
        }
        
        function editMenu(){
        	$('#menuManager_fm').form('clear');
            var row = $('#menuManager_treegrid').datagrid('getSelected');
            if (row){
                $('#menuManager_AMDialog').dialog('open').dialog('setTitle','编辑菜单');
                $('#menuManager_fm').form('load',row);
               	$('#parentId').combobox('setValue',""+row.parentId);
                $('#visibility').combobox('setValue', ""+row.visibility);
                url = '<%=path%>/menuAjaxAction/amMenuInfo.action?id='+row.id;
            }
        }
        function saveMenu(){
            $('#menuManager_fm').form('submit',{
                url: url,
                onSubmit: function(){
                    return $(this).form('enableValidation').form('validate');
                },
                success: function(result){
                    var result = eval('('+result+')');
                    if (result.flag){
                        $('#menuManager_AMDialog').dialog('close');        // close the dialog
                        $('#menuManager_treegrid').treegrid('reload');    // reload the user data
                    } else {
                    	$.messager.show({
                              title: '信息',
                              msg: '失败'
                        });
                    }
                }
            });
        }
        
        function destroyMenu(){
            var rows = $('#menuManager_treegrid').datagrid('getSelections');
            if (rows.length>0){
                $.messager.confirm('提示信息','确认删除选中的信息？',function(r){
                    if (r){
                        $.post('<%=path%>/menuAjaxAction/deleteMenuInfoById.action',{id:rows[0].id},function(result){
                            if (result.flag){
                                $('#menuManager_treegrid').treegrid('reload');    // reload the user data
                            } else {
                                $.messager.show({    // show error message
                                    title: '信息',
                                    msg: '删除失败'
                                });
                            }
                        },'json');
                    }
                });
            }
        }
    </script>
    <style type="text/css">
        #menuManager_fm{
            margin:0;
            padding:10px 30px;
        }
        .ftitle{
            font-size:14px;
            font-weight:bold;
            padding:5px 0;
            margin-bottom:10px;
            border-bottom:1px solid #ccc;
        }
        .fitem{
            margin-bottom:5px;
        }
        .fitem label{
            display:inline-block;
            width:80px;
        }
        .fitem input{
            width:160px;
        }
    </style>
    
</body>
</html>
