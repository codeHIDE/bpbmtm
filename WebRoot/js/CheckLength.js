  function len(s,max,type,name) { 
	var l = 0; 
	var regu = null;
	var message = null;
	var b = document.getElementById(s);
	var c=b.value.replace(/(^\s*)|(\s*$)/g,'');
	if(b.value=='' || b.value==null ){
		alert(name+"不能为空");
		b.focus();
		return false;
	}else{
		if(type=='NS'){
			regu="^[0-9]{1,20}([\.]{0,1}[0-9]{1,2}){0,1}$";
			message="只能输入整数或者小数";
		}else if(type=='EN'){
			regu="^[0-9A-Za-z_]+$";
			message="只能输入英文、数字、下划线";
		}else if(type=='N'){
			regu="^[0-9]*$";
			message="只能输入数字";
		}else if(type=='E'){
			regu="^[A-Za-z ]+$";
			message="只能输入英文字母";
		}else if(type=='EandS'){
			regu = "^[a-zA-Z\u4e00-\u9fa5 ]+$";
			message="只能输入汉字或者英文字母";
		}else if(type == 'ID_CARD'){
			regu = "/^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/";
			message = "身份证号码格式错误";	
		}else if(type=='All'){
			
		}else{
			alert("出错");
			return false;
		}
	}
	var re = new RegExp(regu); 
	if(type=='All'){
		var a=c.split(""); 
		for (var i=0;i<a.length;i++) { 
			if (a[i].charCodeAt(0)<299) { 
				l++; 
			} else { 
				l+=3; 
			} 
		} 
	}else{
		if (re.test(c)) { 
			var a=c.split(""); 
			for (var i=0;i<a.length;i++) { 
				if (a[i].charCodeAt(0)<299) { 
					l++; 
				} else { 
					l+=3; 
				} 
			} 
		}else{ 
			b.value='';
			alert(name+message);
			b.focus();
			return false;
		} 
	}
	if(max!=null){
		if(l>max){
			alert(name+"输入长度过长");
			return false;
		}
	}
	return true;
}
  
  function length(s,max,type,name){
		var l = 0; 
		var regu = null;
		var message = null;
		var b = document.getElementById(s);
		var c=b.value.replace(/(^\s*)|(\s*$)/g,'');
		if(b.value=='' || b.value==null){
			return true;
		}
			if(type=='NS'){
				regu="^[0-9]{1,20}([\.]{0,1}[0-9]{1,2}){0,1}$";
				message="只能输入整数或者小数";
			}else if(type=='EN'){
				regu="^[0-9A-Za-z_]+$";
				message="只能输入英文、数字、下划线";
			}else if(type=='N'){
				regu="^[0-9]*$";
				message="只能输入数字";
			}else if(type=='E'){
				regu="^[A-Za-z ]+$";
				message="只能输入英文字母";
			}else if(type=='EandS'){
				regu = "^[a-zA-Z\u4e00-\u9fa5 ]+$";
				message="只能输入汉字或者英文字母";
			}else if(type == 'ID_CARD'){
				regu = "/^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/";
				message = "身份证号码格式错误";
			}else if(type=='All'){
				
			}else{
				alert("出错");
				return false;
			}
		var re = new RegExp(regu); 
		if(type=='All'){
			var a=c.split(""); 
			for (var i=0;i<a.length;i++) { 
				if (a[i].charCodeAt(0)<299) { 
					l++; 
				} else { 
					l+=3; 
				} 
			} 
		}else{
			if (re.test(c)) { 
				var a=c.split(""); 
				for (var i=0;i<a.length;i++) { 
					if (a[i].charCodeAt(0)<299) { 
						l++; 
					} else { 
						l+=3; 
					} 
				} 
			}else{ 
				b.value='';
				alert(name+message);
				b.focus();
				return false;
			} 
		}
		if(max!=null){
			if(l>max){
				alert(name+"输入长度过长");
				return false;
			}
		}
		return true;
	}
  
  //验证身份证号码
  function checkIdCard(){
	  
  }
  
  