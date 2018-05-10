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
    
    <title>借贷记统计</title>
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
			var seriesJson = eval('(' + data + ')')
		    $('#container').highcharts({
		        chart: {
		            plotBackgroundColor: null,
		            plotBorderWidth: null,
		            plotShadow: false
		        },
		        title: {
		            text: '借贷记统计'
		        },
		        tooltip: {
		    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
		        },
		        plotOptions: {
		            pie: {
		                allowPointSelect: true,
		                cursor: 'pointer',
		                dataLabels: {
		                    enabled: true,
		                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
		                    style: {
		                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
		                    }
		                }
		            }
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
			 return true;
		}
		function f_search() {
			 var res = checkData();
        	 if(res == true){
				 $("#from1").submit();
        	 }
		}
	</script>
  </head>
  
  <body>
  	<form action="statistics!getCardTypeStatistics.ac" method="post" id="from1">
  		<table width="99%" align="center" style="margin-top: 15px">
  			<tr>
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
    <s:hidden id="seriesJson" value="%{seriesJson}"></s:hidden>
  </body>
</html>
