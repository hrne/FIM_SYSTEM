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
public interface ModParmDataService extends BaseService<ModParmData> {

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

	/**
	 * 依據畫面傳回值儲存資料
	 * 
	 * @param id         模組參數id
	 * @param field      修改欄位
	 * @param upperLimit 修改後上限警示值
	 * @param lowerLimit 修改後下限警示值
	 */
	void save_filed_data(int id, String field, int upperLimit, int lowerLimit);

	/**
	 * 依據模組參數id儲存警示值是否啟用
	 * 
	 * @param ModParmDataId
	 */
	void save_modParmDataId(int ModParmDataId, boolean state);
	
	/**
	 * 依據參數代號查詢模組參數資料
	 * 
	 * @param parmCode 參數代號
	 * @return ModParmData
	 */
	ModParmData find_parmCode(String parmCode);

}