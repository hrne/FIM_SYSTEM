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
import com.springmvc.dao.SenHx711Dao;
import com.springmvc.dto.SenDht11Dto;
import com.springmvc.dto.SenHx711Dto;
import com.springmvc.entity.ModMain;
import com.springmvc.entity.ModParmData;
import com.springmvc.entity.ModSen;
import com.springmvc.entity.SenDht11;
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
	private ModParmDataService modParmDataService;

	@Autowired
	private SenHx711Dao senHx711Dao;

	public void save_respJson(ModMain modMain, ModSen modSen, String respJson) {

		// 將回傳資料轉成json
		JSONObject obj = new JSONObject(respJson);

		try {
			// 取出重量
			BigDecimal weight = obj.getBigDecimal("weight");
			BigDecimal t = new BigDecimal(5);
			if(weight.compareTo(t)<=0) {
				weight = new BigDecimal(0);
			}
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

			// 判斷感應裝置是否有設定重量感應模組
			for (ModSen modSen : modMain.getModSenSet()) {
				if (modSen.getSenCode().equals("hx711")) {
					// 查詢感應裝置重量列表，依據更新日期排序
					senHx711List = senHx711Dao.find_modMainId_desc(modMain.getId());

					// 判斷是否有資料
					if (!CollectionUtils.isEmpty(senHx711List)) {
						// 取最新一筆放入
						results.add(senHx711List.get(0));
					}
				}
			}
		}
		return results;
	}

	public List<SenHx711Dto> find_show_page() {

		// 查詢頁面顯示重量資料
		List<SenHx711> enHx711List = find_latest_modMain();

		List<SenHx711Dto> senHx711DtoList = new ArrayList<SenHx711Dto>();

		// 逐筆判斷
		for (SenHx711 senHx711 : enHx711List) {
			// 將重量資料map到dto上
			SenHx711Dto senHx711Dto = ObjectMapperUtils.map(senHx711, SenHx711Dto.class);
			// 找出重量(g)參數設定
			ModParmData modParmDataWeight = modParmDataService.find_parmCode("weight");

			// 放入資料至dto
			senHx711Dto.setModParmDataWeight(modParmDataWeight);

			// 放入dto list
			senHx711DtoList.add(senHx711Dto);
		}

		return senHx711DtoList;
	}

	public List<SenHx711Dto> find_show_chart(int modMainId, Date startDate, Date endDate) {

		List<SenHx711> senHx711List = new ArrayList<SenHx711>();

		List<SenHx711Dto> senHx711DtoList = new ArrayList<SenHx711Dto>();

		// 查詢感應裝置重量列表，依據更新日期排序
		senHx711List = senHx711Dao.find_modMainId_date_desc(modMainId, startDate, endDate);

		for (int i = 0; i < senHx711List.size(); i++) {
			if (senHx711List.get(i) != null) {
				// 將濕度資料map到dto上
				SenHx711Dto senHx711Dto = ObjectMapperUtils.map(senHx711List.get(i), SenHx711Dto.class);
				senHx711DtoList.add(senHx711Dto);
			}
		}

		return senHx711DtoList;
	}

}
