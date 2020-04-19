package com.springmvc.service;

import com.modle.service.BaseService;
import com.springmvc.entity.SenMach;
import com.springmvc.entity.SenRespLog;

/**
 * 感應器感應紀錄的Service介面
 * 
 * @author hrne
 *
 */
public interface SenRespLogService extends BaseService<SenRespLog>{

	/**
	 * 儲存感應器感應紀錄
	 * 
	 * @param senMach 感應器
	 * @param status 成功與否
	 * @param message 回傳訊息
	 */
	void createRespLog(SenMach senMach, boolean status, String message);

}