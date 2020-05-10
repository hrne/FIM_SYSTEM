package com.springmvc.dao;

import java.util.List;

import com.modle.dao.BaseDao;
import com.springmvc.entity.ModMain;

/**
 * 感應裝置主檔Dao介面
 * 
 * @author hrne
 *
 */
public interface ModMainDao extends BaseDao<ModMain> {
	
	/**
	 * 查詢所有啟用的感應裝置主檔
	 * 
	 * @return ModMain list
	 */
	List<ModMain> find_modEnabled();

}