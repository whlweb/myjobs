<%@page import="dao.UserDao"%>
<%@page import="entity.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<jsp:useBean id="userdao" class="dao.UserDao" scope="page" />

</head>

<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>
<body>
	<%
		//找到需要修改的用户对象
			request.setCharacterEncoding("utf-8");
			
		String reviseid = request.getParameter("reviseid");
		if (reviseid != null) {
			int id = Integer.parseInt(request.getParameter("id"));
			String username = request.getParameter("username");
			int gender = Integer.parseInt(request.getParameter("gender"));
			int age = Integer.parseInt(request.getParameter("age"));
			String phone = request.getParameter("phone");
			String address = request.getParameter("address");
			String role = request.getParameter("role");

			// 封装成用户信息对象
			User user = new User();
			user.setAddress(address);
			user.setAge(age);
			user.setGender(gender);
			user.setId(id);
			user.setPhone(phone);
			user.setRole(role);
			user.setUsername(username);

			boolean flag = userdao.update(user, reviseid);
			if (flag) {
			session.setAttribute("user", user);
		out.println("<script language=\"javascript\">alert(\"修改成功！\");	window.location=\"userAdmin2.jsp\";</script>");

			} else {
		out.println("<script language=\"javascript\">alert(\"修改失败！\");	history.go(-1);</script>");

			}
		}
	%>


	<div class="main">
		<div class="optitle clearfix">
			<div class="title">用户管理&gt;&gt;</div>
		</div>
		<form method="post" id="myform"
			action="user_revise2.jsp?reviseid=${user.id}"
			onsubmit="return check()">
			<div class="content">
				<table class="box">
					<tr>
						<td class="field">用户编号：</td>
						<td><input type="text" name="id" id="id" class="text"
							onblur="isidLegal()" value="${user.id}" /> <font color="red">*</font><span
							id="iderror"></span>
						</td>

					</tr>
					<tr>
						<td class="field">用户名称：</td>
						<td><input type="text" name="username" class="text"
							id="username" onblur="isusernameLegal()" value="${user.username}" />
							<font color="red">*</font><span id="usernameerror"></span>
						</td>
					</tr>
					<tr>
						<td class="field">用户性别：</td>
						<td><select name="gender" id="gender">
								<option id="female" value="0">女</option>
								<option id="male" value="1">男</option>
						</select>
						</td>
					</tr>

					<tr>
						<td class="field">用户年龄：</td>
						<td><input type="text" name="age" class="text" id="age"
							onblur="isageLegal()" value="${user.age}" /> <font color="red">*</font><span
							id="ageerror"></span>
						</td>
					</tr>
					<tr>
						<td class="field">用户电话：</td>
						<td><input type="text" name="phone" class="text" id="phone"
							onblur="isphoneLegal()" value="${user.phone}" /> <font
							color="red">*</font><span id="phoneerror"></span>
						</td>

					</tr>
					<tr>
						<td class="field">用户地址：</td>
						<td><textarea name="address" id="address" class="text"
								cols="45" rows="5">${user.address}</textarea>
						</td>
					</tr>
					<tr>
						<td class="field">用户权限：</td>

						<td><input type="radio" name="role" id="user" value="user"
							 disabled="disabled"/>普通用户</td>
					</tr>
				</table>
			</div>
			<div class="buttons">
				<input type="submit" name="submit" value="确认" class="input-button" />
				<input type="button" name="button" value="返回" class="input-button"
					onclick="history.back();" />
			</div>
		</form>
	</div>
	<script type="text/javascript">
		function select() {
			if ('${user.gender}'==1) {
				document.getElementById("male").selected = true;
			} else {
				document.getElementById("female").selected = true;
			}
		}

		function radio() {
			if ('${user.role}'=="admin") {
				document.getElementById("admin").checked = true;
			} else {
				document.getElementById("user").checked = true;
			}

		}

		select();
		radio();
		
		//全部输入项非空检查、格式验证
		function isidLegal() {
			var id = document.getElementById("id").value;
			var regEx = /^\d+$/;
			var iderror = document.getElementById("iderror");

			if (id == null || id == "") {
				iderror.innerHTML = "<font color=\"red\">用户编号不能为空！</font>";
				return false;
			} else if (!regEx.test(id)) {
				iderror.innerHTML = "<font color=\"red\">用户编号只能是数字！</font>";
				return false;
			}
			iderror.innerHTML = "";
			return true;
		}

		//检查用户名
		function isusernameLegal() {
			var username = document.getElementById("username").value;
			var usernameerror = document.getElementById("usernameerror");
			var regEx = /^\w*[\u4E00-\u9FFF]*$/;
			if (username == null || username == "") {
				usernameerror.innerHTML = "<font color=\"red\">用户名不能为空！</font>";
				return false;
			} else if (!regEx.test(username)) {
				usernameerror.innerHTML = "<font color=\"red\">用户名包含非法字符！</font>";
				return false;
			}
			usernameerror.innerHTML = "";
			return true;
		}
		//检查用户年龄
		function isageLegal() {
			var age = document.getElementById("age").value;
			var regEx = /^\d+$/;
			var ageerror = document.getElementById("ageerror");

			if (age == null || age == "") {
				ageerror.innerHTML = "<font color=\"red\">用户年龄不能为空！</font>";
				return false;
			} else if (!regEx.test(age)) {
				ageerror.innerHTML = "<font color=\"red\">用户年龄只能是数字！</font>";
				return false;
			}
			ageerror.innerHTML = "";
			return true;
		}
		//检查用户电话
		function isphoneLegal() {
			var phone = document.getElementById("phone").value;
			var regEx = /^\d+\-?\d+$/;
			var phoneerror = document.getElementById("phoneerror");

			if (phone == null || phone == "") {
				phoneerror.innerHTML = "<font color=\"red\">用户电话不能为空！</font>";
				return false;
			} else if (!regEx.test(phone)||phone.length<7) {
				phoneerror.innerHTML = "<font color=\"red\">电话号码格式错误！</font>";
				return false;
			}
			phoneerror.innerHTML = "";
			return true;
		}		
		//提醒用户
		function remind() {
			var c = confirm('确认修改用户信息吗？');
			return c;
		}
		function check() {
			var id = document.getElementById("id");
			if (!isidLegal()) {
				id.focus();
				return false;
			} else if (!isusernameLegal()) {
				id.focus();
				return false;
			} else if (!isageLegal()) {
				id.focus();
				return false;
			} else if (!isphoneLegal()) {
				id.focus();
				return false;
			} else if (!remind()) {
				id.focus();
				return false;
			}

			return true;
		}
	</script>
</body>
</html>
