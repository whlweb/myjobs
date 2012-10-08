<%@page import="dao.SupplierDao"%>
<%@page import="entity.Supplier"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<jsp:useBean id="supplierdao" class="dao.SupplierDao" scope="page" />

</head>

<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>
<body>
	<%
		//找到需要删除的供应商对象
		request.setCharacterEncoding("utf-8");

		String row = request.getParameter("row");

		boolean flag = supplierdao.delete(row);
		if (flag) {
			out.println("<script language=\"javascript\">alert(\"供应商删除成功！\");	window.location=\"supplierAdmin.jsp\";</script>");

		} else {
			out.println("<script language=\"javascript\">alert(\"供应商删除失败！\");	history.go(-1);</script>");

		}
	%>
</body>
</html>
