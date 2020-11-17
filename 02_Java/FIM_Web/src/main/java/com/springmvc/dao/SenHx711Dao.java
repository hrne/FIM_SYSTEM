package com.springmvc.dao;

import java.util.Date;
import java.util.List;

import com.modle.dao.BaseDao;
import com.springmvc.entity.SenFireAlm;
import com.springmvc.entity.SenHx711;

/**
 * 重量hx711感應資料Dao介面
 * 
 * @author hrne
 *
 */
public interface SenHx711Dao extends BaseDao<SenHx711> {

	/**
	 * 依據感應裝置主檔id查詢重量資料，依照更新日期新到舊排序
	 * 
	 * @param modMainId 感應裝置主檔id
	 * @return SenHx711 list
	 */
	List<SenHx711> find_modMainId_desc(Integer modMainId);
	
	/**
	 * 依據感應裝置主檔id、日期區間，查詢重量資料，依照更新日期新到舊排序
	 * 
	 * @param modMainId 感應裝置主檔id
	 * @param startDate 查詢起日
	 * @param endDate 查詢迄日
	 * @return
	 */
	List<SenHx711> find_modMainId_date_desc(Integer modMainId, Date startDate, Date endDate);

}