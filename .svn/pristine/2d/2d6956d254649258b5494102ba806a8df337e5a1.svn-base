<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>清分信息</title>
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
		<script src="<s:url value='/js/common.js'/>" type="text/javascript"></script>
		<style type="text/css">
			tr{
				height:30px;
			}
		</style>
		<script type="text/javascript">
  			var groupicon = "<s:url value='/js/jqui/ligerUI/skins/icons/communication.gif'/>";
		    CustomersData = "{Rows:"+"<s:property value='merSettleStatictisListJson'/>".replace(/&quot;/g,"\"")+",Total:91}";
		    var aa = eval('('+CustomersData+')'); 
		    //扩展currency类型的对比函数 
	      $.ligerDefaults.Grid.sorters['currency'] = function (val1, val2)
		    {
		        return parseFloat(val1) < parseFloat(val2) ? -1 : parseFloat(val1) > parseFloat(val2) ? 1 : 0;
		    };
        $(function (){
            $("#maingrid4").ligerGrid({
                columns: [   
             	{ display: '机构名称(机构号)', name: 'merName', align: 'center', width: '15%',render:function(rowdata,rowindex){
	            	return rowdata.merName+"("+rowdata.merSysId+")";
	            } },
             	{ display: '清算日期', name: 'settleDate', align: 'left', width: '8%' ,align: 'center' ,render:function(rowdata,rowindex){
             		if(rowdata.settleDate!=null)
	            	return rowdata.settleDate.substring(0,4)+"-"+rowdata.settleDate.substring(4,6)+"-"+rowdata.settleDate.substring(6);
	            	return '';
	            } },
	            { display: '总销售额', name: 'sumAmt', align: 'left', width: '6%' ,align: 'center' ,type:'currency'},
              	{ display: '交易手续费', name: 'sumTransFee', align: 'left', width: '6%' ,align: 'center' ,type:'currency'},
	            { display: '清分金额', name: 'clearAmt', align: 'left', width: '6%' ,align: 'center' ,type:'currency'},
	            { display: '清分失败金额', name: 'faileAmt', align: 'left', width: '6%' ,align: 'center' ,type:'currency'},
	            { display: 'T0提现金额', name: 't0Amt', align: 'left', width: '6%' ,align: 'center' ,type:'currency'},
	           	{ display: 'T0手续费', name: 'reserved', align: 'left', width: '6%' ,align: 'center' ,type:'currency'},
	            { display: 'T0分润', name: 't0MerGain', align: 'left', width: '6%' ,align: 'center' ,type:'currency'},
	            { display: 'T0瑞银分润', name: 't0HfProfit', align: 'left', width: '6%' ,align: 'center' ,type:'currency'},
<%--	            { display: 'D1手续费', name: 'countFee', align: 'left', width: '6%' ,align: 'center',type:'currency'},--%>
<%--	            { display: 'D1分润', name: 'd1MerGain', align: 'left', width: '6%' ,align: 'center',type:'currency'},--%>
<%--	            { display: 'D1瑞银分润', name: 'd1HfProfit', align: 'left', width: '6%' ,align: 'center' ,type:'currency'},--%>
	            { display: '清分状态', name: 'clearStatus', width: '5%', align: 'center',render:function(rowdata,rowindex){
	            	if(rowdata.clearStatus=='0'){return '未清分';}
	            	else if(rowdata.clearStatus=='1'){return '待清分';}
	            	else if(rowdata.clearStatus=='2'){return '清分成功';}
	            	else if(rowdata.clearStatus=='6'){return '部分处理成功';}
	            	else if(rowdata.clearStatus=='3'){return '清分失败';}
	            	else if(rowdata.clearStatus=='4'){return '清分给机构';}
	            	else if(rowdata.clearStatus=='5'){return '财务上账';}
	            	else if(rowdata.clearStatus=='9'){return '处理中';}
	            } },     
		 		{ display: '操作', name: 'null', width: '20%', align: 'center' ,render: function (rowdata, rowindex, value)
           	 	{
                     var h = "";
                  if (!rowdata._editing)
                    {
                    	 <c:if test="${purview['4011'] == '4011'}">
                    		h += "<a onclick='downloadReport("+rowdata.merSysId+",0)' style='cursor:pointer;color:blue;'>报表下载</a> ";
                    	 </c:if>
                    	 <c:if test="${purview['4012'] == '4012'}">
                    	 	h += "<a onclick='downloadReport("+rowdata.merSysId+",1)' style='cursor:pointer;color:blue;'>订单明细下载</a> ";
                    	 </c:if>
                    	 <c:if test="${purview['4013'] == '4013'}">
						 	h += "<a onclick='downloadIssuingFile("+rowdata.merSysId+")' style='cursor:pointer;color:blue;'>代发文件下载</a> ";
						 </c:if>
                    	 <c:if test="${purview['4014'] == '4014'}">
                   	 	 	h += "<a onclick='f_open1("+rowdata.id+","+rowdata.settleDate+","+rowdata.clearStatus+","+rowdata.merSysId+")' style='cursor:pointer;color:blue;'>明细</a> "; 
						 </c:if>
                    }
                    return h;
                }}
                ], 
                totalRender: f_totalRender,
                data: $.extend(true,{},aa),
                rownumbers:true,
                width: '100%', 
                height: '100%', 
                pageSize: 20
                
            });  
            $('#maingrid4').ligerGrid().set('dataAction','server');
            $("#pageloading").hide();
             $("#settleDate").ligerDateEditor({ showTime: true,format: "yyyy-MM-dd"});
        });
        function resetSettle(merSysId,settleDate){
        	if(merSysId == null || merSysId == ''){
        		alert("出错！！");
        		return;
        	}
        	if(settleDate == null || settleDate == ''){
        		alert("出错！！");
        		return;
        	}
        	if(confirm("确认重新清算？"))
        	var url = "<s:url value="/MerSettleStatictis!resetSettle.ac"/>";
        	 $.post(url,
        	 {'merSettleStatictis.merSysId':merSysId,'merSettleStatictis.settleDate':settleDate},
        	 function(data){
        	 	alert(data);
	        });
        	
        }
        
        function downloadIssuingFile(id){
	        	var settleDate2 = $("#settleDate").val();
				location.href="<s:url value='/MerSettleStatictis!downloadIssuingFile.ac'/>?merSettleStatictis.merSysId="+id+"&settleDate2="+settleDate2
			}
        function f_totalRender(data){
        	return "<table align='center' border='2' width='80%'>"
        				+"<tr>"
        					+"<td style='text-align: right;width: 5%;'>交易总金额：</td><td style='text-align: center;width: 3%;'>"+$("#sumAmt").val()+"</td>"
				        	+"<td style='text-align: right;width: 5%;'>清分总金额：</td><td style='text-align: center;width: 3%;'>"+$("#clearAmt").val()+"</td>"
				        	+"<td style='text-align: right;width: 5%;'>手续费：</td><td style='text-align: center;width: 3%;'>"+$("#transFee").val()+"</td>"
				        	+"<td style='text-align: right;width: 5%;'>未清分：</td><td style='text-align: center;width: 3%;'>"+$("#wqfAmt").val()+"</td>"
				        	+"<td style='text-align: right;width: 5%;'>清分成功：</td><td style='text-align: center;width: 3;;'>"+$("#clearSuccAmt").val()+"</td>"
				        	+"<td style='text-align: right;width: 5%;'>清分失败金额：</td><td style='text-align: center;width: 3%;'>"+$("#faileAmt").val()+"</td>"
        				+"</tr>"
						+"<tr>"
				        	+"<td style='text-align: right;'>T0成功金额：</td><td style='text-align: center;width: 3%;'>"+$("#t0SuccSumAmt").val()+"</td>"
				        	+"<td style='text-align: right;'>T0成功手续费：</td><td style='text-align: center;width: 3%;'>"+$("#t0SuccTransFee").val()+"</td>"
				        	+"<td style='text-align: right;'>T0失败金额：</td><td style='text-align: center;width: 3%;'>"+$("#t0ErrorSumAmt").val()+"</td>"
				        	+"<td style='text-align: right;'>T0失败手续费：</td><td style='text-align: center;width: 3%;'>"+$("#t0ErrorTransFee").val()+"</td>"
				        	//+"<td style='text-align: right;'>D1手续费：</td><td style='text-align: center;width: 3%;'>"+$("#d1TransFee").val()+"</td>"
				        	+"<td style='text-align: right;'></td><td style='text-align: center;width: 3%;'>"+"</td>"
				        	+"<td style='text-align: right;'></td><td style='text-align: center;width: 3%;'>"+"</td>"
        				+"</tr>"
					+"</table>";
        	
        }
        
         function f_search()
        {
        	var url = '<s:url value="/MerSettleStatictis!merSettleStatictisList.ac"/>?merSettleStatictis.merSysId='+$("#merSysId").val()+
        	'&settleDate2='+$("#settleDate").val()+'&merSettleStatictis.clearStatus='+$("#clearStatus").val();
        		var merSysId=$("#merSysId").val();  
					var pattern = /^[\S]*$/; //不包含空格
			    	var number = /^[0-9]{1,20}$/;	//数字	
					if(merSysId!=""&&!number.test(merSysId)) {
						alert("机构商户号只能是数字！");
						$("#merSysId").focus();
						return false;
					}else{
						form1.action = url;
						$("#form1").attr("target", '_self');
					  	$("#form1").submit();
					}
        }
        
        
        function f_clean(){
        	$("#merSysId").val("");
        	$("#settleDate").val("");
        	
        }
        
        function downloadReport(id,merType){
       		var settleDate2 = $("#settleDate").val();
      			var url="<s:url value="/MerSettleStatictis!checkFile.ac" />";
      			 $.post(url,{'merSettleStatictis.merSysId':id,settleDate2:settleDate2,merType:merType},function(data){
       			if(data=='fone'){
       				if(merType == 0){
       					alert("无此交易结算报表");
       				}else{
       					alert("无此交易订单明细报表");
       				}
       			}else{
       				window.location.href="<s:url value='/MerSettleStatictis!downloadFile.ac'/>?merSettleStatictis.merSysId="+id+"&settleDate2="+settleDate2+"&merType="+merType;
       			}
	        });
		}
			
		function exports(){
			var count = $("#count").val();
			if(count<=0){
				alert("没数据！");
				return false;
			}
			$("#form1").attr("action", '<s:url value="/MerSettleStatictis!merSettleStatictisExport.ac" />?settleDate2='+$("#settleDate").val());
			 $("#form1").attr("target", '_blank');
			 $( "#form1").submit();
		}
		 function f_open1(id,settleDate,clearStatus,merSysId)
        {	
            $.ligerDialog.open({ url: '<s:url value="/MerSettleStatictis!getMerchantList.ac"/>?merSettleStatictis.merSysId='+merSysId+
                '&merSettleStatictis.settleDate='+settleDate+
                '&merSettleStatictis.id='+id,
            					 height:550,width:1100, isResize: false,title:'子商户清分列表'});  
        }
        
        
    </script>
	</head>
	<body style="padding: 4px; ">
	<%@include file="/js/common.jsp" %>
		<div id="searchbar">
		<input type="hidden" id="sumAmt" value="<s:property value="sumAmt"/>"/>
		<input type="hidden" id="clearAmt" value="<s:property value="clearAmt"/>"/>
		<input type="hidden" id="transFee" value="<s:property value="transFee"/>"/>
		<input type="hidden" id="wqfAmt" value="<s:property value="wqfAmt"/>"/>
		<input type="hidden" id="clearSuccAmt" value="<s:property value="clearSuccAmt"/>"/>
		<input type="hidden" id="faileAmt" value="<s:property value="faileAmt"/>"/>
		<input type="hidden" id="d1TransFee" value="<s:property value="d1TransFee"/>"/>
		<input type="hidden" id="t0SuccSumAmt" value="<s:property value="t0SuccSumAmt"/>"/>
		<input type="hidden" id="t0SuccTransFee" value="<s:property value="t0SuccTransFee"/>"/>
		<input type="hidden" id="t0ErrorSumAmt" value="<s:property value="t0ErrorSumAmt"/>"/>
		<input type="hidden" id="t0ErrorTransFee" value="<s:property value="t0ErrorTransFee"/>"/>
		<input type="hidden" id="count" value="<s:property value="count"/>"/>
			<form id="form1" action="<s:url value="/pages/theReport/settleStatictisList.jsp" />" method="post">
				<table width="80%">
					<tr>
						<td style="text-align: right;">
							机构商户号：
						</td>
						<td style="text-align: left;">
							<input type="text" name="merSysId" id="merSysId" value="${param.merSysId}" />
						</td>
						<td style="text-align: right;">
							清算日期：
						</td>
						<td style="text-align: left;" align="right">
							<input type="text" name="settleDate" id="settleDate" 
							<s:if test = "settleDate2 != null && settleDate2 != ''">
								value="<%=request.getAttribute("settleDate2") %>"
							</s:if>
							<s:else>
								value="${param.settleDate}"
							</s:else> />
						</td>
						<td style="text-align: right;">
							清算状态：
						</td>
						<td style="text-align: left;" align="right">
							<select id="clearStatus" name="clearStatus">
								<option value="-1" <c:if test="${param.clearStatus == -1}">selected</c:if>>请选择</option>
								<option value="0" <c:if test="${param.clearStatus == 0}">selected</c:if>>未清分</option>
								<option value="1" <c:if test="${param.clearStatus == 1}">selected</c:if>>待清分</option>
								<option value="2" <c:if test="${param.clearStatus == 2}">selected</c:if>>已清分</option>
								<option value="3" <c:if test="${param.clearStatus == 3}">selected</c:if>>清分失败</option>
								<option value="4" <c:if test="${param.clearStatus == 4}">selected</c:if>>清分给机构</option>
								<option value="5" <c:if test="${param.clearStatus == 5}">selected</c:if>>财务打款</option>
								<option value="6" <c:if test="${param.clearStatus == 6}">selected</c:if>>部分处理成功</option>
								<option value="9" <c:if test="${param.clearStatus == 9}">selected</c:if>>处理中</option>
							</select>
						</td>
						<td style="text-align: left;"><input id="btn" type="button" value="查 询" onclick="f_search()" class="l-button"/></td>
						<td style="text-align: left;"><input id="btn" type="button" value="重 置" onclick="f_clean()" class="l-button"/></td>
						<td style="text-align: left;"><input id="btn" type="button" value="导 出" onclick="exports()" class="l-button"/></td>
					</tr>
				</table>
			</form>
		</div>
		<div class="l-loading" style="display: block" id="pageloading">
		</div>
		<div id="maingrid4" style="margin: 0; padding: 0">
		</div>
		<div style="display: none;">

		</div>
	</body>
</html>
