<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>配置机构商户通道</title>
    	<link href="<s:url value='/js/jqui/ligerUI/skins/Aqua/css/ligerui-all.css'/>" rel="stylesheet" type="text/css" />
		<link href="<s:url value='/js/jqui/ligerUI/skins/ligerui-icons.css'/>" rel="stylesheet" type="text/css" />
		<script src="<s:url value='/js/jqui/jquery/jquery-1.5.2.min.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/core/base.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerDialog.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerGrid.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerDrag.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerResizable.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/CheckLength.js'/>" type="text/javascript"></script>
		<link href="<s:url value='/js/jqui/ligerUI/skins/Gray/css/all.css'/>" rel="stylesheet" type="text/css" />
		<link href="<s:url value='/js/jqui/ligerUI/skins/ligerui-icons.css'/>" rel="stylesheet" type="text/css" />
		<script src="<s:url value='/js/common.js'/>"></script>
		<style type="text/css">
			tr{
				height:35px;
			}
			.tdata td{text-align: center}
		</style>
		<script type="text/javascript">
			function tractInfo(){
				if($("#appTractId").val()!=-1){
					$.post("<s:url value='/merchantInfo!getTractInfo.ac'/>",{appTractId:$("#appTractId").val()},
					function(data){
						$("#tractLine").show();
						$("#tractInfo").html("通道信息展示：手续费"+data.traceFee+"%；单卡单笔"+data.perCardAmount+"元；单卡累积"+data.cardDayCount+"次；单卡累积"+data.cardDayAmount+"元");
					},"json");
				}
			}
			function vaildata(){
				var tractType=$("#tractType").val();
				var appTractId=$("#appTractId").val();
				var transRate=$("#transRate").val();
				if(tractType=='-1'){
					alert("请选择通道类别");
					return false;
				}
				if(appTractId=='-1'){
					alert("请选择通道");
					return false;
				}
				if(transRate>1){
					alert("交易扣率不能大于1");
					return false;
				}
				if(	length('lowestFee',5,'NS','最低手续费') &&
					length('highestFee',5,'NS','最高手续费') &&
					len('merProfit',5,'NS','机构分润比例') &&
					len('transRate',5,'NS','交易扣率'))
					return true;
					return false;
			}
			function addAppTractInfo(){
			if(vaildata()){
			var app=$("#appTractId").val().split('|');
			var appTractId=app[0];
			var transMerId=app[1];
			if(appTractId!='-1')
				$.ajax({
	                   type: "POST",
	                   dataType: "text",
	                   url: "<s:url value='/merchantInfo!addMerAppTract.ac'/>",
	                   data: {'merAppTract.merSysId':$("#merSysId").val(),'merAppTract.appTractId':appTractId,'merAppTract.transRate':$("#transRate").val(),
					 'merAppTract.lowestFee':$("#lowestFee").val(),'merAppTract.highestFee':$("#highestFee").val(),'merAppTract.merProfit':$("#merProfit").val()
					 ,'merAppTract.transMerId':transMerId,'merAppTract.appTractType':$("#tractType").val()},
	                   success: function (data) {
		                      if(data=="succ"){
		                      location.reload();
		                      }else if(data=="fone"){
		                   	    $.ligerDialog.success('通道添加失败！');
		                      }else if(data=="AllWrong"){
		                      	$.ligerDialog.error('通道添加失败,已有该通道！');
		                      }
	                   },
	                   error: function(data) {
		                  		 $.ligerDialog.success("错误提示:"+data.responseText);
		                         window.parent.$.ligerDialog.closeWaitting(); 
		                 }
					});
					}
			}
			function merTractUp(sta,id){
				$.post("<s:url value='/merchantInfo!updateMerAppTractStatus.ac'/>",
				{'merAppTract.merSysId':$("#merSysId").val(),'merAppTract.id':id,'merAppTract.status':sta},
				function(data){
					if(data=='succ'){
						location.reload();
					}else{
						alert("操作失败");
					}
				},"text");
			}
			function change(val){
				//在此判断通道类型和手续费类型都不=-1
				if(val!='-1'){
					$.post("<s:url value='/merchantInfo!getAllAppTractList.ac'/>",{'appTractInfo.tractType':val,'appTractInfo.transMerId':$("#merSysId").val()},
					function (data){
						strObj = eval(data);
						$("#appTractId").empty();
						$("#appTractId").append("<option value='-1'>--选择通道--</option>");
						$.each(strObj, function(i) {
							$("#appTractId").append("<option value='"+strObj[i].appTractId+"|"+strObj[i].transMerId+"'>"+strObj[i].appTractName+"</option>");
						});
					},"json");
				}else{
					$("#appTractId").empty();
						$("#appTractId").append("<option value='-1'>--选择通道--</option>");
				}
			}

		function f_open5(appTractId)
        {
            $.ligerDialog.open({ url: "<s:url value='/merchantInfo!getTractAndMerAppToUpdate.ac'/>?merAppTract.appTractId="+appTractId+"&merAppTract.merSysId="+$("#merSysId").val(),
            					 height:250,width: 550, isResize: false,title:'修改机构配置信息'});    
        }
		</script>
  </head>
  <body style="padding: 15px;">
  <input type="hidden" id="merSysId" value="<s:property value="merchantInfo.merSysId"/>"></input>
    <div id="content">
			<div class="box_system">
				<table width="100%" border="0" cellpadding="0" cellspacing="0" style="font-size:12px;text-align:center;">
					<tr>
						<td colspan="4">机构商户：<s:property value="merchantInfo.merName"/>
						(<s:property value="merchantInfo.merSysId"/>)</td>
					</tr>
					<tr>
					<td>通道类别：</td>
						<td><div class="l-text" style="width:130px;">
								<select  id="tractType"  class="l-text-field" onchange="change(this.value);" style="width:130px">
									<option value="-1"> ------请选择------ </option>
									<option value="01">还款</option>
									<option value="02">转账</option>
									<option value="03">查询余额</option>
									<option value="04">手机充值</option>
								</select></div>
							</td>
							<td>选择通道： </td>
						<td><div class="l-text"  style="width: 130px;">
							<select id="appTractId" onchange="tractInfo()"  class="l-text-field" style="width:130px">
								<option value="-1">--选择通道--</option>
							</select></div>
						</td>
						</tr>
						<tr>
							<td>交易扣率</td>
							<td>
								<div class="l-text" style="width:130px;float:left;"><input type="text"  class="l-text-field" size="4" id="transRate"/></div><div style="width:10px;float:left;">%</div>
							</td>
							<td>机构分润比：</td>
							<td>
								<div class="l-text" style="width:130px;float:left;"><input type="text"  class="l-text-field" size="4" id="merProfit"/></div><div style="width:10px;float:left;">%</div>
							</td>
					</tr>
					<tr>
					<td>最低手续费：</td>
					<td>
						<div class="l-text" style="width:130px;float:left;"><input   size="4" type="text"  class="l-text-field" id="lowestFee" /></div><div style="width:10px;float:left;">元</div>
					</td>
					<td>最高手续费：</td>
					<td> 
						<div class="l-text" style="width:130px;float:left;"><input   size="4" type="text"  class="l-text-field" id="highestFee" /></div><div style="width:10px;float:left;">元</div>
					</td>	
					</tr>
					<tr>
							<td colspan="4">
								<input type="button" value="确认申请" id="btn" onclick="addAppTractInfo()"/>
							</td>
					</tr>
					<tr style="display: none" id="tractLine" align="center">
						<td colspan="4" style="color:gray" id="tractInfo">
						</td>
					</tr>
				</table>
			</div>
			
			<div class="box_system">
				<table width="100%" border="1" cellpadding="0" cellspacing="0" class="tdata" style="font-size:12px;">
					<tr>
						<td>应用通道号</td>
						<td>交易商户号</td>
						<td>交易扣率</td>
						<td>最低手续费</td>
						<td>最高手续费</td>
						<td>机构分润比例</td>
						<td>状态</td>
						<td>通道类别</td>
						<td>创建时间</td>
						<td>操作</td>
					</tr>
					<s:if test="merAppTractList==null || merAppTractList.size<1">
					<tr>
						<td colspan="9">当前商户未配置支付通道!</td>
					</tr>
					</s:if>
					<s:else>
						<s:iterator value="merAppTractList" var="merTract" status="index">
						<tr>
							<td id="appTractId"><s:property value="appTractId"/></td>
							<td><s:property value="transMerId"/></td>
							<td><s:property value="transRate"/></td>
							<td>
								<s:if test="lowestFee!='-1'">
									<s:set var="lo" value="lowestFee" />
									<fmt:formatNumber value="${lo/100}" type="currency" pattern="0.00"></fmt:formatNumber> 元
								</s:if>
								<s:else>
									0 元
								</s:else>
							</td>
							<td>
								<s:if test="highestFee!='-1'">
									<s:set var="hi" value="highestFee" />
									<fmt:formatNumber value="${hi/100}" type="currency" pattern="0.00"></fmt:formatNumber> 元
								</s:if>
								<s:else>
									0 元
								</s:else>
							</td>
							<td><s:property value="merProfit"/></td>
							<td><s:if test="status==0">未使用</s:if>
							<s:elseif test="status==1">正在使用</s:elseif>
							<s:else>未知</s:else>
							</td>
							<td>
								<s:if test="appTractType==01">
								<span>还款</span>
								</s:if>
								<s:if test="appTractType==02">
								<span>转账</span>
								</s:if>
								<s:if test="appTractType==03">
								<span>查询余额</span>
								</s:if>
								<s:if test="appTractType==04">
								<span>手机充值</span>
								</s:if>
							</td>
							<td><s:property value="createTime"/></td>
							<td id="tractSta"><s:if test="status==0">
								<a href="#" onclick="merTractUp('1','<s:property value="id"/>')">上线</a></s:if>
								<s:elseif test="status==1">
								<a href="#" onclick="merTractUp('0','<s:property value="id"/>')">暂停</a></s:elseif>	
								<a href="#" onclick="f_open5('<s:property value="appTractId"/>')">修改</a>
							</td>
						</tr>
						</s:iterator>
					</s:else>
				</table>
			</div>
			
		</div>
  </body>
</html>
