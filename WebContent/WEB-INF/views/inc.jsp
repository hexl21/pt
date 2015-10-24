<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="edge" />
<link rel="shortcut icon" href="${ctx}/style/images/index/favicon.png" />
<!-- 引入my97日期时间控件 -->
<script type="text/javascript" src="${ctx}/jslib/My97DatePicker/WdatePicker.js" charset="utf-8"></script>

<!-- 引入jQuery -->
<script src="${ctx}/jslib/jquery-1.8.3.js" type="text/javascript" charset="utf-8"></script>
<!-- 引入js HashMap -->
<script src="${ctx}/jslib/HashMap.js" type="text/javascript" charset="utf-8"></script>

<!-- 引入EasyUI -->
<link id="easyuiTheme" rel="stylesheet" href="${ctx}/jslib/easyui1.3.3/themes/<c:out value="${cookie.easyuiThemeName.value}" default="default"/>/easyui.css" type="text/css">
<script type="text/javascript" src="${ctx}/jslib/easyui1.3.3/jquery.easyui.min.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/jslib/easyui1.3.3/locale/easyui-lang-zh_CN.js" charset="utf-8"></script>

<!-- portal -->
<script type="text/javascript" src="${ctx}/jslib/jquery.portal.js" charset="utf-8"></script>

<!-- 扩展EasyUI -->
<script type="text/javascript" src="${ctx}/jslib/extEasyUI.js" charset="utf-8"></script>

<!-- 扩展Jquery -->
<script type="text/javascript" src="${ctx}/jslib/extJquery.js" charset="utf-8"></script>

<!-- 自定义工具类 -->
<script type="text/javascript" src="${ctx}/jslib/lightmvc.js" charset="utf-8"></script>

<!-- 扩展EasyUI图标 -->
<link rel="stylesheet" href="${ctx}/style/lightmvc.css" type="text/css">

<!-- portal  -->
<link rel="stylesheet" href="${ctx}/style/portal.css" type="text/css">
<style>
#header{overflow:hidden;height:69px;background:#2076C3 url("../images/index/headbg.png") repeat-x;line-height:64px;color:#fff;font-family:Verdana,微软雅黑,黑体}

.header{background: url() no-repeat left; width: 600px; height: 64px; float: left;}

.header{background:width: 310px; height: 64px; float: left;}
body {
	font-size: 14px;
}
</style>
<script type="text/javascript">
$(window).load(function(){
	$("#loading").fadeOut();
});
//公共方法
var pubMethod = {
    bind: function (control,code) {
        if (control == ''|| code == '')
        {
            return;
        }

        $('#'+ control).combobox({
            url: '${ctx}/dictionary/combox?code=' + code,
            method: 'get',
            valueField: 'id',
            textField: 'text',
            editable: false,
            panelHeight: 'auto',
            required:true
        });
    }
}
function fixWidth(percent)  
{  
    return document.body.clientWidth * percent ; //这里你可以自己做调整  
} 
</script>
