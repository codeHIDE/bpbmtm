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
			 var yearT = $("#startTime").val(); 
			 var startMon=$("#startMon").val();
			 var now = new Date(); 
			  var year=now.getFullYear()-5; 
			 var obj= document.getElementById("s1"); 
			for(var a=1;a<=11;a++){ 
				if(year == yearT ) {
					$("#s1").append("<option value='"+year+"' selected='selected'>"+year+"</option>");
				} else {
			   		$("#s1").append("<option value='"+year+"'>"+year+"</option>");
				}
			     year=year+1;  
			} 
			
			
            var data = $("#seriesJson").val();
			var seriesJson = eval('(' + data + ')');
			var xAxis = $("#xAxisJson").val();
			var xAxisJson = eval('(' + xAxis + ')');
            
		        $('#container').highcharts({
		            chart: { type: 'column'  },
		            title: { text: '机构成功交易日统计' },
		            subtitle: { text: 'Source: WorldClimate.com' },
		             xAxis: xAxisJson,
		            yAxis: { min: 0, title: { text: '(元)'  } },
		            tooltip: {
		                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
		                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
		                    '<td style="padding:0"><b>{point.y:.2f} 元</b></td></tr>',
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
		    	$("#form1").attr("action", '<s:url value="/statistics!getMerSucTransAmtFeeStatistics.ac"/>');
				 $("#form1").submit();
		}
		
		
		   function f_search_downLoad_excel() {
		  // window.location='<s:url value="/statistics!download_excel_new.ac"/>';
			  $("#form1").attr("action", '<s:url value="/statistics!download_excel_new.ac"/>');
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
  					机构商号：
  				</td>
  				<td style="text-align: left;width: 100px">
  					<div class="l-text" style="width: 150px">
						<input type="text" style="width: 150px" name="merSysId" id="merSysId" value="${merSysId}" class="l-text-field"/>
					</div>
  				</td>
  				<td style="text-align: right;">商户交易年份：</td>
					<td><select  id="s1" name="startTime" >
						</select>
						<s:hidden id="startTime" value="%{startTime}"></s:hidden>
					</td>
					<td style="text-align: right;">商户交易月份：</td>
					<td>
						<select name="startMon" id="startMon"  style="width:60px">
								<option value="01"<c:if test="${startMons=='01'}">selected="selected"</c:if>>
										1月
									</option>
									<option value="02" <c:if test="${startMons=='02'}">selected="selected"</c:if>>
										2月
									</option>
									<option value="03"<c:if test="${startMons=='03'}">selected="selected"</c:if>>
										3月
									</option>
									<option value="04"<c:if test="${startMons=='04'}">selected="selected"</c:if>>
										4月
									</option>
									<option value="05"<c:if test="${startMons=='05'}">selected="selected"</c:if>>
										5月
									</option>
									<option value="06"<c:if test="${startMons=='06'}">selected="selected"</c:if>>
										6月
									</option>
									<option value="07"<c:if test="${startMons=='07'}">selected="selected"</c:if>>
										7月
									</option>
									<option value="08"<c:if test="${startMons=='08'}">selected="selected"</c:if>>
										8月
									</option>
									<option value="09"<c:if test="${startMons=='09'}">selected="selected"</c:if>>
										9月
									</option>
									<option value="10"<c:if test="${startMons=='10'}">selected="selected"</c:if>>
										10月
									</option>
									<option value="11"<c:if test="${startMons=='11'}">selected="selected"</c:if>>
										11月
									</option>
									<option value="12"<c:if test="${startMons=='12'}">selected="selected"</c:if>>
										12月
									</option>
								</select>
						</td>
						<td><input id="btn" type="button" value="查 询" onclick="f_search()" class="l-button"/></td> 
						<td><input id="btns" type="button" value="导 出" onclick="f_search_downLoad_excel()" class="l-button"/></td>
  			</tr>
  		</table>
  	</form>
  	<br/>
    <div id="container" style="min-width: 310px; height: 99%; max-width: 99%; margin: 0 auto"></div>
    <s:hidden id="xAxisJson" value="%{axisJson}"></s:hidden>
    <s:hidden id="seriesJson" value="%{seriesJson}"></s:hidden>
  </body>
</html>
