package com.springmvc.dao;

import java.util.List;

import com.modle.dao.BaseDao;
import com.springmvc.entity.ModData;
import com.springmvc.entity.ModParm;
import com.springmvc.entity.ModSen;
import com.springmvc.entity.SenDht11;

/**
 * 感應模組參數的Dao介面
 * 
 * @author hrne
 *
 */
public interface ModParmDao extends BaseDao<ModParm>{
	
	/**
	 * 依據感應模組ID查詢參數，排除不顯示
	 * 
	 * @param modSen_id
	 * @return
	 */
	List<ModParm> findModParmBySenId(Integer modSen_id);


}