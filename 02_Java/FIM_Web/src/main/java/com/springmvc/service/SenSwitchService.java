package com.springmvc.service;

import java.util.List;

import com.modle.service.BaseService;
import com.springmvc.entity.ModData;
import com.springmvc.entity.SenDht11;
import com.springmvc.entity.SenSwitch;

/**
 * 電源開關感應資料的Service介面
 * 
 * @author hrne
 *
 */
public interface SenSwitchService extends BaseService<SenSwitch>{

	/**
	 * 儲存電源開關感應資料
	 * 
	 * @param senMach 感應裝置
	 * @param respJSON 回傳JSON
	 */
	void createSwitch(ModData senMach, String respJSON);		
		
	/**
	 * 查詢每個啟用的感應裝置最新一筆電源開關
	 * 
	 * @return SenSwitch list 電源開關列表
	 */
	List<SenSwitch> findLatestSwitchData();

}