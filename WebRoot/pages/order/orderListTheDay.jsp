<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>当天交易查询</title>
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
            { display: '订单号', name: 'orderId', align: 'left', width: '11%',align: 'center' },
             { display: '商户简称(商户号)', name: 'shortName', align: 'center', width: '13%',render:function(rowdata,rowindex){
	            	return rowdata.shortName+"("+rowdata.subMerId+")";
	            } },
            { display: '商户订单号', name: 'merOrderId', width: '10%', align: 'center' },
         	{ display: '通道商户号', name: 'transMerId', width: '10%', align: 'center' },
            { display: '商户订单时间', name: 'createTime',width: '12%', heightAlign: 'center' },//merOrderTime
            { display: '交易金额', name: 'merAmt', width: '6%', align: 'right', type:'currency',render:function(rowdata,rowindex){
            	var amt = rowdata.merAmt;
            	amt = amt/100;
            	if(amt>=1000){
            		return "<font color='blue'>"+amt.toFixed(2)+"</font>";
            	}
            	return amt.toFixed(2);
             } 
            	
            }, 
            { display: '交易类型', name: 'transType', width: '5%', align: 'center',render:function(rowdata,rowindex){
            	if(rowdata.transType=='01'){
	            	if(rowdata.orderRateType=='01'){
						return '消费-扣率';            		
	            	}else if(rowdata.orderRateType=='02'){
	            		return '消费-封顶';   
	            	}else if(rowdata.orderRateType=='05'){
	            		return '消费-积分';  
	            	}else if(rowdata.orderRateType=='10'){
	            		return '消费-D0代付';  
	            	}else if(rowdata.orderRateType=='11'){
	            		return '会员消费';  
	            	}else if(rowdata.orderRateType=='12'){
	            		return '新封顶';  
	            	}else if(rowdata.orderRateType=='15'){
	            		return '急速到账';  
	            	}else if(rowdata.orderRateType=='17'){
	            		return '通道直付';  
	            	}else if(rowdata.orderRateType=='31'){
	            		return '香港(境外)';  
	            	}else if(rowdata.orderRateType=='32'){
	            		return '英国(境外)';  
	            	}else if(rowdata.orderRateType=='33'){
	            		return '美国(境外)';  
	            	}else if(rowdata.orderRateType=='34'){
	            		return '法国(境外)';  
	            	}else if(rowdata.orderRateType=='35'){
	            		return '日本(境外)';  
	            	}else if(rowdata.orderRateType=='36'){
	            		return '新西兰(境外)';  
	            	}else if(rowdata.orderRateType=='37'){
	            		return '马来西亚(境外)';  
	            	}else if(rowdata.orderRateType=='38'){
	            		return '泰国(境外)';  
	            	}
	            	
            	}
            	else if(rowdata.transType=='08'){return '余额查询';}
            	else if(rowdata.transType=='07'){return '还款';}
            	else if(rowdata.transType=='04'){return '消费撤销';}
            	else if(rowdata.transType=='05'){return '消费退款';}
            	else if(rowdata.transType=='06'){return '转账';}
            	else if(rowdata.transType=='09'){return '手机充值';}
            } }, 
            { display: '交易状态', name: 'order_status', width: '5%', align: 'center', render:function(rowdata,rowindex){
            	if(rowdata.orderStatus==1){return '支付成功';}
            	else if(rowdata.orderStatus==2){return '支付失败';}
            	else if(rowdata.orderStatus==3){return '处理中';}
            	else if(rowdata.orderStatus==4){return '冻结';}
            	else if(rowdata.orderStatus==0){return '未支付';}
            	else return '未知';
            } }, 
            { display: '返回码描述', name: 'respDesc', width: '12%', align: 'center' },
            { display: '卡号', name: 'cardNo', width: '9%', align: 'center' },
            { display: '卡类型', name: 'cardType', width: '5%', align: 'center',render:function(rowdata,rowindex){
            	if(rowdata.cardType=='1'){return '借记卡';}
            	else if(rowdata.cardType=='2'){return '信用卡';}
            	else if(rowdata.cardType=='3'){return '准贷记卡';}
            	else if(rowdata.cardType=='4'){return '储值卡';}
            } }, 
            { display: '终端号', name: 'terminalId', width: '10%', align: 'center' },
            { display: '操作', name: 'null', width: '8%', align: 'center',render: function (rowdata, rowindex, value)
           	 	{
                    var h = "";
                    if (!rowdata._editing)
                    {
                    	<c:if test="${purview['2011'] == '2011'}">
                        	 h += "	<a onclick='f_open1("+rowdata.orderId+",\""+rowdata.merOrderId+"\")' style='cursor:pointer;color:blue;'>详情</a> ";
						</c:if>
						<c:if test="${purview['2012'] == '2012'}">
						 if(rowdata.reserved != -1){
							 h += "	<a onclick='markOrderException("+rowdata.orderId+",\""+rowdata.merOrderId+"\")' style='cursor:pointer;color:blue;'>标记异常</a> ";
							 h += "	<a onclick='noLiquidation("+rowdata.orderId+")' style='cursor:pointer;color:blue;'>不清算</a> ";
						 }
						 </c:if>
                    }
                    return h;
                } } 
                ],  
                totalRender:f_totalRender,
                rownumbers:true,
                width: '100%', 
                height: '100%',
                 rowAttrRender: function (rowdata,rowid){
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
                }, 
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
                	orderId:$("#orderId").val(),
                	merOrderId:$("#merOrderId").val(),
                	userMobile:$("#userMobile").val(),
                	transType:$("#transType").val(),
                	cardNo:$("#cardNo").val(),
                	orderStatus:$("#orderStatus").val(),
                	merSysId:$("#merSysId").val(),
                	/* agentIdL1:$("#agentIdL1").val(),
                	agentIdL2:$("#agentIdL2").val(), */
                	terminalSerialNumber:$("#terminalSerialNumber").val(),
                	transMerId : $("#transMerId").val(),
                	orderRateType : $("#orderRateType").val(),
                	signStatus : $("#signStatus").val(),
                	orderStatictis : $("#orderStatictis").val()
                },
                url:'<s:url value="/orderInfo!selectOrderTheDay.ac"/>'
            });
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
			  var orderId=$("#orderId").val(); 
			  var userMobile=$("#userMobile").val();
			  var terminalSerialNumber=$("#terminalSerialNumber").val();
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
			   if(terminalSerialNumber!=""&&!number.test(terminalSerialNumber)) {
					alert("终端号只能是数字！");
					$("#terminalSerialNumber").val("");  
					$("#terminalSerialNumber").focus();
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
				if(orderId!=""&&!number.test(orderId)) {
					alert("订单号只能是数字！");
					$("#orderId").val("");  
					$("#orderId").focus();
					return false;
				}
				if(userMobile!=""&&!number.test(userMobile)) {
					alert("手机号只能是数字！");
					$("#userMobile").val("");  
					$("#userMobile").focus();
					return false;
				}
				if(merOrderId!=""&&!number.test(merOrderId)) {
					alert("商户订单号只能是数字！");
					$("#merOrderId").val("");
					$("#merOrderId").focus();
					return false;
				}
				if(transMerId!=""&&!number.test(transMerId)) {
					alert("交易商户号只能是数字！");
					$("#transMerId").val("");
					$("#transMerId").focus();
					return false;
				}
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
        	$("#transType").val("-1");
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
			action="<s:url value="/pages/order/orderListTheDay.jsp" />"
			method="post">
			<table width="80%">
				<tr>
					<td style="text-align: right;">子商户号：</td>
					<td style="text-align: left;">
						<div class="l-text" style="width: 130px;">
							<input type="text" name="busMerId" id="busMerId"
								value="${param.busMerId}" class="l-text-field" />
						</div>
					</td>
					<td style="text-align: right;">订单号：</td>
					<td style="text-align: left;">
						<div class="l-text" style="width: 130px;">
							<input type="text" name="orderId" id="orderId" maxlength="20"
								value="${param.orderId}" class="l-text-field" />
						</div>
					</td>
					<td style="text-align: right;">商户订单号：</td>
					<td style="text-align: left;">
						<div class="l-text" style="width: 130px;">
							<input type="text" name="merOrderId" id="merOrderId"
								value="${param.merOrderId}" class="l-text-field" />
						</div>
					</td>
					<td style="text-align: right;">手机号：</td>
					<td style="text-align: left;">
						<div class="l-text" style="width: 130px;">
							<input type="text" name="userMobile" id="userMobile"
								value="${param.userMobile}" class="l-text-field" />
						</div>
					</td>
				</tr>
				<tr>

					<td style="text-align: right;">交易类型：</td>
					<td><select name="transType" id="transType" style="width: 130px;">
							<option value="-1">--请选择--</option>
							<option value="01"
								<c:if test="${param.transType=='01'}">selected="selected"</c:if>>
								消费</option>
							<option value="04"
								<c:if test="${param.transType=='04'}">selected="selected"</c:if>>
								消费撤销</option>
							<option value="05"
								<c:if test="${param.transType=='05'}">selected="selected"</c:if>>
								消费退款</option>
							<option value="06"
								<c:if test="${param.transType=='06'}">selected="selected"</c:if>>
								转账</option>
							<option value="07"
								<c:if test="${param.transType=='07'}">selected="selected"</c:if>>
								还款</option>
							<option value="08"
								<c:if test="${param.transType=='08'}">selected="selected"</c:if>>
								余额查询</option>
							<option value="09"
								<c:if test="${param.transType=='09'}">selected="selected"</c:if>>
								手机充值</option>
					</select></td>
					<td style="text-align: right;">交易状态：</td>
					<td><select name="orderStatus" id="orderStatus" style="width: 130px;">
							<option value="-1">--请选择--</option>
							<option value="0"
								<c:if test="${param.orderStatus==0}">selected="selected"</c:if>>
								未支付</option>
							<option value="1"
								<c:if test="${param.orderStatus==1}">selected="selected"</c:if>>
								支付成功</option>
							<option value="2"
								<c:if test="${param.orderStatus==2}">selected="selected"</c:if>>
								支付失败</option>
							<option value="3"
								<c:if test="${param.orderStatus==3}">selected="selected"</c:if>>
								处理中</option>
							<option value="4"
								<c:if test="${param.orderStatus==4}">selected="selected"</c:if>>
								冻结</option>
					</select></td>
					<td style="text-align: right;">终端号：</td>
					<td style="text-align: left;">
						<div class="l-text" style="width: 130px;">
							<input type="text" name="terminalSerialNumber"
								id="terminalSerialNumber" value="${param.terminalSerialNumber}"
								class="l-text-field" />
						</div>
					</td>
					<td style="text-align: right;">机构商户号：</td>
					<td style="text-align: left;">
						<div class="l-text" style="width: 130px;">
							<input type="text" name="merSysId" id="merSysId" maxlength="20"
								value="${param.merSysId}" class="l-text-field" />
						</div>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">卡 号：</td>
					<td style="text-align: left;">
						<div class="l-text" style="width: 130px;">
							<input type="text" name="cardNo" id="cardNo"
								value="${param.cardNo}" class="l-text-field" />
						</div>
					</td>
					<%-- <td style="text-align: right;">一级代理商号：</td>
					<td style="text-align: left;">
						<div class="l-text" style="width: 130px;">
							<input type="text" name="agentIdL1" id="agentIdL1" maxlength="20"
								value="${param.agentIdL1}" class="l-text-field" />
						</div>
					</td>
				
					<td style="text-align: right;">二级代理商号：</td>
					<td style="text-align: left;">
						<div class="l-text" style="width: 130px;">
							<input type="text" name="agentIdL2" id="agentIdL2" maxlength="20"
								value="${param.agentIdL2}" class="l-text-field" />
						</div>
					</td> --%>
					
					<td style="text-align: right;">交易商户号：</td>
					<td style="text-align: left;">
						<div class="l-text" style="width: 130px;">
							<input type="text" name="transMerId" id="transMerId" maxlength="20"
								value="${param.transMerId}" class="l-text-field" />
						</div>
					</td>
					<td style="text-align: right;">费率类型：</td>
					<td><select name="orderRateType" id="orderRateType" style="width: 130px;">
							<option value="-1">--请选择--</option>
							<option value="01"
								<c:if test="${param.orderRateType==01}">selected="selected"</c:if>>
								扣率</option>
							<option value="02"
								<c:if test="${param.orderRateType==02}">selected="selected"</c:if>>
								封顶</option>
							<option value="05"
								<c:if test="${param.orderRateType==05}">selected="selected"</c:if>>
								积分</option>
							<option value="10"
								<c:if test="${param.orderRateType==10}">selected="selected"</c:if>>
								D0代付</option>
							<option value="11"
								<c:if test="${param.orderRateType==11}">selected="selected"</c:if>>
								会员消费</option>
							<option value="12"
								<c:if test="${param.orderRateType==12}">selected="selected"</c:if>>
								新封顶</option>
							<option value="15"
								<c:if test="${param.orderRateType==15}">selected="selected"</c:if>>
								急速到账</option>
							<option value="17"
								<c:if test="${param.orderRateType==17}">selected="selected"</c:if>>
								通道直付</option>
							<option value="31"
								<c:if test="${param.orderRateType==31}">selected="selected"</c:if>>
								香港(境外)</option>
							<option value="32"
								<c:if test="${param.orderRateType==32}">selected="selected"</c:if>>
								英国(境外)</option>
							<option value="33"
								<c:if test="${param.orderRateType==33}">selected="selected"</c:if>>
								美国(境外)</option>
							<option value="34"
								<c:if test="${param.orderRateType==34}">selected="selected"</c:if>>
								法国(境外)</option>
								<option value="35"
								<c:if test="${param.orderRateType==35}">selected="selected"</c:if>>
								日本(境外)</option>
								<option value="36"
								<c:if test="${param.orderRateType==36}">selected="selected"</c:if>>
								新西兰(境外)</option>
								<option value="37"
								<c:if test="${param.orderRateType==37}">selected="selected"</c:if>>
								马来西亚(境外)</option>
								<option value="38"
								<c:if test="${param.orderRateType==38}">selected="selected"</c:if>>
								泰国(境外)</option>
					</select></td>
					<td style="text-align: right;">签名状态：</td>
					<td><select name="signStatus" id="signStatus" style="width: 130px;">
							<option value="-1">--请选择--</option>
							<option value="1"
								<c:if test="${param.signStatus==1}">selected="selected"</c:if>>
								错误签名</option>
							<option value="2"
								<c:if test="${param.signStatus==2}">selected="selected"</c:if>>
								正确签名</option>
							<option value="3"
								<c:if test="${param.signStatus==3}">selected="selected"</c:if>>
								补充签名</option>
							<option value="9"
								<c:if test="${param.signStatus==9}">selected="selected"</c:if>>
								未签名</option>
					</select></td>
					<td style="text-align: right;">清算状态：</td>
					<td><select name="orderStatictis" id="orderStatictis" style="width: 130px;">
							<option value="-1">--请选择--</option>
							<option value="1">
								<%-- <c:if test="${param.signStatus==1}">selected="selected"</c:if>> --%>
								不清算状态</option>
							<option value="2">
								<%-- <c:if test="${param.signStatus==2}">selected="selected"</c:if>> --%>
							        进行清算状态</option>
					</select></td>
				</tr>
				<tr>
					<td colspan="2"></td>
					<td style="text-align: left;"><input id="btn" type="button"
						value="查 询" onclick="f_search()" class="l-button" /></td>
					<td style="text-align: left;"><input id="btn2" type="button"
						value="重 置" onclick="f_clean()" class="l-button" /> <input
						id="checkbox" type="checkbox" value="自动刷新" class="checkbox" />&nbsp;&nbsp;自动刷新
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
