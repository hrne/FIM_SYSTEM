package com.springmvc.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.modle.service.BaseServiceImpl;
import com.springmvc.entity.SenDht11;
import com.springmvc.dao.SenDht11Dao;
import com.springmvc.entity.ModMain;
import com.springmvc.entity.ModSen;

/**
 * 溫濕度dht11感應資料Service實作
 * 
 * @author hrne
 *
 */
@Service("senDht11Service")
public class SenDht11ServiceImpl extends BaseServiceImpl<SenDht11> implements SenDht11Service {

	@Autowired
	private ModMainService modMainService;

	@Autowired
	private ModRespLogService modRespLogService;

	@Autowired
	private SenDht11Dao senDht11Dao;

	public void save_respJson(ModMain modMain, ModSen modSen, String respJson) {

		// 將回傳資料轉成json
		JSONObject obj = new JSONObject(respJson);

		try {
			// 取出濕度
			BigDecimal humidity = obj.getBigDecimal("humidity");
			// 取出溫度(攝氏H)
			BigDecimal tempCal = obj.getBigDecimal("temp_cal");
			// 取出溫度(華氏C)
			BigDecimal tempFah = obj.getBigDecimal("temp_fah");

			// 產生資料
			SenDht11 senDht11 = new SenDht11();
			senDht11.setModMain(modMain);
			senDht11.setHumidity(humidity);
			senDht11.setTempCal(tempCal);
			senDht11.setTempFah(tempFah);

			// 將資料寫入DB
			create(senDht11);

			// 儲存成功紀錄，00:連線正常
			modRespLogService.save_modData_modSen(modMain, modSen, "00", obj.toString());
			
		} catch (Exception e) {
			// 回傳資料若其中有一筆空值，則不寫入並儲存錯誤訊息紀錄，02:讀取不到感應模組資料
			modRespLogService.save_modData_modSen(modMain, modSen, "02", obj.toString());
		}
	}

	public List<SenDht11> find_latest_modMain() {

		// 查詢所有啟用的感應裝置主檔
		List<ModMain> modMainList = modMainService.find_modEnabled();

		List<SenDht11> senDht11List = new ArrayList<SenDht11>();

		List<SenDht11> results = new ArrayList<SenDht11>();

		for (ModMain modMain : modMainList) {
			
			// 查詢感應裝置溫濕度列表，依據更新日期排序
			senDht11List = senDht11Dao.find_modMainId_desc(modMain.getId());
			
			// 判斷是否有資料
			if (!CollectionUtils.isEmpty(senDht11List)) {
				// 取最新一筆放入
				results.add(senDht11List.get(0));
			}
		}
		return results;
	}

}
