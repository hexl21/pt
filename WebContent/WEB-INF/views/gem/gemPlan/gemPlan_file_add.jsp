<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script type="text/javascript" src="${ctx}/jslib/plupload_1_5_7/plupload/js/plupload.full.js"></script>
<script type="text/javascript" src="${ctx}/jslib/plupload_1_5_7/plupload/js/i18n/zh-CN.js"></script>
<script type="text/javascript">
	var uploader;
	$(function() {
		uploader = new plupload.Uploader({
			browse_button : 'pickfiles',//选择文件的按钮
			container : 'container',//文件上传容器
			runtimes : 'html5,flash',//设置运行环境，会按设置的顺序，可以选择的值有html5,gears,flash,silverlight,browserplus,html4
			flash_swf_url : '${ctx}/jslib/plupload_1_5_7/plupload/js/plupload.flash.swf',// Flash环境路径设置
			silverlight_xap_url : '${ctx}/jslib/plupload_1_5_7/plupload/js/plupload.silverlight.xap',//silverlight环境路径设置
			url : '${ctx}/sysAttachment/save?id=${id}&tableName=GEM_PLAN',//上传文件路径
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
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false" >
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;" >
		<form id="gemPlanAddFileForm" action="${ctx}/sysAttachment/save?id=${id}&tableName=GEM_PLAN" method="post" enctype="multipart/form-data">
			<table class="grid">
					<td>
						<div id="container" class="fitem">
							<button id="pickfiles" name="pickfiles" >选择文件</button> 
							<a href="javascript:uploader.start()" class="easyui-linkbutton" data-options="iconCls:'icon-redo',plain:true" >开始上传</a>
							<a href="javascript:uploader.stop()" class="easyui-linkbutton" data-options="iconCls:'icon-redo',plain:true" >停止上传</a>
							<div id="filelist" class="fitem">您的浏览器没有安装Flash插件，或不支持HTML5！</div> </div> 
						    <input type="hidden" id="attachmentName" name="attachmentName" value="" />
						</div>
				</tr>
			</table>
		</form>
	</div>
</div>