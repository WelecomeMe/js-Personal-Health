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
             function liuyanDetail(id)
             {
                  var strUrl = "<%=path %>/liuyan?type=liuyanDetail&id="+id;
                  var ret = window.showModalDialog(strUrl,"","dialogWidth:600px; dialogHeight:500px; dialogLeft: status:no; directories:yes;scrollbars:yes;Resizable=no;");
             }
        </script>
	</head>

	<body leftmargin="2" topmargin="2" background='<%=path %>/img/allbg.gif'>
			<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
				<tr bgcolor="#E7E7E7">
					<td height="14" colspan="4" background="<%=path %>/img/tbg.gif">&nbsp;我的咨询信息&nbsp;</td>
				</tr>
				<tr align="center" bgcolor="#FAFAF1" height="22">
				    <td width="5%">序号</td>
					<td width="50%">咨询内容</td>
					<td width="10%">咨询时间</td>
					<td width="10%">操作</td>
		        </tr>	
				<c:forEach items="${requestScope.liuyanList}" var="liuyan" varStatus="s">
				<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
					<td bgcolor="#FFFFFF" align="center">
						 ${s.index+1}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						 <c:out value="${liuyan.content}" escapeXml="false"></c:out>
					</td>
					<td bgcolor="#FFFFFF" align="center">
						 ${liuyan.liuyanshijian}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						 <a style="color: red" href="#" onclick="liuyanDetail(${liuyan.id})">查看回复信息</a>
					</td>
				</tr>
				</c:forEach>
			</table>
	</body>
</html>
