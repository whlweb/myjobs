<%@page import="dao.BillDao"%>
<%@page import="entity.Bill"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<jsp:useBean id="billdao" class="dao.BillDao" scope="page" />

</head>

<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>
<body>
	<%
		//找到需要删除的订单对象
		request.setCharacterEncoding("utf-8");

		String row = request.getParameter("row");

		boolean flag = billdao.delete(row);
		if (flag) {
			out.println("<script language=\"javascript\">alert(\"删除成功！\");	window.location=\"admin_bill_list.jsp\";</script>");

		} else {
			out.println("<script language=\"javascript\">alert(\"删除失败！\");	history.go(-1);</script>");

		}
	%>
</body>
</html>
