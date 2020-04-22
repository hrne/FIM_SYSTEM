package com.springmvc.dao;

import java.util.List;

import com.modle.dao.BaseDao;
import com.springmvc.entity.ModData;

/**
 * 感應裝置的Dao介面
 * 
 * @author hrne
 *
 */
public interface ModDataDao extends BaseDao<ModData> {
	
	/**
	 * 查詢所有啟用的感應裝置
	 */
	List<ModData> findByModEnable();

}