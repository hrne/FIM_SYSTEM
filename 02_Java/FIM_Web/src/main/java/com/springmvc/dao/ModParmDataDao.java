package com.springmvc.dao;

import java.util.List;

import com.modle.dao.BaseDao;
import com.springmvc.entity.ModMain;
import com.springmvc.entity.ModParmData;
import com.springmvc.entity.ModSen;
import com.springmvc.entity.SenDht11;

/**
 * 模組參數資料Dao介面
 * 
 * @author hrne
 *
 */
public interface ModParmDataDao extends BaseDao<ModParmData>{
	
	/**
	 * 依據感應模組id查詢，篩選只於修改畫面顯示
	 * 
	 * @param modSen_id
	 * @return
	 */
	List<ModParmData> findByModSenId(Integer modSenId);


}