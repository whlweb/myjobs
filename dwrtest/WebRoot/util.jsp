<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<script type='text/javascript' src='dwr/engine.js'></script>
<script type='text/javascript' src='dwr/util.js'></script>
<script type="text/javascript" src="dwr/interface/service.js"></script>
</head>
<!--
  	1.$()
  	2.removeAllOptions��addOptions
  	3.getValue��setValue�� 
  	4.addRows �ص���ⷽ���и��������ĺ���
   -->

<body>
	<select id="test">
		<option>Yes</option>
		<option>I</option>
		<option>Can!</option>
	</select>
	<input type="button" value="�ı������б�" onclick="changeList()">
	<input type="button" value="��������б�ֵ" onclick="getSelectedValue()">
	<br>
	<br>
	<br>
	<table border="1">
		<thead>
			<tr>
				<td>����</td>
				<td>רҵ</td>
		</thead>
		<tbody id="table">
			<tr>
				<td>����</td>
				<td>���</td>
			<tr>
				<td>����</td>
				<td>����</td>
			<tr>
				<td>��������</td>
				<td>��Ϣ����</td>
		</tbody>
	</table>
	<input type="button" value="ɾ���������" onclick="deleteTables()">
	<input type="button" value="�ı���" onclick="changeTables()">
	<br>
	<br>
	<br>
	<input type="text" id="text" value="�ٳ�ҲҪ̸����,̸�����������"
		onfocus="rangeText()">
	<input type="button" value="����ı����ֵ" onclick="getTextValue()">
	<br>
	<br>
	<br>
	<div id="div">��������������</div>
	<input type="button" value="����div��ֵ" onclick="setDivValue()">

	<script type="text/javascript">
		function changeList() {
			service.returnArray(function(array) {
				dwr.util.removeAllOptions("test");
				dwr.util.addOptions("test", array);
			});
		}

		function getSelectedValue() {
			alert($("test").value);
		}

		function changeTables() {
			service.userList(function(users) {
				dwr.util.addRows("table", users, [ function(users) {
					return users.username
				}, function(users) {
					return users.password
				}

				]);
			});
		}

		function deleteTables() {
			dwr.util.removeAllRows("table");
		}

		function rangeText() {
			var text = $("text").value;
			var comma = text.indexOf(",");
			dwr.util.selectRange("text", 0, comma);
		}

		function getTextValue() {
			alert(dwr.util.getValue("text"));
		}

		function setDivValue() {
			dwr.util.setValue("div", "See how this dwr util works!");
		}
	</script>
</body>
</html>
