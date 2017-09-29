package com.swpu.bms.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import com.swpu.bms.dao.BookDao;
import com.swpu.bms.dao.PublisherDao;
import com.swpu.bms.entity.Author_Book;
import com.swpu.bms.entity.Books;
import com.swpu.bms.entity.Publishers;

@WebServlet("/bookAction.do")
public class BookAction extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		Map<String, Object> data=new HashMap<String,Object>();
		if(request.getSession().getAttribute("user")==null)
		{
			//data.put("msg", 1000); //表示不能登录
			//return;
		}
		
		response.setContentType("application/json;charset=utf-8");
		Gson gson = new Gson();
		String action = (String) request.getParameter("action");
		BookDao bookDao = new BookDao();
		Books book = new Books();

		try {

			if ("book".equals(action)) // 查询所有图书
			{
				   
				List<Books> books = new ArrayList<Books>();
				List<Publishers>publishers=new ArrayList<Publishers>();
				PublisherDao publisherDao=new PublisherDao();
				publishers=publisherDao.Find(new Publishers(), null);
	
				Map<Integer, String> map=new HashMap<Integer, String>() ;
				if(publishers!=null)
				{
					for(Publishers p:publishers)
					{
						map.put(p.getId(), p.getName());
					}
				}
				
				
				try {
					books = bookDao.Find(book,null);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (books != null) {
					for (int i = 0; i < books.size(); i++) {
						if (books.get(i).getPublishTime() == null) {
							books.get(i).setPublishTime("");
						}
						books.get(i).setPublishername(map.get(books.get(i).getPublisherId()));

					}
				}
				data.put("msg", 130);
				data.put("books", books);
			} 
			else if ("search".equals(action)) // 按条件查询图书
			{
				List<Books> books = new ArrayList<Books>();
				List<Publishers>publishers=new ArrayList<Publishers>();
				PublisherDao publisherDao=new PublisherDao();
				publishers=publisherDao.Find(new Publishers(), null);
	
				Map<Integer, String> map=new HashMap<Integer, String>() ;
				for(Publishers p:publishers)
				{
					map.put(p.getId(), p.getName());
				}
				
				String isbn=request.getParameter("isbn");
				String name=request.getParameter("name");
				if(!("".equals(isbn)))
				{
					book.setIsbn(isbn);
				}
				if(!("".equals(name)))
				{
					book.setName(name);
				}
				books=bookDao.Find(book, book);
				if (books != null) {
					for (int i = 0; i < books.size(); i++) {
						if (books.get(i).getPublishTime() == null) {
							books.get(i).setPublishTime("");
						}
						if(map.get(books.get(i).getPublisherId())!=null)
						{
							books.get(i).setPublishername(map.get(books.get(i).getPublisherId()));
						}
						else
						{
							books.get(i).setPublishername("无");
						}
					}
				}
				data.put("msg", 131);
				data.put("books", books);
				
			} 
			else if ("update".equals(action)) // 修改图书
			{
				//1.更新books表
				//2.删除author_book表中关于该书的作者
				//3.插入该书的新作者
				book.setIsbn(request.getParameter("isbn"));
				book.setName(request.getParameter("name"));
				book.setPublisherId(Integer.parseInt(request.getParameter("publisherId")));
				book.setPrice(Float.parseFloat(request.getParameter("price")));
				String time=request.getParameter("publishTime");
	
				if(time!=null && !("".equals(time)))
				{
					book.setPublishTime(time);
				}
				book.setVersion(Integer.parseInt(request.getParameter("version")));
				String [] authorID=request.getParameterValues("authorID");
				List<Author_Book>author_Books=new ArrayList<Author_Book>();
				for(int i=0;i<authorID.length;i++)
				{
					Author_Book author_Book=new Author_Book();
					author_Book.setAuthorID(Integer.parseInt(authorID[i]));
					author_Book.setIsbn(book.getIsbn());
					author_Books.add(author_Book);
				}
				
				int ret=bookDao.UpdateBook(book, author_Books);
				if(ret==1)
				{
					data.put("msg", 132);
				}
				else
					data.put("msg", 232);
				
			} 
			else if ("delete".equals(action))// 删除图书
			{
				//1.删除图书（books表）
				//2.删除与该图书绑定的作者（author_book表中的数据）
				
				
				book.setIsbn(request.getParameter("isbn"));
				Author_Book author_Book=new Author_Book();
				author_Book.setIsbn(book.getIsbn());
				
				int ret=bookDao.DeleteBook(book, author_Book);
				if(ret==1)
					data.put("msg", 133);
				else
					data.put("msg", 233);
			
				

			}
			else if("add".equals(action))
			{
				book.setIsbn(request.getParameter("isbn"));
				book.setName(request.getParameter("name"));
				book.setPrice(Float.parseFloat(request.getParameter("price")));
				
				book.setPublisherId(Integer.parseInt(request.getParameter("publisherId")));
				book.setPublishTime(request.getParameter("publishTime"));
				book.setVersion(Integer.parseInt(request.getParameter("version")));
				
				List<Author_Book>author_Book=new ArrayList<Author_Book>();
				
				String[] authorid=request.getParameterValues("authorID");
				if (authorid != null) {
					for (int i = 0; i < authorid.length; i++) {
						Author_Book ab = new Author_Book();
						ab.setIsbn(book.getIsbn());
						ab.setAuthorID(Integer.parseInt(authorid[i]));
						author_Book.add(ab);
					}
				}
				else {
					data.put("msg", 234);
					return;
				}
				int ret=bookDao.InsertBook(book, author_Book);
				if(ret!=0)
				{
					data.put("msg", 134);
				}
				else{
					data.put("msg", 234);
				}
				
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			data.put("msg", 300); // 数据库操作异常
			e.printStackTrace();
		} finally {

			

			PrintWriter writer = response.getWriter();
			writer.write(gson.toJson(data));
			System.out.println(gson.toJson(data));
			writer.flush();
		}

	}

}
