<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html;charset=utf-8"
	import="java.util.*" pageEncoding="utf-8"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>
<body>
	<div class="main">
		<div class="optitle clearfix">
			<div class="title">供应商管理&gt;&gt;</div>
		</div>
		<form method="post" id="myform" onsubmit="return check()">
			<div class="content">
				<table class="box">
					<tr>
						<td class="field">供应商编号：</td>
						<td><input type="text" name="id" id="id" class="text"
							onblur="isidLegal()" value="${supplier.id}" disabled="disabled" />
							<font color="red">*</font><span id="iderror"></span></td>

					</tr>
					<tr>
						<td class="field">供应商名称：</td>
						<td><input type="text" name="name" class="text" id="name"
							onblur="isnameLegal()" value="${supplier.name}"
							disabled="disabled" /> <font color="red">*</font><span
							id="nameerror"></span></td>
					</tr>
					<tr>
						<td class="field">供应商描述：</td>
						<td><textarea name="description" id="description" class="text"
								cols="45" rows="5" disabled="disabled">${supplier.description}</textarea>
						</td>
					</tr>

					<tr>
						<td class="field">供应商联系人：</td>
						<td><input type="text" name="contact" class="text" id="contact"
							value="${supplier.contact}" disabled="disabled" /></td>
					</tr>
					<tr>
						<td class="field">供应商电话：</td>
						<td><input type="text" name="phone" class="text" id="phone"
							value="${supplier.phone}" disabled="disabled" />
						</td>

					</tr>
					<tr>
						<td class="field">供应商传真：</td>
						<td><input type="text" name="fax" class="text" id="fax"
							value="${supplier.fax}" disabled="disabled" /></td>
					</tr>
					<tr>
						<td class="field">供应商地址：</td>

						<td><textarea name="address" id="address" class="text"
								cols="45" rows="2" disabled="disabled">${supplier.address}</textarea></td>
					</tr>
				</table>
			</div>
			<div class="buttons">
				<a href="supplier.do?flag=list" class="input-button">返回</a> <a href="#"
					class="input-button" onclick="remind()">删除</a> <a
					href="supplier.do?flag=revise&row=${supplier.id }" class="input-button">修改</a>
			</div>
		</form>
	</div>
	
<script type="text/javascript">
		function remind() {
			var c = confirm('确认删除供应商信息吗？');
			if (c) {
				location.href = "supplier.do?flag=delete&row=${supplier.id }";
			}
		}
</script>
</body>
</html>
