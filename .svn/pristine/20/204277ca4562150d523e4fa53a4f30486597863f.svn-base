<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head><meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>代理商费率信息修改</title>
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
			if(${requestScope.updateAgencyRate=="succ"}){
				alert("代理商费率修改成功！");
			}else if(${requestScope.updateAgencyRate=="merError"}){
				alert("费率值错误!");
			}if(${requestScope.updateAgencyRate=="fone"}){
				alert("代理商费率修改失败！");
			}
			function update(view){
				if(len("agentRate1","10","NS","代理商费率-扣率") && 
				len("agentProfitRate","10","NS","代理商分润比")&&
				//len("agentD1Rate","10","NS","代理商封顶值")&&
				len("agentT0Rate","10","NS","代理商分润比") &&
				len("agentRateNoTop", "10", "NS", "积分扣率")){
					return true;
				}else{
					return false;
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
				<form id="form1" action="<s:url value='/agencyInfo!updateAgencyRate.ac'/>" method="post" onsubmit="return update()">
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
							</td>
						</tr>
							<tr>
								<td width="120px" style="text-align: right;">
									代理商费率-扣率：
								</td>
								<td width="150px" style="text-align: left;">
									<input id="agentRate1" name="agenctInfo.agentRate1" style="width: 140px;"
										 class="inputext_style" value="<s:property value="agenctInfo.agentRate1"/>"/>%
								</td>
								<td width="120px" style="text-align: right;">
									代理商分润比：
								</td>
								<td width="150px" style="text-align: left;">
									<input id="agentProfitRate" name="agenctInfo.agentProfitRate" style="width: 140px;"
										 class="inputext_style" value="<s:property value="agenctInfo.agentProfitRate"/>"/>%
								</td>
								
							</tr>
							<tr>
<%--								<td width="120px" style="text-align: right;">--%>
<%--									代理商D1费率：--%>
<%--								</td>--%>
<%--								<td width="150px" style="text-align: left;">--%>
<%--									<input id="agentD1Rate" name="agenctInfo.agentD1Rate" style="width: 140px;"--%>
<%--										 class="inputext_style" value="<s:property value="agenctInfo.agentD1Rate"/>"/>%--%>
<%--								</td>--%>
								<td width="120px" style="text-align: right;">
									代理商T0费率：
								</td>
								<td width="150px" style="text-align: left;">
									<input id="agentT0Rate" name="agenctInfo.agentT0Rate" style="width: 140px;"
										 class="inputext_style" value="<s:property value="agenctInfo.agentT0Rate"/>"/>%
									<span style="color: red">*</span>
								</td>
							</tr>
							<tr>
								<td width="120px" style="text-align: right;">
									封顶费率：
								</td>
								<td width="150px" style="text-align: left;">
									<input id="agentRate2" name="agenctInfo.agentRate2" style="width: 140px;"
										 class="inputext_style" value="<s:property value="agenctInfo.agentRate2"/>"/>%
								</td>
								<td width="120px" style="text-align: right;">
									封顶值：
								</td>
								<td width="150px" style="text-align: left;">
									<input id="agentHighestFee" name="agenctInfo.agentHighestFee" style="width: 140px;"
										 class="inputext_style" value="<s:property value="agenctInfo.agentHighestFee"/>"/>分
									<span style="color: red">*</span>
								</td>
							</tr>
							<tr>
								<td width="120px" style="text-align: right;">
									积分扣率：
								</td>
								<td width="150px" style="text-align: left;">
									<input id="agentRateNoTop" name="agenctInfo.agentRateNoTop" style="width: 140px;"
										 class="inputext_style" value="<s:property value="agenctInfo.agentRateNoTop"/>"/>%
								</td>
							</tr>
						<tr>
							<td colspan="2" align="center">
								<br />
								<input type="submit" value="确认修改" id="btn" class="l-button" />
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
