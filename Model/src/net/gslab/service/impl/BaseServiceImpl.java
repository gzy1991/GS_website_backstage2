package net.gslab.service.impl;

import java.util.ArrayList;
import java.util.List;






import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.gslab.dao.BaseDao;
import net.gslab.service.BaseService;
import net.gslab.setting.Page;


public class BaseServiceImpl<T> implements BaseService<T> {
	

	protected BaseDao<T> baseDao;

	@Override
	public T find(int id) {
		// TODO Auto-generated method stub
		return baseDao.load(id);
	}

	@Override
	public List<T> findAll() {
		// TODO Auto-generated method stub
		System.out.println("in the BaseServiceImpl findAll");
		return baseDao.loadAll();
	}

	@Override
	public List<T> findList(int... ids) {
		// TODO Auto-generated method stub
		List<T> lists = new ArrayList<T>();
		for(int id:ids){
			if(baseDao.load(id)!=null){
				lists.add(baseDao.load(id));
			}
		}
		return lists;
	}

	@Override
	public Page<T> findPage(Page pageable) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public boolean exists(int id) {
		// TODO Auto-generated method stub
		if(baseDao.load(id)!=null)return true;
		return false;
	}

	@Override
	public void save(T entity) {
		// TODO Auto-generated method stub
		baseDao.save(entity);
		
	}

	@Override
	public void update(T entity) {
		// TODO Auto-generated method stub
		 baseDao.update(entity);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		T entity = baseDao.load(id);
		baseDao.remove(entity);
	}

	@Override
	public void delete(T entity) {
		// TODO Auto-generated method stub
		baseDao.remove(entity);
	}

	public void setBaseDao(BaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}

}
