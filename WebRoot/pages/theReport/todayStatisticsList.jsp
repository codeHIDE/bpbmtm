﻿<%@page import="com.bypay.util.DateUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>T+0结算</title>
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
  
        $(function (){
            $("#maingrid4").ligerGrid({
                columns: [   
             	{ display: '批次号', name: 'batchId', align: 'center', width: '15%'},
             	{ display: '跑批时间(时)', name: 'batchTime', align: 'left', width: '9%' ,align: 'center'},
	            { display: '提现总金额(元)', name: 'orderAmt', align: 'left', width: '9%' ,align: 'center' ,type:'currency'},
              	{ display: '申请时间', name: 'createDate', align: 'left', width: '11%' ,align: 'center'},
	            { display: '完成时间', name: 'finishTime', align: 'left', width: '11%' ,align: 'center'},
	            { display: '总手续费(元)', name: 'transFee', align: 'left', width: '9%' ,align: 'center' ,type:'currency'},
	            { display: '总条数', name: 'orderCount', width: '9%', align: 'center'},  
	            { display: '提现状态', name: 'status', width: '10%', align: 'center',render: function (rowdata, rowindex, value){
	            	if(value=="1"){
	            		return "生成代发文件等待代发";
	            	}else if(value=="2"){
	            		return "代发文件已回盘";
	            	}
	            }},  
		 		{ display: '操作', name: 'null', width: '12%', align: 'center' ,render: function (rowdata, rowindex, value)
           	 	{
                     var h = "";
                  if (!rowdata._editing)
                    {
                    	 <c:if test="${purview['4041'] == '4041'}">
                    		 h += "<a onclick='downloadReport("+rowdata.batchId+")' style='cursor:pointer;color:blue;'>下载</a> ";
                    	 </c:if>
                    	 <c:if test="${purview['4042'] == '4042'}">
                   	 	 	h += "<a onclick='f_open1("+rowdata.batchId+")' style='cursor:pointer;color:blue;'>明细</a> "; 
						 </c:if>
                    }
                    return h;
                }}
                ], 
                rownumbers:true,
                width: '100%', 
                height: '100%', 
                checkbox: false,
                pageSize: 15,                        
		        sortName : null,
		        sortOrder:null,      
		        root :'Rows',                       //数据源字段名
		        record:'Total',                     //数据源记录数字段名
		        pageParmName :'page',               //页索引参数名，(提交给服务器)
		        pagesizeParmName:'pagesize',        //页记录数参数名，(提交给服务器)
                dataAciton : 'server',    
                url:'<s:url value="/MerSettleStatictis!getTodayStatisticsData.ac"/>?createDate2='+$("#createDate").val()
            });  
            $('#maingrid4').ligerGrid().set('dataAction','server');
            $("#pageloading").hide();
             $("#createDate").ligerDateEditor({ showTime: true,format: "yyyy-MM-dd"});
        });
         function f_search()
        {
			$("#form1").submit();
        } 
        function f_clean(){
        	$("#createDate").val("");
        }
        function f_open3(){
        	var createDate = $("#createDate").val();
      			var url="<s:url value="/MerSettleStatictis!checkT0.ac" />";
      			 $.post(url,{createDate:createDate},function(data){
       			if(data=='fone'){
  					alert("没有失败记录");
       			}else{
       				window.location.href="<s:url value='/MerSettleStatictis!downloadT0.ac'/>?createDate="+createDate;
       			}
	        });
        }
        function downloadReport(batchId){
			location.href="<s:url value='/MerSettleStatictis!todayStatisticsDetailExcel.ac'/>?batchId="+batchId;
		}	
		function f_open1(batchId)
        {	
            $.ligerDialog.open({ url: '<s:url value="/MerSettleStatictis!getTodayStatisticsDetail.ac"/>?batchId='+batchId,
            					 height:550,width:900, isResize: false,title:'明细'});  
        }
		function f_open2()
        {
            $.ligerDialog.open({ url: '<s:url value="/pages/theReport/importExcel.jsp"/>',
            					 height:300,width: 580, isResize: false,title:'导入'});  
        }
        
    </script>
	</head>
	<body style="padding: 4px; overflow: hidden;">
	<%@include file="/js/common.jsp" %>
		<div id="searchbar">			
			<form id="form1" action="<s:url value="/pages/theReport/todayStatisticsList.jsp" />" method="post">
				<table width="80%">
					<tr>
						<td style="text-align: right;">
							
						</td>
						<td style="text-align: right;">
							清算日期：
						</td>
						<td style="text-align: left;" align="right">
							<input type="text" name="createDate" id="createDate" 
							<s:if test = "createDate2 != null && createDate2 != ''">
								value="<%=request.getAttribute("createDate2") %>"
							</s:if>
							<s:else>
								value="${param.createDate}"
							</s:else> />
						</td>
						<td style="text-align: left;" align="right">
							
						</td>
						<td style="text-align: left;"><input id="btn1" type="button" value="查 询" onclick="f_search()" class="l-button"/></td>
						<td style="text-align: left;"><input id="btn2" type="button" value="重 置" onclick="f_clean()" class="l-button"/></td>
						<td style="text-align: left;"><input id="btn3" type="button" value="导入" onclick="f_open2()" class="l-button"/></td>
						<td style="text-align: left;"><input id="btn3" type="button" value="导出失败记录" onclick="f_open3()" class="l-button"/></td>
					</tr>
				</table>
			</form>
		</div>
		<div class="l-loading" style="display: block" id="pageloading">
		</div>
		<div id="maingrid4" style="margin: 0; padding: 0">
		</div>
		<div style="display: none;">

		</div>
	</body>
</html>
