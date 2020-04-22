package com.springmvc.service;

import org.springframework.stereotype.Service;
import com.modle.service.BaseServiceImpl;
import com.springmvc.entity.ModData;
import com.springmvc.entity.ModRespLog;

/**
 * 感應紀錄的Service實做
 * 
 * @author hrne
 *
 */
@Service("modRespLogService")
public class ModRespLogServiceImpl extends BaseServiceImpl<ModRespLog> implements ModRespLogService {

	public void createRespLog(ModData modData, boolean status, String message) {

		// 將資料寫入
		ModRespLog modRespLog = new ModRespLog();
		modRespLog.setModData(modData);
		modRespLog.setSucStatus(status);
		modRespLog.setRespMessage(message);

		// 將資料寫入DB
		create(modRespLog);
	}
}
