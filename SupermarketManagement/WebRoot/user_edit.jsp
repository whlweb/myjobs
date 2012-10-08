<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="entity.User"%>
<%@ page language="java" contentType="text/html;charset=utf-8"
	import="java.util.*" pageEncoding="utf-8"%>
<jsp:useBean id="userdao" class="dao.UserDao" scope="page" />


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>
<body>
	<%
		//找到需要修改的用户对象
			String id = request.getParameter("row");
		List<User> users = userdao.getUserList();
		request.setAttribute("users", users);
		User u = null;
		for (User user : users) {
			if (user.getId() == Integer.parseInt(id)) {
		u = user;
		request.setAttribute("user", u);
		break;
			}
		}
	%>

	<div class="main">
		<div class="optitle clearfix">
			<div class="title">用户管理&gt;&gt;</div>
		</div>
		<form method="post" id="myform" onsubmit="return check()">
			<div class="content">
				<table class="box">
					<tr>
						<td class="field">用户编号：</td>
						<td><input type="text" name="id" id="id" class="text"
							onblur="isidLegal()" value="${user.id}" disabled="disabled" /> <font
							color="red">*</font><span id="iderror"></span></td>

					</tr>
					<tr>
						<td class="field">用户名称：</td>
						<td><input type="text" name="username" class="text"
							id="username" onblur="isusernameLegal()" value="${user.username}"
							disabled="disabled" /> <font color="red">*</font><span
							id="usernameerror"></span></td>
					</tr>
					<tr>
						<td class="field">用户性别：</td>
						<td><select name="gender" id="gender" disabled="disabled">
								<option id="female" value="0">女</option>
								<option id="male" value="1">男</option>
						</select></td>
					</tr>

					<tr>
						<td class="field">用户年龄：</td>
						<td><input type="text" name="age" class="text" id="age"
							onblur="isageLegal()" value="${user.age}" disabled="disabled" />
							<font color="red">*</font><span id="ageerror"></span></td>
					</tr>
					<tr>
						<td class="field">用户电话：</td>
						<td><input type="text" name="phone" class="text" id="phone"
							onblur="isphoneLegal()" value="${user.phone}" disabled="disabled" />
							<font color="red">*</font><span id="phoneerror"></span></td>

					</tr>
					<tr>
						<td class="field">用户地址：</td>
						<td><textarea name="address" id="address" class="text"
								cols="45" rows="5" disabled="disabled">${user.address}</textarea></td>
					</tr>
					<tr>
						<td class="field">用户权限：</td>

						<td><c:choose>
								<c:when test="${user.role == 'admin'}">
					    系统管理员
					  </c:when>
								<c:when test="${user.role=='user'}">
					    普通用户
					  </c:when>
							</c:choose></td>
					</tr>
				</table>
			</div>
			<div class="buttons">
				<a href="userAdmin.jsp" class="input-button">返回</a> 
				<a href="#" class="input-button" onclick="remind()">删除</a>
				<a href="user_revise.jsp?row=${user.id }" class="input-button">修改</a>
				<a href="user_changepassword.jsp?row=${user.id }" class="input-button">修改密码</a>
			</div>
		</form>
	</div>
	<script type="text/javascript">
		function remind() {
			var c = confirm('确认删除用户信息吗？');
			if (c) {
				location.href = "user_delete.jsp?row=${user.id }";
			}
		}
		function select() {
			if ('${user.gender}'==1) {
				document.getElementById("male").selected = true;
			} else {
				document.getElementById("female").selected = true;
			}
		}

		select();
	</script>
</body>
</html>
