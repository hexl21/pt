<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<jsp:include page="../../inc.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="edge" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/table.css">
<title>指标得分统计</title>
	<script type="text/javascript">
	function createTable(){
		progressLoad();
	//var s = "网页可见区域宽 ："+ document.body.clientWidth;  
	//	s += "\r\n网页可见区域高："+ document.body.clientHeight;   
	//	s += "\r\n网页可见区域高："+ document.body.offsetHeight +" (包括边线的宽)";  
	//	s += "\r\n网页正文全文宽："+ document.body.scrollWidth;  
	//	s += "\r\n网页正文全文高："+ document.body.scrollHeight;  
	//	s += "\r\n网页被卷去的高："+ document.body.scrollTop;  
	//	s += "\r\n网页被卷去的左："+ document.body.scrollLeft;  
	//	s += "\r\n网页正文部分上："+ window.screenTop;  
	//	s += "\r\n网页正文部分左："+ window.screenLeft;  
	//	s += "\r\n屏幕分辨率的高："+ window.screen.height;  
//	s += "\r\n屏幕分辨率的宽："+ window.screen.width;  
	//	s += "\r\n屏幕可用工作区高度："+ window.screen.availHeight;  
	//	s += "\r\n屏幕可用工作区宽度："+ window.screen.availWidth;  
	//	alert(s); 
 	//清空div内容
 	var height = ($("body").height()-50)+'px';
    var div_t = $("#top2");
    	div_t.html("");
    var div_c = $("#content");
    	div_c.css("height",height);
    	div_c.css("overflow","auto");
    	div_c.html("");
	 $.ajax({
	   type: "POST",
	   url: "${ctx}/gemPlan/gemPlanListSummary_data?id=${id}",
	   data: "",
	   success: function(data){
	      var jsonData = eval(data);
	     var table = $("<table id=\"tt\" border =\"0\" class=\"tableTop\" >");
	     //初始化表头
	     var trTh = $("<tr height=\"30\" class=\"tableTh\" ></tr>");
	 	 	 trTh.appendTo(table);	
		 var arr = jsonData[0]+"";
		 var str = arr.split(",");
		 for(var i=0;i<str.length;i++){
	 		 var td ="<td height=\"30\" "; 
	 		 	 i%2==0? td+="width=\"200\"":td+="width=\"50\"";
	 		 	 td+="class=\"tableTopTh\" >"+str[i]+"</td>";
	 	 		 $(td).appendTo(trTh);
		 }	 
		 table.appendTo(div_t);
	 	 //内容
	 	  var table2 = $("<table id=\"tt2\" border =\"0\" class=\"table\" >");
		 for(var j = 1; j<jsonData.length;j++){
		 	 var tr = $("<tr height=\"30\" ></tr>");
		 	 	 tr.appendTo(table2);
			 var arr = jsonData[j]+"";
			 var str = arr.split(",");
			  for(var k=0;k<str.length;k++){
		 	 	var td = "<td height=\"30\" ";
		 	 		k%2==0?td+=" width=\"200\"":td+="width=\"50\"";
		 	 		k%2==0?td+=" style=\"background-color:#edf7f9;\" ":"";
		 	 	    td+=">"+str[k]+"</td>";
		 	 	    $(td).appendTo(tr);
		 	 }
	 	  }
	 	  table2.appendTo(div_c);
	 	  mergeTableCells("tt2");
	 	  progressClose();
	 	 }
	});
 }
 	function mergeTableCells(tableId){
 		  var temp = new Array();
 		  var temp_row = new Array();
 		  var flag = 0;
 		  $('#'+tableId).find('tr').each(function (i) {  
            $(this).find('td').each(function (j) {  
               var value = $(this).text();
               if(i==0){
               		temp[j] = value;
               		temp_row[j] = i;
               }else{
               	  if(j%2==0){
	               	  	if(value==temp[j]){
							mergeCells("tt2",temp_row[j],i,j,j);
							flag = 1;
						}else{
							temp[j]=value;
							temp_row[j] = i;
						}
               	  }else{
	               	  	if(flag == 1){
								mergeCells("tt2",temp_row[j],i,j,j);
								flag = 0;
						}else{
								temp[j]=value;
								temp_row[j] = i;
						}
               	  }
               }
            }); 
        }); 
 	}
	//合并表格方法 tableId ：
	//begin_row:开始行
	//end_row:结束行
	//begin_col:开始列
	//end_col:结束列
	function mergeCells(tableId,begin_row,end_row,begin_col,end_col){
		for(var i=begin_row;i<=end_row;i++){
		   if(end_col-begin_col>0){
		   		for(var j=begin_col;j<=end_col;j++){
		   			if(j==begin_col){
		   				$("#"+tableId+" tr:eq("+i+") td:eq("+j+")").attr("colspan",end_col-begin_col+1);
		   			}else{
		   				$("#"+tableId+" tr:eq("+i+") td:eq("+j+")").hide();
		   			}
		   		}
		   }
		   if(end_row-begin_row>0){
		   		if(i==begin_row){
		   			$("#"+tableId+" tr:eq("+i+") td:eq("+begin_col+")").attr("rowspan",end_row-begin_row+1);
		   		}else{
		   			$("#"+tableId+" tr:eq("+i+") td:eq("+begin_col+")").hide();
		   		}
		   	 
		   }
		}
	}
</script>
<style type="text/css">  
#container {margin:0 auto; overflow:hidden;width:100%;}  
#top{height:26px;width:100%;} 
#top2{height:36px;width:100%;overflow-y: scroll;}  
body{
	background: url('<%=request.getContextPath()%>/images/bg/bg.jpg');
	overflow: hidden;
}
</style> 
</head>
<body onload="createTable();" >
<div data-options="region:'center',border:false">
<div id = "container" >
	<center>
	<div id="top">
		<span style="font-family:宋体;color:blue;font-size: 20px;font-weight: bold;">指标得分统计</span> 
	</div>
	<div id="top2"></div>
	<div id="content">  
	 </div>  
	</center>
 </div>
 </div>
</body>
</html>