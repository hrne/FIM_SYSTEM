package com.modle.service;

import java.util.List;

/**
 * 基礎商業邏輯操作介面
 * 
 * @author hrne
 * 
 * @param <T>
 * @param <PK>
 */
public interface BaseService<T> {

    /**
     * 查詢全部資料，預設的data table會用此
     * 
     * @return List<T> 全部資料
     */
    List<T> findAll();
    
    /**
     * 依據primary key.查詢資料
     * 
     * @param id
     * @return T 查詢出的Entity
     */
    public T findByPK(int id);
    
    /**
     * 新增/修改物件
     * 
     * @param entity
     */
    public void saveOrUpdate(T entity);
    
    /**
     * 新增物件
     * 
     * @param entity
     */
    public void create(T entity);
    
    /**
     * 修改物件
     * 
     * @param entity
     */
    public void update(T entity);
    
    /**
     * 從資料庫刪除物件
     * 
     * @param theId 要從資料庫刪除的物件id
     */
    public void delete(T entity);

}
