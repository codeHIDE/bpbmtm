<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>商户详情</title>
		<link href="<s:url value='/js/jqui/ligerUI/skins/Aqua/css/ligerui-all.css'/>" rel="stylesheet" type="text/css" />
		<link href="<s:url value='/js/jqui/ligerUI/skins/ligerui-icons.css'/>" rel="stylesheet" type="text/css" />
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
		<script src="<s:url value='/js/My97DatePicker_1/WdatePicker.js'/>"></script>
		<script src="<s:url value='/js/common.js'/>"></script>
		<script type="text/javascript">
			function clse(){
				window.parent.$.ligerDialog.close();
	            window.parent.$(".l-dialog,.l-window-mask").remove();
			}
			function f_open3()
	        {
	        	var btn=document.getElementById("btn2").value;
		        var subMerId=document.getElementById("agenctInfo.agentId").value;
				if(confirm('确定'+btn+'?')==false){
					return;
				}else{
					window.location.href="agencyInfo!agencyDetail.ac?agencyName="
					+subMerId+"&merSysId="+"1";
				}
	        }
	        
	        function update(status){
				if(confirm("是否确认操作？"))
				$.post("<s:url value='/agencyInfo!agencyDetail.ac'/>",
				{'agenctInfo.agentId':$("#agentId").val(),'agenctInfo.status':status},
				function(data){
					if(data=='succ'){
						$("#merStatus").html("<font color='green'>正在使用</font>");
							alert("上线成功！");
							window.parent.location.reload();
					}else if(data=='fone'){
						$("#merStatus").html("<font color='red'>未审批</font>");
					}else if(data=='succSuspended'){
							$("#merStatus").html("<font color='red'>暂停服务</font>");
								alert("已暂停！");
								window.parent.location.reload();
					}else if(data=='fonesSuspended'){
							alert("暂停失败!");
							window.parent.location.reload();
					}else{
							alert("操作失败，请重新操作!");
						}
						location.reload();
				},"text");
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
				<form action="">
						<table width="95%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="120px" style="text-align: right;">
								代理商名称：
							</td>
							<td style="text-align: left;" width="120px">
								<s:property value="agenctInfo.agentName"/>
								<s:if test="agenctInfo.agentName==null">未填写</s:if>
								<s:if test="agenctInfo.agentName==''">未填写</s:if>
								<input type="hidden" name="agenctInfo.agentId" id="agentId" value=<s:property value="agenctInfo.agentId"/> />
								<input type="hidden" name="agenctInfo.status" id="status" value=<s:property value="agenctInfo.status"/> />
							</td>
							<td style="text-align: right;">
								代理商入网号：
							</td>
							<td>&nbsp;
								<s:property value="agenctInfo.agentId"/>
								
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">
								商户状态：
							</td>
							<td>
								<s:if test="agenctInfo.status==0">
								<span style="color:red">未审批</span></s:if>
								<s:if test="agenctInfo.status==1">
								<span style="color:green">正在使用</span></s:if> 
								<s:if test="agenctInfo.status==2">
								<span style="color:red">暂停</span></s:if>
							</td>
							<td style="text-align: right;">
								所属商户：
							</td>
							<td>
								<s:property value="agenctInfo.merSysId"/>
								<s:if test="agenctInfo.merSysId==null">未填写</s:if>
								<s:if test="agenctInfo.merSysId==''">未填写</s:if>
							</td>
						</tr>
						<tr>
							<td width="120px" style="text-align: right;">
								结算机构名称：
							</td>
							<td>
								<s:property value="agenctInfo.settAgency"/>
								<s:if test="agenctInfo.settAgency==null">未填写</s:if>
								<s:if test="agenctInfo.settAgency==''">未填写</s:if>
							</td>
							<td width="150px" style="text-align: right;">
								结算账户号：
							</td>
							<td>
								<s:property value="agenctInfo.settAccountNo"/>
								<s:if test="agenctInfo.settAccountNo==null">未填写</s:if>
								<s:if test="agenctInfo.settAccountNo==''">未填写</s:if>
							</td>
						</tr>
						<tr>
							<td width="120px" style="text-align: right;">
								业务类别：
							</td>
							<td>
								<s:iterator value="busTypeList" var="b">
									<s:if test="#b.busId==agenctInfo.busType">
										<s:property value="#b.busName"/>
									</s:if>
								</s:iterator>
							</td>
							<td width="120px" style="text-align: right;">
								产品类别：
							</td>
							<td>
								<s:set value="'|'+agenctInfo.TerminalType+'|'" var="products"/>
								<s:iterator value="terminalTypeList" var="busProduct" status="status">
									<c:set value="|${busProduct.productId}|" var="product"/>
									<c:choose>
									<c:when test="${fn:contains(products,product)}">
										<s:property value="#busProduct.productNmae"/>&emsp;
									</c:when>
									<c:otherwise>
										<s:property value="#busProduct.productNmae"/>&emsp;
									</c:otherwise>
									</c:choose>
								</s:iterator>
							</td>
						</tr>
						<tr>
							<td width="120px" style="text-align: right;">
								<c:set value="${fn:split(agenctInfo.billCycle,'|') }" var="bills"/>
								<c:set value="${bills[1] }" var="day"/>
								清分周期：
							</td>
							<td style="text-align: left;">
								${bills[0] }
								<c:if test="${day=='D'}">
									日
								</c:if>
								<c:if test="${day=='M'}">
									月
								</c:if>
							</td>
						</tr>
						<tr>
							<td width="120px" style="text-align: right;">
								签约日期：
							</td>
							<td>
								<s:property value="agenctInfo.createTime"/>
								<s:if test="agenctInfo.createTime==null">未填写</s:if>
								<s:if test="agenctInfo.createTime==''">未填写</s:if>
								
							</td>
							<td width="150px" style="text-align: right;">
								结算账户名：
							</td>
							<td style="text-align: left;">
								<s:property value="agenctInfo.settAccountName"/>
								<s:if test="agenctInfo.settAccountName==null">未填写</s:if>
								<s:if test="agenctInfo.settAccountName==''">未填写</s:if>
							</td>	
						</tr>
						<tr>		
							<td colspan="4" align="center">
							<br />
								<s:if test="agenctInfo.status==0">
		                    		<input id="btn2" class="l-button" type="button" value="上线" onclick="update(1)"/>
		                    	</s:if>
		                    	<s:if test="agenctInfo.status==1">
		                    		<input id="btn2" class="l-button" type="button" value="暂停" onclick="update(2)"/>
		                    	</s:if>
		                    	<s:if test="agenctInfo.status==2">
		                    		<input id="btn2" class="l-button" type="button" value="恢复" onclick="update(1)"/>
		                    	</s:if>
								<input type="button" value="关 闭"  id="btn2" class="l-button" onclick="clse()" />
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>

	</body>
</html>
