<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>配置机构商户交易费率</title>
    	<link href="<s:url value='/js/jqui/ligerUI/skins/Aqua/css/ligerui-all.css'/>" rel="stylesheet" type="text/css" />
		<link href="<s:url value='/js/jqui/ligerUI/skins/ligerui-icons.css'/>" rel="stylesheet" type="text/css" />
		<script src="<s:url value='/js/jqui/jquery/jquery-1.5.2.min.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/core/base.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerDialog.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerGrid.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerDrag.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerComboBox.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerResizable.js'/>" type="text/javascript"></script>
		<link href="<s:url value='/js/jqui/ligerUI/skins/Gray/css/all.css'/>" rel="stylesheet" type="text/css" />
		<script src="<s:url value='/js/common.js'/>"></script>
		<script src="<s:url value='/js/CheckLength.js'/>"></script>
		<style type="text/css">
			tr{
				height:35px;
			}
			.tdata td{text-align: center;}
		</style>
		<script type="text/javascript">
			var proData = 	[{ id: '01', text: '消费' },
            				{ id: '04', text: '撤销' },
			             	{ id: '05', text: '退款' },
			              	{ id: '06', text: '转账'},
			              	{ id: '07', text: '信用卡还款' },
			              	{ id: '08', text: '余额查询' },
			              	{ id: '09', text: '手机充值' }];
			$(
			function(){
				var a = $("#actionList").ligerComboBox({ data: proData, isMultiSelect: true });
				var b = $("#transNotifyActionList").ligerComboBox({ data: proData, isMultiSelect: true });
				var configCodeJson = $("#configCodeJson").val();
				var transNotifyActionList = $("#transNotifyActionList1").val();
				b.selectValue(transNotifyActionList);
				a.selectValue(configCodeJson);
			}
			);
			function getBankBehalfAccountInf(){
					if($("#clearType").attr("checked")==true){
					   $.post("<s:url value='/bank!getBankBehalfAccountInfoAllList.ac'/>",function(data){
					   		var bankList=eval(data);
					   		$.each(bankList,function(index){
					   			$("#bankName").append("<option value='"+bankList[index].bankId+"'>"+bankList[index].payBankName+"</option>");
					   		})
					   });
					}else{
						$("#bankName").empty();
						$("#bankName").html("<option>--请选择--</option>");
					}
						
			}
			
			function setTrans(){
				var busInfoStr='',busProductStr='',right='';
				if($("#right").attr("checked")==true){
					right='125';
				}
				//$('input[name="busInfo"]:checked').each(function(){
				//	if(busInfoStr != '')busInfoStr+="|";
				//	busInfoStr += $(this).val();    
				//});    
				$('input[name="busProduct"]:checked').each(function(){
					if(busProductStr != '')busProductStr+="|";
					busProductStr += $(this).val();    
				});   
				busInfoStr = '05';
				if(busProductStr == ''){
					alert("请选择产品类别");
				//}else if(busInfoStr == ''){
				//	alert("请选择业务类别");    
				}else{
					var bill = $("#billCycle").val() + "|" + $("#billCycle2").val();
					$.post("<s:url value='/merchantInfo!updateBusInfo.ac'/>",
					{	merSysId:$("#merSysId").val(),
						perCardAmount:$("#perCardAmount").val(),
						cardDayAmount:$("#cardDayAmount").val(),
						cardDayCount:$("#cardDayCount").val(),
						right:right,
						debitPerCardAmount:$("#debitPerCardAmount").val(),
						debitDayAmount:$("#debitDayAmount").val(),
						debitDayCount:$("#debitDayCount").val(),
						platRate:$("#platRate").val(),billCycle:bill,
						busInfo:busInfoStr,busProduct:busProductStr},
					function (data){
						if(data == 'succ'){
							alert("结算类别信息更新成功!");
							location.reload();
						}else{
							alert("结算类别信息更新失败!");
						}
					},"text");
				}
			}
			function validate(){
				var terminalType = $("#terminalType").val();
				//var transNotifyStatus = $("#transNotifyStatus").val();
				var busInfoStr='',busProductStr='',right='';
				if($("#right").attr("checked")==true){
					right='125';
				}
				/*$('input[name="merTrans.busType"]:checked').each(function(){
					if(busInfoStr != '')busInfoStr+="|";
					busInfoStr += $(this).val();    
				});    
				 if(busInfoStr == ''){
					alert("请选择交易类型");   
					return false; 
				}
				if(transNotifyStatus == '1'){
					if(	!len('transNotifyUrl',100,'All','同步URL地址')){
						return false;
					}
				}*/
				var basicRegion1 = $("#basicRegion1").val();
				var basicRegion2 = $("#basicRegion2").val();
				var basicMcc = $("#basicMcc").val();
				if(basicRegion1 == -1 || basicRegion2 == -1){
					alert("请选择基础地区");
					return false;
				}
				if(basicMcc == -1){
					alert("请选择基础MCC");
					return false;
				}
				if(	len('lowsetTransAmt',5,'NS','最低交易金额') &&
					len('merRate2',5,'NS','费率-封顶') &&
					len('merRate1',5,'NS','费率-扣率') &&
					len('merRateNoTop', 5, 'NS', '积分扣率') &&
					len('merHighestFee',5,'NS','封顶值') &&
					//len('merLowestFee',5,'NS','封底值') &&
					len('merProfitRate',5,'NS','分润比') &&
					//len('basicRate',5,'NS','子商户基础费率') &&
					//len('basicRate2',5,'NS','基础费率2(可用于封顶)') &&
					//len('basicHighestFee',5,'NS','基础封顶费用') &&
					len('t0MerRate',10,'NS','T+0费率') &&
					/* len('d1MerRate',10,'NS','D+1费率')&& */
					len('t0MerProfit',10,'NS','服务商T0收益') /* &&
					len('d1MerProfit',10,'NS','服务商D+1收益') */){
					var lowsetTransAmt = $("#lowsetTransAmt").val();
					var merLowestFee = $("#merLowestFee").val();
					if(merLowestFee < 0.01){
						alert("封底值不能小于1分");
						return false;
					}
					if(lowsetTransAmt < 1){
						alert("最低交易金额不能小于1元");
						return false;
					}
					return true;
				}
					return false;
				
			}
			function update(){
				if(validate())
				if(confirm("确认要更新吗？"))
				$.ajax({
	                   type: "POST",
	                   dataType: "text",
	                   url: "<s:url value='/merchantInfo!setMerTrans.ac'/>?merTrans.actionList="+$("#actionList_val").val()+"&merTrans.transNotifyActionList="+$("#transNotifyActionList_val").val(),
	                   data: $('#form1').serialize(),
	                   success: function (data) {
	                      if(data=="succ"){
	                   	    $.ligerDialog.success('修改成功！');
	                      }else if(data=="fone"){
	                   	    $.ligerDialog.success('修改失败！');
	                      }
	                   },
	                   error: function(data) {
		                  		 $.ligerDialog.success("错误提示:"+data.responseText);
		                         window.parent.$.ligerDialog.closeWaitting(); 
		                 }
					});
			}
			//function getMerRate(){
			//	$("#merRate").html("商户收益："+(100-$("#platRate").val())+" %");
			//}
			function disp(val){
				if(val == '04'){
					$(".tr_show").css("visibility","visible");
				}else if(val == '08'){
					$(".tr_show").css("visibility","hidden");
				}
			}
			function sel(val){
				if(val != -1){
					$.post("<s:url value='/merchantInfo!getRegionCodeListBySuperCode.ac'/>",
					{'regionCode.superCode':val},
					function (data){
						strObj = eval(data);
						$("#basicRegion2").empty();
						$("#basicRegion2").append("<option value='-1'>--市--</option>");
						$.each(strObj, function(i) {
							$("#basicRegion2").append("<option value='"+strObj[i].code+"'>"+strObj[i].name+"</option>");
						});
					},"json");
				}
			}
		</script>
  </head>
  <body style="padding: 5px;">
    <div id="content">
			<div class="box_system">
			<form name="form1" id="form1">
			  <input type="hidden" id="merSysId" name="merTrans.merSysId" value="<s:property value='merchantInfo.merSysId'/>"/>
			   <input type="hidden" id="configCodeJson" name="configCodeJson" value="<s:property value='configCodeJson'/>"/>
			   <input type="hidden" id="transNotifyActionList1" name="transNotifyActionList" value="<s:property value='transNotifyActionList'/>"/>
			   <input type="hidden" value="05" name="merTrans.busType"/>
			<table width="98%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td style="text-align: right">当前机构商户：</td>
						<td><s:property value="merchantInfo.merName"/>
						(<s:property value="merchantInfo.merSysId"/>)</td>
						
					</tr>
					<tr>
					<td style="text-align: right">终端类别：</td>
						<td >
									<select name="merTrans.terminalType" id="terminalType">
										<option value="-1">
											--请选择--
										</option>
										<option value="04" <c:if test="${merTrans.terminalType=='04'}">selected="selected"</c:if>>
											 APP
										</option>
										<option value="05" <c:if test="${merTrans.terminalType=='05'}">selected="selected"</c:if>>
											 微信支付宝
										</option>
										<option value="06" <c:if test="${merTrans.terminalType=='06'}">selected="selected"</c:if>>
											 无卡支付
										</option>
										<!-- <option value="08" <c:if test="${merTrans.terminalType=='08'}">selected="selected"</c:if>>
											 POS
										</option> -->
									</select>
						<!-- 
							<s:set value="'|'+merTrans.terminalType+'|'" var="products"/>
							<s:iterator value="terminalTypeList" var="busProduct" status="status">
								<c:set value="|${busProduct.productId}|" var="product"/>
								<c:choose>
								<c:when test="${fn:contains(products,product)}">
									<input type="checkbox" name="merTrans.terminalType" checked="checked" value="<s:property value='#busProduct.productId'/>"/> <s:property value="#busProduct.productNmae"/>&emsp;
								</c:when>
								<c:otherwise>
									<input type="checkbox" name="merTrans.terminalType" value="<s:property value='#busProduct.productId'/>"/> <s:property value="#busProduct.productNmae"/>&emsp;
								</c:otherwise>
								</c:choose>
							</s:iterator>
						 -->
						</td>
						<!--<td style="text-align: right"> 自动代清分：</td>
						<td>
							
							<select name="merTrans.clearType">
							<option value="0" <s:if test="merTrans.clearType == 0">selected</s:if>>不清分</option>
							<option value="1" <s:if test="merTrans.clearType == 1">selected</s:if>>代清分给子商户</option>
							<option value="2" <s:if test="merTrans.clearType == 2">selected</s:if>>清分给平台</option>
							</select>
						</td>
							 <td style="text-align: right">交易类型：</td>
						<td >
							<s:set value="'|'+merTrans.busType+'|'" var="buss"/>
							<s:iterator value="busTypeList" var="busInfo" status="index">
								<c:set value="|${busInfo.busId}|" var="bus"/>
								<c:choose>
								<c:when test="${fn:contains(buss,bus)}">
									<input type="checkbox" name="merTrans.busType" checked="checked" value="<s:property value='#busInfo.busId'/>"/> <s:property value="#busInfo.busName"/>&emsp;
								</c:when>
								<c:otherwise>
									<input type="checkbox" name="merTrans.busType" value="<s:property value='#busInfo.busId'/>"/> <s:property value="#busInfo.busName"/>&emsp;
								</c:otherwise>
								</c:choose>
							</s:iterator>
						</td> -->
						<td style="text-align: right">费率类型：</td>
						<td>
							<input type="hidden" name="merTrans.rateType" value="03"/>扣率+封顶
						<%--
							<select name="merTrans.rateType">
								<option value="01" <c:if test="${merTrans.rateType=='01'}">selected="selected"</c:if>>扣率</option>
								<option value="02" <c:if test="${merTrans.rateType=='02'}">selected="selected"</c:if>>封顶</option>
							</select>
						--%></td>
					</tr>
					
					<!-- <tr>
						<td style="text-align: right">交易同步状态：</td>
						<td>
							<select name="merTrans.transNotifyStatus" id="transNotifyStatus">
							<option value="0">不同步</option>
							<option value="1">同步</option>
							</select>
						</td>
							<td style="text-align: right"> 同步类型：</td>
						<td colspan="3">
							<div class="l-text-wrapper" style="float:left">
							<div class="l-text l-text-combobox l-text-focus">
							<input type="text" id="transNotifyActionList"  class="l-text-field" ligerui-id="transNotifyActionList"/>
							</div>
							<input type="hidden" name="transNotifyActionList_val" id="transNotifyActionList_val" data-ligerid="transNotifyActionList"/>
							</div>
						</td>
					</tr> -->
							<!--<c:set value="${fn:split(merchantInfo.billCycle,'|') }" var="bills"/>
							<c:set value="${bills[1] }" var="day"/>
							结算周期：<input type="text" size="1" id="billCycle" value="${bills[0] }"/>/
							<select id="billCycle2">
								<c:if test="${day=='D'}">
									<option value="D" selected="selected">日</option>
									<option value="M">月</option>
								</c:if>
								<c:if test="${day=='M'}">
									<option value="M" selected="selected">月</option>
									<option value="D">日</option>
								</c:if>
								<c:if test="${day!='M' && day!='D'}">
									<option value="D">日</option>
									<option value="M">月</option>
								</c:if>
							</select>
						</td>
						<td>
							T+0结算
							<input type="checkbox" name="right" id="right" <c:if test="${fn:contains(merManager.purview,'125')}"> checked="checked"</c:if>/>&emsp;-->
						<!--<tr>
						
						 <td style="text-align: right">
						是否自动审核：
						</td>
						<td>
							<select name="merTrans.autoApprove">
								<option value="0" <s:if test="merTrans.autoApprove == 0">selected</s:if>>未开通</option>
								<option value="1" <s:if test="merTrans.autoApprove == 1">selected</s:if>>已开通</option>
							</select>
						</td> 
					</tr>-->
				<!-- 	<tr>
						<td style="text-align: right">子商户是否需要实人认证：</td>
						<td>
							<select name="merTrans.authStatus">
								<option value="0" <s:if test="merTrans.authStatus == 0">selected</s:if>>否</option>
								<option value="1" <s:if test="merTrans.authStatus == 1">selected</s:if>>是</option>
							</select>
						</td>
						<td style="text-align: right">T+0业务状态：</td>
						<td>
							<select name="merTrans.t0Status">
							<option value="0" <s:if test="merTrans.t0Status == 0">selected</s:if>>未开通</option>
							<option value="1" <s:if test="merTrans.t0Status == 1">selected</s:if>>已开通</option>
						</select>
						</td>	
					</tr> -->
					<%-- <tr>
						
						<td style="text-align: right">D+1费率：</td>
						<td>
							<input name="merTrans.d1MerRate" id="d1MerRate" value="<s:property value="merTrans.d1MerRate"/>"/>%
						</td> 
					</tr>--%>
					<tr>
						<td style="text-align: right">T+0费率：</td>
						<td>
							<input name="merTrans.t0MerRate" id="t0MerRate" value="<s:property value="merTrans.t0MerRate"/>"/>%
						</td>
						<td style="text-align: right">服务商T0收益：</td>
						<td>
							<input name="merTrans.t0MerProfit" id="t0MerProfit" value="<s:property value="merTrans.t0MerProfit"/>"/>%
						</td>
						
<%--						<td style="text-align: right">--%>
<%--						服务商D+1收益：--%>
<%--						</td>--%>
<%--						<td>--%>
<%--							<input name="merTrans.d1MerProfit" id="d1MerProfit" value="<s:property value="merTrans.d1MerProfit"/>"/>%--%>
<%--						</td> --%>
					</tr>
					<tr>
					<td style="text-align: right">
						终端可交易类型：
						</td>
						<td>
							<div class="l-text-wrapper" style="float:left">
							<div class="l-text l-text-combobox l-text-focus">
							<input type="text" id="actionList"  class="l-text-field" ligerui-id="actionList"/>
							</div>
							<input type="hidden" name="actionList_val" id="actionList_val" data-ligerid="actionList"/>
							</div><div style="float:left">(默认为全选)</div>
						</td>
						<td style="text-align: right">最低交易金额：</td>
						<td>
							<input  name="merTrans.lowsetTransAmt" id="lowsetTransAmt" maxlength="8" value="<s:property value="merTrans.lowsetTransAmt"/>"/>元
						</td>
					</tr>
					<tr>
						
						<td style="text-align: right">
						扣率：
						</td>
						<td>
							<input name="merTrans.merRate1" id="merRate1" value="<s:property value="merTrans.merRate1"/>"/>%
						</td>
						<td style="text-align: right">
						最低手续费：</td>
						<td>
							<input name="merTrans.merLowestFee" id="merLowestFee" maxlength="8" value="<s:property value="merTrans.merLowestFee"/>"/>元
						</td>
						<!-- <td style="text-align: right">
						同步URL地址：</td>
						<td>
							<input name="merTrans.transNotifyUrl" id="transNotifyUrl" value="<s:property value="merTrans.transNotifyUrl"/>"/>
						</td> -->
					</tr>
						
						<!-- <td style="text-align: right">
						费率-封顶：</td>
						<td>
							<input name="merTrans.merRate2" id="merRate2" value="<s:property value="merTrans.merRate2"/>"/>%
						</td> -->
					<!-- <tr>
						<td style="text-align: right">
						封顶值：
						</td>
						<td>
							<input name="merTrans.merHighestFee" id="merHighestFee" maxlength="8" value="<s:property value="merTrans.merHighestFee"/>"/>元
						</td>
						<td style="text-align: right">
						封底值：</td>
						<td>
							<input name="merTrans.merLowestFee" id="merLowestFee" maxlength="8" value="<s:property value="merTrans.merLowestFee"/>"/>元
						</td>
					</tr> -->
					
				<!-- 	<tr>
						<td style="text-align: right">子商户基础费率：</td>
						<td>
							<input name="merTrans.basicRate" id="basicRate" value="<s:property value="merTrans.basicRate"/>"/>%
						</td>
						<td style="text-align: right">
						基础费率2(可用于封顶)：
						</td>
						<td>
							<input name="merTrans.basicRate2" id="basicRate2" value="<s:property value="merTrans.basicRate2"/>"/>%
						</td>
					</tr> -->
				<tr>
						<!-- 	<td style="text-align: right">
						基础封顶费用：
						</td>
						<td>
							<input name="merTrans.basicHighestFee" id="basicHighestFee" maxlength="3" value="<s:property value="merTrans.basicHighestFee"/>"/>元
						</td> -->
						<td style="text-align: right">
						封顶费率：
						</td>
						<td>
							<input name="merTrans.merRate2" id="merRate2" value="<s:property value="merTrans.merRate2"/>"/>%
						</td>
						<td style="text-align: right">
						封顶值：
						</td>
						<td>
							<input name="merTrans.merHighestFee" id="merHighestFee" value="<s:property value="merTrans.merHighestFee"/>"/>元
						</td>
						
						
					</tr>
					<tr>
						<td style="text-align: right">
						积分扣率：
						</td>
						<td>
							<input name="merTrans.merRateNoTop" id="merRateNoTop" value="<s:property value="merTrans.merRateNoTop"/>"/>%
						</td>
						<td style="text-align: right">
						分润比：
						</td>
						<td>
							<input name="merTrans.merProfitRate" id="merProfitRate" value="<s:property value="merTrans.merProfitRate"/>"/>%
						</td>
					</tr>
					<tr>
					<td style="text-align: right">
							基础MCC：
						</td>
						<td>
							<select name="merTrans.basicMcc" id="basicMcc" style="width:130px">
								<option value="-1">请选择</option>
								<s:iterator value="mccCodeList">
									<option value="<s:property value="mcc"/>" <s:if test="mcc == merTrans.basicMcc">selected</s:if>><s:property value="desc"/>(<s:property value="profit"/>)</option>
								</s:iterator>
							</select>						
						</td>
					<td style="text-align: right">
						基础地区：
						</td>
						<td>
							<s:set var="sheng" value="merTrans.basicRegion.substring(0,2)"></s:set>
							<s:set var="shi" value="merTrans.basicRegion.substring(2,4)"></s:set>
							<select name="merTrans.basicRegion" id="basicRegion1" onchange="sel(this.value)" style="width:60px">
								<option value="-1">--省--</option>
								<s:iterator value="regionCodeList">
									<s:if test="level == 1">
										<option value="<s:property value="code"/>" <s:if test="code == #sheng">selected</s:if>><s:property value="name"/></option>
									</s:if>
								</s:iterator>
							</select>
							<select name="merTrans.basicRegion" id="basicRegion2" style="width:60px">
								<option value="-1">--市--</option>
								<s:if test="merTrans != null">
									<s:iterator value="regionCodeList">
										<s:if test="level == 2 && superCode eq #sheng">
											<option value="<s:property value="code"/>" <s:if test="code == #shi">selected</s:if>><s:property value="name"/></option>
										</s:if>
									</s:iterator>
								</s:if>
							</select>
						</td></tr>
					<tr align="center">
						<td colspan="4">
							<input type="button" value="更新" id="btn" style="width:80px" onclick="update()"/>
						</td>
					</tr>
				</table>
				</form>
			</div>
		</div>
  </body>
</html>
