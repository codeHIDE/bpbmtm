<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>T1报盘文件下载</title>
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
		<script src="<s:url value='/js/jqui/CustomersData.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerDateEditor.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/common.js'/>"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerToolBar.js'/>" type="text/javascript"></script>
		
		
		<style type="">
			tr{
				height:30px;
			}
		</style>
	<script type="text/javascript">
    	$(function(){
            $("#maingrid4").ligerGrid({
                columns: [
           { display: '时间', name: 'settleDate', width: '12%' ,align: 'center' },
           { display: '文件名', name: 'fileName', width: '12%' ,align: 'center' },
            { display: '结算类型', name: 'settleType', width: '12%', align: 'center' ,render:function(rowdata){
            	return "T1";
            }},
           { display: '操作', name: 'null', width: '10%', align: 'center' ,render: function (rowdata, rowindex, value)
           	 	{
           	 		var h = "";
              		 h = "<a onclick='downloadDf(\""+rowdata.settleDate+"\",\""+rowdata.fileName+"\")' style='cursor:pointer;color:blue;'>下载</a>" ;
                    return h;
                }}
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
                url:'<s:url value="/MerSettleStatictis!checkList.ac"/>?settleDate='+$("#settleDate").val()                           
            });
            $("#maingrid4").ligerGrid().set('dataAction','server');
            $("#pageloading").hide();
            $("#settleDate").ligerDateEditor({ showTime: true,format: "yyyy-MM-dd"});
        });
        
           function f_search()
        {
        	if($("#settleDate").val()==""){
        		alert("请选择日期");
        	}else{
           		$("#form1").submit();
           }
        }
        
   
              function downloadDf(settleDate,fileName){
		  		location.href = '<s:url value="/MerSettleStatictis!downloadCheck.ac"/>?fileName='+fileName+'&settleDate='+settleDate;
       		 }
        
    </script>
	</head>
	<body style="padding: 0px; overflow: hidden;"> 
		<%@include file="/js/common.jsp" %>
		<div id="searchbar">			
			<form id="form1" action="<s:url value="/pages/theReport/checkReport.jsp" />" method="post">
				<table width="80%">
					<tr>
						<td style="text-align: right;">
							日期：
						</td>
						<td style="text-align: left;" align="right">
							<input type="text" name="settleDate" id="settleDate" 
							<s:if test = "settleDate2 != null && settleDate2 != ''">
								value="<%=request.getAttribute("settleDate2") %>"
							</s:if>
							<s:else>
								value="${param.settleDate}" oo="111"
							</s:else> />
						</td>
						<td style="text-align: left;"><input id="btn" type="button" value="查 询" onclick="f_search()" class="l-button"/></td>
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
