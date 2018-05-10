<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>机构商户查询</title>
		<link href="<s:url value='/js/jqui/ligerUI/skins/Aqua/css/ligerui-all.css'/>" rel="stylesheet" type="text/css" />
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
		<script src="<s:url value='/js/common.js'/>"></script>
		<style type="text/css">
			tr{
				height:30px;
			}
		</style>
		<script type="text/javascript">
       /* $(function () {
            $("#maingrid4").ligerGrid({
            columns: [
           
             { display: '清算日期', name: 'null', align: 'center', width: '15%',render:function(rowdata,rowindex){
	            	 return (rowdata.settleDate).substring(0,8);
	            } },
	         { display: '清算时间', name: 'null', align: 'center', width: '15%',render:function(rowdata,rowindex){
	            	 return (rowdata.settleDate).substring(8,16);
	            } },
	        { display: '结算日期', name: 'null', align: 'center', width: '15%',render:function(rowdata,rowindex){
	            	return (rowdata.reserved1).substring(0,8);
	            } },
	         { display: '结算时间', name: 'null', align: 'center', width: '15%',render:function(rowdata,rowindex){
	            	return (rowdata.reserved1).substring(8,16);
	            } },
	         { display: '商户号', name: 'subMerId', align: 'center', width: '15%'},
	         { display: '代理商号', name: 'merSysId', align: 'center', width: '15%'},
	         { display: '结算流水号', name: 'id', align: 'center', width: '15%'},
	         { display: '结算金额', name: 'clearAmt', align: 'center', width: '15%'},
	         { display: '结算类型', name: 'settleType', align: 'center', width: '15%',render:function(rowdata,rowindex){
	         	var sett = null;
	         	if(rowdata.settleType == '01'){
	         		sett = "T0"
	         	}else if(rowdata.settleType == '02'){
	         		sett = "D1"
	         	}else if(rowdata.settleType == '-1'){
	         		sett = "T1"
	         	}
	            	return sett;
	            }},
            { display: '结算状态', name: 'status', width: '12%', align: 'center',render:function(rowdata,rowindex)
            	{
            	if(rowdata.clearStatus==0){return '0';}
            	else if(rowdata.status==1){return '1';}
            	else if(rowdata.status==2){return '2';}
            	else if(rowdata.status==3){return '3';}
            	else if(rowdata.status==4){return '4';}
            	else if(rowdata.status==5){return '5';}
            	else if(rowdata.status==6){return '6';}
            	else if(rowdata.status==7){return '7';}
            	else if(rowdata.status==9){return '9';}
            	else if(rowdata.status==10){return '10';}
            	else if(rowdata.status==11){return '11';}
            	else if(rowdata.status==12){return '12';}
            	else if(rowdata.status==13){return '13';}
            	else if(rowdata.status==14){return '14';}
            	else{return "-1";}
            	} 
            },  
            { display: '状态描述', name: 'status', width: '12%', align: 'center',render:function(rowdata,rowindex)
            	{
            	if(rowdata.clearStatus==0){return '未清分';}
            	else if(rowdata.status==1){return '待清分';}
            	else if(rowdata.status==2){return '已清分';}
            	else if(rowdata.status==3){return '失败';}
            	else if(rowdata.status==4){return '清分给机构';}
            	else if(rowdata.status==5){return '财务打款';}
            	else if(rowdata.status==6){return '部分成功';}
            	else if(rowdata.status==7){return '停止清算';}
            	else if(rowdata.status==9){return '处理中';}
            	else if(rowdata.status==10){return '申请';}
            	else if(rowdata.status==11){return '生成代发文件,等待代发';}
            	else if(rowdata.status==12){return '代发成功';}
            	else if(rowdata.status==13){return '代发失败';}
            	else if(rowdata.status==14){return '代发处理中';}
            	else{return "未知";}
            	} 
            },     
            { display: '银行', name: 'bankName', width: '12%', align: 'center' },
            { display: '卡号', name: 'accountName', width: '12%', align: 'center' }, 
            { display: '卡类型', name: 'null', width: '12%', align: 'center' }, 
            { display: '结算费率', name: 't0MerRate', width: '12%', align: 'center' }, 
            { display: '结算手续费', name: 'reserved', width: '12%', align: 'center' }, 
            { display: '代理商分润比', name: 'merProfitRate', width: '12%', align: 'center' }, 
            { display: '代理商分润', name: 't0MerGain', width: '12%', align: 'center' }, 
                ], 
                rownumbers:true,
                width: '100%', 
                height: '100%', 
                checkbox: false,
                pageSize: 15,
                pageSizeOptions: [15],                        
		        sortName : null,
		        sortOrder:null,      
		        root :'Rows',                       //数据源字段名
		        record:'Total',                     //数据源记录数字段名
		        pageParmName :'page',               //页索引参数名，(提交给服务器)
		        pagesizeParmName:'pagesize',        //页记录数参数名，(提交给服务器)
                dataAciton : 'server',
                url:'<s:url value="/MerSettleStatictis!selectReoprt.ac"/>?merSysId='+$("#merSysId").val()+'&settleDate1='+$("#settleDate1").val()+
                '&settleDate2='+$("#settleDate2").val()+'&settleType='+$("#settleType").val()+'&clearStatus='+$("#clearStatus").val(
            }); 
            $('#maingrid4').ligerGrid().set('dataAction','server');   
            $("#pageloading").hide();
            $("#settleDate1").ligerDateEditor({ showTime: true,format: "yyyyMMdd"});
		  	$("#settleDate2").ligerDateEditor({ showTime: true,format: "yyyyMMdd"});
        });*/
        $(function(){
        	$("#settleDate1").ligerDateEditor({ showTime: true,format: "yyyyMMddhhmmss"});
		  	$("#settleDate2").ligerDateEditor({ showTime: true,format: "yyyyMMddhhmmss"});
		  	
		  	if(${requestScope.succ=='fone'}){
				alert("没有数据");
			}
        });
         function f_search()
        {
       
        	var settleDate1 = $("#settleDate1").val();
        	var settleDate2 = $("#settleDate2").val();
        	var merSysId = $("#merSysId").val();
        	var settleType = $("#settleType").val();
        	var clearStatus = $("#clearStatus").val();
        	window.location.href="<s:url value='/MerSettleStatictis!selectReoprt.ac'/>?settleDate1="+settleDate1+"&settleDate2="+settleDate2+"&merSysId="+merSysId
       				+"&settleType="+settleType+"&clearStatus="+clearStatus;
      			/*var url="<s:url value="/MerSettleStatictis!selectReoprt.ac" />";
      			 $.post(url,{settleDate1:settleDate1,settleDate2:settleDate2,merSysId:merSysId,
      			 settleType:settleType,clearStatus:clearStatus},function(data){
       			if(data!='fone'){
       				if(merType == 0){
       					alert("无此交易结算报表");
       				}else{
       					alert("无此交易订单明细报表");
       				}
       			}else{
       				
       			}
	        });*/
        }
        
        function f_clean(){
        	$("#merSysId").val("");
        	$("#signPerson").val("");
        	$("#signDate").val("");
        	$("#settleDate1").val("");
        	$("#settleDate2").val("");
        	$("#status").val("-1");
        	$("#platMerId").val("");
        	$("#tractId").val("-1");
        }
        
        function change(value){
        	if(value == '02'){
        		$("#clearStatus").empty();
        		$("#clearStatus").append("<option value='-1'>请选择</option>");
        		$("#clearStatus").append("<option value='0'>申请</option>");
        		$("#clearStatus").append("<option value='1'>生成代发文件等待代发</option>");
        		$("#clearStatus").append("<option value='2'>代发成功</option>");
        		$("#clearStatus").append("<option value='3'>代发失败</option>");
        		$("#clearStatus").append("<option value='9'>代发处理中</option>");
        	}else if(value == '01' || value == '-1'){
        		$("#clearStatus").empty();
        		$("#clearStatus").append("<option value='-1'>请选择</option>");
        		$("#clearStatus").append("<option value='0'>未清分</option>");
        		$("#clearStatus").append("<option value='1'>待清分</option>");
        		$("#clearStatus").append("<option value='2'>已清分</option>");
        		$("#clearStatus").append("<option value='3'>失败</option>");
        		$("#clearStatus").append("<option value='4'>清分给机构</option>");
        		$("#clearStatus").append("<option value='5'>财务打款</option>");
        		$("#clearStatus").append("<option value='6'>部分成功</option>");
        		$("#clearStatus").append("<option value='7'>停止清算</option>");
        		$("#clearStatus").append("<option value='9'>处理中</option>");
        	}else{
        		$("#clearStatus").empty();
        		$("#clearStatus").append("<option value='-1'>请选择</option>");
        		$("#clearStatus").append("<option value='2'>成功</option>");
        		$("#clearStatus").append("<option value='3'>失败</option>");
        		$("#clearStatus").append("<option value='9'>处理中</option>");
       		}
        }
        
    </script>
	</head>
	<body style="padding: 4px; overflow: hidden;">
		<div id="content">
		<div class="box_system">
			<form id="form1" action="<s:url value="/pages/merchant/selectMerchantInfo.jsp"/>" method="post" >
				<table width="100%" border="0">
					<thead></thead>
					<tr>
						<td style="text-align: right;width:100px;">
							清算日期：
						</td>
						<td style="text-align: left;">
							<div style="float:left"><input type="text" name="settleDate1" id="settleDate1" value="${param.settleDate1}" class="l-text-field" />
							</div><div style="float:left">&nbsp;&nbsp;-&nbsp;&nbsp;</div>
							<div style="float:left"><input type="text" name="settleDate2" id="settleDate2" value="${param.settleDate2}" class="l-text-field" />
						</div></td>
						<td style="text-align: right;width:100px;">
							代理商号：
						</td>
						<td style="text-align: left;">
						<div class="l-text" style="width: 130px;">
							<input type="text" name="merSysId" id="merSysId" value="${param.merSysId}" />
							</div>
						</td>
					</tr>
					<tr>
						
						<td style="text-align: right;width:100px;">
							结算类型：
						</td>
						<td style="text-align: left;"><div class="l-text" style="width: 130px;">
							<select name="settleType" id="settleType" onchange="change(this.value)" class="l-text-field" style="width: 130px;">
								<option value="-2">
									--请选择--
								</option>
								<option value="02">
									T0
								</option>
								<option value="-1">
									T1
								</option>
								<option value="01">
									D1
								</option>
							</select>
							</div>
						</td>
						<td style="text-align: right;width:100px;">
							清算状态：
						</td>
						<td><div class="l-text" style="width: 130px;">
							<select name="clearStatus" id="clearStatus"  class="l-text-field" style="width: 130px;">
								<option value="-1">
									--请选择--
								</option>
								<option value="2" <c:if test="${param.clearStatus==2}">selected="selected"</c:if>>
									成功
								</option>
								<option value="3" <c:if test="${param.clearStatus==3}">selected="selected"</c:if>>
									失败
								</option>
								<option value="9" <c:if test="${param.clearStatus==5}">selected="selected"</c:if>>
									处理中
								</option>
							</select></div>
							</td>
							<td style="text-align: left;"><input id="btn" type="button" value="导出" onclick="f_search()" class="l-button"/></td>
							<td style="text-align: left;"></td>
					</tr>
				</table>
			</form>
		</div>
		</div>
	</body>
</html>
