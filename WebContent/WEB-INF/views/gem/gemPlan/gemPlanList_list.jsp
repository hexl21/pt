<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../../inc.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="edge" />
<c:if test="${fn:contains(sessionInfo.resourceList, '/gemModelBom/edit')}">
	<script type="text/javascript">
		$.canEdit = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionInfo.resourceList, '/gemModelBom/delete')}">
	<script type="text/javascript">
		$.canDelete = true;
	</script>
</c:if>
<title>模型管理</title>

<script type="text/javascript">
	var treeGrid;
	$(function() {
		treeGrid = $('#treeGrid').treegrid({
			url : '${ctx}/gemPlan/detailTreeGrid?id=${id}',
			idField : 'id',
			treeField : 'gemModelBomName',
			parentField : 'pid',
			fit : true,
			fitColumns : false,
			border : false,
			frozenColumns : [ [ {
				title : 'id',
				field : 'id',
				width : 150,
				hidden : true
			} ] ],
			columns : [ [ {
				field : 'gemModelBomName',
				title : '指标',
				width : fixWidth(0.5)
			},{
				field : 'value',
				title : '权重',
				width : fixWidth(0.1),
				sortable : true,
				formatter : function(value, row, index) {
					if(value!=null){
						var str = (value+"");
						if(str.substring(0,1)=='.'){
							str = "0"+str;
						}
						return str;
					}
				}
			}, {
				field : 'score',
				title : '分值',
				width : fixWidth(0.2),
				formatter : function(value, row, index) {
					if(value!=null){
						var str = (value+"");
						if(str.substring(0,1)=='.'){
							str = "0"+str;
						}
						return str;
					}
				}
			}, {
				field : 'statusName',
				title : '状态',
				width : fixWidth(0.18)
			}
			] ],
			toolbar : '#toolbar'
		});
	});
	</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'center',border:false"  style="overflow: hidden;">
			<table id="treeGrid"></table>
		</div>
	</div>
</body>
</html>