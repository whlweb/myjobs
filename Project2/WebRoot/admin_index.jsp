<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=utf-8"
	import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
<title>超市账单管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>

<frameset rows="100,*" cols="*" frameborder="no" border="0"
	framespacing="0">
	<frame src="admin_top.jsp" name="topFrame" scrolling="no"
		noresize="noresize" id="topFrame" />
	<frameset cols="200,*" frameborder="no" border="0" framespacing="0">
		<frame src="admin_left.jsp" name="leftFrame" scrolling="no"
			noresize="noresize" id="leftFrame" />
		<frame src="bill.do?flag=list" name="mainFrame" id="mainFrame" />
	</frameset>
</frameset>
<noframes>
	<body>
	</body>
</noframes>
</html>