<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
</head>

<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>
<body>
	<div class="main">
		<div class="optitle clearfix">
			<div class="title">供应商管理&gt;&gt;</div>
		</div>
		<form method="post" id="myform"
			action="supplier.do?flag=dorevise&reviseid=${supplier.id }"
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
					onclick="window.location='supplier.do?flag=list';"  />
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
