<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script type="text/javascript" src="${ctx}/jslib/plupload_1_5_7/plupload/js/plupload.full.js"></script>
<script type="text/javascript" src="${ctx}/jslib/plupload_1_5_7/plupload/js/i18n/zh-CN.js"></script>
<script type="text/javascript">
	var uploader;
	var url = "";
	$(function() {
	 uploader = new plupload.Uploader({
			browse_button : 'pickfiles',//选择文件的按钮
			container : 'container',//文件上传容器
			runtimes : 'html5,flash',//设置运行环境，会按设置的顺序，可以选择的值有html5,gears,flash,silverlight,browserplus,html4
			flash_swf_url : '${ctx}/jslib/plupload_1_5_7/plupload/js/plupload.flash.swf',// Flash环境路径设置
			silverlight_xap_url : '${ctx}/jslib/plupload_1_5_7/plupload/js/plupload.silverlight.xap',//silverlight环境路径设置
			url : '${ctx}/gemPlan/saveFile',//上传文件路径
			max_file_size : '3gb',//100b, 10kb, 10mb, 1gb
			//chunk_size : '1mb',//分块大小，小于这个大小的不分块
			unique_names : true,//生成唯一文件名
			// resize : { width : 320, height : 240, quality : 90 },
			// 指定要浏览的文件类型
			filters : [ {title : "文档", extensions : "zip,doc,docx,xls,xlsx,ppt,pptx,jpg,gif,png"} ]
		});
		uploader.bind('Init', function(up, params) {
			$('#filelist').html("");
		});
		uploader.bind('BeforeUpload', function(uploader, file) {
			$('.plupload_delete').hide();
		});
		uploader.bind('FilesAdded', function(up, files) {
			$.each(files, function(i, file) {
				$('#filelist').append('<div id="' + file.id + '">' + file.name + ' (' + plupload.formatSize(file.size) + ') <b></b>' + '&nbsp;<span onclick="uploader.removeFile(uploader.getFile($(this).parent().attr(\'id\')));$(this).parent().remove();" style="cursor: pointer;" class="plupload_delete"><img src="${ctx}/images/ext/no.png"/></span></div>');
			});
			up.refresh();
		});
		uploader.bind('UploadProgress', function(up, file) {
			$('#' + file.id + " b").html(file.percent + "%");
		});
		uploader.bind('Error', function(up, err) {
			$('#filelist').append("<div>Error: " + err.code + ", Message: " + err.message + (err.file ? ", File: " + err.file.name : "") + "</div>");
			up.refresh();
		});
		uploader.bind('FileUploaded', function(up, file, info) {
			$('#' + file.id + " b").html("100%");
			$('#attachmentName').attr('value', file.name);
		});
		uploader.init();
		$('#uploadfiles').click(function(e) {
			uploader.start();
			e.preventDefault();
		});
		 //专家
		// $('#zjUserIds').combotree({
		//    url: '${ctx}/user/zjTree',
		//    multiple: true,
		//    required: true,
		//    panelHeight : 'auto'
		//});
		
		 $('#zjUserIds').combogrid({
		    panelWidth: 450,
		    multiple: true,
			idField: 'id',
			textField: 'name',
			url : '${ctx}/user/dataGrid',
			loadMsg:'加载数据中...',
			fitColumns: true,
          	striped: true,
          	editable: true,
          	pagination: true,           //是否分页
          	rownumbers: true,           //序号
          	collapsible: false,         //是否可折叠的
          	fit: true,                  //自动大小
         	method: 'post',
			frozenColumns : [ [ {
				title : 'id',
				field : 'id',
				width : fixWidth(0.05),
				hidden : true
			} ] ],
			columns:[ [  {
				width : fixWidth(0.5),
				title : '姓名',
				field : 'name',
				sortable : true
			},{
				width : fixWidth(0.5),
				title : '所属部门',
				field : 'organizationName'
			}] ],
			fitColumns: true
			//toolbar : '#toolbar'
		});
		
		$('#organizationId').combotree({
			url : '${ctx}/organization/tree',
			parentField : 'pid',
			lines : true,
			panelHeight : 'auto'
		});
		$('#gemPlanAddForm').form({
			url : '${ctx}/gemPlan/add2',
			onSubmit : function() {
				var values = $('#zjUserIds').combogrid('getValues'); 
				var text = $('#zjUserIds').combogrid('getText'); 
				$('#zjUserNames').val(text);
				progressLoad();
				var isValid = $(this).form('validate');
				if (!isValid) {
					progressClose();
				}
				return isValid;
			},
			success : function(result) {
				progressClose();
				result = $.parseJSON(result);
				if (result.success) {
					uploader.settings.url = '${ctx}/sysAttachment/save?id='+result.str+"&tableName=GEM_PLAN";
					uploader.start();
					parent.$.messager.alert('提示', result.msg, 'info');
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为user.jsp页面预定义好了
					parent.$.modalDialog.handler.dialog('close');
				} else {
					parent.$.messager.alert('错误', result.msg, 'error');
				}
			}
		});
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false" >
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;" >
		<form id="gemPlanAddForm" method="post" enctype="multipart/form-data">
			<table class="grid">
				<tr>
					<td>标题</td>
					<td><input id="title" name="title" type="text" placeholder="请输入标题" class="easyui-validatebox span2" style="width: 300px; height: 26px;" data-options="required:true" value=""></td>
				</tr>
				<tr>
					<td>模型</td>
					<td>
						<select id = "gemModelBomId" name="gemModelBomId" class="easyui-combobox" data-options="width:200,height:29,editable:false,panelHeight:'auto'">
						<c:forEach items="${modelList}" var="modelList">
							<option value="${modelList.key}" >${modelList.value}</option>
						</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td>企业</td>
					<td>
					<select id="organizationId"  name="organizationId" placeholder="请选择企业"  class="easyui-validatebox span2" style="width: 200px; height: 29px;" data-options="required:true"></select>
					<input id ="organizationNames" name="organizationNames" type="hidden"/>
					</td>
				</tr>
				<tr>
					<td>专家</td>
					<td>
					<select id="zjUserIds"  name="zjUserIds" placeholder="请选择企业" class="easyui-combogrid" style="width:250px" data-options="required:true"></select>
					<input id ="zjUserNames" name="zjUserNames" type="hidden"/>
					</td>
				</tr>
				<tr>
					<td>文件</td>
					<td>
					 	<div id="container" class="fitem">
							<button id="pickfiles" name="pickfiles" >选择文件</button> 
							<div id="filelist" class="fitem">您的浏览器没有安装Flash插件，或不支持HTML5！</div> </div> 
						    <input type="hidden" id="attachmentName" name="attachmentName" value="" />
						</div>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>