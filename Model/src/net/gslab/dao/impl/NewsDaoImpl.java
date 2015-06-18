package net.gslab.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.hibernate.Query;
import org.hibernate.Session;

import net.gslab.dao.NewsDao;
import net.gslab.entity.News;

@Repository(value="newsDaoImpl")
public class NewsDaoImpl extends BaseDaoImpl<News> implements NewsDao{
	
	
    public News getNewsByID(int id)//获取新闻
    {
    	Session session= getSession();//获取session
    	String hql = "selecrt * from News where id = ?";//设置sql查询语句
    	Query query = session.createQuery(hql);//创建一个查询
    	query.setParameter(0, id);  //固定用法?
    	
    	return (News)query.uniqueResult();//?
    }
	
	public List<News> queryNewsByNewsId(int id)//查找
	{
		
		List<News> news=this.find("from News u where u.id=id");
		
		return news;
		
	}
   

}
