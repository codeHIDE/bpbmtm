<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>转账交易查询</title>
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
            { display: '操作', name: 'null', width: '6%', align: 'center',render: function (rowdata, rowindex, value)
           	 	{
                    var h = "";
                    if (!rowdata._editing)
                    {
                    	<c:if test="${purview['2031'] == '2031'}">
                          h += "	<a onclick='f_open1("+rowdata.orderId+",\""+rowdata.merOrderId+"\")' style='cursor:pointer;color:blue;'>详情</a> ";
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
                	merOrderTime:$("#merOrderTime").val(),
                	orderStatus:$("#orderStatus").val(),
                	terminalId:$("#terminalSerialNumber").val(),
                	cardNo:$("#cardNo").val()/* ,
                	agentIdL1:$("#agentIdL1").val(),
                	agentIdL2:$("#agentIdL2").val() */
                },
                url:'<s:url value="/orderInfo!selectTransfer.ac"/>'
            });
            $("#pageloading").hide();
            $('#maingrid4').ligerGrid().set('dataAction','server');
            $("#merOrderTime").ligerDateEditor({ showTime: true,format: "yyyy-MM-dd"});
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
        function f_search_downLoad_excel() {
        	 $("#form1").attr("action", '<s:url value="/orderInfo!download_excel_news.ac"/>');
			 $("#form1").attr("target", '_blank');
			 $("#form1").submit();
		}
         function f_search()
        {
          	 var merSysId=$("#busMerId").val();  
			 var orderId=$("#orderId").val(); 
			 var terminalId=$("#terminalSerialNumber").val();
			 var cardNo=$("#cardNo").val();
			 /* var agentIdL1=$("#agentIdL1").val();
			 var agentIdL2=$("#agentIdL2").val(); */ 
			 var pattern = /^[\S]*$/; //不包含空格
			 var number = /^[0-9]{1,20}$/;	//数字	
			 if(merSysId!=""&&!number.test(merSysId)) {
				alert("机构商户号只能是数字！");
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
			$("#form1").attr("action", '<s:url value="/pages/order/TransferList.jsp"/>');
			 $("#form1").attr("target", '_self');
			$("#form1").submit();
        }
        
       function f_clean(){
        	$("#busMerId").val("");
        	$("#orderId").val("");
        	$("#merOrderId").val("");
        	$("#userMobile").val("");
        	$("#terminalSerialNumber").val("");
        	$("#merOrderTime").val("");
        	$("#orderStatus").val("-1"); 
        	$("#busType").val("-1");  
        	/* $("#agentIdL1").val("");
        	$("#agentIdL2").val(""); */ 
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
	<s:if test="message!=null">
		<script>
			alert("导出内容大于2000条，请添加查询条件！");
		</script>
	</s:if>
	<%@include file="/js/common.jsp" %>
		<div id="searchbar">
			<form id="form1" action="<s:url value="/pages/order/TransferList.jsp" />" method="post">
				<table width="95%">
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
							终端号：
						</td>
						<td style="text-align: left;"> 
							<div class="l-text" style="width: 130px;">
								<input type="text" name="terminalSerialNumber" id="terminalSerialNumber"  value="${param.terminalSerialNumber}" class="l-text-field"/>
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
					</tr>
					<tr>	
					<td style="text-align: right;">
							商户交易时间：
						</td>
						<td style="text-align: left;"> 
							<input type="text" name="merOrderTime" id="merOrderTime"  value="${param.merOrderTime}"/>
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
					</tr>
					<tr>
						<td colspan="8" style="text-align: center;">
								<input id="btn" type="button" value="查 询" onclick="f_search()" class="l-button"/>
								<input id="btn2" type="button" value="重 置" onclick="f_clean()" class="l-button"/>
							<c:if test="${purview['2032'] == '2032'}">
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
