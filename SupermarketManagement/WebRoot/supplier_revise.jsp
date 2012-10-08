<%@page import="dao.SupplierDao"%>
<%@page import="entity.Supplier"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<jsp:useBean id="supplierdao" class="dao.SupplierDao" scope="page" />

</head>

<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>
<body>
	<%
		//找到需要修改的供应商对象
			request.setCharacterEncoding("utf-8");
			
		String row = request.getParameter("row");
		String reviseid = request.getParameter("reviseid");
		if (reviseid != null) {
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			String description = request.getParameter("description");
			String contact = request.getParameter("contact");
			String phone= request.getParameter("phone");
			String fax = request.getParameter("fax");
			String address = request.getParameter("address");

			// 封装成供应商信息对象
			Supplier supplier = new Supplier();
			supplier.setAddress(address);
			supplier.setContact(contact);
			supplier.setDescription(description);
			supplier.setFax(fax);
			supplier.setId(id);
			supplier.setName(name);
			supplier.setPhone(phone);
			boolean flag = supplierdao.update(supplier, reviseid);
			if (flag) {
		out.println("<script language=\"javascript\">alert(\"修改成功！\");	window.location=\"supplierAdmin.jsp\";</script>");

			} else {
		out.println("<script language=\"javascript\">alert(\"修改失败！\");	history.go(-1);</script>");

			}
		} else if (row != null) {
			List<Supplier> suppliers = supplierdao.getSupplierList();
			request.setAttribute("suppliers", suppliers);
			Supplier s = null;
			for (Supplier supplier : suppliers) {
		if (supplier.getId() == Integer.parseInt(row)) {
			s = supplier;
			request.setAttribute("supplier", s);
			break;
		}
			}
		}
	%>


	<div class="main">
		<div class="optitle clearfix">
			<div class="title">供应商管理&gt;&gt;</div>
		</div>
		<form method="post" id="myform"
			action="supplier_revise.jsp?reviseid=${supplier.id }"
			onsubmit="return check()">
			<div class="content">
				<table class="box">
					<tr>
						<td class="field">供应商编号：</td>
						<td><input type="text" name="id" id="id" class="text"
							onblur="isidLegal()" value="${supplier.id}"  />
							<font color="red">*</font><span id="iderror"></span></td>

					</tr>
					<tr>
						<td class="field">供应商名称：</td>
						<td><input type="text" name="name" class="text" id="name"
							onblur="isnameLegal()" value="${supplier.name}"
							 /> <font color="red">*</font><span
							id="nameerror"></span></td>
					</tr>
					<tr>
						<td class="field">供应商描述：</td>
						<td><textarea name="description" id="description" class="text"
								cols="45" rows="5" >${supplier.description}</textarea>
						</td>
					</tr>

					<tr>
						<td class="field">供应商联系人：</td>
						<td><input type="text" name="contact" class="text" id="contact"
							value="${supplier.contact}"  /></td>
					</tr>
					<tr>
						<td class="field">供应商电话：</td>
						<td><input type="text" name="phone" class="text" id="phone"
							value="${supplier.phone}"  />
						</td>

					</tr>
					<tr>
						<td class="field">供应商传真：</td>
						<td><input type="text" name="fax" class="text" id="fax"
							value="${supplier.fax}"  /></td>
					</tr>
					<tr>
						<td class="field">供应商地址：</td>

						<td><textarea name="address" id="address" class="text"
								cols="45" rows="2" >${supplier.address}</textarea></td>
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
