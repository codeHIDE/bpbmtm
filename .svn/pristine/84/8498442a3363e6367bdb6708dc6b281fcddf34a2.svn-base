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
        $(function () {
            $("#maingrid4").ligerGrid({
            columns: [
             { display: '订单号', name: 'orderId', align: 'center', width: '10%'},
             { display: '商户号', name: 'subMerId', align: 'center', width: '10%'},
            /*{ display: '状态', name: 'status', width: '6%', align: 'center',render:function(rowdata,rowindex)
            	{
            	if(rowdata.status==0){return '未操作';}
            	else if(rowdata.status==1){return '已操作';}
            	else{
            		return "";
            	}
            	} 
            }, */
            { display: '冻结状态', name: 'status', width: '6%', align: 'center',render:function(rowdata,rowindex)
            	{
            	if(rowdata.frozenStatus==1){return '返还';}
            	else if(rowdata.frozenStatus==2){return '冻结';}
            	else if(rowdata.frozenStatus==3){return '扣除';}
            	else{
            		return "";
            	}
            	} 
            },     
            { display: '返还时间', name: 'returnTime', width: '12%', align: 'center' },
            { display: '冻结时间', name: 'frozenTime', width: '12%', align: 'center' }, 
            { display: '扣除时间', name: 'deductionTime', width: '12%', align: 'center' }, 
            { display: '保留域1', name: 'reserved1', width: '12%', align: 'center' }, 
            { display: '保留域2', name: 'reserved2', width: '12%', align: 'center' }, 
            { display: '保留域3', name: 'reserved3', width: '12%', align: 'center' }
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
                url:'<s:url value="/orderInfo!selectOrderFrozen.ac"/>?orderFrozen.orderId='+$("#orderId").val()+'&orderFrozen.subMerId='+$("#subMerId").val()
            }); 
            $('#maingrid4').ligerGrid().set('dataAction','server');   
            $("#pageloading").hide();
        });
        

         function f_search()
        {
       		var merSysId=$("#subMerId").val();  
			var pattern = /^[\S]*$/; //不包含空格
	    	var number = /^[0-9]{1,20}$/;	//数字
			if(merSysId!=""&&!number.test(merSysId)) {
				alert("商户号只能是数字！");
				$("#subMerId").focus();
				return false;
			}else{
			  $("#form1").submit();
			}
        }
    </script>
	</head>
	<body style="padding: 4px; overflow: hidden;">
		<div id="searchbar">
			<form id="form1" action="<s:url value="/pages/order/selectOrderFrozen.jsp"/>" method="post" >
				<table width="100%" border="0">
					<thead></thead>
					<tr>
						<td style="text-align: right;width:100px;">
							子商户号：
						</td>
						<td style="text-align: left;">
						<div class="l-text" style="width: 130px;">
							<input type="text" name="subMerId" id="subMerId" value="${param.subMerId}" class="l-text-field" />
							</div>
						</td>
						<td style="text-align: right;width:100px;">
							订单号：
						</td>
						<td style="text-align: left;">
						<div class="l-text" style="width: 130px;">
							<input type="text" name="orderId" id="orderId" maxlength="20" class="l-text-field" value="${param.orderId}" />
								</div>
						</td>
						<td style="text-align: left;"><input id="btn" type="button" value="查 询" onclick="f_search()" class="l-button"/></td>
						<td style="text-align: left;"></td>
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
