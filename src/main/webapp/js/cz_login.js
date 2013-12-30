function loginBox(){
	var loginHTML = '<div id="winBox_login">'
						+'<div class="bg"></div>'
						+'<div class="winBox_loginMain">'
							+'<dl class="tyRead">'
								+'<dt class="current">天翼阅读用户</dt>'
								+'<dd style="height:90px;"></dd>'
								+'<dd class="h48">'
									+'<label>用户名：</label>'
								    +'<input type="text" value="请输入手机/邮箱/用户名" id="account" onfocus="if(this.value == \'请输入手机/邮箱/用户名\') { this.value = \'\'}" onblur="if(this.value == \'\') { this.value = \'请输入手机/邮箱/用户名\'}" class="input_text">'
								+'</dd>'
								+'<dd class="h48">'
									+'<label>密码：</label>'
								    +'<input type="password" id="password" class="input_text">'
								    +'<span class="getPwd"><a href="/user/find-pass.html" target=\'_blank\'>找回密码？</a></span>'
								+'</dd>'
								+'<dd class="h30">'
									+'<label></label>'
								    +'<input type="checkbox">'
								    +'<span class="c2c6084">记住帐号</span>'
								+'</dd>'
								+'<dd class="h30">'
									+'<label></label>'
								    +'<input type="checkbox" checked="checked">'
								    +'<span class="agreement">已阅读<a href="http://passport.189.cn/SelfS/About/Agreement.aspx" target="_blank">服务条款</a></span>'
								+'</dd>'
								+'<dd class="optionBox">'
									+'<a id="submit" class="winBox_btn" href="javascript:void(0)">登录</a><a class="winBox_btn winBox_btnOff" href="javascript:register();">注册新用户</a>'
								+'</dd>'
							+'</dl>'
//							+'<dl class="ty">'
//								+'<dt>天翼帐号用户</dt>'
//								+'<dd style="height:45px;display:none;"></dd>'
//								+'<dd style="display:none;">'
//									+'<iframe id="udbLogin" width="280" height="257" scrolling="no" frameborder="0" src=""></iframe>'
//				                +'</dd>'
//							+'</dl>	'
						+'</div>'
//						+'<div class="other_login">'
//				            +'<span>使用合作网站登录：</span>'
//				            +'<a class="other_login_sina sinaLogin" href="javascript:" title="新浪"></a>'
//				            +'<a class="tencentLogin other_login_qq" href="javascript:" title="腾讯"></a>'
//				            +'<a class="renrenLogin other_login_rr" href="javascript:" title="人人网"></a>'
//				        +'</div>'
						+'<span class="winBox_close" onclick="closeWinBox();">&nbsp;</span>'
					+'</div>'	;
	winBox(loginHTML);
}/* Scripts For HeadTop Login */


$(function(){
	if($("#lg_in").is(":hidden")) return; 
	$(".login").keydown(function(e){
		if (e.keyCode == 13) {
		$(this).find(".submit").trigger("click");
	}
	});
});

/* End top Login Scripts */


/* 弹窗登录 */
$(function(){
	$('.winBox_loginMain dt').live('click',function(){
		$(this).parents('.winBox_loginMain').find('dt').removeClass('current');
		$(this).parents('.winBox_loginMain').find('dd').hide();
		$(this).addClass('current').siblings('dd').show();
		if($(this).parents('dl').hasClass('ty')){
			var reback=_frontPath_urlRewriter_baseUrl+"/charge/alipay.html";
			$("#udbLogin").attr("src",_dynamicPath + "user/udbLoginAction.shtml?reback="+reback);
		}

	});

	$("#submit").live('click',function() {
		var reUrlBack = getParameter("reback");
		var account = $.trim($("#account").val());
		if (account == "" || "请输入用户名" == account) {
			message('#account', '请输入您的用户名');
			return;
		}
		var psd = $.trim($("#password").val());
		if (psd == "") {
			message('#password', '请输入您的密码');
			return;
		}
		var loginState = 'off';
		if ($("#loginState").attr("checked") == 'checked') {
			loginState = 'on';
		}
		$.getJSON(_dynamicPath + "login.json", {
			'account' : account,
			'password' : psd,
			'loginState' : loginState
		}, function(data) {
			if (1 == data.message.code) {
				window.location.href = _frontPath_urlRewriter_baseUrl+"admin/manage.htm";
			} 
		});
	});

	function message(obj,desc){
		$('.errorTips').remove();
		$(obj).parent().append('<span class="errorTips">'+desc+'</span>');
	};
});

function register(){
	var reback=getParameter("reback");
	var url=null;
	if(reback!=null){
		url=_frontPath+'/user/register-user.html?reback='+reback;
	}else{
		url=_frontPath+'/user/register-user.html?reback='+location.href;
	}
	window.open(url,"_blank");
}

