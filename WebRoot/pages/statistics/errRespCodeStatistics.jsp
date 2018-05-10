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
    
    <title>错误码统计</title>
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
		    $('#container').highcharts({
		        chart: {
		            plotBackgroundColor: null,
		            plotBorderWidth: null,
		            plotShadow: false
		        },
		        title: {
		            text: '错误码统计'
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
	<style type="text/css">
		tr{
			height:30px;
		}
	</style>
  </head>
  
  <body>
  	<form action="statistics!getErrRespCodeStatistics.ac" method="post" id="from1">
  		<table width="95%" align="center" style="margin-top: 15px" border="0">
  			<thead></thead>
  			<tr>
  				<td style="text-align: right;width: 140px">
  					机构商号：
  				</td>
  				<td style="text-align: left;">
  					<div class="l-text" style="width: 140px">
						<input type="text" name="merSysId" style="width: 140px" id="merSysId" value="${merSysId}" class="l-text-field"/>
					</div>
  				</td>
  				<td style="text-align: right;width: 140px">
  					交易类型：
  				</td>
  				<td style="text-align: left;">
  					<div style="width: 140px">
						<select name="transType" id="transType" style="width: 150px;">
							<option value="-1">--请选择--</option>
							<option value="01"
								<c:if test="${transType=='01'}">selected="selected"</c:if>>
								消费</option>
							<option value="04"
								<c:if test="${transType=='04'}">selected="selected"</c:if>>
								消费撤销</option>
							<option value="05"
								<c:if test="${transType=='05'}">selected="selected"</c:if>>
								消费退款</option>
							<option value="06"
								<c:if test="${transType=='06'}">selected="selected"</c:if>>
								转账</option>
							<option value="07"
								<c:if test="${transType=='07'}">selected="selected"</c:if>>
								还款</option>
							<option value="08"
								<c:if test="${transType=='08'}">selected="selected"</c:if>>
								余额查询</option>
							<option value="09"
								<c:if test="${transType=='09'}">selected="selected"</c:if>>
								手机充值</option>
					</select>
					</div>
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
    <s:hidden id="seriesJson" value="%{seriesJson}"></s:hidden>
  </body>
</html>
