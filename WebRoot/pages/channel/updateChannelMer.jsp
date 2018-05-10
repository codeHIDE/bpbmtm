<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>编辑渠道商户</title>
		<link href="<s:url value='/js/jqui/ligerUI/skins/Aqua/css/ligerui-all.css'/>" rel="stylesheet" type="text/css" />
		<link href="<s:url value='/js/jqui/ligerUI/skins/Gray/css/all.css'/>" rel="stylesheet" type="text/css" />
		<script src="<s:url value='/js/jqui/jquery/jquery-1.5.2.min.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/core/base.js'/>" type="text/javascr" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerDialog.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerFilter.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerGrid.showFilter.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerDateEditor.js'/>" type="text/javascript"></script>
		<script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
		<script src="<s:url value='/js/jqui/jquery-validation/jquery.validate.min.js'/>" type="text/javascript"></script> 
		<script src="<s:url value='/js/CheckLength.js'/>" type="text/javascript"></script> 
		<script type="text/javascript" src="<s:url value='/js/My97DatePicker_1/WdatePicker.js' />"></script>
		<!-- 选色器所需js和css
		<link rel="stylesheet" href="<s:url value='/css/colorpicker.css'/>" type="text/css" /> -->
       	<script type="text/javascript" src="<s:url value='/js/colorpicker/colorpicker.js'/>"></script>
		<!-- 选色器所需js和css -->
		<style type="text/css">
			tr{
				height:30px;
			}
			input{
				width: 220px;
			}
	        .l-table-edit {}
	        .l-table-edit-td{ padding:4px;}
	        .l-button-submit,.l-button-test{width:80px; float:left; margin-left:10px; padding-bottom:2px;}
	        .l-verify-tip{ left:230px; top:120px;}
		</style>
		<script type="text/javascript">
			function updateMer(){
				window.parent.$.ligerDialog.confirm('是否要保存信息?', function (yes) {
				if(yes){
					//window.parent.$.ligerDialog.waitting('正在保存中,请稍候...'); 
					$.ajax({
		                   type: "POST",
		                   dataType: "html",
		                   url: '<s:url value="/channel!updateChannelMerInfo.ac"/>',
		                   data: $('#addMerchant').serialize(),
		                   success: function (data) {
		                      	   window.parent.$.ligerDialog.closeWaitting(); 
		                       	   alert(data);
								  window.parent.$.ligerDialog.closeWaitting(); 
		                   }
		                });
					}
				});
			}
		</script>
	</head>
	<body style="padding: 4px; overflow: hidden;">
		<div id="content">
			<s:if test="message!=null">
				<span><s:property value="message" /> </span>
			</s:if>
			<div class="box_system">
				<form action="<s:url value="channel!addChannelMerInfo.ac"/>" id="addMerchant" method="post" name="form1" enctype="multipart/form-data" >
					<table width="80%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td>
								<input  class="l-text-field" style="width:200px" type="hidden" name="channelMerInfo.chId" type="text" id="chId"  value="<s:property value="channelMerInfo.chId"/>" />
							</td>
						</tr>
						<tr>
							<td style="text-align: right;"> 
								渠道商户名称： 
							</td>
							<td>
							<div class="l-text" style="width: 200px;">
								<input  class="l-text-field" style="width:200px" type="text" name="channelMerInfo.chName" type="text" id="chName"  value="<s:property value="channelMerInfo.chName"/>" />
							</div>
							</td>
							<td style="text-align: right;">
								渠道商户号：
							</td>
							<td>
							<div class="l-text" style="width: 200px;">
								<input type="text" class="l-text-field" style="width:200px" name="channelMerInfo.chMerId" id="chMerId"  value="<s:property value="channelMerInfo.chMerId"/>" />
							</div>
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">
								渠道终端号：
							</td>
							<td>
							<div class="l-text" style="width: 200px;">
								<input type="text" class="l-text-field" style="width:200px"
									name="channelMerInfo.chTermId" id="chTermId"  value="<s:property value="channelMerInfo.chTermId"/>" />
							</div>
							</td>
							<td style="text-align: right;">
								渠道序列号：
							</td>
							<td>
							<div class="l-text" style="width: 200px;">
								<input type="text" class="l-text-field" style="width:200px"
									name="channelMerInfo.chSeriId" id="chSeriId"  value="<s:property value="channelMerInfo.chSeriId"/>" />
									</div>
							</td>
						</tr>
						<tr>
							<td width="150px" style="text-align: right;">
								终端主密钥：
							</td>
							<td>
							<div class="l-text" style="width: 200px;">
								<input type="text" class="l-text-field" style="width:200px"
									name="channelMerInfo.chZMK" id="chZMK"  value="<s:property value="channelMerInfo.chZMK"/>" />
									</div>
							</td>
							<td width="120px" style="text-align: right;">
								渠道地址：
							</td>
							<td>
							<div class="l-text" style="width: 150px;">
								<select name="channelMerInfo.chAddId" id="chAddId"  style="width: 150px"  class="l-text-field" >
											<option value="-1">
											----请选择----
											</option>
											<s:iterator value="channelAddInfoList" var="channelAddInfo">
												<option value="<s:property value="#channelAddInfo.chAddId"/>" 
												<s:if test="#channelAddInfo.chAddId == channelMerInfo.chAddId ">selected="selected"</s:if>>
														 <s:property value="#channelAddInfo.chName"/>
												</option>
											</s:iterator>
									</select>
								</div>
							</td>
						</tr>
						<tr>
							<td width="120px" style="text-align: right;">
								渠道类型：
							</td>
						<td>
							<div class="l-text" style="width: 200px;">
							<select name="channelMerInfo.chType" id="chType"  style="width: 200px"  class="l-text-field" >
								<option value="1"  <s:if test="#channelMerInfo.chType=='1'">selected="selected"</s:if>>
									点对点
								</option>
								<option value="2" <s:if test="#channelMerInfo.chType=='2'">selected="selected"</s:if>>
									轮询
								</option>
							</select>
							</div>
							</td>
							<td width="120px" style="text-align: right;">
								交易类型：
							</td>
						<td>
							<div class="l-text" style="width: 200px;">
							<select name="channelMerInfo.rateType" id="rateType"  style="width: 200px"  class="l-text-field" >
								<option value="00"  <s:if test="#channelMerInfo.rateType=='00'">selected="selected"</s:if>>
									普通消费
								</option>
								<option value="31"  <s:if test="#channelMerInfo.rateType=='31'">selected="selected"</s:if>>
									香港(境外)
								</option>
								<option value="32"  <s:if test="#channelMerInfo.rateType=='32'">selected="selected"</s:if>>
									英国(境外)
								</option>
								<option value="33"  <s:if test="#channelMerInfo.rateType=='33'">selected="selected"</s:if>>
									美国(境外)
								</option>
								<option value="34"  <s:if test="#channelMerInfo.rateType=='34'">selected="selected"</s:if>>
									法国(境外)
								</option>
								<option value="35" <c:if test="#channelMerInfo.rateType=='35'}">selected="selected"</c:if>>
									日本(境外)
								</option>
								<option value="36" <c:if test="#channelMerInfo.rateType=='36'}">selected="selected"</c:if>>
									新西兰(境外)
								</option>
								<option value="37" <c:if test="#channelMerInfo.rateType=='37'}">selected="selected"</c:if>>
									马来西亚(境外)
								</option>
								<option value="38" <c:if test="#channelMerInfo.rateType=='38'}">selected="selected"</c:if>>
									泰国(境外)
								</option>
							</select>
							</div>
							</td>
						</tr>
						<tr>
							<td width="120px" style="text-align: right;">
								渠道费率：
							</td>
							<td>
							<div class="l-text" style="width: 200px;">
								<select name="channelMerInfo.chFeeId" id="chFeeId"  style="width: 200px"  class="l-text-field" >
											<option value="-1">
												-------------请选择--------------
											</option>
											<s:iterator value="feeList" var="channelFeeType">
											<option value="<s:property value="chFeeId"/>"
												 <s:if test="#channelFeeType.chFeeId == channelMerInfo.chFeeId  ">selected="selected"</s:if>>
														 <s:property value="feeTypeName"/>
												</option>
											</s:iterator>
									</select>
									</div>
							</td>
						</tr>
						<tr>
							<td style="text-align: right;"> 
								T0手续费： 
							</td>
							<td>
							<div class="l-text" style="width: 200px;">
								<input  class="l-text-field" style="width:200px" type="text" name="channelMerInfo.t0Fee" type="text" id="t0Fee"  value="<s:property value="channelMerInfo.t0Fee"/>" />元
							</div>
							</td>
							<td style="text-align: right;">
								T0费率：
							</td>
							<td>
							<div class="l-text" style="width: 200px;">
								<input type="text" class="l-text-field" style="width:200px" name="channelMerInfo.t0Rate" id="t0Rate"  value="<s:property value="channelMerInfo.t0Rate"/>" />
							</div>
							</td>
						</tr>
						<tr>
							<td style="text-align:center" colspan="4">
								<input type="button" value="修改" id="btn" onclick="updateMer()" style="width:80px; "/>
							</td>
						</tr>
					</table>
				</form>
				</div>
			</div>
	</body>
</html>
