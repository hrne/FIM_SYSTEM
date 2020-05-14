package com.springmvc.service;

import com.modle.service.BaseService;
import com.springmvc.entity.ModSen;

/**
 * 感應模組Service介面
 * 
 * @author hrne
 *
 */
public interface ModSenService extends BaseService<ModSen>{

	/**
	 * 依據模組代碼查詢感應模組資料
	 * 
	 * @param senCode 模組代碼
	 * @return ModSen
	 */
	ModSen find_senCode(String senCode);

}