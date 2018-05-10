<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>应用通道列表</title>
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
	<script type="text/javascript">
    	$(function(){
            $("#maingrid4").ligerGrid({
            columns: [
            { display: '通道号', name: 'appTractId', align: 'left', width: '12%' ,align: 'center' },
            { display: '通道名称', name: 'appTractName', width: '12%', align: 'center' },
            { display: '支付商户号', name: 'transMerId', width: '10%', align: 'center' }, 
 			{ display: '支付通道', name: 'payTract', width: '10%', align: 'center' }, 
            { display: '入网时间', name: 'createTime', width: '12%', align: 'center' }, 
             { display: '通道类别', name: 'tractType', width: '10%', align: 'center',render:function(rowdata,rowindex){
            	if(rowdata.tractType=='01'){return '还款';}
            	else if(rowdata.tractType=='02'){return '转账';}
            	else if(rowdata.tractType=='03'){return '余额查询';}
            	else if(rowdata.tractType=='04'){return '手机冲值';}
            	else if(rowdata.tractType=='01|02'){return '还款,转账';}
            	else if(rowdata.tractType=='01|03'){return '还款,余额查询';}
            	else if(rowdata.tractType=='01|04'){return '还款,手机冲值';}
            	else if(rowdata.tractType=='02|03'){return '转账,余额查询';}
            	else if(rowdata.tractType=='02|04'){return '转账,手机冲值';}
            	else if(rowdata.tractType=='03|04'){return '余额查询,手机冲值';}
            	else if(rowdata.tractType=='01|02|03'){return '还款,转账,余额查询';}
            	else if(rowdata.tractType=='01|02|04'){return '还款,转账,手机冲值';}
            	else if(rowdata.tractType=='01|03|04'){return '还款,余额查询,手机冲值';}
            	else if(rowdata.tractType=='02|03|04'){return '转账,余额查询,手机冲值';}
            	else if(rowdata.tractType=='01|02|03|04'){return '还款,转账,余额查询,手机冲值';}
            } },
            { display: '通道状态', name: 'status', width: '10%', align: 'center',render:function(rowdata,rowindex){
            	if(rowdata.status=='0'){return '未启用';}
            	else if(rowdata.status=='1'){return '已启用';}
            	else if(rowdata.status=='2'){return '暂停';}
            	else if(rowdata.status=='3'){return '暂停服务';}
            	else if(rowdata.status=='4'){return '停止服务';}
            	else if(rowdata.status=='5'){return '黑名单';}
            } },
           
             { display: '操作', name: 'null', width: '10%', align: 'center' ,render: function (rowdata, rowindex, value)
           	 	{
                    var h = "";
                    if (!rowdata._editing)
                    {
                    	<c:if test="${purview['5041'] == '5041'}">
	                    	var option = "启用 ";
	                    	if(rowdata.status=='1'){  
	                    		option = "暂停 ";
	                    		h += "<a onclick='f_open3("+rowdata.appTractId+")' style='cursor:pointer;color:blue;'>"+option+"</a>"; 
	                    	}else if(rowdata.status=='0'||rowdata.status=='2'){
	                    		option = "启用 ";
		                    		h += "<a onclick='f_open3("+rowdata.appTractId+")' style='cursor:pointer;color:blue;'>"+option+"</a>"; 
	                    	}
	                    </c:if>
	                    <c:if test="${purview['5042'] == '5042'}">
                   	   		h += "<a onclick='f_open2("+rowdata.appTractId+")' style='cursor:pointer;color:blue;'>详情 </a>";
                   	   	</c:if>
                   	   	<c:if test="${purview['5043'] == '5043'}">
                    		h += "<a onclick='f_open4("+rowdata.appTractId+")' style='cursor:pointer;color:blue;'>  修改  </a>";
                    	</c:if>
                        //h += "<a href='javascript:deleteRow(" + rowindex + ")'>删除</a> "; 
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
                 url:'<s:url value="/appTractInfo!selectAppTractAllList.ac"/>?appTractId='+$("#appTractId").val()+
                //"&appTractName="+encodeURI($("#appTractName").val())+
                "&transMerId="+$("#transMerId").val()+
                "&appTractName="+$("#appTractName").val()+
                "&payTract="+$("#payTract").val()+ 
                "&tractType="+$("#tractType").val()+
                "&status="+$("#status").val()                                
            });
            $("#maingrid4").ligerGrid().set('dataAction','server');
            $("#pageloading").hide();
        });
        
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
        	$("#appTractId").val("");
        	$("#appTractName").val("");
        	$("#transMerId").val("");
        	$("#payTract").val("-1");
        	$("#status").val("-1");
        }
           
        function f_open3(appTractId)
        {
            $.ligerDialog.open({ url: '<s:url value="/appTractInfo!appTractDetail.ac"/>?appTractId='+appTractId,
            height:370,width:650, isResize: false,title:'通道审核'});    
        }
        
         function f_open2(appTractId)
        {
            $.ligerDialog.open({ url: '<s:url value="/appTractInfo!selectAppTractDetail.ac"/>?appTractId='+appTractId,
            height:370,width:650, isResize: false,title:'通道详情'});    
        }
        
        function f_open4(appTractId)
        {
            $.ligerDialog.open({ url: '<s:url value="/appTractInfo!updateAppTractInfoDetail.ac"/>?appTractId='+appTractId,
            					 height:400,width:750, isResize: false,title:'修改通道信息'});    
        }
        
       //	function serEncryptKeyInfo(tractId)
       // 	{
       //  		 $.ligerDialog.open({ url: '<s:url value="/tractInfo!tractDetail.ac"/>?tractId='+tractId,
       //      					 height:500,width:650, isResize: false,title:'密钥管理'});    
       //   }
         if(data=='succ'){
			$("#tractStatus").html("<font color='red'>已审批</font>");
		}
        
       
    </script>
	</head>
	<body style="padding: 4px; overflow: hidden;">
		<div id="searchbar">
			<form id="form1" action="<s:url value="/pages/appTractInfo/selectAppTractInfo.jsp"/>" method="post">
				<table width="80%">
					<thead></thead>
					<tr>
						<td style="text-align: right;">
							通道号：
						</td>
						<td style="text-align: left;">
						<div class="l-text" style="float:left;">
							<input type="text" class="l-text-field" name="appTractId" id="appTractId"
								value="${param.appTractId}" />
						</div>
						</td>
						<td style="text-align: right;">
							通道名称：
						</td>
						<td style="text-align: left;">
						<div class="l-text" style="float:left;">
							<input type="text" class="l-text-field" name="appTractName" id="appTractName" maxlength="20" value="${param.appTractName}" />
							</div>
						</td>
						<td style="text-align: right;">
							支付商户号：
						</td>
						<td style="text-align: left;">
						<div class="l-text" style="float:left;">
							<input type="text" class="l-text-field" name="transMerId" id="transMerId"
								value="${param.transMerId}" />
						</div>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;">
							支付通道：
						</td>
						<td>
							<select name="payTract"  id="payTract">
								<option value="-1">
									--请选择--
								</option>
								<option value="CSTP" <c:if test="${param.payTract=='CSTP'}">selected="selected"</c:if>>
									CSTP
								</option>
								<option value="BJPOSP" <c:if test="${param.payTract=='BJPOSP'}">selected="selected"</c:if>>
									BJPOSP
								</option>
								<option value="SUPATM" <c:if test="${param.payTract=='SUPATM'}">selected="selected"</c:if>>
									SUPATM
								</option>
								<option value="UMSBJ" <c:if test="${param.payTract=='UMSBJ'}">selected="selected"</c:if>>
									UMSBJ
								</option>
							</select>
							</td>
						<td style="text-align: right;">
							通道状态：
						</td>
						<td>
							<select name="status"  id="status">
								<option value="-1">
									--请选择--
								</option>
								<option value="0" <c:if test="${param.status=='0'}">selected="selected"</c:if>>
									未启用 
								</option>
								<option value="1" <c:if test="${param.status=='1'}">selected="selected"</c:if>>
									已启用
								</option>
								<option value="2" <c:if test="${param.status=='2'}">selected="selected"</c:if>>
									暂停
								</option>
							</select>
						</td>
						<td style="text-align: left"><input id="btn" type="button" value="查 询" onclick="f_search()" class="l-button"/></td>
						<td style="text-align: left;"><input id="btn2" type="button" value="重 置" onclick="f_clean()" class="l-button"/></td>
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
