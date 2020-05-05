package com.springmvc.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.modle.service.BaseServiceImpl;
import com.springmvc.dao.ModDataDao;
import com.springmvc.dao.SenDht11Dao;
import com.springmvc.dao.SenSwitchDao;
import com.springmvc.entity.ModData;
import com.springmvc.entity.SenDht11;
import com.springmvc.entity.SenSwitch;

/**
 * 電源開關感應資料的Service實做
 * 
 * @author hrne
 *
 */
@Service("senSwitchService")
public class SenSwitchServiceImpl extends BaseServiceImpl<SenSwitch> implements SenSwitchService {
	
	@Autowired
	private ModDataDao modDataDao;
	
	@Autowired
	private SenSwitchDao senSwitchDao;

	public void createSwitch(ModData modData, String respJSON) {
		
		//將回傳資料轉成json
		JSONObject obj = new JSONObject(respJSON);
		
		SenSwitch senSwitch = new SenSwitch();
		
		//寫入感應裝置
		senSwitch.setModData(modData);

		//取出電源開關狀態
		Integer powStatus = obj.getInt("pow_status");
		senSwitch.setPowStatus(powStatus);
	
		//取出電池電力(v)
		BigDecimal batteryVolt = obj.getBigDecimal("battery_volt");
		senSwitch.setBatteryVolt(batteryVolt);
		
		//儲存資料
		create(senSwitch);
	}
	
	public List<SenSwitch> findLatestSwitchData() {
		
		//查詢所有啟用的感應裝置
		List<ModData> listModData = modDataDao.findByModEnable();
		
		List<SenSwitch> listSwitch = new ArrayList<SenSwitch>();
		
		List<SenSwitch> results = new ArrayList<SenSwitch>();
		
		for(ModData modData :listModData) {			
			//查詢感應裝置電源開關列表，依據更新日期排序
			listSwitch = senSwitchDao.findSwitchOrderData(modData);			
			//若有資料則放入
			if(!CollectionUtils.isEmpty(listSwitch)) {
				//取最新一筆放入
				results.add(listSwitch.get(0));
			}
		}
		return results;
	}		
}
