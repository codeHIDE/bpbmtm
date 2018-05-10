<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>机构终端详情</title>
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
		<script src="<s:url value='/js/CheckLength.js'/>"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerCheckBox.js'/>" type="text/javascript"></script>
	    <script type="text/javascript">
        function updateStatus() {
        if(check()){
       		$.ligerDialog.confirm('确定修改信息?', function (yes) {
				if(yes){ 
				$.ajax({
                   type: "POST",
                   dataType: "html",
                   url: "<s:url value='/merTerminalInfo!updateMerTerminalInfo.ac'/>",
                   data: $('#form1').serialize(),
                   success: function (data) {
                      if(data=="succ"){
                   	    $.ligerDialog.success('基本信息修改成功！');
                      }
                   },
                   error: function(data) {
	                  		 $.ligerDialog.success("错误提示:"+data.responseText);
	                         window.parent.$.ligerDialog.closeWaitting(); 
	                 }
				})
				}
			});
		}
		}
		function check(){
			if(!len('terminalCode','10','N','设备代码')){
				return false;
			}
			var version=$("#version").val();
			if(version=''){
				alert("版本号不能为空！");
				return false;
			}
			if(!len('versionDesc','200','All','版本信息描述')){
				return false;
			}
			var updatePath=$("#updatePath").val();
			if(updatePath==''){
				alert("更新地址不能为空！");
				return false;
			}
			if(updatePath.length>100){
				alert("更新地址长度不能超过100！");
				return false;
			}
			return true;
		}
		
		function cls() {
			parent.location.reload();
        	window.parent.$.ligerDialog.close();
	        window.parent.$(".l-dialog,.l-window-mask").remove();
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
		<div class="box_system">
		<form id="form1" action="<s:url value="/merTerminalInfo!updateMerTerminalInfo.ac"/>" method="post">
		<table width="90%" border="0" cellpadding="0" cellspacing="0" style="text-align: center;">
			<s:if test="merTerminalInfo==null">
			<tr>
				<td colspan="4" align="center">
					<span style="color: red;font-size: 20px;">没有查到机构终端信息!</span>
				</td>
			</tr>
			</s:if><s:else>
			<tr>
				<td align="right">
					机构号：
				</td>
				<td align="left">
					&nbsp;&nbsp;<s:property value="merTerminalInfo.merSysId"/>
				</td>
				<td align="right">
					编号： 
				</td>
				<td align="left">
					&nbsp;&nbsp;<s:property value="merTerminalInfo.id"/>
					<input type="hidden" name="merTerminalInfo.id" value="<s:property value="merTerminalInfo.id"/>"/>
				</td>
			</tr>
			<tr>
				<td align="right">
					状态： 
				</td>
				<td align="left">
					&nbsp;&nbsp;<c:if test="${merTerminalInfo.status=='0'}">
						不可用
					</c:if><c:if test="${merTerminalInfo.status=='1'}">
						可用
					</c:if>
				</td>
				<td align="right">
					设备代码： 
				</td>
				<td align="left">
					&nbsp;&nbsp;<input type="text" name="merTerminalInfo.terminalCode" id="terminalCode" value="<s:property value="merTerminalInfo.terminalCode"/>"/>
				</td>
			</tr>
			<tr>
				<td align="right">
					设备系统信息： 
				</td>
				<td align="left">
					&nbsp;&nbsp;<select name="merTerminalInfo.terminalSysterm" style="width: 130px">
						<option value="02" <c:if test="${merTerminalInfo.terminalSysterm=='02'}">selected="selected"</c:if>>
						IOS
						</option>
						<option value="01" <c:if test="${merTerminalInfo.terminalSysterm=='01'}">selected="selected"</c:if>>
						ANDROID
						</option>
						<option value="03" <c:if test="${merTerminalInfo.terminalSysterm=='03'}">selected="selected"</c:if>>
						WIN
						</option>
					</select>
				</td>
				<td align="right">
					设备类别： 
				</td>
				<td align="left">
					&nbsp;&nbsp;<select name="merTerminalInfo.category" style="width: 130px">
						<option value="04" <c:if test="${merTerminalInfo.category=='04'}">selected="selected"</c:if>>
						刷卡器
						</option>
						<option value="08" <c:if test="${merTerminalInfo.category=='08'}">selected="selected"</c:if>>
						POS机
						</option>
					</select>
				</td>
			</tr>
			<tr>
				<td align="right">
					更新类型： 
				</td>
				<td align="left">
					&nbsp;&nbsp;<select name="merTerminalInfo.updateType" style="width: 130px">
						<option value="00" <c:if test="${merTerminalInfo.updateType=='00'}">selected="selected"</c:if>>
						不更新
						</option>
						<option value="01" <c:if test="${merTerminalInfo.updateType=='01'}">selected="selected"</c:if>>
						强制更新
						</option>
						<option value="02" <c:if test="${merTerminalInfo.updateType=='02'}">selected="selected"</c:if>>
						非强制更新
						</option>
					</select>
				</td>
				<td align="right">
					版本号： 
				</td>
				<td align="left">
					&nbsp;&nbsp;<input type="text" name="merTerminalInfo.version" id="version" value="<s:property value="merTerminalInfo.version"/>"/>
				</td>
			</tr>
			<tr>
				<td align="right">
					版本信息描述： 
				</td>
				<td align="left">
					&nbsp;&nbsp;<input type="text" name="merTerminalInfo.versionDesc" id="versionDesc" value="<s:property value="merTerminalInfo.versionDesc"/>"/>
				</td>
				<td align="right">
					更新地址： 
				</td>
				<td align="left">
					&nbsp;&nbsp;<input type="text" name="merTerminalInfo.updatePath" id="updatePath" value="<s:property value="merTerminalInfo.updatePath"/>"/>
				</td>
			</tr>
			<tr>
				<td align="right">
					入网时间： 
				</td>
				<td align="left">
					&nbsp;&nbsp;<s:property value="merTerminalInfo.createTime"/>
				</td>
				<td align="right">
					文件名称： 
				</td>
				<td align="left">
					&nbsp;&nbsp;<s:property value="merTerminalInfo.fileName"/>
					<s:property value="merTerminalInfo.fileType"/>
					<!-- 
					<input type="text" name="merTerminalInfo.fileName" id="fileName" value="<s:property value="merTerminalInfo.fileName"/>"/>
					<s:property value="merTerminalInfo.fileType"/>
					<input type="hidden" name="merTerminalInfo.fileType" id="fileType" value="<s:property value="merTerminalInfo.fileType"/>"/>
					 -->
				</td>
			</tr>
			</s:else>
			<tr>
				<td colspan="2" align="center">
					<br/>
					<s:if test="merTerminalInfo!=null">
					<input type="button" value="修改" class="l-button" onclick="updateStatus()"/>
					</s:if>
				</td>
				<td colspan="2" align="center">
					<br/>
					<input type="button" value="关闭" onclick="cls()" class="l-button"/>
				</td>
			</tr>
		</table>
		</form>
		</div>
		</div>
	</body>
</html>
