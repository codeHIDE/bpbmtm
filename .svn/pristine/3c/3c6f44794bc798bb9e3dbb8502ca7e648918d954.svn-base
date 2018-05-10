<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/common/taglibs.jsp"%>
<%@include file="/js/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>清分信息</title>
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
        $(function (){
            $("#maingrid4").ligerGrid({
                columns: [   
             	 { display: '机构号', name: 'merSysId', align: 'left', width: '8%' ,align: 'center' },
             	{ display: '清算日期', name: 'settleDate', align: 'left', width: '8%' ,align: 'center' ,render:function(rowdata,rowindex){
             		if(rowdata.settleDate!=null)
	            	return rowdata.settleDate.substring(0,4)+"-"+rowdata.settleDate.substring(4,6)+"-"+rowdata.settleDate.substring(6);
	            	return '';
	            } },
	            { display: '金额', name: 'merAmt', align: 'left', width: '8%' ,align: 'center' ,type:'currency',render:function(rowdata,rowindex){
		          		return rowdata.merAmt/100;
		            }},
	            { display: '手续费', name: 'feeAmt', align: 'left', width: '8%' ,align: 'center' ,type:'currency',render:function(rowdata,rowindex){
	            	if(rowdata.feeAmt == 0)
	            	return "0";
	            	else
		          	return rowdata.feeAmt/100;
		            }},
	            { display: '状态', name: 'status', align: 'left', width: '8%' ,align: 'center',render:function(rowdata,rowindex){
	            	if(rowdata.status=='0'){return '未操作';}
	            	else if(rowdata.status=='1'){return '上账成功';}
	            	else if(rowdata.status=='2'){return '上账失败';}
	            }},
	            { display: '创建时间', name: 'createTime', align: 'left', width: '8%' ,align: 'center' },
	            { display: '提交时间', name: 'updateTime', align: 'left', width: '8%' ,align: 'center' },
	            { display: '提交操作员', name: 'updateOpenId', width: '8%', align: 'center' },
		 		{ display: '操作', name: 'null', width: '12%', align: 'center' ,render: function (rowdata, rowindex, value)
           	 	{
                     var h = "";
                  if (!rowdata._editing)
                    {
                    	<c:if test="${purview['4031'] == '4031'}">
                    	  if(rowdata.status=='0'){
                    		 h += "<a onclick='f_open("+rowdata.merSysId+",\""+rowdata.settleDate+"\")' style='cursor:pointer;color:blue;'>上账</a> ";
  						  }
  						</c:if>
  						<c:if test="${purview['4032'] == '4032'}">
                     	 	 h += "<a onclick='f_open1("+rowdata.merSysId+",\""+rowdata.settleDate+"\")' style='cursor:pointer;color:blue;'> 修改</a> ";
						</c:if>
						<c:if test="${purview['4033'] == '4033'}"> 
                     	 	 h += "<a onclick='f_open2("+rowdata.merSysId+",\""+rowdata.settleDate+"\")' style='cursor:pointer;color:blue;'> 详情</a> ";
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
		        sortName : null,
		        sortOrder:null,      
		        root :'Rows',                       //数据源字段名
		        record:'Total',                     //数据源记录数字段名
		        pageParmName :'page',               //页索引参数名，(提交给服务器)
		        pagesizeParmName:'pagesize',        //页记录数参数名，(提交给服务器)
                dataAciton : 'server',    
                url:'<s:url value="/accountManage!selectAccountManageAllList.ac"/>?accountManage.merSysId='+$("#merSysId").val()+'&settleDate2='+$("#settleDate").val()
                
            });  
            $('#maingrid4').ligerGrid().set('dataAction','server');
            $("#pageloading").hide();
             $("#settleDate").ligerDateEditor({ showTime: true,format: "yyyy-MM-dd"});
        });
      
        
         function f_search()
        {
        	var merSysId=$("#merSysId").val();  
			var pattern = /^[\S]*$/; //不包含空格
			var number = /^[0-9]{1,20}$/;	//数字	
			if(merSysId!=""&&!number.test(merSysId)) {
				alert("机构商户号只能是数字！");
				$("#merSysId").focus();
			    return false;
			}else{
			    $("#form1").submit();
			}
        }
        
        
        function f_clean(){
        	$("#merSysId").val("");
        	$("#settleDate").val("");
        	
        }
         function f_open(merSysId,settleDate)
        {
           window.parent.$.ligerDialog.confirm('是否上账?', function (yes) {
           		if(yes){
           		    $.ligerDialog.waitting('上账中,请稍候...');
                     setTimeout(function ()
                     {
                         $.ajax({
			             type: "POST",
			                   dataType: "html",
			                   url: '<s:url value="/accountManage!upAccount.ac"/>',
			                   data: {settleDate:settleDate,merSysId:merSysId},
			                   success: function (data) {
			                      alert(data);
			                    //  $.ligerDialog.success(data);
			                      $.ligerDialog.closeWaitting();
		                     		f_search();
			                   }
			              });
                     }, 2000);
				}
			});      		    
        }
        function f_open2(merSysId,settleDate)
        {
            $.ligerDialog.open({ url: '<s:url value="/accountManage!selectAccountManageDetail.ac"/>?merSysId='+merSysId+'&settleDate='+settleDate,
            height:350,width:550, isResize: false,title:'详情'});    
        }
        
        function f_open1(mid,settleDate)
        {
            $.ligerDialog.open({ url: '<s:url value="/pages/theReport/LevelPwdCheck.jsp"/>?mid='+mid+'&settleDate='+settleDate,
            					 height:200,width: 350, isResize: false,title:'确认级别密码'});    
        }
			
			
        
    </script>
	</head>
	<body style="padding: 4px; overflow: hidden;">
		<div id="searchbar">			
			<form id="form1" action="<s:url value="/pages/theReport/statictisUpList.jsp" />" method="post">
				<table width="80%">
					<tr>
						<td style="text-align: right;">
							机构商户号：
						</td>
						<td style="text-align: left;">
							<input type="text" name="merSysId" id="merSysId" value="${param.merSysId}" />
						</td>
						<td style="text-align: right;">
							清算日期：
						</td>
						<td style="text-align: left;">
							<input type="text" name="settleDate" id="settleDate" value="<%=request.getParameter("settleDate")==null?request.getAttribute("settleDate"):request.getParameter("settleDate")%>" />
						</td>
						<td style="text-align: left;"><input id="btn" type="button" value="查 询" onclick="f_search()" class="l-button"/></td>
						<td style="text-align: left;"><input id="btn" type="button" value="重 置" onclick="f_clean()" class="l-button"/></td>
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
