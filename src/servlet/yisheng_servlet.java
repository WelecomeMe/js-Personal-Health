package servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Tgonggao;
import model.Tyisheng;

import db.DB;


public class yisheng_servlet extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException 
	{
        String type=req.getParameter("type");
		
		
		if(type.endsWith("yishengAdd"))
		{
			yishengAdd(req, res);
		}
		if(type.endsWith("yishengMana"))
		{
			yishengMana(req, res);
		}
		if(type.endsWith("yishengDel"))
		{
			yishengDel(req, res);
		}
		if(type.endsWith("yishengAll"))
		{
			yishengAll(req, res);
		}
	}
	
	
	public void yishengAdd(HttpServletRequest req,HttpServletResponse res)
	{
		String xingming=req.getParameter("xingming");
		String xingbie=req.getParameter("xingbie");
		String nianling=req.getParameter("nianling");
		String biye=req.getParameter("biye");
		
		String loginname=req.getParameter("loginname");
		String loginpw=req.getParameter("loginpw");
		String del="no";
		
		String sql="insert into t_yisheng values(?,?,?,?,?,?,?)";
		Object[] params={xingming,xingbie,nianling,biye,loginname,loginpw,del};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("message", "操作成功");
		req.setAttribute("path", "yisheng?type=yishengMana");
		
        String targetURL = "/common/success.jsp";
		dispatch(targetURL, req, res);
        
	}
	
	
	public void yishengDel(HttpServletRequest req,HttpServletResponse res)
	{
		String id=req.getParameter("id");
		
		String sql="update t_yisheng set del='yes' where id=?";
		Object[] params={id};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("message", "操作成功");
		req.setAttribute("path", "yisheng?type=yishengMana");
		
        String targetURL = "/common/success.jsp";
		dispatch(targetURL, req, res);
	}

	public void yishengMana(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		List yishengList=new ArrayList();
		String sql="select * from t_yisheng where del='no'";
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				Tyisheng yisheng=new Tyisheng();
				
				yisheng.setId(rs.getInt("id"));
				yisheng.setXingming(rs.getString("xingming"));
				yisheng.setXingbie(rs.getString("xingbie"));
				yisheng.setNianling(rs.getString("nianling"));
				
				yisheng.setBiye(rs.getString("biye"));
				yisheng.setLoginname(rs.getString("loginname"));
				yisheng.setLoginpw(rs.getString("loginpw"));
				yisheng.setDel(rs.getString("del"));
				
				yishengList.add(yisheng);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("yishengList", yishengList);
		req.getRequestDispatcher("admin/yisheng/yishengMana.jsp").forward(req, res);
	}
	
	
	public void yishengAll(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		List yishengList=new ArrayList();
		String sql="select * from t_yisheng where del='no'";
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				Tyisheng yisheng=new Tyisheng();
				
				yisheng.setId(rs.getInt("id"));
				yisheng.setXingming(rs.getString("xingming"));
				yisheng.setXingbie(rs.getString("xingbie"));
				yisheng.setNianling(rs.getString("nianling"));
				
				yisheng.setBiye(rs.getString("biye"));
				yisheng.setLoginname(rs.getString("loginname"));
				yisheng.setLoginpw(rs.getString("loginpw"));
				yisheng.setDel(rs.getString("del"));
				
				yishengList.add(yisheng);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("yishengList", yishengList);
		req.getRequestDispatcher("admin/yisheng/yishengAll.jsp").forward(req, res);
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
	public void init(ServletConfig config) throws ServletException 
	{
		super.init(config);
	}
	
	public void destroy() 
	{
		
	}
}
