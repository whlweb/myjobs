package com.pb.news.web.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.pb.news.dao.NewsDao;
import com.pb.news.dao.impl.NewsDaoImpl;
import com.pb.news.entity.News;
import com.pb.news.service.impl.NewsServiceImpl;

/**
 * Servlet implementation class AddNewsServlet
 */
public class AddNewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNewsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//接收新闻内容，调用相应Service方法处理请求，最红决定跳转到哪个jsp页面
		NewsServiceImpl newsService=new NewsServiceImpl();
		NewsDao newsDao=new NewsDaoImpl();
		newsService.setNewsDao(newsDao);
		
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
		if (bRet) {
			response.sendRedirect("jsp/admin/newsDetailList.jsp");
		} else {
			response.sendRedirect("jsp/admin/newsDetailCreate.jsp");
		}
	}

}
