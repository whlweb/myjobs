<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="entity.User"%>
<%@ page language="java" contentType="text/html;charset=utf-8"
	import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link type="text/css" rel="stylesheet" href="css/style.css" />
<jsp:useBean id="userdao" class="dao.UserDao" scope="page" />
</head>
<body>
	<div class="main">
		<div class="optitle clearfix">
			<em><input type="button" name="button" value="返回"
				class="input-button" onclick="location.href='userAdmin.jsp'" />
			</em>
			<div class="title">查询结果&gt;&gt;</div>
		</div>
		<div class="content">
			<table class="list">
				<tbody>
					<tr>
						<td width="70" height="29"><div class="STYLE1" align="center">编号</div>
						</td>
						<td width="80"><div class="STYLE1" align="center">用户名称</div>
						</td>
						<td width="100"><div class="STYLE1" align="center">性别</div></td>
						<td width="100"><div class="STYLE1" align="center">年龄</div></td>

						<td width="150"><div class="STYLE1" align="center">电话</div></td>
						<td width="150"><div class="STYLE1" align="center">地址</div></td>
						<td width="150"><div class="STYLE1" align="center">权限</div></td>
					</tr>

					<%
				    request.setCharacterEncoding("utf-8");
					String username = request.getParameter("username");
					List<User> users = userdao.query(username);
					request.setAttribute("users", users);
					if (users.size() == 0) {
						out.println("<script language=\"javascript\">alert(\"没有查询到相应的用户，请重试！\");	history.go(-1);</script>");

					}
				%>
					<c:forEach items="${users}" var="user">
						<tr>
							<td>${user.id}</td>
							<td><a href="user_edit.jsp?row=${user.id}">${user.username}</a>
							</td>
							<td><c:choose>
									<c:when test="${user.gender==1}">
					    男
					  </c:when>
									<c:when test="${user.gender==0}">
					    女
					  </c:when>
								</c:choose></td>
							<td>${user.age}</td>
							<td>${user.phone }</td>
							<td>${user.address}</td>
							<td><c:choose>
									<c:when test="${user.role == 'admin'}">
					    系统管理员
					  </c:when>
									<c:when test="${user.role=='user'}">
					    普通用户
					  </c:when>
								</c:choose></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>
