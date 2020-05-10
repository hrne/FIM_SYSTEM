package com.springmvc.dao;

import com.modle.dao.BaseDao;
import com.springmvc.entity.SysRespStatus;

/**
 * 回傳狀態代碼檔Dao介面
 * 
 * @author hrne
 *
 */
public interface SysRespStatusDao extends BaseDao<SysRespStatus>{
			
	/**
	 * 依據回傳代碼查詢
	 * 
	 * @param statusCode 回傳代碼
	 * @return SysRespStatus
	 */
	SysRespStatus find_statusCode(String statusCode);

}