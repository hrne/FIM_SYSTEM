package com.springmvc.service;

import com.modle.service.BaseService;
import com.springmvc.entity.ModData;
import com.springmvc.entity.ModRespLog;
import com.springmvc.entity.ModSen;

/**
 * 感應紀錄的Service介面
 * 
 * @author hrne
 *
 */
public interface ModRespLogService extends BaseService<ModRespLog> {

	/**
	 * 將錯誤資料寫入每一個感應模組
	 * 
	 * @param modData 感應裝置 
	 * @param statusCode 回傳代碼
	 * @param message 回傳訊息
	 */
	void createRespLogByModData(ModData modData, String statusCode, String message);
	
	/**
	 * 儲存感應模組紀錄
	 * 
	 * @param modData 感應裝置 
	 * @param modSen 感應模組
	 * @param statusCode 回傳代碼
	 * @param message 回傳訊息
	 */
	void createRespLogByModSen(ModData modData, ModSen modSen, String statusCode, String message);

}