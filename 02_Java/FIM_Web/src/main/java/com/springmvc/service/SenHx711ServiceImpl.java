package com.springmvc.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.modle.service.BaseServiceImpl;
import com.springmvc.dao.SenHx711Dao;
import com.springmvc.entity.ModMain;
import com.springmvc.entity.ModSen;
import com.springmvc.entity.SenHx711;

/**
 * 重量hx711感應資料Service實作
 * 
 * @author hrne
 *
 */
@Service("senHx711Service")
public class SenHx711ServiceImpl extends BaseServiceImpl<SenHx711> implements SenHx711Service {

	@Autowired
	private ModMainService modMainService;

	@Autowired
	private ModRespLogService modRespLogService;

	@Autowired
	private SenHx711Dao senHx711Dao;

	public void save_respJson(ModMain modMain, ModSen modSen, String respJson) {

		// 將回傳資料轉成json
		JSONObject obj = new JSONObject(respJson);

		try {
			// 取出重量
			BigDecimal weight = obj.getBigDecimal("weight");

			// 產生資料
			SenHx711 senHx711 = new SenHx711();
			senHx711.setModMain(modMain);
			senHx711.setWeight(weight);

			// 將資料寫入DB
			create(senHx711);

			// 儲存成功紀錄，00:連線正常
			modRespLogService.save_modData_modSen(modMain, modSen, "00", obj.toString());
			
		} catch (Exception e) {
			// 回傳資料若其中有一筆空值，則不寫入並儲存錯誤訊息紀錄，02:讀取不到感應模組資料
			modRespLogService.save_modData_modSen(modMain, modSen, "02", obj.toString());
		}
	}

	public List<SenHx711> find_latest_modMain() {

		// 查詢所有啟用的感應裝置主檔
		List<ModMain> modMainList = modMainService.find_modEnabled();

		List<SenHx711> senHx711List = new ArrayList<SenHx711>();

		List<SenHx711> results = new ArrayList<SenHx711>();

		for (ModMain modMain : modMainList) {
			
			// 查詢感應裝置重量列表，依據更新日期排序
			senHx711List = senHx711Dao.find_modMainId_desc(modMain.getId());
			
			// 判斷是否有資料
			if (!CollectionUtils.isEmpty(senHx711List)) {
				// 取最新一筆放入
				results.add(senHx711List.get(0));
			}
		}
		return results;
	}

}
