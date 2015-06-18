package net.gslab.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T> {

	public abstract T load(Serializable id);

	public abstract List<T> loadAll();

	public abstract void save(T entity);

	public abstract void remove(T entity);

	public abstract void update(T entity);

	public abstract List find(String hql);

	public abstract List find(String hql, Object... params);

}