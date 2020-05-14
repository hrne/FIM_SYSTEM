package com.springmvc.service;

import com.modle.service.BaseService;
import com.springmvc.entity.SysRespStatus;

/**
 * 回傳狀態代碼檔Service介面
 * 
 * @author hrne
 *
 */
public interface SysRespStatusService extends BaseService<SysRespStatus> {

	/**
	 * 依據回傳代碼查詢
	 * 
	 * @param statusCode 回傳代碼
	 * @return SysRespStatus
	 */
	SysRespStatus find_statusCode(String statusCode);
	
}