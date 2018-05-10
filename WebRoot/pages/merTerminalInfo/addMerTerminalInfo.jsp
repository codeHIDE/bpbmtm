<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>配置机构终端</title>
		<link href="<s:url value='/js/jqui/ligerUI/skins/Aqua/css/ligerui-all.css?332'/>" rel="stylesheet" type="text/css" />
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
		<script src="<s:url value='/js/jquery/jquery-ui-1.7.1.custom.min.js'/>"></script>
		<script src="<s:url value='/js/CheckLength.js'/>" type="text/javascript"></script> 
		<script src="<s:url value='/js/common.js'/>"></script>
		 <script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerCheckBox.js'/>" type="text/javascript"></script>
	    <script type="text/javascript">
	     var flag=false;
	    
        $(function () {
       			if(${requestScope.addTractInfo=='success'}){
					alert("添加终端成功！");
					}
            $("#chk1").change(function () {
            	 alert(this.checked); 
            });
        });
			
			function adda(){
				var merSysId=$('#merSysId').val();
				var terminalSysterm=$('#terminalSysterm').val();
				var category=$('#category').val();
				var updateType=$('#updateType').val();
				if(merSysId==-1){
					alert("请选择机构商户号");
					return false;
				}
				if(category==-1){
					alert("请选择设备类别");
					return false;
				}
				if(terminalSysterm==-1){
					alert("请选择设备系统信息");
					return false;
				}
				
				if(updateType==-1){
					alert("请选择更新类型");
					return false;
				}
				if(	
					len('terminalCode',10,'All','设备代码') &&
					len('fileName',50,'All','文件名称')	&&
					len('version',10,'All','版本号') &&
					len('versionDesc',200,'All','版本信息描述')&&
					len('updatePath',100,'All','更新地址')
					){
						addMerTerminal.submit();
						return true;
					}
					return false;
			}
			
			
    </script>
		<style type="">
			tr{
				height:30px;
			}
			td{
				width:120px;
			}
		</style>
	</head>
	<body style="padding: 4px; overflow: hidden;">
		<div id="content">
			<s:if test="message!=null">
				<span><s:property value="message" /></span>
			</s:if>
			<div class="box_system">
				<form action="<s:url value="/merTerminalInfo!addMerTerminalInfo.ac"/>" id="addMerTerminal" method="post"  >
					<table width="96%" border="0" cellpadding="0" cellspacing="0">
						<tr>
								<td style="text-align: right;">
								更新类型：
							</td>
							<td>
								<select name="merTerminalInfo.updateType" id="updateType">
									<option value="-1">
										---请选择---
									</option>
									<option value="00" <s:if test="merTerminalInfo.updateType=='00'">selected="selected"</s:if>>
										不更新
									</option>
									<option value="01" <s:if test="merTerminalInfo.updateType=='01'">selected="selected"</s:if>>
										强制更新
									</option>
									<option value="02" <s:if test="merTerminalInfo.updateType=='02'">selected="selected"</s:if>>
										非强制更新
									</option>
								</select>
							</td>
						
							<td style="text-align: right;">
								设备类别：
							</td>
							<td>
								<select name="merTerminalInfo.category" id="category">
									<option value="-1">
										---请选择---
									</option>
									<option value="04" <s:if test="merTerminalInfo.category=='04'">selected="selected"</s:if>>
										刷卡
									</option>
									<option value="08" <s:if test="merTerminalInfo.category=='08'">selected="selected"</s:if>>
										POS机
									</option>
								</select>
							</td>
								<td style="text-align: right;">
								设备系统信息：
							</td>
							<td>
								<select name="merTerminalInfo.terminalSysterm" id="terminalSysterm">
									<option value="-1">
										---请选择---
									</option>
									<option value="01"<s:if test="merTerminalInfo.terminalSysterm=='01'">selected="selected"</s:if>>
										ANDROID
									</option>
									<option value="02"<s:if test="merTerminalInfo.terminalSysterm=='02'">selected="selected"</s:if>>
										IOS
									</option>
									<option value="03"<s:if test="merTerminalInfo.terminalSysterm=='03'">selected="selected"</s:if>>
										WIN
									</option>
								</select>
							</td>
						</tr>
						<tr>
				<td style="text-align: right;">
								商户号：
							</td>
							<td style="text-align: left;">
								<select name="merTerminalInfo.merSysId" id="merSysId"  style="width:135px">
												<option selected="selected" value="0">--请选择-- </option>
												<s:iterator value="merInfoList" var="bank">
													<option value="<s:property value="#bank.merSysId" />" <s:if test="merTerminalInfo.merSysId==#bank.merSysId">selected="selected"</s:if>>
														<s:property value="#bank.merSysId" />
													</option>
											</s:iterator>
											   
								   </select>
							</td>
							<td style="text-align: right;">
								设备代码：
							</td>
							<td>
							<div class="l-text" style="float:left;">
								<input type="text" class="l-text-field" value="<s:property value="merTerminalInfo.terminalCode"/>"
									name="merTerminalInfo.terminalCode" id="terminalCode" />
									</div>
							</td>
						
							<td width="150px" style="text-align: right;">
								文件名：
							</td>
							<td style="text-align: left;">
							<div class="l-text" style="float:left;">
								<input type="text" class="l-text-field" value="<s:property value="merTerminalInfo.fileName"/>" 
								name="merTerminalInfo.fileName" id="fileName" />
								</div>
							</td>
							
						</tr>
						<tr>
							<td width="150px" style="text-align: right;">
								版本号：
							</td>
							<td style="text-align: left;">
							<div class="l-text" style="float:left;">
								<input type="text" class="l-text-field" value="<s:property value="merTerminalInfo.version"/>" 
								name="merTerminalInfo.version" id="version" />
								</div>
							</td>
							<td width="150px" style="text-align: right;">
								版本信息描述：
							</td>
							<td style="text-align: left;">
							<div class="l-text" style="float:left;">
								<input type="text" class="l-text-field" value="<s:property value="merTerminalInfo.versionDesc"/>" 
								name="merTerminalInfo.versionDesc" id="versionDesc" />
								</div>
							</td>
							<td width="150px" style="text-align: right;">
								更新地址：
							</td>
							<td style="text-align: left;">
							<div class="l-text" style="float:left;">
								<input type="text" class="l-text-field" value="<s:property value="merTerminalInfo.updatePath"/>" 
								name="merTerminalInfo.updatePath" id="updatePath" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="6" align="center">
								<input type="button" value="确认配置" id="btn" onclick="adda()"  class="btn1" />
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<s:if test="#request.addMerTerminalInfo == 'success'">
			<script>
				 $.ligerDialog.success("添加终端成功!");
			</script>
		</s:if>
		<s:if test="#request.addMerTerminalInfo == 'fail'">
			<script>
				alert("添加终端失败!");
			</script>
		</s:if>
	</body>
</html>
