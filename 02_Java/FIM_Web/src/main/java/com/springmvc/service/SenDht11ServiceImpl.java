package com.springmvc.service;

import java.math.BigDecimal;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import com.modle.service.BaseServiceImpl;
import com.springmvc.entity.SenDht11;
import com.springmvc.entity.SenMach;

/**
 * 溫濕度dht11感應資料的Service實做
 * 
 * @author hrne
 *
 */
@Service("senDht11Service")
public class SenDht11ServiceImpl extends BaseServiceImpl<SenDht11> implements SenDht11Service {

	/**
	 * 儲存溫濕度dht11感應資料
	 * @param senMach 感應器資料
	 * @param respJSON 回傳JSON
	 */
	public void createDht11(SenMach senMach, String respJSON) {
		
		
		//將回傳資料轉成json
		JSONObject obj = new JSONObject(respJSON);
		
		SenDht11 senDht11 = new SenDht11();
		
		//寫入感應器ID
		senDht11.setSenMachId(senMach.getId());
	
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

}
