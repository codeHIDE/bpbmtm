<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>日交易金额、分润统计</title>
    <link href="<s:url value='/js/jqui/ligerUI/skins/Aqua/css/ligerui-all.css'/>" rel="stylesheet" type="text/css" />
	<link href="<s:url value='/js/jqui/ligerUI/skins/Gray/css/all.css'/>" rel="stylesheet" type="text/css" />
	<link href="<s:url value='/js/jqui/ligerUI/skins/ligerui-icons.css'/>" rel="stylesheet" type="text/css" />
	<script src="<s:url value='/js/jqui/jquery/jquery-1.5.2.min.js'/>" type="text/javascript"/></script>
	<script src="<s:url value='/js/Highcharts-4.0.1/js/highcharts.js'/>" type="text/javascript"/></script>
	<script src="<s:url value='/js/Highcharts-4.0.1/js/themes/grid.js'/>" type="text/javascript"/></script>
	<script src="<s:url value='/js/Highcharts-4.0.1/js/modules/exporting.js'/>" type="text/javascript"/></script>
	
	<script src="<s:url value='/js/jqui/ligerUI/js/core/base.js'/>" type="text/javascript"></script>
	<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerToolBar.js'/>" type="text/javascript"></script>
	<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerDialog.js'/>" type="text/javascript"></script>
	<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerGrid.js'/>" type="text/javascript"></script>
	<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerFilter.js'/>" type="text/javascript"></script>
	<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerDrag.js'/>" type="text/javascript"></script>
	<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerResizable.js'/>" type="text/javascript"></script>
	<script src="<s:url value='/js/jqui/ligerGrid.showFilter.js'/>" type="text/javascript"></script>
	<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerDateEditor.js'/>" type="text/javascript"></script>
	<script src="<s:url value='/js/common.js'/>" type="text/javascript"></script>
	
	<script type="text/javascript">
		$(function () { 
			$("#startTime").ligerDateEditor({ showTime: true,format: "yyyy-MM-dd"});
            $("#endTime").ligerDateEditor({ showTime: true,format: "yyyy-MM-dd"});
            
            var data = $("#seriesJson").val();
			var seriesJson = eval('(' + data + ')');
			var xAxis = $("#xAxisJson").val();
			var xAxisJson = eval('(' + xAxis + ')');
			
			var sumInfo =$("#sumInfo").val();
		
			$('#container').highcharts({ 
				title: { 
					text: '日交易金额、手续费统计', 
					x: -20 //center 
				}, 
				subtitle: { 
					text: sumInfo, 
					x: -20 
				}, 
				xAxis: xAxisJson, 
				yAxis: { 
					title: { 
						text: 'unit (元)' 
					}, 
					plotLines: [{ 
						value: 0, 
						width: 1, 
						color: '#808080' 
						}] 
					}, 
					tooltip: { 
						valueSuffix: '' 
					}, 
					legend: { 
						layout: 'horizontal', 
						align: 'center', 
						verticalAlign: 'bottom', 
						borderWidth: 0 
					}, 
					series: seriesJson
					}); 
				});
		function checkData(){
			var startTime = $("#startTime").val();
			var endTime = $("#endTime").val();
			if(startTime == "" || startTime == null) {
					alert("请输入开始时间！");
					$("#startTime").val("");
					$("#startTime").focus();
					return false;
			 }
			 if(endTime == "" || endTime == null) {
					alert("请输入结束时间！");
					$("#endTime").val("");
					$("#endTime").focus();
					return false;
			  }
			 var day = interval(startTime,endTime);
			 day = day + 1;
			 if(day > 31) {
			  	alert("最多选择30天数据！");
			  	return false;
			 }
			
			 return true;
		}
		
		function interval(startDate, endDate){
		    var d1 = new Date(startDate.replace(/-/g, "/"));
		    var d2 = new Date(endDate.replace(/-/g, "/"));
		    var time = d2.getTime() - d1.getTime();
		    return parseInt(time / (1000 * 60 * 60 * 24));
		}
		
		function f_search() {
			 var res = checkData();
        	 if(res == true){
				 $("#from1").submit();
        	 }
		}
	</script>
	<style type="text/css">
		tr{
			height:30px;
		}
	</style>
  </head>
  
  <body>
  	<form action="statistics!dayMerAmtFee.ac" method="post" id="from1">
  		<table width="95%" align="center" style="margin-top: 15px" border="0">
  			<thead></thead>
  			<tr>
  				<td style="text-align: right;">
  					商户号：
  				</td>
  				<td style="text-align: left;width: 100px">
  					<div class="l-text" style="width: 140px">
						<input type="text" name="mid" style="width: 140px" id="mid" value="${mid}" class="l-text-field"/>
					</div>
  				</td>
  					<td style="text-align: right; ">
  					类型：
  				</td>
  				<td style="text-align: left;width: 100px">
  					<select name="merType"  id="merType">
  						<option value="-1">全部</option>
  						<option value="0" <c:if test="${merType=='0'}">selected="selected"</c:if>>机构</option>
  						<option value="1" <c:if test="${merType=='1'}">selected="selected"</c:if>>子商户</option>
  					</select>
  				</td>
  				<td style="text-align: right;">
					商户交易时间：
				</td>
				<td style="text-align: left;"> 
					<div style="float: left;"> 
						<input type="text" name="startTime" id="startTime"  value="${startTime}"/>
					</div>
					<span style="float: left;">&nbsp;&nbsp;-&nbsp;&nbsp;</span>
					<div style="float: left;">  
						<input type="text" name="endTime" id="endTime"  value="${endTime}" />
					</div>
					&nbsp;&nbsp;
					<input id="btn" type="button" value="查 询" onclick="f_search()" class="l-button"/>
				</td>
  			</tr>
  		</table>
  	</form>
  	<br/>
    <div id="container" style="min-width: 310px; height: 99%; max-width: 99%; margin: 0 auto"></div>
    <s:hidden id="xAxisJson" value="%{axisJson}"></s:hidden>
    <s:hidden id="seriesJson" value="%{seriesJson}"></s:hidden>
    
    <s:hidden id="sumInfo" value="%{sumInfo}"></s:hidden>s
  </body>
</html>
