package com.springmvc.dao;

import java.util.List;

import com.modle.dao.BaseDao;
import com.springmvc.entity.SenDht11;

/**
 * 溫濕度dht11感應資料Dao介面
 * 
 * @author hrne
 *
 */
public interface SenDht11Dao extends BaseDao<SenDht11>{
			
	/**
	 * 依據感應裝置主檔id查詢溫濕度資料，依照更新日期新到舊排序
	 * 
	 * @param modMainId 感應裝置主檔id
	 * @return SenDht11 list
	 */
	List<SenDht11> find_modMainId_desc(Integer modMainId);


}