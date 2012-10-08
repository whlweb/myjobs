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
  	2.removeAllOptions与addOptions
  	3.getValue与setValue； 
  	4.addRows 重点理解方法中各个参数的含义
   -->

<body>
	<select id="test">
		<option>Yes</option>
		<option>I</option>
		<option>Can!</option>
	</select>
	<input type="button" value="改变下拉列表" onclick="changeList()">
	<input type="button" value="获得下拉列表值" onclick="getSelectedValue()">
	<br>
	<br>
	<br>
	<table border="1">
		<thead>
			<tr>
				<td>姓名</td>
				<td>专业</td>
		</thead>
		<tbody id="table">
			<tr>
				<td>张三</td>
				<td>软件</td>
			<tr>
				<td>李四</td>
				<td>艺术</td>
			<tr>
				<td>王二麻子</td>
				<td>信息工程</td>
		</tbody>
	</table>
	<input type="button" value="删除表格内容" onclick="deleteTables()">
	<input type="button" value="改变表格" onclick="changeTables()">
	<br>
	<br>
	<br>
	<input type="text" id="text" value="再丑也要谈恋爱,谈到世界充满爱"
		onfocus="rangeText()">
	<input type="button" value="获得文本框的值" onclick="getTextValue()">
	<br>
	<br>
	<br>
	<div id="div">代表月亮消灭你</div>
	<input type="button" value="设置div的值" onclick="setDivValue()">

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
