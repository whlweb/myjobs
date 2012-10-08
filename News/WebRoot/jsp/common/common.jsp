<link type="text/css" rel="stylesheet" href="../../css/common.css"/>
<jsp:useBean id="newsDao" class="com.pb.news.dao.impl.NewsDaoImpl" scope="page"/>
<jsp:useBean id="newsService" class="com.pb.news.service.impl.NewsServiceImpl"  scope="page"/>
<jsp:setProperty property="newsDao" name="newsService" value="<%=newsDao%>"/>