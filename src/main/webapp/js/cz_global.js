var reUrl = window.location;
 $.ajaxSetup({ cache: false });
 var winURL = window.location.href;
 var ydqCode_text="请输入阅点劵号";
 var ydqPwd_text="请输入密码";
 var msgpay;
 var msgJson={"phone":'',"code":''};
 var payType=winURL.substring(winURL.lastIndexOf("/")+1, winURL.indexOf(".html"));;
 var payTypeText={'msgpay':'短信','bestpay':'翼支付','pointscard':'阅点券','mobilecard':'手机充值卡'}[payType] || '支付宝';
$(function(){
	initIndexRecharge();
});

/////////////////
/**充值中心首页按钮*/
function initIndexRecharge(){
	/*===== 充值按钮 =====*/
	$(".btn_indexRecharge").click(function(){
		$.ajax({
			url : _dynamicPath + "checkLogin.json",
			dataType : "json",
			type : "get",
			async : false,
			success : function(json) {
				if (json.login) {
					window.location= _frontPath_urlRewriter_baseUrl+'admin/manage.htm';
				} else{
					loginBox();
				}			
			}});
	});
}

function ydqInputInit(){

	$('#ydqCode').live('focus',function(){
		$(this).val()==ydqCode_text&&$(this).val('');
	}); 
	$('#ydqCode').live('blur',function(){
		$(this).val()==""&&$(this).val(ydqCode_text);
	});
	$('#ydqPwd').live('focus',function(){
		$(this).val()==ydqPwd_text&&$(this).val('');
	});
	$('#ydqPwd').live('blur',function(){
		$(this).val()==""&&$(this).val(ydqPwd_text);
	}); 
	//阅点券充值提交
		initSubmitYDQ();
	
	$(".viewDetail").live('click',function(){
		$(this).toggleClass('dirUp');
		$('.ydDetail').toggle();
	});
	$(".viewExpire").live('click',function(){
		$(this).toggleClass('dirUp');
		$('.ydExpireList').toggle();
	});
}
/**支付方式选择*/
function chargeTypeSelect(){
	
	if(winURL.indexOf("index.html")!=-1){
		$("#mainNav a:eq(0)").addClass("current").siblings("a").removeClass("current");
	}
	if(winURL.indexOf('charge/alipay.html')!=-1 ||winURL.indexOf('charge/msgpay.html')!=-1||
			winURL.indexOf('charge/bestpay.html')!=-1||winURL.indexOf('charge/pointscard.html')!=-1||winURL.indexOf('charge/shenzhoufu.html')!=-1){
		
		$("#mainNav a:eq(1)").addClass("current").siblings("a").removeClass("current");
		$('.aside_nav').find("dd."+payType).addClass("current").siblings("dd").removeClass("current");
		
		/*if(payType=='bestpay'){
			$('#money20').append("<b>送200阅点</b>");
			$('#money10').append("<b>送100阅点</b>");
			$('#money5').append("<b>送50阅点</b>");
		}
		*/
		
	
		var cjdescHTML = '<div class="hd_cj_desc">11月4日—12月25日<p>1.活动期间注册用户只要登陆www充值<span style="color:red">1</span>分钱，即可获得一次大转盘抽奖机会</p><p>2.用户单次充值每满<span style="color:red">5</span>元可增加一次抽奖机会，活动期间每个用户每天最多可获得6次抽奖机会<br/><a href="http://read.189.cn/newweb/choujiang/zp.html" target="_blank" style="color:red;">去抽金条</a></p></div>';
		$(".info_yd").find(".info_title").prepend(cjdescHTML);


	}else if(winURL.indexOf("myAccount.html")!=-1){
		$("#mainNav a:eq(2)").addClass("current").siblings("a").removeClass("current");
		$('.aside_nav').find("dd.account").addClass("current").siblings("dd").removeClass("current");
	}else if(winURL.indexOf("buyRecord")!=-1){
		$("#mainNav a:eq(2)").addClass("current").siblings("a").removeClass("current");
		$('.aside_nav').find("dd.order").addClass("current").siblings("dd").removeClass("current");
	}else if(winURL.indexOf("chargeRecord")!=-1){
		$("#mainNav a:eq(2)").addClass("current").siblings("a").removeClass("current");
		$('.aside_nav').find("dd.recharge").addClass("current").siblings("dd").removeClass("current");
	}else if(winURL.indexOf("show/account.html")!=-1||winURL.indexOf("show/order.html")!=-1){
		$("#mainNav a:eq(1)").addClass("current").siblings("a").removeClass("current");
		//$('.aside_nav').find("dd.recharge").addClass("current").siblings("dd").removeClass("current");
	}else if(winURL.indexOf('integration/detail')!=-1){
		$("#mainNav a:eq(3)").addClass("current").siblings("a").removeClass("current");
		$('.aside_nav').find(".consume").addClass("current").siblings("dd").removeClass("current");
	}else if(winURL.indexOf('integration/exchange.html')!=-1){
		$("#mainNav a:eq(3)").addClass("current").siblings("a").removeClass("current");
		$('.aside_nav').find(".exchange").addClass("current").siblings("dd").removeClass("current");
	}


}
function initSubmitRecharge(){
	
	$(".submitRecharge").live('click',function(){
		var money = $("input[name='money']:checked").val();
		var account = $("#account").data('account');
		if(money=='other'){
			var otherMoney =$.trim($("#otherMoney").val());
			if(otherMoney==''){
				otherMoney=1;
				$("#otherMoney").val("1");
			}
			if(!isMoney(otherMoney)){
				alert("金额输入不正确");
				$("#otherMoney").focus();
				return ;
			}
			
			money = otherMoney;
//			if(money<1){
//				alert("充值金额至少为1元");
//				return ;
//			}
		}
		
		if(winURL.indexOf("msgpay")!=-1){
		var phone = $("#sendPhoneNum").val();
			if(phone=="" || phone == '请输入您的手机号码'){
				alert("请输入您的手机号码");
				$("#sendPhoneNum").focus();
				return ;
			}
		
			if(!isMobile(phone)){
				alert("请输入正确的手机号码");
				$("#sendPhoneNum").focus();
				return;
			}
		
			if(!isCT(phone)){
				alert("请输入以133/153/180/181/189/1700/177开头的电信号码");
				$("#sendPhoneNum").focus();
				return;
			}
			msgJson.phone=phone;
		}
		
		var accountHTML = $("#account").html();
		//var payType = getParameter("payType");
		
		$('.stepsBox').find(".current").removeClass("current").next().next().addClass("current");
		var submitInfoHTML = "";
		 	submitInfoHTML += '<dl class="conList mb15 clearfix">'
								+'<dt>充值账号：</dt>'
								+'<dd data-account="'+account+'" id="account">'+accountHTML+'</dd>'
							  +'</dl>';

		 	submitInfoHTML += '<dl class="conList mb15 clearfix">'
								+'<dt>充值金额：</dt>'
								+'<dd><strong>'+money+'</strong>元</dd>'
							  +'</dl>';

			submitInfoHTML +='<dl class="conList mb15 clearfix">'
								+'<dt>合阅点数：</dt>'
								+'<dd><strong>'+(money*100+getAddition(payType,money))+'</strong>阅点</dd>'
							  +'</dl>';
			submitInfoHTML +='<dl class="conList mb15 clearfix">'
								+'<dt>充值方式：</dt>'
								+'<dd>'+payTypeText+'</dd>'
							  +'</dl>';
			submitInfoHTML +='<span id="formValue" data-account="'+account+'" data-money="'+money+'" data-paytype="'+payType+'"></span>';
		$(".infoList").html(submitInfoHTML);
		$(this).removeClass("submitRecharge").addClass("btn_submitPay submitPay");
	});
}
function initSubmitPay(){

	$(".submitPay").live("click",function(){
		var $formValue = $("#formValue");
		var account = $formValue.data("account");
		var	money = $formValue.data("money");
		var payTips='';
		var payBtns='';
			payTips="<p>支付完成前，请不要关闭此支付验证窗口。<br />支付完成后，请根据您支付的情况点击下面按钮。</p>";
			payBtns ='<a href="javascript:;" class="btn_style1 paySuccess">支付完成</a><a href="'+_frontPath_urlRewriter_baseUrl+'/help.html" class="btn_style2">支付出现问题</a>';
		if(payType=='msgpay'){
			var phone = msgJson.phone;
			var phone_s=phone.substring(0,3)+"****"+phone.substring(7,11);
			payTips='<p>您的阅点充值提示短信已经发送到您的手机<span class="c0024ff">'+phone_s+'</span>上,请根据短信提示回复。成功后请到"<a href="'+_frontPath_urlRewriter_baseUrl+'/manage/myAccount.html" class="c0024ff">我的帐户</a>"查看阅点余额。</p>';
			payBtns ='<a href="'+_frontPath_urlRewriter_baseUrl+'/show/account.html?account='+account+'" class="btn_style1 paySuccess">查看支付结果</a><a href="'+_frontPath_urlRewriter_baseUrl+'/help.html" class="btn_style2">支付出现问题</a>';
		}
		var url='';
		if(payType=='alipay'){
			url=_dynamicPath+"recharge/toAliPay.shtml?payType=alipay&money="+money;
		}else if(payType=='bestpay'){
			url=_dynamicPath+"recharge/toBestPay.shtml?payType=bestpay&money="+money;
		}else if(payType=='msgpay'){//短信支付
			url=_dynamicPath+"recharge/toMsgPay.shtml?payType=msgpay&phone="+msgJson.phone+"&money="+money+"&jsoncallback=?";
			}
		if(payType=='msgpay'){
			//ajax提交
			msgSubmitForAjax(url,payTips,payBtns);
		}else{
			confirmBox('支付提示',payTips,payBtns,314);
			window.open(url,'_blank');
		}
	});
}
function msgSubmitForAjax(url,payTips,payBtns){
	$.getJSON(url,function(res){
		if(res.desc=='success'){
			confirmBox('支付提示',payTips,payBtns,314);
		}else{
			alert(res.desc);
		}
	});
}
function initPaySuccess(){
	//支付完成
	$(".paySuccess").live("click",function(){
		closeWinBox();
		$('.stepsBox').find(".step:last").addClass("current").siblings().removeClass('current');
		$(".stepsMain").find(".btn_submit,button").remove();
		window.location.href=_frontPath_urlRewriter_baseUrl+"/show/account.html?account="+ $("#account").data('account');
	});

	

}
function initSubmitMsg(){
	//短信充值按钮
	$(".btn_submitBind").live('click',function(){
		var phone = $("#bindPhone").find("#sendPhoneNum").val();
		var VerificationCode = $("#bindPhone").find("#VerificationCode").val();
		
		$.ajax({
			url : _dynamicPath + "recharge/sendMsg_send.shtml?phone="+phone+"&code="+VerificationCode+"&jsoncallback=?",
			dataType : "jsonp",
			type : "get",
			async : false,
			success : function(res) {
				if (res.success) {
					$("#account").find("span").remove();
					$("#account").append('<span>（短代支付号码：'+res.phone+'  <a href="javascript:;" class="btnchangeBind">替换</a> ）</span>');
					 msgJson.phone=phone;
					 msgJson.code=VerificationCode;
					$(this).parents("#bindPhone").remove();
					closeWinBox();
				} else{
					alert(res.message);
				}
				
			}});
		
	});
}
function initSubmitYDQ(){
	//阅点券充值提交
	$('.submitYDQ').live('click',function(){
		var ydqCode = $('#ydqCode').val();
		var ydqPwd = $('#ydqPwd').val();
		if(ydqCode==''||ydqCode==ydqCode_text){
			alert('请输入阅点券号');
			$('#ydqCode').focus();
			return false;
		}
		if(ydqPwd==''||ydqPwd==ydqPwd_text){
			alert('请输入密码');
			$('#ydqPwd').focus();
			return false;
		}else{
			
			var url=_dynamicPath+"recharge/charge_recharge.shtml?cardNumber="+ydqCode+"&cardPassWord="+ydqPwd+"&jsoncallback=?";
			$.getJSON(url,function(res){
				if(res.desc=='success'){
					$('.stepsBox').find(".step:last").addClass("current").siblings().removeClass('current');
					$(".stepsMain").find(".btn_submit,button").remove();
					window.location.href=_frontPath_urlRewriter_baseUrl+"/show/account.html?account="+res.account;
				}else{
					alert(res.result);
				}
			});
			//window.location.href=_dynamicPath+"recharge/charge_recharge.shtml?cardNumber="+ydqCode+"&cardPassWord="+ydqPwd;
	    
		}

	});
}


function initSubmitSZF(){
	$('.submitSZF').live('click',function(){
		var money = $("input[name='money']:checked").val();
		var account = $("#account").data('account');
		if(money=='other'){
			var otherMoney =$.trim($("#otherMoney").val());
			if(otherMoney==''){
				otherMoney=1;
				$("#otherMoney").val("1");
			}
			if(!isMoney(otherMoney)){
				alert("金额输入不正确");
				$("#otherMoney").focus();
				return ;
			}
			money = otherMoney;
//			if(money<1){
//				alert("充值金额至少为1元");
//				return ;
//			}
		}
		
		var rechargeCardType = $('#rechargeCardType').val();
		var rechargeCardCode = $('#rechargeCardCode').val();
		var rechargeCardPwd = $('#rechargeCardPwd').val();
		if(rechargeCardType == ''||rechargeCardType=='null'){
			alert('请选择充值卡类型');
		}else if(rechargeCardCode==''||rechargeCardCode=='null'){
			alert('请输入充值卡序列号');
			$('#rechargeCardCode').fosuc();
		}else if(rechargeCardPwd==''||rechargeCardPwd=='null'){
			alert('请输入密码');
			$('#rechargeCardCode').focus();
		}else{
			//alert(money+'\n'+account+'\n'+rechargeCardType+'\n'+rechargeCardCode+'\n'+rechargeCardPwd )
			
			//window.open(_dynamicPath+"recharge/toSzfPay.shtml?cardTypeCombine="+rechargeCardType+"&cardSerial="+rechargeCardCode+"&cardPwd="+rechargeCardPwd+"&money="+money,'_blank');
		
			var url=_dynamicPath+"recharge/toSzfPay.shtml?cardTypeCombine="+rechargeCardType+"&cardSerial="+rechargeCardCode+"&cardPwd="+rechargeCardPwd+"&money="+money+"&jsoncallback=?";
			$.getJSON(url,function(res){
				if(res.desc!='success'){
					alert(res.desc);
				}else{
					$("#desc").empty().text("处理中,请稍候...");
					$(".stepsMain").find(".btn_submit,button").remove();
					//setTimeOut(function(){
						window.location.href=_frontPath_urlRewriter_baseUrl+"/show/szfShow.html?orderNo="+res.orderNo;
					//},1000);
					
				}
			});
		}
	});
}
function queryOrderStatus(){
	$.getJSON(url,function(res){
		if(res.desc){
			clearInterval(id);
			window.location.href=_frontPath_urlRewriter_baseUrl+"/show/order.html?orderNo="+orderNo;
		}
	});
}
/* 弹窗 */
 function winBox(c,w,h){

 	w = w||400; 
 	$('.winBox,.mask').remove();
	$(document.body).append('<div class="winBox">'+c+'</div><div class="mask"></div>');
	$(".winBox").css({"width":w+'px',"height":h+'px'})	;
	$(".winBox").css({"top":parseInt($(window).height()/2+parseInt($(window).scrollTop())-$(".winBox").height()/2)+"px","margin-left":parseInt(-$(".winBox").width()/2)+"px"});
	$(".mask").css({'height':$(document).height()});
	$('.winBox, .mask').fadeIn();
	return false;
}

function closeWinBox(){
	$('.winBox,.mask').remove();
}
/* End 弹窗 */

/*===== 确认弹窗 =====*/
function confirmBox(title,content,btns,w,h){
	var confirmHTML = '<div id="winBox_confirm">'
						+'<div class="winBox_title">'+title+'</div>'
						+'<div class="winBox_content">'+ content +'</div>'
						+'<div class=winBox_btnBox>'+btns+'</div>'
						+'<span class="winBox_close" onclick="closeWinBox();">×</span>'
					 +'</div>';
	winBox(confirmHTML,w,h);
}

/*===== 发送验证码 =====*/
function client_send(id,type,obj){
	var _secNum	= 60;
	var leftTimer;
	var _this=$(obj);
	var _telID=$("#"+id);
	var phone = _telID.val();
			if(phone=="" || phone == '请输入您的手机号码'){
			alert("请输入您的手机号码");
			return;
		}
		
		if(!isMobile(phone)){
			alert("请输入正确的手机号码");
			return;
		}
		
		if(!isCT(phone)){
			alert("请输入以133/153/180/181/189/1700/177开头的电信号码");
			return;
		}
		$.getJSON(_dynamicPath+"recharge/sendMsg_code.shtml?type="+type+"&phone="+phone+"&jsoncallback=?",function(res){
			if(res.success == true){
				leftTimer=setInterval(function(){
					  	_telID.attr("disabled", "disabled");
	    				_this.attr("disabled", "disabled").val(_secNum);
					 if(_secNum<=0){
						  _this.removeAttr("disabled", "disabled");
						  _telID.removeAttr("disabled", "disabled");
						   _this.parent().find(".recendTips").remove();
						  _this.val("重新发送");
						  
						  clearInterval(leftTimer);
						  return;
					  }
					  _secNum--;

				}, 1000);

				 _this.parent().find(".recendTips").remove();
				 _this.parent().append('<span class="recendTips"><em>地址已发送到您的手机，请注意查收！</em>如果1分钟内没有收到信息，请点击重新发送或咨询在线客服！</span>');
				
			}
			else{
				alert(res.message);
			}
		});
	}

// 校验手机号
function isMobile(s) {
    var patrn = /(^1[3|4|5|6|7|8|9][0-9]{9}$)/;
    if (!patrn.exec(s)) {
        return false;
    }
    return true;
} 
//* 校验电信手机号*请不要引用此方法@see util.js isCT(v)
function isCT(v){
	var reg = /^(133|153|181|180|189|177)\d{8}|(1700)\d{7}$/;
	if (!reg.test(v)) {
        return false;
    }
    return true;
}

function isMoney(m){
	var reg = /^([1-9][\d]{0,7}|0)(\.[\d]{1,2})?$/;
	if (!reg.test(m)) {
        return false;
    }
	return true;
}
/**
 * payType:充值方式
 * money:充值金额，单位元
 * */
function getAddition(payType,money){
	var addition=0;
/*	if(payType=='bestpay'){
		if(money==20){
			addition=200;
		}else if(money==10){
			addition=100;
		}else if(money==5){
			addition=50;
		}
	}
	*/
	return addition;
}

/**p-用户积分，y-兑换积分,规则编号*/
function redeemNow(y,reluesId){
	var p=parseInt($("#score").text());
	var redeemBtns ='<a href="javascript:;" class="btn_style1" onclick="closeWinBox();">确定</a>';
	var redeemText='';
	
	if(p<y){
		redeemText = '<p class="oneLineText">积分不足，请兑换小于或等于您积分的物品！</p>';
		confirmBox('积分兑换',redeemText,redeemBtns);
		
	}else{
		$.getJSON(_dynamicPath+"recharge/exchange.shtml?jsoncallback=?",{reluesId:reluesId},function(data){
			if(data=='1000'){
				redeemText = '<p class="oneLineText">兑换成功!</p>';
				//更新阅读，积分
				getBalance();
			}else{
				redeemText = '<p class="oneLineText">兑换失败!</p>';
			}
			confirmBox('积分兑换',redeemText,redeemBtns);
			
		});
	}
	

}

function getBalance(){
	$.getJSON(_dynamicPath+"recharge/balance.shtml?jsoncallback=?",function(res){
		var html="";
			if(res.isLogin){
				html="您的阅点余额：<strong>"+res.balance+"</strong>&nbsp;&nbsp;积分：<strong>"+res.integral+"</strong>";
			}
		$("#balance").empty().html(html);
	});
	
}
function getFocus(obj){
	$(".input_radio:eq(4)").attr("checked",true);
}
function recharge(payType){
	$.ajax({
		url : _dynamicPath + "user/checkLogin.shtml?jsoncallback=?",
		dataType : "jsonp",
		type : "get",
		async : false,
		success : function(json) {
			var res = eval('(' + json + ')');
			if (res.login) {
				window.location= _frontPath_urlRewriter_baseUrl+'/charge/'+payType+'.html';
			} else{
				loginBox();
			}
			
		}});
}
