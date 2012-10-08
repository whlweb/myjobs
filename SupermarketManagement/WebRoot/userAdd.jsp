<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="entity.User"%>
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
<jsp:useBean id="userdao" class="dao.UserDao" scope="page" />
</head>
<body>
	<div class="main">
		<div class="optitle clearfix">
			<div class="title">用户管理&gt;&gt;</div>

		</div>
		<form id="form1" name="form1" method="post" action="user_add.jsp"
			onsubmit="return check()">
			<div class="content">
				<table class="box">
					<tr>
						<td class="field">用户编号：</td>
						<td><input type="text" name="id" id="id" class="text"
							onblur="isidLegal()" /> <font color="red">*</font><span
							id="iderror"></span></td>

					</tr>
					<tr>
						<td class="field">用户名称：</td>
						<td><input type="text" name="username" class="text"
							id="username" onblur="isusernameLegal()" /> <font color="red">*</font><span
							id="usernameerror"></span></td>
					</tr>
					<tr>
						<td class="field">用户密码：</td>

						<td><input type="password" name="password" class="text"
							id="password" onblur="ispasswordLegal()" /> <font color="red">*</font><span
							id="passworderror"></span></td>
					</tr>
					<tr>
						<td class="field">确认密码：</td>
						<td><input type="password" name="password2" id="password2"
							class="text" onblur="ispassword2Legal()" /> <font color="red">*</font><span
							id="password2error"></span></td>
					</tr>

					<tr>
						<td class="field">用户性别：</td>
						<td><select name="gender" id="gender">
								<option value="0">女</option>
								<option value="1">男</option>
						</select></td>
					</tr>

					<tr>
						<td class="field">用户年龄：</td>
						<td><input type="text" name="age" class="text" id="age"
							onblur="isageLegal()" /> <font color="red">*</font><span
							id="ageerror"></span></td>
					</tr>
					<tr>
						<td class="field">用户电话：</td>
						<td><input type="text" name="phone" class="text" id="phone"
							onblur="isphoneLegal()" /> <font color="red">*</font><span
							id="phoneerror"></span></td>

					</tr>
					<tr>
						<td class="field">用户地址：</td>
						<td><textarea name="address" id="address" class="text"
								cols="45" rows="5"></textarea></td>
					</tr>
					<tr>
						<td class="field">用户权限：</td>

						<td><input type="radio" name="role" id="role" value="user"
							checked="checked" />普通用户 <input type="radio" name="role"
							id="role" value="admin" />系统管理员</td>
					</tr>
				</table>
			</div>
			<div class="buttons">
				<input type="submit" name="button" id="button" value="数据提交"
					class="input-button" /> <input type="button" name="button"
					id="button" onclick="window.location='user.do';" value="返回"
					class="input-button" />
			</div>
		</form>
	</div>
	<script type="text/javascript">
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
		//检查密码
		function ispasswordLegal() {
			var password = document.getElementById("password").value;
			var regEx = /^\w+$/;
			var passworderror = document.getElementById("passworderror");
			if (password == null || password == "") {
				passworderror.innerHTML = "<font color=\"red\">密码不能为空！</font>";
				return false;
			} else if (password.length < 5) {
				passworderror.innerHTML = "<font color=\"red\">密码长度小于5位！！</font>";
				return false;
			} else if (!regEx.test(password)) {
				passworderror.innerHTML = "<font color=\"red\">密码包含非法字符！</font>";
				return false;

			}
			passworderror.innerHTML = "";
			return true;
		}

		//检查重复密码
		function ispassword2Legal() {
			var password = document.getElementById("password").value;
			var password2 = document.getElementById("password2").value;
			var password2error = document.getElementById("password2error");
			if (password!=password2) {
				password2error.innerHTML = "<font color=\"red\">两次密码输入不一致！</font>";
				return false;
			} 
			password2error.innerHTML = "";
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
			var c = confirm('确认添加用户信息吗？');
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
			} else if (!ispasswordLegal()) {
				id.focus();
				return false;
			} else if (!ispassword2Legal()) {
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
