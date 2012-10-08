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
		request.setCharacterEncoding("utf-8");
			String row=request.getParameter("row");
		    String reviseid = request.getParameter("reviseid");
		    if(reviseid!=null&&!reviseid.equals("")){
		    	String oldpw=request.getParameter("oldpassword");
		    	System.out.println(oldpw);
		    	String newpw=request.getParameter("password");
		    	System.out.println(newpw);
		    	boolean flag=userdao.changepw(reviseid, oldpw, newpw);
		    	if (flag) {
					out.println("<script language=\"javascript\">alert(\"密码修改成功！\");	window.location=\"userAdmin.jsp\";</script>");

					} else {
					out.println("<script language=\"javascript\">alert(\"密码修改失败！\");	window.location=\"user_changepassword.jsp?row="+reviseid+"\";</script>");

				}
		    }
	
	%>


	<div class="main">
		<div class="optitle clearfix">
			<div class="title">用户管理&gt;&gt;</div>
		</div>
		<form method="post" id="myform"
			action="user_changepassword.jsp?reviseid=<%=row %>"
			onsubmit="return check()">
			<div class="content">
				<table class="box">
					<tr>
						<td class="field">旧的密码：</td>

						<td><input type="password" name="oldpassword" class="text"
							id="oldpassword" onblur="isoldpasswordLegal()" /> <font
							color="red">*</font><span id="oldpassworderror"></span></td>
					</tr>
					<tr>
						<td class="field">新的密码：</td>

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
		function isoldpasswordLegal() {
			var oldpassword = document.getElementById("oldpassword").value;
			var regEx = /^\w+$/;
			var oldpassworderror = document.getElementById("oldpassworderror");
			if (oldpassword == null || oldpassword == "") {
				oldpassworderror.innerHTML = "<font color=\"red\">密码不能为空！</font>";
				return false;
			} else if (oldpassword.length < 5) {
				oldpassworderror.innerHTML = "<font color=\"red\">密码长度小于5位！！</font>";
				return false;
			} else if (!regEx.test(oldpassword)) {
				oldpassworderror.innerHTML = "<font color=\"red\">密码包含非法字符！</font>";
				return false;

			}
			oldpassworderror.innerHTML = "";
			return true;
		}
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
			if (password != password2) {
				password2error.innerHTML = "<font color=\"red\">两次密码输入不一致！</font>";
				return false;
			}
			password2error.innerHTML = "";
			return true;
		}

		function check() {
			var oldpassword = document.getElementById("oldpassword");
			if (!isoldpasswordLegal()) {
				oldpassword.focus();
				return false;
			} else if (!ispasswordLegal()) {
				oldpassword.focus();
				return false;
			} else if (!ispassword2Legal()) {
				oldpassword.focus();
				return false;
			}
			return true;
		}
	</script>
</body>
</html>
