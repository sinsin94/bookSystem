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

import com.swpu.bms.dao.PublisherDao;

import com.swpu.bms.entity.Publishers;

@WebServlet("/publisherAction.do")
public class PublisherAction extends HttpServlet {

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
		PublisherDao publisherdao=new PublisherDao();
		Publishers publisher=new Publishers();
		
		try {
			if("publisher".equals(action)) //查询所有作者
			{
				
				int ret=0;
				List<Publishers>publishers=new ArrayList<Publishers>();
				publishers = publisherdao.Find(publisher,null);

				data.put("msg", 120); //表示成功
				data.put("publishers", publishers);
		
			}
			else if("delete".equals(action)) //删除用户(参数：用户id)
			{
				
				publisher.setId(Integer.parseInt(request.getParameter("id")));
				//删除用户数据
				int ret;
				
				ret=publisherdao.DeletePublisher(publisher);

				if(ret==0) //删除失败
				{	
					data.put("msg", 221); //表示失败
				}
				else
				{
					
					data.put("msg", 121); //表示成功
				}
				
			}
			else if("update".equals(action)) //更新出版社数据
			{
				
				publisher.setId(Integer.parseInt(request.getParameter("id")));
				publisher.setName(request.getParameter("name"));
				int ret;
				//修改出版社数据
				
				ret=publisherdao.UpdateByID(publisher);
			
				if(ret==0) //删除失败
				{
					data.put("msg", 222); //表示失败
				}
				else
				{
					data.put("msg", 122); //表示成功
					
				}
				
			}
			else if("add".equals(action)) //添加用户数据 （参数：用户所有数据）
			{
				
			
				publisher.setName(request.getParameter("name"));
				
				//修改用户数据
				
				publisher=publisherdao.Insert(publisher);
			
				if(publisher!=null)
				{
					if(publisher.getId()!=0) //删除成功
					{
						data.put("msg", 123); //表示成功
					}
					else
					{
						data.put("msg", 223); //表示失败
					}
				}
				else
				{
					data.put("msg", 223); //表示失败
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
