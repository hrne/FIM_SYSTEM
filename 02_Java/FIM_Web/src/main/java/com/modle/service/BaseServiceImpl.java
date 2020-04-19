package com.modle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.modle.dao.BaseDao;

/**
 * 基礎商業邏輯操作介面
 * 
 * @author hrne
 * 
 * @param <T>
 * @param <PK>
 * @param <DAO>
 */
@Service
public abstract class BaseServiceImpl<T> implements BaseService<T> {

    @Autowired
    private BaseDao<T> baseDao;
    
	@Override
    public List<T> findAll() {
        return this.getDao().findAll();
    }
    
    @Override
    public T findByPK(int id) {
        return this.getDao().findByPK(id);
    }
    
    @Override
    public void saveOrUpdate(T entity) {
    	this.getDao().saveOrUpdate(entity);
    }
    
    @Override
    public void create(T entity) {
    	this.getDao().create(entity);
    }
    
    @Override
    public void update(T entity) {
    	this.getDao().update(entity);
    }
    
    @Override
    public void delete(T entity) {
    	this.getDao().delete(entity);
    }
    
    /**
     * 回傳Data Access Object
     * 
     * @return BaseDao
     */
    public BaseDao<T> getDao() {
        return this.baseDao;
    }

    /**
     * 設定Data Access Object
     * 
     * @param baseDao
     */
    public void setDao(final BaseDao<T> baseDao) {
        this.baseDao = baseDao;
    }

}
