<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head><meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>商户修改结算信息</title>
		<link href="<s:url value='/js/jqui/ligerUI/skins/Aqua/css/ligerui-all.css'/>" rel="stylesheet" type="text/css" />
		<link href="<s:url value='/js/jqui/ligerUI/skins/ligerui-icons.css'/>" rel="stylesheet" type="text/css" />
		<link href="<s:url value='/js/jqui/ligerUI/skins/Gray/css/all.css'/>" rel="stylesheet" type="text/css" />
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
		<script src="<s:url value='/js/CheckLength.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerDateEditor.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/common.js'/>"></script>
		<script type="text/javascript">
		function update(){
			if(vaildata())
				$.ligerDialog.confirm('确定修改信息?', function (yes) { 
					if(yes){
						$.ajax({
		                   type: "POST",
		                   dataType: "text",
		                    url: "<s:url value='/merchantInfo!tractAndMerAppToUpdate.ac'/>",
		                   data: $('#form1').serialize(),
		                   success: function (data) {
		                      if(data=="succ"){
		                   	    $.ligerDialog.success('修改成功！');
		                      }else if (data=="succ"){
		                      	$.ligerDialog.success('修改失败！');
		                      }
		                   },
		                   error: function(data) {
			                  		 $.ligerDialog.success("错误提示:"+data.responseText);
			                         window.parent.$.ligerDialog.closeWaitting(); 
			                 }
						});
						
					}else{
							dialogClose();
						}	
				});
		}
					
				function cls(){
				window.parent.$.ligerDialog.close();
	            window.parent.$(".l-dialog,.l-window-mask").remove();
			}
			function vaildata(){
					var transRate=$("#transRate").val();
					if(transRate>1){
						alert("交易扣率不可以大于1");
						return false;
					}
					if( 
					len('transRate',20,'NS','交易扣率')&&
					len('merProfit',20,'NS','机构分润比')&&
					length('lowestFee',20,'N','最低手续费')&&
					length('highestFee',20,'N','最高手续费')
					)
						return true;
						return false;
			}
		</script>

		<style type="text/css">
		tr {
			height: 30px;
		}
		</style>
	</head>
	<body style="padding: 4px; overflow: hidden;">

		<div id="content">
			<s:if test="message!=null">
				<span><s:property value="message" /> </span>
			</s:if>
			<div class="box_system">
				<form id="form1" action="<s:url value='/merchantInfo!updateSettleMerchantInfo.ac'/>" method="post">
					<table width="98%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td style="text-align: right;">
								机构商户号：
							</td>
							<td>
								<s:property value="merAppTract.merSysId" />
								<input type="hidden" name="merAppTract.merSysId" value="<s:property value="merAppTract.merSysId"/>"></input>
							</td>
							<td width="120px" style="text-align: right;">
								通道号：
							</td>
							<td >
								<s:property value="merAppTract.appTractId" />
								<input type="hidden" name="merAppTract.appTractId" value="<s:property value="merAppTract.appTractId"/>"></input>
								
							</td>
							
						</tr>
						<tr>
							<td style="text-align: right;">
									通道状态：
								</td>
								<td id="status">
									<s:if test="merAppTract.status==0">
										<span style="color: red">未启用</span>
									</s:if>
									<s:if test="merAppTract.status==1">
										<span style="color: green">正在使用</span>
									</s:if>
								</td>
								<td style="text-align: right;">
									费率类型：
								</td>
								<td >
									<s:if test="merAppTract.appTractInfo.tractType=='01'">
										<span style="color: red">还款</span>
									</s:if>
									<s:if test="merAppTract.appTractInfo.tractType=='02'">
										<span style="color: red">转账</span>
									</s:if>
									<s:if test="merAppTract.appTractInfo.tractType=='03'">
										<span style="color: red">余额查询</span>
									</s:if>
									<s:if test="merAppTract.appTractInfo.tractType=='04'">
										<span style="color: red">手机充值</span>
									</s:if>
									<input type="hidden" name="merAppTract.appTractInfo.tractType" id="tractType" value="<s:property value="merAppTract.appTractInfo.tractType"/>"></input>
								</td>
							</tr>
							<tr>
								<td width="150px" style="text-align: right;">
									交易扣率：
								</td>
								<td>
									<input type="text" size="4" name="merAppTract.transRate" id="transRate" value="<s:property value="merAppTract.transRate"/>"></input> %
								</td>
								<td width="150px" style="text-align: right;">
									机构分润比：
								</td>
								<td>
									<input type="text" size="4" name="merAppTract.merProfit" id="merProfit" value="<s:property value="merAppTract.merProfit"/>"></input> %
								</td>
								
								
							</tr>
							<tr>
								<td width="150px" style="text-align: right;">
									最低手续费：
								</td>
								 <td>
									<input type="text"  size="4" name="merAppTract.lowestFee" id="lowestFee" 
									<s:if test="merAppTract.lowestFee == '-1'">value=""</s:if>
									<s:elseif test="merAppTract.lowestFee == '0'">value="0"</s:elseif>
									<s:else>value="<fmt:formatNumber value="${merAppTract.lowestFee/100}"/>"</s:else>></input> 元
								</td>
								<td width="150px" style="text-align: right;">
									最高手续费：
								</td>
								<td>
									<input type="text"  size="4" name="merAppTract.highestFee"  id="highestFee" 
									<s:if test="merAppTract.highestFee == -1">value=""</s:if>
									<s:elseif test="merAppTract.highestFee == 0">value="0"</s:elseif>
										<s:else>value="<fmt:formatNumber value="${merAppTract.highestFee/100}"/>"</s:else>></input> 元
								</td>
							</tr>
							<tr>
								<td style="text-align: right;">
									通道类型：
								</td>
								<td >
								<s:if test="merAppTract.appTractType==01"> 还款</s:if>
								<s:if test="merAppTract.appTractType==02"> 转账</s:if> 
								<s:if test="merAppTract.appTractType==03"> 余额查询</s:if>
								<s:if test="merAppTract.appTractType==04">手机冲值</s:if>
								<input  type="hidden" name="merAppTract.appTractType"
								value="<s:property value="merAppTract.appTractType"/>"></input>	
								</td>
							</tr>
						<tr>
							<td colspan="2" align="center">
								<input type="button" value="确认修改" id="btn" class="l-button" onclick="update()" />
							</td>
							<td colspan="2" align="center">
								<input type="button" value="关 闭" id="btn2" class="l-button" onclick="cls()" />
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</body>
</html>
