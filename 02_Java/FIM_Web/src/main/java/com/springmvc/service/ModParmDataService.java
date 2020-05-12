package com.springmvc.service;

import java.util.List;

import com.modle.service.BaseService;
import com.springmvc.dto.ModMainDto;
import com.springmvc.dto.ModParmDataDto;
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
	
	/**
	 * 依據模組參數資料Dto儲存資料
	 * 
	 * @param modParmDataDto 模組參數Dto
	 */
	void save_modParmDataDto(ModParmDataDto modParmDataDto);
	
}