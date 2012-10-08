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
</head>
<body>
	<table border="1">
				<tr>
					<th>电影名称</th>
					<th>类型</th>
					<th>主演</th>
					<th>导演</th>
					<th>价格</th>
				</tr>
				<c:forEach items="${films}" var="film">
					<tr>
						<td>${film.name}</td>
						<td>${film.filmtype.type}</td>
						<td>${film.actor}</td>
						<td>${film.director}</td>
						<td align="center">${film.price}</td>
					</tr>
				</c:forEach>
			</table>
	<script type="text/javascript">
		
	</script>
</body>
</html>
