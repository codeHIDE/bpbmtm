$(document).ready(function(){
	$("#goaler1 tr:even").addClass("tr1");
	$("#goaler1 tr:odd").addClass("tr2");
	
	$("#goaler1 tr").hover(
		function(){
			$(this).addClass("tr3");
		},
		function(){
			$(this).removeClass("tr3");
		}
	);
	
	$(".btn").hover(
		function(){
			$(this).removeClass("btn");
			$(this).addClass("h_btn");
		},
		function(){
			$(this).removeClass("h_btn");
			$(this).addClass("btn");
		}
	);
	
	$(".btn1").hover(
		function(){
			$(this).removeClass("btn1");
			$(this).addClass("h_btn1");
		},
		function(){
			$(this).removeClass("h_btn1");
			$(this).addClass("btn1");
		}
	);
	
});
function getRootPath()
{
	// 获取当前网址，如： http://localhost:8080/bmtm/login.html
	var wholePath = window.document.location.href;

	// 获取主机地址之后的目录，如： /bmtm/login.html
	var suffixPath = window.document.location.pathname;

	var pos = wholePath.indexOf(suffixPath);
	// 获取主机地址，如： http://localhost:8080
	var hostPath = wholePath.substring(0, pos);

	// 获取带"/"的项目名，如：/bmtm
	var projectName = suffixPath.substring(0,
			suffixPath.substr(1).indexOf('/') + 1);

	return (hostPath + projectName);
}
//清除session
/*
function clearSession(){
	window.location.href="common/relogin.jsp";
}
setInterval("clearSession()",1000*20*60);
*/



