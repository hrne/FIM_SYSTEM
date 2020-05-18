package com.springmvc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.modle.service.BaseServiceImpl;
import com.modle.util.ObjectMapperUtils;
import com.springmvc.dao.ModRespLogDao;
import com.springmvc.dao.SenDht11Dao;
import com.springmvc.dto.ModRespLogDto;
import com.springmvc.dto.SenFireAlmDto;
import com.springmvc.entity.ModMain;
import com.springmvc.entity.ModRespLog;
import com.springmvc.entity.ModSen;
import com.springmvc.entity.SenDht11;
import com.springmvc.entity.SenFireAlm;
import com.springmvc.entity.SysRespStatus;

/**
 * 感應紀錄Service實作
 * 
 * @author hrne
 *
 */
@Service("modRespLogService")
public class ModRespLogServiceImpl extends BaseServiceImpl<ModRespLog> implements ModRespLogService {

	@Autowired
	private SysRespStatusService sysRespStatusService;

	@Autowired
	private ModMainService modMainService;

	@Autowired
	private ModRespLogDao modRespLog;

	public void save_modData_statusCode(ModMain modMain, String statusCode, String respMessage) {

		// 依據回傳代碼查詢回傳狀態代碼檔
		SysRespStatus sysRespStatus = sysRespStatusService.find_statusCode(statusCode);

		// 取出感應裝置主檔使用的感應模組
		for (ModSen modSen : modMain.getModSenSet()) {

			// 產生資料
			ModRespLog modRespLog = new ModRespLog();
			modRespLog.setModMain(modMain);
			modRespLog.setModSen(modSen);
			modRespLog.setSysRespStatus(sysRespStatus);
			modRespLog.setRespMessage(respMessage);

			// 將資料寫入DB
			create(modRespLog);
		}
	}

	public void save_modData_modSen(ModMain modMain, ModSen modSen, String statusCode, String respMessage) {

		// 依據回傳代碼查詢回傳狀態代碼檔
		SysRespStatus sysRespStatus = sysRespStatusService.find_statusCode(statusCode);

		// 產生資料
		ModRespLog modRespLog = new ModRespLog();
		modRespLog.setModMain(modMain);
		modRespLog.setModSen(modSen);
		modRespLog.setSysRespStatus(sysRespStatus);
		modRespLog.setRespMessage(respMessage);

		// 將資料寫入DB
		create(modRespLog);
	}

	public List<ModRespLog> find_latest_modMain() {

		// 查詢所有啟用的感應裝置主檔
		List<ModMain> modMainList = modMainService.find_modEnabled();

		List<ModRespLog> modRespLogList = new ArrayList<ModRespLog>();

		List<ModRespLog> results = new ArrayList<ModRespLog>();

		for (ModMain modMain : modMainList) {

			// 依據每個感應裝置的模組查詢紀錄
			for (ModSen modSen : modMain.getModSenSet()) {
				// 查詢感應裝置感應紀錄列表，依據更新日期排序
				modRespLogList = modRespLog.find_modMainId_modSenId_desc(modMain.getId(), modSen.getId());

				// 判斷是否有資料
				if (!CollectionUtils.isEmpty(modRespLogList)) {
					// 取最新一筆放入
					results.add(modRespLogList.get(0));
				}
			}
		}
		return results;
	}

	public List<ModRespLogDto> find_show_page() {
		// 查詢感應紀錄資料
		List<ModRespLog> modRespLogList = find_latest_modMain();

		// 將感應紀錄資料map到dto上供頁面顯示
		List<ModRespLogDto> modRespLogDtoList = ObjectMapperUtils.mapAll(modRespLogList, ModRespLogDto.class);
		return modRespLogDtoList;

	}

}
