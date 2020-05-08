package com.springmvc.service;

import java.util.List;

import com.modle.service.BaseService;
import com.springmvc.dto.ModDataDto;
import com.springmvc.entity.ModMain;

/**
 * 感應裝置的Service介面
 * 
 * @author hrne
 *
 */
public interface ModDataService extends BaseService<ModMain> {
	
	/**
	 * 查詢所有啟用的感應裝置
	 * 
	 * @return ModData list
	 */
	List<ModMain> findByModEnable();
	
	/**
	 * 依據Dto儲存感應裝置
	 * 
	 * @param modDataDto
	 */
	void saveModDataByDto(ModDataDto modDataDto);

}