package com.springmvc.service;

import java.util.List;

import com.modle.service.BaseService;
import com.springmvc.entity.ModMain;
import com.springmvc.entity.ModSen;
import com.springmvc.entity.SenSwitch;

/**
 * 電源開關感應資料Service介面
 * 
 * @author hrne
 *
 */
public interface SenSwitchService extends BaseService<SenSwitch> {

	/**
	 * 依據回傳Json儲存資料，若資料有錯誤則紀錄log不儲存
	 * 
	 * @param modMain  感應裝置主檔
	 * @param modSen   感應模組
	 * @param respJson 回傳Json
	 */
	void save_respJson(ModMain modMain, ModSen modSen, String respJson);

	/**
	 * 依據啟用感應裝置主檔查詢最新的電源開關資料
	 * 
	 * @return SenSwitch list
	 */
	List<SenSwitch> find_latest_modMain();

	/**
	 * 依據感應裝置id傳送訊號給Arduino開關電源
	 * 
	 * @param modMainId 感應裝置id
	 * @param state 現行開關訊號
	 * @return 是否成功
	 */
	boolean turn_senSwitchId(Integer modMainId, boolean state);

}