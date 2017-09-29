package com.swpu.bms.dao;

import java.sql.Connection;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


import com.swpu.bms.entity.Author_Book;

import com.swpu.bms.entity.Books;
import com.swpu.bms.utils.GetSql;

public class BookDao extends BaseDao<Books> {

	
	public int UpdateBook(Books book,List<Author_Book>author_Books)
	{
		
		//1.更新books表
		//2.删除author_book表中关于该书的作者
		//3.插入该书的新作者
		
		BookDao bookDao=new BookDao();
		Author_Book_Dao author_Book_Dao=new Author_Book_Dao();
		//获取数据库连接
		Connection conn=getConnection();
		GetSql<Books> bGetSql= new GetSql<Books>();
		GetSql<Author_Book>abGetSql=new GetSql<Author_Book>();
		try {
			conn.setAutoCommit(false);
			Statement statement=conn.createStatement();
			
			statement.addBatch(bGetSql.GetUpdateByIDSql(book));
			
			//删除该书的所有作者
			if (author_Books.size() >= 1) {
				Author_Book author_Book = new Author_Book();

				author_Book.setIsbn(author_Books.get(0).getIsbn());

				statement.addBatch(abGetSql.GetDeleteSql(author_Book));
			}
			
			if(author_Books!=null)
			{
				//插入该书的新作者
				for(Author_Book author_Book2:author_Books)
				{
					statement.addBatch(abGetSql.getInsertSql(author_Book2));
				}
			}
			
			
			statement.executeBatch(); //批量执行   
            conn.commit();//提交事务  
			return 1;
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				conn.rollback(); //进行事务回滚  
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			try {
				closeResouce(); //关闭资源
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}  
		
		
		return 0;
	}
	public int DeleteBook(Books book,Author_Book author_Book)
	{
		//1.删除与该图书绑定的作者（author_book表中的数据）
		//2.删除图书（books表）
		
		
		BookDao bookDao=new BookDao();
		Author_Book_Dao author_Book_Dao=new Author_Book_Dao();
		//获取数据库连接
		Connection conn=getConnection();
		GetSql<Books> bGetSql= new GetSql<Books>();
		GetSql<Author_Book>abGetSql=new GetSql<Author_Book>();
		try {
			
			Statement statement=conn.createStatement();
			
			
			conn.setAutoCommit(false);
		
			
			//删除该书绑定的作者（author_book表）
			//System.out.println(abGetSql.GetDeleteSql(author_Book));
			statement.addBatch(abGetSql.GetDeleteSql(author_Book));
			
		//	System.out.println(bGetSql.GetDeleteSql(book));
			//删除图书
			statement.addBatch(bGetSql.GetDeleteSql(book));
			
			
			statement.executeBatch(); //批量执行   
            conn.commit();//提交事务  
			return 1;
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				conn.rollback(); //进行事务回滚  
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			try {
				closeResouce(); //关闭资源
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}  
		return 0;
	}
	
	public int InsertBook(Books book,List<Author_Book>author_Book)
	{
		//1.添加图书
		//2.添加图书和作者的关系
		GetSql<Books> bGetSql=new GetSql<Books>();
		GetSql<Author_Book> abGetSql=new GetSql<Author_Book>();
		try {
			Connection conn=getConnection();
			Statement statement=conn.createStatement();
			conn.setAutoCommit(false);
			
			//1.添加图书
			statement.addBatch(bGetSql.getInsertSql(book)); 
			
			//2.添加作者和图书关系
			
			for(Author_Book ab:author_Book)
			{
				statement.addBatch(abGetSql.getInsertSql(ab));
			}
			
			statement.executeBatch(); //批量执行   
            conn.commit();//提交事务  
			return 1;
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

}
