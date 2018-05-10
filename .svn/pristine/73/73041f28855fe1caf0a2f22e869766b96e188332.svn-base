<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>机构通道规则</title>
		<link href="<s:url value='/js/jqui/ligerUI/skins/Aqua/css/ligerui-all.css'/>" rel="stylesheet" type="text/css" />
		<link href="<s:url value='/js/jqui/ligerUI/skins/ligerui-icons.css'/>" rel="stylesheet" type="text/css" />
		<link href="<s:url value='/js/jqui/ligerUI/skins/Gray/css/all.css'/>" rel="stylesheet" type="text/css" />
		<script src="<s:url value='/js/jqui/jquery/jquery-1.5.2.min.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/core/base.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerToolBar.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerDialog.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerGrid.js'/>" type="text/javascript"></script>
		<!-- <script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerFilter.js'/>" type="text/javascript"></script>  -->
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerFilters.js'/>" type="text/javascript"></script>
				<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerComboBox.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerDrag.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerResizable.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerGrid.showFilter.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerDateEditor.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/common.js'/>"></script>
		<script type="text/javascript">
			function cls(){
	            window.parent.$.ligerDialog.close();
	            window.parent.$(".l-dialog,.l-window-mask").remove(); 
			}
			
			$(function ()
        	{
        		var tractFields=[];
        		var mccFields = [];
				var tractList=eval($("#tractConfigList").val());
				$.each(tractList,function(index){
					var str = "";
					if(tractList[index].tractInfo.ratesType == '01'){
						str="扣率,"+tractList[index].tractInfo.tractRate+"%";
					}else if(tractList[index].tractInfo.ratesType == '02'){
						str="封顶,"+tractList[index].tractInfo.tractRate+"%,"+tractList[index].tractInfo.tractHighestFee+"元";
					}
					tractFields[index]="{display:'"+tractList[index].tractId+"',name:'"+tractList[index].tractInfo.tractName+"',value:'"+str+"'}";
				});
				
				
				var arr = eval('[' + tractFields + ']');
				var hash = {};
				for(var i = 0; i < arr.length; i++) {
					(hash[arr[i]] == undefined) && (hash[arr[i]["display"]+","+arr[i]["name"]+","+arr[i]["value"]]=arr[i]["display"]+","+arr[i]["name"]+","+arr[i]["value"]);
				}
				var tractField = [];
				var a = 0;
				for(var o in hash){
					var c = o.split(',');
					var t = c[2]+","+c[3];
					if(c.length == 5)
						t+=(","+c[4]);
					tractField[a]="{display:'"+c[0]+"',name:'"+c[1]+"',value:'"+t+"'}";
					a++;
				}
				
	            var fields = {conditions: eval('[' + tractField + ']')};
	
	            var filter = $("#filter").ligerFilter({ fields: fields });
	
	            $("#Button1").click(function ()
	            {
	                var group = $.ligerui.toJSON(filter.getData());
	                if(group != null && group.length != 2){
		                var url="<s:url value="/merchantInfo!insertRulesMerAmt.ac" />";
		                var merSysId = tractList[0].merSysId;
		                //$("#txtGroup").val($.ligerui.toJSON(group));
		                if(confirm("确定要提交吗?")){
		                	 $.post(url,{tractRules:group,'merTrans.merSysId':merSysId},function(data){
			                	if(data!=''&&data!=null){
			                		alert(data);
			                	}
		                	});
		                }
	                }
	            });
        });
         
		</script>

		<style type="text/css">
			#txtGroup {
				height: 282px;
				width: 463px;
			}
			#Button1 {
				margin-left: 45%;
			}
		</style>
	</head>
	<body style="padding: 4px;">
		<form id="form">
			<div id="filter" style="border: 1px solid #d3d3d3;"></div>
			<input type="hidden" id="tractConfigList"
				value="<s:property value="tractInfoListJson"/>" />
		</form>
		<table width="100%" border="1" cellpadding="0" cellspacing="0">
			<tr>
				<td>
					序列号
				</td>
				<td>
					通道编号
				</td>
				<td>
					通道名称
				</td>
				<td>
					通道类型
				</td>
				<td>
					机构手续费
				</td>
				<!-- 
				<td>
					最高手续费
				</td>
				<td>
					机构交易阀值
				</td>
				 -->
				<td>
					通道手续费
				</td>
				<td>
					通道封顶值
				</td>
				<td>
					通道交易阀值
				</td>
				<td>
					信用卡成本
				</td>
				<td>
					借记卡成本
				</td>
				<td>
					信用卡最高成本
				</td>
				<td>
					借记卡最高成本
				</td>
			</tr>
			<s:iterator var="merTract" value="merTractList" status="index">
				<tr>
					<td>
						<s:property value="#index.index+1" />
					</td>
					<td>
						<s:property value="#merTract.tractId" />
					</td>
					<td>
						<s:property value="#merTract.tractInfo.tractName" />
					</td>
					<td>
						<s:if test="#merTract.profitType=='01'">
							扣率型
						</s:if>
						<s:if test="#merTract.profitType=='02'">
							封顶型
						</s:if>
					</td>
					<td>
						<s:property value="#merTract.rate" />
						%
					</td>
					<!-- 
					<td>
						<s:if test="#merTract.tractInfo.perHighestFee!=null and #merTract.tractInfo.perHighestFee!=''">
							<s:set var="amt" value="#merTract.tractInfo.perHighestFee" />
							<fmt:formatNumber value="${amt/100}" type="currency"
								pattern="0.00"></fmt:formatNumber>
							元
						</s:if>
					</td>
					<td>
						<s:if test="#merTract.tractInfo.perHighestFee!=null and #merTract.tractInfo.perHighestFee!='' 
							and #merTract.tractInfo.merCost!=null and #merTract.tractInfo.merCost!=''">
							<s:set var="amt" value="#merTract.tractInfo.perHighestFee" />
							<s:set var="cost" value="#merTract.tractInfo.merCost" />
							<fmt:formatNumber value="${amt/cost}" type="currency"
								pattern="0.00"></fmt:formatNumber>
							元
						</s:if>
					</td>
					 -->
					<td>
						<s:property value="#merTract.tractInfo.tractRate" />%
					</td>
					<td>
						<s:if test="#merTract.tractInfo.tractHighestFee!=null and #merTract.tractInfo.tractHighestFee!=''">
							<s:set var="amt" value="#merTract.tractInfo.tractHighestFee" />
							<fmt:formatNumber value="${amt/100}" type="currency"
								pattern="0.00"></fmt:formatNumber>
							元
						</s:if>
					</td>
					<td>
						<s:if test="#merTract.tractInfo.tractQuota!=null and #merTract.tractInfo.tractQuota!=''">
							<s:set var="amt" value="#merTract.tractInfo.perHighestFee" />
							<fmt:formatNumber value="${amt/100}" type="currency"
								pattern="0.00"></fmt:formatNumber>
							元
						</s:if>
					</td>
					<td>
					<s:if test="#merTract.tractInfo.creditRateCost!=null and #merTract.tractInfo.creditRateCost!=''">
						<s:set var="amt" value="#merTract.tractInfo.creditRateCost" />
						<fmt:formatNumber value="${amt/100}" type="currency"
							pattern="0.00"></fmt:formatNumber>
						元
					</s:if>
					</td>
					<td>
					<s:if test="#merTract.tractInfo.debitRateCost!=null and #merTract.tractInfo.debitRateCost!=''">
						<s:set var="amt" value="#merTract.tractInfo.debitRateCost" />
						<fmt:formatNumber value="${amt/100}" type="currency"
							pattern="0.00"></fmt:formatNumber>
						元
					</s:if>
					</td>
					<td>
					<s:if test="#merTract.tractInfo.creditHighestCost!=null and #merTract.tractInfo.creditHighestCost!=''">
						<s:set var="amt" value="#merTract.tractInfo.creditHighestCost" />
						<fmt:formatNumber value="${amt/100}" type="currency"
							pattern="0.00"></fmt:formatNumber>
						元
					</s:if>
					</td>
					<td>
					<s:if test="#merTract.tractInfo.debitHighestCost!=null and #merTract.tractInfo.debitHighestCost!=''">
						<s:set var="amt" value="#merTract.tractInfo.debitHighestCost" />
						<fmt:formatNumber value="${amt/100}" type="currency"
							pattern="0.00"></fmt:formatNumber>
						元
					</s:if>
					</td>
				</tr>
			</s:iterator>
		</table>
		<p>
			<input id="Button1" type="button" value="确认提交" />
			<input type="button" value="关 闭" id="btn2" onclick="cls()" />
		</p>
	</body>
</html>
