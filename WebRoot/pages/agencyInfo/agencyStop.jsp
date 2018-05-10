<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>代理商编辑</title>
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
		<script src="<s:url value='/js/CheckLength.js'/>"></script>
		<script src="<s:url value='/js/common.js'/>"></script>
		<script type="text/javascript">
			function check(){
					if(	len("agentName","60","EandS","代理商名称")&&
						len("regNo","20","N","工商注册号")&&
						len("taxNo","20","N","税务登记号")&&
						len("regAddr","100","All","工商注册地址")){
					return true;
					}return false
			}
			function clse(){
				window.parent.$.ligerDialog.close();
	            window.parent.$(".l-dialog,.l-window-mask").remove();
			}
	        function update(){
				if(check()){
					$.ligerDialog.confirm('确定修改信息?', function (yes) {
						if(yes){ 
						$.ajax({
		                   type: "POST",
		                   dataType: "html",
		                   url: "<s:url value='/agencyInfo!agencyUpdate.ac'/>",
		                   data: $('#form1').serialize(),
		                   success: function (data) {
		                      if(data=="succ"){
		                   	    $.ligerDialog.success('修改成功！');
		                      }else if(data=="fone"){
		                   	    $.ligerDialog.success('修改成功！');
	                   	    }
		                   },
		                   error: function(data) {
			                  		 $.ligerDialog.success("错误提示:"+data.responseText);
			                         window.parent.$.ligerDialog.closeWaitting(); 
			                 }
						})
						
						}else{
							dialogClose();
						}	
					});
				}
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
				<form action="<s:url value="/agencyInfo!agencyUpdate.ac" />" name="form1" id="form1" method="post" onsubmit="return update()">
						<table width="95%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="120px" style="text-align: right;">
								代理商名称：
							</td>
							<td style="text-align: left;" width="120px">
								<input type="text" name="agenctInfo.agentName" id="agentName" value=<s:property value="agenctInfo.agentName"/> />
								<input type="hidden" name="agenctInfo.agentId" id="agentId" value=<s:property value="agenctInfo.agentId"/> />
							</td>
							<td style="text-align: right;">
								代理商入网号：
							</td>
							<td>&nbsp;
								<s:property value="agenctInfo.agentId"/>
							</td>
						</tr>
						<tr>
							<td width="120px" style="text-align: right;">
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
								业务类别：
							</td>
							<td>
								<select name="agenctInfo.busType" id="busType">
								<s:iterator value="busTypeList" var="b">
									<option <s:if test="#b.busId==agenctInfo.busType">selected="selected"</s:if> value="<s:property value="#b.busId"/>">
										<s:property value="#b.busName"/>
									</option>
								</s:iterator>
								</select>
							</td>
							<td width="150px" style="text-align: right;">
								结算机构名称：
							</td>
							<td>
									<s:iterator value="bankBehalfBranchList" var="b">
										<s:if test="#b.code==agenctInfo.settAgency">
											<s:property value="#b.bankName"/>
										</s:if>
									</s:iterator>
							</td>
						</tr>
						<tr>
							<td width="120px" style="text-align:right;">
								色值：
							</td>
							<td>
								<select name="agenctInfo.color">
								<option value="-1">--请选择--</option>
								<s:iterator value="cssConfigList" var="c">
									<option <s:if test="#c.cssId==agenctInfo.color">selected="selected"</s:if> value="<s:property value="#c.cssId"/>">
										<s:property value="#c.cssName"/>
									</option>
								</s:iterator>
								</select>
							</td>
						
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
								产品类别：
							</td>
							<td>
								<s:set value="'|'+agenctInfo.TerminalType+'|'" var="products"/>
								<s:iterator value="terminalTypeList" var="busProduct" status="status">
									<c:set value="|${busProduct.productId}|" var="product"/>
									<c:choose>
									<c:when test="${fn:contains(products,product)}">
										<input type="checkbox" name="agenctInfo.TerminalType" checked="checked" value="<s:property value='#busProduct.productId'/>"/> <s:property value="#busProduct.productNmae"/>&emsp;
									</c:when>
									<c:otherwise>
										<input type="checkbox" name="agenctInfo.TerminalType" value="<s:property value='#busProduct.productId'/>"/> <s:property value="#busProduct.productNmae"/>&emsp;
									</c:otherwise>
									</c:choose>
								</s:iterator>
							</td>
							<td width="150px" style="text-align: right;">
								结算账户号：
							</td>
							<td>
								<s:property value="agenctInfo.settAccountNo"/>
							</td>
						</tr>
						<tr>
							<td width="120px" style="text-align: right;">
								工商注册号：
							</td>
							<td style="text-align: left;" width="120px">
								<input type="text" name="agenctInfo.regNo" id="regNo" value=<s:property value="agenctInfo.regNo"/> />
							</td>
							<td style="text-align: right;">
								税务登记号：
							</td>
							<td>
								<input type="text" name="agenctInfo.taxNo" id="taxNo" value=<s:property value="agenctInfo.taxNo"/> />
							</td>
						</tr>
						<tr>
							<td width="120px" style="text-align: right;">
								工商注册地址：
							</td>
							<td style="text-align: left;" width="120px">
								<input type="text" name="agenctInfo.regAddr" id="regAddr" value=<s:property value="agenctInfo.regAddr"/> />
							</td>
							<td style="text-align: right;">
								备注：
							</td>
							<td>
								<input type="text" name="agenctInfo.remark" id="remark" value="<s:property value="agenctInfo.remark"/>" />
							</td>
						</tr>
						<tr>		
							<td colspan="4" align="center">
							<br />
		                    	<input id="btn2" class="l-button" type="button" onclick="update()" value="修改"/>
								<input type="button" value="关 闭"  id="btn2" class="l-button" onclick="clse()" />
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>

	</body>
</html>
