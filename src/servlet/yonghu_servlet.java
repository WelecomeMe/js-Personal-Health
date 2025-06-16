package servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.TYonghu;

import db.DB;

public class yonghu_servlet extends HttpServlet{
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException 
	{
        String type=req.getParameter("type");
        
        if(type.endsWith("zhuce"))
        {
        	zhuce(req, res);
        }
        if(type.endsWith("yhUpd"))
        {
        	yhUpd(req, res);
        }
        if(type.endsWith("passUpd"))
        {
        	passUpd(req, res);
        }
		if(type.endsWith("yhMana"))
		{
			yhMana(req, res);
		}
	}
	
	public void yhUpd(HttpServletRequest req,HttpServletResponse res)
	{
		int id = Integer.parseInt(req.getParameter("id"));
		String zt = req.getParameter("zt");
		String sql = "update t_yonghu set del = ? where id = ?";
		
		Object[] params={zt,id};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("message", "操作成功");
		req.setAttribute("path", "yonghu?type=yhMana");
		
        String targetURL = "/common/success.jsp";
		dispatch(targetURL, req, res);
	}
	
	public void passUpd(HttpServletRequest req,HttpServletResponse res)
	{
		int id = Integer.parseInt(req.getParameter("id"));
		String pass = "111111";
		String sql = "update t_yonghu set loginPass = ? where id = ?";
		Object[] params={pass,id};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("message", "操作成功");
		req.setAttribute("path", "yonghu?type=yhMana");
		
        String targetURL = "/common/success.jsp";
		dispatch(targetURL, req, res);
	}
	
	public void yhMana(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException
	{
		List yhList = new ArrayList();
		
		String xingming = req.getParameter("xingming")==null?"":req.getParameter("xingming");
		String loginName = req.getParameter("loginName")==null?"":req.getParameter("loginName");
		
		String sql = "select id,xingming,xingbie,loginName,del from t_yonghu where 1=1";
		if(!("".equals(xingming.trim()))){
			sql += " and xingming like '%"+xingming+"%'";
		}
		if(!("".equals(loginName.trim()))){
			sql += " and loginName like '%"+loginName+"%'";
		}
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				TYonghu yh = new TYonghu();
				yh.setId(rs.getInt("id"));
				yh.setXingming(rs.getString("xingming"));
				yh.setXingbie(rs.getString("xingbie"));
				yh.setLoginName(rs.getString("loginName"));
				yh.setDel(rs.getString("del"));
				
				yhList.add(yh);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("yhList", yhList);
		req.setAttribute("xingming", xingming);
		req.setAttribute("loginName", loginName);
		req.getRequestDispatcher("admin/yonghu/yhMana.jsp").forward(req, res);
	}
	public void zhuce(HttpServletRequest req,HttpServletResponse res)
	{
		String loginName = req.getParameter("loginName");
		String loginPass = req.getParameter("loginPass");
		String xingming = req.getParameter("xingming");
		String xingbie = req.getParameter("xingbie");
		String shengri = req.getParameter("shengri");
		String del = "no";
		
		String sql="insert into t_yonghu values(?,?,?,?,?,?)";
		Object[] params={loginName,loginPass,xingming,xingbie,shengri,del};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("message", "注册成功,请登录!");
		req.setAttribute("path", "login.jsp");
		
        String targetURL = "/common/success.jsp";
		dispatch(targetURL, req, res);
	}
	
	public void dispatch(String targetURI,HttpServletRequest request,HttpServletResponse response) 
	{
		RequestDispatcher dispatch = getServletContext().getRequestDispatcher(targetURI);
		try 
		{
		    dispatch.forward(request, response);
		    return;
		} 
		catch (ServletException e) 
		{
                    e.printStackTrace();
		} 
		catch (IOException e) 
		{
			
		    e.printStackTrace();
		}
	}
}
