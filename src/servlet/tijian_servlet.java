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

import model.TJilu;
import model.TTijian;

import db.DB;


public class tijian_servlet extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException 
	{
        String type=req.getParameter("type");
		
		
		if(type.endsWith("tijianAdd"))
		{
			tijianAdd(req, res);
		}
		if(type.endsWith("tijianMana"))
		{
			tijianMana(req, res);
		}
		if(type.endsWith("tijianDel"))
		{
			tijianDel(req, res);
		}
	}
	
	
	public void tijianAdd(HttpServletRequest req,HttpServletResponse res)
	{
		int youhu_id=Integer.parseInt(req.getParameter("yonghu_id"));
		String shijian=req.getParameter("shijian");
		String jieguo=req.getParameter("jieguo");
		
		
		String sql="insert into t_tijian values(?,?,?)";
		Object[] params={youhu_id,shijian,jieguo};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("msg", "操作成功");
        String targetURL = "/common/msg.jsp";
		dispatch(targetURL, req, res);
        
	}
	
	
	public void tijianDel(HttpServletRequest req,HttpServletResponse res)
	{
		String id=req.getParameter("id");
		
		String sql="delete from t_tijian where id=?";
		Object[] params={id};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("msg", "操作成功");
        String targetURL = "/common/msg.jsp";
		dispatch(targetURL, req, res);
	}

	public void tijianMana(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		List tijianList=new ArrayList();
		String sql="select * from t_tijian where yonghu_id="+Integer.parseInt(req.getParameter("yonghu_id"));
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				TTijian tijian=new TTijian();
				
				tijian.setId(rs.getInt("id"));
				tijian.setYonghu_id(rs.getInt("yonghu_id"));
				tijian.setShijian(rs.getString("shijian"));
				tijian.setJieguo(rs.getString("jieguo"));
				
				tijianList.add(tijian);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("tijianList", tijianList);
		req.getRequestDispatcher("admin/tijian/tijianMana.jsp").forward(req, res);
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
