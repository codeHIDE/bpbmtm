<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>通道统计列表</title>
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
            { display: '通道号', name: 'tractId', align: 'left', width: '8%' ,align: 'center' },
            { display: '通道名称', name: 'tractName', width: '10%', align: 'center' }, 
             { display: '通道状态', name: 'ratesType', width: '8%', align: 'center',render:function(rowdata,rowindex){
            	if(rowdata.ratesType=='01'){return '扣率型';}
            	else if(rowdata.ratesType=='02'){return '封顶型';}
            } },
            { display: '统计时间', name: 'statictisDate', width: '10%', align: 'center' },
            { display: '交易金额(元)', name: 'payAmt', width: '10%', align: 'center' ,type:'currency'}, 
            { display: '已用金额(元)', name: 'useAmt', width: '8%', align: 'center',type:'currency' },
 			{ display: '退款金额(元)', name: 'refundAmt', width: '10%', align: 'center' ,type:'currency'}, 
            { display: '通道手续费(元)', name: 'reserved1', width: '8%', align: 'center',type:'currency' },
            { display: '入账金额(元)', name: 'amt', width: '8%', align: 'center',type:'currency',render:function(rowdata,rowindex){
            	return (rowdata.useAmt - rowdata.refundAmt - rowdata.reserved1)/100;
            } }
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
                url:'<s:url value="/tractStatictis!selectTractStatictisList.ac"/>?tractId='
                +$("#tractId").val()+"&tractName="+encodeURI($("#tractName").val())+
                '&ratesType='+$("#ratesType").val()+'&settleDate2='+$("#statictisDate").val()
                +'&payTract='+$("#payTract").val()
            });
            $("#maingrid4").ligerGrid().set('dataAction','server');
            $("#pageloading").hide();
            $("#statictisDate").ligerDateEditor({ showTime: true,format: "yyyy-MM-dd"});
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
        	$("#tractId").val("");
        	$("#tractName").val("");
        	$("#ratesType").val("-1");
        	$("#statictisDate").val("");
        	$("#payTract").val("-1");
        }
       
    </script>
	</head>
	<body style="padding: 4px; overflow: hidden;">
	<%@include file="/js/common.jsp" %>
		<div id="searchbar">
			<form id="form1" action="<s:url value="/pages/tractStatictis/tractStatictisList.jsp"/>" method="post">
			<table width="90%">
					<thead></thead>
					<tr>
						<td style="text-align: right;">
							通道号：
						</td>
						<td style="text-align: left;">
						<div class="l-text" style="float:left;">
							<input type="text" class="l-text-field" name="tractId" id="tractId" value="${param.tractId}" />
								</div>
						</td>
						<td style="text-align: right;">
							通道名称：
						</td>
						<td style="text-align: left;">
						<div class="l-text" style="float:left;">
							<input type="text" class="l-text-field" name="tractName" id="tractName" maxlength="20" value="${param.tractName}" />
								</div>
						</td>
						<td style="text-align: right;">
							统计日期：
						</td>
							<td style="text-align: left;" align="right">
							<input type="text" name="statictisDate" id="statictisDate" 
							<s:if test = "settleDate2 != null && settleDate2 != ''">
								value="<%=request.getAttribute("settleDate2") %>"
							</s:if>
							<s:else>
								value="${param.statictisDate}"
							</s:else> />
						</td>
						<td style="text-align: right;">
							通道类型：
						</td>
						<td>
							<select name="ratesType"  id="ratesType">
								<option value="-1">
									--请选择--
								</option>
								<option value="01" <c:if test="${param.ratesType=='01'}">selected="selected"</c:if>>
									扣率型
								</option>
									<option value="02" <c:if test="${param.ratesType=='02'}">selected="selected"</c:if>>
									封顶型
								</option>
							</select>
							</td>
							<td style="text-align: right;">
								支付通道：
							</td>
							<td>
								<select name="payTract" id="payTract">
									<option value="-1">
										---请选择---
									</option>
									<option value="CSTP" <c:if test="${param.payTract=='CSTP'}">selected="selected"</c:if>>
										CSTP
									</option>
									<option value="BJPOSP" <c:if test="${param.payTract=='BJPOSP'}">selected="selected"</c:if>>
										BJPOSP
									</option>
									<option value="UMSBJ" <c:if test="${param.payTract=='UMSBJ'}">selected="selected"</c:if>>
										UMSBJ
									</option>
									<option value="RUIYIN" <c:if test="${param.payTract=='RUIYIN'}">selected="selected"</c:if>>
										RUIYIN
									</option>
								</select>
							</td>
					</tr>
					<tr>
						<td style="text-align: center;width: 100px;" colspan="5">
						<input id="btn" type="button" value="查 询" onclick="f_search()" class="l-button"/>
						</td>
						<td style="text-align: center;width: 100px;" colspan="5">
						<input id="btn2" type="button" value="重 置" onclick="f_clean()" class="l-button"/>
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
	</body>
</html>
