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
    
    <title>代理商利润统计</title>
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
            var data = $("#seriesJson").val();
			var seriesJson = eval('(' + data + ')');
			var xAxis = $("#xAxisJson").val();
			var xAxisJson = eval('(' + xAxis + ')');
            
		        $('#container').highcharts({
		            chart: {
		                type: 'column'
		            },
		            title: {
		                text: '代理商利润统计'
		            },
		            subtitle: {
		                text: ''
		            },
		            xAxis: xAxisJson,
		            yAxis: {
		                min: 0,
		                title: {
		                    text: '(元)'
		                }
		            },
		            tooltip: {
		                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
		                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
		                    '<td style="padding:0"><b>{point.y:.1f}元</b></td></tr>',
		                footerFormat: '</table>',
		                shared: true,
		                useHTML: true
		            },
		            plotOptions: {
		                column: {
		                    pointPadding: 0.2,
		                    borderWidth: 0,
		                    pointWidth: 10
		                }
		            },
		            series: seriesJson
		        });
		 });
	</script>
	<style type="text/css">
		tr{
			height:30px;
		}
	</style>
  </head>
  
  <body>
    <div id="container" style="min-width: 310px; height: 99%; max-width: 99%; margin: 0 auto"></div>
    <s:hidden id="xAxisJson" value="%{axisJson}"></s:hidden>
    <s:hidden id="seriesJson" value="%{seriesJson}"></s:hidden>
  </body>
</html>
