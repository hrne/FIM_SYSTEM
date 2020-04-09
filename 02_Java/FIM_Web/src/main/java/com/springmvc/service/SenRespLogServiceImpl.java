package com.springmvc.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.modle.service.BaseServiceImpl;
import com.springmvc.entity.SenMach;
import com.springmvc.entity.SenRespLog;
import com.springmvc.entity.SenRespLogPK;

/**
 * 工具機感應紀錄的Service實做
 * 
 * @author hrne
 *
 */
@Service("senRespLogService")
public class SenRespLogServiceImpl extends BaseServiceImpl<SenRespLog> implements SenRespLogService {
	
	/**
	 * 儲存工具機感應紀錄
	 * 
	 * @param senMach 工具機
	 * @param status 成功與否
	 * @param message 回傳訊息
	 */
	public void createRespLog(SenMach senMach, boolean status, String message) {
		//建立與工具機關聯
		SenRespLogPK senRespLogPK = new SenRespLogPK();
		senRespLogPK.setSenMach(senMach);
		
		//將資料寫入
		SenRespLog senRespLog = new SenRespLog();
		senRespLog.setSenRespLogPK(senRespLogPK);
		senRespLog.setSucStatus(status);
		senRespLog.setRespMessage(message);

		//將資料寫入DB
		create(senRespLog);
	}
}
