/**
 * treegrid初始化
 */
$('#menuManager_treegrid').treegrid({
	  	url:contextPath+'/menuAjaxAction/getMenuTreeGridData.action',
        rownumbers: true,
        pagination: true,
        pageSize: 15,
        pageList: [15,30,45,60],
        idField: 'id',
        treeField: 'menuName',
        fitColumns: true,
        onBeforeLoad: function(row,param){
            if (!row) {    // load top level rows
                param.id = 0;    // set id=0, indicate to load new page rows
            }
        },
	    toolbar: [{
			iconCls: 'icon-add',
			handler: function(){alert('添加')}
		},'-',{
			iconCls: 'icon-edit',
			handler: function(){alert('修改')}
		},'-',{
			iconCls: 'icon-remove',
			handler: function(){alert('删除')}
		}],
	    columns:[[
	        {title:'菜单名称',field:'menuName',width:180},
	        {title:'可见性',field:'visibility',width:60},
	        {title:'菜单路径',field:'menuUrl',width:80},
	        {title:'备注',field:'marak',width:80}
	    ]]
	    
});