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
             function liuyantoyisheng(yisheng_id)
             {
                  var strUrl = "<%=path %>/admin/liuyan/liuyantoyisheng.jsp?yisheng_id="+yisheng_id;
                  var ret = window.showModalDialog(strUrl,"","dialogWidth:600px; dialogHeight:500px; dialogLeft: status:no; directories:yes;scrollbars:yes;Resizable=no;");
             }
        </script>
	</head>

	<body leftmargin="2" topmargin="2" background='<%=path %>/img/allbg.gif'>
			<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
				<tr bgcolor="#E7E7E7">
					<td height="14" colspan="44" background="<%=path %>/img/tbg.gif">&nbsp;医生信息管理&nbsp;</td>
				</tr>
				<tr align="center" bgcolor="#FAFAF1" height="22">
				    <td width="5%">序号</td>
					<td width="15%">医生姓名</td>
					<td width="15%">医生性别</td>
					<td width="15%">医生年龄</td>
					<td width="15%">毕业院校</td>
					<td width="5%">操作</td>
		        </tr>	
				<c:forEach items="${requestScope.yishengList}" var="yisheng" varStatus="ss">
				<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
					<td bgcolor="#FFFFFF" align="center">
						 ${ss.index+1}
					</td>
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
						<a style="color: red" href="#" onclick="liuyantoyisheng(${yisheng.id})">在线咨询</a>
					</td>
				</tr>
				</c:forEach>
			</table>
	</body>
</html>
