﻿﻿<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>代付查询</title>
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
		<script src="<s:url value='/js/CheckLength.js'/>"></script>
		<style type="text/css">
			tr{
				height:30px;
			}
		</style>
		<script type="text/javascript">
        $(function () {
            $("#maingrid4").ligerGrid({
            columns: [
            { display: '流水号', name: 'orderId', align: 'center', width: '15%'},
            { display: '姓名', name: 'accountName', width: '12%', align: 'center'},
            { display: '卡号', name: 'cardNo', width: '12%', align: 'center'},
            { display: '交易时间', name: 'transTime', width: '8%', align: 'center' },	
            { display: '交易金额', name: 'transAmt', width: '8%', align: 'right', type:'currency',render:function(rowdata,rowindex){
            	var amt = rowdata.transAmt;
            	amt = amt/100;
            	if(amt>=1000){
            		return "<font color='blue'>"+amt.toFixed(2)+"</font>";
            	}
            	return amt.toFixed(2);
             } 
            }, 
            { display: '交易类型', name: 'payType', width: '5%', align: 'center', render:function(rowdata,rowindex){
            	if(rowdata.payType==1){return '手机端';}
            	else if(rowdata.payType==2){return 'POS端';}
            	else if(rowdata.payType==3){return '机构分润';}
            	else if(rowdata.payType==4){return '第三方';}
            	else return '未知';
            } }, 
            { display: '应答码', name: 'respCode', width: '8%', align: 'center' },	
            { display: '应答解释', name: 'respMsg', width: '12%', align: 'center' },	
            { display: '操作', name: 'null', width: '28%', align: 'left' ,render: function (rowdata, rowindex, value)
           	 	{
                    var h = "";
                   	h += "<a onclick='f_open6(\""+rowdata.id+"\")' style='cursor:pointer;color:blue;'>代付查询</a> "; 
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
                 url:'<s:url value="/orderInfo!selectDyPay.ac"/>?dyPayInfo.cardNo='+$("#cardNo").val()
                 +'&dyPayInfo.orderId='+$("#orderId").val()
                 +'&dyPayInfo.payType='+$("#payType").val()
                 +'&dyPayInfo.settleDt='+$("#settleDt").val()
                 +'&dyPayInfo.merSysId='+$("#merSysId").val()
            }); 
            $('#maingrid4').ligerGrid().set('dataAction','server');   
            $("#pageloading").hide();
             $("#settleDt").ligerDateEditor({ showTime: true,width:"130",format: "yyyyMMdd"});
        });
        

         function f_search()
        {
        /* 	if(len("merSysId",20,"N","机构商号")) */
        		$("#form1").attr("target", '');
				 $("#form1").attr("action", '<s:url value="/pages/order/dyPay.jsp" />');
				  $("#form1").submit();
			/* return false; */
        }
        
         function f_search_downLoad_excel() {
         	 $("#form1").attr("action", '<s:url value="/orderInfo!downloadDy.ac"/>?dyPayInfo.cardNo='+$("#cardNo").val()
                 +'&dyPayInfo.orderId='+$("#orderId").val()
                 +'&dyPayInfo.payType='+$("#payType").val()
                 +'&dyPayInfo.settleDt='+$("#settleDt").val()
                 +'&dyPayInfo.merSysId='+$("#merSysId").val());
			 $("#form1").attr("target", '_blank');
			 $("#form1").submit();
		}
		
        function f_open6(idNum){
		  		 $.ajax({
			                   type: "POST",
			                   dataType: "text",
			                   url: '<s:url value="/orderInfo!queryDyPay.ac"/>',
			                   data: {"id":idNum},
			                   success: function (data) {
			                     alert(data);
			                     // window.parent.$.ligerDialog.closeWaitting();
			                     window.location.reload();
				                 // dialogClose();
			                   }
				           });
       		 }
        
    </script>
	</head>
	<body style="padding: 4px; overflow: hidden;">
		<div id="searchbar">
			<form id="form1" action="<s:url value="/pages/order/dyPay.jsp"/>" method="post" >
				<table width="100%" border="0">
					<thead></thead>
					<tr>
						<td style="text-align: right;width:100px;">
							卡号：
						</td>
						<td style="text-align: left;">
						<div class="l-text" style="width: 130px;">
							<input style="width: 130px" type="text" name="cardNo" id="cardNo" value="${param.cardNo}" class="l-text-field" />
							</div>
						</td>
						<td style="text-align: right;width:100px;">
							流水号：
						</td>
						<td style="text-align: left;">
						<div class="l-text" style="width: 130px;">
							<input type="text" name="orderId" id="orderId"  class="l-text-field" value="${param.orderId}" />
								</div>
						</td>
						<td style="text-align: right;">
							交易类型：
						</td>
						<td>
							<select name="payType" id="payType" style="width: 130px;">
								<option value="-1">
									--请选择--
								</option>
								<option value="1" <c:if test="${param.payType==1}">selected="selected"</c:if>>
									手机端
								</option>
								<option value="2" <c:if test="${param.payType==2}">selected="selected"</c:if>>
									POS端
								</option>
								<option value="4" <c:if test="${param.payType==4}">selected="selected"</c:if>>
									第三方
								</option>
							</select>
						</td>
					<td style="text-align: right;">结算日期：</td>
						<td style="text-align: left;">
							<input type="text" name="settleDt" id="settleDt"  value="${param.settleDt}" />
						</td>
					</tr>
					<tr>
						<td style="text-align: right;width:100px;">
							第三方机构号：
						</td>
						<td style="text-align: left;">
						<div class="l-text" style="width: 130px;">
							<input type="text" name="merSysId" id="merSysId"  class="l-text-field" value="${param.merSysId}" />
								</div>
						</td>
					</tr>
					<tr>
							<td style="text-align: left;"><input id="btn" type="button" value="查 询" onclick="f_search()" class="l-button"/></td>
							<td style="text-align: left;"><input id="btn3" type="button" value="导 出" onclick="f_search_downLoad_excel()" class="l-button"/></td>
							
							<td style="text-align: left;"><br /></td>
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
