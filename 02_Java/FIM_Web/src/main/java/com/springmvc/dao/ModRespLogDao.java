package com.springmvc.dao;

import java.util.List;

import com.modle.dao.BaseDao;
import com.springmvc.entity.ModRespLog;
import com.springmvc.entity.SenDht11;

/**
 * 感應紀錄Dao介面
 * 
 * @author hrne
 *
 */
public interface ModRespLogDao extends BaseDao<ModRespLog>{
	
	/**
	 * 依據感應裝置主檔id查詢感應紀錄資料，依照更新日期新到舊排序
	 * 
	 * @param modMainId 感應裝置主檔id
	 * @return ModRespLog list
	 */
	List<ModRespLog> find_modMainId_desc(Integer modMainId);
	
	/**
	 * 依據感應裝置主檔id及感應模組id查詢感應紀錄資料，依照更新日期新到舊排序
	 * 
	 * @param modMainId 感應裝置主檔id
	 * @param modSenId 感應模組id
	 * @return ModRespLog list
	 */
	List<ModRespLog> find_modMainId_modSenId_desc(Integer modMainId,Integer modSenId);

}