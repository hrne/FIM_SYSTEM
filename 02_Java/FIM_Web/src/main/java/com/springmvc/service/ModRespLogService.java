package com.springmvc.service;

import java.util.List;

import com.modle.service.BaseService;
import com.springmvc.dto.ModRespLogDto;
import com.springmvc.dto.SenDht11Dto;
import com.springmvc.entity.ModMain;
import com.springmvc.entity.ModRespLog;
import com.springmvc.entity.ModSen;
import com.springmvc.entity.SenDht11;

/**
 * 感應紀錄Service介面
 * 
 * @author hrne
 *
 */
public interface ModRespLogService extends BaseService<ModRespLog> {

	/**
	 * 依據感應裝置主檔儲存使用感應模組的log資料
	 * 
	 * @param modMain 感應裝置主檔
	 * @param statusCode 回傳代碼
	 * @param respMessage 回傳訊息
	 */
	void save_modData_statusCode(ModMain modMain, String statusCode, String respMessage);
	
	/**
	 * 依據使用感應模組儲存log資料
	 * 
	 * @param modMain 感應裝置主檔
	 * @param modSen 感應模組
	 * @param statusCode 回傳代碼
	 * @param respMessage 回傳訊息
	 */ 
	void save_modData_modSen(ModMain modMain, ModSen modSen, String statusCode, String respMessage);
	
	/**
	 * 查詢啟用感應裝置的感應紀錄
	 * 
	 * @return ModRespLog list
	 */
	List<ModRespLog> find_latest_modMain();
	
	/**
	 * 查詢於頁面顯示感應紀錄資料
	 * 
	 * @return ModRespLogDto list
	 */
	List<ModRespLogDto>find_show_page();

}