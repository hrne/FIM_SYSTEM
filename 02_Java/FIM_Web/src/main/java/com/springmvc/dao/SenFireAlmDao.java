package com.springmvc.dao;

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


}