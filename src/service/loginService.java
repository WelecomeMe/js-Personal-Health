package service;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import model.TAdmin;
import model.TYonghu;
import model.Tyisheng;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

import db.DB;

public class loginService
{
	public String login(String userName,String userPw,int userType)
	{
		String result="no";
		
		if(userType==0)//ÏµÍ³¹ÜÀíÔ±µÇÂ½
		{
			String sql="select * from t_admin where userName=? and userPw=?";
			Object[] params={userName,userPw};
			DB mydb=new DB();
			mydb.doPstm(sql, params);
			try 
			{
				ResultSet rs=mydb.getRs();
				boolean mark=(rs==null||!rs.next()?false:true);
				if(mark==false)
				{
					 result="no";
				}
				else
				{
					 result="yes";
					 TAdmin admin=new TAdmin();
					 admin.setUserId(rs.getInt("userId"));
					 admin.setUserName(rs.getString("userName"));
					 admin.setUserPw(rs.getString("userPw"));
					 WebContext ctx = WebContextFactory.get(); 
					 HttpSession session=ctx.getSession(); 
					 session.setAttribute("userType", 0);
		             session.setAttribute("admin", admin);
				}
				rs.close();
			} 
			catch (SQLException e)
			{
				System.out.println("µÇÂ¼Ê§°Ü£¡");
				e.printStackTrace();
			}
			finally
			{
				mydb.closed();
			}
			
		}
		
		if(userType==1)
		{
			String sql="select * from t_yonghu where loginName = ? and loginPass = ? and del='no'";
			Object[] params={userName,userPw};
			DB mydb=new DB();
			mydb.doPstm(sql, params);
			try 
			{
				ResultSet rs=mydb.getRs();
				boolean mark=(rs==null||!rs.next()?false:true);
				if(mark==false)
				{
					 result="no";
				}
				else
				{
					 result="yes";
					 
					 TYonghu yonghu = new TYonghu();
					 
					 yonghu.setId(rs.getInt("id"));
					 yonghu.setXingming(rs.getString("xingming"));
					 yonghu.setLoginName(rs.getString("loginName"));
					 yonghu.setLoginPass(rs.getString("loginPass"));
					 
					 WebContext ctx = WebContextFactory.get(); 
					 HttpSession session=ctx.getSession(); 
					 session.setAttribute("userType", 1);
		             session.setAttribute("yonghu", yonghu);
				}
				rs.close();
			} 
			catch (SQLException e)
			{
				System.out.println("µÇÂ¼Ê§°Ü£¡");
				e.printStackTrace();
			}
			finally
			{
				mydb.closed();
			}
		}
		
		
		if(userType==2)
		{
			String sql="select * from t_yisheng where loginname = ? and loginpw = ? and del='no'";
			Object[] params={userName,userPw};
			DB mydb=new DB();
			mydb.doPstm(sql, params);
			try 
			{
				ResultSet rs=mydb.getRs();
				boolean mark=(rs==null||!rs.next()?false:true);
				if(mark==false)
				{
					 result="no";
				}
				else
				{
					 result="yes";
					 
					 Tyisheng yisheng=new Tyisheng();
						
					 yisheng.setId(rs.getInt("id"));
					 yisheng.setXingming(rs.getString("xingming"));
					 yisheng.setXingbie(rs.getString("xingbie"));
					 yisheng.setNianling(rs.getString("nianling"));
					
					 yisheng.setBiye(rs.getString("biye"));
					 yisheng.setLoginname(rs.getString("loginname"));
					 yisheng.setLoginpw(rs.getString("loginpw"));
					 yisheng.setDel(rs.getString("del"));
					 
					 WebContext ctx = WebContextFactory.get(); 
					 HttpSession session=ctx.getSession(); 
					 session.setAttribute("userType", 2);
		             session.setAttribute("yisheng", yisheng);
				}
				rs.close();
			} 
			catch (SQLException e)
			{
				System.out.println("µÇÂ¼Ê§°Ü£¡");
				e.printStackTrace();
			}
			finally
			{
				mydb.closed();
			}
		}
		
		return result;
	}

	public String adminPwEdit(String userPwNew)
	{
		WebContext ctx = WebContextFactory.get(); 
		HttpSession session=ctx.getSession(); 
		TAdmin admin=(TAdmin)session.getAttribute("admin");
		
		String sql="update t_admin set userPw=? where userId=?";
		Object[] params={userPwNew,admin.getUserId()};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		
		return "yes";
	}
	
    public String yonghuPwEdit(String userPwNew)
    {
		WebContext ctx = WebContextFactory.get(); 
		HttpSession session=ctx.getSession(); 
		TYonghu yonghu=(TYonghu)session.getAttribute("yonghu");
		
		String sql="update t_yonghu set loginPass=? where id=?";
		Object[] params={userPwNew,yonghu.getId()};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		
		return "yes";
    }
    
    public String yonghuCheck(String loginName,String loginPass){
    	String result = "no";
    	String sql = "select 1 from t_yonghu where loginName = ? and loginPass = ?";
    	Object[] params={loginName,loginPass};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		try 
		{
			ResultSet rs=mydb.getRs();
			
			if(rs.next())
			{
				result="yes";
			}
			rs.close();
		} 
		catch (SQLException e)
		{
			System.out.println("µÇÂ¼Ê§°Ü£¡");
			e.printStackTrace();
		}
		finally
		{
			mydb.closed();
		}
    	return result;
    }
}
