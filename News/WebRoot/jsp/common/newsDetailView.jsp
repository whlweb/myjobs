<%@ page language="java" import="java.util.*,com.pb.news.entity.*" pageEncoding="UTF-8"%>

<jsp:useBean id="newsDao" beanName="com.pb.news.dao.impl.NewsDaoImpl" type="com.pb.news.dao.NewsDao" />
<jsp:useBean id="newsService" beanName="com.pb.news.service.impl.NewsServiceImpl" type="com.pb.news.service.NewsService" />
<jsp:setProperty property="newsDao" name="newsService" value="<%=newsDao%>"/>
<link href="<%=request.getContextPath() %>/css/common.css" rel="stylesheet" type="text/css" />
<%
	String id = request.getParameter("id");
	News news = newsService.getNewsById(Integer.parseInt(id));
	request.setAttribute("news", news);
%>
<!--传过来的ID：${param.id }  -->
<div class="main-text-box-tbg">
	<div class="main-text-box-bbg">
		<div class="article-box">
			<h1>${requestScope. news["title"] }</h1>
			<div class="source-bar">发布者：<%=news.getAuthor() %> 分类：新闻信息  更新时间：<%=news.getCreateDate() %>" </div>
			<div class="article-content">
				<span class="article-summary"><b>摘要：<%=news.getSummary() %></b></span>
					<img src="<%=request.getContextPath() %>/upload/<%=news.getPicPath() %>"/><br/>
				<%=news.getContent() %>
			</div>
		</div>
	</div>
</div>