$( document ).ready( function () {
	$("#ac_cr_form").validate( {
		/*debug:true,*/
		rules: {
			username: {
				required: true,
				rangelength:([3,16]),
				remote:{
					type:"post",
					url:"/account/existValidate",
					data: {
                         username: function () {
                             return $("#ac_cr_username").val();
                         }
                     },
					dataFilter: function (data) {
                        if (data == "success") {
                            return true;
                        }
                        else {
                            return false;
                        }
                    }
				}
			},
			password: {
				required: true,
				isPwd:true
			},
			password2: {
				required: true,
				equalTo: "#ac_cr_password"
			},
			phone:{
				isTel: true,
				required: true,
			},
			name: {
				required: true,
				isChinese: true,
			},
			card: {
				required: true,
				isCard:true,
				remote:{
					type:"post",
					url:"/account/existValidate",
					data: {
                         card: function () {
                             return $("#ac_cr_card").val();
                         }
                     },
					dataFilter: function (data) {
                        if (data == "success") {
                            return true;
                        }
                        else {
                            return false;
                        }
                    }
				}
			},
			address: {
				required: true
			},
			email: {
				required: true,
				email: true,
				remote:{
					type:"post",
					url:"/account/existValidate",
					data: {
                         email: function () {
                             return $("#ac_cr_email").val();
                         }
                     },
					dataFilter: function (data) {
                        if (data == "success") {
                            return true;
                        }
                        else {
                            return false;
                        }
                    }
				}
			}
		},
		messages: {
			username: {
				required: "该项不能为空",
				rangelength:"请输入长度在 {0} 到 {1} 之间的字符串",
				remote:"该用户已存在",
			},
			password: {
				required: "该项不能为空",
				isPwd: "以字母开头，长度在6-12之间，只能包含字符、数字和下划线"
			},
			password2: {
				required: "该项不能为空",
				equalTo: "你的输入不相同"
			},
			phone:{
				required: "该项不能为空",
				isTel:"请正确填写您的联系方式"
			},
			name: {
				required: "该项不能为空",
				isChinese:"只能使用汉字"
			},
			card: {
				required: "该项不能为空",
				remote:"该用户已存在",
			},
			address: {
				required: "该项不能为空"
			},
			email: {
				required: "该项不能为空",
				email: "请输入正确的email",
				remote:"该用户已存在",
			}
		},
		success: function(label) {
			label.text("ok!").addClass("success");
		},
		errorElement: "span",
		errorPlacement: function ( error, element ) {
			// Add the `help-block` class to the error element
			error.addClass( "help-block" );
			if ( element.prop( "type" ) === "checkbox" ) {
				error.insertAfter( element.parent( "label" ) );
				error.appendTo(element.parent().parent().parent().parent());
			} else {
				error.insertAfter( element );
				error.appendTo(element.parent().parent());
			}
		},
		/*高亮显示*/
		highlight: function ( element, errorClass, validClass ) {
			$( element ).parents( ".col-sm-5" ).addClass( "has-error" ).removeClass( "has-success" );
		},
		/*反高亮显示*/
		unhighlight: function (element, errorClass, validClass) {
			$( element ).parents( ".col-sm-5" ).addClass( "has-success" ).removeClass( "has-error" );
		},
		submitHandler:function(form){
			/*alert("提交事件!");*/   
			$.ajax({
				url:"/account/save",
		    	type:"post",
				data:$('#ac_cr_form').serialize(),
				success:function(dataJson){
	                if(dataJson == "success" ){
	                	var result = confirm("您的账号尚未激活，请问是否需要现在去激活");
	                	if(result){
	                		var uurl = $("#ac_cr_email").val();
	                        uurl = gotoEmail(uurl);
	                        if (uurl != "") {
	                        	/*window.open("http://"+uurl);*/
	                        	document.location.href="http://"+uurl;
	                        } else {
	                            alert("抱歉!未找到对应的邮箱登录地址，请自己登录邮箱查看邮件！");
	                        }
	                	}
	                }else{
	                    alert("出错了");
	                }
	              }
		      }); 
		    },
	} );
	
		/*window.setTimeout(function(){alert("Hello")},3000);*/
	$('#myModal').on('shown.bs.modal', function () {
		var time = 20;
		$("#ac_cr_close").text("("+(time)+")");
		var set=setInterval(function(){
			$("#ac_cr_close").text("("+(--time)+")");
		}, 1000);
		var out = setTimeout(function(){
			clearInterval(set);
			$("#ac_cr_close").addClass("btn btn-primary");
			$("#ac_cr_close").removeAttr("disabled");
			$("#ac_cr_close").text("同意");
		}, 20000);
		$('#myModal').on('hide.bs.modal', function () {
			clearInterval(set);
			clearTimeout(out);
		})
	})
	
	$("#ac_cr_close").click(function(){
		$("#ac_cr_submit").removeClass("disabled");
		$("#ac_cr_submit").removeAttr("disabled");
		$("#ac_cr_agree").addClass("danger");
	});
	
	/*$("#ac_cr_agree").click(function(){
		alert(123);
		if($("#ac_cr_agree")){
			
		}
	});*/
	
	function gotoEmail($mail) {
	    $t = $mail.split('@')[1];
	    $t = $t.toLowerCase();
	    if ($t == '163.com') {
	        return 'mail.163.com';
	    } else if ($t == 'vip.163.com') {
	        return 'vip.163.com';
	    } else if ($t == '126.com') {
	        return 'mail.126.com';
	    } else if ($t == 'qq.com' || $t == 'vip.qq.com' || $t == 'foxmail.com') {
	        return 'mail.qq.com';
	    } else if ($t == 'gmail.com') {
	        return 'mail.google.com';
	    } else if ($t == 'sohu.com') {
	        return 'mail.sohu.com';
	    } else if ($t == 'tom.com') {
	        return 'mail.tom.com';
	    } else if ($t == 'vip.sina.com') {
	        return 'vip.sina.com';
	    } else if ($t == 'sina.com.cn' || $t == 'sina.com') {
	        return 'mail.sina.com.cn';
	    } else if ($t == 'tom.com') {
	        return 'mail.tom.com';
	    } else if ($t == 'yahoo.com.cn' || $t == 'yahoo.cn') {
	        return 'mail.cn.yahoo.com';
	    } else if ($t == 'tom.com') {
	        return 'mail.tom.com';
	    } else if ($t == 'yeah.net') {
	        return 'www.yeah.net';
	    } else if ($t == '21cn.com') {
	        return 'mail.21cn.com';
	    } else if ($t == 'hotmail.com') {
	        return 'www.hotmail.com';
	    } else if ($t == 'sogou.com') {
	        return 'mail.sogou.com';
	    } else if ($t == '188.com') {
	        return 'www.188.com';
	    } else if ($t == '139.com') {
	        return 'mail.10086.cn';
	    } else if ($t == '189.cn') {
	        return 'webmail15.189.cn/webmail';
	    } else if ($t == 'wo.com.cn') {
	        return 'mail.wo.com.cn/smsmail';
	    } else if ($t == '139.com') {
	        return 'mail.10086.cn';
	    } else {
	        return '';
	    }
	}
} );

