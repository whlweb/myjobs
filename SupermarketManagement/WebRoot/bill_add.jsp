<%@page import="dao.BillDao"%>
<%@page import="entity.Bill"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<jsp:useBean id="billdao" class="dao.BillDao" scope="page" />

</head>

<body>
	<%
		request.setCharacterEncoding("utf-8");

		int id = Integer.parseInt(request.getParameter("billNum"));
		String name = request.getParameter("name");
		int amount = Integer.parseInt(request.getParameter("amount"));
		double money = Double.parseDouble(request.getParameter("money"));
		String unit = request.getParameter("unit");
		int payed = Integer.parseInt(request.getParameter("payed"));
		int supplier_id = Integer.parseInt(request.getParameter("supplier"));
		String description = request.getParameter("description");

		// 封装成账单信息对象
		Bill bill = new Bill();
		bill.setAmount(amount);
		bill.setDescription(description);
		bill.setId(id);
		bill.setMoney(money);
		bill.setName(name);
		bill.setPayed(payed);
		bill.setSupplier_id(supplier_id);
		bill.setUnit(unit);
		boolean flag = billdao.add(bill);
		if (flag) {
			out.println("<script language=\"javascript\">alert(\"添加成功！\");	window.location=\"admin_bill_list.jsp\";</script>");

			} else {
			out.println("<script language=\"javascript\">alert(\"添加失败！\");	history.go(-1);</script>");
				
			}
		%>
	
	
</body>
</html>
