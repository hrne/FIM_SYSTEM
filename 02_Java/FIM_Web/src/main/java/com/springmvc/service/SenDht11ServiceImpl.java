package com.springmvc.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.modle.service.BaseServiceImpl;
import com.modle.util.ObjectMapperUtils;
import com.springmvc.entity.SenDht11;
import com.springmvc.dao.SenDht11Dao;
import com.springmvc.dto.SenDht11Dto;
import com.springmvc.dto.SenSwitchDto;
import com.springmvc.entity.ModMain;
import com.springmvc.entity.ModParmData;
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
	private ModParmDataService modParmDataService;

	@Autowired
	private SenDht11Dao senDht11Dao;

	public void save_respJson(ModMain modMain, ModSen modSen, String respJson) {

		// 將回傳資料轉成json
		JSONObject obj = new JSONObject(respJson);

		try {
			// 取出濕度
			BigDecimal humidity = obj.getBigDecimal("humidity");
			// 取出溫度(攝氏C)
			BigDecimal tempCal = obj.getBigDecimal("temp_cal");
			// 取出溫度(華氏F)
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

			// 判斷感應裝置是否有設定溫濕度感應模組
			for (ModSen modSen : modMain.getModSenSet()) {
				if (modSen.getSenCode().equals("dht11")) {
					// 查詢感應裝置溫濕度列表，依據更新日期排序
					senDht11List = senDht11Dao.find_modMainId_desc(modMain.getId());

					// 判斷是否有資料
					if (!CollectionUtils.isEmpty(senDht11List)) {
						// 取最新一筆放入
						results.add(senDht11List.get(0));
					}
				}
			}
		}
		return results;
	}

	public List<SenDht11Dto> find_show_page() {

		// 查詢頁面顯示溫濕度資料
		List<SenDht11> senDht11List = find_latest_modMain();

		List<SenDht11Dto> senDht11DtoList = new ArrayList<SenDht11Dto>();

		// 逐筆判斷
		for (SenDht11 senDht11 : senDht11List) {
			// 將濕度資料map到dto上
			SenDht11Dto senDht11Dto = ObjectMapperUtils.map(senDht11, SenDht11Dto.class);
			// 找出濕度參數設定
			ModParmData modParmDataHumidity = modParmDataService.find_parmCode("humidity");
			// 找出溫度(攝氏C)參數設定
			ModParmData modParmDataTempCal = modParmDataService.find_parmCode("temp_cal");
			// 找出溫度(華氏F)參數設定
			ModParmData modParmDataTempFah = modParmDataService.find_parmCode("temp_fah");

			// 放入資料至dto
			senDht11Dto.setModParmDataHumidity(modParmDataHumidity);
			senDht11Dto.setModParmDataTempCal(modParmDataTempCal);
			senDht11Dto.setModParmDataTempFah(modParmDataTempFah);

			// 放入dto list
			senDht11DtoList.add(senDht11Dto);
		}

		return senDht11DtoList;
	}

	public List<SenDht11Dto> find_show_chart(int modMainId, Date startDate, Date endDate) {

		List<SenDht11> senDht11List = new ArrayList<SenDht11>();

		List<SenDht11Dto> senDht11DtoList = new ArrayList<SenDht11Dto>();

		// 查詢感應裝置溫濕度列表，依據更新日期排序
		senDht11List = senDht11Dao.find_modMainId_date_desc(modMainId, startDate, endDate);

		int size = senDht11List.size();
		if (size >= 50) {
			size = 50;
		}

		for (int i = 0; i < size; i++) {
			if (senDht11List.get(i) != null) {
				// 將濕度資料map到dto上
				SenDht11Dto senDht11Dto = ObjectMapperUtils.map(senDht11List.get(i), SenDht11Dto.class);
				senDht11DtoList.add(senDht11Dto);
			}
		}

		return senDht11DtoList;
	}

}
