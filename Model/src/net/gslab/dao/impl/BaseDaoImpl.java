package net.gslab.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import net.gslab.dao.BaseDao;
import net.gslab.setting.Page;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

@Repository("baseDaoImpl")
public class BaseDaoImpl<T> implements BaseDao<T> {
	private Class<T> entityClass;
	@Resource(name = "hibernateTemplate")//相当于set方法
	private HibernateTemplate hibernateTemplate;//hibernateTemplate就是hibernate

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	
	

	public BaseDaoImpl() {
		Type genType = getClass().getGenericSuperclass();
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		entityClass = (Class) params[0];
	}

	@Override
	public T load(Serializable id) {//返回一个数据库里的实体，Serializable是一个接口，可序列化
		return (T) getHibernateTemplate().load(entityClass, id);
	}

	public T get(Serializable id) {
		return (T) this.getHibernateTemplate().get(entityClass, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.gslab.dao.impl.BaseDao#loadAll()
	 */
	@Override
	public List<T> loadAll() {
		//使用这个方法会出BUG...调试中
		System.out.println("in the BaseDaoImpl");
		return this.getHibernateTemplate().loadAll(entityClass);
	}
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.gslab.dao.impl.BaseDao#save(T)
	 */
	@Override
	public void save(T entity) {                 //泛型
		System.out.println("in the baseDao Impl");
		this.getHibernateTemplate().save(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.gslab.dao.impl.BaseDao#remove(T)
	 */
	@Override
	public void remove(T entity) {
		this.getHibernateTemplate().delete(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.gslab.dao.impl.BaseDao#update(T)
	 */
	@Override
	public void update(T entity) {
		this.getHibernateTemplate().update(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.gslab.dao.impl.BaseDao#find(java.lang.String)
	 */
	@Override
	public List<T> find(String hql) {
		System.out.println("hql in the BaseDaoImpl:"+hql);
		return this.getHibernateTemplate().find(hql);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.gslab.dao.impl.BaseDao#find(java.lang.String, java.lang.Object)
	 */
	@Override
	public List find(String hql, Object... params) {
		return this.getHibernateTemplate().find(hql, params);
	}

	public void initialize(Object entity) {
		this.getHibernateTemplate().initialize(entity);
	}

	public Page pagedQuery(String hql, int pageNo, final int pageSize,
			Object... values) {
		System.out.println("In the pagedQuery hql:" + hql);
		Assert.hasText(hql);
		Assert.isTrue(pageNo >= 1, "pageNo should start from 1");

		String countQueryString = "select count(*)"
				+ removeSelect(removeOrders(hql));
		System.out.println("values: " + values.toString());
		List countlist = this.getHibernateTemplate().find(countQueryString,
				values);
		System.out.println("countlist: " + countlist);
		long totalCount = (Long) countlist.get(0);
		if (totalCount < 1) {
			return new Page();
		}

		final int startIndex = Page.getStartOfPage(pageNo, pageSize);
		System.out.println("startIndex:" + startIndex + "  pageSize:"
				+ pageSize);
		Query query = createQuery(hql, values);

		/*
		 * !!!!!MYSQL不能执行limit top hibernate: select limit ? ? topic0_.topicId
		 * as topicId2_ 想办法用
		 */

		List list = query.setFirstResult(startIndex).setMaxResults(pageSize).list();

		return new Page(startIndex, totalCount, pageSize, list);
	}

	public Query createQuery(String hql, Object[] values) {
		// TODO Auto-generated method stub
		Assert.hasText(hql);
		Query query = getSession().createQuery(hql);
		for (int i = 0; i < values.length; i++) {
			query.setParameter(i, values[i]);
		}
		return query;
	}

	// ---
	public Session getSession() {
		// TODO Auto-generated method stub
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		return session;
	}

	private String removeSelect(String hql) {
		// TODO Auto-generated method stub
		Assert.hasText(hql);
		int beginPos = hql.toLowerCase().indexOf("from");
		Assert.isTrue(beginPos != -1, "hql:" + hql
				+ "must have a keyword 'from'");
		return hql.substring(beginPos);
	}

	private static String removeOrders(String hql) {
		Assert.hasText(hql);
		Pattern p = Pattern.compile("order\\s * by[\\w\\W\\s\\S]*",
				Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(hql);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		return sb.toString();
	}



	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	

}
