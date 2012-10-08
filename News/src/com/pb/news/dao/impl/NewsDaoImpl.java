package com.pb.news.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.pb.news.dao.BaseDao;
import com.pb.news.dao.NewsDao;
import com.pb.news.entity.News;
import com.pb.news.util.ConfigManager;
import com.pb.news.util.Page;

public class NewsDaoImpl extends BaseDao implements NewsDao {
	//通过新闻id获取新闻
		public News getNewsById(int id) {		
			News news=null;
			try {
				//（3）获得Statement对象，执行SQL语句
				String sql = "select * from news_detail where id="+id;
				Object[] params={};
				ResultSet rs=this.executeSQL(sql, params);
				//（4）处理执行结果(ResultSet)，
				if(rs.next()){
					int categoryId=rs.getInt("categoryId");
					String title=rs.getString("title");
					String summary=rs.getString("summary");
					String content=rs.getString("content");
					String picPath=rs.getString("picPath");
					String author=rs.getString("author");
					Timestamp time=rs.getTimestamp("createdate");
					
					//封装成新闻信息对象
					news=new News();
					news.setId(id);
					news.setCategoryId(categoryId);
					news.setTitle(title);
					news.setSummary(summary);
					news.setContent(content);
					news.setPicPath(picPath);
					news.setAuthor(author);
					news.setCreateDate(time);
				}
			}catch (SQLException e) {			
				e.printStackTrace();
			}finally{
				//释放资源
				this.closeResource();
			}
			return news;
		}
	// 查询新闻信息
	public List<News> getNewsList(){
		List<News> newList=new ArrayList<News>();
		try {
			//（3）获得Statement对象，执行SQL语句
			String sql="select * from news_detail";
			Object[] params={};
			ResultSet rs=this.executeSQL(sql, params);
			//（4）处理执行结果(ResultSet)，
			while(rs.next()){
				int id=rs.getInt("id");
				String title=rs.getString("title");
				String summary=rs.getString("summary");
				String content=rs.getString("content");
				String author=rs.getString("author");
				Timestamp time=rs.getTimestamp("createdate");
				
				//封装成新闻信息对象
				News news=new News();
				news.setId(id);
				news.setTitle(title);
				news.setSummary(summary);
				news.setContent(content);
				news.setAuthor(author);
				news.setCreateDate(time);
				
				//将新闻对象放进集合中
				newList.add(news);
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//释放资源
			this.closeResource();
		}
		return newList;
	}

	// 增加新闻信息
	public boolean add(News news) {
		boolean flag=false;
		try {
			String sql="insert into news_detail(id,categoryId,title,summary,content,picPath,createdate,author) values(SEQ_NEWS.nextval,?,?,?,?,?,?,?)";
			Object[] params={news.getCategoryId(),news.getTitle(),news.getSummary(),news.getContent(),news.getPicPath(), new java.sql.Timestamp(news.getCreateDate().getTime()),news.getAuthor()};
			System.out.println(sql);
			int i=this.executeUpdate(sql, params);
			//（4）处理执行结果
			if(i>0){
				System.out.println("插入新闻成功！");
			}
			flag=true;
		}finally{
			//释放资源
			this.closeResource();
		}
		return flag;
	}
	
	// 删除新闻信息
	public boolean delete(int id) {
		boolean flag=false;
		try {
			String sql = "delete from  news_Detail where id=?";
			Object[] params={id};
			int i=this.executeUpdate(sql, params);
			//（4）处理执行结果
			if(i>0){
				System.out.println("删除新闻成功！");
			}
			flag=true;
		}  finally {
			//释放资源
			this.closeResource();
		}
		return flag;
	}

	// 修改新闻
	public boolean update(News news) {
		boolean flag=false;
		try {
			String sql = "update News_detail set categoryId=? , title=?, summary=? , content=?, picpath=?"
		             +",author=? ,modifydate=? where id=?";
			Object[] params={news.getCategoryId(),news.getTitle(),news.getSummary(),news.getContent(),news.getPicPath(),news.getAuthor(),new java.sql.Timestamp(news.getModifyDate().getTime()),news.getId()};
			int i=this.executeUpdate(sql, params);
			//（4）处理执行结果
			if(i>0){
				System.out.println("修改新闻成功！");
			}
			flag=true;
		}  finally {
			//释放资源
			this.closeResource();
		}
		return flag;
	}
	
	/**
	 * 获取新闻总数量
	 */
	public int getTotalCount() {
		int totalCount=0;
		String sql="select count(*) from news_detail";
		Object[]params={};
		ResultSet rs=this.executeSQL(sql, params);
		try {
			while(rs.next()){
				totalCount=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.closeResource();
		}
		return totalCount;
	}
	
	/**
	 * 分页获取新闻信息
	 */
	public List<News> getPageNewsList(int pageNo, int pageSize) {
		List<News> newsList=new ArrayList<News>();
		String sql="SELECT id,title,author,createdate FROM (SELECT id,title,author,createdate,ROWNUM rn FROM news_detail) a WHERE a.rn>=? AND a.rn<=? ";
		Page page=new Page();
		page.setCurrPageNo(pageNo);//设置当前页码
		page.setPageSize(pageSize);//每页显示记录数
		//计算sql语句的起始记录数以及结束记录数的行数
		int startRow=page.getStartRow();
		int endRow=page.getEndRow();
		Object[]params={startRow,endRow};
		ResultSet rs=this.executeSQL(sql, params);
		try {
			while(rs.next()){
				int id=rs.getInt("id");
				String title=rs.getString("title");
				String author=rs.getString("author");
				Date date=rs.getDate("createdate");
				News newInfo=new News();
				newInfo.setId(id);
				newInfo.setTitle(title);
				newInfo.setAuthor(author);
				newInfo.setCreateDate(new java.sql.Timestamp(date.getTime()));
				
				newsList.add(newInfo);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.closeResource();
		}
		return newsList;
	}
	
	/**
	 * 获取新闻总数量（执行存储过程）
	 * @return
	 */
	public int getTotalCountProc(){
		int totalCount=0;
		CallableStatement proc=null;
		String sql="{call getNewsCount(?)}";
		getConnection();
		try {
			proc=conn.prepareCall(sql);
			proc.registerOutParameter(1, Types.INTEGER);
			proc.execute();
			totalCount=proc.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(proc!=null)
				try {
					proc.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		return totalCount;
		
	}
	
	//测试
		public static void main(String[] args) {
			NewsDaoImpl newsDao=new NewsDaoImpl();
			/*newsDao.add(3, 1, "快女选秀快讯", "快女比赛正进入白热化", "她、她、她，谁是你心中的快女冠军？",
					new Date());*/
			//newsDao.update(3, "快女选秀快讯速递");
			//newsDao.delete(3);
			/*List<News> newsList=newsDao.getNewsList();
			for(News news:newsList){
				System.out.println(news.getId()+"\t"+news.getTitle()+"\t"+news.getSummary()+"\t"+news.getContent()+"\t"+news.getAuthor()+"\t"+news.getCreateDate());
			}*/
			/*int totalCount=newsDao.getTotalCount();
			System.out.println("新闻总数量是："+totalCount);*/
			//第一页新闻信息（每页显示2条）
			//List<News> newsList=newsDao.getPageNewsList(1, 2);
			//第二页新闻信息（每页显示2条）
			//List<News> newsList=newsDao.getPageNewsList(2, 2);
			//第三页新闻信息（每页显示2条）
			/*List<News> newsList=newsDao.getPageNewsList(3, 2);
			for(News news:newsList){
				System.out.println(news.getTitle()+"\t"+news.getAuthor()+"\t"+news.getCreateDate());
			}*/
			int totalCount=newsDao.getTotalCountProc();
			System.out.println("新闻总数量是："+totalCount);
		}

}
