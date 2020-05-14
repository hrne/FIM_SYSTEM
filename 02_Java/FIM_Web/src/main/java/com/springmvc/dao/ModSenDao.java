package com.springmvc.dao;

import com.modle.dao.BaseDao;
import com.springmvc.entity.ModSen;

/**
 * 感應模組Dao介面
 * 
 * @author hrne
 *
 */
public interface ModSenDao extends BaseDao<ModSen>{

	/**
	 * 依據模組代碼查詢感應模組資料
	 * 
	 * @param senCode 模組代碼
	 * @return ModSen
	 */
	ModSen find_senCode(String senCode);

}