<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*,com.pb.news.entity.*" pageEncoding="UTF-8"%>


<html>
	<head>
		<link href="<%=request.getContextPath() %>/css/common.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=request.getContextPath() %>/ckeditor/ckeditor.js"></script>
	</head>

<body>
<form name ="dataForm" id="dataForm" action="<%=request.getContextPath() %>/AddNewsServlet" method="post"  enctype="multipart/form-data">
	<table class="tb" border="0" cellspacing="5" cellpadding="0">
		<thead>
			<tr><td align="center" colspan="2" class="text_tabledetail2">增加新闻</td></tr>
		</thead>
		<tbody>
			<tr>
				<td class="text_tabledetail2">分类</td>
				<td>
				<!-- 列出所有的新闻分类 -->
					<select name="categoryId">
	        			<option value="1">国内</option>
	        			<option value="2">国际</option>
	        			<option value="3">娱乐</option>
	        			<option value="4">军事</option>
	        			<option value="5">财经</option>
	        			<option value="6">天气</option>
	        		</select>
				</td>
			</tr>
			<tr>
				<td class="text_tabledetail2">标题</td>
				<td><input type="text" name="title" value=""/></td>
			</tr>
			<tr>
				<td class="text_tabledetail2">作者</td>
				<td><input type="text" name="author" value=""/></td>
			</tr>
			
			<tr>
				<td class="text_tabledetail2">摘要</td>
				<td><textarea id="summary" name="summary" rows="8" cols="50"></textarea></td>
			</tr>
			<tr>
				<td class="text_tabledetail2">内容</td>
				<td>
				<div id="xToolbar"></div>
				<textarea id="newscontent" name="newscontent" class="ckeditor" rows="8"></textarea>
				</td>
			</tr>
			<tr>
				<td class="text_tabledetail2">上传图片 </td>
				<td><input type="file" name="picPath" value=""/></td>
			</tr>
			<tr>
				<td style="text-align:center;" colspan="2">
					<button type="submit" class="page-btn" name="save">保存</button>
					<input type="hidden" name="id" value=""/>
					<button type="button" class="page-btn" name="return" onclick="javascript:location.href='newsDetailList.jsp'">返回</button>
				</td>
			</tr>
		</tbody>
	</table>
</form>
</body>

</html>