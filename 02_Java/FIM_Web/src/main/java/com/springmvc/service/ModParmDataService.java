package com.springmvc.service;

import java.util.List;

import com.modle.service.BaseService;
import com.springmvc.entity.ModParmData;

/**
 * 模組參數資料Service介面
 * 
 * @author hrne
 *
 */
public interface ModParmDataService extends BaseService<ModParmData>{

	/**
	 * 依據感應模組id查詢模組參數資料，篩選只於修改畫面顯示
	 * 
	 * @param modSenId 感應模組id
	 * @return ModParmData list
	 */
	List<ModParmData> find_modSenId_show(Integer modSenId);
	
}