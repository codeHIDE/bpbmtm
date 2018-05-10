<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ include file="/common/taglibs.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'test.jsp' starting page</title>
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
				alert("a");
		        $('#container').highcharts({
		            chart: {
		                type: 'column'
		            },
		            title: {
		                text: 'Monthly Average Rainfall'
		            },
		            subtitle: {
		                text: 'Source: WorldClimate.com'
		            },
		            xAxis: {
		                categories: [
		                    'Jan',
		                    'Feb',
		                    'Mar',
		                    'Apr',
		                    'May',
		                    'Jun',
		                    'Jul',
		                    'Aug',
		                    'Sep',
		                    'Oct',
		                    'Nov',
		                    'Dec'
		                ]
		            },
		            yAxis: {
		                min: 0,
		                title: {
		                    text: 'Rainfall (mm)'
		                }
		            },
		            tooltip: {
		                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
		                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
		                    '<td style="padding:0"><b>{point.y:.1f} mm</b></td></tr>',
		                footerFormat: '</table>',
		                shared: true,
		                useHTML: true
		            },
		            plotOptions: {
		                column: {
		                    pointPadding: 0.2,
		                    borderWidth: 0
		                }
		            },
		            series: [{
		                name: 'Tokyo',
		                data: [49.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4]
		    
		            }, {
		                name: 'New York',
		                data: [83.6, 78.8, 98.5, 93.4, 106.0, 84.5, 105.0, 104.3, 91.2, 83.5, 106.6, 92.3]
		    
		            }, {
		                name: 'London',
		                data: [48.9, 38.8, 39.3, 41.4, 47.0, 48.3, 59.0, 59.6, 52.4, 65.2, 59.3, 51.2]
		    
		            }, {
		                name: 'Berlin',
		                data: [42.4, 33.2, 34.5, 39.7, 52.6, 75.5, 57.4, 60.4, 47.6, 39.1, 46.8, 51.1]
		    
		            }]
		        });
		    });
		    
	</script>
  </head>
  
  <body>
    This is my JSP page. <br>
    <div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
  </body>
</html>
