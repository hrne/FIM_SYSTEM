package com.springmvc.dao;

import java.util.List;

import com.modle.dao.BaseDao;
import com.springmvc.entity.ModMain;
import com.springmvc.entity.ModSen;

/**
 * 感應模組的Dao介面
 * 
 * @author hrne
 *
 */
public interface ModSenDao extends BaseDao<ModSen>{

	/**
	 * 依據感應模組代碼查詢
	 * 
	 * @param senCode 感應模組代碼
	 * @return ModSen
	 */
	ModSen findBySenCode(String senCode);

}