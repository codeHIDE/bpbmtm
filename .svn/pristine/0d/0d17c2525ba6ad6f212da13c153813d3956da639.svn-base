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
		<script src="<s:url value='/js/jqu	i/ligerUI/js/plugins/ligerGrid.js'/>" type="text/javascript"></script>
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
				if($("#tract").val()!=-1){
					$.post("<s:url value='/merchantInfo!selectTractInfo.ac'/>",{'tractInfo.tractId':$("#tract").val()},
					function(data){
						if(data == 'fone'){
							alert("出错");
							return;
						}
						$("#tractLine").show();
						$("#tractInfo").html("通道信息展示：手续费"+data.tractRate+"%；单卡单笔"+data.perCardQuota/100+"元；单卡累积"+data.cardCount+"次；单卡累积"+data.cardQuota/100+"元");
					},"json");
				}
			}
			function vaildata(){
				var profitType=$("#profitType").val();
				var tract=$("#tract").val();
				if(profitType=='-1'){
					alert("请选择手续费类型");
					return false;
				}
				if(tract=='-1'){
					alert("请选择通道类型");
					return false;
				}
				return true;
				/**if(profitType=='01' || profitType=='02'){
					if(rate>1){
						alert("商户手续费不能大于1");
						return false;
					}var rate=$("#rate").val();
				}else{
					if(rate>1){
						alert("T+0商户手续费不能大于1");
						return false;
					}
				}
				if(!length('lowestFee',5,'N','T+0最低封顶值'))
					return false;
				if(profitType=='01'){
					if( len('rate',5,'NS','商户手续费')&&
						len('merRatio',5,'N','机构分润比')
					)
						return true;
						return false;
				}
				if(profitType=='02'){
					if( 
					length('HighestFee',5,'N','最高封顶值')&&
					len('rate',5,'NS','商户手续费')&&
					len('merRatio',5,'N','机构分润比')
					)
						return true;
						return false;
				}
				if(profitType=='03'){
					if( len('rate',5,'NS','T+0商户手续费')&&
						len('merRatio',5,'N','机构分润比')
					)
						return true;
						return false;
				}
				if(profitType=='04'){
					if( 
					length('HighestFee',5,'N','T+0最高封顶值')&&
					len('rate',5,'NS','T+0商户手续费')&&
					len('merRatio',5,'N','机构分润比')
					)
						return true;
						return false;
				}*/
			}
			/**,'merTract.highestFee':$("#HighestFee").val()
					 ,'merTract.lowestFee':$("#lowestFee").val()
					 ,'merTract.rate':$("#rate").val(),
					 'merTract.merRatio':$("#merRatio").val()*/
			function addTractInfo(){
				if(vaildata())
				$.ajax({
	                   type: "POST",
	                   dataType: "text",
	                   url: "<s:url value='/merchantInfo!addMerTract.ac'/>",
	                   data: {'merTract.merSysId':$("#merSysId").val(),'merTract.tractId':$("#tract").val(),'merTract.profitType':$("#profitType").val()},
	                   success: function (data) {
		                      if(data=="succ"){
		                      location.reload();
		                      }else if(data=="fone"){
		                   	    $.ligerDialog.success('通道添加失败！');
		                      }else if(data=="fones"){
		                     	 $.ligerDialog.success('通道添加失败,已有该通道！');
		                      }else if(data=="fones1"){
		                      	$.ligerDialog.success('通道添加失败,请先进行交易配置！');
		                      }
	                   },
	                   error: function(data) {
		                  		 $.ligerDialog.success("错误提示:"+data.responseText);
		                         window.parent.$.ligerDialog.closeWaitting(); 
		                 }
					});
			}
			function merTractUp(sta,tid,profitType){
				$.post("<s:url value='/merchantInfo!updateMerTractStatus.ac'/>",
				{'merTract.merSysId':$("#merSysId").val(),'merTract.tractId':tid,'merTract.status':sta,'merTract.profitType':profitType},
				function(data){
					if(data=='succ'){
						location.reload();
					}
				},"text");
			}
			
			function updateMerDefault(tid,profitType){
				$.post("<s:url value='/merchantInfo!updateMerTrackDefaultStatus.ac'/>",
				{'merTract.merSysId':$("#merSysId").val(),'merTract.tractId':tid,'merTract.profitType':profitType},
				function(data){
					if(data=='succ'){
						location.reload();
					}else if(data=="fone"){
						$.ligerDialog.success('设置失败');
					}
				},"text");
			}
			
			function change(){
				//在此判断通道类型和手续费类型都不=-1
				//var rate=$("#rate");  
				//var merRatio=$("#merRatio");
				//var lowestFee=$("#lowestFee");
				//var highestFee=$("#HighestFee");
				var payTract=$("#payTract").val();
				var profitType = $("#profitType").val();
				var cardFlag = $("#cardFlag").val();
				if(profitType!='-1')
					$.post("<s:url value='/merchantInfo!getAllTractList.ac'/>",
					{'tractInfo.ratesType':profitType,'tractInfo.payTract':payTract,'tractInfo.cardFlag':cardFlag},
					function (data){
						strObj = eval(data);
						$("#tract").empty();
						$("#tract").append("<option value='-1'>--选择通道--</option>");
						$.each(strObj, function(i) {
							$("#tract").append("<option value='"+strObj[i].tractId+"'>"+strObj[i].tractName+"|"+strObj[i].tractAgency+"</option>");
						});
					},"json");
				if(profitType=='-1'){
					/**$(".label1").css("visibility","hidden");
	        		$(".label2").css("visibility","hidden");
	        		$(".td_show").css("visibility","hidden");*/
	        		$("#tract").empty();
					$("#tract").append("<option value='-1'>--选择通道--</option>");
					/**rate.val('');
					merRatio.val('');
					lowestFee.val('');
					highestFee.val('');*/
				}
				/**else if(profitType=='01'){
	        		$(".label1").css("visibility","hidden");
	        		$(".label2").css("visibility","hidden");
	        		$(".td_show").css("visibility","hidden");
	        		highestFee.val('');
	        	}else if(profitType=='02'){
	        		$(".td_show").css("visibility","visible");
	        		$(".label1").css("visibility","hidden");
	        		$(".label2").css("visibility","hidden");
	        	}else if(profitType=='03'){
	        		$(".label1").css("visibility","visible");
	        		$(".td_show").css("visibility","hidden");
	        		$(".label2").css("visibility","hidden");
	        		highestFee.val('');
	        	}else if(profitType=='04'){
	        		$(".td_show").css("visibility","visible");
	        		$(".label1").css("visibility","visible");
	        		$(".label2").css("visibility","visible");
	        	}else{
	        		$(".td_show").css("visibility","hidden");
	        	}*/
			}

		function f_open5(tractId,profitType)
        {
            $.ligerDialog.open({ url: '<s:url value='/merchantInfo!getTractAndMerToUpdate.ac'/>?merTract.tractId='+tractId+'&merTract.merSysId='+$("#merSysId").val()+'&merTract.profitType='+profitType,
            					 height:250,width: 550, isResize: false,title:'修改机构配置信息'});    
        }
		</script>
  </head>
  <body style="padding: 15px;">
  <input type="hidden" id="merSysId" value="<s:property value='merchantInfo.merSysId'/>"/>
    <div id="content">
			<div class="box_system">
				<table width="100%" border="0" cellpadding="0" cellspacing="0" style="font-size:12px;text-align:center;">
					<tr>
						<td colspan="4">机构商户：<s:property value="merchantInfo.merName"/>
						(<s:property value="merchantInfo.merSysId"/>)</td>
					</tr>
					<tr>
					<td>手续费类型：</td>
						<td>
							<div class="l-text" >
								<select  id="profitType" onchange="change();" class="l-text-field" style="width:130px">
									<option value="-1"> ---请选择--- </option>
									<option value="01">扣率</option>
									<option value="02">封顶型</option>
									<option value="05">积分扣率</option>
									<!-- <option value="03">T+0 扣率</option>
									<option value="04">T+0 封顶型</option> -->
								</select>
							</div>
						</td>
						<td>
							支付通道：
						</td>
						<td>
							<div class="l-text" >
								<select onchange="change();" style="width:130px" class="l-text-field" id="payTract">
									<option value="-1">
										---请选择---
									</option>
									<option value="CSTP">
										CSTP
									</option>
									<option value="BJPOSP">
										BJPOSP
									</option>
									<option value="UMSBJ">
										UMSBJ
									</option>
									<option value="RUIYIN">
										RUIYIN
									</option>
								</select>
							</div>
						</td>
					</tr>
					 <tr>
					 	<!-- <td>支持卡类型<br/>(仅限于借贷记路由用)：</td>
						<td>
					     	<div class="l-text" >
								<select id="cardFlag" onchange="change()" style="width:130px" class="l-text-field">
									<option value="0">--全部--</option>
									<option value="1">借记卡</option>
									<option value="2">贷记卡</option>
								</select>
							</div><div style="width:10px;float:left;"></div>
						</td> -->
						<td>选择通道：</td>
						<td>
					     	<div class="l-text" >
								<select id="tract" onchange="tractInfo()" style="width:130px" class="l-text-field">
									<option value="-1">--选择通道--</option>
								</select>
							</div>
						</td>
						<!--<td><label class="label1" style="visibility: hidden">T+0</label>商户手续费：</td>
						<td>
							<div class="l-text" style="width:130px;float:left;"><input class="l-text-field" type="text" size="4" id="rate"/></div><div style="width:10px;float:left;">%</div>
						</td>-->
					</tr>
					<!--<tr>
					<td><label class="aaa" style="visibility: hidden">T+0</label>机构分润比：</td>
							<td>
								<div class="l-text" style="width:130px;float:left;"><input type="text" size="4" class="l-text-field" id="merRatio"/></div><div style="width:10px;float:left;">%</div>
							</td>
					<td><label class="label2" style="visibility: hidden">T+0</label>最低封底值：</td>
							<td><div class="l-text" style="width:130px;float:left;">
								<input class="l-text-field"   size="4" type="text" id="lowestFee" /></div><div style="width:10px;float:left;">元</div>
								
							</td>
					</tr>
					<tr>
							<td  class="td_show" style="visibility: hidden"><label class="label2" style="visibility: hidden">T+0</label>最高封顶值：</td>
							<td class="td_show" style="visibility: hidden;white-space:nowrap" > <div class="l-text" style="width:130px;float:left;">
								<input  size="4" type="text" class="l-text-field"  id="HighestFee" /></div><div style="width:10px;float:left;">元</div>
							</td>
							<td colspan="2"></td>
					</tr> -->
					<tr>
							<td colspan="4">
								<input type="button" value="确认申请" id="btn" onclick="addTractInfo()"/>
							</td>
					</tr>
					<tr style="display: none" id="tractLine" align="center">
						<td colspan="5" style="color:gray" id="tractInfo">
						</td>
					</tr>
				</table>
			</div>
			
			<div class="box_system">
				<table width="100%" border="1" cellpadding="0" cellspacing="0" class="tdata" style="font-size:12px;">
					<tr>
						<td>通道号</td>
						<td>通道名</td>
						<td>费率类型</td>
						<td>支付通道</td>
						<td>支付商户号</td>
						<td>状态</td>
						<td>单笔限额(元)</td>
						<td>累积次数</td>
						<td>累积限额(元)</td>
						<td>支持卡类型</td>
						<td>操作</td>
					</tr>
					<s:if test="merTractList==null || merTractList.size<1">
					<tr>
						<td colspan="12">当前商户未配置支付通道!</td>
					</tr>
					</s:if>
					<s:else>
						<s:iterator value="merTractList" var="merTract" status="index">
						<tr>
							<td id="tractId"><s:property value="tractId"/></td>
							<td><s:property value="tractInfo.tractName"/></td>
							<td>
							<s:if test="profitType == 01">扣率</s:if>
							<s:elseif test="profitType == 02">封顶</s:elseif>
							<s:elseif test="profitType == 03">T+0扣率</s:elseif>
							<s:elseif test="profitType == 04">T+0封顶</s:elseif>
							<s:elseif test="profitType == 05">积分</s:elseif>
							</td>
							<td><s:property value="tractInfo.payTract"/></td>
							<td><s:property value="tractInfo.transMerId"/></td>
							<td><s:if test="status==0">未使用</s:if>
							<s:elseif test="status==1">使用中</s:elseif>
							<s:elseif test="status==2">停止</s:elseif>
							<s:else>未知</s:else>
							(
							<s:if test="defaultStatus==0">普通通道</s:if>
							<s:if test="defaultStatus==1"><font style="color: red">默认通道</font></s:if>
							)
							</td>
							<td>
							<fmt:formatNumber value="${tractInfo.perCardQuota/100}" pattern="0.00"/>
							</td>
							<td><s:property value="tractInfo.cardCount"/></td>
							<td>
							<fmt:formatNumber value="${tractInfo.cardQuota/100}" pattern="0.00"/>
							</td>
							<td>
								<s:if test="tractInfo.cardFlag == 1">
								借记卡
							</s:if>
							<s:elseif test="tractInfo.cardFlag == 2">
								贷记卡
							</s:elseif>
							<s:else>
								全部
							</s:else>
							</td>
							<td id="tractSta"><s:if test="status==0">
								<a href="#" onclick="merTractUp('1','<s:property value="tractId"/>','<s:property value="profitType"/>')">上线</a></s:if>
								<s:elseif test="status==1">
								<a href="#" onclick="merTractUp('0','<s:property value="tractId"/>','<s:property value="profitType"/>')">暂停</a></s:elseif>	
								<s:if test="defaultStatus==0">
								<a href="#" onclick="updateMerDefault('<s:property value="tractId"/>','<s:property value="profitType"/>')">设置默认通道</a>
								</s:if>
								<s:elseif test="defaultStatus==1">
								</s:elseif>
								<!-- <a href="#" onclick="f_open5('<s:property value="tractId"/>','<s:property value="profitType"/>')">修改</a> -->
							</td>
						</tr>
						</s:iterator>
					</s:else>
				</table>
			</div>
			
		</div>
  </body>
</html>
