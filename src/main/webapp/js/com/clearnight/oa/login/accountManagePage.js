/**
 * Created by Administrator on 2015/11/13 0013.
 */
$(function(){
    accountManage = {};

    /*初始化账户管理table*/
    accountManage.initDataTabel  = function(){
        $("#accountManageTable").datagrid({
            fit:true,
            border:false,
            url:contextPath+'/loginAjaxAction/getAccountPagenation.action',
            pagination: true,
            rownumbers:true,
            pageSize: 15,
            pageList: [15,30,45,60],
            fitColumns: true,
            idField: 'id',
            striped:true,
            footer:'#searchForm',
            toolbar: "#manageForm",
            columns:[[
                {field:'id',checkbox:true,width:20},
                {field:'accountName',title:'用户名',width:20},
                {field:'insertDate',title:'添加时间',width:20,sortable:'true'}
            ]]
         });
    }
    /*初始化添加或修改dialog*/
    accountManage.initAMDialog = function(){
        var amText = "保存";
        var id = $("input[type='hidden']").val();
        $("#AM_dialog").dialog({
            closed:true,
            modal:true,
            buttons:[{
                text:amText,
                iconCls:'icon-save',
                handler:function(){
                    $("#accountManageAMForm").form("submit",{
                        url: contextPath+'/loginAjaxAction/amAccount.action',
                        onSubmit: function(){
                            return $(this).form('enableValidation').form('validate');
                        },
                        success:function(data){
                            data = $.parseJSON(data);
                            $.messager.show({
                                title:'提示',
                                msg:data.info
                            });
                            $('#AM_dialog').dialog('close');
                            $('#accountManageTable').datagrid('reload');
                        }
                    });
                }
            },{
                text:'取消',
                iconCls:'icon-cancel',
                handler:function(){
                    $("#accountManageAMForm").form('clear');
                    $("#AM_dialog").dialog("close");
                }
            }]
        });
    }
    /*查询按钮*/
    accountManage.searchBtnClick = function(){
        var searchData = serializeObject($("#searchForm"));
        //var accountName = $("input[name='accountName']").val();
        $("#accountManageTable").datagrid('load',searchData);

    }
    /*添加按钮*/
    accountManage.addBtnClick = function(){
        $("#AM_dialog").dialog('open').dialog('setTitle','添加账户');
        $("#accountManageAMForm").form('clear');

    }
    /*修改按钮*/
    accountManage.updateBtnClick = function(){
        var row = $('#accountManageTable').datagrid('getSelected');
        if(row){
            $("#accountManageAMForm").form('clear');
            $("#AM_dialog").dialog('open').dialog('setTitle','修改账户');
            row.accountPwd = "";
            $('#accountManageAMForm').form('load',row);
        }else{
            $.messager.show({
                title:'提示',
                msg:'请选择要修改的账户'
            });
        }
    }
    /*删除按钮*/
    accountManage.removeBtnClick = function(){
        var rows =  $('#accountManageTable').datagrid('getSelections');
        if(rows.length>0){
            var ids = [];
            for(var i=0;i<rows.length;i++){
                ids.push(rows[i].id);
            }
            $.messager.confirm('提示信息','是否删除选中的行？',function(r){
                if (r){
                    $.post(contextPath+'/loginAjaxAction/deleteAccount.action',
                        {"ids":""+ids+""},
                        function(result){
                            console.info(result);
                            if (result.flag){
                                $('#accountManageTable').datagrid('reload');    // reload the user data
                                $.messager.show({    // show error message
                                    title: '信息',
                                    msg: result.info
                                });
                            } else {
                                $.messager.show({    // show error message
                                    title: '信息',
                                    msg: result.info
                                });
                            }
                        },'json');

                }
            });
        }
    }

    /*执行出初始化表格*/
    accountManage.initDataTabel();
    /*执行初始化添加和删除表单*/
    accountManage.initAMDialog();

});
