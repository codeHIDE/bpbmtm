<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>添加终端生成批从统计</title>
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
		<script src="<s:url value='/js/jqui/CustomersData.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerDateEditor.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/common.js'/>"></script>
		<style type="">
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
    	$(function(){
            $("#maingrid4").ligerGrid({
                columns: [
            { display: '批次号', name: 'batchId', align: 'left', width: '5%' ,align: 'center' },
            { display: '生成时间', name: 'createTime', width: '10%', align: 'center' },
            { display: '生成数量', name: 'createNum', width: '5%', align: 'center' }, 
 			{ display: '机构号', name: 'merSysId', width: '10%', align: 'center' }, 
            { display: '厂商编号', name: 'factoryId', width: '8%', align: 'center' }, 
            { display: '状态', name: 'status', width: '5%', align: 'center',render:function(rowdata,rowindex){
            	if(rowdata.status=='0'){return '未操作';}
            	else if(rowdata.status=='1'){return '请求成功';}
            	else if(rowdata.status=='2'){return '请求失败';}
            	else if(rowdata.status=='3'){return '已导入';}
            } },
            { display: '生成文件名', name: 'fileName', width: '8%', align: 'center'},
              { display: '保留域1', name: 'reserved1', width: '8%', align: 'center' ,render:function(rowdata,rowindex){
            	if(rowdata.reserved1=='04'){return 'APP';}
            	else if(rowdata.reserved1=='08'){return 'POS';}
            } }, 
              { display: '保留域2', name: 'reserved2', width: '10%', align: 'center' }, 
              { display: '保留域3', name: 'reserved3', width: '10%', align: 'center' },
             { display: '操作', name: 'null', width: '22%', align: 'center' ,render: function (rowdata, rowindex, value)
           	 	{
                    var h = "";
                    if (!rowdata._editing)
                    {
                    	<c:if test="${purview['6051'] == '6051'}">
	                    	if(rowdata.status=='1'){
	                    		h += "<a onclick='f_open3("+rowdata.batchId+")' style='cursor:pointer;color:blue;'>生成终端</a> ";
	                    	}
                    	</c:if>
                    	 <c:if test="${purview['6052'] == '6052'}">
                    	 	if(rowdata.status=='1'||rowdata.status=='3'){
                    	 		h += "<a onclick='f_open4("+rowdata.batchId+")' style='cursor:pointer;color:blue;'>下载终端文件</a> ";
	                    	}
                    	 </c:if>
                    	 <c:if test="${purview['6053'] == '6053'}">
                    	 if(rowdata.factoryId=='19'){
                    	 	h += "<a onclick='f_open5("+rowdata.batchId+")' style='cursor:pointer;color:blue;'>下载密钥文件</a> ";
	                     }
	                     </c:if>
                    }		
                    return h;
                }}
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
                url:'<s:url value="/BatchConfig!selectTerminalBatchConfig.ac"/>?terminalBatchConfig.merSysId='+$("#merSysId").val()+
                '&terminalBatchConfig.batchId='+$("#batchId").val()+'&terminalBatchConfig.factoryId='+$("#factoryId").val()
            });
            $("#maingrid4").ligerGrid().set('dataAction','server');
            $("#pageloading").hide();
        });
          function f_open3(subMerId)
        {
       	 xf();
            location.href = "<s:url value='/BatchConfig!buildFile.ac'/>?terminalBatchConfig.batchId="+subMerId
        }
         function f_open4(subMerId)//subMerId批次号
        {
        	location.href = "<s:url value='/BatchConfig!download.ac'/>?terminalBatchConfig.batchId="+subMerId;
        }
        function f_open5(subMerId)//subMerId批次号
        {
        	location.href = "<s:url value='/BatchConfig!downloadKey.ac'/>?terminalBatchConfig.batchId="+subMerId;
        }
        function itemclick()
        {
            g.options.data = $.extend(true,{}, CustomersData);
            g.showFilter();
        }
   
        function f_search()
        {
           $("#form1").submit();
        }
        
        function f_clean(){
        	$("#merSysId").val("");
        	$("#batchId").val("");
        	$("#factoryId").val("");
        }
       
    </script>
	</head>
	<body style="padding: 4px; overflow: hidden;">
	<div id="alert">
<h4><span>提示</span></h4>
<p>
  	终端生成中，请稍后。。。。
</p>
</div>
		<div id="searchbar">
			<form id="form1" action="<s:url value="/pages/merTerminalInfo/TerminalBatchConfigList.jsp"/>" method="post">
				<table width="80%">
					<thead></thead>
					<tr>
						<td style="text-align: right;">
							批次号：
						</td>
						<td>
							<div class="l-text" style="float:left;">
								<input type="text" class="l-text-field" name="batchId" id="batchId" value="${param.batchId}" />
							</div>
						</td>
						<td style="text-align: right;">
							机构号：
						</td>
						<td style="text-align: left;">
						<div class="l-text" style="float:left;">
							<input type="text" class="l-text-field" name="merSysId" id="merSysId" value="${param.merSysId}" />
								</div>
						</td>
						<td style="text-align: right;">
							厂商编号：
						</td>
						<td>
							<div class="l-text" style="float:left;">
								<input type="text" class="l-text-field" name="factoryId" id="factoryId" value="${param.factoryId}" />
							</div>
						</td>
						<td style="text-align: left"><input id="btn" type="button" value="查 询" onclick="f_search()" class="l-button"/></td>
						<td style="text-align: left;"><input id="btn2" type="button" value="重 置" onclick="f_clean()" class="l-button"/></td>
					</tr>
				</table>
			</form>
			<script type="text/javascript">
				if(${requestScope.mess=="2"}){
					$.ligerDialog.error("文件下载异常！");
				}if(${requestScope.mess=="4"}){
					$.ligerDialog.error("文件路径不存在！");
				}if(${requestScope.mess=="5"}){
					$.ligerDialog.error("有重复的TSN，但是生成文件时异常！");
					c();
				}if(${requestScope.mess=="6"}){
					$.ligerDialog.error("关闭流失败！");
					c();
				}if(${requestScope.mess=="7"}){
					$.ligerDialog.success("生成终端成功！");
					c();
				}if(${requestScope.mess=="8"}){
					$.ligerDialog.success("有重复的TSN，已生成文件！\n url:D:\\Repeat_TSN\\TSN.txt");
					c();
				}if(${requestScope.mess=="9"}){
					$.ligerDialog.error("终端生成批次状态修改失败！");
					c();
				}if(${requestScope.mess=="10"}){
					$.ligerDialog.error("终端已经生成请勿重复生成！");
					c();
				}
			</script>
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
