package servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
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
import model.TYonghu;
import model.Tliuyan;
import model.Tyisheng;

import db.DB;


public class liuyan_servlet extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException 
	{
        String type=req.getParameter("type");
		
		
		if(type.endsWith("liuyantoyisheng"))
		{
			liuyantoyisheng(req, res);
		}
		if(type.endsWith("liuyanMana_yonghu"))
		{
			liuyanMana_yonghu(req, res);
		}
		
		if(type.endsWith("liuyanMana_yisheng"))
		{
			liuyanMana_yisheng(req, res);
		}
		if(type.endsWith("liuyan_huifu"))
		{
			liuyan_huifu(req, res);
		}
		if(type.endsWith("liuyanDetail"))
		{
			liuyanDetail(req, res);
		}
	}
	
	
	public void liuyantoyisheng(HttpServletRequest req,HttpServletResponse res)
	{
		
		String content=req.getParameter("content");
		String liuyanshijian=new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()); 
		String huifu="";
		String huifushiian="";
		int youhu_id=Integer.parseInt(req.getParameter("yonghu_id"));
		int yisheng_id=Integer.parseInt(req.getParameter("yisheng_id"));
		
		String liuyan_type=req.getParameter("liuyan_type");
		
		String sql="insert into t_liuyan values(?,?,?,?,?,?,?)";
		Object[] params={content,liuyanshijian,huifu,huifushiian,youhu_id,yisheng_id,liuyan_type};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("msg", "¡Ù—‘ÕÍ±œ");
        String targetURL = "/common/msg.jsp";
		dispatch(targetURL, req, res);
        
	}
	

	public void liuyanMana_yonghu(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		TYonghu yonghu=(TYonghu)(req.getSession().getAttribute("yonghu"));
		String type="toyisheng";
		
		List liuyanList=new ArrayList();
		String sql="select * from t_liuyan where yonghu_id=? and liuyan_type=?";
		Object[] params={yonghu.getId(),type};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				Tliuyan liuyan=new Tliuyan();
				
				liuyan.setId(rs.getInt("id"));
				
				liuyan.setContent(rs.getString("content"));
				liuyan.setLiuyanshijian(rs.getString("liuyanshijian"));
				liuyan.setHuifu(rs.getString("huifu"));
				liuyan.setHuifushijian(rs.getString("huifushijian"));
				
				liuyan.setYonghu_id(rs.getInt("yonghu_id"));
				liuyan.setYisheng_id(rs.getInt("yisheng_id"));
				liuyan.setLiuyan_type(rs.getString("liuyan_type"));
				
				
				
				liuyanList.add(liuyan);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("liuyanList", liuyanList);
		req.getRequestDispatcher("admin/liuyan/liuyanMana_yonghu.jsp").forward(req, res);
	}
	
	
	public void liuyanMana_yisheng(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		Tyisheng yisheng=(Tyisheng)(req.getSession().getAttribute("yisheng"));
		String type="toyisheng";
		
		List liuyanList=new ArrayList();
		String sql="select * from t_liuyan where yisheng_id=? and liuyan_type=?";
		Object[] params={yisheng.getId(),type};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				Tliuyan liuyan=new Tliuyan();
				
				liuyan.setId(rs.getInt("id"));
				liuyan.setContent(rs.getString("content"));
				liuyan.setLiuyanshijian(rs.getString("liuyanshijian"));
				liuyan.setHuifu(rs.getString("huifu"));
				liuyan.setHuifushijian(rs.getString("huifushijian"));
				
				liuyan.setYonghu_id(rs.getInt("yonghu_id"));
				liuyan.setYisheng_id(rs.getInt("yisheng_id"));
				liuyan.setLiuyan_type(rs.getString("liuyan_type"));
				
				liuyanList.add(liuyan);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("liuyanList", liuyanList);
		req.getRequestDispatcher("admin/liuyan/liuyanMana_yisheng.jsp").forward(req, res);
	}
	
	
	public void liuyan_huifu(HttpServletRequest req,HttpServletResponse res)
	{
		int id=Integer.parseInt(req.getParameter("id"));
		String huifu=req.getParameter("huifu");
		String huifushijian=new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()); 
		
		String sql="update t_liuyan set huifu=?,huifushijian=? where id=?";
		Object[] params={huifu,huifushijian,id};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("msg", "ªÿ∏¥ÕÍ±œ");
        String targetURL = "/common/msg.jsp";
		dispatch(targetURL, req, res);
	}
	
	
	public void liuyanDetail(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		Tliuyan liuyan=new Tliuyan();
		String sql="select * from t_liuyan where id=?";
		Object[] params={Integer.parseInt(req.getParameter("id"))};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				liuyan.setContent(rs.getString("content"));
				liuyan.setLiuyanshijian(rs.getString("liuyanshijian"));
				liuyan.setHuifu(rs.getString("huifu"));
				liuyan.setHuifushijian(rs.getString("huifushijian"));
				
				liuyan.setYonghu_id(rs.getInt("yonghu_id"));
				liuyan.setYisheng_id(rs.getInt("yisheng_id"));
				liuyan.setLiuyan_type(rs.getString("liuyan_type"));
				
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("liuyan", liuyan);
		req.getRequestDispatcher("admin/liuyan/liuyanDetail.jsp").forward(req, res);
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
