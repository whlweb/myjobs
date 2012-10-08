<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="entity.*"%>
<%@ page language="java" contentType="text/html;charset=utf-8"
	import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>
<body class="frame-bd">

<ul class="left-menu">

	<li><a href="bill.do?flag=list" target="mainFrame"><img src="images/btn_bill.gif" /></a></li>
	<c:if test="${role=='admin'}">
	<li><a href="supplier.do?flag=list" target="mainFrame" ><img src="images/btn_suppliers.gif" /></a></li>
	<li><a href="user.do?flag=list" target="mainFrame"><img src="images/btn_users.gif" /></a></li>
	</c:if>
	<c:if test="${role=='user'}">
	<li><a href="userAdmin2.jsp" target="mainFrame"><img src="images/btn_users.gif" /></a></li>
	</c:if>
	<li><a href="#" onclick="exit()"><img src="images/btn_exit.gif" /></a></li>
</ul>
<script type="text/javascript">
  function exit(){
	 var c=confirm('您真的要退出系统吗？');
	 if(c==true)
		 window.parent.location=href="login.do?flag=exit";
  }
</script>
</body>
</html>
