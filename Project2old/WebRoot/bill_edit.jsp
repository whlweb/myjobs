<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="entity.*"%>
<%@ page language="java" contentType="text/html;charset=utf-8"
	import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<jsp:useBean id="billdao" class="dao.BillDao" scope="page" />
<jsp:useBean id="supplierdao" class="dao.SupplierDao" scope="page" />
<link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>
	<%
		//找到需要修改的订单对象
			String id = request.getParameter("row");
		List<Bill> bills = billdao.getBillList();
		request.setAttribute("bills", bills);
		Bill b = null;
		for (Bill bill : bills) {
			if (bill.getId() == Integer.parseInt(id)) {
		b = bill;
		request.setAttribute("bill", b);
		break;
			}
		}
		
				List<Supplier> suppliers = supplierdao.getSupplierList();
				request.setAttribute("suppliers", suppliers);
	%>

	<div class="main">
		<div class="optitle clearfix">
			<div class="title">账单管理&gt;&gt;</div>
		</div>
		<form method="post" id="myform" onsubmit="return check()">
			<div class="content">
				<table class="box">
					<tr>
						<td class="field">账单编号：</td>
						<td><input type="text" name="billNum" id="billNum"
							class="text" onblur="isidLegal()" value="${bill.id}"
							disabled="disabled" /><font color="red"> *</font><span
							id="iderror"></span></td>
					</tr>
					<tr>
						<td class="field">交易金额：</td>
						<td><input type="text" name="money" id="money" class="text"
							onblur="ismoneyLegal()" value="${bill.money}" disabled="disabled" /><font
							color="red"> *</font><span id="moneyerror"></span></td>
					</tr>
					<tr>
						<td class="field">交易单位：</td>
						<td><input type="text" name="unit" id="unit" class="text"
							onblur="isunitLegal()" value="${bill.unit}" disabled="disabled" /><font
							color="red"> *</font><span id="uniterror"></span></td>
					</tr>
					<tr>
						<td class="field">交易数量：</td>
						<td><input type="text" name="amount" id="amount" class="text"
							onblur="isamountLegal()" value="${bill.amount}"
							disabled="disabled" /><font color="red"> *</font><span
							id="amounterror"></span></td>
					</tr>
					<tr>
						<td class="field">商品名称：</td>
						<td><input type="text" name="name" class="text"
							value="${bill.name}" disabled="disabled" /></td>
					</tr>
					<tr>
						<td class="field">商品描述：</td>
						<td><textarea name="description" disabled="disabled">${bill.description}</textarea>
						</td>
					</tr>
					<tr>
						<td class="field">所属供应商：</td>
						<td><select name="supplier" disabled="disabled" id="select">
								<c:forEach items="${suppliers}" var="supplier">
									<option value="${supplier.id}">${supplier.name}</option>
								</c:forEach>

						</select></td>
					</tr>
					<tr>
						<td class="field">是否付款：</td>
						<td><select name="payed" id="payed" disabled="disabled">
								<option value="0" >否</option>
								<option value="1" >是</option>
						</select></td>
					</tr>
				</table>
			</div>
			<div class="buttons">
				<a href="bill_revise.jsp?row=${bill.id}" class="input-button">修改</a>
				<a href="#" class="input-button" onclick="remind()">删除</a> <a
					href="admin_bill_list.jsp" class="input-button">返回</a>
			</div>
		</form>
	</div>
	<script type="text/javascript">
		function remind() {
			var c = confirm('确认删除账单信息吗？');
			if (c) {
				location.href = "bill_delete.jsp?row=${bill.id }";
			}
		}

		//设置支付状态下拉框
		function selectpayed() {
			var select = document.getElementById("payed");
			for ( var i = 0; i < select.options.length; i++) {
			    if(select.options[i].value=="${bill.payed}"){
			    select.options[i].selected=true;
			    break;
              }
			}
		}
		//设置供应商下拉框
		function selectsupplier() {
			var select = document.getElementById("select");
			for ( var i = 0; i < select.options.length; i++) {
			    if(select.options[i].value=="${bill.supplier_id}"){
			    select.options[i].selected=true;
			    break;
              }
			}
		}

		selectpayed();
		selectsupplier();
	</script>
</body>
</html>
