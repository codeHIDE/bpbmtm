<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="initial-scale=1, maximum-scale=3, minimum-scale=1, user-scalable=no">
		<title>用户注册</title>
		<link href="images/bootstrap.min.css" rel="stylesheet">
		<link href="images/main.css" rel="stylesheet">
		<link href="images/enter.css" rel="stylesheet">
		<script src="images/jquery.min.js"></script>
		<script src="images/bootstrap.min.js"></script>
		<script src="images/jquery.particleground.min.js"></script>
		<script type="text/javascript">
		 $(document).ready(function () {
        var intro = $('.intro');
        $('#particles').particleground({
            dotColor: 'rgba(52, 152, 219, 0.36)',
            lineColor: 'rgba(52, 152, 219, 0.86)',
            density: 130000,
            proximity: 500,
            lineWidth: 0.2
        });
        intro.css({
            'margin-top': -(intro.height() / 2 + 100)
        });
    });
   function checkValue(){
				 var  re = /^1\d{10}$/;
				 var userName =$("#username").val();
				 var password =$("#password").val();
				 var passwordAgain =$("#passwordAgain").val();
				 if(userName!=""&&!re.test(userName)) {
					alert("手机号不正确！");
					$("#userName").val("");  
					$("#userName").focus();
					return false;
			 	 }
			 	 if(password!=passwordAgain){
			 	 alert("两次输入密码不同！");
					$("#password").val("");  
					$("#password").focus();
					return false;
			 	 }
			 	 return true;
			
			}
		
		   function sub(){
		   if(checkValue()){
    		var formData=
				{
					userName : $("#username").val(),
					invitePhone : $("#invitePhone").val(),
					password : $("#password").val(),
					passwordAgain : $("#passwordAgain").val(),
					checkCode : $("#checkCode").val()
				};
			var url = "<s:url value="/member!invite.ac"/>";
   			$.post(url,formData,function(data){
                    alert(data);
    			},"text");
    		}
    	}
    	
    	function countDown(obj,second){
			// 如果秒数还是大于0，则表示倒计时还没结束
			if(second>=0){
			// 获取默认按钮上的文字
			if(typeof buttonDefaultValue === 'undefined' ){
			buttonDefaultValue = obj.defaultValue;
			}
			// 按钮置为不可点击状态
			obj.disabled = true; 
			// 按钮里的内容呈现倒计时状态
			obj.value = buttonDefaultValue+'('+second+')';
			// 时间减一
			second--;
			// 一秒后重复执行
			setTimeout(function(){
			countDown(obj,second);},1000);
			// 否则，按钮重置为初始状态
			}else{
			// 按钮置未可点击状态
			obj.disabled = false; 
			// 按钮里的内容恢复初始状态
			obj.value = buttonDefaultValue;
			} 
		}

    	function getCode(obj){
    		 if(checkValue()){
    		 countDown(obj,60);
    		var formData=
				{
					userName : $("#username").val(),
					invitePhone : $("#invitePhone").val(),
					password : $("#password").val(),
					passwordAgain : $("#passwordAgain").val()
				};
			var url = "<s:url value="/member!getCode.ac"/>";
   			$.post(url,formData,function(data){
    			},"text");
    		}
    	}
</script>
</head>
<body>
<div id="particles">
   <div class="intro" style="margin-top: -256.5px;">
    <div class="container">
      <div class="row" style="padding:30px 0;">
        <div class="col-md-3 col-centered tac"> <img src="images/108.png" alt="logo"> </div>
      </div>
    </div>
    <div class="container">
      <div class="row">
        <div class="col-md-3 col-sm-8 col-centered">
          <form method="post" id="register-form" autocomplete="off" action="/" class="nice-validator n-default" novalidate>
            <div class="form-group">
              <input type="text" class="form-control" id="username" name="username"  placeholder="手机号" autocomplete="off" aria-required="true" data-tip="11位手机号"  />
            </div>
            <div class="form-group">
              <input type="password" class="form-control" id="password" name="password" placeholder="密码" aria-required="true" data-tip="请设置您的密码" />
            </div>
            <div class="form-group">
              <input type="password" class="form-control" id="passwordAgain" name="passwordAgain" placeholder="再次输入密码" aria-required="true" data-tip="请再输入一次密码" />
            </div>
              <div class="form-group">
              <input type="text" class="form-control" id="checkCode" name="checkCode" placeholder="验证码"  aria-required="true" />
            	</div>
            <div id="validator-tips" class="validator-tips"></div>
          	<input type = "hidden"  id ="invitePhone" name = "invitePhone" value="${param.invitePhone}" />
            <div class="form-center-button">
              <button id="reset" type="button" class="btn btn-primary btn-current"  onclick="getCode(this)">获取验证码</button>
			  <input type="button" value="注册" class="l-button"  id="btn" style="width:80px;" onclick="sub()" class="btn btn-primary btn-current"/>
          </form>
        </div>
      </div>
    </div>
    <div class="modal fade" id="myModal" tabindex="-1" style="text-align: left" role="dialog">
      <div class="modal-dialog" role="document">
        <div class="modal-content">

          <div class="modal-body" id="agreementContent"></div>
        </div>
      </div>
    </div>
  </div>
</div>

</body>
</html>