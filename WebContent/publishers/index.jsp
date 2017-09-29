<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>图书管理信息系统-出版社管理</title>
<link rel=stylesheet href="../inc/main.css" type="text/css">
<link rel=stylesheet href="../css/publishers.css" type="text/css">
<script type="text/javascript" src="../js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="../js/publishers.js"></script>
<style type="text/css">
</style>
</head>
<body>
	<%@include file="/inc/head.jsp"%>
	<table class="add_publishers_table" align="center" width="980">
		<tr>
			<td align="right">
				<a href="#" class="add_authors">添加出版社</a>
			</td>
		</tr>
	</table>
	<table align="center" width="980" >
		<caption>出版社列表</caption>
		<tr>
			<th width="200">出版社ID</th>
			<th width="550">出版社名称</th>
			<th width="230">操作</th>
		</tr>
		
	</table>
	
	<table  class="user_content" style="text-align:center"  align="center" width="980" border='1'cellspacing="0" cellpadding="0">		
		<tr>
			<td width="200">出版社ID</td>
			<td width="550">出版社名称</td>
			<td width="230">操作</td>
		</tr>
		

	
				
		<tr>
			<td width="200">出版社ID</td>
			<td width="550">出版社名称</td>
			<td width="230">操作</td>
		</tr>
		
	</table>
	 <div class="total" >
        <div class="denglu">
            <p class="login_head">信息修改</p>
            <div class="clear"></div>
        
            
            <div class="entry">
            	<span>出版社ID号：</span>
                <input type="text" id="publishers_id" class="psd-txt" readonly >
            </div>
                <div class="entry" >
                <span>出版社名字：</span>
                <input type="text" id="publishers_name" class="psd-txt"  >
            </div>
          <div class="clear"></div>
           
            <a href="#" class="bb submit">提交</a>
            </div>
    </div>
    
     <div class="total_add" >
        <div class="denglu1">
            <p class="login_head1">添加出版社</p>
            <div class="clear1"></div>

            <div class="entry1">
            <span>出版社名：</span>
                <input type="text" id="username_add" class="psd-txt1"  >
            </div>     
            <div class="clear1"></div>     
            <a href="#" class="bb1 submit_add">提交</a>
        </div>
    </div>
</body>
</html>