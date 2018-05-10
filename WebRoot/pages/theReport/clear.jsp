<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>清 分</title>
		<link href="<s:url value='/js/jqui/ligerUI/skins/Aqua/css/ligerui-all.css'/>" rel="stylesheet" type="text/css" />
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
		<script src="<s:url value='/js/common.js'/>" type="text/javascript"></script>
		<script type="text/javascript">
		
					
			function cleara(busMerId,settleDate){
					var settleDate=$("#settleDate").val();
					var busMerId=$("#busMerId").val();
					 window.parent.$.ligerDialog.open({ url:'<s:url value="/settleStatictis!getMerchantList.ac"/>?busMerId='+busMerId+'&settleDate='+$("#settleDate").val(),
								 height:550,width:900, isResize: false,title:'子商户清分列表'});  
			}
			
				function lookCleara(busMerId,settleDate){
					var settleDate=$("#settleDate").val();
					var busMerId=$("#busMerId").val();
					 window.parent.$.ligerDialog.open({ url:'<s:url value="/settleStatictis!getMerchantListClear.ac"/>?busMerId='+busMerId+'&settleDate='+$("#settleDate").val(),
								 height:550,width:900, isResize: false,title:'子商户清分列表'});  
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
			
			td{
					width: 1000px;
			}
		</style>
	</head>
	<body style="padding: 4px; overflow: hidden;">

		<div id="content">
			<s:if test="message!=null">
				<span><s:property value="message" /> </span>
			</s:if>
			<div class="box_system">
						<table width="95%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="120px" style="text-align: right;">
								商户名：
							</td>
							<td>
								<s:property value="settleStatictis.subMerName" />
								<input type="hidden" name="settleStatictis.subMerName" value="<s:property value="settleStatictis.subMerName"/>"></input>
								<s:if test="subMerInfo.subMerId==''">未填写</s:if>
								<s:if test="subMerInfo.subMerId==null">未填写</s:if>
								<input type="hidden" name="settleStatictis.busMerId" id="busMerId" value="<s:property value="settleStatictis.busMerId"/>"></input>
								<input type="hidden" name="settleStatictis.settleDate" id="settleDate" value="<s:property value="settleStatictis.settleDate"/>"></input>
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">
								应发款：
							</td>
							<td>
								<s:set var="transss" value="settleStatictis.transss" /><fmt:formatNumber value="${transss/100}" type="currency" pattern="0.00"></fmt:formatNumber> 元
								<input type="hidden" name="settleStatictis.transss" value="<s:property value="settleStatictis.transss"/>"></input>
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">
								清分状态：
							</td>
							<td>
								<s:if test="settleStatictis.clearStatus==0">未清分</s:if>
								<s:if test="settleStatictis.clearStatus==1">已清分</s:if>
								<input type="hidden" name="settleStatictis.clearStatus" value="<s:property value="settleStatictis.clearStatus"/>"></input>
							</td>
						</tr>
						<tr>
							<td>
								<input type="hidden" name="settleStatictis.busMerId" id="busMerId" value="<s:property value="settleStatictis.busMerId"/>"></input>
								<input type="hidden" name="settleStatictis.settleDate" id="settleDate" value="<s:property value="settleStatictis.settleDate"/>"></input>
							</td>
						</tr>
						<tr>		
							<td colspan="4" align="center">
							<br />
							<s:if test="settleStatictis.clearStatus==0"> 
								<input type="button" value="确认清分"  id="btn1" class="l-button" onclick="cleara('<s:property value="settleStatictis.busMerId"/>','<s:property value="settleStatictis.settleDate"/>')" />
							</s:if>
							<s:if test="settleStatictis.clearStatus==1"> 
								<input type="button" value="查看明细"  id="btn1" class="l-button" onclick="lookCleara('<s:property value="settleStatictis.busMerId"/>','<s:property value="settleStatictis.settleDate"/>')" />
							</s:if>
								<input type="button" value="关 闭"  id="btn2" class="l-button" onclick="cls()" />
							</td>	
						</tr>
					</table>
			</div>
		</div>

	</body>
</html>
