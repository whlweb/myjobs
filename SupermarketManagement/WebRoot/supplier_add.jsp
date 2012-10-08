<%@page import="dao.SupplierDao"%>
<%@page import="entity.Supplier"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<jsp:useBean id="supplierdao" class="dao.SupplierDao" scope="page" />

</head>

<body>
	<%
		request.setCharacterEncoding("utf-8");

	int id = Integer.parseInt(request.getParameter("id"));
	String name = request.getParameter("name");
	String description = request.getParameter("description");
	String contact = request.getParameter("contact");
	String phone= request.getParameter("phone");
	String fax = request.getParameter("fax");
	String address = request.getParameter("address");

	// 封装成供应商信息对象
	Supplier supplier = new Supplier();
	supplier.setAddress(address);
	supplier.setContact(contact);
	supplier.setDescription(description);
	supplier.setFax(fax);
	supplier.setId(id);
	supplier.setName(name);
	supplier.setPhone(phone);


		boolean flag = supplierdao.add(supplier);
		if (flag) {
			out.println("<script language=\"javascript\">alert(\"添加成功！\");	window.location=\"supplierAdmin.jsp\";</script>");

			} else {
			out.println("<script language=\"javascript\">alert(\"添加失败！\");	history.go(-1);</script>");

		}
	%>


</body>
</html>
