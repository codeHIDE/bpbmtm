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
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerMessageBox.js'/>" type="text/javascript"></script>
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
           
             { display: '机构商户名称(机构商户号)', name: 'merName', align: 'center', width: '15%',render:function(rowdata,rowindex){
	            	 return rowdata.merName+"("+rowdata.merSysId+")";
	            } },
	        /*{ display: '平台商户名称(平台商户号)', name: 'null', align: 'center', width: '15%',render:function(rowdata,rowindex){
	            	if(rowdata.platMerInfo==null){
	            		return "";
	            	}
	            	return rowdata.platMerInfo.platMerName+"("+rowdata.platMerInfo.platMerId+")";
	            } },*/
            { display: '机构商户状态', name: 'status', width: '12%', align: 'center',render:function(rowdata,rowindex)
            	{
            	if(rowdata.status==0){return '未审批';}
            	else if(rowdata.status==1){return '已审批';}
            	else if(rowdata.status==2){return '已上线';}
            	else if(rowdata.status==3){return '暂停';}
            	else if(rowdata.status==4){return '黑名单';}
            	} 
            },     
            { display: '入网时间', name: 'createTime', width: '12%', align: 'center' },
            { display: '联系人', name: 'contactor', width: '12%', align: 'center' }, 
            { display: '联系电话', name: 'contactorPhone', width: '12%', align: 'center' }, 
            { display: '操作', name: 'null', width: '40%', align: 'left' ,render: function (rowdata, rowindex, value)
           	 	{
                     var h = "";
                   	 <c:if test="${purview['1041'] == '1041'}">
                    	if(rowdata.status==0){
	                    		h += "<a onclick='f_open3("+rowdata.merSysId+")' style='cursor:pointer;color:blue;'>审批</a> "; 
                    	} else if(rowdata.status=='1') {
	                    		h += "<a onclick='f_open3("+rowdata.merSysId+")' style='cursor:pointer;color:blue;'>上线</a> "; 
                    	}else if(rowdata.status=='2'){
	                    		h += "<a onclick='f_open3("+rowdata.merSysId+")' style='cursor:pointer;color:blue;'>暂停</a> "; 
                    	}else if(rowdata.status=='3'){
	                    		h += "<a onclick='f_open3("+rowdata.merSysId+")' style='cursor:pointer;color:blue;'>恢复 </a> "; 
                   	 	}
                    </c:if>
                    <c:if test="${purview['1042'] == '1042'}">
                   		 h += "<a onclick='f_open5("+rowdata.merSysId+")' style='cursor:pointer;color:blue;'>编辑</a> ";
                   	</c:if>
                   	<c:if test="${purview['1043'] == '1043'}">
                    	h += "<a onclick='f_open6("+rowdata.merSysId+")' style='cursor:pointer;color:blue;'>交易配置 </a> "; 
                    </c:if>
                    <c:if test="${purview['1044'] == '1044'}">
                    	h += "<a onclick='f_open7("+rowdata.merSysId+")' style='cursor:pointer;color:blue;'>修改权限</a> "; 
                    </c:if>
                    <c:if test="${purview['1045'] == '1045'}">
                    	h += "<a onclick='f_open8("+rowdata.merSysId+")' style='cursor:pointer;color:blue;'>上传LOGO </a> "; 
                    </c:if>
                    <c:if test="${purview['1046'] == '1046'}">
                   		h += "<a onclick='f_open9("+rowdata.merSysId+")' style='cursor:pointer;color:blue;'>修改结算信息 </a> ";
                   	</c:if>
                   	//<c:if test="${purview['1047'] == '1047'}">
                  // 		h += "<a onclick='f_open10("+rowdata.merSysId+")' style='cursor:pointer;color:blue;'>通道规则 </a> ";
                   //	</c:if>
                   	<c:if test="${purview['1048'] == '1048'}">
                   		h += "<a onclick='f_open11("+rowdata.merSysId+")' style='cursor:pointer;color:blue;'>添加操作员</a> ";
                   	</c:if>
                   	<c:if test="${purview['1049'] == '1049'}">
                   		h += "<a onclick='updatePassword("+rowdata.merSysId+")' style='cursor:pointer;color:blue;'>重置密码</a> ";
                   	</c:if>
                   	<c:if test="${purview['10491'] == '10491'}">
	                   	if(rowdata.isIntoPieces==0){
	                   		h += "<a  onclick='updateIntoPieces("+rowdata.merSysId+",1)' style='cursor:pointer;color:blue;'>暂停进件</a>";
	                   	}
	                   	else if(rowdata.isIntoPieces==1){
	                   		h += "<a onclick='updateIntoPieces("+rowdata.merSysId+",0)' style='cursor:pointer;color:blue;'>开启进件</a>";
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
                url:'<s:url value="/merchantInfo!selectMerchantAll.ac"/>?merchantInfo.merSysId='+$("#merSysId").val()+'&merchantInfo.merName='+$("#merShortName").val()+
                '&merchantInfo.signPerson='+$("#signPerson").val()+ '&merchantInfo.signDate='+$("#signDate").val()+ '&merchantInfo.createTime='+$("#createTime").val()
                +'&merchantInfo.status='+$("#status").val()+'&merchantInfo.platMerId='+$("#platMerId").val()+'&merchantInfo.tractId='+$("#tractId").val()
            }); 
            $('#maingrid4').ligerGrid().set('dataAction','server');   
            $("#pageloading").hide();
            $("#signDate").ligerDateEditor({ showTime: true,format: "yyyy-MM-dd"});
            $("#createTime").ligerDateEditor({ showTime: true,format: "yyyy-MM-dd"});
        });
        

         function f_search()
        {
        		var merSysId=$("#merSysId").val();  
					var pattern = /^[\S]*$/; //不包含空格
			    	var number = /^[0-9]{1,20}$/;	//数字
			    	
					if(merSysId!=""&&!number.test(merSysId)) {
						alert("商户号只能是数字！");
						$("#merSysId").focus();
						return false;
					}else{
					  $("#form1").submit();
					}
         
        }
        
        function f_clean(){
        	$("#merSysId").val("");
        	$("#merShortName").val("");
        	$("#signPerson").val("");
        	$("#signDate").val("");
        	$("#createTime").val("");
        	$("#status").val("-1");
        	$("#platMerId").val("");
        	$("#tractId").val("-1");
        }
        
         function f_open3(mid)
        {
            $.ligerDialog.open({ url: '<s:url value="/merchantInfo!merchantIndex.ac"/>?mid='+mid,
            					 height:420,width:650, isResize: false,title:'机构商户审核'});    
        }
        function f_open4(mid)
        {
            $.ligerDialog.open({ url: '<s:url value="/merchantInfo!merDetail.ac"/>?mid='+mid,
            					 height:440,width:700, isResize: false,title:'机构商户详情'});    
        }
        
          function f_open5(mid)
        {
            $.ligerDialog.open({ url: '<s:url value="/merchantInfo!updateMerchantDetail.ac"/>?mid='+mid,
            					 height:470,width: 650, isResize: false,title:'修改机构商户信息'});    
        }
        
        function f_open6(mid)
        {
            $.ligerDialog.open({ url: '<s:url value="/merchantInfo!toSetTransInfo.ac"/>?mid='+mid,
            					 height:540,width:840, isResize: false,title:'配置机构商户交易信息'});    
        }
        
         function f_open7(mid)
        {    
            $.ligerDialog.open({ url: '<s:url value="/merchantInfo!toUpdateManager.ac"/>?mid='+mid,
            					 height:450,width:500, isResize: false,title:'修改权限'});    
        }
        
         function f_open8(mid)
        {    
            $.ligerDialog.open({ url: '<s:url value="/merchantInfo!merUploadLogo.ac"/>?mid='+mid,
            					 height:240,width:340, isResize: false,title:'上传LOGO'});    
        }
        
        
          function f_open9(mid)
        {
            $.ligerDialog.open({ url: '<s:url value="/pages/merchant/LevelPwdCheck.jsp"/>?mid='+mid,
            					 height:200,width: 300, isResize: false,title:'确认级别密码'});    
        }
        
        //通道规则
          function f_open10(mid)
        {
            $.ligerDialog.open({ url: '<s:url value="/merchantInfo!tractRules.ac"/>?mid='+mid,
            					 height:430,width: 950, isResize: false,title:'通道规则'});    
        }
        
        //添加操作员
          function f_open11(mid)
        {
            $.ligerDialog.open({ url: '<s:url value="/merchantInfo!toAddManager.ac"/>?mid='+mid,
            					 height:430,width: 550, isResize: false,title:'添加操作员'});    
        }      
         
        function updatePassword(mid){
       		$.ligerDialog.confirm("确认重置机构商户密码?", function (yes) {
        	if(yes){
        		$.post('<s:url value="/merchantInfo!updatePassword.ac"/>',
				  {
				    mid:mid
				  },
				  function(data,status){
				    if(data=="succ"){
				    	$.ligerDialog.success('重置密码成功!新密码 为:123456');
				    }else{
				    	$.ligerDialog.error('重置密码失败!');
				 	   }
				  });
        		}
        	});
        }
           
        function updateIntoPieces(merSysId,isIntoPieces){
			title = isIntoPieces==1?"暂停":"开启";
			$.ligerDialog.confirm("是否"+title+"该机构商户进件？",function(yes){
				if(yes){
					$.ajax({
						type: "POST", 
						url:'<s:url value="/merchantInfo!updateIsIntoPieces.ac" />',
						data:{'merchantInfo.merSysId':merSysId,'merchantInfo.isIntoPieces':isIntoPieces},
						error: function(request) {
							$.ligerDialog.error("Connection error");
						},
						success: function(data) {
							if(data == "succ") {
								$.ligerMessageBox.success("提示","操作成功!",function(data){
		                   	    	window.location.reload(); //刷新窗口			
		                   	      });
							}else{
								$.ligerDialog.error("操作失败!");
							}
						}});
				}		
			});
		}
        
    </script>
	</head>
	<body style="padding: 4px; overflow: hidden;">
		<div id="searchbar">
			<form id="form1" action="<s:url value="/pages/merchant/selectMerchantInfo.jsp"/>" method="post" >
				<table width="100%" border="0">
					<thead></thead>
					<tr>
						<td style="text-align: right;width:100px;">
							机构商户号：
						</td>
						<td style="text-align: left;">
						<div class="l-text" style="width: 130px;">
							<input type="text" name="merSysId" id="merSysId" value="${param.merSysId}" class="l-text-field" />
							</div>
						</td>
						<td style="text-align: right;width:100px;">
							机构商户名称：
						</td>
						<td style="text-align: left;">
						<div class="l-text" style="width: 130px;">
							<input type="text" name="merShortName" id="merShortName" maxlength="20" class="l-text-field" value="${param.merShortName}" />
								</div>
						</td>
						<td style="text-align: right;width:100px;">
							签约人：
						</td>
						<td style="text-align: left;">
						<div class="l-text" style="width: 130px;">
							<input type="text" name="signPerson" id="signPerson"  class="l-text-field" value="${param.signPerson}" />
								</div>
						</td>
						<td style="text-align: right;width:100px;">
							平台商户号：
						</td>
						<td style="text-align: left;">
						<div class="l-text" style="width: 130px;">
							<input type="text" name="platMerId"  class="l-text-field"  id="platMerId" value="${param.platMerId}" />
							</div>
						</td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td style="text-align: right;width:100px;">
							签约时间：
						</td>
						<td style="text-align: left;">
							<input type="text" name="signDate" id="signDate" value="${param.signDate}" />
						</td>
						<td style="text-align: right;width:100px;">
							入网时间：
						</td>
						<td style="text-align: left;">
							<input type="text" name="createTime" id="createTime" value="${param.createTime}"/>
						</td>
						<td style="text-align: right;width:100px;">
							机构商户状态：
						</td>
						<td><div class="l-text" style="width: 130px;">
							<select name="status" id="status"  class="l-text-field" style="width: 130px;">
								<option value="-1">
									--请选择--
								</option>
								<option value="0" <c:if test="${param.status==0}">selected="selected"</c:if>>
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
									黑名单
								</option>
							</select></div>
							</td>
							<td style="text-align: right;width:100px;">
								通道号：
							</td>
							<td style="text-align: left;">
								<input type="text" name="tractId" id="tractId" value="${param.tractId}"/>
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
