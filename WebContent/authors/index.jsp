<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>图书管理信息系统-作者管理</title>
<link rel=stylesheet href="../inc/main.css" type="text/css">
<link rel=stylesheet href="../css/authors.css" type="text/css">
 <script type="text/javascript" src="../js/jquery-1.11.3.min.js"></script>
 <script type="text/javascript" src="../js/authors.js"></script>
<style type="text/css">
</style>
</head>
<body>
	<%@ include file="/inc/head.jsp"%>
	<table class="add_authors_table" align="center" width="980">
		<tr>
			<td align="right">
				<a href="#" class="add_authors">添加作者</a>
			</td>
		</tr>
	</table>
	<table align="center" width="980">
		<caption>作者列表</caption>
		<tr>
			<th width="150">作者ID</th>
			<th width="300">名</th>
			<th width="300">姓</th>
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
            <p class="login_head">作者信息修改</p>
            <div class="clear"></div>        
            <div class="entry">                
               <span>作者ID：</span>
               <input type="text" id="authors_id" class="num-txt" readonly>               
            </div>
            <div class="entry">
            <span>作者名：</span>
                <input type="text" id="authors_firstname" class="psd-txt"  >
            </div>
            <div class="entry" >
                <span>作者名：</span>
                <input type="text" id="authors_lastname" class="psd-txt"  >        
            <div class="clear"></div>
           
            <a href="#" class="bb submit">提交</a>
            </div>
            
    </div>
    </div>
    
     <div class="total_add" >
        <div class="denglu1">
            <p class="login_head1">添加作者</p>
            <div class="clear1"></div>

            <div class="entry1">
            <span>作者名：</span>
                <input type="text" id="firstname_add" class="psd-txt1"  >
            </div>
                <div class="entry1" >
                <span>作者姓：</span>
                <input type="text" id="lastname_add" class="psd-txt1"  >
            </div>
    
            <div class="clear1"></div>
           
            <a href="#" class="bb1 submit_add">提交</a>
        </div>
    </div>
</body>
</html>