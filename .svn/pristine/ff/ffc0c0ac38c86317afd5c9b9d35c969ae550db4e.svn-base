<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>应用交易查询</title>
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
		var grid;
        $(function (){
           grid= $("#maingrid4").ligerGrid({
            columns: [
        { display: '订单号', name: 'orderId', align: 'left', width: '11%',align: 'center' },
             { display: '商户简称(商户号)', name: 'shortName', align: 'center', width: '13%',render:function(rowdata,rowindex){
	            	return rowdata.shortName+"("+rowdata.subMerId+")";
	            } },
            { display: '商户订单号', name: 'merOrderId', width: '10%', align: 'center' },
            { display: '商户订单时间', name: 'createTime',width: '12%', heightAlign: 'center' },//merOrderTime
            { display: '交易金额', name: 'merAmt', width: '6%', align: 'right', type:'currency'}, 
            { display: '交易类型', name: 'transType', width: '5%', align: 'center',render:function(rowdata,rowindex){
            	if(rowdata.transType=='01'){return '消费';}
            	else if(rowdata.transType=='08'){return '余额查询';}
            	else if(rowdata.transType=='07'){return '还款';}
            	else if(rowdata.transType=='04'){return '消费撤销';}
            	else if(rowdata.transType=='05'){return '消费退款';}
            	else if(rowdata.transType=='06'){return '转账';}
            	else if(rowdata.transType=='09'){return '手机充值';}
            } }, 
            { display: '交易状态', name: 'orderStatus', width: '5%', align: 'center', render:function(rowdata,rowindex){
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
            { display: '操作', name: 'null', width: '6%', align: 'center',render: function (rowdata, rowindex, value,record)
           	 	{
                    var h = "";
                    if (!rowdata._editing)
                    {
                    	<c:if test="${purview['2041'] == '2041'}">
                          h += "<a onclick='f_open1("+rowdata.orderId+",\""+rowdata.merOrderId+"\")' style='cursor:pointer;color:blue;'>详情</a> ";
						</c:if>
                    }
                    return h;
                } } 
                ],  
                totalRender:f_totalRender,
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
                parms:{
                	busMerId:$("#busMerId").val(),
                	orderId:$("#orderId").val(),
                	merOrderId:$("#merOrderId").val(),
                	userMobile:$("#userMobile").val(),
                	transType:$("#transType").val(),
                	terminalSerialNumber:$("#terminalSerialNumber").val(),
                	orderStatus:$("#orderStatus").val(),
                	startTime:$("#startTime").val(),
                	endTime:$("#endTime").val(),
                	merSysId:$("#merSysId").val(),
                	cardNo:$("#cardNo").val(),
                	busType:$("#busType").val()/* ,
                	agentIdL1:$("#agentIdL1").val(),
                	agentIdL2:$("#agentIdL2").val() */
                },
                url:'<s:url value="/orderInfo!selectApplyList.ac"/>'
            
            });
            $("#pageloading").hide();
            $('#maingrid4').ligerGrid().set('dataAction','server');
            //$("#settleDate").ligerDateEditor({ showTime: true,format: "yyyy-MM-dd"});
            $("#startTime").ligerDateEditor({ showTime: true,width:"130"});
            $("#endTime").ligerDateEditor({ showTime: true,width:"130"});
        });
        
        function f_totalRender(data)
		{
		    return "交易金额合计:"+data.sumMerAmt+"&nbsp;&nbsp;&nbsp;交易手续费合计:"+data.sumTransFee; 
		}
        function itemclick()
        {
            g.options.data = $.extend(true,{}, CustomersData);
            g.showFilter();
        }
         function checkInfo() {
        	  var busMerId=$("#busMerId").val(); 
			  var orderId=$("#orderId").val(); 
			  var merOrderId = $("#merOrderId").val();
			  var userMobile=$("#userMobile").val();
			  var merSysId=$("#merSysId").val(); 
			  var cardNo=$("#cardNo").val(); 
			  /* var agentIdL1 = $("#agentIdL1").val();
			  var agentIdL2 =$("#agentIdL2").val(); */ 
			  var startTime = $("#startTime").val();
			  var endTime = $("#endTime").val();
			  var terminalSerialNumber = $("#terminalSerialNumber").val();
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
			  }
			  if(merOrderId!=""&&!number.test(merOrderId)) {
					alert("商户订单号只能是数字！");
					$("#merOrderId").val("");  
					$("#merOrderId").focus();
					return false;
			  }
			  if(userMobile!=""&&!number.test(userMobile)) {
					alert("手机号只能是数字！");
					$("#userMobile").val("");  
					$("#userMobile").focus();
					return false;
			  }
			  if(merSysId!=""&&!number.test(merSysId)) {
					alert("机构商号只能是数字！");
					$("#merSysId").val("");
					$("#merSysId").focus();
					return false;
			  }
			  if(cardNo!=""&&!number.test(cardNo)) {
					alert("卡号只能是数字！");
					$("#cardNo").val("");
					$("#cardNo").focus();
					return false;
			  }
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
			  if(startTime != "" && endTime == "") {
					alert("请输入结束时间！");
					$("#endTime").val("");
					$("#endTime").focus();
					return false;
			  }
			  if(endTime != "" && startTime == "") {
					alert("请输入开始时间！");
					$("#startTime").val("");
					$("#startTime").focus();
					return false;
			  }
			  if(terminalSerialNumber!=""&&!number.test(terminalSerialNumber)) {
					alert("子商户号只能是数字！");
					$("#terminalSerialNumber").val("");  
					$("#terminalSerialNumber").focus();
					return false;
			  }
        	return true;
        }
        function f_search()
        {
        	 var res = checkInfo();
        	 if(res== true){
				 $("#form1").attr("target", '_self');
		   		 $("#form1").attr("action", '<s:url value="/pages/order/applyList.jsp"/>');
			 	 $("#form1").submit();     	 
        	 }
        }
        
        function f_search_downLoad_excel() {
         	 $("#form1").attr("action", '<s:url value="/orderInfo!downloadAppayExcel.ac"/>');
			 $("#form1").attr("target", '_blank');
			 $("#form1").submit();
		}
        
        
        
        function f_clean(){
        	$("#busMerId").val("");
        	$("#orderId").val("");
        	$("#merOrderId").val("");
        	$("#userMobile").val("");
        	$("#merOrderTime").val("");
        	$("#transType").val("09");
        	$("#terminalSerialNumber").val("");
        	$("#orderStatus").val("-1");
        	$("#startTime").val("");
        	$("#endTime").val("");
        	$("#merSysId").val("");
        	/* $("#agentIdL1").val("");
        	$("#agentIdL2").val(""); */
        	$("#busType").val("");  
        	$("#shipmentStatus").val(""); 
        	$("#cardNo").val("");  
        }
        
         function f_open1(orderId,merOrderId)
        {
            $.ligerDialog.open({ url: '<s:url value="/orderInfo!selectOrderDetail.ac"/>?orderId='+orderId+'&merOrderId='+merOrderId,
            					  height:520,width:690, isResize: false,title:'交易详情'});    
        }
        
        
    </script>
	</head>
	<body style="padding: 4px; overflow: hidden;">
	<%@include file="/js/common.jsp" %>
		<div id="searchbar">
		<form id="form1" action="<s:url value="/pages/order/applyList.jsp" />" method="post">
				<table width="95%" border="0">
					<thead></thead>
					<tr>
						<td style="text-align: right;">
							子商户号：
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
							商户订单号：
						</td>
						<td style="text-align: left;">
							<div class="l-text" style="width: 130px;">
								<input type="text" name="merOrderId" id="merOrderId" value="${param.merOrderId}" class="l-text-field"/>
							</div>
						</td>
						<td style="text-align: right;">
							手机号：
						</td>
						<td style="text-align: left;">
							<div class="l-text" style="width: 130px;">
								<input type="text" name="userMobile" id="userMobile" value="${param.userMobile}" class="l-text-field"/>
							</div>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;">
							机构商户号：
						</td>
						<td style="text-align: left;"> 
							<div class="l-text" style="width: 130px;">
								<input type="text" name="merSysId" id="merSysId" value="${param.merSysId}" class="l-text-field"/>
							</div>
						</td>
						<td style="text-align: right;">
							商户交易时间：
						</td>
						<td style="text-align: left;">
							<div style="width: 130px;float: left;"> 
								<input type="text" name="startTime" id="startTime"  value="${param.startTime}"/>
							</div>
							<span style="float: left;">&nbsp;&nbsp;-&nbsp;&nbsp;</span>
							<div style="width: 140px;float: left;">  
								<input type="text" name="endTime" id="endTime"  value="${param.endTime}" />
							</div>
						</td>
						
						<td style="text-align: right;">
							交易类型：
						</td>
						<td>
							<select name="transType" id="transType" style="width: 130px;">
								<option value="09">
									手机充值
								</option>
								<!-- 
								<option value="09" <c:if test="${param.transType=='09'}">selected="selected"</c:if>>
									手机充值
								</option>
								 -->
							</select>
						</td>
						<td style="text-align: right;">
							终端号：
						</td>
						<td style="text-align: left;">
							<div class="l-text" style="width: 130px;">
								<input type="text" name="terminalSerialNumber" id="terminalSerialNumber" value="${param.terminalSerialNumber}" class="l-text-field"/>
							</div>
						</td>
					</tr>
					<tr>
						
						<td style="text-align: right;">
							交易状态：
						</td>
						<td>
							<select name="orderStatus" id="orderStatus" style="width: 130px;">
								<option value="-1">
									--请选择--
								</option>
								<option value="0" <c:if test="${param.orderStatus==0}">selected="selected"</c:if>>
									未支付
								</option>
								<option value="1" <c:if test="${param.orderStatus==1}">selected="selected"</c:if>>
									支付成功
								</option>
								<option value="2" <c:if test="${param.orderStatus==2}">selected="selected"</c:if>>
									支付失败
								</option>
								<option value="3" <c:if test="${param.orderStatus==3}">selected="selected"</c:if>>
									处理中
								</option>
								<option value="4" <c:if test="${param.orderStatus==4}">selected="selected"</c:if>>
									冻结
								</option>								
							</select>
						</td>
						<%-- <td style="text-align: right;">
							一级代理商号：
						</td>
						<td style="text-align: left;">
							<div class="l-text" style="width: 130px;">
								<input type="text" name="agentIdL1" id="agentIdL1" maxlength="20" value="${param.agentIdL1}" class="l-text-field"/>
							</div>
						</td>
						<td style="text-align: right;">
							二级代理商号：
						</td>
						<td style="text-align: left;">
							<div class="l-text" style="width: 130px;">
								<input type="text" name="agentIdL2" id="agentIdL2" maxlength="20" value="${param.agentIdL2}" class="l-text-field"/>
							</div>
						</td> --%>                                                                                      
						<td style="text-align: right;">
							卡 号：
						</td>
						<td style="text-align: left;">
							<div class="l-text" style="width: 130px;">
								<input type="text" name="cardNo" id="cardNo" value="${param.cardNo}" class="l-text-field"/>
							</div>
						</td>
						<td style="text-align: right;">
							业务类型：
						</td>
						<td style="text-align: left;">
							<select name="busType" id="busType" style="width: 130px;">
								<option value="-1">
									--请选择--
								</option>
								<option value="05"
									<c:if test="${param.busType=='05'}">selected="selected"</c:if>>
									有磁有密
								</option>
							</select>
						</td>
					</tr>
					<tr>
						   
						<td style="text-align: center;" colspan="6">
							<input id="btn" type="button" value="查 询" onclick="f_search()" class="l-button"/>
							<input id="btn2" type="button" value="重 置" onclick="f_clean()" class="l-button"/>
						<c:if test="${purview['2042'] == '2042'}">
							<input id="btn3" type="button" value="导 出" onclick="f_search_downLoad_excel()" class="l-button"/>
						</c:if>
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
