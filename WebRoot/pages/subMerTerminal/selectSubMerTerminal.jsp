﻿<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
            { display: '终端编号TSN', name: 'tsn', align: 'center', width: '10%'},
            { display: '终端号', name: 'terminalId', align: 'center', width: '8%'},
	        { display: '厂商号', name: 'factory', align: 'center', width: '8%'},
            { display: '子商户号', name: 'subMerId', width: '12%', align: 'center' ,render:function(rowdata,rowindex)
            	{
            	if(rowdata.subMerId=='-1'){return '无子商户';}
            	return rowdata.subMerId;
            	} 
            },
            /* { display: '一级代理商号', name: 'agentIdL1', width: '8%', align: 'center' ,render:function(rowdata,rowindex)
            	{
            	if(rowdata.agentIdL1=='-1'){return '无一级代理';}
            	return rowdata.agentIdL1;
            	} 
            },
            { display: '二级代理商号', name: 'agentIdL2', width: '8%', align: 'center' ,render:function(rowdata,rowindex)
            	{
            	if(rowdata.agentIdL2=='-1'){return '无二级代理';}
            	return rowdata.agentIdL2;
            	} 
           	}, */
       	    { display: '机构商号', name: 'merSysId', width: '10%', align: 'center' ,render:function(rowdata,rowindex)
            	{
            	if(rowdata.merSysId=='-1'){return '无机构';}
            	return rowdata.merSysId;
            	} },	
            /* { display: '平台商号', name: 'platMerId', width: '10%', align: 'center' }, */	
            { display: '登录名', name: 'loginName', width: '8%', align: 'center' },	
            { display: '类别', name: 'category', width: '5%', align: 'center',render:function(rowdata,rowindex)
            	{
            	if(rowdata.category=='04'){return '刷卡器';}
            	else if(rowdata.category=='08'){return 'POS机';}
            	} 
            },   
            { display: '状态', name: 'status', width: '5%', align: 'center',render:function(rowdata,rowindex)
            	{
            	if(rowdata.status==0){return '未使用';}
            	else if(rowdata.status==1){return '正在使用';}
            	else if(rowdata.status==2){return '暂停使用';}
            	else if(rowdata.status==3){return '黑名单';}
            	} 
            },     
            { display: '操作', name: 'null', width: '28%', align: 'left' ,render: function (rowdata, rowindex, value)
           	 	{
                    var h = "";
                    var pwd = "pwd";
                    <c:if test="${purview['6011'] == '6011'}">
               			h += "<a onclick='f_open5("+rowdata.id+")' style='cursor:pointer;color:blue;'>编辑</a> ";
               		</c:if>
               		<c:if test="${purview['6012'] == '6012'}">
                   		h += "<a onclick='f_open6("+rowdata.id+","+0+")' style='cursor:pointer;color:blue;'>重置密码 </a> "; 
                   	</c:if>
                   	/* <c:if test="${purview['6013'] == '6013'}">
                   		h += "<a onclick='f_open6("+rowdata.id+","+1+")' style='cursor:pointer;color:blue;'>重置状态</a> "; 
                   	</c:if> */
                   	<c:if test="${purview['6014'] == '6014'}">
                   		h += "<a onclick='f_open6("+rowdata.id+","+2+")' style='cursor:pointer;color:blue;'>清空子商户</a> "; 
                   	</c:if>
               		/* <c:if test="${purview['6015'] == '6015'}">
               			h += "<a onclick='f_open6("+rowdata.id+","+3+")' style='cursor:pointer;color:blue;'>清空一级代理 </a> ";
                    </c:if>
                    <c:if test="${purview['6017'] == '6017'}">
               			h += "<a onclick='f_open6("+rowdata.id+","+4+")' style='cursor:pointer;color:blue;'>清空二级代理 </a> ";
                    </c:if> */
                    <c:if test="${purview['6016'] == '6016'}">
	                    if(rowdata.status != 0){
	                   		 h += "<a onclick=\"f_open7('"+rowdata.id+"')\"  style='cursor:pointer;color:blue;'>设备注销</a> ";
	                    }
	                </c:if>
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
                url:'<s:url value="/subMerTerminal!selectSubMerTerminal.ac"/>?subMerTerminal.terminalId='+$("#terminalId").val()+'&subMerTerminal.tsn='+$("#tsn").val()+
                '&subMerTerminal.subMerId='+$("#subMerId").val()+ '&subMerTerminal.loginName='+$("#loginName").val() + '&subMerTerminal.merSysId='+$("#merSysId").val()
                /* +'&subMerTerminal.agentIdL1='+$("#agentId").val() */+'&subMerTerminal.status='+$("#status").val()+'&subMerTerminal.category='+$("#category").val()/* +"&subMerTerminal.level="+$("#level").val() */
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
        
          function f_open5(mid)
        {
            $.ligerDialog.open({ url: '<s:url value="/subMerTerminal!selectSubMerTerminalById.ac"/>?subMerTerminal.id='+mid,
            					 height:470,width: 650, isResize: false,title:'终端详情'});    
        }
        
        function f_open6(mid,type)
        {
        	var url = "<s:url value='/subMerTerminal!resetSubMerTerminal.ac'/>?subMerTerminal.id="+mid;
        	if(type == 0){
        		url+="&subMerTerminal.loginPwd=123456";
        	}else if(type == 1){
        		url+="&subMerTerminal.status=0";
        	}else if(type == 2){
        		url = "<s:url value='/subMerTerminal!resetSubMerInfo.ac'/>?subMerTerminal.id="+mid +"&subMerTerminal.subMerId=-1";
        	}else if(type == 3){
        		/* url+="&subMerTerminal.agentIdL1=-1"; */
        	}else if(type == 4){
        		/* url+="&subMerTerminal.agentIdL2=-1"; */
        	}
        	if(confirm("是否确认操作？"))
				$.post(url,
				function(data){
					if(data == "succ"){
						if(type != 0){
							alert("重置成功");
							
						}else{
							alert("密码重置成功,新密码为123456");
						}
						window.location.reload();
					}else if(data == "fone"){
						alert("重置失败");
					}
				},"text");
        }
        
         function f_open7(id)
        {
        	var url = "<s:url value='/subMerTerminal!terminalCancel.ac'/>?subMerTerminal.id="+id;
        	if(confirm("是否确认操作？"))
				$.post(url,
				function(data){
					if(data == "success"){
						alert("注销成功");
					}else if(data == "fail"){
						alert("注销失败");
					}
					$("#form1").submit();
				},"text");
        }
        
        function doUpload(){
        	var form = document.getElementById("upload");
    		form.submit();
        }
    </script>
	</head>
	<body style="padding: 4px; overflow: hidden;">
		<div id="searchbar">
			<form id="form1" action="<s:url value="/pages/subMerTerminal/selectSubMerTerminal.jsp"/>" method="post" >
				<table width="100%" border="0">
					<thead></thead>
					<tr>
						<td style="text-align: right;width:100px;">
							终端号：
						</td>
						<td style="text-align: left;">
						<div class="l-text" style="width: 130px;">
							<input style="width: 130px" type="text" name="terminalId" id="terminalId" value="${param.terminalId}" class="l-text-field" />
							</div>
						</td>
						<td style="text-align: right;width:100px;">
							设备编号：
						</td>
						<td style="text-align: left;">
						<div class="l-text" style="width: 130px;">
							<input style="width: 130px" type="text" name="tsn" id="tsn" value="${param.tsn}"  class="l-text-field"  />
						</div>
						</td>
						<td style="text-align: right;width:100px;">
							子商户号：
						</td>
						<td style="text-align: left;">
						<div class="l-text" style="width: 130px;">
							<input type="text" name="subMerId" id="subMerId"  class="l-text-field" value="${param.subMerId}" />
								</div>
						</td>
						<td style="text-align: right;width:100px;">
							登录名：
						</td>
						<td style="text-align: left;">
						<div class="l-text" style="width: 130px;">
							<input type="text" name="loginName"  class="l-text-field"  id="loginName" value="${param.loginName}" />
							</div>
						</td> 
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td style="text-align: right;width:100px;">
							机构商号：
						</td>
						<td style="text-align: left;">
							<input type="text" name="merSysId" id="merSysId" value="${param.merSysId}" />
						</td>
						<%-- <td style="text-align: right;width:100px;">
							代理商号：
						</td>
						<td style="text-align: left;">
							<select id="level" name="level" style="width: 50px;">
								<option value="1" <c:if test="${param.level==1}">selected</c:if>>一级</option>
								<option value="2" <c:if test="${param.level==2}">selected</c:if>>二级</option>
							</select>
							<input type="text" name="agentId" id="agentId" value="${param.agentId}" style="width: 75px;"/>
						</td> --%>
						<td style="text-align: right;width:100px;">
							状态：
						</td>
						<td><div class="l-text" style="width: 130px;">
							<select name="status" id="status"  class="l-text-field" style="width: 130px;">
								<option value="-1">
									--请选择--
								</option>
								<option value="0" <c:if test="${param.status==0}">selected="selected"</c:if>>
									未使用 
								</option>
								<option value="1" <c:if test="${param.status==1}">selected="selected"</c:if>>
									正在使用
								</option>
								<option value="2" <c:if test="${param.status==2}">selected="selected"</c:if>>
									暂停使用
								</option>
								<option value="3" <c:if test="${param.status==3}">selected="selected"</c:if>>
									黑名单
								</option>
							</select></div>
							</td>
							<td style="text-align: right;width:100px;">
								类别：
							</td>
							<td style="text-align: left;">
								<div class="l-text" style="width:130px;">
									<select name="category" id="category" class="l-text-field" style="width: 130px;">
										<option value="-1">
											--请选择--
										</option>
										<option value="04" <c:if test="${param.category==04}">selected="selected"</c:if>>
											刷卡器 
										</option>
										<option value="08" <c:if test="${param.category==08}">selected="selected"</c:if>>
											POS机
										</option>
									</select>
								</div>
							</td>
							<td style="text-align: left;"><input id="btn" type="button" value="查 询" onclick="f_search()" class="l-button"/></td>
							<td style="text-align: left;"><br /></td>
					</tr>
				</table>
			</form>
			<form id="upload" action="<s:url value="/subMerTerminal!uploadtxt.ac" />" method="post" enctype="multipart/form-data">
			   	<table width="100%" border="0">
					<thead></thead>
					<tr>
						<td style="text-align: right;width:100px;">
							导入POS：
						</td>
						<td style="text-align: left;">
						<div class="l-text" style="width: 130px;">
							<input type="file" id="txtFile" name="txtFile" value="" style="width:165px" accept="text/plain" />
							<input type="button" value="上传" class="l-button" onclick="doUpload()" /> 
							
							</div>
						</td>
						<td></td>
						<td></td>
					</tr>
				</table>
			   	<br />
			   	<div>
   	</div>
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
