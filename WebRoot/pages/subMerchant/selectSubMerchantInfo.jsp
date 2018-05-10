 <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>子商户查询</title> 
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
		<style type="text/css">
			tr{
				height:30px;
			}
		</style>
		<script type="text/javascript">
        $(function (){
            $("#maingrid4").ligerGrid({
                columns: [
	            { display: '子商户入网号', name: 'subMerId', align: 'left', width: '10%' ,align: 'center' },
	            { display: '所属机构商户', name: 'merName', align: 'center', width: '16%',render:function(rowdata,rowindex){
	            	return rowdata.merName+"("+rowdata.merSysId+")";
	            } },
	            { display: '子商户名称', name: 'subMerName', width: '8%', align: 'center' },
	            { display: '子商户状态', name: 'status', width: '5%', align: 'center',render:function(rowdata,rowindex){
	            	if(rowdata.status=='0'){return '未审批';}
	            	else if(rowdata.status=='1'){return '已审批';}
	            	else if(rowdata.status=='2'){return '开通服务';}
	            	else if(rowdata.status=='3'){return '暂停服务';}
	            	else if(rowdata.status=='5'){return '审核失败';}
	            	else if(rowdata.status=='4'){return '黑名单';}
	            } },   
	             { display: '终端类型', name: 'subMerTrans.terminalType', width: '6%', align: 'center',render:function(rowdata,rowindex){
	            	if(rowdata.terminalType != null){
		            	if(rowdata.terminalType=='04'){return '自有终端';}
		            	else if(rowdata.terminalType=='08'){return '银联终端';}
	            	}else{
	            	return "";}
	            } },  
	            { display: '入网时间', name: 'createTime', width: '7%', align: 'center' },
	            { display: '结算账户名', name: 'settAccountName', width: '9%', align: 'center' }, 
	            { display: '结算账户号', name: 'settAccountNo', width: '11%', align: 'center' },
	            { display: '账户余额', name: 'gAccStatus', width: '7%', align: 'center',type:'currency' }, 
	            { display: '操作', name: 'null', width: '30%', align: 'center' ,render: function (rowdata, rowindex, value)
           	 	{
                    var h = "";
                    if (!rowdata._editing)
                    {
                    	 h += "<a onclick='f_getScanMer(\""+rowdata.subMerId+"\")' style='cursor:pointer;color:blue;'>扫码报备商户</a> ";
                    	<c:if test="${purview['1051'] == '1051'}">
	                    	if(rowdata.status=='3'||rowdata.status=='1'||rowdata.status=='0'){
	                    		h += "<a onclick='f_open3(\""+rowdata.subMerId+"\")' style='cursor:pointer;color:blue;'>上线</a> ";
	                    	}if(rowdata.status=='2'){
	                    		h += "<a onclick='f_open3(\""+rowdata.subMerId+"\")' style='cursor:pointer;color:blue;'>暂停</a> ";
	                    	}
                    	</c:if>
                    	<c:if test="${purview['1052'] == '1052'}">
           				    h += "<a onclick='f_open4(\""+rowdata.subMerId+"\")' style='cursor:pointer;color:blue;'>编辑</a> ";
           				</c:if>
           				<c:if test="${purview['1058'] == '1058'}">
                 			h += "<a onclick='f_open11(\""+rowdata.subMerId+"\")' style='cursor:pointer;color:blue;'>详情</a> ";
                 	 	</c:if>
           				<c:if test="${purview['1053'] == '1053'}">
                    	  	h += "<a onclick='f_open6(\""+rowdata.subMerId+"\")' style='cursor:pointer;color:blue;'>终端号</a> ";
                    	</c:if>
                    	<c:if test="${purview['1054'] == '1054'}">
                    	 	h += "<a onclick='f_open7(\""+rowdata.subMerId+"\")' style='cursor:pointer;color:blue;'>商户照片</a> ";
                    	</c:if>
                    	<c:if test="${purview['1055'] == '1055'}">
	                    	 if(rowdata.autoApprove=='1'){
	                    	 	h += "<a onclick='f_open8(\""+rowdata.subMerId+"\")' style='cursor:pointer;color:blue;'>实人认证</a> ";
	                    	 }
	                   	</c:if>
                    	 <c:if test="${purview['1056'] == '1056'}">
                    	 	h += "<a onclick='f_open9(\""+rowdata.subMerId+"\")' style='cursor:pointer;color:blue;'>修改结算信息</a> ";
                    	 </c:if>
                    	 <c:if test="${purview['1057'] == '1057'}">
                    		h += "<a onclick='f_open5(\""+rowdata.subMerId+"\")' style='cursor:pointer;color:blue;'>上传图片</a> ";
                    	 </c:if>
                    	 <c:if test="${purview['1059'] == '1059'}">
                    		 if("0"==rowdata.t0Status||""==rowdata.t0Status||null==rowdata.t0Status){
                        		 h += "<a onclick='f_open10("+rowdata.subMerId+",1)' style='cursor:pointer;color:blue;'>开通T+0</a> ";
                        	 }else if("1"==rowdata.t0Status){
                        		 h += "<a onclick='f_open10("+rowdata.subMerId+",0)' style='cursor:pointer;color:blue;'>关闭T+0</a> ";
                        	 }
                    	 </c:if>
                    	
                    }		
                    return h;
                }}
                ], 
                type: "POST",
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
                url:'<s:url value="/subMerInfo!findAll.ac"/>?subMerInfo.subMerId='+$("#subMerId").val()
                +'&subMerInfo.subMerName='+encodeURI($("#subMerName").val())+'&subMerInfo.settAccountNo='+$("#settAccountNo").val()
                + '&subMerInfo.createTime='+$("#createTime").val()+'&subMerInfo.status='+$("#status").val()
                +'&subMerInfo.merSysId='+$("#merSysId").val()+'&subMerInfo.tsn='+$("#terminalSerialNumber").val()
                +'&subMerInfo.subMerTerminalType='+$("#category").val()+'&subMerInfo.maxTime='+$("#maxTime").val()
                +'&subMerInfo.contactorPhone='+$("#contactorPhone").val()+'&subMerInfo.legalIdcard='+$("#legalIdcard").val()
            });
            $('#maingrid4').ligerGrid().set('dataAction','server');
            $("#pageloading").hide();
            $("#createTime").ligerDateEditor({ showTime: true,format: "yyyy-MM-dd"});
            $("#maxTime").ligerDateEditor({ showTime: true,format: "yyyy-MM-dd"});
        });
        function itemclick()
        {
            g.options.data = $.extend(true,{}, CustomersData);
            g.showFilter();
        }
         function f_search()
        {
        		var merSysId=$("#merSysId").val(); 
        		var subMerId=$("#subMerId").val();
        		var settAccountNo=$("#settAccountNo").val();
        		var contactorPhone=$("#contactorPhone").val();
					var pattern = /^[\S]*$/; //不包含空格
			    	var number = /^[0-9]{1,20}$/;	//数字	
					if(merSysId!=""&&!number.test(merSysId)) {
						alert("机构商户号只能是数字！");
						$("#merSysId").focus();
						$("#merSysId").val(""); 
						return false;
					}
					if(subMerId!=""&&!number.test(subMerId)) {
						alert("子商户号只能是数字！");
						$("#subMerId").focus();
						$("#subMerId").val(""); 
						return false;
					}
					if(settAccountNo!=""&&!number.test(settAccountNo)) {
						alert("结算账户号只能是数字！");
						$("#settAccountNo").focus();
						$("#settAccountNo").val(""); 
						return false;
					}if(contactorPhone!=""&&!number.test(contactorPhone)) {
						alert("手机号只能是数字！");
						$("#contactorPhone").focus();
						$("#contactorPhone").val(""); 
						return false;
					}else{
					  $("#form1").submit();
					}
        }
        function f_clean(){
        	$("#subMerId").val("");
        	$("#subMerName").val("");
        	$("#settAccountNo").val("");
        	$("#createTime").val("");
        	$("#maxTime").val("");
        	$("#status").val("-1"); 
        	$("#merSysId").val("");  
        	$("#terminalSerialNumber").val(""); 
        	$("#category").val("-1");
        	$("#contactorPhone").val("");
        	$("#legalIdcard").val("");	
        }
        function f_open3(subMerId)
	    {
	        $.ligerDialog.open({ url: '<s:url value="/subMerInfo!subMerchantTop.ac"/>?id='+subMerId,
            					 height:550,width:650, isResize: false,title:'子商户详情'});    
	    }
         function f_open4(subMerId)
        {
            $.ligerDialog.open({ url: '<s:url value="/subMerInfo!getById.ac"/>?id='+subMerId,
            					 height:550,width:650, isResize: false,title:'子商户编辑'});    
        }
         function f_open5(subMerId)
        {
            $.ligerDialog.open({ url: '<s:url value="/pages/subMerchant/UploadPicture.jsp"/>?id='+subMerId,
            					 height:490,width:650, isResize: false,title:'上传图片'});    
        }
         function f_open6(subMerId)
        {
            $.ligerDialog.open({ url: '<s:url value="/pages/subMerchant/selectSubMerTerminal.jsp"/>?id='+subMerId,
            					 height:250,width: 580, isResize: false,title:'子商户终端号'});    
        }
          function f_open7(subMerId)
        {
            $.ligerDialog.open({ url: '<s:url value="/subMerInfo!selectSubMerchantPicture.ac"/>?subMerInfo.subMerId='+subMerId+'&id=<%=System.currentTimeMillis()%>',
            					 height:500,width:900, isResize: false,title:'商户照片'});    
        }
         function f_open8(subMerId)
        {
            $.ligerDialog.open({ url: '<s:url value="/pages/authInfo/authList.jsp"/>?subMerId='+id,
            					 height:600,width:950, isResize: false,title:'实人认证'});    
        }
        
		function f_open9(subMerId){
			$.ligerDialog.open({ url: '<s:url value="/subMerInfo!passCheck.ac"/>?id='+subMerId,
            					 height:170,width:350, isResize: false,title:'密码验证'});
		}
		
		function f_open10(id,t0Status){
			var statusStr = '';
			if(t0Status=="0"){
				statusStr='关闭';
			}else{
				statusStr='开通';
			}
			$.ligerDialog.confirm("确定"+statusStr+"T+0?", function (yes) {
				 if(yes){
					 $.ajax({
						   type: "POST",
						   url: "<s:url value='/subMerInfo!changet0Status.ac' />",
						   data: "subMerTrans.subMerId="+id+"&subMerTrans.t0Status="+t0Status,
						   success: function(msg){
						     if(msg=="true"){
						    	 $.ligerDialog.success(""+statusStr+"成功!");
						    	 location.reload();
						     }else{
						    	 $.ligerDialog.warn(""+statusStr+"失败,请重新操作");
						     }
						   }
						});
				 }
			});
		}
		function f_open11(subMerId)
        {
            $.ligerDialog.open({ url: '<s:url value="/subMerInfo!getSubMerInfoById.ac"/>?id='+subMerId,
            					 height:550,width:650, isResize: false,title:'子商户详情'});    
        }
        
        function checkPay(subMerId)
        {
		     $.ajax({
	                   type: "POST",
	                   dataType: "text",
	                   url: '<s:url value="/subMerInfo!checkPay.ac"/>',
	                   data: {"subMerId":subMerId},
	                   success: function (data) {
	                      if(data=="true"){
	                       	//window.parent.$.ligerDialog.success('标记异常订单成功');
	                       	alert("代付验证成功!");
	                      }else {
	                      	//window.parent.$.ligerDialog.error('标记异常订单失败,请重试!');
	                      	alert("代付验证失败,请重试!");
	                      }
	                     // window.parent.$.ligerDialog.closeWaitting();
	                     window.location.reload();
		                 // dialogClose();
	                   }
		           });
        }
        
        function f_getScanMer(subMerId)
        {
		     $.ajax({
	                   type: "POST",
	                   dataType: "text",
	                   url: '<s:url value="/subMerInfo!getScanMer.ac"/>',
	                   data: {"subMerId":subMerId},
	                   success: function (data) {
	                       	alert(data);
	                     window.location.reload();
	                   }
		           });
        }
    </script>
	</head>
	<body style="padding: 4px; overflow: hidden;">
	<%@include file="/js/common.jsp" %>
		<div id="searchbar">			
			<form id="form1" action="<s:url value="/pages/subMerchant/selectSubMerchantInfo.jsp" />" method="post">
				<table width="100%">
					<thead></thead>
					<tr>
						<td style="text-align: right;">
							子商户入网号：
						</td>
						<td style="text-align: left;">
							<input type="text" name="subMerId" id="subMerId" value="${param.subMerId}" />
						</td>
						<td style="text-align: right;">
							子商户名称：
						</td>
						<td style="text-align: left;">
							<input type="text" name="subMerName" id="subMerName"  value="${param.subMerName}" maxlength="20"/>
						</td>
						<td style="text-align: right;">
							结算账户号：
						</td>
						<td style="text-align: left;">
							<input type="text" name="settAccountNo" id="settAccountNo" value="${param.settAccountNo}" />
						</td>
						<td style="text-align: right;">
							终端号：
						</td>
						<td style="text-align: left;">
							<input type="text" name="terminalSerialNumber" id="terminalSerialNumber" value="${param.terminalSerialNumber}" />
						</td>
					</tr>
					<tr>
						<td style="text-align: right;">
							入网时间：
						</td>
						<td >
							<input type="text" name="createTime" id="createTime"  value="${param.createTime}" style="float:left;"/>
						</td>
						<td style="text-align: right;">
							入网结束时间：
						</td>
						<td>
							<input type="text" name="maxTime" id="maxTime"  value="${param.maxTime}" style="float:right;"/>
						</td>
							<td style="text-align: right;">
								机构商户号：
							</td>
							<td style="text-align: left;">
								<input type="text" name="merSysId" id="merSysId"  value="${param.merSysId}"/>
							</td>
							<td style="text-align: right;">
							子商户状态：
							</td>
							<td>
							<select name="status" id="status">
								<option value="-1">
									--请选择--
								</option>
								<option value="0" <c:if test="${param.status=='0'}">selected="selected"</c:if>>
									未审批 
								</option>
								<option value="1" <c:if test="${param.status==1}">selected="selected"</c:if>>
									已审批
								</option>
								<option value="2" <c:if test="${param.status==2}">selected="selected"</c:if>>
									开通服务
								</option>
								<option value="3" <c:if test="${param.status==3}">selected="selected"</c:if>>
									暂停服务
								</option>
								<option value="4" <c:if test="${param.status==4}">selected="selected"</c:if>>
									停止服务
								</option>
								<option value="5" <c:if test="${param.status==5}">selected="selected"</c:if>>
									审核失败
								</option>
							</select>
							</td>
					</tr>
					<tr>
							<td style="text-align: right;">
								终端类型：
							</td>
							<td>
							<select name="category" id="category">
								<option value="-1">
									--请选择--
								</option>
								<option value="04" <c:if test="${param.category=='04'}">selected="selected"</c:if>>
									APP
								</option>
								<option value="08" <c:if test="${param.category=='08'}">selected="selected"</c:if>>
									POS
								</option>
							</select>
							</td>
							<td style="text-align: right;">
								手机号：
							</td>
							<td>
								<input type="text" name="contactorPhone" id="contactorPhone"  value="${param.contactorPhone}"/>
							</td>
							<td style="text-align: right;">
								法人证件号：
							</td>
							<td>
								<input type="text" name="legalIdcard" id="legalIdcard"  value="${param.legalIdcard}"/>
							</td>	
							<td style="text-align: left;"><input id="btn" type="button" value="查 询" onclick="f_search()" class="l-button"/></td>
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
