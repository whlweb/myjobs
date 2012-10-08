<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%--局部变量和全局变量 --%>
<%int i=10;

%>
<%!int j=10; 
public int add(){
	return 5+9;
}
%>
i++: <%=i++ %><br/>
j++: <%=j++ %><br/>
<%=add() %>
</body>
</html>