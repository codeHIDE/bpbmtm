<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head><meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>代理商结算信息修改</title>
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
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerDateEditor.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/My97DatePicker_1/WdatePicker.js'/>"></script>
		<script src="<s:url value='/js/common.js'/>"></script>
		<script src="<s:url value='/js/CheckLength.js'/>"></script>
		<script type="text/javascript">
		function check(){
				if(len("billCycle","3","N","清分周期")&&
				len("settAccountNo","30","N","结算账户号")&&
				len("settAccountName","60","All","结算账户名")){
				return true;
				}return false
		}
		function update(){
			var b=document.getElementById("billCycle").value;
			var d=document.getElementById("billCycle2").value;
			var c=b+"|"+d;
			var b=document.getElementById("settAgency").value;
				if(check()){
					$.ligerDialog.confirm('确定修改信息?', function (yes) { 
					if(yes){
						$.ajax({
		                   type: "POST",
		                   dataType: "html",
		                   url: '<s:url value="/agencyInfo!updateSettAgenctInfo.ac"/>?agenctInfo.billCycle='+c+'&agenctInfo.settAgency'+b,
		                   data: $('#form1').serialize(),
		                   success: function (data) {
		                      if(data=="success"){
		                   	    $.ligerDialog.success('修改成功！');
		                    	
		                     	//dialogClose();
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
		}
					
				function cls(){
				window.parent.$.ligerDialog.close();
	            window.parent.$(".l-dialog,.l-window-mask").remove();
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
				<form id="form1" action="<s:url value='/agencyInfo!updateSettAgenctInfo.ac'/>" method="post">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="120px" style="text-align: right;">
								代理商户号：
							</td>
							<td>
								<s:property value="agenctInfo.agentId" />
								<input type="hidden" name="agenctInfo.agentId" value="<s:property value="agenctInfo.agentId"/>"></input>
							</td>
							<td style="text-align: right;">
								代理商名称：
							</td>
							<td>
							<s:property value="agenctInfo.agentName" />
								<input type="hidden" name="agenctInfo.agentName" value="<s:property value="agenctInfo.agentName"/>"></input>
							</td>
						</tr>
						<tr>
							
							<td style="text-align: right;">
								商户状态：
							</td>
							<td id="Status">
								<s:if test="agenctInfo.status==0">
									<span style="color: red">未审批</span>
								</s:if>
								<s:if test="agenctInfo.status==1">
									<span style="color: green">已审批</span>
								</s:if>
								<s:if test="agenctInfo.status==2"><span style="color: green">开通服务</span></s:if>
								<s:if test="agenctInfo.status==3">暂停服务</s:if>
								<s:if test="agenctInfo.status==4">停止服务</s:if>
								<s:if test="agenctInfo.status==5">黑名单</s:if>
								<input type="hidden" name="agenctInfo.status"
									value="<s:property value="agenctInfo.status"/>"></input>
							</td>
							<td width="120px" style="text-align: right;">
								结算账户类型：
							</td>
							<td>
								<input type="radio" <s:if test="agenctInfo.settAccType==1">checked="checked"</s:if> name="agenctInfo.settAccType" id="settAccType1" value="1"  />对公
								<input type="radio" <s:if test="agenctInfo.settAccType==2">checked="checked"</s:if> name="agenctInfo.settAccType" id="settAccType2" value="2"  />对私
							</td>
						</tr>
						<tr>
						<!-- 
						<td width="120px" style="text-align: right;">
								结算周期： 
							</td>
							<td>
								<s:property value="agenctInfo.billCycle" />
								<input type="hidden" name="agenctInfo.billCycle"
									value="<s:property value="agenctInfo.billCycle"/>"></input>
							</td>
						 -->
						<td width="120px" style="text-align: right;">
								<c:set value="${fn:split(agenctInfo.billCycle,'|') }" var="bills"/>
								<c:set value="${bills[1] }" var="day"/>
								清分周期：
							</td>
							<td style="text-align: left;">
								<input type="text" size="1" id="billCycle" value="${bills[0] }" name="billCycle"/>/
							<select id="billCycle2"  name="billCycleDay">
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
							<td style="text-align: right;">
								结算账户名：
							</td>
							<td>
									<input type="text" name="agenctInfo.settAccountName" id="settAccountName"
									value="<s:property value="agenctInfo.settAccountName"/>"></input>
							</td>
						</tr>
						<tr>
							
							<td width="120px" style="text-align: right;">
								结算账户号：
							</td>
							<td>
								<input type="text" name="agenctInfo.settAccountNo" id="settAccountNo"
									value="<s:property value="agenctInfo.settAccountNo"/>"></input>
							</td>
							<td width="120px" style="text-align: right;">
									结算机构名称：
								</td>
								<td>
									<select name="agenctInfo.settAgency" id="settAgency"  style="width:135px">
													<s:iterator value="bankBehalfBranchList" var="bank">
													<s:if test="#bank.code==agenctInfo.settAgency">
													<option value="<s:property value="#bank.code" />" selected="selected">
														<s:property value="#bank.bankName" />
													</option>
													</s:if>
													<s:else>
												<option value="<s:property value="#bank.code" />">
													<s:property value="#bank.bankName" />
												</option>
												</s:else>
											</s:iterator>
								   </select>
								</td>
						</tr>
						
						<tr>
							<td colspan="2" align="center">
								<br />
								<input type="button" value="确认修改" id="btn" class="l-button" onclick="update()" />
							</td>
							<td colspan="2" align="center">
								<br />
								<input type="button" value="关 闭" id="btn2" class="l-button" onclick="cls()" />
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>

	</body>
</html>
