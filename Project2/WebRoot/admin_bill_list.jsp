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
<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>
<body>
	<div class="menu">
		<form method="post" action="bill.do">
			<input name="flag" value="query" type="hidden" /> 商品名称：<input
				type="text" name="billname" class="input-text" />&nbsp;&nbsp;&nbsp;&nbsp;
			是否付款：<select name="payed">
				<option value="">请选择</option>
				<option value="1">已付款</option>
				<option value="0">未付款</option>
			</select>&nbsp;&nbsp;&nbsp;&nbsp; <input type="submit" name="submit"
				value="组合查询" class="button" />
		</form>
	</div>
	<div class="main">
		<div class="optitle clearfix">
			<em><input type="button" name="button" value="添加数据"
				class="input-button" onclick="location.href='modify.jsp'" /> </em>
			<div class="title">账单管理&gt;&gt;</div>
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
				<c:forEach items="${bills}" var="bill">
					<tr>
						<td>${bill.id}</td>
						<td><a href="bill.do?flag=edit&row=${bill.id}">${bill.name}</a>
						</td>
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
							</c:forEach></td>
						<td>${bill.description }</td>
						<td>${bill.date }</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<form action="bill.do" method="post" id="navbtns">
			<input type="hidden" name="flag" value="list"> <input
				type="hidden" name="page" id="pageNo" value="1"/>
			<div align="center">
				<ul>
					<li>共${billCount}条记录&nbsp;&nbsp; ${currPage }/${billPageCount
						}页</li>
					<c:if test="${currPage>1}">
						<a href="bill.do?flag=list&page=1">首页</a>
						<a href="bill.do?flag=list&page=${currPage-1}">上一页</a>
					</c:if>
					<c:if test="${currPage<billPageCount}">
						<a href="bill.do?flag=list&page=${currPage+1}">下一页</a>
						<a href="bill.do?flag=list&page=${billPageCount}">最后一页</a>
					</c:if>
				</ul>
				跳转至 <input type="text" name="inputPage" id="inputPage" size="2">页 
					<button type="button" onclick='pageNav(document.getElementById("inputPage").value)'>GO</button> 
				
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
			} else if (num > "${billPageCount}") {
				page.value = "${billPageCount}";
			} else {
				 page.value= num;
			}
			form.submit();
		}


	</script>
</body>
</html>
