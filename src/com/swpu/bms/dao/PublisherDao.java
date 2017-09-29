package com.swpu.bms.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.swpu.bms.entity.Author_Book;
import com.swpu.bms.entity.Books;
import com.swpu.bms.entity.Publishers;
import com.swpu.bms.utils.GetSql;

public class PublisherDao extends BaseDao<Publishers>{

	public int DeletePublisher(Publishers publishers){
		
		//1.删除author_book中books表的isbn
		//2 删除books 表中对应的出版社id
		//3 删除publsisher表中的数据
		Books books=new Books();
		BookDao bookDao=new BookDao();
		
		books.setPublisherId(publishers.getId());
		GetSql<Books>bGetSql=new GetSql<Books>();
		GetSql<Publishers>pGetSql=new GetSql<Publishers>();
		GetSql<Author_Book>abGetSql=new GetSql<Author_Book>();
		Connection conn=getConnection();
		try {
			conn.setAutoCommit(false);
			Statement statement=conn.createStatement();
			List<Books>result;
			result=bookDao.Find(books, books); //isbn找到了
			//1.删除author_book中books表的isbn
			if(result!=null)
			{
				for(Books books2:result)
				{
					Author_Book author_Book=new Author_Book();
					author_Book.setIsbn(books2.getIsbn());
					statement.addBatch(abGetSql.GetDeleteSql(author_Book));
				}
			}
			
			
			
			
			//2删除books 表中对应的出版社id
			statement.addBatch(bGetSql.GetDeleteSql(books));
			
			//3 删除publsisher表中的数据
			statement.addBatch(pGetSql.GetDeleteSql(publishers));
			
			
			statement.executeBatch(); //批量执行   
            conn.commit();//提交事务  
            return 1;
			
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return 0;
		
	}
}
