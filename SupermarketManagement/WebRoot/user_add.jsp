<%@page import="dao.UserDao"%>
<%@page import="entity.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<jsp:useBean id="userdao" class="dao.UserDao" scope="page" />

</head>

<body>
	<%
		request.setCharacterEncoding("utf-8");

		int id = Integer.parseInt(request.getParameter("id"));
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		int gender = Integer.parseInt(request.getParameter("gender"));
		int age = Integer.parseInt(request.getParameter("age"));
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String role = request.getParameter("role");

		// 封装成用户信息对象
		User user = new User();
		user.setAddress(address);
		user.setAge(age);
		user.setGender(gender);
		user.setId(id);
		user.setPassword(password);
		user.setPhone(phone);
		user.setRole(role);
		user.setUsername(username);

		boolean flag = userdao.add(user);
		if (flag) {
			out.println("<script language=\"javascript\">alert(\"添加成功！\");	window.location=\"userAdmin.jsp\";</script>");

			} else {
			out.println("<script language=\"javascript\">alert(\"添加失败！\");	history.go(-1);</script>");

		}
	%>


</body>
</html>
