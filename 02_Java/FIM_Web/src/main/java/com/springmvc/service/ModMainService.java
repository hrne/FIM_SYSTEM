package com.springmvc.service;

import java.util.List;

import com.modle.service.BaseService;
import com.springmvc.dto.ModMainDto;
import com.springmvc.entity.ModMain;

/**
 * 感應裝置主檔Service介面
 * 
 * @author hrne
 *
 */
public interface ModMainService extends BaseService<ModMain> {
	
	/**
	 * 查詢所有啟用的感應裝置
	 * 
	 * @return ModMain list
	 */
	List<ModMain> find_modEnabled();
	
	/**
	 * 依據感應裝置主檔Dto儲存資料
	 * 
	 * @param modMainDto 感應裝置主檔Dto
	 */
	void save_modMainDto(ModMainDto modMainDto);
	
	/**
	 * 掃描感應裝置資料
	 */
	void scan_MainMod();
	
//	/**
//	 * 頁面折線圖初始化使用
//	 * 
//	 * @return ModMain List
//	 */
//	List<ModMainDto> find_chart_init();

}