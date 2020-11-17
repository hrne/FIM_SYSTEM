package com.springmvc.dao;

import java.util.Date;
import java.util.List;

import com.modle.dao.BaseDao;
import com.springmvc.entity.SenDht11;
import com.springmvc.entity.SenFireAlm;

/**
 * 火災警報感應資料Dao介面
 * 
 * @author hrne
 *
 */
public interface SenFireAlmDao extends BaseDao<SenFireAlm>{
			
	/**
	 * 依據感應裝置主檔id查詢火災警報感應資料，依照更新日期新到舊排序
	 * 
	 * @param modMainId 感應裝置主檔id
	 * @return SenFireAlm list
	 */
	List<SenFireAlm> find_modMainId_desc(Integer modMainId);
	
	/**
	 * 依據感應裝置主檔id、日期區間，查詢火災警報感應資料，依照更新日期新到舊排序
	 * 
	 * @param modMainId 感應裝置主檔id
	 * @param startDate 查詢起日
	 * @param endDate 查詢迄日
	 * @return
	 */
	List<SenFireAlm> find_modMainId_date_desc(Integer modMainId, Date startDate, Date endDate);


}