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
import com.swpu.bms.dao.AuthorDao;
import com.swpu.bms.entity.Authors;


@WebServlet("/authorAction.do")
public class AuthorAction extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		

		Map<String, Object> data=new HashMap<String,Object>();
		if(request.getSession().getAttribute("user")==null)
		{
			//data.put("msg", 1000); //表示不能登录
			//return;
		}
		response.setContentType("application/json;charset=utf-8");
		String action=(String) request.getParameter("action");
		AuthorDao authordao=new AuthorDao();
		Authors author=new Authors();
		
		try {
			if("author".equals(action)) //查询所有作者
			{
				
				List<Authors>authors=new ArrayList<Authors>();
				authors = authordao.Find(author,null);

				data.put("msg", 110); //表示成功
				data.put("authors", authors);
		
			}
			else if("delete".equals(action)) //删除作者(参数：用户id)
			{
				
				

				author.setId(Integer.parseInt(request.getParameter("id")));
				//删除用户数据
				int ret=0;
				
				ret=authordao.deleteAuthor(author);

				if(ret==1) //删除成功
				{	
					data.put("msg", 111); //表示成功
				}
				else
				{
					data.put("msg", 211); //表示失败
				}
				
			}
			else if("update".equals(action)) //更新作者数据 （参数：作者所有数据）
			{
				
				author.setId(Integer.parseInt(request.getParameter("id")));
				author.setFirstname(request.getParameter("firstName"));
				author.setLastname(request.getParameter("lastName"));
				int ret;
				//修改作者数据
				
				ret=authordao.UpdateByID(author);
			
				if(ret==0) //删除失败
				{
					data.put("msg", 212); //表示失败
				}
				else
				{
					
					data.put("msg", 112); //表示成功
				}
				
			}
			else if("add".equals(action)) //添加用户数据 （参数：用户所有数据）
			{
				
				
				author.setFirstname(request.getParameter("firstName"));
				author.setLastname(request.getParameter("lastName"));
				
				//修改用户数据
				
				author=authordao.Insert(author);
			
				if(author!=null)
				{
					if(author.getId()!=0) //删除成功
					{
						data.put("msg", 113); //表示成功
					}
					else
					{
						data.put("msg", 213); //表示失败
					}
				}
				else
				{
					data.put("msg", 213); //表示失败
				}
				
				
			}
			
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			data.put("msg", "300"); //数据库操作异常
			e.printStackTrace();
		}
		finally {
			
			Gson gson=new Gson();
			
			PrintWriter writer = response.getWriter();
			writer.write(gson.toJson(data));
			System.out.println(gson.toJson(data));
			writer.flush();
		}
		
		
		
	}

}
