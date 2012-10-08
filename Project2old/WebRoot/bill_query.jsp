<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="entity.*"%>
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
<jsp:useBean id="billdao" class="dao.BillDao" scope="page" />
<jsp:useBean id="supplierdao" class="dao.SupplierDao" scope="page" />
</head>
<body>
	<div class="main">
		<div class="optitle clearfix">
			<em><input type="button" name="button" value="返回"
				class="input-button" onclick="location.href='admin_bill_list.jsp'" />
			</em>
			<div class="title">查询结果&gt;&gt;</div>
		</div>
		<div class="content">
			<table class="list">
				<tr>
					<td>账单编号</td>
					<td>商品名称</td>
					<td>商品数量</td>
					<td>交易金额</td>
					<td>是否付款</td>
					<td>供应商名称</td>
					<td>商品描述</td>
					<td>账单时间</td>
				</tr>
				<%
					request.setCharacterEncoding("utf-8");
					List<Supplier> suppliers = supplierdao.getSupplierList();
					request.setAttribute("suppliers", suppliers);
					String name = request.getParameter("billname");
					String payed = request.getParameter("payed");
					List<Bill> bills = billdao.query(name, payed);
					request.setAttribute("bills", bills);
					if (bills.size() == 0) {
						out.println("<script language=\"javascript\">alert(\"没有查询到相应的订单，请重试！\");	history.go(-1);</script>");

					}
				%>
				<c:forEach items="${bills}" var="bill">
					<tr>
						<td>${bill.id}</td>
						<td><a href="bill_edit.jsp?row=${bill.id}">${bill.name}</a></td>
						<td>${bill.amount}</td>
						<td>${bill.money}</td>
						<td><c:choose>
								<c:when test="${bill.payed==1}">
					    已付款
					  </c:when>
								<c:when test="${bill.payed==0}">
					    未付款
					  </c:when>
							</c:choose>
						</td>
						<td><c:forEach items="${suppliers}" var="supplier">
								<c:if test="${bill.supplier_id==supplier.id}">${supplier.name}</c:if>
							</c:forEach>
						</td>
						<td>${bill.description }</td>
						<td>${bill.date }</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>
