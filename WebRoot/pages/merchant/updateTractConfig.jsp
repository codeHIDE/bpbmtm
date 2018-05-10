<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head><meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>商户修改结算信息</title>
		<link href="<s:url value='/js/jqui/ligerUI/skins/Aqua/css/ligerui-all.css'/>" rel="stylesheet" type="text/css" />
		<link href="<s:url value='/js/jqui/ligerUI/skins/ligerui-icons.css'/>" rel="stylesheet" type="text/css" />
		<link href="<s:url value='/js/jqui/ligerUI/skins/Gray/css/all.css'/>" rel="stylesheet" type="text/css" />
		<script src="<s:url value='/js/jqui/jquery/jquery-1.5.2.min.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/core/base.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerToolBar .js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerDialog.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerGrid.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerFilter.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerDrag.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerResizable.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerGrid.showFilter.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/CustomersData.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/CheckLength.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerDateEditor.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/common.js'/>"></script>
		<script type="text/javascript">
		function update(){
			if(vaildata())
				$.ligerDialog.confirm('确定修改信息?', function (yes) { 
					if(yes){
						$.ajax({
		                   type: "POST",
		                   dataType: "text",
		                    url: "<s:url value='/merchantInfo!tractAndMerToUpdate.ac'/>",
		                   data: $('#form1').serialize(),
		                   success: function (data) {
		                      if(data=="succ"){
		                   	    $.ligerDialog.success('修改成功！');
		                      }else if (data=="succ"){
		                      	$.ligerDialog.success('修改失败！');
		                      }
		                   },
		                   error: function(data) {
			                  		 $.ligerDialog.success("错误提示:"+data.responseText);
			                         window.parent.$.ligerDialog.closeWaitting(); 
			                 }
						});
						
					}else{
							dialogClose();
						}	
				});
		}
					
				function cls(){
				window.parent.$.ligerDialog.close();
	            window.parent.$(".l-dialog,.l-window-mask").remove();
			}
			function vaildata(){
				var profitType=$("#profitType").val();
				if(profitType=='01' || profitType=='02'){
					var rate=$("#rate12").val();
					if(rate>1){
						alert("商户手续费不能大于1");
						return false;
					}
				}else{
					var rate=$("#rate34").val();
					if(rate>1){
						alert("T+0商户手续费不能大于1");
						return false;
					}
				}
				if(profitType=='01' || profitType=='02' || profitType=='03'){
					if(!length('lowestFee123',5,'N','最低封顶值'))
					return false;
				}
				if(profitType=='04'){
					if(!length('lowestFee4',5,'N','T+0最低封顶值'))
					return false;
				}
				if(profitType=='01'){
					if( len('rate12',5,'NS','商户手续费')&&
						len('merRatio',5,'N','机构分润比')
					)
						return true;
						return false;
				}
				if(profitType=='02'){
					if( 
					lengrh('highestFee2',5,'N','最高封顶值')&&
					len('rate12',5,'NS','商户手续费')&&
					len('merRatio',5,'N','机构分润比')
					)
						return true;
						return false;
				}
				if(profitType=='03'){
					if( len('rate34',5,'NS','T+0商户手续费')&&
						len('merRatio',5,'N','机构分润比')
					)
						return true;
						return false;
				}
				if(profitType=='04'){
					if( 
					length('highestFee4',5,'N','T+0最高封顶值')&&
					len('rate34',5,'NS','T+0商户手续费')&&
					len('merRatio',5,'N','机构分润比')
					)
						return true;
						return false;
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
				<form id="form1" action="<s:url value='/merchantInfo!updateSettleMerchantInfo.ac'/>" method="post">
					<table width="98%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td style="text-align: right;">
								机构商户号：
							</td>
							<td>
								<s:property value="merTract.merSysId" />
								<input type="hidden" name="merTract.merSysId" value="<s:property value="merTract.merSysId"/>"></input>
								<input type="hidden" name="merTract2.merSysId" value="<s:property value="merTract.merSysId"/>"></input>
							</td>
							<td width="120px" style="text-align: right;">
								通道号：
							</td>
							<td >
								<s:property value="merTract.tractId" />
								<input type="hidden" name="merTract.tractId" value="<s:property value="merTract.tractId"/>"></input>
								<input type="hidden" name="merTract2.tractId" value="<s:property value="merTract.tractId"/>"></input>
							</td>
							
						</tr>
						<tr>
							<td style="text-align: right;">
									通道状态：
								</td>
								<td id="status">
									<s:if test="merTract.status==0">
										<span style="color: red">未启用</span>
									</s:if>
									<s:if test="merTract.status==1">
										<span style="color: green">正在使用</span>
									</s:if>
									<input type="hidden" name="merTract.status" id="status" value="<s:property value="merTract.status"/>"></input>
									<input type="hidden" name="merTract2.status" id="status" value="<s:property value="merTract.status"/>"></input>
								</td>
								<td style="text-align: right;">
									费率类型：
								</td>
								<td id="status">
									<s:if test="merTract.profitType=='01'">
										<span style="color: red">扣率</span>
									</s:if>
									<s:if test="merTract.profitType=='02'">
										<span style="color: red">封顶</span>
									</s:if>
									<s:if test="merTract.profitType=='03'">
										<span style="color: red">T+0扣率</span>
									</s:if>
									<s:if test="merTract.profitType=='04'">
										<span style="color: red">T+0封顶</span>
									</s:if>
									<input type="hidden" name="merTract.profitType" id="profitType" value="<s:property value="merTract.profitType"/>"></input>
									<input type="hidden" name="merTract2.profitType" id="profitType" value="<s:property value="merTract.profitType"/>"></input>
								</td>
							</tr>
							<tr>
							<s:if test="merTract.profitType=='03' or merTract.profitType=='04' ">
								<td width="150px" style="text-align: right;">
									T+0商户手续费：
								</td>
								<td>
									<input type="text" size="4" name="merTract.rate" id="rate34" value="<s:property value="merTract.rate"/>"></input> %
									<input type="hidden" name="merTract2.rate" value="<s:property value="merTract.rate"/>"></input>
								</td>
							</s:if>
							<s:if test="merTract.profitType=='01' or merTract.profitType=='02'">
								<td width="150px" style="text-align: right;">
									商户手续费：
								</td>
								<td>
									<input type="text" size="4" name="merTract.rate" id="rate12" value="<s:property value="merTract.rate"/>"></input> %
									<input type="hidden" name="merTract2.rate" value="<s:property value="merTract.rate"/>"></input>
								</td>
							</s:if>
								<td width="150px" style="text-align: right;">
									机构分润比：
								</td>
								<td>
									<input type="text" size="4" name="merTract.merRatio" id="merRatio" value="<s:property value="merTract.merRatio"/>"></input> %
									<input type="hidden" name="merTract2.merRatio" value="<s:property value="merTract.merRatio"/>"></input>
								</td>
								
								
							</tr>
							<tr>
							<s:if test="merTract.profitType=='01'  or merTract.profitType=='02' or merTract.profitType=='03'">
								<td width="150px" style="text-align: right;">
									最低手续费：
								</td>
								 <td>
									<input type="text"  size="4" name="merTract.lowestFee" id="lowestFee123" 
									<s:if test="merTract.lowestFee == -1">value=""</s:if>
									<s:elseif test="merTract.lowestFee == 0">value="0"</s:elseif>
										<s:else>value="<fmt:formatNumber value="${merTract.lowestFee/100}" pattern="0"/>"</s:else>></input> 元
									<input type="hidden"  size="4" name="merTract2.lowestFee"
									<s:if test="merTract.lowestFee == -1">value=""</s:if>
									<s:elseif test="merTract.lowestFee == 0">value="0"</s:elseif>
										<s:else>value="<fmt:formatNumber value="${merTract.lowestFee/100}" pattern="0"/>"</s:else>></input>
								</td>
								</s:if>
								<s:if test="merTract.profitType=='04'">
								<td width="150px" style="text-align: right;">
									T+0最低手续费：
								</td>
								 <td>
									<input type="text"  size="4" name="merTract.lowestFee"  id="lowestFee4" 
									<s:if test="merTract.lowestFee == -1">value=""</s:if>
									<s:elseif test="merTract.lowestFee == 0">value="0"</s:elseif>
										<s:else>value="<fmt:formatNumber value="${merTract.lowestFee/100}" pattern="0"/>"</s:else>></input> 元
									<input type="hidden"  size="4" name="merTract2.lowestFee"
									<s:if test="merTract.lowestFee == -1">value=""</s:if>
									<s:elseif test="merTract.lowestFee == 0">value="0"</s:elseif>
										<s:else>value="<fmt:formatNumber value="${merTract.lowestFee/100}" pattern="0"/>"</s:else>></input>
								</td>
								</s:if>
								<s:if test="merTract.profitType=='02'">
									<td width="150px" style="text-align: right;">
										封顶手续费：
									</td>
									<td>
										<input type="text"  size="4" name="merTract.highestFee"  id="highestFee2" 
										<s:if test="merTract.highestFee == -1">value=""</s:if>
										<s:elseif test="merTract.highestFee == 0">value="0"</s:elseif>
										<s:else>value="<fmt:formatNumber value="${merTract.highestFee/100}" pattern="0"/>"</s:else>></input> 元
										<input type="hidden"  size="4" name="merTract2.highestFee"
										<s:if test="merTract.highestFee == -1">value=""</s:if>
										<s:elseif test="merTract.highestFee == 0">value="0"</s:elseif>
										<s:else>value="<fmt:formatNumber value="${merTract.highestFee/100}" pattern="0"/>"</s:else>></input>
									</td>
								</s:if>
								<s:if test="merTract.profitType=='04'">
									<td width="150px" style="text-align: right;">
											T+0封顶手续费：
									</td>
									<td>
										<input type="text"  size="4" name="merTract.highestFee" id="highestFee4" 
										<s:if test="merTract.highestFee == -1">value=""</s:if>
										<s:elseif test="merTract.highestFee == 0">value="0"</s:elseif>
										<s:else>value="<fmt:formatNumber value="${merTract.highestFee/100}" pattern="0"/>"</s:else>></input> 元
										<input type="hidden"  size="4" name="merTract2.highestFee" 
										<s:if test="merTract.highestFee == -1">value=""</s:if>
										<s:elseif test="merTract.highestFee == 0">value="0"</s:elseif>
										<s:else>value="<fmt:formatNumber value="${merTract.highestFee/100}" pattern="0"/>"</s:else>></input>
									</td>
								</s:if>
							</tr>
							<tr>
								
							</tr>
							

						<tr>
							<td colspan="2" align="center">
								<br />
								<input type="button" value="确认修改" id="btn" class="l-button" onclick="update()" />
							</td>
							<td colspan="2" align="center">
								<br />
								<input type="button" value="关 闭" id="btn2" class="l-button" onclick="cls()" />
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</body>
</html>
