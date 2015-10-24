<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="inc.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="edge" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>
<link href="${ctx}/style/main.css" rel="stylesheet" type="text/css" />
<script>

	var sessionInfo_userId = '${sessionInfo.id}';
	if (sessionInfo_userId) {//如果登录,直接跳转到index页面
		window.location.href='${ctx}/admin/index';
	}
		
	$(function() {
		$('#loginform').form({
		    url:'${ctx}/admin/login',
		    onSubmit : function() {
		    	progressLoad();
				var isValid = $(this).form('validate');
				if(!isValid){
					progressClose();
				}
				return isValid;
			},
		    success:function(result){
		    	result = $.parseJSON(result);
		    	progressClose();
		    	if (result.success) {
		    		window.location.href='${ctx}/admin/index';
		    	}else{
		    		$.messager.show({
		    			title:'提示',
		    			msg:'<div class="light-info"><div class="light-tip icon-tip"></div><div>'+result.msg+'</div></div>',
		    			showType:'show'
		    		});
		    	}
		    }
		});
	});
	function submitForm(){
		   var loginname =  $('#loginname').val();
	   var password =  $('#password').val();
	   if(loginname==''){
	   	  $.messager.show({
    			title:'提示',
    			msg:'<div class="light-info"><div class="light-tip icon-tip"></div><div>请输入用户名</div></div>',
    			showType:'show'
    		});
    	 return false;
	   }
	   if(password==''){
	   	  $.messager.show({
    			title:'提示',
    			msg:'<div class="light-info"><div class="light-tip icon-tip"></div><div>请输入密码</div></div>',
    			showType:'show'
    		});
    	 return false;
	   }
		$('#loginform').submit();
	}
	
	function clearForm(){
		$('#loginform').form('clear');
	}
</script>
</head>
<body>
	<div class="login">
    <div class="box png">
		<div class="logo png"></div>
		<div class="input">
			<div class="log">
			<form id="loginform"  method="post">
				<div class="name">
					<label>用户名</label><input type="text" class="text" id="loginname" placeholder="用户名" name="loginname" tabindex="1">
				</div>
				<div class="pwd">
					<label>密　码</label><input type="password" class="text" id="password" placeholder="密码" name="password" tabindex="2">
					<input type="button" class="submit" tabindex="3" value="登录" onclick="submitForm();">
					<div class="check"></div>
				</div>
				<div class="tip"></div>
				</form>
			</div>
		</div>
	</div>
    <div class="air-balloon ab-1 png"></div>
	<div class="air-balloon ab-2 png"></div>
    <div class="footer"></div>
</div>
	<!--[if lte IE 7]>
	<div id="ie6-warning"><p>您正在使用 低版本浏览器，在本页面可能会导致部分功能无法使用。建议您升级到 <a href="http://www.microsoft.com/china/windows/internet-explorer/" target="_blank">Internet Explorer 8</a> 或以下浏览器：
	<a href="http://www.mozillaonline.com/" target="_blank">Firefox</a> / <a href="http://www.google.com/chrome/?hl=zh-CN" target="_blank">Chrome</a> / <a href="http://www.apple.com.cn/safari/" target="_blank">Safari</a> / <a href="http://www.operachina.com/" target="_blank">Opera</a></p></div>
	<![endif]-->
	
	<style>
	/*ie6提示*/
	#ie6-warning{width:100%;position:absolute;top:0;left:0;background:#fae692;padding:5px 0;font-size:12px}
	#ie6-warning p{width:960px;margin:0 auto;}
	</style>
	<script type="text/javascript" src="${ctx}/jslib/fun.base.js"></script>
	<script type="text/javascript" src="${ctx}/jslib/script.js"></script>
	<script>
	jQuery(function ($) {
	 if ( jQuery.browser.msie && ( jQuery.browser.version == "6.0" )&& ( jQuery.browser.version == "7.0" ) && !jQuery.support.style ){
	  jQuery('#ie6-warning').css({'top':jQuery(this).scrollTop()+'px'});
	 }
	});
	</script>
	</body>
</html>