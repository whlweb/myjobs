package com.pb.news.dao;

import java.util.List;

import com.pb.news.entity.News;


public interface NewsDao {
	//通过新闻id获取新闻
	public News getNewsById(int id);
	
	// 查询新闻信息
	public List<News> getNewsList();

	// 增加新闻信息
	public boolean add(News news) ;
	
	// 删除新闻信息
	public boolean delete(int id) ;
	
	// 修改新闻
	public boolean update(News news) ;	
	
	//获取新闻总数量
	public int getTotalCount();
	
	//分页获取新闻信息
	public List<News> getPageNewsList(int pageNo,int pageSize);

}
