<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="entity.Supplier"%>
<%@ page language="java" contentType="text/html;charset=utf-8"
	import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="billdao" class="dao.BillDao" scope="page" />
<jsp:useBean id="supplierdao" class="dao.SupplierDao" scope="page" />


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>
<body>
	<br>
	<div class="menu">
		<form method="get" action="">
			商品名称：<input type="text" name="productName" class="input-text" />&nbsp;&nbsp;&nbsp;&nbsp;
			是否付款：<select name="payStatus">
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
		<form method="post" id="myform" action="bill_add.jsp"
			onsubmit="return check()">
			<div class="content">
				<table class="box">
					<tr>
						<td class="field">账单编号：</td>
						<td><input type="text" name="billNum" id="billNum"
							class="text" onblur="isidLegal()" /><font color="red"> *</font><span
							id="iderror"></span>
						</td>
					</tr>
					<tr>
						<td class="field">交易金额：</td>
						<td><input type="text" name="money" id="money" class="text"
							onblur="ismoneyLegal()" /><font color="red"> *</font><span
							id="moneyerror"></span>
						</td>
					</tr>
					<tr>
						<td class="field">交易单位：</td>
						<td><input type="text" name="unit" id="unit" class="text"
							onblur="isunitLegal()" /><font color="red"> *</font><span
							id="uniterror"></span>
						</td>
					</tr>
					<tr>
						<td class="field">交易数量：</td>
						<td><input type="text" name="amount" id="amount" class="text"
							onblur="isamountLegal()" /><font color="red"> *</font><span
							id="amounterror"></span>
						</td>
					</tr>
					<tr>
						<td class="field">商品名称：</td>
						<td><input type="text" name="name" class="text" />
						</td>
					</tr>
					<tr>
						<td class="field">商品描述：</td>
						<td><textarea name="description"></textarea>
						</td>
					</tr>
					<tr>

						<td class="field">所属供应商：</td>
						<td><select name="supplier">
								<%
									List<Supplier> suppliers = supplierdao.getSupplierList(); 
									request.setAttribute("suppliers", suppliers);
								%>
								<c:forEach items="${suppliers}" var="supplier">
									<option value="${supplier.id}">${supplier.name}</option>
								</c:forEach>

						</select>
						</td>
					</tr>
					<tr>
						<td class="field">是否付款：</td>
						<td><select name="payed">
								<option value="0">否</option>
								<option value="1">是</option>

						</select>
						</td>
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
		//全部输入项非空检查、格式验证
		function isidLegal() {
			var id = document.getElementById("billNum").value;
			var regEx = /^\d+$/;
			var iderror = document.getElementById("iderror");

			if (id == null || id == "") {
				iderror.innerHTML = "<font color=\"red\">商品编号不能为空！</font>";
				return false;
			} else if (!regEx.test(id)) {
				iderror.innerHTML = "<font color=\"red\">商品编号只能是数字！</font>";
				return false;
			}
			iderror.innerHTML = "";
			return true;
		}
		//检查金额
		function ismoneyLegal() {
			var money = document.getElementById("money").value;
			var regEx = /^\d+$/;
			var regEx2 = /^\d+\.\d+$/;
			var moneyerror = document.getElementById("moneyerror");

			if (money == null || money == "") {
				moneyerror.innerHTML = "<font color=\"red\">金额不能为空！</font>";
				return false;
			} else if (!regEx.test(money) && !regEx2.test(money)) {
				moneyerror.innerHTML = "<font color=\"red\">金额只能是数字！</font>";
				return false;
			}
			moneyerror.innerHTML = "";
			return true;
		}
		//检查单位
		function isunitLegal() {
			var unit = document.getElementById("unit").value;
			var uniterror = document.getElementById("uniterror");
			var regEx = /^\w*[\u4E00-\u9FFF]*$/;
			if (unit == null || unit == "") {
				uniterror.innerHTML = "<font color=\"red\">单位不能为空！</font>";
				return false;
			} else if (!regEx.test(unit)) {
				usernameerror.innerHTML = "<font color=\"red\">单位包含非法字符！</font>";
				return false;
			}
			uniterror.innerHTML = "";
			return true;
		}
		//检查交易数量
		function isamountLegal() {
			var amount = document.getElementById("amount").value;
			var regEx = /^\d+$/;
			var amounterror = document.getElementById("amounterror");

			if (amount == null || amount == "") {
				amounterror.innerHTML = "<font color=\"red\">数量不能为空！</font>";
				return false;
			} else if (!regEx.test(amount)) {
				amounterror.innerHTML = "<font color=\"red\">数量只能是数字！</font>";
				return false;
			}
			amounterror.innerHTML = "";

			return true;
		}

		function remind() {
			var c = confirm('确认添加账单信息吗？');
			return c;
		}
		function check() {
			var username = document.getElementById("billNum");
			if (!isidLegal()) {
				username.focus();
				return false;
			} else if (!ismoneyLegal()) {
				username.focus();
				return false;
			} else if (!isunitLegal()) {
				username.focus();
				return false;
			} else if (!isamountLegal()) {
				username.focus();
				return false;
			} else if (!remind()) {
				username.focus();
				return false;
			}

			return true;
		}
	</script>
</body>
</html>
