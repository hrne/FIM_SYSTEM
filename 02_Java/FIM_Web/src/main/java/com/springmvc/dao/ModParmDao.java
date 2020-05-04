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
	 * 依據感應裝置查詢溫濕度資料，依照更新日期新到舊排序
	 * 
	 * @param modData 感應裝置
	 * @return SenDht11 list 溫濕度列表
	 */
	List<ModParm> findModParmBySen(Integer modSen_id);


}