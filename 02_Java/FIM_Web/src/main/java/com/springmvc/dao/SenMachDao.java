package com.springmvc.dao;

import java.util.List;

import com.modle.dao.BaseDao;
import com.springmvc.entity.SenMach;

/**
 * 工具機資料的Dao介面
 * 
 * @author hrne
 *
 */
public interface SenMachDao extends BaseDao<SenMach> {
	
	/**
	 * 查詢所有啟用的工具機
	 */
	List<SenMach> findByMachEnable();

}