package com.swpu.bms.test;

import org.junit.Test;

import com.swpu.bms.dao.AuthorDao;
import com.swpu.bms.dao.Author_Book_Dao;
import com.swpu.bms.dao.BookDao;
import com.swpu.bms.dao.PublisherDao;
import com.swpu.bms.dao.UsersDao;
import com.swpu.bms.entity.Author_Book;
import com.swpu.bms.entity.Authors;
import com.swpu.bms.entity.Books;
import com.swpu.bms.entity.Publishers;
import com.swpu.bms.entity.Users;
import com.swpu.bms.utils.GetSql;


import java.util.ArrayList;
import java.util.List;


import org.apache.commons.codec.digest.DigestUtils;


public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Users user = new Users();

		user.setId(1);
		user.setPassword("123");
		user.setUsername("123");
	   user.setRealname("张dfg三");
	   GetSql<Users>uGetSql=new GetSql<Users>();
	   try {
		   System.out.println(uGetSql.GetUpdateByIDSql(user));
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	//用户管理测试
	@Test
	public void usrs_test()
	{
		
		
		try {
			UsersDao userDao = new UsersDao();

			Users user = new Users();

			user.setId(4);
			user.setPassword("sd");
			user.setUsername("123");
		   user.setRealname("张dfg三");

		//	user=userDao.Insert(user);
		//	System.out.println(userDao.UpdateByID(user,2));
		//	System.out.println(userDao.UserCheck(user));
			
			List<Users>users=new ArrayList<Users>();
		//	users=userDao.Find(user,user);
			if(users!=null)
			{
				for(Users user1:users)
				{
					System.out.println(user1);
				}
			}
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	//作者管理测试
	@Test
	public void authors_test()
	{
		try {
			AuthorDao authorDao = new AuthorDao();

			Authors author = new Authors();

			author.setLastname("1");
			author.setFirstname("1");

			author=authorDao.Insert(author);
		
			System.out.println(author);
			//authorDao.Find(author, null);
			
			
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//图书管理测试
	@Test
	public  void book_test()
	{
		try {
			
			BookDao bookDao=new BookDao();
			Books books=new Books();
			books.setIsbn("5");
//			books.setName("1");
//			books.setPrice((float) 12);
//			
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
//			long start = System.currentTimeMillis(); 
//			
//			books.setPublishTime(sdf.format(start));
//			books.setVersion(1);
//			books.setPublisherId(3);
			
		
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//出版社管理测试
	@Test
	public void publisher_test()
	{
		try {
			PublisherDao publisherDao=new PublisherDao();
			Publishers publishers=new Publishers();
			
			publishers.setName("2");
			publisherDao.Insert(publishers);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public  void author_book()
	{
		Author_Book_Dao author_Book_Dao=new Author_Book_Dao();
		Author_Book author_Book=new Author_Book();
		author_Book.setAuthorID(5);
		//author_Book.setIsbn("1");
		try {
			author_Book_Dao.Delete(author_Book);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
