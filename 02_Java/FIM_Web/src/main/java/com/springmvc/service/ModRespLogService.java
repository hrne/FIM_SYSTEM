package com.springmvc.service;

import com.modle.service.BaseService;
import com.springmvc.entity.ModData;
import com.springmvc.entity.ModRespLog;

/**
 * 感應紀錄的Service介面
 * 
 * @author hrne
 *
 */
public interface ModRespLogService extends BaseService<ModRespLog>{
	
	/**
	 * 儲存感應紀錄
	 * 
	 * @param modData
	 * @param status
	 * @param message
	 */
	void createRespLog(ModData modData, boolean status, String message);

}