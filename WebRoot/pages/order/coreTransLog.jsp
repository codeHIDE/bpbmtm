<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>核心交易查询</title>
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
	tr {
		height: 30px;
	}
</style>
<script type="text/javascript">
        $(function () {
           	 loadData();
             $("#pageloading").hide();
             $('#maingrid4').ligerGrid().set('dataAction','server');
             $("#settleDate").ligerDateEditor({ showTime: true,format: "yyyy-MM-dd"});
             $("#merOrderTime").ligerDateEditor({ showTime: true,format: "yyyy-MM-dd"});
             
            var interval;
            $("#checkbox").change(function(){
		        if($("#checkbox").attr("checked")==true){
		   		   interval = setInterval(loadData, "15000");
		        } else {
		           clearInterval(interval);
		        }        
		 	} );
		 	
        });
        function loadData() {
        	 $("#maingrid4").ligerGrid({
            columns: [
            { display: '交易日期', name: 'intTxnDt', align: 'left', width: '11%',align: 'center' },
            { display: '交易时间', name: 'intTxnTm', align: 'left', width: '11%',align: 'center' },
            { display: '商户', name: 'merId', align: 'left', width: '11%',align: 'center' },
            { display: '终端', name: 'termId', align: 'left', width: '11%',align: 'center' },
            { display: '渠道商户号', name: 'chMerId', align: 'left', width: '11%',align: 'center' },
            { display: '渠道终端号', name: 'chTermId', align: 'left', width: '11%',align: 'center' },
            { display: '渠道流水号', name: 'chTrackingNo', align: 'left', width: '11%',align: 'center' },
            { display: '交易类型', name: 'tradeCode', width: '5%', align: 'center',render:function(rowdata,rowindex){
            	if(rowdata.tradeCode=='000000'){
            			return '消费'; }
            	else if(rowdata.tradeCode=='310000'){return '查余';}
            	else if(rowdata.tradeCode=='200000'){return '撤销';}
            } }, 
            /*
            { display: '是否T0交易', name: 'transSource', width: '5%', align: 'center',render:function(rowdata,rowindex){
            	if(rowdata.transSource=='100'){
            			return '普通POS'; }
            	else if(rowdata.transSource=='101'){return 'T0Pos';}
            	else if(rowdata.transSource=='201'){return '手机Pos';}
            } },*/ 
            { display: 'transSource', name: 'transSource', align: 'left', width: '11%',align: 'center' },
            { display: '撤销状态', name: 'cancelStat', width: '5%', align: 'center',render:function(rowdata,rowindex){
            	if(rowdata.cancelStat=='0'){
            			return '正常'; }
            	else if(rowdata.cancelStat=='1'){return '撤销';}
            	else if(rowdata.cancelStat=='3'){return '已冲正';}
            } }, 
            { display: '卡号', name: 'cardNo', align: 'left', width: '11%',align: 'center' },
            { display: '检索参考号', name: 'referenceNo', align: 'left', width: '11%',align: 'center' },
            { display: '交易金额', name: 'transAmt', align: 'left', width: '11%',align: 'center' },
             { display: '交易状态', name: 'transStat', width: '5%', align: 'center',render:function(rowdata,rowindex){
            	if(rowdata.transStat=='0'){
            			return '未处理'; }
            	else if(rowdata.transStat=='1'){return '失败';}
            	else if(rowdata.transStat=='2'){return '成功';}
            } }, 
            { display: '应答码', name: 'replyCd', align: 'left', width: '11%',align: 'center' },
            { display: '操作', name: 'null', width: '8%', align: 'center',render: function (rowdata, rowindex, value)
           	 	{
                    var h = "";
                       // h += "	<a onclick='f_open1("+rowdata.orderId+",\""+rowdata.merOrderId+"\")' style='cursor:pointer;color:blue;'>详情</a> ";
                    return h;
                } } 
                ],  
                totalRender:f_totalRender,
                rownumbers:true,
                width: '100%', 
                height: '100%',
                /*  rowAttrRender: function (rowdata,rowid){
                	if (rowdata.respCode=='02' || rowdata.respCode=='04' ||
                			rowdata.respCode=='33' ||
                			rowdata.respCode=='34' ||
                			rowdata.respCode=='41' ||
                			rowdata.respCode=='43' ||
                			rowdata.respCode=='45' ||
                			rowdata.respCode=='54' ||
                			rowdata.respCode=='59'
                			) {
                		  return "style='background:red;'";
                	}
                	return "";
                },  */
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
                 parms:{
                	busMerId:$("#busMerId").val(),
                	cardNo:$("#cardNo").val(),
                	orderStatus:$("#orderStatus").val(),
                	startTime:$("#startTime").val(),
                	endTime:$("#endTime").val(),
                	merSysId:$("#merSysId").val(),
                	t0Status:$("#t0Status").val(),
                	/* agentIdL1:$("#agentIdL1").val(),
                	agentIdL2:$("#agentIdL2").val(), */
                	refundStatus:$("#refundStatus").val(),
                	transMerId : $("#transMerId").val()
                },
                url:'<s:url value="/orderInfo!selectTransLog.ac"/>'
            });
            $("#startTime").ligerDateEditor({ showTime: true,width:"130",format: "yyyyMMdd"});
            $("#endTime").ligerDateEditor({ showTime: true,width:"130",format: "yyyyMMdd"});
        }
        
        
        function f_totalRender(data)
		{
		    return "交易金额合计:"+data.sumMerAmt+"&nbsp;&nbsp;&nbsp;交易手续费合计:"+data.sumTransFee; 
		}
        
        function itemclick()
        {
            g.options.data = $.extend(true,{}, CustomersData);
            g.showFilter();
        }
        
        function f_search_downLoad_excel() {
         	 $("#form1").attr("action", '<s:url value="/orderInfo!downloadCoreXls.ac"/>');
			 $("#form1").attr("target", '_blank');
			 $("#form1").submit();
		}
		
         function f_open1(orderId,merOrderId)
        {
            $.ligerDialog.open({ url: '<s:url value="/orderInfo!selectOrderDetail.ac"/>?orderId='+orderId+'&merOrderId='+merOrderId,
            					 height:520,width:690, isResize: false,title:'交易详情'});    
        }
        
         function noLiquidation(orderId){
        	  $.ajax({
                  type: "POST",
                  dataType: "text",
                  url: '<s:url value="/orderInfo!noLiquidation.ac"/>',
                  data: {"orderId":orderId},
                  success: function (data) {
                     if(data=="true"){
                      	//window.parent.$.ligerDialog.success('标记异常订单成功');
                      	alert("不清算标记单成功!");
                     }else {
                     	//window.parent.$.ligerDialog.error('标记异常订单失败,请重试!');
                     	alert("不清算标记失败,请重试!");
                     }
                    // window.parent.$.ligerDialog.closeWaitting();
                    window.location.reload();
	                 // dialogClose();
                  }
	           });
         }
         
         function markOrderException(orderId,merOrderId)
        {
           window.parent.$.ligerDialog.confirm('是否标记此订单?', function (yes) {
	           if(yes){
				     $.ajax({
			                   type: "POST",
			                   dataType: "text",
			                   url: '<s:url value="/orderInfo!markOrderException.ac"/>',
			                   data: {"orderId":orderId,"merOrderId":merOrderId},
			                   success: function (data) {
			                      if(data=="true"){
			                       	//window.parent.$.ligerDialog.success('标记异常订单成功');
			                       	alert("标记异常订单成功!");
			                      }else {
			                      	//window.parent.$.ligerDialog.error('标记异常订单失败,请重试!');
			                      	alert("标记异常订单失败,请重试!");
			                      }
			                     // window.parent.$.ligerDialog.closeWaitting();
			                     window.location.reload();
				                 // dialogClose();
			                   }
				           });
				     }
	      });		
        }
        
         function f_search()
        {
			  var merSysId=$("#merSysId").val();  
			  var merOrderId=$("#merOrderId").val(); 
			  var busMerId=$("#busMerId").val(); 
			  var cardNo=$("#cardNo").val();
			  /* var agentIdL1=$("#agentIdL1").val();
			  var agentIdL2=$("#agentIdL2").val(); */
			  var transMerId=$("#transMerId").val();
			  var pattern = /^[\S]*$/; //不包含空格
		      var number = /^[0-9]{1,20}$/;	//数字	
		      /* if(agentIdL1!=""&&!number.test(agentIdL1)) {
					alert("一级代理商号只能是数字！");
					$("#agentIdL1").val("");  
					$("#agentIdL1").focus();
					return false;
			   }
			     if(agentIdL2!=""&&!number.test(agentIdL2)) {
					alert("二级代理商号只能是数字！");
					$("#agentIdL2").val("");  
					$("#agentIdL2").focus();
					return false;
			   } */
			   if(cardNo!=""&&!number.test(cardNo)) {
					alert("卡号只能是数字！");
					$("#cardNo").val("");  
					$("#cardNo").focus();
					return false;
			   }
			  if(merSysId!=""&&!number.test(merSysId)) {
					alert("机构商户号只能是数字！");
					$("#merSysId").val("");  
					$("#merSysId").focus();
					return false;
			   }
			   if(busMerId!=""&&!number.test(busMerId)) {
					alert("子商户号只能是数字！");
					$("#busMerId").val("");  
					$("#busMerId").focus();
					return false;
				}
				if(transMerId!=""&&!number.test(transMerId)) {
					alert("交易商户号只能是数字！");
					$("#transMerId").val("");
					$("#transMerId").focus();
					return false;
				}
				$("#form1").attr("target", '');
				 $("#form1").attr("action", '<s:url value="/pages/order/coreTransLog.jsp" />');
				 $("#form1").submit();
        }
        
         function f_clean(){
        	$("#busMerId").val("");
        	$("#orderId").val("");
        	$("#merOrderId").val("");
        	$("#userMobile").val("");
        	$("#settleDate").val("");
        	$("#merOrderTime").val("");
        	$("#transQid").val("");
        	$("#gwType").val("-1");
        	$("#orderStatus").val("-1"); 
        	$("#busType").val("-1");  
        	$("#merSysId").val("");
        	$("#terminalSerialNumber").val("");
        	$("#cardNo").val("");
        	/* $("#agentIdL1").val("");
        	$("#agentIdL2").val(""); */
        	$("#transMerId").val("");  	
        }
    </script>
</head>

<body style="padding: 4px; overflow: hidden;">
	<%@include file="/js/common.jsp"%>
	<div id="searchbar">
		<form id="form1"
			action="<s:url value="/pages/order/coreTransLog.jsp" />"
			method="post">
			<table width="80%">
				<tr>
					<td style="text-align: right;">
							交易时间：
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
						<td style="text-align: right;">
							子商户号：
						</td>
						<td style="text-align: left;">
							<div class="l-text" style="width: 130px;">
								<input type="text" name="busMerId" id="busMerId" value="${param.busMerId}" class="l-text-field"/>
							</div>
						</td>
						<td style="text-align: right;">
							卡 号：
						</td>
						<td style="text-align: left;">
							<div class="l-text" style="width: 130px;">
								<input type="text" name="cardNo" id="cardNo" value="${param.cardNo}" class="l-text-field"/>
							</div>
						</td>
						<td style="text-align: right;">
							交易状态：
						</td>
						<td>
							<select name="orderStatus" id="orderStatus" style="width: 130px;">
								<option value="-1">
									--请选择--
								</option>
								<option value="0" <c:if test="${param.orderStatus==0}">selected="selected"</c:if>>
									未处理
								</option>
								<option value="2" <c:if test="${param.orderStatus==2}">selected="selected"</c:if>>
									支付成功
								</option>
								<option value="1" <c:if test="${param.orderStatus==1}">selected="selected"</c:if>>
									支付失败
								</option>
							</select>
						</td>
				</tr>
				<tr>
						<td style="text-align: right;">
							撤销状态码：
						</td>                                                                                    
						<td style="text-align: left;">
							<select name="refundStatus" id="refundStatus" style="width: 130px;">
								<option value="-1">
									--请选择--
								</option>
								<option value="0" <c:if test="${param.refundStatus==0}">selected="selected"</c:if>>
									正常
								</option>
								<option value="1" <c:if test="${param.refundStatus==1}">selected="selected"</c:if>>
									已撤销
								</option>
								<option value="2" <c:if test="${param.refundStatus==2}">selected="selected"</c:if>>
									已冲正
								</option>
							</select>
						</td>
						<td style="text-align: right;">
							机构商户号：
						</td>
						<td style="text-align: left;"> 
							<div class="l-text" style="width: 130px;">
								<input type="text" name="merSysId" id="merSysId" value="${param.merSysId}" class="l-text-field"/>
							</div>
						</td>
						<td style="text-align: right;">交易商户号：</td>
						<td style="text-align: left;">
							<div class="l-text" style="width: 130px;">
								<input type="text" name="transMerId" id="transMerId" maxlength="20"
									value="${param.transMerId}" class="l-text-field" />
							</div>
						</td>
						<td style="text-align: right;">transSource：</td>
						<td style="text-align: left;">
							<div class="l-text" style="width: 130px;">
								<input type="text" name="t0Status" id="t0Status" maxlength="20"
									value="${param.t0Status}" class="l-text-field" />
							</div>
						</td>
						
						<!-- 
							<select name="t0Status" id="t0Status" style="width: 130px;">
								<option value="-1">
									--请选择--
								</option>
								<option value="100" <c:if test="${param.t0Status==100}">selected="selected"</c:if>>
									未开通
								</option>
								<option value="101" <c:if test="${param.t0Status==101}">selected="selected"</c:if>>
									已开通
								</option>
							</select>
							 -->
							 
							
				</tr>
				<tr>
					<td colspan="2"></td>
					<td style="text-align: left;"><input id="btn" type="button"
						value="查 询" onclick="f_search()" class="l-button" /></td>
					<td style="text-align: left;"><input id="btn2" type="button"
						value="重 置" onclick="f_clean()" class="l-button" /> 
					<input id="btn3" type="button" value="导 出" onclick="f_search_downLoad_excel()" class="l-button"/>
					</td>
					</tr>
			</table>
		</form>
	</div>
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<div id="maingrid4" style="margin: 0; padding: 0"></div>
	<div style="display: none;"></div>
</body>
</html>
