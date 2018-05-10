<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>T+0结算明细</title>
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
		<style> 
* {margin:0;padding:0;font-size:12px;} 
html,body {height:100%;width:100%;} 
#content {background:#f8f8f8;padding:30px;height:100%;} 
#content a {font-size:30px;color:#369;font-weight:700;} 
#alert {border:1px solid #369;width:300px;height:150px;background:#e2ecf5;z-index:1000;position:absolute;display:none;} 
#alert h4 {height:20px;background:#369;color:#fff;padding:5px 0 0 5px;} 
#alert h4 span {float:left;} 
#alert h4 span#close {margin-left:210px;font-weight:500;cursor:pointer;} 
#alert p {padding:12px 0 0 30px;} 
#alert p input {width:120px;margin-left:20px;} 
#alert p input.myinp {border:1px solid #ccc;height:16px;} 
#alert p input.sub {width:60px;margin-left:30px;} 
</style>
		<script type="text/javascript">
  
        $(function (){
           grid = $("#maingrid4").ligerGrid({
                columns: [
	            { display: 'ID', name: 'id', align: 'left', width: '9%' ,align: 'center' },
	           	{ display: '商户号', name: 'subMerId', width: '15%', align: 'center' },
	           	{ display: '机构商户号', name: 'merSysId', width: '15%', align: 'center'},       
	            { display: '提现金额(元)', name: 'orderAmt', width: '9%', align: 'center',type:'currency'},
	            { display: '提现状态', name: 'orderStatus', width: '9%', align: 'center',render:function(rowdata,rowindex){
	            	if(rowdata.orderStatus=='0'){return '申请';}
	            	else if(rowdata.orderStatus=='1'){return '生成代发文件等待代发';}
	            	else if(rowdata.orderStatus=='2'){return '代发成功';}
	            	else if(rowdata.orderStatus=='3'){return '当日代发失败';}
	            	else if(rowdata.orderStatus=='4'){return '次日代发失败';}
	            	else if(rowdata.orderStatus=='5'){return '已T+1清分';}
	            	else if(rowdata.orderStatus=='9'){return '代发处理中';}
	            }},
	            { display: '申请时间', name: 'createTime', width: '9%', align: 'center'},
	            { display: '完成时间', name: 'finishTime', width: '9%', align: 'center'}, 
	            /* { display: '提现订单号', name: 'transId', width: '9%', align: 'center'}, */
	            /* { display: '提现流水号', name: 'transQid', width: '9%', align: 'center'}, */
	            { display: '手续费(元)', name: 'transFee', width: '9%', align: 'center' ,type:'currency'},
	            { display: '批次号', name: 'batchId', width: '9%', align: 'center'},
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
                url:'<s:url value="/MerSettleStatictis!getTodayStatisticsDetailData.ac"/>?batchId='+$("#batchId").val()
                
            });  
            $('#maingrid4').ligerGrid().set('dataAction','server');
            $("#pageloading").hide();
        });
		function search(){
			grid.setParm("subMerId", $("#subMerId").val());             
			grid.loadData(getRootPath() + '/MerSettleStatictis!getTodayStatisticsDetailData.ac');
		}
		function resetParam(){
			$("#subMerId").val("");
		}
    </script>
	</head>
	<body style="padding: 4px; overflow: hidden;">
	<div id="alert">
<h4><span>提示</span></h4>
<p>
  	清分中，请稍后。。。。
</p>
</div>
	<%@include file="/js/common.jsp" %>
		<div id="searchbar">
			<input type="hidden" name="batchId" id="batchId" value="${batchId }"/>
			商户号:
			<input type="text" name="subMerId" id="subMerId" />
			<input type="button" value="查询" class="l-button" onclick="search()"/>
			<input type="reset" value="重置" class="l-button" onclick="resetParam()"/>
		</div>
		<div class="l-loading" style="display: block" id="pageloading">
		</div>
		<div id="maingrid4" style="margin: 0; padding: 0">
		</div>
		<div style="display: none;">

		</div>
		<script type="text/javascript"> 
var myAlert = document.getElementById("alert"); 
var reg = $("#xf"); 

function xf() 
{ 
	myAlert.style.display = "block"; 
	myAlert.style.position = "absolute"; 
	myAlert.style.top = "50%"; 
	myAlert.style.left = "50%"; 
	myAlert.style.marginTop = "-75px"; 
	myAlert.style.marginLeft = "-150px";
	mybg = document.createElement("div"); 
	mybg.setAttribute("id","mybg"); 
	mybg.style.background = "#000"; 
	mybg.style.width = "100%"; 
	mybg.style.height = "100%"; 
	mybg.style.position = "absolute"; 
	mybg.style.top = "0"; 
	mybg.style.left = "0"; 
	mybg.style.zIndex = "500"; 
	mybg.style.opacity = "0.3"; 
	mybg.style.filter = "Alpha(opacity=30)"; 
	document.body.appendChild(mybg);
	document.body.style.overflow = "hidden"; 
}

function c() {
	myAlert.style.display = "none"; 
    mybg.style.display = "none"; 
};
</script>
	</body>
	
</html>
