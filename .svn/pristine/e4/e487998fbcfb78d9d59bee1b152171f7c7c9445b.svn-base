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
		<script src="<s:url value='/js/jqui/jquery/jquery-1.5.2.min.js'/>" type="text/javascript"></script>
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
		<style type="text/css">
			tr{
				height:30px;
			}
		</style>
	<script type="text/javascript">
		//设置下拉框的年月
		function yearMonth(yearT,monthT) {
			//年份
			var now = new Date(); 
            var year = now.getFullYear()-5; 
			var obj = document.getElementById("s1"); 
			for(var i = 1;i <= 10; i++){ 
				if(year == yearT ) {
					$("#s1").append("<option value='"+year+"' selected='selected'>"+year+"</option>");
				} else {
			   		$("#s1").append("<option value='"+year+"'>"+year+"</option>");
				}
			     year = year + 1;  
			}
			//月份
			var month = 1; 
			var obj = document.getElementById("s2"); 
			for(var i = 1;i <= 12; i++){ 
				if(month == monthT ) {
					$("#s2").append("<option value='"+month+"' selected='selected'>"+month+"</option>");
				} else {
			   		$("#s2").append("<option value='"+month+"'>"+month+"</option>");
				}
			     month = month + 1;  
			}
		}
		
		var grid;
        $(function (){
        	var yearT = $("#startTime").val();
			var monthT = $("#endTime").val(); 
			yearMonth(yearT,monthT);
        	
            grid= $("#maingrid4").ligerGrid({
            columns: [
        	 { display: '代理商号', name: 'agentId', align: 'left', width: '15%',align: 'center' },
             { display: '代理商名', name: 'agentName', align: 'center', width: '15%',render:function(rowdata,rowindex){
	            	return rowdata.agentName;
	            } },
	         { display: '代理级别', name: 'level', align: 'center', width: '12%',align: 'center' },
             { display: '交易手续费', name: 'sumTransFee', width: '15%', align: 'right', type:'currency'},
             { display: '代理商利润', name: 'agentProfit', width: '15%', align: 'right', type:'currency'}, 
             { display: '操作', name: 'null', width: '17%', align: 'center',render: function (rowdata, rowindex, value,record)
           	 	{
                    var h = "";
                    if (!rowdata._editing)
                    {
                        h += "<a onclick='f_details("+rowdata.agentId+")' style='cursor:pointer;color:blue;'>详情</a> ";
						h += "<a onclick='f_downLoad_excel("+rowdata.agentId+")' style='cursor:pointer;color:blue;'>下载</a> ";						  
                    }
                    return h;
                } } 
                ],  
                rownumbers:true,
                width: '100%', 
                height: '100%', 
                checkbox: false,
                pageSize: 15,
                pageSizeOptions: [15],                        
		        sortName : null,
		        sortOrder:null,      
		        root :'Rows',                       //数据源字段名
		        record:'Total',                     //数据源记录数字段名
		        pageParmName :'page',               //页索引参数名，(提交给服务器)
		        pagesizeParmName:'pagesize',        //页记录数参数名，(提交给服务器)
                dataAciton : 'server',
                url:'<s:url value="/statistics!getAgentProfitStatistics.ac"/>?merSysId='+$("#merSysId").val()+'&startTime='+$("#s1").val()
                +'&endTime='+$("#s2").val()
            });
            $("#pageloading").hide();
            $('#maingrid4').ligerGrid().set('dataAction','server');
        });


		function f_downLoad_excel(agentId) {
			 $("#agentId").val(agentId);
			 //$("#from1").attr("action", '<s:url value="/statistics!agentProfitDownLoad.ac"/>');
			 //$("#form1").submit();
			 $("#from1").attr("action", '<s:url value="/statistics!agentProfitDownLoad.ac"/>');
			 $("#from1").submit();      
		}
		
		var win1;
		function f_details(agentId) {
			var yearT = $("#startTime").val();
			var monthT = $("#endTime").val(); 
			if (win1) win1.show();
        	else win1 = $.ligerDialog.open({ height: 500, url: '<s:url value="/statistics!agentProfitDetails.ac"/>?agentId='+agentId
				+'&startTime='+yearT+'&endTime='+monthT, width: 1000, showMax: true, showToggle: true, showMin: true, isResize: true, slide: false });
		}
		
		function f_search()
        {
        	 var res = checkData();
        	 if(res == true){
			     $("#from1").attr("action", '<s:url value="/pages/statistics/agentProfitInfo.jsp"/>');
				 $("#from1").submit();      
        	 }
        }
 		function checkData(){
			var merSysId = $("#merSysId").val();
			if(merSysId == "" || merSysId == null) {
					alert("请输入机构号！");
					$("#merSysId").val("");
					$("#merSysId").focus();
					return false;
			 }
			 return true;
		}
	</script>
  </head>
  <body>
  <%@include file="/js/common.jsp" %>
  <div id="searchbar">
  	<form action="<s:url value="/pages/statistics/agentProfitInfo.jsp"/>" method="post" id="from1">
  		<table align="center" style="margin-top: 15px" border="0" width="95%">
  			<thead></thead>
  			<tr>
				<td style="text-align: right;">
					机构商号：
				</td>
				<td>
					<div class="l-text" style="width: 140px">
						<input type="text" name="merSysId" style="width: 140px" id="merSysId" value="${param.merSysId}" class="l-text-field"/>
					</div>
				</td>
				<td style="text-align: right;">
					结算年：
				</td>
				<td style="text-align: left;">
					<select id="s1" name="startTime" ></select>
					<input type="hidden" id="startTime" value="${param.startTime}">
					月：
					<select id="s2" name="endTime" ></select>
					<input type="hidden" id="endTime" value="${param.endTime}">
				</td>                                                                                    
				<td style="text-align: left;" colspan="4">
					<input type="hidden" name="agentId" id="agentId">
					<input id="btn" type="button" value="查 询" onclick="f_search()" class="l-button"/>
				</td>
			</tr>
  		</table>
  	</form>
  </div>
   <div class="l-loading" style="display: block" id="pageloading"></div>
   <div id="maingrid4" style="margin: 0; padding: 0;margin-top: 10px"></div>
   <div style="display: none;"></div>
  </body>
</html>
