
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<title>DWR-Engin Test</title>

<script type='text/javascript' src='dwr/engine.js'></script>

<script type='text/javascript' src='dwr/util.js'></script>

<script type='text/javascript' src='dwr/interface/service.js'></script>



</head>

<body>

	<h1 align="center">DWR Engin Test Page</h1>


	<input type="button" value="test DWR engine" onclick="engineTest()">
	<hr>


	<script language="javascript">
		function engineTest() {
			//开始预处理
			dwr.engine.beginBatch();
			//事务内容
			returnArray();
			returnList();
			//开始批处理
			dwr.engine.endBatch({
				timeout : 3000,
				errorhandler : function() {
					alert("ERROR!!");
				},
				prehook : premsg(),
				posthook : postmsg()
			});

		}
		function returnArray() {
			service.returnArray(function(array) {
				for ( var i = 0; i < array.length; i++) {
					alert(array[i]);
				}
			});
		}
		function returnList() {
			service.returnArray(function(list) {
				for ( var i = 0; i < list.length; i++) {
					alert(list[i]);
				}
			});
		}

		function premsg() {
			alert("开始预处理");
		}
		function postmsg() {
			alert("逆转预处理");
		}
	</script>

</body>

</html>
