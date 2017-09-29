<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>图书管理信息系统-登录</title>
<style type="text/css">
table {
	font-size: 12px;
}

table caption {
	font-size: 14px;
}
</style>

</head>
<%
				//login 测试
			//	session.setAttribute("action", "login");
				
			//	session.setAttribute("userName", "saf");
			//	session.setAttribute("password", "123");
				
				
				//deleteuser 测试
			//	session.setAttribute("action", "deleteuser");
			//	session.setAttribute("id", 4);
				
				
				//updateuser 测试
			//	session.setAttribute("action", "updateuser");
			//	session.setAttribute("id", "8");
			//	session.setAttribute("userName", "123123");
			//	session.setAttribute("password", "123123");
			//	session.setAttribute("realName", "enen");
				
				
				//showall 测试
				session.setAttribute("action", "showall");
				
			%>

<body>
	<table align="center">
		<tr>
			<td><img src="<%=request.getContextPath()%>/images/head.jpg">
			</td>
		</tr>
	</table>
	<form action="userAction.do" name="form1" method="post">
		<table align="center">

			
			<tr>
				<td colspan="2" align="center"><input type="submit" value="登录">
					<input type="reset" value="清空"></td>
			</tr>
			
		</table>
	</form>
</body>
</html>