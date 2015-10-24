<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<c:if test="${fn:contains(sessionInfo.resourceList, '/gemPlan/addFileList')}">
	<script type="text/javascript">
		var canAdd = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionInfo.resourceList, '/gemPlan/delFileList')}">
	<script type="text/javascript">
		var canDelete = true;
	</script>
</c:if>
<jsp:include page="../../inc.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title></title>

<script type="text/javascript">
var dataGrid;
	$(function() {
		dataGrid = $('#dataGrid').datagrid({
			url : '${ctx}/gemPlan/dataGridFile?id=${id}',
			rownumbers : true,
			pagination : true,
			singleSelect: true,					    //**********true只选一行记录
			selectOnCheck:false,                    //********false取消点击 复选框 同时选中 行记录
			idField : 'id',
			sortName : 'id',
			sortOrder : 'asc',
			frozenColumns : [ [ 
			{
				width : '100',
				title : 'id',
				field : 'id',
				hidden:true,
				sortable : true
			},{
				width : '100',
				title : 'id',
				field : 'id',
				hidden:true,
				sortable : true
			},{
				field : 'fileName',
				title : '文件名称',
				width : fixWidth(0.6)
			},{
				field : 'att',
				title : '文件',
				width : fixWidth(0.3),
				formatter : function(value, row, index) {
					   var id = row.id;
					   var fn = row.fileName;
					   var str = '&nbsp;';
					   if (canAdd) {
					   		str += $.formatString("<a href='javascript:void(0)' onclick='downLoadFileFun(\""+id+"\");' class='easyui-linkbutton'>下载</a>", row.id);
					   }
					   if (canDelete) {
						    str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
						   	str += $.formatString("<a href='javascript:void(0)' onclick='deleteFileFun(\""+id+"\");' class='easyui-linkbutton'>删除</a>", row.id);
					   }
					return str;
				}
			}
		    ] ],
			toolbar : '#toolbar'
		});
	});
	function downLoadFileFun(id){
		document.forms[0].action = '${ctx}/sysAttachment/get?id='+id;
		document.forms[0].submit();
	}
	function deleteFileFun(id){
		parent.$.messager.confirm('提示', '您是否要删除当前文件？', function(b) {
			if (b) {
				var currentUserId = '${sessionInfo.id}';/*当前登录用户的ID*/
				if (currentUserId != id) {
					progressLoad();
					$.post('${ctx}/sysAttachment/delete?id='+id,{},
					 function(result) {
						if (result.success) {
							parent.$.messager.alert('提示', result.msg, 'info');
							dataGrid.datagrid('reload');
						}
						progressClose();
					}, 'JSON');
				} else {
					parent.$.messager.show({
						title : '提示',
						msg : '不可以删除自己！'
					});
				}
			}
		});
	}
	</script>
</head>
<body  class="easyui-layout" data-options="fit:true,border:false" scroll="no">
<form method="post">
	<div data-options="region:'center',border:false">
		<table id="dataGrid" data-options="fit:true,border:false"></table>
	</div>
</form>
</body>
</html>