<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%
    session.setAttribute("uid", "admin");
    Object o = application.getAttribute("count");
    if(o == null){
    	application.setAttribute("count", 1);
    }
    else{
    	application.setAttribute("count", (Integer)o+1);
    }
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
用户名：${sessionScope.uid }<br>
网站访问次数：${count }
</body>
</html>