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
	<div class="main">
		<div class="optitle clearfix">
			<div class="title">供应商管理&gt;&gt;</div>
		</div>
		<form id="form1" name="form1" method="post" action="supplier.do?flag=add"
			onsubmit="return check();">
			<div class="content">
				<font color="red"></font>
				<table class="box">
					<tbody>
						<tr>
							<td class="field">供应商编号：</td>
							<td><input name="id" id="id" class="text" type="text" onblur="isidLegal()"/>
								<font color="red">*</font><span id="iderror"></span>
							</td>
						</tr>
						<tr>
							<td class="field">供应商名称：</td>
							<td><input name="name" id="name" value="" class="text"
								type="text" onblur="isnameLegal()"/> <font color="red">*</font><span
								id="nameerror"></span>
							</td>
						</tr>
						<tr>
							<td class="field">供应商描述：</td>
							<td><textarea name="description" id="description" cols="45"
									rows="5"></textarea>
							</td>
						</tr>
						<tr>
							<td class="field">供应商联系人：</td>

							<td><input name="contact" id="contact" value="" class="text"
								type="text">
							</td>
						</tr>
						<tr>
							<td class="field">供应商电话：</td>
							<td><input name="phone" id="phone" value="" class="text"
								type="text">
							</td>
						</tr>
						<tr>
							<td class="field">供应商传真：</td>

							<td><input name="fax" id="fax" value="" class="text"
								type="text">
							</td>
						</tr>
						<tr>
							<td class="field">供应商地址：</td>
							<td><input name="address" id="address" value="" class="text"
								type="text">
							</td>
						</tr>
					</tbody>
				</table>
			</div>

			<div class="buttons">
				<input name="button" id="button" value="提交" class="input-button"
					type="submit"> <input name="button" id="button"
					onclick="window.location='supplier.do?flag=list';" value="返回"
					class="input-button" type="button">
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
				iderror.innerHTML = "<font color=\"red\">供应商编号不能为空！</font>";
				return false;
			} else if (!regEx.test(id)) {
				iderror.innerHTML = "<font color=\"red\">供应商编号只能是数字！</font>";
				return false;
			}
			iderror.innerHTML = "";
			return true;
		}

		//检查供应商名
		function isnameLegal() {
			var name = document.getElementById("name").value;
			var nameerror = document.getElementById("nameerror");
			if (name == null || name == "") {
				nameerror.innerHTML = "<font color=\"red\">供应商名不能为空！</font>";
				return false;
			}
			nameerror.innerHTML = "";
			return true;
		}

		//提醒
		function remind() {
			var c = confirm('确认添加供应商信息吗？');
			return c;
		}

		function check() {
			var id = document.getElementById("id");
			if (!isidLegal()) {
				id.focus();
				return false;
			} else if (!isnameLegal()) {
				id.focus();
				return false;
			}
			return true;
		}
	</script>
</body>
</html>
