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
    <title>机构分润</title>
	<script src="<s:url value='/js/jqui/jquery/jquery-1.5.2.min.js'/>" type="text/javascript"/></script>
	<script src="<s:url value='/js/Highcharts-4.0.1/js/highcharts.js'/>" type="text/javascript"/></script>
	<script src="<s:url value='/js/Highcharts-4.0.1/js/themes/grid.js'/>" type="text/javascript"/></script>
	<script src="<s:url value='/js/Highcharts-4.0.1/js/modules/exporting.js'/>" type="text/javascript"/></script>
	<script src="<s:url value='/js/common.js'/>" type="text/javascript"></script>
	
	<script type="text/javascript">
	
		$(function () {
            var data = $("#seriesJson").val();
			var seriesJson = eval('(' + data + ')');
			var xAxis = $("#xAxisJson").val();
			var xAxisJson = eval('(' + xAxis + ')');
            var totalInfo = $("#totalInfo").val();
		        $('#container').highcharts({
		            chart: { type: 'column'  },
		            title: { text: '机构日分润统计' },
		            subtitle: { text: totalInfo },
		            xAxis: xAxisJson,
		            yAxis: { min: 0, title: { text: '(元)'  } },
		            tooltip: {
		                headerFormat: '<span style="font-size:10px;">{point.key}</span><table>',
		                pointFormat: '<tr><td style="color:{series.color};padding:0;font-size:11px;">{series.name}:&nbsp;</td>' +
		                    '<td style="padding:0;font-size:12px;"><b>{point.y:.4f}&nbsp;元</b></td></tr>',
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

		function f_search() {
		   	$("#form1").attr("action", '<s:url value="merProfitStatictis!merDayProfitStatistics.ac"/>');
		   	$("#form1").attr("target", '_self');
			$("#form1").submit();
		}
		
		
		function download_mer_excel() {
			$("#form1").attr("action", '<s:url value="/merProfitStatictis!download_mer_excel.ac"/>');
			$("#form1").attr("target", '_blank');
			$("#form1").submit();
		}
		
  
	</script>
	<style type="text/css">
		tr{
			height:30px;
		}
	</style>
  </head>
  
  <body>

  	<form action="" method="post" id="form1">
  		<table width="95%" align="center" style="margin-top: 15px" border="0">
  			<thead></thead>
  			<tr>
  				<td style="text-align: right; ">
  					商户号：
  				</td>
  				<td style="text-align: left;width: 100px">
  					<div class="l-text" style="width: 150px">
						<input type="text" style="width: 150px" name="merProfitStatictis.mid" id="mid" value="${merProfitStatictis.mid}" class="l-text-field"/>
					</div>
  				</td>
  				<td style="text-align: right; ">
  					类型：
  				</td>
  				<td style="text-align: left;width: 100px">
  					<select name="merProfitStatictis.merType">
  						<option value="0">机构</option>
  						<option value="2">1级代理</option>
  						<option value="3">2级代理</option>
  						<option value="4">平台类商户</option>
  					</select>
  				</td>
  				<td style="text-align: right;">清算年份：</td>
					<td>
						<select name="year" id="year">
						    <c:forEach var="years" begin="2000" end="2029" step="1">
						            <option value="${years}" <c:if test="${year == years}">selected="selected"</c:if>>${years}</option>
						    </c:forEach>
						</select> 
					</td>
					<td style="text-align: right;">清算月份：</td>
					<td>
						<select name="month" id="month">
						    <c:forEach var="months" begin="1" end="12" step="1">
						            <option value="${months}" <c:if test="${month == months}">selected="selected"</c:if>>${months}</option>
						    </c:forEach>
						</select> 
					</td>
					<td><input id="btn" type="button" value="查 询" onclick="f_search()" class="l-button"/></td> 
					<td><input id="btns" type="button" value="导 出" onclick="download_mer_excel()" class="l-button"/></td>
  			</tr>
  		</table>
  	</form>
  	<br/>
    <div id="container" style="min-width: 310px; height: 99%; max-width: 99%; margin: 0 auto;"></div>
    <s:hidden id="xAxisJson" value="%{axisJson}"></s:hidden>
    <s:hidden id="seriesJson" value="%{seriesJson}"></s:hidden>
    <s:hidden id="totalInfo" value="%{totalInfo}"></s:hidden>
  </body>
</html>
