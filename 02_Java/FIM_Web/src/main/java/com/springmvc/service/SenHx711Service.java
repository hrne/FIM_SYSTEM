package com.springmvc.service;

import java.util.List;

import com.modle.service.BaseService;
import com.springmvc.dto.SenDht11Dto;
import com.springmvc.dto.SenHx711Dto;
import com.springmvc.entity.ModMain;
import com.springmvc.entity.ModSen;
import com.springmvc.entity.SenHx711;

/**
 * 重量hx711感應資料Service介面
 * 
 * @author hrne
 *
 */
public interface SenHx711Service extends BaseService<SenHx711> {

	/**
	 * 依據回傳Json儲存資料，若資料有錯誤則紀錄log不儲存
	 * 
	 * @param modMain 感應裝置主檔
	 * @param modSen 感應模組
	 * @param respJson 回傳Json
	 */
	void save_respJson(ModMain modMain, ModSen modSen, String respJson);
	
	/**
	 * 依據啟用感應裝置主檔查詢最新的重量資料
	 * 
	 * @return SenHx711 list
	 */
	List<SenHx711> find_latest_modMain();
	
	/**
	 * 查詢於頁面顯示重量資料
	 * 
	 * @return SenDht11Dto list
	 */
	List<SenHx711Dto>find_show_page();

}