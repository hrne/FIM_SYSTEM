package com.springmvc.dao;

import java.util.List;

import com.modle.dao.BaseDao;
import com.springmvc.entity.SenSwitch;

/**
 * 電源開關感應資料Dao介面
 * 
 * @author hrne
 *
 */
public interface SenSwitchDao extends BaseDao<SenSwitch>{
			
	/**
	 * 依據感應裝置主檔id查詢電源開關資料，依照更新日期新到舊排序
	 * 
	 * @param modMainId 感應裝置主檔id
	 * @return SenSwitch list
	 */
	List<SenSwitch> find_modMainId_desc(Integer modMainId);
}