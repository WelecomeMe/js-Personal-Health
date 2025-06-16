<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false" %> 
<%
String path = request.getContextPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />

		<link rel="stylesheet" type="text/css" href="<%=path %>/css/base.css" />
		
		<script language="JavaScript" src="<%=path %>/js/public.js" type="text/javascript"></script>
		
        <script language="javascript">
           function yishengAdd()
           {
              var url="<%=path %>/admin/yisheng/yishengAdd.jsp";
              window.location.href=url;
           }
       </script>
	</head>

	<body leftmargin="2" topmargin="2" background='<%=path %>/img/allbg.gif'>
			<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
				<tr bgcolor="#E7E7E7">
					<td height="14" colspan="44" background="<%=path %>/img/tbg.gif">&nbsp;医生信息管理&nbsp;</td>
				</tr>
				<tr align="center" bgcolor="#FAFAF1" height="22">
					<td width="15%">医生姓名</td>
					<td width="15%">医生性别</td>
					<td width="15%">医生年龄</td>
					<td width="15%">毕业院校</td>
					<td width="15%">登陆账号</td>
					<td width="15%">登陆密码</td>
					<td width="5%">操作</td>
		        </tr>	
				<c:forEach items="${requestScope.yishengList}" var="yisheng">
				<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
					<td bgcolor="#FFFFFF" align="center">
						 ${yisheng.xingming}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						 ${yisheng.xingbie}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						 ${yisheng.nianling}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						 ${yisheng.biye}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						 ${yisheng.loginname}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						 ${yisheng.loginpw}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						<a class="pn-loperator" href="<%=path %>/yisheng?type=yishengDel&id=${yisheng.id}">删除</a>
					</td>
				</tr>
				</c:forEach>
			</table>
			
			<table width='98%'  border='0'style="margin-top:8px;margin-left: 5px;">
			  <tr>
			    <td>
			      <input type="button" value="添加医生信息" style="width: 80px;" onclick="yishengAdd()" />
			    </td>
			  </tr>
		    </table>
	</body>
</html>
