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
		<link rel="stylesheet" type="text/css" href="<%=path %>/css/base.css" />
		<script type="text/javascript" src="<%=path %>/jsxx/jsxxBus.js"></script>
        <script language="javascript">
           function yhztChange(id,zt)
           {
               if(confirm('您确定更改当前用户状态?'))
               {
                   window.location.href="<%=path %>/yonghu?type=yhUpd&id="+id+"&zt="+zt;
               }
           }
       
           function passUpd(id)
           {
			   if(confirm('密码将被重置为111111,确认操作?'))
               {
					window.location.href="<%=path %>/yonghu?type=passUpd&id="+id;
			   }
           }
       </script>
	</head>

	<body leftmargin="2" topmargin="2" background='<%=path %>/img/allbg.gif'>
			<table width='98%'  border='0'style="margin-top:8px;margin-left: 5px;">
			  <tr>
			    <td>
			        <form action="<%=path %>/yonghu?type=yhMana" name="formAdd" method="post">
			          	姓名： <input type="text" name="xingming" value="${requestScope.xingming }"/>
			           &nbsp;
			          	登录名： <input type="text" name="loginName" value="${requestScope.loginName }"/>
			           &nbsp;
			           <input type="submit" value="查询"/>
			        </form>
			    </td>
			  </tr>
		    </table>
			
			<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
				<tr bgcolor="#E7E7E7">
					<td height="14" colspan="5" background="<%=path %>/img/tbg.gif">&nbsp;用户管理&nbsp;</td>
				</tr>
				<tr align="center" bgcolor="#FAFAF1" height="22">
					<td width="10%">姓名</td>
					<td width="8%">性别</td>
					<td width="10%">登录名</td>
					<td width="10%">状态</td>
					<td width="10%">操作</td>
		        </tr>	
				<c:forEach items="${requestScope.yhList}" var="yonghu">
				<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
					<td bgcolor="#FFFFFF" align="center">
						${yonghu.xingming}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						${yonghu.xingbie}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						${yonghu.loginName}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						<c:if test="${yonghu.del=='yes'}">
							已停用
						</c:if>	
						<c:if test="${yonghu.del=='no'}">
							正常
						</c:if>		
					</td>
					<td bgcolor="#FFFFFF" align="center">
						<c:if test="${yonghu.del=='yes'}">
							<input type="button" onclick="yhztChange(${yonghu.id},'no')" value="启用"/>
						</c:if>	
						<c:if test="${yonghu.del=='no'}">
							<input type="button" onclick="yhztChange(${yonghu.id},'yes')" value="停用"/>
						</c:if>		
						<input type="button" onclick="passUpd(${yonghu.id})" value="初始密码"/>								 
					</td>
				</tr>
				</c:forEach>
			</table>
	</body>
</html>
