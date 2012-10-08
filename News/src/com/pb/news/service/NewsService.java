package com.pb.news.service;

import java.util.List;

import com.pb.news.entity.News;

public interface NewsService {
	// 通过新闻id获取新闻
	public News getNewsById(int id); 
	// 更新选择的新闻
	public boolean updateNews(News news);
	// 添加新闻
	public boolean addNews(News news);	
	// 删除新闻
	public boolean deleteNews(int id);
	//查询新闻信息
	public List<News> getNewsList();
	//获取新闻总数量
	public int getTotalCount();
	
	//分页获取新闻信息
	public List<News> getPageNewsList(int pageNo,int pageSize);
}
