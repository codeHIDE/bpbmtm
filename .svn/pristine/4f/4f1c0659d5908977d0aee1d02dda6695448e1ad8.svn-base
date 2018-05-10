<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>结算状态查询</title>
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
		<style type="">
			tr{
				height:30px;
			}
		</style>
	<script type="text/javascript">
    	$(function(){
    		statusChange();
            $("#maingrid4").ligerGrid({
                columns: [
            { display: '商户号', name: 'subMerId', align: 'left', width: '10%' ,align: 'center' },
            { display: '机构商户号', name: 'merSysId', width: '10%', align: 'center' },
            { display: '提现状态', name: 'orderStatus', width: '10%', align: 'center',render:function(rowdata,rowindex){
            	if(rowdata.orderStatus=='0'){return '申请';}
            	else if(rowdata.orderStatus=='1'){return '生成代发文件等待代发';}
            	else if(rowdata.orderStatus=='2'){return '代发成功';}
            	else if(rowdata.orderStatus=='3'){return '代发失败';}
            	else if(rowdata.orderStatus=='9'){return '代发处理中';}
            	
            } },
            { display: '提现金额(元)', name: 'orderAmt', width: '10%', align: 'center',type:'currency'}, 
            { display: '提现订单号', name: 'transId', width: '10%', align: 'center' }, 
 			{ display: '提现流水号', name: 'transQid', width: '10%', align: 'center' }, 
            { display: '入网时间', name: 'createTime', width: '8%', align: 'center' }, 
            { display: '手续费(元)', name: 'transFee', width: '8%', align: 'center',type:'currency'},
            { display: '费率(元)', name: 'tractRate', width: '8%', align: 'center',type:'currency'}, 
            { display: '批次号', name: 'batchId', width: '8%', align: 'center' }
           
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
                url:'<s:url value="/subMerCashout!selectSubMerCashoutList.ac"/>?orderStatus='+$("#orderStatus").val()
            });
            $("#maingrid4").ligerGrid().set('dataAction','server');
            $("#pageloading").hide();
        });
        
        function itemclick()
        {
            g.options.data = $.extend(true,{}, CustomersData);
            g.showFilter();
        }
   
        function f_search()
        {
           $("#form1").submit();
        }
        
           
         if(data=='succ'){
			$("#tractStatus").html("<font color='red'>已审批</font>");
		}
        
         function downloadReport(){
        	 var orderStatus=$("#orderStatus").val();
			location.href="<s:url value='/subMerCashout!todayStatisticsDetailExcel.ac'/>?orderStatus="+orderStatus;
		}
		
		function f_open2()
        {
            $.ligerDialog.open({ url: '<s:url value="/pages/subMerCashout/importExcelSubMerCashout.jsp"/>',
            					 height:300,width: 580, isResize: false,title:'导入'});  
        }
		
		function statusChange(){
			var value = $("#orderStatus").val();
			if("3"==value){
				$("#statusLable").show();
			}else{
				$("#statusLable").hide();
			}
			
		}	
		
       
    </script>
	</head>
	<body style="padding: 4px;">
	<%@include file="/js/common.jsp" %>
		<div id="searchbar">
			<form id="form1" action="<s:url value="/pages/subMerCashout/selectSubMerCashout.jsp"/>" method="post">
				<table width="80%">
					<thead></thead>
					<tr>
						<td style="text-align: right;">
							提现状态：
						</td>
						<td>
							<select name="orderStatus"  id="orderStatus" onchange="statusChange()" >
								<option value="3" <c:if test="${param.orderStatus=='3'}">selected="selected"</c:if>>
									代发失败
								</option>
								<option value="0" <c:if test="${param.orderStatus=='0'}">selected="selected"</c:if>>
									申请
								</option>
								<option value="1" <c:if test="${param.orderStatus=='1'}">selected="selected"</c:if>>
									生成代发文件等待代发 
								</option>
								<option value="2" <c:if test="${param.orderStatus=='2'}">selected="selected"</c:if>>
									代发成功
								</option>
								
								<option value="9" <c:if test="${param.orderStatus=='9'}">selected="selected"</c:if>>
									代发处理中
								</option>
							</select>
						</td>
						
						<td >
						<input id="btn2" type="button" value="查 询" onclick="f_search()" class="l-button"/>
						</td>
						<td>
							<label id="statusLable">
								<input id="btn1" type="button" value="下 载" class="l-button" onclick="downloadReport()"/>
								<input id="btn3" type="button" value="导 入" class="l-button" onclick="f_open2()"/>
							</label>
						</td>
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
