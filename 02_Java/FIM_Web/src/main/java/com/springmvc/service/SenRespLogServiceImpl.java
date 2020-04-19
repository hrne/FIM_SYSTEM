package com.springmvc.service;

import org.springframework.stereotype.Service;
import com.modle.service.BaseServiceImpl;
import com.springmvc.entity.SenMach;
import com.springmvc.entity.SenRespLog;

/**
 * 感應器感應紀錄的Service實做
 * 
 * @author hrne
 *
 */
@Service("senRespLogService")
public class SenRespLogServiceImpl extends BaseServiceImpl<SenRespLog> implements SenRespLogService {
	
	/**
	 * 儲存感應器感應紀錄
	 * 
	 * @param senMach 感應器
	 * @param status 成功與否
	 * @param message 回傳訊息
	 */
	public void createRespLog(SenMach senMach, boolean status, String message) {
		
		//將資料寫入
		SenRespLog senRespLog = new SenRespLog();
		senRespLog.setSenMachId(senMach.getId());
		senRespLog.setSucStatus(status);
		senRespLog.setRespMessage(message);

		//將資料寫入DB
		create(senRespLog);
	}
}
