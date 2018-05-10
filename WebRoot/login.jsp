<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.bypay.util.RemoveSession"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	RemoveSession.removeExitSession(request);
%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>商户管理平台</title>
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="Cache-Control" content="no-store" />
		<meta http-equiv="expires" content="0" />
		<link href="<s:url value='/css/sh_css.css'/>" rel="stylesheet" type="text/css" />
		<script src="<s:url value='/js/jqui/jquery/jquery-1.5.2.min.js'/>" type="text/javascript">
        </script>
		<script type="text/javascript">
			if (window != top) { 
				top.location.href = location.href;  
			}
		
			$(document).ready(function(){
				$("#verifypic").click(function(){
	         		$("#validateCode").attr("src","<s:url value='/common/validatecode.jsp'/>?" + Math.random());
				});
				
				//密码显示
				var password=$("#password").val();
				if(password==''){
					$("#coverPass").css("display","block");
					$("#password").css("display","none");
				}else{
					$("#coverPass").css("display","none");
					$("#password").css("display","block");
				}
				$("#coverPass").focus(function(){
					$(this).css("display","none");
					$("#password").css("display","block");
					$("#password").focus();
				});
				$("#password").blur(function(){
					if($(this).val()==''){
						$("#coverPass").css("display","block");
						$(this).css("display","none");
					}
				});
			});
			
			function formSubmit() {
					var userName = $("#userName").val();
			    	var password = $("#password").val();
			    	var code = $("#code").val();
			    	if(userName=null || userName==''||userName=='用户名'){
			    		alert('用户名不能为空!');
			    		$("#userName").focus();
			    		return false;
			    	}else if(password==null || password==''||password=='密码'){
			    		alert('密码不能为空!');
			    		$("#password").focus();
			    		return false;
			    	}else if(code==null || code==''||code=='密码'){
			    		alert('验证码不能为空!');
			    		$("#code").focus();
			    		return false;
			    	}
			    	this.loginForm.submit();
				}
				
				//按回车提交 
				function keydown(e){ 
					var explorer = window.navigator.userAgent ;
					//ie //Chrome
					if (explorer.indexOf("MSIE") >= 0||explorer.indexOf("Chrome") >= 0) {
						if (event.keyCode == 13)   
					       {    
					          formSubmit();      
					       }    
					}
					//firefox 
					else if (explorer.indexOf("Firefox") >= 0) {
						if (e.which== 13)   
					       {    
					           formSubmit();  
					       }    
					}
				} 
				document.onkeydown=keydown;

		</script>
		<style type="">
.bg{
	padding: 0px;
	margin: 0px;
	background-color: #f2f2f2;
}
#login_top{
	text-align: center;
}
#login_top img{
	width: 100%;
}
#login_main{
	background-color: #f2f2f2;
	height: 453px;
}
.login_input{
	background-image: url("image/login_input_bg.png");
	width: 328px;
	height: 413px;
	margin-top: 40px;
	margin-left: 80px;
}
#login_input_top{
	text-align: center;
	font-size: 20px;
	font-weight:bolder;
	padding-top: 30px;
}

.login_input_text{
	width:270px;
	height: 53px; 
	border: 0px;
	background-image: url("image/login_input.png");
	margin-top: 10px;
	margin-left: 20px;
	background-color: #f2f2f2;
	font-size: 18px;
	color: #b2b2b2;
	
}
.login_rand_code{
	width: 100px;
	height: 48px;
	border: 0px;
	background-color: #f2f2f2;
	background-image: url("image/login_rand_code.png");
	margin-left: 20px;
	margin-top: 15px;
	font-size: 18px;
	color: #b2b2b2;
	
}
#login_rand_code img{
	padding-top: 19px;
}
.login_button{
	width: 272px;
	height: 54px;
	border: 0px;
	background-color: #f2f2f2;
	background-image: url("image/login_button.png");
	margin-left: 20px;
	margin-top: 15px;
	font-size: 20px;
}
#login_main_right{
    margin-left: 612px;
    margin-top: -281px;
    width: 99px;
}
#login_icon_text{
    font-size: 22px;
    font-weight: bold;
    margin-left: 112px;
    margin-top: -103px;
    width: 260px;
}
#login_bottom{
	text-align: center;
}
#login_bottom img{
	width:100%;
}
		</style>
	</head>
	<body>
	<div class="bg">
		<div id="login_top">
			<img src="image/login_top.png" alt="" />
		</div>
		<div id="login_main">
			<form id="loginForm" action="<s:url value="/login!login.ac"/>"  method="post" >
			
			<div class="login_input">
				<div id="login_input_top">
					<span>管理员登陆</span>
					<p class="wrong">
								<c:if test="${param.err=='timeOut'}">
									登录超时，请重新登录
								</c:if>
								<c:if test="${param.err!='timeOut'}">
									${requestScope.error}
								</c:if>
								</p>
				</div>
<%--				登陆名输入框--%>
				<div id="login_input_name">
					<input class="login_input_text" name="userName" id="userName" type="text" 
					onfocus="if(this.value=='用户名') {this.value='';}this.style.color='#333';"
					onblur="if(this.value=='') {this.value='用户名';this.style.color='#b2b2b2';}"
					<s:if test="userName==null">value="用户名"</s:if>
					<s:else>value="<s:property value="userName"/>"</s:else> />
					<input type="text" style="display: none;" id="userName2" />
				</div>
<%--				密码输入框--%>
				<div >
<%--					<input class="login_input_text" name="password" id="password" type="password" />--%>
					
					
					<input type="text" value="密码" style="color: #b2b2b2;"
					class="login_input_text" id="coverPass" autocomplete="off"/>
					<input name="password" class="login_input_text" id="password"
					type="password" style="display: none"
					onfocus="if(this.value=='') {this.style.color='#333';}"
					value="<s:property value="password"/>" autocomplete="off" />
				</div>
				<div id="login_rand_code">
					<input type="text" class="login_rand_code" name="code" id="code"
					onfocus="if(this.value=='验证码') {this.value='';}this.style.color='#333';"
					onblur="if(this.value=='') {this.value='验证码';this.style.color='#b2b2b2';}"
					value="验证码" />
					
					<img src="<s:url value="/common/validatecode.jsp" />"
					id="validateCode" border="0" width="94" height="41"
					id="verifypic" align="top" title="刷新验证码" alt="验证码"
					style="cursor: pointer;" />
					
				</div>
				
				<div id="login_button">
					<input type="button" onclick="formSubmit()" class="login_button" value="登录"/>
				</div>
			</div>
			</form>
			
			<!-- 
			<div id="login_main_right">
				<div id="login_icon"><img src="image/login_icon.png"/></div>
				<div id="login_icon_text">
					<span style="font-size: 40px;">RICHER</span><br/>
					<span>瑞 银 金 融</span><br/>
					<span>便捷支付 &nbsp;&nbsp;&nbsp;&nbsp;尽在掌握</span>
					
					
				</div>
			</div>
			 -->
		</div>
		
		<div id="login_bottom">
		   <img src="image/login_bottom.png" alt="" />
		</div>
<%--		<div class="sh_top">--%>
<%--			<div class="sht_logo">--%>
<%--				<a href="#"><img src="<s:url value='/image/login_02.jpg'/>"--%>
<%--						width="690" height="74" /> </a>--%>
<%--			</div>--%>
<%--		</div>--%>
<%--		<div class="sh_main">--%>
<%--			<div class="sh_image">--%>
<%--				<div class="shm">--%>
<%--					<div class="sh_cont">--%>
<%--						<div class="shc_top"></div>--%>
<%--						<div class="shc_center">--%>
<%--							<div class="shcc_img">--%>
<%--								<img src="image/login_12.png" width="10" height="12" />--%>
<%--							</div>--%>
<%--							<div class="shcc_left">--%>
<%--								<img src="image/login_08.jpg" width="199" height="178" />--%>
<%--							</div>--%>
<%--							<div class="shcc_right">--%>
<%--								<p class="wrong">--%>
<%--								<c:if test="${param.err=='timeOut'}">--%>
<%--									登录超时，请重新登录--%>
<%--								</c:if>--%>
<%--								<c:if test="${param.err!='timeOut'}">--%>
<%--									${requestScope.error}--%>
<%--								</c:if>--%>
<%--								</p>--%>
<%--								<form id="loginForm" action="<s:url value="/login!login.ac"/>"--%>
<%--									method="post">--%>
<%--									<table border="0" cellspacing="0" cellpadding="0">--%>
<%--										<tr>--%>
<%--											<td colspan="3">--%>
<%--												<input name="userName" type="text" class="inputstyle2"--%>
<%--													id="userName"--%>
<%--													onfocus="if(this.value=='用户名') {this.value='';}this.style.color='#333';"--%>
<%--													onblur="if(this.value=='') {this.value='用户名';this.style.color='#b2b2b2';}"--%>
<%--													<s:if test="userName==null">value="用户名"</s:if>--%>
<%--													<s:else>value="<s:property value="userName"/>"</s:else> />--%>
<%--													<input type="text" style="display: none;" id="userName2" />--%>
<%--											</td>--%>
<%--										</tr>--%>
<%--										<tr>--%>
<%--											<td colspan="3">--%>
<%--												<input type="text" value="密码" style="color: #b2b2b2;"--%>
<%--													class="inputstyle3" id="coverPass" autocomplete="off"/>--%>
<%--												<input name="password" class="inputstyle3" id="password"--%>
<%--													type="password" style="display: none"--%>
<%--													onfocus="if(this.value=='') {this.style.color='#333';}"--%>
<%--													value="<s:property value="password"/>" autocomplete="off" />--%>
<%--											</td>--%>
<%--										</tr>--%>
<%--										<tr>--%>
<%--											<td width="95">--%>
<%--												<input type="text" class="inputstyle4" name="code" id="code"--%>
<%--													onfocus="if(this.value=='验证码') {this.value='';}this.style.color='#333';"--%>
<%--													onblur="if(this.value=='') {this.value='验证码';this.style.color='#b2b2b2';}"--%>
<%--													value="验证码" />--%>
<%--											</td>--%>
<%--											<td width="104">--%>
<%--												<img src="<s:url value="/common/validatecode.jsp" />"--%>
<%--													id="validateCode" border="0" width="94" height="41"--%>
<%--													id="verifypic" align="top" title="刷新验证码" alt="验证码"--%>
<%--													style="cursor: pointer;" />--%>
<%--											</td>--%>
<%--											<td width="131">--%>
<%--												<a id="verifypic" style="cursor: pointer;">看不清,换一张？</a>--%>
<%--											</td>--%>
<%--										</tr>--%>
<%--										<tr>--%>
<%--											<td colspan="3">--%>
<%--												<img src="<s:url value='/image/login_26.jpg'/>" width="274"--%>
<%--													onclick="formSubmit();" height="42" />--%>
<%--											</td>--%>
<%--										</tr>--%>
<%--										<tr>--%>
<%--											<td colspan="3">--%>
<%--												<a href="#">忘记密码？</a>--%>
<%--											</td>--%>
<%--										</tr>--%>
<%--									</table>--%>
<%--								</form>--%>
<%--							</div>--%>
<%--							<div class="clear"></div>--%>
<%--						</div>--%>
<%--						<div class="shc_bottom"></div>--%>
<%--						<div class="shc_text">--%>
<%--							©2011 ByPay.cn 沪ICP备10214197号--%>
<%--						</div>--%>
<%--					</div>--%>
<%--					<div class="clear"></div>--%>
<%--				</div>--%>
<%--			</div>--%>
<%--		</div>--%>
<%--		<div class="sh_footer">--%>
<%--			<a href="http://www.bypay.cn/bypay/aboutus.html" target="_blank">关于我们</a>|--%>
<%--			<a href="http://www.bypay.cn/bypay/news.html" target="_blank">新闻中心</a>|--%>
<%--			<a href="http://www.bypay.cn/bypay/partner.html" target="_blank">合作伙伴</a>|--%>
<%--			<a href="http://www.bypay.cn/bypay/product.html" target="_blank">产品中心</a>|--%>
<%--			<a href="http://www.bypay.cn/bypay/job.html" target="_blank">诚聘英才</a>|--%>
<%--			<a href="http://www.bypay.cn/bypay/contact.html" target="_blank">联系百付</a>--%>
<%--		</div>--%>
	</div>
	</body>
</html>