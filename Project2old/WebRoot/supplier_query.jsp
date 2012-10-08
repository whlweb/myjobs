<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="entity.Supplier"%>
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
<jsp:useBean id="supplierdao" class="dao.SupplierDao" scope="page" />
</head>
<body>
	<div class="main">
		<div class="optitle clearfix">
			<em><input type="button" name="button" value="返回"
				class="input-button" onclick="location.href='supplierAdmin.jsp'" />
			</em>
			<div class="title">查询结果&gt;&gt;</div>
		</div>
		<div class="content">
			<table class="list">
				<tbody>
					<tr>
						<td width="70" height="29"><div class="STYLE1" align="center">供应商编号</div>
						</td>
						<td width="80"><div class="STYLE1" align="center">供应商名称</div>
						</td>
						<td width="100"><div class="STYLE1" align="center">描述</div>
						</td>
						<td width="150"><div class="STYLE1" align="center">联系人</div>
						</td>
						<td width="150"><div class="STYLE1" align="center">电话</div>
						</td>
						<td width="150"><div class="STYLE1" align="center">地址</div>
						</td>
					</tr>

					<%
						request.setCharacterEncoding("utf-8");
						String q_name = request.getParameter("name");
						String q_description = request.getParameter("description");
						List<Supplier> suppliers = supplierdao.query(q_name, q_description);
						request.setAttribute("suppliers", suppliers);
						if (suppliers.size() == 0) {
							out.println("<script language=\"javascript\">alert(\"没有查询到相应的供应商，请重试！\");	history.go(-1);</script>");

						}
					%>
					<c:forEach items="${suppliers}" var="supplier">
						<tr>
							<td>${supplier.id}</td>
							<td><a href="supplier_edit.jsp?row=${supplier.id}">${supplier.name}</a>
							</td>
							<td>${supplier.description}</td>
							<td>${supplier.contact }</td>
							<td>${supplier.phone}</td>
							<td>${supplier.address}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>
