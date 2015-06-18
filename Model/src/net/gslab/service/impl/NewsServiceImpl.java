package net.gslab.service.impl;

import java.util.List;

import javax.annotation.Resource;

import net.gslab.dao.BaseDao;
import net.gslab.dao.NewsDao;
import net.gslab.entity.News;
import net.gslab.service.NewsService;

import org.springframework.stereotype.Service;

@Service(value="newsServiceImpl")
public class NewsServiceImpl extends BaseServiceImpl<News> implements NewsService{

	@Resource(name = "newsDaoImpl")
	private NewsDao newsDao;
	
	@Resource(name="newsDaoImpl")
	public void setNewsDao(NewsDao newsDao) {
		this.newsDao = newsDao;
		super.setBaseDao(newsDao);
	}
	
	public List<News> listNews()
	{
		List<News> news=newsDao.find("from News");
		System.out.println("listNews() in the UserService"+news);
		return news;
		
	}
	
//	public NewsServiceImpl() {
//		// TODO Auto-generated constructor stub
//		setBaseDao(newsDao);
//	}
	
//	@Override
//	public void setBaseDao(BaseDao<News> newsDao) {
//		// TODO Auto-generated method stub
//		super.setBaseDao(newsDao);
//
//	}
	
//	public void save(News news){
//		System.out.println("in the newsServiceImpl");
//		System.out.println(news.getNewsId());
//		newsDao.save(news);
//	}
	

	
}
