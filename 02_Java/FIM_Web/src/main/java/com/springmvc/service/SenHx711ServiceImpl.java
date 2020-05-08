package com.springmvc.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.modle.service.BaseServiceImpl;
import com.springmvc.dao.ModMainDao;
import com.springmvc.dao.SenHx711Dao;
import com.springmvc.entity.ModMain;
import com.springmvc.entity.ModSen;
import com.springmvc.entity.SenDht11;
import com.springmvc.entity.SenHx711;

/**
 * 重量hx711感應資料的Service實做
 * 
 * @author hrne
 *
 */
@Service("senHx711Service")
public class SenHx711ServiceImpl extends BaseServiceImpl<SenHx711> implements SenHx711Service {

	@Autowired
	private ModRespLogService modRespLogService;

	@Autowired
	private ModMainDao modDataDao;

	@Autowired
	private SenHx711Dao senHx711Dao;

	public void createHx711(ModMain modData, ModSen modSen, String respJSON) {
		
		// 將回傳資料轉成json
		JSONObject obj = new JSONObject(respJSON);
		
		try {
			// 取出重量
			BigDecimal weight = obj.getBigDecimal("weight");
			SenHx711 senHx711 = new SenHx711();
			// 寫入感應裝置
			senHx711.setModData(modData);
			senHx711.setWeight(weight);
			// 儲存資料
			create(senHx711);
			//儲存成功紀錄
			modRespLogService.createRespLogByModSen(modData, modSen, "00", obj.toString());
		} catch (Exception e) {
			// 回傳資料若其中有一筆空值，則不寫入並寫入錯誤訊息紀錄
			modRespLogService.createRespLogByModSen(modData, modSen, "02", obj.toString());
		}
	}

	public List<SenHx711> findLatestHx711Data() {

		// 查詢所有啟用的感應裝置
		List<ModMain> listModData = modDataDao.findByModEnable();

		List<SenHx711> listHx711 = new ArrayList<SenHx711>();

		List<SenHx711> results = new ArrayList<SenHx711>();

		for (ModMain modData : listModData) {
			// 查詢感應裝置重量列表，依據更新日期排序
			listHx711 = senHx711Dao.findHx711OrderData(modData);
			// 若有資料則放入
			if (!CollectionUtils.isEmpty(listHx711)) {
				// 取最新一筆放入
				results.add(listHx711.get(0));
			}
		}
		return results;
	}

}
