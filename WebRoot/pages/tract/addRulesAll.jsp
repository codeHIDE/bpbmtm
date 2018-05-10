<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>添加通道</title>
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
		<script type="text/javascript" src="<s:url value='/js/My97DatePicker_1/WdatePicker.js' />"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerComboBox.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/CheckLength.js'/>" type="text/javascript"></script> 
		<script src="<s:url value='/js/common.js'/>"></script>
		 <script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerCheckBox.js'/>" type="text/javascript"></script>
		<style type="">
			tr{
				height:30px;
			}
			td{
				width:120px;
			}
		</style>
		<script type="text/javascript">
			function lo(){
				var fdList = "";
				var klList = "";
				var tractList=eval($("#tractList").val());
				$.each(tractList,function(index){
					if(tractList[index].ratesType == '01'){
						klList += "{id:'"+tractList[index].tractId+"',text:'"+tractList[index].tractName+"'},";
					}else if(tractList[index].ratesType == '02'){
						fdList += "{id:'"+tractList[index].tractId+"',text:'"+tractList[index].tractName+"'},";
					}
				});
				if(fdList == ""){
					alert("没有封顶通道！");
					window.close();
				}
				if(klList == ""){
					alert("没有扣率通道！");
					window.close();
				}
				fdList = fdList.substring(0,fdList.length-1);
				klList = klList.substring(0,klList.length-1);
				fdList = eval('[' + fdList + ']');
				klList = eval('[' + klList + ']');
				$("#fdList").ligerComboBox({ data: fdList, isMultiSelect: true });
				$("#klList").ligerComboBox({ data: klList, isMultiSelect: true });
			}
			function adda(){
				var klTract = $("#klList_val").val();
				var fdTract = $("#fdList_val").val();
				var yjBeginTime = $("#yjBeginTime").val();
				var yjEndTime = $("#yjEndTime").val();
				if(klTract == -1){
					alert("请选择扣率通道");
					return;
				}
				if(fdTract == -1){
					alert("请选择封顶通道");
					return;
				}	
				
				if( len('yjBeginTime',30,'All','交易开始时间') &&
					len('yjEndTime',30,'All','交易结束时间')){
					var url = "<s:url value="/tractInfo!insertRulesAll.ac"/>";
					var group = {yjBeginTime:yjBeginTime,yjEndTime:yjEndTime,klTract:klTract,fdTract:fdTract};
					group = $.ligerui.toJSON(group);
					 $.post(url,{tractRules:group},function(data){
	                	if(data!=''&&data!=null){
	                		alert(data);
	                		window.close();
	                		window.parent.location.reload();
	                	}
                	});
				}
			}
		</script>
	</head>
	<body style="padding: 4px; overflow: hidden;" onload="lo()">
		<div id="content">
			<s:if test="message!=null">
				<span></span>
			</s:if>
			<div class="box_system">
				<form action="<s:url value="/tractInfo!insertRulesAll.ac"/>" id="addTract" method="post"  >
				<input type="hidden" value="<s:property value="tractList"/>" id="tractList"/>
					<table width="96%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td style="text-align: right;">交易开始时间：</td>
							<td>
								<input type="text"  id="yjBeginTime"
													onfocus="WdatePicker({dateFmt:'HH:mm:ss'})"
													class="inputext_style" />
							</td>
							<td style="text-align: right;">交易结束时间：</td>
							<td>
								<input type="text"  id="yjEndTime"
													onfocus="WdatePicker({dateFmt:'HH:mm:ss'})"
													class="inputext_style" />
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">扣率通道：</td>
							<td>
								<div class="l-text-wrapper" style="float:left">
								<div class="l-text l-text-combobox l-text-focus">
								<input type="text" id="klList"  class="l-text-field" ligerui-id="klList"/>
								</div>
								<input type="hidden" name="klList_val" id="klList_val" data-ligerid="klList"/>
								</div>
							</td>
							<td style="text-align: right;">封顶通道：</td>
							<td>
								<div class="l-text-wrapper" style="float:left">
								<div class="l-text l-text-combobox l-text-focus">
								<input type="text" id="fdList"  class="l-text-field" ligerui-id="fdList"/>
								</div>
								<input type="hidden" name="fdList_val" id="fdList_val" data-ligerid="fdList"/>
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="4" align="center">
								<input type="button" value="确认添加" id="btn" onclick="adda()" class="btn1" />
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<s:if test="#request.addTractInfo == 'success'">
			<script>
				alert("添加通道成功!");
			</script>
		</s:if>
		<s:if test="#request.addTractInfo == 'noPass'">
			<script>
				alert("添加通道失败!");
			</script>
		</s:if>
	</body>
</html>
