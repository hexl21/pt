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
	function createTable(){
	 $.ajax({
	   type: "POST",
	   url : '${ctx}/gemPlan/detailTableGrid?id=${id}',
	   data: "",
	   success: function(data){
	   
	     var jsonData = eval(data);
	     var tdname1="null";
	     var tdname2="null";
	     var tdname3="null";
	     $.each(jsonData, function(index, objVal) { //遍历对象数组，index是数组的索引号，objVal是遍历的一个对象。    
	       
	        var value1 = objVal["value"];
	        var gemModelBomName1 = objVal["gemModelBomName"];
	        var gemModelBomId1 = objVal["gemModelBomId"];
	        var ct1 = objVal["ct"];
	        
	        var value2 = objVal["value2"];
	        var gemModelBomName2 = objVal["gemModelBomName2"];
	        var gemModelBomId2 = objVal["gemModelBomId2"];
	        var ct2 = objVal["ct2"];
	        
	        var value3 = objVal["value3"];
	        var gemModelBomName3 = objVal["gemModelBomName3"];
	        var gemModelBomId3 = objVal["gemModelBomId3"];
	        var ct3 = objVal["ct3"];
	        
	        var value4 = objVal["value4"];
	        var gemModelBomName4 = objVal["gemModelBomName4"];
	        var gemModelBomId4 = objVal["gemModelBomId4"];
	        var ct4 = objVal["ct4"];
	        
	       
	        var table = document.all("tableGrid");
	       	var row_00 = table.insertRow(-1);// 在最下的位置
	       	row_00.align = 'center';
	       
	       	var cell1 = row_00.insertCell();
			var cell2 = row_00.insertCell();
			var cell3 = row_00.insertCell();
			var cell4 = row_00.insertCell();
			var cell5 = row_00.insertCell();
			var cell6 = row_00.insertCell();
			var cell7 = row_00.insertCell();
			var cell8 = row_00.insertCell();
			
			
			cell1.innerHTML=gemModelBomName1;
			cell2.innerHTML=value1;
			
			
			
		
			cell3.innerHTML=gemModelBomName2;
			cell4.innerHTML=value2;
			
			
			
			cell5.innerHTML=gemModelBomName3;
			cell6.innerHTML=value3;
			
			
			cell7.innerHTML=gemModelBomName4;
			cell8.innerHTML=value4;
	      }); 
	      autoRowSpan(tableGrid,0,0);
	     
	      
	   }
	}
	
	);
	
	
	}
	
	
	function autoRowSpan(tb,row,col) 
{ 

var lastValue=""; 
var value=""; 
var pos=1; 
for(var i=row;i<tb.rows.length;i++){ 
value = tb.rows[i].cells[col].innerText; 

if(lastValue == value){ 
tb.rows[i].deleteCell(col); 
tb.rows[i-pos].cells[col].rowSpan = tb.rows[i-pos].cells[col].rowSpan+1; 
tb.rows[i].deleteCell(col+1); 
tb.rows[i-pos].cells[col+1].rowSpan = tb.rows[i-pos].cells[col+1].rowSpan+1; 
pos++; 
}else{ 
lastValue = value; 
pos=1; 
} 
} 
} 
	</script>
</head>
<body overflow-y="scroll" >
<script type="text/javascript">
createTable();

</script>
	<div class="easyui-layout" data-options="fit:true,border:false" style="OVERFLOW-Y: auto; OVERFLOW-X: hidden; WIDTH: 698px; HEIGHT: 10px">
		<div data-options="region:'center',border:false"  style="overflow: hidden;">
			<table id="tableGrid" name="tableGrid" border="1" cellspacing="0" bordercolor="#000000" >
			<tr>
			<th style="width:70px;" align="center"><p>一级指标</p></th>
			<th style="width:40px;" align="center"><p>得分</p></th>
			<th style="width:180px;" align="center"><p>二级指标</p></th>
			<th style="width:40px;" align="center"><p>得分</p></th>
			<th style="width:200px;" align="center"><p>三级指标</p></th>
			<th style="width:40px;" align="center"><p>得分</p></th>
			<th style="width:400px;" align="center"><p>四级指标</p></th>
			<th style="width:40px;" align="center"><p>得分</p></th>
			</tr>
			</table>
		</div>
	</div>

</body>

</html>
<script type="text/javascript">

</script>