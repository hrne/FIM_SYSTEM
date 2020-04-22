package com.springmvc.service;

import java.util.List;

import com.modle.service.BaseService;
import com.springmvc.dto.ModDataDto;
import com.springmvc.entity.ModData;

/**
 * 感應裝置的Service介面
 * 
 * @author hrne
 *
 */
public interface ModDataService extends BaseService<ModData> {
	
	/**
	 * 查詢所有啟用的感應器
	 * 
	 * @return ModData list
	 */
	List<ModData> findByModEnable();
	
	/**
	 * 依據Dto儲存感應裝置
	 * 
	 * @param modDataDto
	 */
	void saveModDataByDto(ModDataDto modDataDto);

}