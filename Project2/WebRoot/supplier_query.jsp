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
</head>
<body>
	<div class="main">
		<div class="optitle clearfix">
			<em><input type="button" name="button" value="返回"
				class="input-button" onclick="window.location='supplier.do?flag=list';"  />
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
