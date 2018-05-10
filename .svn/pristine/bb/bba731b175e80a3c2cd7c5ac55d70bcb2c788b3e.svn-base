<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>更新权限</title>
		<link href="<s:url value='/js/jqui/ligerUI/skins/Aqua/css/ligerui-all.css'/>" rel="stylesheet" type="text/css" />
		<script src="<s:url value='/js/jqui/ligerUI/js/core/base.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/jquery/jquery-1.5.2.min.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/tree/ligerTree.js'/>" type="text/javascript"></script>
	 	<script src="<s:url value='/js/common.js'/>"></script>

		<script type="text/javascript">
	        function initRights(busMerId){
		       	$.post("<s:url value='/merManager!assignRight.ac'/>",
		       	{    
		       		busMerId:busMerId,
		       		mmAccount:$("#mmAccount").val()	
		       	},
		    	function(json){
		    		strObj = eval(json);
		    		var data = [];
		    		$.each(strObj, function(i) {
		    			if(strObj[i]!=null){
			    			if(strObj[i].isCheck=='true'){
			    				data.push({ id: strObj[i].modelNo, pid: strObj[i].superNo, text: strObj[i].modelName ,ischecked:true});
			    			}else{
			    				data.push({ id: strObj[i].modelNo, pid: strObj[i].superNo, text: strObj[i].modelName});
			    			}
		    			}
		    		});
					$("#tree1").ligerTree({  
			            data:data, 
			            idFieldName :'id',
			            parentIDFieldName :'pid'
			            });
		    	},"json");
		      }
		      
	       	function addRight(mid){
	    		manager = $("#tree1").ligerGetTreeManager();
	    		var notes = manager.getChecked();//复选框
	    		var rightStr = "";
	    		for(var i=0;i<notes.length;i++){
	    			rightStr += notes[i].data.id;
	    			if(i<notes.length-1){
	    				rightStr += ",";
	    			}
	    			
	    		}
	    		if(rightStr.length<1){
	    			alert("请选择权限");
	    		}else{
	    			//document.getElementById("purview").value=rightStr;
	    			//window.location.href='sysManager!updateSysManager.ac?purview='+rightStr+'&smId='+smId;
	    			
	    			$.post("<s:url value='/merManager!updateAdminPurview.ac'/>",
					{
						busMerId:mid,
						mmAccount:$("#mmAccount").val(),
						mmPass:$("#mmPass").val(),
						purview:rightStr
					},
					
					function(data){
						if(data=='succ'){
								alert("更新权限成功！");
								window.parent.location.reload();
						}else if(data=='fones'){
							alert("更新权限失败!");
						}else if(data=='foness'){
							alert("已有管理员和权限无法再次分配!");
						}
					},"text");
	    		}
	    	}
	    	
	    	function cls(){
				window.parent.$.ligerDialog.close();
	            window.parent.$(".l-dialog,.l-window-mask").remove();
			}
			
			
	    </script>
	    <style type="text/css">
			tr{
				height:30px;
			}
			
		</style>
	</head>
	<body style="padding: 10px" onload="initRights('<s:property value="busMerId"/>');">
		<form action="" method="post" id="form1">
			<div id="content">
				<div class="box_system">
					<table width="96%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="120px" style="text-align: right;">
							<h4>
								当前管理员 ：
							<h4>
							</td>
							<td style="text-align: left;" width="120px">
								<s:property value="merManager.mmAccount" />
								<input type="hidden" name="merManager.mmAccount" id="mmAccount" 
								value="<s:property value="merManager.mmAccount"/>"/>
							</td>
						</tr>
					</table>
					<h4>
						权限管理结构图
					</h4>
					<div style="margin-left: 30px">
						<ul id="tree1"></ul>
					</div>
				</div>
			</div><br/>
			<div style="margin-left: 80px">
				<input type="button" value="确  认" onclick="addRight('<s:property value="busMerId"/>')" class="l-button" onmouseover="dod()"
							onmouseout="this.className='l-button'" />&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="button" value="关 闭" id="btn2" class="l-button" onclick="cls()" />
			</div>
			
		</form>
	</body>
</html>
