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
<jsp:useBean id="supplierdao" class="dao.SupplierDao" scope="page" />
<link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>
	<div class="menu">

		<table>
			<tbody>
				<tr>
					<td><form method="post" action="supplier_query.jsp">
							供应商名称：<input name="name" class="input-text" type="text">
							&nbsp;&nbsp;&nbsp;&nbsp;供应商描述：<input name="description"
								class="input-text" type="text">&nbsp;&nbsp;&nbsp;&nbsp;<input
								value="组 合 查 询" type="submit">
						</form>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="main">
		<div class="optitle clearfix">
			<em><input value="添加数据" class="input-button"
				onclick="window.location='supplierAdd.jsp'" type="button">
			</em>
			<div class="title">供应商管理&gt;&gt;</div>
		</div>

		<div class="content">
			<table class="list">
				<tbody>
					<tr>
						<td width="70" height="29"><div class="STYLE1" align="center">编号</div>
						</td>
						<td width="80"><div class="STYLE1" align="center">供应商名称</div>
						</td>
						<td width="100"><div class="STYLE1" align="center">供应商描述</div>
						</td>
						<td width="100"><div class="STYLE1" align="center">联系人</div>
						</td>

						<td width="100"><div class="STYLE1" align="center">电话</div>
						</td>
						<td width="100"><div class="STYLE1" align="center">地址</div>
						</td>
					</tr>
					<%
						List<Supplier> suppliers = supplierdao.getSupplierList();
						request.setAttribute("suppliers", suppliers);
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