package model;

public class Tliuyan
{
	private int id;
	
	private String content;
	private String liuyanshijian;
	private String huifu;
	private String huifushijian;
	
	private int yonghu_id;
	private int yisheng_id;
	private String liuyan_type;//toyonghu-----toyisheng
	
	private TYonghu yonghu;
	private Tyisheng yisheng;
	
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	public String getHuifu()
	{
		return huifu;
	}
	public void setHuifu(String huifu)
	{
		this.huifu = huifu;
	}
	
	public String getHuifushijian()
	{
		return huifushijian;
	}
	public void setHuifushijian(String huifushijian)
	{
		this.huifushijian = huifushijian;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getLiuyan_type()
	{
		return liuyan_type;
	}
	public void setLiuyan_type(String liuyan_type)
	{
		this.liuyan_type = liuyan_type;
	}
	public String getLiuyanshijian()
	{
		return liuyanshijian;
	}
	public void setLiuyanshijian(String liuyanshijian)
	{
		this.liuyanshijian = liuyanshijian;
	}
	public int getYisheng_id()
	{
		return yisheng_id;
	}
	public void setYisheng_id(int yisheng_id)
	{
		this.yisheng_id = yisheng_id;
	}
	public int getYonghu_id()
	{
		return yonghu_id;
	}
	public void setYonghu_id(int yonghu_id)
	{
		this.yonghu_id = yonghu_id;
	}
	public Tyisheng getYisheng()
	{
		return yisheng;
	}
	public void setYisheng(Tyisheng yisheng)
	{
		this.yisheng = yisheng;
	}
	public TYonghu getYonghu()
	{
		return yonghu;
	}
	public void setYonghu(TYonghu yonghu)
	{
		this.yonghu = yonghu;
	}
	
}
