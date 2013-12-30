/**
 * 判断为数字
 */
function isNumber(n){
	return /^[0-9]{1,}$/.test(n);
}

/**
 * 获取页面参数
 */
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
}

//校验电信手机号
function isCT(v){
	var reg = /^(133|153|181|180|189|177)\d{8}|(1700)\d{7}$/;
	if (!reg.test(v)) {
        return false;
    }
    return true;
}

// 校验手机号
function isMobile(s) {
    var patrn = /(^1[3|4|5|6|7|8|9][0-9]{9}$)/;
    if (!patrn.exec(s)) {
        return false;
    }
    return true;
}