package com.springmvc.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.modle.service.BaseServiceImpl;
import com.springmvc.entity.SenDht11;
import com.springmvc.dao.ModMainDao;
import com.springmvc.dao.ModSenDao;
import com.springmvc.dao.SenDht11Dao;
import com.springmvc.entity.ModMain;
import com.springmvc.entity.ModSen;

/**
 * 溫濕度dht11感應資料的Service實做
 * 
 * @author hrne
 *
 */
@Service("senDht11Service")
public class SenDht11ServiceImpl extends BaseServiceImpl<SenDht11> implements SenDht11Service {

	@Autowired
	private ModRespLogService modRespLogService;

	@Autowired
	private ModMainDao modDataDao;

	@Autowired
	private SenDht11Dao senDht11Dao;

	public void createDht11(ModMain modData, ModSen modSen, String respJSON) {

		// 將回傳資料轉成json
		JSONObject obj = new JSONObject(respJSON);

		try {
			// 取出濕度
			BigDecimal humidity = obj.getBigDecimal("humidity");
			// 取出溫度(攝氏H)
			BigDecimal tempCal = obj.getBigDecimal("temp_cal");
			// 取出溫度(華氏C)
			BigDecimal tempFah = obj.getBigDecimal("temp_fah");

			// 讀取資料都有值，才寫入
			SenDht11 senDht11 = new SenDht11();
			// 寫入感應裝置
			senDht11.setModData(modData);
			senDht11.setHumidity(humidity);
			senDht11.setTempCal(tempCal);
			senDht11.setTempFah(tempFah);

			// 儲存資料
			create(senDht11);
			//儲存成功紀錄
			modRespLogService.createRespLogByModSen(modData, modSen, "00", obj.toString());
		} catch (Exception e) {
			// 回傳資料若其中有一筆空值，則不寫入並寫入錯誤訊息紀錄
			modRespLogService.createRespLogByModSen(modData, modSen, "02", obj.toString());
		}

		// 回傳資料若其中有一筆空值，則不寫入並寫入錯誤訊息紀錄
	}

	public List<SenDht11> findLatestDht11Data() {

		// 查詢所有啟用的感應裝置
		List<ModMain> listModData = modDataDao.findByModEnable();

		List<SenDht11> listDht11 = new ArrayList<SenDht11>();

		List<SenDht11> results = new ArrayList<SenDht11>();

		for (ModMain modData : listModData) {
			// 查詢感應裝置溫濕度列表，依據更新日期排序
			listDht11 = senDht11Dao.findDht11OrderData(modData);
			// 若有資料則放入
			if (!CollectionUtils.isEmpty(listDht11)) {
				// 取最新一筆放入
				results.add(listDht11.get(0));
			}
		}
		return results;
	}

}
