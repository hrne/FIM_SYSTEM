package com.springmvc.dao;

import java.util.List;

import com.modle.dao.BaseDao;
import com.springmvc.entity.ModParmData;
import com.springmvc.entity.ModSen;

/**
 * 模組參數資料Dao介面
 * 
 * @author hrne
 *
 */
public interface ModParmDataDao extends BaseDao<ModParmData>{
	
	/**
	 * 依據感應模組id查詢模組參數資料，篩選只於修改畫面顯示
	 * 
	 * @param modSenId 感應模組id
	 * @return ModParmData list
	 */
	List<ModParmData> find_modSenId_show(Integer modSenId);

	/**
	 * 依據參數代號查詢模組參數資料
	 * 
	 * @param parmCode 參數代號
	 * @return ModParmData
	 */
	ModParmData find_parmCode(String parmCode);
}