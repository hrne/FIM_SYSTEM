package com.springmvc.dao;

import java.util.List;

import com.modle.dao.BaseDao;
import com.springmvc.entity.ModData;
import com.springmvc.entity.SenHx711;

/**
 * 重量hx711感應資料的Dao介面
 * 
 * @author hrne
 *
 */
public interface SenHx711Dao extends BaseDao<SenHx711> {
	
	/**
	 * 依據感應裝置查詢重量資料，依照更新日期新到舊排序
	 * 
	 * @param modData 感應裝置
	 * @return SenHx711 list 重量列表
	 */
	List<SenHx711> findHx711OrderData(ModData modData);

	

}