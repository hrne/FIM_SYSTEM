package com.springmvc.service;

import java.util.List;

import com.modle.service.BaseService;
import com.springmvc.entity.ModParm;
import com.springmvc.entity.ModSen;

/**
 * 感應模組參數的Service介面
 * 
 * @author hrne
 *
 */
public interface ModParmService extends BaseService<ModParm>{


	/**
	 * 依據感應裝置查詢溫濕度資料，依照更新日期新到舊排序
	 * 
	 * @param modData 感應裝置
	 * @return SenDht11 list 溫濕度列表
	 */
	List<ModParm> findModParmBySen(Integer modSen_id);
}