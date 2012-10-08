package com.pb.news.service.impl;

import java.util.List;

import com.pb.news.dao.NewsDao;
import com.pb.news.entity.News;
import com.pb.news.service.NewsService;

public class NewsServiceImpl implements NewsService {
	private NewsDao newsDao;
	
	@Override
	public News getNewsById(int id) {
		// TODO Auto-generated method stub
		return newsDao.getNewsById(id);
	}

	public NewsDao getNewsDao() {
		return newsDao;
	}

	public void setNewsDao(NewsDao newsDao) {
		this.newsDao = newsDao;
	}

	@Override
	public boolean updateNews(News news) {
		return newsDao.update(news);
	}

	@Override
	public boolean addNews(News news) {
		// TODO Auto-generated method stub
		System.out.println(news.getAuthor()+"\t"+news.getPicPath());
		return newsDao.add(news);
	}

	@Override
	public boolean deleteNews(int id) {
		// TODO Auto-generated method stub
		 return newsDao.delete(id);
	}

	@Override
	public List<News> getNewsList() {
		// TODO Auto-generated method stub
		return newsDao.getNewsList();
	}

	@Override
	public int getTotalCount() {
		return newsDao.getTotalCount();
	}

	@Override
	public List<News> getPageNewsList(int pageNo, int pageSize) {
		return newsDao.getPageNewsList(pageNo, pageSize);
	}

}
