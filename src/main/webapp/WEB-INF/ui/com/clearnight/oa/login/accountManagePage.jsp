<%--
  Created by IntelliJ IDEA.
  User: ChenZhao
  Date: 2015/11/13 0013
  Time: 下午 7:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%String path = request.getContextPath();%>
<html>
<head>
    <title>账户管理</title>
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">
  <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
  <meta http-equiv="description" content="This is my page">
  <link rel="stylesheet" href="<%=path%>/js/easyui1.4/themes/default/easyui.css" type="text/css"></link>
  <link rel="stylesheet" href="<%=path%>/js/easyui1.4/themes/icon.css" type="text/css"></link>
  <script type="text/javascript" charset="UTF-8" src="<%=path%>/js/easyui1.4/jquery.min.js"></script>
  <script type="text/javascript" charset="UTF-8" src="<%=path%>/js/easyui1.4/jquery.easyui.min.js"></script>
  <script type="text/javascript" charset="UTF-8" src="<%=path%>/js/easyui1.4/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>
<!--数据展示表格开始-->
    <table id="accountManageTable"></table>
<!--数据展示表格结束-->
<!--查询表单和操作按钮开始-->
    <div id="manageForm" style="padding:2px 5px;">
  <!--查询表单开始-->
      <div>
        <form id="searchForm">
          <table>
            <tr>
              <td style="color: #444;font-size:12px;">用户名：</td>
              <td><input class="easyui-textbox" style="width: 110px" name="accountName"/></td>
              <td style="color: #444;font-size:12px;">添加时间：</td>
              <td><input class="easyui-datebox" style="width:110px" name="insertDate"></td>
              <td><a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="accountManage.searchBtnClick()">查询</a></td>
            </tr>
          </table>
        </form>
      </div>
  <!--查询表单结束-->
  <!--操作按钮开始-->
      <div style="margin-bottom:0px">
        <table>
          <tr>
            <td>
              <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="accountManage.addBtnClick()">添加</a>
            </td>
            <td>
              <div style="float: left;height: 24px;border-left: 1px solid #ccc;border-right: 1px solid #fff;margin: 2px 1px;"></div>
            </td>
            <td>
              <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="accountManage.updateBtnClick()">修改</a>
            </td>
            <td>
              <div style="float: left;height: 24px;border-left: 1px solid #ccc;border-right: 1px solid #fff;margin: 2px 1px;"></div>
            </td>
            <td>
              <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="accountManage.removeBtnClick()">删除</a>
            </td>
          </tr>
        </table>
      </div>
  <!--操作按钮结束-->
    </div>
<!--查询表单和操作按钮结束-->

<!--编辑表单开始-->
  <div class="easyui-dialog" style="width:600px;height:400px;padding:10px 20px;" id="AM_dialog">
      <form id="accountManageAMForm" method="post">
        <input type="hidden" name="id"/>
        <input type="hidden" name="insertDate"/>
        <table>
            <tr>
              <td>用户名：</td>
              <td><input class="easyui-textbox" name="accountName" data-options="
                    required:true,
                    novalidate:true,
                    prompt:'用户名不能为空',
                    missingMessage:'用户名不能为空'"/>
              </td>
            </tr>
            <tr>
              <td>密码：</td>
              <td><input class="easyui-textbox" type="password" name="accountPwd" data-options="
                    required:true,
                    novalidate:true,
                    missingMessage:'密码不能为空'"/>
              </td>
            </tr>
            <tr>
                <td>用户：</td>
                <td><select class="easyui-combogrid" name="userId" style="width:100px" data-options="
                        idField: 'id',
                        textField: 'userName',
                        panelWidth: 450,
                        panelMinWidth: '30%',
                        url: '<%=path%>/userAjaxAction/getUserPagenation.action',
                        columns: [[
                             {field:'id',checkbox:true,width:50},
                             {field:'phoneForAccount',formatter:showImg,title:'头像',width:50},
                             {field:'userName',title:'用户名',width:50},
                             {field:'userSex',title:'性别',width:50,formatter:formatSex},
                             {field:'nation',title:'民族',formatter:formatNation,width:50},
                             {field:'birthday',title:'出生日期',width:50,sortable:'true'},
                             {field:'userAge',title:'年龄',width:50,sortable:'true'}
                        ]],
                        fitColumns: true
                    ">
                </select>
                </td>
            </tr>
        </table>
      </form>
  </div>
<!--编辑表单结束-->
</body>
<script type="text/javascript">
  var contextPath = "<%=path%>";
</script>
<script type="text/javascript" src="<%=path%>/js/com/clearnight/oa/login/framework.js"></script>
<script type="text/javascript" src="<%=path%>/js/com/clearnight/oa/login/accountManagePage.js"></script>
<script type="text/javascript">
    /**
     * 显示头像
     */
    function showImg(value,row,index){
        var url = contextPath+"/images/img8.jpg";
        if(row.fileBeanId){
            url = contextPath+"/fileManageAction/getFileBytesByFileId.action?id="+row.fileBeanId;
        }
        return "<img align='center' style='width:50px;height:50px' src="+url+" />";
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
</script>
</html>
