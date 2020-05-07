package com.springmvc.service;

import org.springframework.stereotype.Service;
import com.modle.service.BaseServiceImpl;
import com.springmvc.entity.ModData;
import com.springmvc.entity.ModRespLog;
import com.springmvc.entity.ModSen;

/**
 * 感應紀錄的Service實做
 * 
 * @author hrne
 *
 */
@Service("modRespLogService")
public class ModRespLogServiceImpl extends BaseServiceImpl<ModRespLog> implements ModRespLogService {

	public void createRespLogByModData(ModData modData, String statusCode, String message) {
		
		//將未知錯誤資料寫入每一筆感應模組中
		for(ModSen modSen:modData.getModSenSet()) {
			// 將資料寫入
			ModRespLog modRespLog = new ModRespLog();
			modRespLog.setModData(modData);
			modRespLog.setModSen(modSen);
			modRespLog.setStatusCode(statusCode);
			modRespLog.setRespMessage(message);

			// 將資料寫入DB
			create(modRespLog);
		}

	}
	
	public void createRespLogByModSen(ModData modData, ModSen modSen, String statusCode, String message) {

		// 將資料寫入
		ModRespLog modRespLog = new ModRespLog();
		modRespLog.setModData(modData);
		modRespLog.setModSen(modSen);
		modRespLog.setStatusCode(statusCode);
		modRespLog.setRespMessage(message);

		// 將資料寫入DB
		create(modRespLog);
	}
}
