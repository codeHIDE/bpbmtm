﻿<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>机构提现申请</title>
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
            { display: '机构号', name: 'merSysId', align: 'center', width: '8%'},
            { display: '提现金额', name: 'payMoney', width: '8%', align: 'right', type:'currency',render:function(rowdata,rowindex){
            	var amt = rowdata.payMoney;
            	amt = amt/100;
            	return amt.toFixed(2);
            } 
            }, 
            { display: '申请时间', name: 'createTime', width: '8%', align: 'center' },	
            { display: '提现状态', name: 'status', width: '5%', align: 'center', render:function(rowdata,rowindex){
            	if(rowdata.status==0){return '提现申请';}
            	else if(rowdata.status==1){return '提现完成';}
            	else return '未知';
            } }, 
            { display: '审核人', name: 'confirm', width: '8%', align: 'center' },	
            { display: '审核时间', name: 'confirmTime', width: '8%', align: 'center' },	
            { display: '操作', name: 'null', width: '10%', align: 'center',render: function (rowdata, rowindex, value,record)
           	 	{
                    var h = "";
                   if(rowdata.status==0){
                 	  h += "<a onclick='payOrder("+rowdata.id+")' style='cursor:pointer;color:blue;'>提现通过</a> ";
                    }
                    return h;
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
                url:'<s:url value="/statistics!selectMerApply.ac"/>?merSysId='+$("#merSysId").val()+
                '&startTime='+$("#startTime").val()+
                 '&endTime='+$("#endTime").val()
            }); 
            $('#maingrid4').ligerGrid().set('dataAction','server');   
            $("#pageloading").hide();
            $("#startTime").ligerDateEditor({ showTime: true,width:"150",format: "yyyy-MM-dd"});
            $("#endTime").ligerDateEditor({ showTime: true,width:"150",format: "yyyy-MM-dd"});
        });
        

         function f_search()
        {
        /* 	if(len("merSysId",20,"N","机构商号")) */
				  $("#form1").submit();
			/* return false; */
        }
        
        function f_makeFile(){
    	var form = document.getElementById("form1");
        window.location.href="<s:url value='/statistics!makeFile.ac'/>";
     }
     
          function payOrder(id)
        {
           window.parent.$.ligerDialog.confirm('是否代付此订单?', function (yes) {
	           if(yes){
		     		$(".span_show").css("visibility","hidden");
				     $.ajax({
			                   type: "POST",
			                   dataType: "text",
			                   url: '<s:url value="/statistics!payOrder.ac"/>',
			                   data: {"id":id},
			                   success: function (data) {
			                     alert(data);
			                     window.location.reload();
			                   }
				           });
				     }
	      });		
        }
        
    </script>
	</head>
	<body style="padding: 4px; overflow: hidden;">
		<div id="searchbar">
			<form id="form1" action="<s:url value="/pages/statistics/merProfitApply.jsp"/>" method="post" >
				<table width="100%" border="0">
					<thead></thead>
					<tr>
						<td style="text-align: right;width:100px;">
							申请时间：
						</td>
						<td style="text-align: left;">
						<div class="l-text" style="width: 150px;">
							<input style="width: 150px" type="text" name="startTime" id="startTime" value="${param.startTime}" class="l-text-field" />
							</div>
						</td>
						<td style="text-align: right;width:100px;">
							至
						</td>
						<td style="text-align: left;">
						<div class="l-text" style="width: 150px;">
							<input style="width: 150px" type="text" name="endTime" id="endTime" value="${param.endTime}" class="l-text-field" />
							</div>
						</td>
						<td style="text-align: right;width:100px;">
							机构号：
						</td>
						<td style="text-align: left;">
						<div class="l-text" style="width: 130px;">
							<input style="width: 130px" type="text" name="merSysId" id="merSysId" value="${param.merSysId}" class="l-text-field" />
							</div>
						</td>
					</tr>
					<tr>
							<td style="text-align: left;"><input id="btn" type="button" value="查 询" onclick="f_search()" class="l-button"/></td>
							<td style="text-align: left;"><input id="btn" type="button" value="生成文件" onclick="f_makeFile()" class="l-button"/></td>
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
