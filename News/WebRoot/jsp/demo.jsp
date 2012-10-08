<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%
/*     String username = "admin";
    request.setAttribute("username", username); */
    request.setAttribute("student.name", "张三");
    ArrayList list = new ArrayList();
    list.add("北京洪水");
    list.add("热火夺冠");
    request.setAttribute("list", list);
    request.setAttribute("totalRecordCount", 8);
    request.setAttribute("pageSize", 2);
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
${username } <br>
${requestScope["student.name"] }<br>
${list[1] }<br>
${totalRecordCount/pageSize }
</body>
</html>