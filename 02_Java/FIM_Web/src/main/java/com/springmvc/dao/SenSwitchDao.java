package com.springmvc.dao;

import java.util.List;

import com.modle.dao.BaseDao;
import com.springmvc.entity.ModMain;
import com.springmvc.entity.SenDht11;
import com.springmvc.entity.SenSwitch;

/**
 * 電源開關感應資料的Dao介面
 * 
 * @author hrne
 *
 */
public interface SenSwitchDao extends BaseDao<SenSwitch>{
			
	/**
	 * 依據感應裝置查詢電源開關資料，依照更新日期新到舊排序
	 * 
	 * @param modData 感應裝置
	 * @return SenSwitch list 電源開關列表
	 */
	List<SenSwitch> findSwitchOrderData(ModMain modData);

}