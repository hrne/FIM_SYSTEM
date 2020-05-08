package com.springmvc.service;

import java.util.List;

import com.modle.service.BaseService;
import com.springmvc.entity.ModParmData;
import com.springmvc.entity.ModSen;

/**
 * 感應模組參數的Service介面
 * 
 * @author hrne
 *
 */
public interface ModParmService extends BaseService<ModParmData>{

	/**
	 * 依據感應模組ID查詢參數，排除不顯示
	 * 
	 * @param modSen_id
	 * @return
	 */
	List<ModParmData> findModParmBySenId(Integer modSen_id);
}