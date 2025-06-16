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

import model.THuodong;
import model.TJilu;
import model.TTijian;

import db.DB;


public class huodong_servlet extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException 
	{
        String type=req.getParameter("type");
		
		
		if(type.endsWith("huodongAdd"))
		{
			huodongAdd(req, res);
		}
		if(type.endsWith("huodongMana"))
		{
			huodongMana(req, res);
		}
		if(type.endsWith("huodongDel"))
		{
			huodongDel(req, res);
		}
	}
	
	
	public void huodongAdd(HttpServletRequest req,HttpServletResponse res)
	{
		int youhu_id=Integer.parseInt(req.getParameter("yonghu_id"));
		String shijian=req.getParameter("shijian");
		String didian=req.getParameter("didian");
		String xingshi=req.getParameter("xingshi");
		
		String zhuti=req.getParameter("zhuti");
		String zuzhizhe=req.getParameter("zuzhizhe");
		String neirong=req.getParameter("neirong");
		
		
		String sql="insert into t_huodong values(?,?,?,?,?,?,?)";
		Object[] params={youhu_id,shijian,didian,xingshi,zhuti,zuzhizhe,neirong};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("msg", "操作成功");
        String targetURL = "/common/msg.jsp";
		dispatch(targetURL, req, res);
        
	}
	
	
	public void huodongDel(HttpServletRequest req,HttpServletResponse res)
	{
		String id=req.getParameter("id");
		
		String sql="delete from t_huodong where id=?";
		Object[] params={id};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("msg", "操作成功");
        String targetURL = "/common/msg.jsp";
		dispatch(targetURL, req, res);
	}

	public void huodongMana(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		List huodongList=new ArrayList();
		String sql="select * from t_huodong where yonghu_id="+Integer.parseInt(req.getParameter("yonghu_id"));
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				THuodong huodong=new THuodong();
				
				huodong.setId(rs.getInt("id"));
				huodong.setYonghu_id(rs.getInt("yonghu_id"));
				huodong.setShijian(rs.getString("shijian"));
				huodong.setDidian(rs.getString("didian"));
				
				huodong.setXingshi(rs.getString("xingshi"));
				huodong.setZhuti(rs.getString("zhuti"));
				huodong.setZuzhizhe(rs.getString("zuzhizhe"));
				huodong.setNeirong(rs.getString("neirong"));
				
				huodongList.add(huodong);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("huodongList", huodongList);
		req.getRequestDispatcher("admin/huodong/huodongMana.jsp").forward(req, res);
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
