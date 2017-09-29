package com.swpu.bms.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.swpu.bms.dao.UsersDao;
import com.swpu.bms.entity.Users;

@WebServlet("/userAction.do")
public class UserAction extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("application/json;charset=utf-8");
		
		
		String action=(String) request.getParameter("action");
		UsersDao usersdao=new UsersDao();
		Users user=new Users();
		Map<String, Object> data=new HashMap<String,Object>();
		try {
			if("login".equals(action)) //用户登录（参数：用户名和密码）
			{
				user.setUsername(request.getParameter("userName"));
				user.setPassword(request.getParameter("password"));
				//查找数据库(根据用户名和密码的方式查找)
				int ret=0;
				
				ret = usersdao.UserCheck(user);

				if(ret!=0) //用户合法
				{
					data.put("msg", 100); //表示成功
					data.put("user", user.getUsername());
					
					//设置session
					request.getSession().setAttribute("user", user.getUsername());
				}
				else
				{
					data.put("msg", 200); //表示失败
				}
			}
//			if(request.getSession().getAttribute("user")==null)
//			{
//				data.put("msg", 1000); //表示不能登录
//				return;
//			}
			
			else if("delete".equals(action)) //删除用户(参数：用户id)
			{
				
				user.setId(Integer.parseInt(request.getParameter("id")));
				//删除用户数据
				int ret;
				
				ret=usersdao.Delete(user);

				if(ret==0) //删除失败
				{	
					data.put("msg", 201); //表示失败
				}
				else
				{
					
					data.put("msg", 101); //表示成功
				}
				
			}
			else if("update".equals(action)) //更新用户数据 （参数：用户所有数据）
			{
				
				user.setId(Integer.parseInt(request.getParameter("id")));
				user.setUsername(request.getParameter("userName"));
				user.setPassword(request.getParameter("password"));
				user.setRealname(request.getParameter("realName"));
				int ret;
				//修改用户数据
				
				ret=usersdao.UpdateByID(user);
			
				if(ret==0) //删除失败
				{
					data.put("msg", 202); //表示失败
				}
				else
				{
					
					data.put("msg", 102); //表示成功
				}
				
			}
			else if("add".equals(action)) //添加用户数据 （参数：用户所有数据）
			{
				
				
				user.setUsername(request.getParameter("userName"));
				user.setPassword(request.getParameter("password"));
				user.setRealname(request.getParameter("realName"));
			
				//修改用户数据
				
				user=usersdao.Insert(user);
			
				if(user!=null)
				{
					if(user.getId()!=0) //添加成功
					{
						data.put("msg", 103); //表示成功
					}
					else
					{
						data.put("msg", 203); //表示失败
					}
				}
				else
				{
					data.put("msg", 203); //表示失败
				}
				
				
				
			}
			else if("user".equals(action)) //查询所有用户
			{
				List<Users> users = new ArrayList<>();

				users = usersdao.Find(user,null);


				data.put("msg", 104);
				data.put("users", users);
				
			}
			else if("logout".equals(action))
			{
				request.getSession().removeAttribute("user");
				request.getSession().invalidate();				
				data.put("msg", 105);
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			data.put("msg", 300); //数据库操作异常
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
