var _dynamicPath = "http://127.0.0.1:8080/";
var _frontPath = "http://127.0.0.1/";
var _frontPath_urlRewriter_baseUrl="http://127.0.0.1:8080/";

//var _dynamicPath ="http://192.168.10.107:8080/";
//var _frontPath = "http://192.168.10.107:8080/";
//var _frontPath_urlRewriter_baseUrl="http://192.168.10.107:8080/";

var goMailUrl="http://elive.webmail.189.cn/webadmin/vnet_mail.htmls?cmd_ID=0";
	
function request(paras)
{ 
    var url = location.href; 
    var paraString = url.substring(url.indexOf("?")+1,url.length).split("&"); 
    var paraObj = {} 
    for (i=0; j=paraString[i]; i++){ 
    paraObj[j.substring(0,j.indexOf("=")).toLowerCase()] = j.substring(j.indexOf("=")+1,j.length); 
    } 
    var returnValue = paraObj[paras.toLowerCase()]; 
    if(typeof(returnValue)=="undefined"){ 
    return ""; 
    }else{ 
    return returnValue; 
    } 
}

/** 异步调用 */
function tyGetJson(url,divName)
{
	$.getJSON(url,function(data){
		$(divName).html(data);
		setloadImg(divName);
	});
}
/** 异步调用 */
function goPage(url,divName,action)
{
	var pageNum=$('#'+action+'_pageNum').val();
	$.getJSON(url+"pageNum="+pageNum,function(data){
		$(divName).html(data);
	});
}

// 跳转到登录页面
function showLogin(rebackURL) {
	if(rebackURL!=null){
		rebackURL= decodeURIComponent(rebackURL).replace(_frontPath, "");
	    if(!(""==rebackURL)){
	    	var url=_dynamicPath + "user/setRebackURL.shtml?jsoncallback=?&reback="+rebackURL;
	    	$.ajax({
				url : url,
				dataType : "jsonp",
				type : "get",
				async : false,
				success : function(json) {
					if(json.code==200)
						{
						window.location.href=_frontPath+"user/login.html";
						}
				}
	    	});
	}
	 else
	    	{
	    	window.location.href=_frontPath+"user/login.html";
	    	}
	}
	
}
// 加载广告，如果有
function loadAdInfo()
{
	
	$("div[id^='adInfo_']").each(function()
	{
		var id = $(this).attr("id").substring('adInfo_'.length, $(this).attr("id").length);	
		var object = this;
		var url = _dynamicPath + "adInfo/adInfoAjax!adInfo.shtml?jsoncallback=?&adId="+id;
		$.getJSON(url,function(data){
			var dataObj=eval("("+data+")");
			$("a",object).attr('href',dataObj.url);
			$("a",object).attr('target',"_blank");
			$("img",object).attr('src',dataObj.adImage);
		});
	} 
	);		
}
function hrefLogin() {
	showLogin(window.location.href);
}
function login() {
			var account = $.trim($("#top_account").val());
			if (account == "") {
				var rp = new RemmendPopup();
				rp.init({
					val : 1,
					text : '请输入您的用户名',
					elem : $('#top_account')
				});
				return;
			}

			var psd = $.trim($("#top_password").val());
			if (psd == "") {
				var rp = new RemmendPopup();
				rp.init({
					val : 1,
					text : '请输入您的密码',
					elem : $('#top_password')
				});
				return;
			}
			$.getJSON(
							_dynamicPath
									+ "user/doLogin.shtml?jsoncallback=?",
							{
								'account' : account,
								'password' : psd
							},
							function(data) {
								if (200 == data.code) {
									reflushUserInfo();
								} else {
									var rp = new RemmendPopup();
									rp.init({
										val : 1,
										text : data.desc,
										elem : $('#top_password')
									});
								}
							});
}
// 退出
function logout() {
	$.getJSON(
					_dynamicPath + "user/logout.shtml?jsoncallback=?",
					function(data) {
						if (data.code == 200) {
							$(".lg_wel").html("");
							$(".lg_in").show();
							$(".lg_wel").hide();
							$("#top_account").attr("value",'');
							$("#top_password").attr("value",'');
							if (data.desc != null&&""!=$.trim(data.desc)) {
								window.location.href = data.desc;
							}
							if (String(window.location).indexOf("personal") >= 0
									|| String(window.location).indexOf(
											"register_ok") >= 0) {
								window.location.href = _frontPath
										+ "index.html";
							} else {
								var obj = $("#rightLoginInclude");
								if (obj) {
									obj.load("./layout/right_login.html?r="
											+ Math.random());
								}
							}
							
						}
					});
	removeBalance();
	notificToOfUserLogOut();
	load_signin_status();
}

/**
 * 延迟加载图片
 * @param obj 例如"img"
 */
function setloadImg(obj)
{
//	obj = obj +" img";
//	$(obj).each(function() {
//		$(this).addClass('lazy');
//		$(this).attr('data-original', $(this).attr('src'));
//		$(this).attr('src', "");
//	});
//	
//	$(obj).lazyload({ 
//		threshold : 0 ,
//		effect : "fadeIn"
//		}); 
}

/**
 * 动态刷新用户站点用户信息 如灯塔、右侧用户信息层
 */
function reflushUserInfo()
{
	$("#rightLoginInclude").html("");
	$("#rightLoginInclude").load(_frontPath + "layout/right_login.html");
	load_signin_status();
	getBalance();
	
	notiByReader();
	notificToOfUserLogin();
}
function getBalance(){
	if($("#balance")){
		$.getJSON(_dynamicPath+"recharge/balance.shtml?jsoncallback=?",function(res){
			var html="您的阅点余额：<strong>"+res.balance+"</strong>&nbsp;&nbsp;积分：<strong>"+res.integral+"</strong>";
			$("#balance").empty().html(html);
		});
	}
}
function removeBalance(){
	if($("#balance")){
		$("#balance").html("");
	}
}
//login.html 页面键盘事件响应
$(function(){
	$(".user_cut_login").keydown(function(e){
	 if (e.keyCode == 13) {
	           $(this).find(".submit").trigger("click");
	 }
	});
});

$(document).ready(function(){
	$.getScript(_frontPath+'js/jquery.lazyload.js',function()
			{
	loadAdInfo();
	setloadImg();
			});
});

//页面登陆后能和flex程序共享
function notificToOfUserLogin(){
	if($("#Main") == null) return;
	//此页面有阅读器,重新设置:User BookInfo等信息
	$("#Main").loadBookAndUserInfo();
}

function notificToOfUserLogOut(){
	if($("#Main") == null) return;
	//此页面有阅读器,重新设置:User BookInfo等信息
	$("#Main").loadBookAndUserInfo();
}

function load_signin_status(){
	$.ajax({
		url:_dynamicPath + "signin/get_signin_status.shtml?jsoncallback=?",
		dataType : "jsonp",
		type : "get",
		success : function(json) {
			var status = json.status;
			var message_text = "";
			switch (status){
			case "-1":
				$('#tydzs_id').show();
				return;
			case "-2":
				message_text = "亲，还没开始，请耐心等待哦!";
				break;
			case "-3":
				message_text = "亲，已抢完了，下一个时段再来吧！";
				break;
			case "-4":
				message_text = '<a href="javascript:;" class="signinClick signbtn"><img src="images/signIn.png" alt="签到抢阅点" /></a>';
				break;
			case "-5":
				message_text = "亲，没抢到，下个时段再来吧!";
				break;
			case "-6":
				message_text = "抢到阅点了，快去买书吧!";
				break;
			}
			$('#signInInclude').remove();
			var signinBox = '<div id="signInInclude" style="margin-top:-5px; margin-bottom:8px;">'
				+'<div class="signinBox">'
				+'<p>真金白银大放送，每日9点/13点/18点签到抢阅点。<a href="qiandao.html">详情&gt;&gt;</a></p>'
				+'<div class="singninBtnbox">'
				+ message_text
				+'<span class="arrowUp"></span>'
				+'</div>'
				+'</div>'
				+'</div>';
			
			$('#rightLoginInclude').after(signinBox);
		},
		error : function(res) {}
	});
}

var reUrl = window.location;
$(document)
		.ready(
				function() {
					$(".sinaLogin")
							.live(
									"click",
									function() {
										var url = "";
										if (reUrl == null || reUrl == "") {
											url = _dynamicPath
													+ "user/sinaLoginAction.shtml";
										} else {
											url = _dynamicPath
													+ "user/sinaLoginAction.shtml?reback="
													+ reUrl;
										}
										//window.location.href = url;
										window.open(url,"_blank");
									});
					$(".tencentLogin")
							.live(
									"click",
									function() {
										var url = "";
										if (reUrl == null || reUrl == "") {
											url = _dynamicPath
													+ "user/tencentLoginAction.shtml";
										} else {
											url = _dynamicPath
													+ "user/tencentLoginAction.shtml?reback="
													+ reUrl;
										}
										//window.location.href = url;
										window.open(url,"_blank");
									});
					$(".renrenLogin")
							.live(
									"click",
									function() {
										var url = "";
										if (reUrl == null || reUrl == "") {
											url = _dynamicPath
													+ "user/renrenLoginAction.shtml";
										} else {
											url = _dynamicPath
													+ "user/renrenLoginAction.shtml?reback="
													+ reUrl;
										}
										//window.location.href = url;
										window.open(url,"_blank");
									});
					notiByReader();
				});

function notiByReader() {
	$.getJSON(
					_dynamicPath + "user/name.shtml?jsoncallback=?",
					function(data) {
						if (data != null) {
							var data = eval('(' + data + ')');
							
							var unReadStr = "";
							if(data.unReadMail != null)
							{
								unReadStr += "你的邮箱有<a href='"+goMailUrl+"'>"+data.unReadMail+"</a>封未读邮件&nbsp;&nbsp;";
							}
								$(".lg_wel")
										.html(
												"天翼阅读欢迎您！ <a href='"+_frontPath+"/personal.html'>"
														+ data.userName
														+ "</a>&nbsp;&nbsp;"+unReadStr+"<a href=\""+_frontPath_urlRewriter_baseUrl+"/charge/alipay.html\">充值</a>&nbsp;&nbsp;<a href=\"javascript:logout()\">退出</a>");
								$(".lg_wel").show();
								$(".lg_in").hide();
						} else {
							$(".lg_wel").html("");
							$(".lg_in").show();
							$(".lg_wel").hide();
						}
					});
	
}

(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
	  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
	  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
	  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

	  ga('create', 'UA-43555875-1', '189.cn');
	  ga('send', 'pageview');
	  
	  
	  function register(){
			var reback=getParameter("reback");
			var url=null;
			if(reback!=null){
				url=_frontPath+'/user/register-user.html?reback='+reback;
			}else{
				url=_frontPath+'/user/register-user.html?reback='+location.href;
			}
			window.open(url,"_blank");
		};
	  
	  function getParameter(name){ 
		    var paramStr=location.search; 
		    if(paramStr.length==0)return null; 
		    if(paramStr.charAt(0)!='?')return null; 
		    //paramStr=unescape(paramStr); 
		    paramStr=paramStr.substring(1); 
		    if(paramStr.length==0)return null; 
		    var params=paramStr.split('&'); 
		    var p = null;
		    for(var i=0;i<params.length;i++){
		        if(params[i].indexOf(name) >= 0){           
		            p = params[i].split('=');
		            p = p[1];         
		        }
		    }
		    return p;
		};
