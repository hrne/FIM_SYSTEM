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
import com.springmvc.dao.ModDataDao;
import com.springmvc.dao.SenDht11Dao;
import com.springmvc.entity.ModData;

/**
 * 溫濕度dht11感應資料的Service實做
 * 
 * @author hrne
 *
 */
@Service("senDht11Service")
public class SenDht11ServiceImpl extends BaseServiceImpl<SenDht11> implements SenDht11Service {
	
	@Autowired
	private SenDht11Dao senDht11Dao;
	
	@Autowired
	private ModDataDao modDataDao;

	public void createDht11(ModData modData, String respJSON) {
		
		//將回傳資料轉成json
		JSONObject obj = new JSONObject(respJSON);
		
		SenDht11 senDht11 = new SenDht11();
		
		//寫入感應裝置
		senDht11.setModData(modData);
	
		//取出濕度
		BigDecimal humidity = obj.getBigDecimal("humidity");
		senDht11.setHumidity(humidity);
		
		//取出溫度(攝氏H)
		BigDecimal tempCal = obj.getBigDecimal("temp_cal");
		senDht11.setTempCal(tempCal);
		
		//取出溫度(華氏C)
		BigDecimal tempFah = obj.getBigDecimal("temp_fah");
		senDht11.setTempFah(tempFah);
		
		//儲存資料
		create(senDht11);
	}
	
	public List<SenDht11> findLatestDht11Data() {
		
		//查詢所有啟用的感應裝置
		List<ModData> listModData = modDataDao.findByModEnable();
		
		List<SenDht11> listDht11 = new ArrayList<SenDht11>();
		
		List<SenDht11> results = new ArrayList<SenDht11>();
		
		for(ModData modData :listModData) {			
			//查詢感應裝置溫濕度列表，依據更新日期排序
			listDht11 = senDht11Dao.findDht11OrderData(modData);			
			//若有資料則放入
			if(!CollectionUtils.isEmpty(listDht11)) {
				//取最新一筆放入
				results.add(listDht11.get(0));
			}
		}
		return results;
	}		

}
