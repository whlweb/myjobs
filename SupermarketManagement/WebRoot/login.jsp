<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=utf-8"
	import="java.util.*" pageEncoding="UTF-8"%>

<html>
<head>
<title>系统登录 - 超市账单管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>
<%
	String login = request.getParameter("login");
	if ("exit".equals(login))
		session.invalidate();
%>
<body class="blue-style">
	<div id="login">
		<div class="icon"></div>
		<div class="login-box">
			<form method="post" action="admin_index.jsp"
				onsubmit="return check()">
				<dl>
					<dt>用户名</dt>
					<dd>
						<input type="text" name="userName" id="username"
							class="input-text" onblur="isUsernameLegal()" /><span
							id="usernull"></span>
					</dd>
					<dt>密 码</dt>
					<dd>
						<input type="password" name="passWord" id="password"
							class="input-text" onblur="isPasswordLegal()" /><span
							id="nullpassword"></span>
					</dd>
				</dl>
				<div class="buttons">
					<input type="submit" name="submit" value="登录系统"
						class="input-button" /> <input type="reset" name="reset"
						value="重    填" class="input-button" />
				</div>
			</form>
			<div align="right">

				<%
					if ("empty".equals(login)) {
						out.print("<font color=red>您还没有登陆！</font>");
					} else if ("error".equals(login)) {
						out.print("<font color=red>用户名或密码输入有误！</font>");
					}
				%>
			</div>

		</div>
	</div>
	<script type="text/javascript">
		//全部输入项非空检查
		//检查用户名是否存在
		function isUsernameLegal() {
			var username = document.getElementById("username").value;
			if (username == null || username == "") {
				usernull.innerHTML = "<font color=\"red\">用户名不能为空！</font>";
				return false;
			}
			usernull.innerHTML = "";
			return true;
		}
		//检查密码内容位数
		function isPasswordLegal() {
			var password = document.getElementById("password").value;
			var pwdnull = document.getElementById("nullpassword");

			if (password == null || password == "") {
				pwdnull.innerHTML = "<font color=\"red\">密码不能为空！</font>";
				return false;
			} else if (password.length < 5) {
				pwdnull.innerHTML = "<font color=\"red\">密码长度至少5位！！</font>";
				return false;
			} else
				pwdnull.innerHTML = "";
			return true;
		}

		function check() {
			var username = document.getElementById("username");
			if (!isUsernameLegal()) {
				username.focus();
				return false;
			} else if (!isPasswordLegal()) {
				username.focus();
				return false;
			}
			return true;
		}
	</script>

</body>
</html>
