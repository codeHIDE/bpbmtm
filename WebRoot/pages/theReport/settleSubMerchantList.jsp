<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title></title>
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
		<script src="<s:url value='/js/jqui/CustomersData.js'/>" type="text/javascript"></script>
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
		/*
		totalSummary:   
				       {  
				          align: 'left', //汇总单元格内容对齐方式:left/center/right   
				          type: 'sum', //汇总类型sum,max,min,avg ,count。可以同时多种类型   
				          render: function (e)   
				          {   
				              //汇总渲染器，返回html加载到单元格   
				              //e 汇总Object(包括sum,max,min,avg,count)   
				    //          return "合计：" + (e.sum.substring(1, 10));  
				              return "合计：" + (e.sum.toFixed(2))/100;  
				          }   
				       }
		*/
		var groupicon = "<s:url value='/js/jqui/ligerUI/skins/icons/communication.gif'/>";
		    CustomersData = "{Rows:"+"<s:property value='merSettleStatictisListJson'/>".replace(/&quot;/g,"\"")+",Total:91}";
		     
		    var aa = eval('('+CustomersData+')'); 
		
  		var grid = null;
        $(function (){
            window['g'] = $("#maingrid4").ligerGrid({
                 columns: [
	            { display: '子商户入网号', name: 'mid', align: 'left', width: '18%' ,align: 'center' },
	           	{ display: '子商户名称', name: 'merName', width: '11%', align: 'center' },
	           	{ display: '清分周期', name: 'billCycle', width: '11%', align: 'center' ,render:function(rowdata,rowindex){
	           		if(rowdata.billCycle != null){
		            	if(rowdata.billCycle=='00'){return '停止清算';}
		            	else if(rowdata.billCycle=='01'){return 'T+1';}
		            	/* else if(rowdata.billCycle=='02'){return 'D+1';} */
		            	}else{
		            	return "";}
	           	}},
	           	{ display: '清分状态', name: 'clearStatus', width: '10%', align: 'center',render:function(rowdata,rowindex){
	            	if(rowdata.clearStatus=='0'){return '未清分';}
	            	else if(rowdata.clearStatus=='1'){return '待清分';}
	            	else if(rowdata.clearStatus=='2'){return '清分成功';}
	            	else if(rowdata.clearStatus=='6'){return '部分处理成功';}
	            	else if(rowdata.clearStatus=='3'){return '清分失败';}
	            	else if(rowdata.clearStatus=='4'){return '清分给机构';}
	            	else if(rowdata.clearStatus=='5'){return '财务上账';}
	            	else if(rowdata.clearStatus=='9'){return '处理中';}
	            } },       
	            { display: '清算日期', name: 'settleDate', width: '11%', align: 'center' },
	            { display: '总销售额', name: 'sumAmt', width: '11%', align: 'center',type:'currency'},
	            { display: '交易手续费', name: 'sumTransFee', width: '11%', align: 'center' ,type:'currency'},
	            { display: '清分金额', name: 'clearAmt', width: '11%', align: 'center' ,type:'currency'}, 
                ],
                data: $.extend(true,{},aa),
                rownumbers:true,
                width: '100%', 
                height: '100%', 
                pageSize: 30
            });  
            $('#maingrid4').ligerGrid().set('dataAction','server');
            $("#pageloading").hide();
        });
          function sttle(){
	          if(confirm("确认清分？")){
					xf();
					$.post("<s:url value="/clearing!clearingTrue.ac"/>",
					{settleDate:$("#settleDate").val(),merSysId:$("#merSysId").val()},
					 function(data){
	       				alert(data);
	        			c();
						var clearStatus = window.parent.clearStatus.value;
						var merSysId = window.parent.merSysId.value;
	        			var settleDate = window.parent.settleDate.value;
	        			window.parent.form1.action = "<s:url value="/MerSettleStatictis!merSettleStatictisList.ac"/>?settleDate2="+settleDate+
	        			"&merSettleStatictis.merSysId="+merSysId+"&merSettleStatictis.clearStatus="+clearStatus;
	        			window.parent.form1.submit();
						window.parent.$.ligerDialog.close();
						window.parent.$(".l-dialog,.l-window-mask").remove();
			        });
				}
			}
			function sub(){
				var url = '<s:url value="/MerSettleStatictis!getMerchantList.ac"/>?merSettleStatictis.merSysId='+$("#merSysId").val()+
                '&merSettleStatictis.settleDate='+$("#settleDate").val()+'&merSettleStatictis.id='+$("#id").val()+
                '&clearStatus='+$("#clearStatus").val()+'&mid='+$("#mid").val();
				form1.action= url;
				form1.submit();
			}
			function submit(){
				form1.action="/clearing!clearingTrue.ac";
				form1.submit();
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
			<form id="form1" action="<s:url value="/pages/theReport/settleSubMerchantList.jsp"/>"  method="post">
				<table width="100%" border="0">
					<tr>
						<td>商户总数：<s:property value="count"/></td>
						<td>交易总金额：<s:property value="merAmt"/></td>
						<td>交易总手续费：<s:property value="transFee"/></td>
						<td>清算总额：<s:property value="clearAmt"/></td>
						<td>子商户号：<input id="mid" value="<s:property value="mid"/>" onchange="sub()"/></td>
						<td style="text-align:center">清分状态：<select id="clearStatus" onchange="sub()">
								<option value="-1" >全部</option>
								<option value="0" <s:if test="clearStatus==0">selected</s:if>>未清分</option>
								<option value="1" <s:if test="clearStatus==1">selected</s:if>>待清分</option>
								<option value="2" <s:if test="clearStatus==2">selected</s:if>>清分成功</option>
								<option value="3" <s:if test="clearStatus==3">selected</s:if>>清分失败</option>
								<option value="6" <s:if test="clearStatus==6">selected</s:if>>部分成功</option>	
								<option value="9" <s:if test="clearStatus==9">selected</s:if>>处理中</option>								
							</select></td>
						<td style="">
						<input type="hidden" name="settleDate" id="settleDate" value="<s:property value="merSettleStatictis.settleDate"/>"/>
						<input type="hidden" name="merSysId" id="merSysId" value="<s:property value="merSettleStatictis.merSysId"/>"/>
						<input type="hidden" name="id" id="id" value="<s:property value="merSettleStatictis.id"/>"/>
						<!-- <div style="float:left;" id="aa"></div>
						<c:if test="${purview['4014'] == '4014'}">
							<s:if test="merSettleStatictis.clearStatus == 0 ">
								请先上账，后清分。。。。
							</s:if>
							<s:if test="merSettleStatictis.clearStatus == 1">
								<input id="btn" type="button" value="确认清分" onclick="sttle()" class="l-button" />
							</s:if>
							</c:if> -->
						</td>
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
}
</script>
	</body>
	
</html>
