<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>系统管理平台-系统日分润统计</title>
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
		var grid;
        $(function (){
           grid= $("#maingrid4").ligerGrid({
            columns: [
        	 { display: '清算日期', name: 'settleDate', align: 'center', width: '10%',align: 'center' },
             { display: '总消费金额', name: 'sumAmt', align: 'center', width: '10%', type:'currency'},
             { display: '总消费手续费', name: 'sumFeeAmt', width: '10%', align: 'center', type:'currency'},
             { display: '总消费订单笔数', name: 'sumCount', width: '10%', align: 'center'},
             { display: '总撤销金额', name: 'sumCamt', width: '10%', align: 'center',type:'currency'},
             { display: '总撤销手续费', name: 'sumFeeCamt', width: '10%', align: 'center',type:'currency'},
             { display: '总撤销订单笔数', name: 'sumCCount', width: '10%', align: 'center'},
             { display: '总退款金额', name: 'sumRamt', width: '10%', align: 'center',type:'currency'},
             { display: '总退款手续费', name: 'sumFeeRamt', width: '10%', align: 'center',type:'currency'},
             { display: '总退款订单笔数', name: 'sumRCount', width: '10%', align: 'center'},
             { display: '创建时间', name: 'createTime',width: '10%', align: 'center' },
             { display: '总通道手续费', name: 'sumTractFee', width: '10%', align: 'center', type:'currency'}, 
             { display: 'Bypay分润', name: 'bypayProfit', width: '10%', align: 'center',type:'currency'}
                ],  
                totalRender:f_totalRender,
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
                parms:{
                	startTime:$("#startTime").val(),
                	endTime:$("#endTime").val()
                },
                url:'<s:url value="/statistics!byPayProfitStatictis.ac"/>'
            });
            $("#pageloading").hide();
            $('#maingrid4').ligerGrid().set('dataAction','server');
            $("#startTime").ligerDateEditor({ showTime: true, format: "yyyy-MM-dd"});
            $("#endTime").ligerDateEditor({ showTime: true, format: "yyyy-MM-dd"});
            
        });
        
        function f_totalRender(data)
		{
			return "<table align='center' border='2' width='80%'>"
					+"<tr>"
					+"<td style='text-align: right;'>总消费金额合计：</td><td style='text-align: center;width: 20%;'>"+data.sumAmtSum+"</td>"
					+"<td style='text-align: right;'>总撤销金额合计：</td><td style='text-align: center;width: 10%;'>"+data.sumCamtSum+"</td>"
					+"<td style='text-align: right;'>总退款金额合计：</td><td style='text-align: center;width: 10%;'>"+data.sumRamtSum+"</td>"
					+"<td style='text-align: right;'>分润合计：</td><td style='text-align: center;width: 10%;'>"+data.bypayProfitSum+"</td>"
					+"</tr>"
					+"<tr>"
					+"<td style='text-align: right;'>总消费手续费合计：</td><td style='text-align: center;'>"+data.sumFeeAmtSum+"</td>"
					+"<td style='text-align: right;'>总撤销手续费合计：</td><td style='text-align: center;'>"+data.sumFeeCamtSum+"</td>"
					+"<td style='text-align: right;'>总退款手续费合计：</td><td style='text-align: center;'>"+data.sumFeeRamtSum+"</td>"
					+"<td style='text-align: right;'>总通道手续费合计：</td><td style='text-align: center;'>"+data.sumTractFeeSum+"</td>"
					+"</tr>"
					+"</table>";
		}
        function checkInfo() {
			  var startTime = $("#startTime").val();
			  var endTime = $("#endTime").val();
			  if(startTime == "" && endTime == "") {
					$("#startTime").val("");
					$("#startTime").focus();
					return false;
			  }
			  if(startTime != "" && endTime == "") {
					alert("请输入结束时间！");
					$("#endTime").val("");
					$("#endTime").focus();
					return false;
			  }
			  if(endTime != "" && startTime == "") {
					alert("请输入开始时间！");
					$("#startTime").val("");
					$("#startTime").focus();
					return false;
			  }
        	return true;
        }
        function f_search()
        {
        	 var res = checkInfo();
        	 if(res == true){
				 $("#form1").attr("target", '_self');
			     $("#form1").attr("action", '<s:url value="/pages/statistics/bypayProfitStatistics.jsp"/>');
				 $("#form1").submit();        	 
        	 }
        }
    </script>
	</head>
	<body style="padding: 4px;">
	<%@include file="/js/common.jsp" %>
		<div id="searchbar">
		<form id="form1" action="<s:url value="/pages/statistics/bypayProfitStatistics.jsp" />" method="post">
				<table width="80%" border="0">
					<thead></thead>
					<tr>
						<td style="text-align: right;">
							清算日期：
						</td>
						<td style="text-align: left;"> 
							<div style="width: 130px;float: left;"> 
								<input type="text" name="startTime" id="startTime"  value="${param.startTime}"/>
							</div>
							<span style="float: left;">&nbsp;&nbsp;-&nbsp;&nbsp;</span>
							<div style="width: 130px;float: left;">  
								<input type="text" name="endTime" id="endTime"  value="${param.endTime}" />
							</div>
						</td>
						<td style="text-align: right;">
							<input id="btn" type="button" value="查 询" onclick="f_search()" class="l-button"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div class="l-loading" style="display: block" id="pageloading">
		</div>
		<div id="maingrid4" style="margin: 0; padding: 0;margin-top: 10px"></div>
		<div style="display: none;">
		</div>
	</body>
</html>
