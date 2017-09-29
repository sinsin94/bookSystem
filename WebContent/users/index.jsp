<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html>
<head>
<title>图书管理信息系统-作者管理</title>
<link rel=stylesheet href="../inc/main.css" type="text/css">

 <link rel="stylesheet" type="text/css" href="../css/user_index.css"  />
 <link rel="stylesheet" type="text/css" href="../css/pagination.css"  />
 <script type="text/javascript" src="../js/jquery-1.11.3.min.js"></script>
  <script type="text/javascript" src="../js/jquery.pagination.js"></script>
 <script type="text/javascript" src="../js/user_index.js"></script>

<style type="text/css">
</style>

</head>
<body>
	<!-- 引入head.jsp文件 -->
	<%@include file="/inc/head.jsp"%>
	<table class="add_user_table" align="center" width="980">
		<tr>
			<td align="right">
				<a class="add_user" href="#">添加用户</a>
			</td>
		</tr>
	</table>
	<table align="center" width="980" class="head" cellpadding="0" cellspacing="0">
		<caption>用户列表</caption>
		<tr>
			<th width="150">用户ID</th>
			<th width="300">登录帐户</th>
			<th width="300">真实姓名</th>
			<th width="230">操作</th>
		</tr>
		
	</table>
	<table class="user_content" style="text-align:center"  align="center" width="980" border='1'cellspacing="0" cellpadding="0">
		
	
		<tr>
			<td width="150px" >123</td>
			<td width="300px">123</td>
			<td width="300px">123</td>
			<td width="230px"><a class="delete" style="color:blue ;margin-left:10px;margin-right:18px">删除</a>  <a style="color:blue;margin-left:20px">修改</a></td>

		</tr>	
   </table>
   
       <div class="total" >
        <div class="denglu">
            <p class="login_head">信息修改</p>
            <div class="clear"></div>
        
            <div class="entry">
                
               <span>用户ID号：</span>
               <input type="text" id="userid" class="num-txt" readonly>
                
            </div>
            <div class="entry">
            <span>用户名字：</span>
                <input type="text" id="username" class="psd-txt" readonly >
            </div>
                <div class="entry" >
                <span>真实名字：</span>
                <input type="text" id="realname" class="psd-txt"  placeholder="请确认密码">
            </div>
                 <div class="entry">
                 <span>修改密码：</span>
                <input  id="password" type="password" class="num-txt" placeholder="请输入密码">
            </div>
            <div class="entry">
            	<span>确认密码：</span>
                <input type="password" id="password2" class="psd-txt" placeholder="请确认密码">
            </div>
         
            
           
            <div class="clear"></div>
           
            <a href="#" class="bb submit">提交</a>
            </div>
    </div>
    
     <div class="total_add" >
        <div class="denglu1">
            <p class="login_head1" >添加用户</p>
            <div class="clear1"></div>

            <div class="entry1">
            <span>用户名字：</span>
                <input type="text" id="username_add" class="psd-txt1" placeholder="请输入用户名" >
            </div>
                <div class="entry1" >
                <span>真实名字：</span>
                <input type="text" id="realname_add" class="psd-txt1"  placeholder="请输入真实姓名">
            </div>
                 <div class="entry1">
                 <span>修改密码：</span>
                <input type="password" id="password_add" class="num-txt1" placeholder="请输入密码">
            </div>
            <div class="entry1">
            	<span>确认密码：</span>
                <input type="password" id="password2_add" class="psd-txt1" placeholder="请确认密码">
            </div>
         
            
           
            <div class="clear1"></div>
           
            <a href="#" class="bb1 submit_add">提交</a>
        </div>
    </div>
    
    <div id='pagination4' class='pagination'></div>
            <input type="text" id="totalpage1" style="display:none">
</body>
</html>