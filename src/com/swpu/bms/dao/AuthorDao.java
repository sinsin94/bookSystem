package com.swpu.bms.dao;

import java.sql.Connection;

import java.sql.Statement;
import java.util.List;

import com.swpu.bms.entity.Author_Book;
import com.swpu.bms.entity.Authors;
import com.swpu.bms.entity.Books;
import com.swpu.bms.utils.GetSql;
public class AuthorDao extends BaseDao<Authors>{
	
	//这里删除会涉及到多表操作（涉及事务）
	//1.通过作者id 找到author_book 表中 作者与书的关系（list集合）
	
	//2.删除author_book表中关于该作者的信息
	
	//3.删除authors表中的作者
	
	//4.通过author_book表中的isbn删除books表中的记录  (多表联合查询)
	//DELETE books FROM books LEFT JOIN author_book ON books.isbn=author_book.isbn WHERE author_book.isbn IS NULL
	
	//author 里面包含了作者的id
	public int deleteAuthor(Authors author)
	{
		
		Author_Book author_Book=new Author_Book();
		Author_Book_Dao author_Book_Dao=new Author_Book_Dao();

		author_Book.setAuthorID(author.getId());
		//获取数据库连接
		Connection conn=getConnection();
		GetSql<Author_Book>abGetSql=new GetSql<Author_Book>();
		GetSql<Authors>aGetSql=new GetSql<Authors>();
		GetSql<Books>bGetSql=new GetSql<Books>();
		try {
			//查找author-book中关于此作者的信息
			List<Author_Book>result=author_Book_Dao.Find(author_Book,author_Book);
			
			
			conn.setAutoCommit(false);  
			Statement statement=conn.createStatement();
			
			// 添加删除author_book表中关于该作者的信息的sql
			if(result!=null)
			{
				for(Author_Book ab:result)
				{
					Authors authors=new Authors();
					authors.setId(ab.getAuthorID());
					
					
					
					statement.addBatch(abGetSql.GetDeleteSql(ab));
					
					
					
					
				}
			}
			
			if(result!=null)
			{
				//添加删除authors表中的作者的sql
				for(Author_Book ab:result)
				{
					Authors authors=new Authors();
					authors.setId(ab.getAuthorID());
					
					statement.addBatch(aGetSql.GetDeleteSql(authors));
					
					
				}
			}
			else
			{
				statement.addBatch(aGetSql.GetDeleteSql(author));
			}
			
			
			
			//通过author_book表中的isbn删除books表中的记录

			String sql="DELETE books FROM books LEFT JOIN author_book ON books.isbn=author_book.isbn WHERE author_book.isbn IS NULL";
			statement.addBatch(sql);
			statement.executeBatch(); //批量执行   
            conn.commit();//提交事务  
			return 1;
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			try {
				conn.rollback();//进行事务回滚  
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			e.printStackTrace();
		}
		finally {
			
			try {
				closeResouce();  //关闭资源
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		
		return 0;
	}

}
