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

import db.DB;


public class jilu_servlet extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException 
	{
        String type=req.getParameter("type");
		
		
		if(type.endsWith("jiluAdd"))
		{
			jiluAdd(req, res);
		}
		if(type.endsWith("jiluMana"))
		{
			jiluMana(req, res);
		}
		if(type.endsWith("jiluDel"))
		{
			jiluDel(req, res);
		}
	}
	
	
	public void jiluAdd(HttpServletRequest req,HttpServletResponse res)
	{
		int youhu_id=Integer.parseInt(req.getParameter("yonghu_id"));
		String shijian=req.getParameter("shijian");
		String yinshi=req.getParameter("yinshi");
		String yundong=req.getParameter("yundong");
		String shuimian=req.getParameter("shuimian");
		String beizhu=req.getParameter("beizhu");
		
		
		String sql="insert into t_jilu values(?,?,?,?,?,?)";
		Object[] params={youhu_id,shijian,yinshi,yundong,shuimian,beizhu};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("msg", "操作成功");
        String targetURL = "/common/msg.jsp";
		dispatch(targetURL, req, res);
        
	}
	
	
	public void jiluDel(HttpServletRequest req,HttpServletResponse res)
	{
		String id=req.getParameter("id");
		
		String sql="delete from t_jilu where id=?";
		Object[] params={id};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("msg", "操作成功");
        String targetURL = "/common/msg.jsp";
		dispatch(targetURL, req, res);
	}

	public void jiluMana(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		List jiluList=new ArrayList();
		String sql="select * from t_jilu where yonghu_id="+Integer.parseInt(req.getParameter("yonghu_id"));
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				TJilu jilu=new TJilu();
				
				jilu.setId(rs.getInt("id"));
				jilu.setYonghu_id(rs.getInt("yonghu_id"));
				jilu.setShijian(rs.getString("shijian"));
				jilu.setYinshi(rs.getString("yinshi"));
				
				jilu.setYundong(rs.getString("yundong"));
				jilu.setShuimian(rs.getString("shuimian"));
				jilu.setBeizhu(rs.getString("beizhu"));
				
				jiluList.add(jilu);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("jiluList", jiluList);
		req.getRequestDispatcher("admin/jilu/jiluMana.jsp").forward(req, res);
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
