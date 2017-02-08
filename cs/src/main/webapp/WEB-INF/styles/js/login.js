function forgetPassword(){
		var username = $("#lo_username").val();
		if(username != null&&username != ""){
			document.location.href="/account/retrieve?username="+username;
		}else{
			alert("请输入用户名");
		}
	}
	
	$("#lo_submit").click(function(){
	    $.ajax({
	        url:'/account/register',
	        type:"POST",
	        data:$('#lo_form').serialize(),
	        success: function(data) {
	            if(data == "success"){
	            	document.location.href="/account/success";
	            }else if (data == "unactivation") {
	            	alert("该账号尚未激活，请前往相应邮箱进行激活！");
				}else{
	            	alert("用户名不存在或密码错误");
	            }
	        },
	        error:function(){
	        	alert("出错了");
	        }
	    });
	});