﻿<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>渠道商户管理</title>
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
            { display: '渠道Id', name: 'chId', align: 'center', width: '8%'},
            { display: '渠道商户名称', name: 'chName', align: 'center', width: '8%'},
            { display: '渠道商户号', name: 'chMerId', width: '12%', align: 'center'}, 
            { display: '渠道终端号', name: 'chTermId', width: '8%', align: 'center' },	
            { display: '渠道批次号', name: 'chBatchId', width: '8%', align: 'center' },	
            { display: '渠道序列号', name: 'chSeriId', width: '8%', align: 'center' },	
            { display: '渠道流水号', name: 'chTermSeqId', width: '8%', align: 'center' },	
            { display: 'T0手续费', name: 't0Fee', width: '8%', align: 'center' },	
            { display: 'T0费率', name: 't0Rate', width: '8%', align: 'center' },	
             { display: '渠道状态', name: 'chStat', width: '5%', align: 'center',render:function(rowdata,rowindex)
            	{
            	if(rowdata.chStat==0){return '正常';}
            	else if(rowdata.chStat==1){return '失效';}
            	} 
            },     
             { display: '渠道类型', name: 'chType', width: '5%', align: 'center',render:function(rowdata,rowindex)
            	{
            	if(rowdata.chType==0){return '未分配';}
            	else if(rowdata.chType==1){return '点对点';}
            	else if(rowdata.chType==2){return '轮询';}
            	} 
            },     
            { display: '交易类型', name: 'rateType', width: '5%', align: 'center',render:function(rowdata,rowindex)
            	{
            	if(rowdata.rateType==00){return '普通消费';
            	}else if(rowdata.rateType=='31'){
	            		return '香港(境外)';  
	            	}else if(rowdata.rateType=='32'){
	            		return '英国(境外)';  
	            	}else if(rowdata.rateType=='33'){
	            		return '美国(境外)';  
	            	}else if(rowdata.rateType=='34'){
	            		return '法国(境外)';
	           		}else if(rowdata.rateType=='35'){
	           			return '日本(境外)';
	           		}else if(rowdata.rateType=='36'){
	           			return '新西兰(境外)';
	           		}else if(rowdata.rateType=='37'){
	           			return '马来西亚(境外)';
	           		}else if(rowdata.rateType=='38'){
	           			return '泰国(境外)';
	           		}
	            }
            },     
            { display: '地区', name: 'provId', width: '8%', align: 'center' },
            { display: '开通日期', name: 'openDate', width: '8%', align: 'center' },
            { display: '操作', name: 'null', width: '8%', align: 'center' ,render: function (rowdata, rowindex, value)
           	 	{
                    var h = "";
	                    h += "<a onclick='f_open3(\""+rowdata.chId+"\")' style='cursor:pointer;color:blue;'>签到</a> ";
	                    h += "<a onclick='f_open4(\""+rowdata.chId+"\")' style='cursor:pointer;color:blue;'>编辑</a> ";
	                    h += "<a onclick='f_open5(\""+rowdata.chId+"\")' style='cursor:pointer;color:blue;'>启用/停用</a> ";
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
                url:'<s:url value="/channel!selectChannelMer.ac"/>?channelMerInfo.chMerId='+$("#chMerId").val()+
                '&channelMerInfo.chTermId='+$("#chTermId").val()+
                '&channelMerInfo.rateType='+$("#rateType").val()
            }); 
            $('#maingrid4').ligerGrid().set('dataAction','server');   
            $("#pageloading").hide();
        });
        

         function f_search()
        {
        /* 	if(len("merSysId",20,"N","机构商号")) */
				  $("#form1").submit();
			/* return false; */
        }
        
        function f_clean(){
        	$("#merSysId").val("");
        	$("#merShortName").val("");
        	$("#signPerson").val("");
        	$("#signDate").val("");
        	$("#createTime").val("");
        	$("#status").val("-1");
        	/* $("#platMerId").val(""); */
        	$("#tractId").val("-1");
        	/* $("#level").val("1"); */
        }
        
        function f_add()
        {
        $.ligerDialog.open({ url: '<s:url value="/channel!addChannelMer.ac"/>',
	           	 height:400,width: 600, isResize: false,title:'渠道商户添加'});    
        }
        
        function f_open3(chId)
        {
		     $.ajax({
	                   type: "POST",
	                   dataType: "text",
	                   url: '<s:url value="/channel!sign.ac"/>',
	                   data: {"chId":chId},
	                   success: function (data) {
	                      alert(data);
	                     // window.parent.$.ligerDialog.closeWaitting();
	                     window.location.reload();
		                 // dialogClose();
	                   }
		           });
        }
        function f_open4(chId)
        {
		    $.ligerDialog.open({ url: '<s:url value="/channel!getById.ac"/>?chId='+chId,
            					 height:350,width:750, isResize: false,title:'渠道商户编辑'});    
        }
        function f_open5(chId)
        {
		     $.ajax({
	                   type: "POST",
	                   dataType: "text",
	                   url: '<s:url value="/channel!changeStat.ac"/>',
	                   data: {"chId":chId},
	                   success: function (data) {
	                      alert('修改成功');
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
			<form id="form1" action="<s:url value="/pages/channel/selectChannelMer.jsp"/>" method="post" >
				<table width="100%" border="0">
					<thead></thead>
					<tr>
						<td style="text-align: right;width:100px;">
							渠道商户号：
						</td>
						<td style="text-align: left;">
						<div class="l-text" style="width: 130px;">
							<input style="width: 130px" type="text" name="chMerId" id="chMerId" value="${param.chMerId}" class="l-text-field" />
							</div>
						</td>
						<td style="text-align: right;width:100px;">
							渠道终端号：
						</td>
						<td style="text-align: left;">
						<div class="l-text" style="width: 130px;">
							<input type="text" name="chTermId" id="chTermId"  class="l-text-field" value="${param.chTermId}" />
								</div>
						</td>
						<td style="text-align: right;width:100px;">
							交易类型：
						</td>
						<td style="text-align: left;">
						<div class="l-text" style="width: 130px;">
							<select name="rateType" id="rateType" style="width: 130px;">
							<option value="-1">--请选择--</option>
							<option value="00"
								<c:if test="${param.rateType==00}">selected="selected"</c:if>>
								普通消费</option>
							<option value="31"
								<c:if test="${param.rateType==31}">selected="selected"</c:if>>
								香港(境外)</option>
							<option value="32"
								<c:if test="${param.rateType==32}">selected="selected"</c:if>>
								英国(境外)</option>
							<option value="33"
								<c:if test="${param.rateType==33}">selected="selected"</c:if>>
								美国(境外)</option>
							<option value="34"
								<c:if test="${param.rateType==34}">selected="selected"</c:if>>
								法国(境外)</option>
							<option value="35" <c:if test="${param.ratesType=='35'}">selected="selected"</c:if>>
									日本(境外)
								</option>
								<option value="36" <c:if test="${param.ratesType=='36'}">selected="selected"</c:if>>
									新西兰(境外)
								</option>
								<option value="37" <c:if test="${param.ratesType=='37'}">selected="selected"</c:if>>
									马来西亚(境外)
								</option>
								<option value="38" <c:if test="${param.ratesType=='38'}">selected="selected"</c:if>>
									泰国(境外)
								</option>
					</select>
								</div>
						</td>
						<td></td>
					</tr>
					<tr>
							<td style="text-align: left;"><input id="btn" type="button" value="查 询" onclick="f_search()" class="l-button"/></td>
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
