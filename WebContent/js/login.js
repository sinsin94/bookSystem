
//登陆
$(document).ready(function(){
   
	//生产成验证码
	 createCode();
	 
	
	 $(".psd-txt").focus(function(){
		 var msg_user = "";
			var setFocused = false;
			if ($(".num-txt").val()=='') {
				msg_user += "帐号不能为空！\n";
				alert(msg_user);
				$(".num-txt").focus();
				setFocused = true;
				 
			} 
		});
	
	 
	 
	
	
	 $(".check-txt").focus(function(){
		 var msg_psw = "";
		 var setFocused = false;
			if ($(".psd-txt").val()=='') {
				msg_psw += "密码不能为空！\n";
				alert(msg_psw);
				$(".psd-txt").focus();
				setFocused = true;
			
			} 
		});
		
		
	 $(".login").click(function(){
//		var msg = "";
//		var setFocused = false;
//		if ($(".num-txt").val()=='') {
//			msg += "帐号不能为空！\n";
//			$(".num-txt").focus();
//			setFocused = true;
//			 
//		}
//	
//		if ($(".psd-txt").val() == "") {
//			msg += "密码不能为空！\n";
//			if (!setFocused) {
//				$(".psd-txt").focus();
//				setFocused = true;
//			}
//		}
		
		if($(".check-txt").val() == ""){
			var msg="";
            msg += "验证码不能为空！\n";
            $(".check-txt").focus();
            alert(msg);
            return false;
        }else if($('.check-txt').val().toUpperCase() != $('#checkCode').text().toUpperCase()){
            
		      msg += "验证码输入错误，请重新输入！！\n";
            $('.check-txt').focus();
            alert(msg);
            createCode();
            return false;
        }
	

		login() ;
		return true;
		
	   });
	
	
	 $(".code").click(function(){
	    createCode();
	 });
});





//生成验证码
var code;
function createCode() 
{
 code = "";
 var codeLength = 4; //验证码的长度
 var checkCode = document.getElementById("checkCode");
 var codeChars = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 
      'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
      'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'); //所有候选组成验证码的字符，当然也可以用中文的
 for(var i = 0; i < codeLength; i++) 
 {
  var charNum = Math.floor(Math.random() * 52);
  code += codeChars[charNum];
 }
 if(checkCode) 
 {
  checkCode.className = "code";
  checkCode.innerHTML = code;
 }
}

function login() 
{
  
  var username =$(".num-txt").val() ;
  var password=$(".psd-txt").val() ;
  
  $.ajax({
		url:'userAction.do',
		data:{
			"action":"login",
			"userName":username,
			"password":password,
		},
		success:function(data){
			if(data.msg==100){
				window.location.href="./users/index.jsp";
				
			}
			else
				{
					alert("用户非法");
				}
		},
		dataType:'json'
	});
}

