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
		 <script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerCheckBox.js'/>" type="text/javascript"></script>
	    <script type="text/javascript">
        function updateInfo(id) {
       		$.ligerDialog.confirm('确定修改状态?', function (yes) {
				if(yes){ 
				$.ajax({
                   type: "POST",
                   dataType: "html",
                   url: "<s:url value='/subMerInfo!updateFindById.ac'/>?merTerminalInfo.id="+id,
                   data: $('#form1').serialize(),
                   success: function (data) {
                      if(data=="2"){
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
		
		function updateStatus(id,status){
			if(confirm("是否确认操作？")){
				$.post("<s:url value='/merTerminalInfo!updateMerTerminalInfo.ac'/>",
				{'merTerminalInfo.id':id,'merTerminalInfo.status':status},
				function(data){
					if(data=='succ'){
						alert("操作成功！");
					}else{
						alert("操作失败，请刷新列表重新操作!");
						cls();
					}
				},"text");
			}
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
		<table width="100%" border="0" cellpadding="0" cellspacing="0" style="text-align: center;">
			<s:if test="merTerminalInfo==null">
			<tr>
				<td colspan="4" align="center">
					<span style="color: red;font-size: 20px;">没有查到机构终端信息!</span>
				</td>
			</tr>
			</s:if><s:else>
			<tr>
				<td>
					机构号：
				</td>
				<td>
					<s:property value="merTerminalInfo.merSysId"/>
				</td>
				<td>
					编号:
				</td>
				<td>
					<s:property value="merTerminalInfo.id"/>
					<input type="hidden" id="id" value="<s:property value="merTerminalInfo.id"/>"/>
				</td>
			</tr>
			<tr>
				<td>
					状态:
				</td>
				<td style="color: red;">
					<c:if test="${merTerminalInfo.status=='0'}">
						不可用
					</c:if><c:if test="${merTerminalInfo.status=='1'}">
						可用
					</c:if>
				</td>
				<td>
					设备代码:
				</td>
				<td>
					<s:property value="merTerminalInfo.terminalCode"/>
				</td>
			</tr>
			<tr>
				<td>
					设备系统信息:
				</td>
				<td>
					<s:if test="merTerminalInfo.terminalSysterm=='01'">
					ANDROID
					</s:if>
					<s:if test="merTerminalInfo.terminalSysterm=='02'">
					IOS
					</s:if>
					<s:if test="merTerminalInfo.terminalSysterm=='03'">
					WIN
					</s:if>
				</td>
				<td>
					设备类别:
				</td>
				<td>
					<c:if test="${merTerminalInfo.category=='04'}">
						刷卡器
					</c:if><c:if test="${merTerminalInfo.category=='08'}">
						POS机
					</c:if>
				</td>
			</tr>
			<tr>
				<td>
					更新类型:
				</td>
				<td>
					<c:if test="${merTerminalInfo.updateType=='00'}">
						不更新
					</c:if><c:if test="${merTerminalInfo.updateType=='01'}">
						强制更新
					</c:if><c:if test="${merTerminalInfo.updateType=='02'}">
						非强制更新
					</c:if>
				</td>
				<td>
					版本号:
				</td>
				<td>
					<s:property value="merTerminalInfo.version"/>
				</td>
			</tr>
			<tr>
				<td>
					版本信息描述:
				</td>
				<td>
					<s:property value="merTerminalInfo.versionDesc"/>
				</td>
				<td>
					更新地址:
				</td>
				<td>
					<s:property value="merTerminalInfo.updatePath"/>
				</td>
			</tr>
			<tr>
				<td>
					入网时间:
				</td>
				<td>
					<s:property value="merTerminalInfo.createTime"/>
				</td>
				<td>
					文件名称:
				</td>
				<td>
					<s:property value="merTerminalInfo.fileName"/>
				</td>
			</tr>
			</s:else>
			<tr>
				<td colspan="2" align="center">
					<br/>
					<s:if test="merTerminalInfo!=null">
						<c:if test="${merTerminalInfo.status=='0'}">
							<input type="button" value="启用" class="l-button" 
								onclick="updateStatus('<s:property value="merTerminalInfo.id"/>',1)"/>
						</c:if><c:if test="${merTerminalInfo.status=='1'}">
							<input type="button" value="暂停" class="l-button" 
								onclick="updateStatus('<s:property value="merTerminalInfo.id"/>',0)"/>
						</c:if>
					</s:if>
				</td>
				<td colspan="2" align="center">
					<br/>
					<input type="button" value="关闭" onclick="cls()" class="l-button"/>
				</td>
			</tr>
		</table>
		</div>
		</div>
	</body>
</html>
