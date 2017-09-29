<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>图书管理信息系统-图书管理</title>
<link rel=stylesheet href="../inc/main.css" type="text/css">

<link rel=stylesheet href="../css/book.css" type="text/css">
 <script type="text/javascript" src="../js/jquery-1.11.3.min.js"></script>
 <script type="text/javascript" src="../js/book.js"></script>

<style type="text/css">
</style>
</head>
<body>
	<%@include file="/inc/head.jsp"%>
	
		


	<table class="content_book"  align="center" width="980">
		<caption>图书列表</caption>
		<tr>
			<td colspan="3">
				<a class="add" href="#">+添加新图书</a>
			</td>
			<td style="text-align:right">
			   <span>图书ISBN：</span>	
			</td>
			<td >			
				<input type="text" id="book_isbn_seach"   placeholder="请输入isbn">
			</td>
			<td  style="text-align:right">
			<span>书名：</span>
			</td>
			<td>				
				<input type="text" id="book_name_seach"   placeholder="请输入书名">
			</td>
		    <td style="float:right">
			<a id="search_book" >提交查询</a>
			</td>

		
		</tr>
		<tr>
			<th width="50">序号</th>
			<th width="130">图书ISBN</th>
			<th width="300">书名</th>
			<th width="150">出版社名称</th>
			<th width="70">价格</th>
			<th width="70">版本号</th>
			<th width="80">出版年份</th>
			<th width="130">操作</th>
		</tr>
		</table>

	  <div class="total" >
        <div class="denglu">
            <p class="login_head">信息修改</p>
            <div class="clear"></div>
        
            <div class="entry">
              
               <span>图书isbn：</span>
               <input type="text" id="isbn" class="num-txt"  placeholder="请输入图书isbn">
                
            </div>
            <div class="entry">
            <span>图书名字：</span>
                <input type="text" id="book_name" class="psd-txt"  placeholder="请输入图书名字" >
            </div>
         
            <div class="entry">
            	<span>版本号码：</span>
                <input type="text" id="version" class="psd-txt" placeholder="请输入版本号码">
            </div>
            <div class="entry">
            	<span>出版社ID：</span>
                <input type="text" id="publisherId_update" class="psd-txt" placeholder="请输入版本号码">
            </div>
            
            <div class="entry" >
            	<span>作者ID：</span>
                <input type="text" id="authorID_update" class="psd-txt" placeholder="请输入版本号码">
            </div>
            
            
           <div class="entry">
            	<span>出版年份：</span>
            	 <input type="date" id="year" class="psd-txt" placeholder="请输入出版年份"> 
            </div>
                          <div class="entry">
                 <span>图书价格：</span>
                <input  id="price" type="text" class="num-txt" placeholder="请输入图书价格">
            </div>
             <div class="entry" >
                <span>出版社名：</span>
                <select class="book_publisher_update" >
                <option>1</option>
                <option>2</option>
                <option>3</option>
                <option>4</option>
                <option>5</option>
                </select>              
            </div>
          <div class="entry" >
                <span>作者名字：</span>
                <select class="book_authors_update" >
                <option>1</option>
                <option>2</option>
                <option>3</option>
                <option>4</option>
                <option>5</option>
                </select>              
            </div>
         
            
           
            <div class="clear"></div>
           
            <a href="#" class="bb submit">提交</a>
            </div>
    </div>	
		 
     	  <div class="total_add" >
        <div class="denglu1">
            <p class="login_head1">图书添加</p>
            <div class="clear1"></div>
        
            <div class="entry1">
              
               <span>图书isbn：</span>
               <input type="text" id="isbn1" class="num-txt"  placeholder="请输入图书isbn">
                
            </div>
            <div class="entry1">
            <span>图书名字：</span>
                <input type="text" id="book_name1" class="psd-txt"  placeholder="请输入图书名字" >
            </div>

            <div class="entry1">
            	<span>版本号码：</span>
                <input type="text" id="version1" class="psd-txt" placeholder="请输入版本号码">
            </div>
            <div class="entry1" style="display:none">
            	<span>出版社ID：</span>
                <input type="text" id="publisherId_add" class="psd-txt" placeholder="请输入版本号码">
            </div>
            <div class="entry1">
                 <span>图书价格：</span>
                <input  id="price1" type="text" class="num-txt1" placeholder="请输入图书价格">
            </div>
           <div class="entry1">
            	<span>出版年份：</span>
                <input type="date" id="year1" class="psd-txt" placeholder="请输入出版年份">
            </div>

            <div class="entry1" >
                <span>出版社名：</span>
                <select class="book_publisher" >
     
                </select>              
            </div>
            <div class="entry1">
                 <span style="margin-left:18px">    **作者：</span>
                <select class="book_authors1" multiple="multiple" size="1">
                <option>1</option>
                <option>2</option>
                <option>3</option>
                <option>4</option>
                <option>5</option>
                </select>
            </div>
            
            <div class="entry1" style="display:none">
                 <span style="margin-left:18px">    **作者id：</span>
          		 <input  id="authors_add_id"  type="text" class="num-txt1" placeholder="请输入图书价格">
            </div>
            
            
         
            
           
            <div class="clear"></div>
           
            <a href="#" class="bb submit1">提交</a>
            </div>
    </div>	
</body>
</html>