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
	<form method="post" action="query.do">
		电影名称：<input type="text" name="name" />
		<hr>
		类型：<select name="type">
			<option value="">全部</option>
			<c:forEach items="${types}" var="t">
				<option value="${t.tid}">${t.type}</option>
			</c:forEach>
		</select>
		<hr>
		主演：<input type="text" name="actor" />
		<hr>
		导演：<input type="text" name="director" />
		<hr>
		价格：<input type="text" name="price1" />到<input type="text"
			name="price2" />
		<hr>
		<input type="submit" name="submit" value="组合查询" class="button" />
	</form>
	<script type="text/javascript">
		
	</script>
</body>
</html>
