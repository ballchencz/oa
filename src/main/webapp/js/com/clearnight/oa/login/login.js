/**
 * dialog初始化
 */
$("#login_loginDialog").dialog(
	{
		 width:500,
		 height:300,
		 closable:false,
		 modal:false
	}
);
/**
 * 用户名input初始化
 */
$("#accountName").textbox(
	{
		icons:[{
			iconCls:'icon-man'
		}],
		prompt:'请输入用户名',
		missingMessage:'用户名不能为空',
		required:true
	}
);

/**
 * 密码input初始化
 */
$("#accountPwd").textbox({
		icons:[{
			iconCls:'icon-lock'
		}],
		missingMessage:'密码不能为空',
		required:true
});

/**
 * 登陆和重置按钮的初始化
 */
$("#login_form_loginBtn").linkbutton({
	
});
$("#login_form_resBtn").linkbutton({
});

$(document).keypress(function (event) {
    var key = event.which;
    if(key==13){
    	loginBtnClick();
    }
   
});

$("#login_form").form({
	novalidate:true
});

/**
 * 登陆按钮的点击事件
 */
function loginBtnClick(){
	$("#login_form").form("submit",{
		url:contextPath+"/loginAjaxAction/validLogin.action",
		onSubmit : function(param){
			var isValid = $(this).form('enableValidation').form('validate');
			if(isValid){
				//param.accountName = $('#accountName').val();
				//param.accountPwd = $('#accountPwd').val();				
			}else{
				return isValid;
			}
		},
		success:function(data){
			data = $.parseJSON(data);
			console.info(data);
			if(data.flag){
				$.messager.show({
					title:'登陆信息',
					msg:'登陆成功',
					//timeout:5000,
					showType:'slide'
				});
				setTimeout(function(){					
					window.location = contextPath+"/loginAction/toFrameWorkPageAction.action";
				}, 1000);
			}else{
				$.messager.alert('Warning','用户名或密码错误');
			}
		}
	});
}

/**
 * 重置按钮的点击事件
 */
function resBtnClick(){
	$("#login_form").form("clear");
	
}


