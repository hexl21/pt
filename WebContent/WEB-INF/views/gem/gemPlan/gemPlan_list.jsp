<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../../inc.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="edge" />
<c:if test="${fn:contains(sessionInfo.resourceList, '/gemPlan/gem_add')}">
	<script type="text/javascript">
		$.canAdd = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionInfo.resourceList, '/gemPlan/gem_delete')}">
	<script type="text/javascript">
		$.canDelete = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionInfo.resourceList, '/gemPlan/addFileList')}">
	<script type="text/javascript">
		$.addFileList = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionInfo.resourceList, '/gemPlan/fileListQuery')}">
	<script type="text/javascript">
		$.fileListQuery = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionInfo.resourceList, '/gemPlan/toGemPlanListDetail')}">
	<script type="text/javascript">
		$.canDetail = true;
	</script>
</c:if>
<title>制定评分计划</title>
	<script type="text/javascript">
	var dataGrid;
	$(function() {
		dataGrid = $('#dataGrid').datagrid({
			url : '${ctx}' + '/gemPlan/dataGrid?gemPlanHeadId=${gemPlanHeadId}',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect: true,					    //**********true只选一行记录
			selectOnCheck:false,                    //********false取消点击 复选框 同时选中 行记录
			nowrap:false,
			idField : 'id',
			sortName : 'id',
			sortOrder : 'asc',
			pageSize : 50,
			pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
		//frozenColumns属性固定没有滚动条
		//columns：有规定条
			frozenColumns : [ [ 
			{checkbox:true}
			,
			{
				width : '100',
				title : 'id',
				field : 'id',
				hidden:true,
				sortable : true
			}
			, {
				width : fixWidth(0.2),
				title : '标题',
				field : 'title',
				sortable : true
			} ,  
			{
				width : fixWidth(0.2),
				title : '模型',
				field : 'gemModelBomName',
				sortable : true
			} 
		    ] ],
		    columns:[[
		    {
				width : fixWidth(0.15),
				title : '企业',
				field : 'organizationName',
				sortable : true
			} ,{
				width : fixWidth(0.15),
				title : '专家',
				field : 'zjUserNames',
				sortable : true,
				formatter : function(value, row, index) {
					var str = '&nbsp;';
					str += $.formatString('<a href="javascript:void(0)" onclick="openZjPfFrame(\'{0}\');" >'+value+'</a>', row.id);
					return str;
				}
			} , {
				width : fixWidth(0.06),
				title : '分值',
				field : 'score',
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
			} ,{
				field : 'att',
				title : '附件',
				width : fixWidth(0.1),
				formatter : function(value, row, index) {
					   var str = '&nbsp;';
					   if($.addFileList){
							str += $.formatString('<a href="javascript:void(0)" onclick="addFileFun(\'{0}\');" >添加</a>', row.id);
							str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
					   }
					   if($.fileListQuery){
							str += $.formatString('<a href="javascript:void(0)" onclick="openFileListFrame(\'{0}\');" >查看</a>', row.id);
					   }
					return str;
				}
			},{
				field : 'action',
				title : '指标信息',
				width : fixWidth(0.08),
				formatter : function(value, row, index) {
					var str = '&nbsp;';
					if ($.canDetail) {
						//str += $.formatString('<a href="javascript:void(0)" onclick="detailFun(\'{0}\');" >查看</a>', row.id);
						str += $.formatString('<a href="javascript:void(0)" onclick="openFrame(\'{0}\');" >查看</a>', row.id);
					}
					return str;
				}
			}
			]],
			toolbar : '#toolbar'
		});
	});
	
	function addFun() {
		parent.$.modalDialog({
			title : '添加',
			width : 500,
			height : 350,
			href : '${ctx}/gemPlan/addPage',
			buttons : [ {
				text : '添加',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#gemPlanAddForm');
					f.submit();
				}
			} ]
		});
	}
	function addFileFun(id) {
		parent.$.modalDialog({
			title : '添加文件',
			width : 400,
			height : 250,
			href : '${ctx}/gemPlan/addFilePage?id='+id,
			buttons : [ {
				text : '返回',
				handler : function() {
					parent.$.modalDialog.handler.dialog('close');
				}
			} ]
		});
	}
	function deleteFun() {
		var checked = $('#dataGrid').datagrid('getChecked');
			if(checked[0]==null){
		    	$.messager.alert('提示','请选择一条记录！','info'); 
		    	return false;
			}	
		var id = checked[0].id;
		var ids = "";
		for(var i = 0;i<checked.length;i++){
			ids!=null&&ids!=''?ids+="@"+checked[i].id:ids=checked[i].id;
		}
		parent.$.messager.confirm('询问', '您是否要删除？', function(b) {
			if (b) {
				var currentUserId = '${sessionInfo.id}';/*当前登录用户的ID*/
				if (currentUserId != id) {
					progressLoad();
					$.post('${ctx}/gemPlan/delete?id='+ids, {},
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
	function calculateFun() {
		var checked = $('#dataGrid').datagrid('getChecked');
			if(checked[0]==null){
		    	$.messager.alert('提示','请选择一条记录！','info'); 
		    	return false;
			}
			if(checked.length > 1){
		    	$.messager.alert('提示','只能选择一条记录进行计算！','info'); 
		    	return false;
			}
		var id = checked[0].id;
		var ids = "";
		for(var i = 0;i<checked.length;i++){
			ids!=null&&ids!=''?ids+="@"+checked[i].id:ids=checked[i].id;
		}
		parent.$.messager.confirm('询问', '您是否要计算？', function(b) {
			if (b) {
				var currentUserId = '${sessionInfo.id}';/*当前登录用户的ID*/
				if (currentUserId != id) {
					progressLoad();
					$.post('${ctx}/gemPlan/calculateZdScore?ids='+ids, {},
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
	function openFileFun() {
		var checked = $('#dataGrid').datagrid('getChecked');
			if(checked[0]==null){
		    	$.messager.alert('提示','请选择一条记录！','info'); 
		    	return false;
			}
			if(checked.length > 1){
		    	$.messager.alert('提示','只能选择一条记录！','info'); 
		    	return false;
			}
		var id = checked[0].id;
		openFileListFrame(id);
	}
	
	function searchFun() {
		//$('#dataGrid').datagrid('loadData', { total: 0, rows: [] }); 清理dataGrid数据集
		dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
	}
	function cleanFun() {
		$('#searchForm input').val('');
		dataGrid.datagrid('load', {});
	}
    function openFrame(id) {
	    $('#dd').empty();
	    //$('#dd').append("<iframe frameborder='0' width='100%' height='100%' src='${ctx}/gemPlan/toGemPlanListDetail?id="+id+"' ></iframe>");
	    //$('#dd').append("<iframe frameborder='0' width='100%' height='100%' src='${ctx}/gemPlan/toGemPlanListDetailTable?id="+id+"' ></iframe>");
	    $('#dd').append("<iframe frameborder='0' width='100%' height='100%' src='${ctx}/gemPlan/gemPlanListSummary_list?id="+id+"' ></iframe>");
	    $('#dd').window('refresh');
        $('#dd').window('open');
    }
    function openFileListFrame(id) {
     	$('#df').empty();
        $('#df').append("<iframe frameborder='0' width='100%' height='100%' src='${ctx}/gemPlan/toListFilePage?id="+id+"&r="+Math.random()+"' ></iframe>");
        $('#df').window('refresh');
        $('#df').window('open');
    }
    function openZjPfFrame(id) {
     	$('#zj').empty();
        $('#zj').append("<iframe frameborder='0' width='100%' height='100%' src='${ctx}/gemPlanListUser/gemPlanListUserSummary_list?id="+id+"' ></iframe>");
        $('#zj').window('refresh');
        $('#zj').window('open');
    }
    function exportDatas(){
    	var checked = $('#dataGrid').datagrid('getChecked');
			if(checked[0]==null){
		    	$.messager.alert('提示','请选择一条记录！','info'); 
		    	return false;
			}
			if(checked.length > 1){
		    	$.messager.alert('提示','只能选择一条记录进行导出！','info'); 
		    	return false;
			}
		var id = checked[0].id;
    	window.location.href = "${ctx}/gemPlan/exportSummaryList?id="+id;
    }
	</script>
<style>
	 /**
     .datagrid-header-row td{background-color:blue;color:#fff}
	 */
    .datagrid-row {height: 32px;}
</style>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',border:false" style="height: 30px; overflow: hidden;background-color: #f4f4f4">
		<form id="searchForm">
			<table>
				<tr>
					<th>标题:</th>
					<td>
					<td><input name="title" placeholder="请输入标题"/></td>
					</td>
					<th>模型:</th>
					<td>
						<select id="gemModelBomId" name="gemModelBomId" class="easyui-combobox" data-options="width:184,height:20,editable:false,panelHeight:'auto'">
							<option value="">--请选择--</option>
						<c:forEach items="${modelList}" var="modelList">
							<option value="${modelList.key}" >${modelList.value}</option>
						</c:forEach>
						</select>
					</td>
					<td>
						<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon_search',plain:true" onclick="searchFun();">查询</a>
						<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon_cancel',plain:true" onclick="cleanFun();">清空</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div data-options="region:'center',border:false,title:'数据列表'" >
		<table id="dataGrid" data-options="fit:true,border:false"></table>
	</div>
	<div id="toolbar" style="display: none;">
			<c:if test="${fn:contains(sessionInfo.resourceList, '/gemPlan/gem_add')}">
			<a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon_add'">添加</a>
		</c:if>
		<c:if test="${fn:contains(sessionInfo.resourceList, '/gemPlan/gem_delete')}">
			<a onclick="deleteFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'edit_remove'">删除</a>
		</c:if>
		<c:if test="${fn:contains(sessionInfo.resourceList, '/gemPlan/gem_jisuan')}">
			<a onclick="calculateFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon_jisuan'">计算</a>
		</c:if>
<%--			<a onclick="exportDatas();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon_jisuan'">导出</a>--%>
<%--			--%>
<%--			<a onclick="openFileFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon_jisuan'">附件</a>--%>
<%--			--%>
<%--			<span style="font-weight: 600;"><font color="red">提示：1.导出可以导出计算结果 2.评价标准在附件里</font></span>--%>
	</div>
	<div id="dd" title="指标详细信息"  class="easyui-window" fit="true" minimizable='false' maximizable='false' modal='true' closed="true" style="width:800px;height:400px;padding:5px;overflow: hidden;"></div> 
	<div id="df" title="附件信息" class="easyui-window" fit="false" modal='true' minimizable='false' maximizable='false' closed="true" style="width:450px;height:350px;padding:5px;overflow: hidden;"></div> 
	<div id="zj" title="专家评分信息" class="easyui-window" fit= "true" modal='false' minimizable='false' maximizable='false' closed="true" style="width:450px;height:350px;padding:5px;overflow: hidden;"></div> 
</body>
</html>