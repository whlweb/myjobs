<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=utf-8"
	import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>
	<div class="menu">
		<table>
			<tbody>
				<tr>
					<td><form method="post" action="supplier.do?flag=query">
							供应商名称：<input name="name" class="input-text" type="text">
							&nbsp;&nbsp;&nbsp;&nbsp;供应商描述：<input name="description"
								class="input-text" type="text">&nbsp;&nbsp;&nbsp;&nbsp;<input
								value="组 合 查 询" type="submit">
						</form></td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="main">
		<div class="optitle clearfix">
			<em><input value="添加数据" class="input-button"
				onclick="window.location='supplierAdd.jsp'" type="button"> </em>
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

						<td width="100"><div class="STYLE1" align="center">电话</div></td>
						<td width="100"><div class="STYLE1" align="center">地址</div></td>
					</tr>
					<c:forEach items="${suppliers}" var="supplier">
						<tr>
							<td>${supplier.id}</td>
							<td><a href="supplier.do?flag=edit&row=${supplier.id}">${supplier.name}</a>
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
		<form action="supplier.do" method="post" id="navbtns">
			<input type="hidden" name="flag" value="list"> <input
				type="hidden" name="page" id="pageNo" value="1" />
			<div align="center">
				<ul>
					<li>共${supplierCount}条记录&nbsp;&nbsp; ${currPage
						}/${supplierPageCount }页</li>
					<c:if test="${currPage>1}">
						<a href="supplier.do?flag=list&page=1">首页</a>
						<a href="supplier.do?flag=list&page=${currPage-1}">上一页</a>
					</c:if>
					<c:if test="${currPage<supplierPageCount}">
						<a href="supplier.do?flag=list&page=${currPage+1}">下一页</a>
						<a href="supplier.do?flag=list&page=${supplierPageCount}">最后一页</a>
					</c:if>
				</ul>
				跳转至 <input type="text" name="inputPage" id="inputPage" size="2"/>页
				<button type="button"
					onclick='pageNav(document.getElementById("inputPage").value)'>GO</button>

			</div>
		</form>
	</div>
	<script type="text/javascript">
		function pageNav(inputNo) {
			var form = document.getElementById("navbtns");
			var page = document.getElementById("pageNo");
			var num = parseInt(inputNo);
			if (isNaN(num)) {
				alert('请输入正确的数字！');
				return false;
			} else if (num < 1) {
				page.value = 1;
			} else if (num > "${supplierPageCount}") {
				page.value = "${supplierPageCount}";
			} else {
				page.value = num;
			}
			form.submit();
		}
	</script>
</body>
</html>