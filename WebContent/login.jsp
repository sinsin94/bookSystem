
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <link rel="stylesheet" type="text/css" href="css/index.css"  />
  <link type="text/css" href="css/animate.css" rel="stylesheet">
  <link type="text/css" href="css/style.css" rel="stylesheet">
  <link rel="stylesheet" href="http://dreamsky.github.io/main/blog/common/init.css">
  
  <script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>

  <script src="js/login.js"></script>
  <script src="js/jquery.fittext.js"></script>
  <script src="js/jquery.lettering.js"></script>
  <script src="js/highlight.min.js"></script>
  <script src="js/jquery.textillate.js"></script>
    <script src="js/text.js"></script>
<head>
<title>图书管理信息系统-登录</title>

</style>

<!-- <script type="text/javascript">
	function checkForm(myForm) {
		var msg = "";
		var setFocused = false;
		if (myForm.account.value == "") {
			msg += "帐号不能为空！\n";
			myForm.account.focus();
			setFocused = true;
		}

		if (myForm.password.value == "") {
			msg += "密码不能为空！\n";
			if (!setFocused) {
				myForm.password.focus();
				setFocused = true;
			}
		}

		if (msg != "") {
			alert(msg);
			return false;
		}

		return true;

	}
</script> -->

</head>
<body>
     
    <div class="jumbotron">
			<div class="container">
              <h2 class="glow in tlt" style="font-size:68.667px">图书管理信息系统</h2>
              <p class="tlt" data-in-effect="bounceInDown">
                 Books Management System.
              </p>
  		    </div>
    </div>
   
    <img  class="headbg" src="<%=request.getContextPath()%>/images/myhbg.jpg">   
    
    <div class="total">
        <div class="denglu">
            <p class="login_head">用户登录</p>
            <div class="clear"></div>
            <div class="entry">
                <input type="text" class="num-txt" placeholder="输入用户名字">
            </div>
            <div class="entry">
                <input type="password" class="psd-txt" placeholder="输入登录密码">
            </div>
            
            <div class="check">
                <P>
                <input type="text" class="check-txt" placeholder="输入验证码">
                 <b  id="checkCode" style="background-color:#b5b5b5;width: 110px;height: 40px;margin-left: 18px">Bmzk</b>
                 
                  </P>
            </div>
            <div class="clear"></div>
            <p class="mi_ma">忘记密码</p>
            <p class="mian_fei">免费注册</p>
            <a href="#" class="bb login">登录</a>
        </div>
    </div>
     
	<!-- 
	<form action="" name="form1" method="post"
		onsubmit="return checkForm(this);">
		<table align="center">
			<caption>用户登录</caption>
			
					<tr>
				<td>用户帐号</td>
				<td><input type="text" style="width: 150px" name="userName">
				</td>
			</tr>
			<tr>
				<td>用户密码</td>
				<td><input type="password" style="width: 150px" name="password">
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="登录">
					<input type="reset" value="清空"></td>
			</tr>
		</table>
	</form>
	 -->
</body>
</html>