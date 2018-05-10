﻿<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>扫码查询</title>
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
		<script src="<s:url value='/js/common.js'/>"></script>
		<script src="<s:url value='/js/CheckLength.js'/>"></script>
		<style type="text/css">
			tr{
				height:30px;
			}
		</style>
		<script type="text/javascript">
        $(function () {
            $("#maingrid4").ligerGrid({
            columns: [
            { display: '流水号', name: 'orderId', align: 'center', width: '15%'},
            { display: '渠道', name: 'chcd', width: '12%', align: 'center'},
            { display: '机构号', name: 'merSysId', width: '12%', align: 'center'},
            //{ display: '交易时间', name: 'transTime', width: '8%', align: 'center' },	
            { display: '交易时间', name: 'createTime', width: '8%', align: 'center' },	
            { display: '交易金额', name: 'txamt', width: '8%', align: 'right', type:'currency',render:function(rowdata,rowindex){
            	var amt = rowdata.txamt;
            	amt = amt/100;
            	if(amt>=1000){
            		return "<font color='blue'>"+amt.toFixed(2)+"</font>";
            	}
            	return amt.toFixed(2);
             } 
            }, 
            { display: '交易类型', name: 'orderType', width: '5%', align: 'center', render:function(rowdata,rowindex){
            	if(rowdata.orderType=='01'){return '扫码下单';}
            	else if(rowdata.orderType=='02'){return '扫码预下单';}
            	else if(rowdata.orderType=='03'){return '银联快捷';}
            	else if(rowdata.orderType=='04'){return '取消订单';}
            	else if(rowdata.orderType=='05'){return '退款订单';}
            	else if(rowdata.orderType=='06'){return '封顶订单';}
            	else return '未知';
            } }, 
            { display: '应答码', name: 'respcd', width: '8%', align: 'center' },	
            { display: '应答解释', name: 'respmsg', width: '12%', align: 'center' },	
            { display: '原交易流水', name: 'origOrderId', width: '12%', align: 'center' },	
            { display: '操作', name: 'null', width: '28%', align: 'left' ,render: function (rowdata, rowindex, value)
           	 	{
                    var h = "";
                   	h += "<a onclick='f_open6(\""+rowdata.id+"\")' style='cursor:pointer;color:blue;'>扫码查询</a> "; 
                   	h += "<a onclick='f_open7(\""+rowdata.id+"\")' style='cursor:pointer;color:blue;'>订单取消</a> "; 
                   	h += "<a onclick='f_open8(\""+rowdata.id+"\")' style='cursor:pointer;color:blue;'>订单退款</a> "; 
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
                 url:'<s:url value="/orderInfo!selectScanPay.ac"/>?scanOrderInfo.orderId='+$("#orderId").val()
                 +'&scanOrderInfo.orderType='+$("#orderType").val()
                 +'&scanOrderInfo.transTime='+$("#transTime").val()
                 +'&scanOrderInfo.merSysId='+$("#merSysId").val()
                 +'&scanOrderInfo.respcd='+$("#respcd").val()
            }); 
            $('#maingrid4').ligerGrid().set('dataAction','server');   
            $("#pageloading").hide();
             $("#transTime").ligerDateEditor({ showTime: true,width:"130",format: "yyyy-MM-dd"});
        });
        

         function f_search()
        {
        /* 	if(len("merSysId",20,"N","机构商号")) */
        		$("#form1").attr("target", '');
				 $("#form1").attr("action", '<s:url value="/pages/order/scanPay.jsp" />');
				  $("#form1").submit();
			/* return false; */
        }
        
     	function downloadExcel() {
         	 $("#form1").attr("action", '<s:url value="/orderInfo!downloadScan.ac"/>?scanOrderInfo.orderId='+$("#orderId").val()
                 +'&scanOrderInfo.orderType='+$("#orderType").val()
                 +'&scanOrderInfo.transTime='+$("#transTime").val()
                 +'&scanOrderInfo.merSysId='+$("#merSysId").val()
                 +'&scanOrderInfo.respcd='+$("#respcd").val());
			 $("#form1").attr("target", '_blank');
			 $("#form1").submit();
		}
        function f_open6(idNum){
		  		 $.ajax({
			                   type: "POST",
			                   dataType: "text",
			                   url: '<s:url value="/orderInfo!queryScanPay.ac"/>',
			                   data: {"id":idNum},
			                   success: function (data) {
			                     alert(data);
			                     // window.parent.$.ligerDialog.closeWaitting();
			                     window.location.reload();
				                 // dialogClose();
			                   }
				           });
       		 }
        
        function f_open7(idNum){
		  		 $.ajax({
			                   type: "POST",
			                   dataType: "text",
			                   url: '<s:url value="/orderInfo!cancelScanPay.ac"/>',
			                   data: {"id":idNum},
			                   success: function (data) {
			                     alert(data);
			                     // window.parent.$.ligerDialog.closeWaitting();
			                     window.location.reload();
				                 // dialogClose();
			                   }
				           });
       		 }
       		 
       		 function f_open8(idNum){
		  		 $.ajax({
			                   type: "POST",
			                   dataType: "text",
			                   url: '<s:url value="/orderInfo!refundScanPay.ac"/>',
			                   data: {"id":idNum},
			                   success: function (data) {
			                     alert(data);
			                     // window.parent.$.ligerDialog.closeWaitting();
			                     window.location.reload();
				                 // dialogClose();
			                   }
				           });
       		 }
       		 
    </script>
	</head>
	<body style="padding: 4px; overflow: hidden;">
		<div id="searchbar">
			<form id="form1" action="<s:url value="/pages/order/scanPay.jsp"/>" method="post" >
				<table width="100%" border="0">
					<thead></thead>
					<tr>
						<td style="text-align: right;width:100px;">
							流水号：
						</td>
						<td style="text-align: left;">
						<div class="l-text" style="width: 130px;">
							<input type="text" name="orderId" id="orderId"  class="l-text-field" value="${param.orderId}" />
								</div>
						</td>
						<td style="text-align: right;">
							交易类型：
						</td>
						<td>
							<select name="orderType" id="orderType" style="width: 130px;">
								<option value="-1">
									--请选择--
								</option>
								<option value="01" <c:if test="${param.payType=='01'}">selected="selected"</c:if>>
									扫码下单
								</option>
								<option value="02" <c:if test="${param.payType=='02'}">selected="selected"</c:if>>
									扫码预下单
								</option>
								<option value="03" <c:if test="${param.payType=='03'}">selected="selected"</c:if>>
									银联快捷
								</option>
								<option value="04" <c:if test="${param.payType=='04'}">selected="selected"</c:if>>
									取消订单
								</option>
								<option value="05" <c:if test="${param.payType=='05'}">selected="selected"</c:if>>
									退款订单
								</option>
								<option value="06" <c:if test="${param.payType=='06'}">selected="selected"</c:if>>
									封顶订单
								</option>
							</select>
						</td>
					<td style="text-align: right;">交易日期：</td>
						<td style="text-align: left;">
							<input type="text" name="transTime" id="transTime"  value="${param.transTime}" />
						</td>
					</tr>
					<tr>
						<td style="text-align: right;width:100px;">
							机构号：
						</td>
						<td style="text-align: left;">
						<div class="l-text" style="width: 130px;">
							<input type="text" name="merSysId" id="merSysId"  class="l-text-field" value="${param.merSysId}" />
								</div>
						</td>
						<td style="text-align: right;width:100px;">
							应答码：
						</td>
						<td style="text-align: left;">
						<div class="l-text" style="width: 130px;">
							<input type="text" name="respcd" id="respcd"  class="l-text-field" value="${param.respcd}" />
								</div>
						</td>
					</tr>
					<tr>
							<td style="text-align: left;"><input id="btn" type="button" value="查 询" onclick="f_search()" class="l-button"/></td>
							<td style="text-align: left;"><input id="btn3" type="button" value="导 出" onclick="downloadExcel()" class="l-button"/></td>
							<!-- 
							<td style="text-align: left;"><input id="btn3" type="button" value="导 出" onclick="f_search_downLoad_excel()" class="l-button"/></td>
							 -->
							<td style="text-align: left;"><br /></td>
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
