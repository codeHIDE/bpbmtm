<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>异常交易查询</title>
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
		$(function () {
          	 loadData();
          	 $("#pageloading").hide();
             $('#maingrid4').ligerGrid().set('dataAction','server');
          	 var interval;
           $("#checkbox").change(function(){
		        if($("#checkbox").attr("checked")==true){
		   		   interval = setInterval(loadData, "10000");
		        } else {
		           clearInterval(interval);
		        }        
		 	} );
		 	
       });
		var grid;
        function loadData(){
           grid= $("#maingrid4").ligerGrid({
            columns: [
            { display: '订单号', name: 'orderId', align: 'left', width: '11%',align: 'center' },
            { display: '商户号', name: 'submerId', align: 'center', width: '13%' },        
	      	{ display: '异常类型', name: 'orderRiskType', width: '15%', align: 'center',render:function(rowdata,rowindex){
            	if(rowdata.orderRiskType=='00'){return '手动标记';}
            	if(rowdata.orderRiskType=='11'){return '用户多点操作';}
            	if(rowdata.orderRiskType=='12'){return '卡号对应多个用户身份';}
            	if(rowdata.orderRiskType=='13'){return '相同身份对应多卡号';}
            	if(rowdata.orderRiskType=='21'){return '单订单多次支付失败';}
            	if(rowdata.orderRiskType=='22'){return '单日交易总额大于平均值20%';}
            	if(rowdata.orderRiskType=='23'){return '单卡多点同时交易';}
            	if(rowdata.orderRiskType=='24'){return '零点至五点的交易';}
            	else return '未知';
            } }, 
            { display: '异常处理方式', name: 'orderRiskProcType', width: '15%', align: 'center', render:function(rowdata,rowindex){
            	if(rowdata.orderRiskProcType==1){return '报警';}
            	else return '未知';
            } }, 
            { display: '处理时间', name: 'orderRiskProcTime',width: '12%', heightAlign: 'center' },
            { display: '扩展域', name: 'ext', width: '12%', align: 'center' },
            { display: '保留域', name: 'reseRved1', width: '9%', align: 'center' },
            { display: '操作', name: 'null', width: '12%', align: 'center',render: function (rowdata, rowindex, value,record)
           	 	{
           	 		<c:if test="${purview['2051'] == '2051'}">
                    	var h = "<a onclick='f_open1("+rowdata.orderId+")' style='cursor:pointer;color:blue;'>详情</a> ";
                    </c:if>
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
                url:'<s:url value="/abnormalOrder!selecrAbnormalOrder.ac"/>?orderRisk.submerId='+$("#busMerId").val()
                		+'&orderRisk.orderId='+$("#orderId").val()+'&startTime='+$("#startTime").val()
                		+'&endTime='+$("#endTime").val()           
            });
            $("#startTime").ligerDateEditor({ showTime: true,format: "yyyy-MM-dd",width:"130"});
            $("#endTime").ligerDateEditor({ showTime: true,format: "yyyy-MM-dd",width:"130"});
            
        };
        
        function f_search()
        {
			  var busMerId=$("#busMerId").val(); 
			  var orderId=$("#orderId").val(); 
			  var pattern = /^[\S]*$/; //不包含空格
			  var number = /^[0-9]{1,20}$/;	//数字	
					
					if(busMerId!=""&&!number.test(busMerId)) {
						alert("子商户号只能是数字！");
						$("#busMerId").val("");  
						$("#busMerId").focus();
						return false;
					}
					
					if(orderId!=""&&!number.test(orderId)) {
						alert("订单号只能是数字！");
						$("#orderId").val("");  
						$("#orderId").focus();
						return false;
					}else{
					 $("#form1").attr("target", '_self');
					  $("#form1").attr("action", '<s:url value="/pages/order/abnormalOrderList.jsp"/>');
					  $("#form1").submit();
					}
        }
        
        function f_clean(){
        	$("#busMerId").val("");
        	$("#orderId").val("");
        	$("#startTime").val("");
        	$("#endTime").val("");
        }
        
        function f_open1(orderId,merOrderId)
        {
            $.ligerDialog.open({ url: '<s:url value="/orderInfo!selectOrderDetail.ac"/>?orderId='+orderId,
            					  height:520,width:690, isResize: false,title:'交易详情'});    
        }
        function f_search_downLoad_excel() {
         	 $("#form1").attr("action", '<s:url value="/abnormalOrder!download_excel.ac"/>');
			 $("#form1").attr("target", '_blank');
			 $("#form1").submit();
		}
    </script>
	</head>
	<body style="padding: 4px; overflow: hidden;">
	<%@include file="/js/common.jsp" %>
		<div id="searchbar">
			<form id="form1" action="<s:url value="/pages/order/orderListHis.jsp" />" method="post">
				<table width="95%" border="0">
					<thead></thead>
					<tr>
						<td style="text-align: right;">
							商户号：
						</td>
						<td style="text-align: left;">
							<div class="l-text" style="width: 130px;">
								<input type="text" name="busMerId" id="busMerId" value="${param.busMerId}" class="l-text-field"/>
							</div>
						</td>
						<td style="text-align: right;">
							订单号：
						</td>
						<td style="text-align: left;">
							<div class="l-text" style="width: 130px;">
								<input type="text" name="orderId" id="orderId" maxlength="20" value="${param.orderId}" class="l-text-field"/>
							</div>
						</td>
						<td style="text-align: right;">
							处理时间：
						</td>
						<td style="text-align: left;"> 
							<div style="width: 130px;float: left;"> 
								<input type="text" name="startTime" id="startTime"  value="${param.startTime}"/>
							</div>
							<span style="float: left;">&nbsp;&nbsp;-&nbsp;&nbsp;</span>
							<div style="width: 130px;float: left;">  
								<input type="text" name="endTime" id="endTime"  value="${param.endTime}" />
							</div>
						</td>                                                                                 
						<td style="text-align: left;" colspan="4">
							<input id="btn" type="button" value="查 询" onclick="f_search()" class="l-button"/>&nbsp;&nbsp;
							<input id="btn2" type="button" value="重 置" onclick="f_clean()" class="l-button"/>&nbsp;&nbsp;							
						<c:if test="${purview['2052'] == '2052'}">
							<input id="btn3" type="button" value="导 出" onclick="f_search_downLoad_excel()" class="l-button"/>
						</c:if>
						<input id="checkbox" type="checkbox" value="自动刷新" class="checkbox"/>&nbsp;&nbsp;自动刷新
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div class="l-loading" style="display: block" id="pageloading">
		</div>
		<div id="maingrid4" style="margin: 0; padding: 0;margin-top: 10px"></div>
		<div style="display: none;">
		</div>
	</body>
</html>
