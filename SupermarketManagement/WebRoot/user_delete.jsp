<%@page import="dao.UserDao"%>
<%@page import="entity.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<jsp:useBean id="userdao" class="dao.UserDao" scope="page" />

</head>

<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>
<body>
	<%
		//找到需要删除的用户对象
		request.setCharacterEncoding("utf-8");

		String row = request.getParameter("row");

		boolean flag = userdao.delete(row);
		if (flag) {
			out.println("<script language=\"javascript\">alert(\"用户删除成功！\");	window.location=\"userAdmin.jsp\";</script>");

		} else {
			out.println("<script language=\"javascript\">alert(\"用户删除失败！\");	history.go(-1);</script>");

		}
	%>
</body>
</html>
