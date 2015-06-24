package net.gslab.dao;

import java.io.Serializable;
import java.util.List;

import net.gslab.setting.Page;

public interface BaseDao<T> {

	public abstract T load(Serializable id);

	public abstract List<T> loadAll();

	public abstract void save(T entity);

	public abstract void remove(T entity);

	public abstract void update(T entity);

	public abstract List<T> find(String hql);

	public abstract List<T> find(String hql, Object... params);
	public abstract int getCount(String hql);
    public abstract Page getPage(final String hql,final int offset,final int length);
}