<%@page import="java.util.Date"%>
<%@page import="com.pb.news.entity.News"%>
<%@page import="java.io.*,java.util.*,org.apache.commons.fileupload.*"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>

<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<%@include file="../common/common.jsp" %>
<body>
<%
//request.setCharacterEncoding("utf-8");
boolean bRet = false;
boolean bUpload = false;
String uploadFileName = "";
String fieldName = "";
News news = new News();
news.setCreateDate(new Date());
boolean isMultipart = ServletFileUpload.isMultipartContent(request);
String uploadFilePath = request.getSession().getServletContext().getRealPath("/upload/" );
if (isMultipart == true) {
	FileItemFactory factory = new DiskFileItemFactory();
	ServletFileUpload upload = new ServletFileUpload(factory);
	try {
		List<FileItem> items = upload.parseRequest(request);
		Iterator<FileItem> iter = items.iterator();
		while (iter.hasNext()) {
			FileItem item = (FileItem) iter.next();
			if (item.isFormField()){
				fieldName = item.getFieldName();
				if (fieldName.equals("title")){
					news.setTitle(item.getString("UTF-8"));
				}else if(fieldName.equals("id")){
					String id = item.getString();
					if (id != null && !id.equals("")){
						news.setId(Integer.parseInt(id));
					}
				}else if (fieldName.equals("categoryId")){
					news.setCategoryId(Integer.parseInt(item.getString()));
				}else if (fieldName.equals("summary")){
					news.setSummary(item.getString("UTF-8"));
				}else if (fieldName.equals("newscontent")){
					news.setContent(item.getString("UTF-8"));
				}else if(fieldName.equals("author")){
					news.setAuthor(item.getString("UTF-8"));
				}
			}else{
				String fileName = item.getName();
				if (fileName != null && !fileName.equals("")) {
					File fullFile = new File(item.getName());
					File saveFile = new File(uploadFilePath, fullFile.getName());
					item.write(saveFile);
					uploadFileName = fullFile.getName();
					news.setPicPath(uploadFileName);
					bUpload = true;
				}
			}
			
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
System.out.println("上传成功后的文件名是：："+news.getPicPath());

	//调用后台方法，将新闻信息插入数据库
	bRet=newsService.addNews(news);
%>

<%
if (bRet) {
	response.sendRedirect("newsDetailList.jsp");
} else {
	response.sendRedirect("newsDetailCreate.jsp");
}
%>
</body>
</html>