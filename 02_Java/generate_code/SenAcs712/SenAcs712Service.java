package com.springmvc.service;

import java.util.List;

import com.modle.service.BaseService;
import com.springmvc.entity.SenDht11;
import com.springmvc.entity.ModData;

/**
 * 溫濕度dht11感應資料的Service介面
 * 
 * @author hrne
 *
 */
public interface SenDht11Service extends BaseService<SenDht11>{

	/**
	 * 儲存溫濕度dht11感應資料
	 * 
	 * @param senMach 感應裝置
	 * @param respJSON 回傳JSON
	 */
	void createDht11(ModData senMach, String respJSON);		
		
	/**
	 * 查詢每個啟用的感應裝置最新一筆溫濕度資料
	 * 
	 * @return SenDht11 list 溫濕度列表
	 */
	List<SenDht11> findLatestDht11Data();

}