package com.springmvc.service;

import java.math.BigDecimal;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.modle.service.BaseServiceImpl;
import com.springmvc.entity.SenDht11;
import com.springmvc.entity.SenDht11PK;
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
	 * @param senMach 工具機資料
	 * @param respJSON 回傳JSON
	 */
	public void createDht11(SenMach senMach, String respJSON) {
		
		//建立與工具機關聯
		SenDht11PK senDht11PK = new SenDht11PK();
		senDht11PK.setSenMach(senMach);
		
		//將回傳資料轉成json
		JSONObject obj = new JSONObject(respJSON);
		
		SenDht11 senDht11 = new SenDht11();
		senDht11.setSenDht11PK(senDht11PK);
	
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
