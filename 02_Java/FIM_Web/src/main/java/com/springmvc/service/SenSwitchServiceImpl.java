package com.springmvc.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.modle.service.BaseServiceImpl;
import com.springmvc.dao.ModMainDao;
import com.springmvc.dao.ModSenDao;
import com.springmvc.dao.SenDht11Dao;
import com.springmvc.dao.SenSwitchDao;
import com.springmvc.entity.ModMain;
import com.springmvc.entity.ModSen;
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
	private ModRespLogService modRespLogService;

	@Autowired
	private ModMainDao modDataDao;

	@Autowired
	private ModSenDao modSenDao;

	@Autowired
	private SenSwitchDao senSwitchDao;

	public void createSwitch(ModMain modData, ModSen modSen, String respJSON) {

		// 將回傳資料轉成json
		JSONObject obj = new JSONObject(respJSON);

		try {
			// 取出電源開關狀態
			Integer powStatus = obj.getInt("pow_status");
			// 取出電池電力(v)
			BigDecimal batteryVolt = obj.getBigDecimal("battery_volt");

			SenSwitch senSwitch = new SenSwitch();
			// 寫入感應裝置
			senSwitch.setModData(modData);
			senSwitch.setPowStatus(powStatus);
			senSwitch.setBatteryVolt(batteryVolt);
			
			// 儲存資料
			create(senSwitch);
			//儲存成功紀錄
			modRespLogService.createRespLogByModSen(modData, modSen, "00", obj.toString());
		} catch (Exception e) {
			// 回傳資料若其中有一筆空值，則不寫入並寫入錯誤訊息紀錄
			modRespLogService.createRespLogByModSen(modData, modSen, "02", obj.toString());
		}
	}

	public List<SenSwitch> findLatestSwitchData() {

		// 查詢所有啟用的感應裝置
		List<ModMain> listModData = modDataDao.findByModEnable();

		List<SenSwitch> listSwitch = new ArrayList<SenSwitch>();

		List<SenSwitch> results = new ArrayList<SenSwitch>();

		for (ModMain modData : listModData) {
			// 查詢感應裝置電源開關列表，依據更新日期排序
			listSwitch = senSwitchDao.findSwitchOrderData(modData);
			// 若有資料則放入
			if (!CollectionUtils.isEmpty(listSwitch)) {
				// 取最新一筆放入
				results.add(listSwitch.get(0));
			}
		}
		return results;
	}

	public boolean turnSwitch(SenSwitch senSwitch) {

		boolean returnStatus = false;

		// 查詢感應裝置
		ModMain modData = senSwitch.getModData();

		CloseableHttpClient httpCilent = HttpClients.createDefault();

		RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(5000)
				.setConnectionRequestTimeout(5000).setSocketTimeout(5000).setRedirectsEnabled(true).build();

		// 傳送ip+感應裝置代號
		HttpPost httpPost = new HttpPost("http://" + modData.getIpAddress() + "/sensor");

		httpPost.setConfig(requestConfig);

		// 讀取電源開關感應模組
		ModSen modSen = modSenDao.findBySenCode("switch");

		try {
			// json:開源開關
			JSONObject obj = new JSONObject();
			// 如果現行的狀態是0關閉，則打開，反之則關閉。
			if (senSwitch.getPowStatus() == 0) {
				obj.put("pow_enabled", 1);
			} else {
				obj.put("pow_enabled", 0);
			}

			// 設定開關
			StringEntity entity = new StringEntity(obj.toString());
			httpPost.setEntity(entity);

			// 讀取Arduino資料
			HttpResponse httpResponse = httpCilent.execute(httpPost);

			int statusCode = httpResponse.getStatusLine().getStatusCode();

			// 接受感應裝置回傳資料
			// status code:200 代表成功
			if (statusCode == 200) {
				returnStatus = true;
				// 成功:訊息紀錄收到資料
				modRespLogService.createRespLogByModSen(modData, modSen, "00", "開關:" + senSwitch.getPowStatus());
			} else {
				returnStatus = false;
				// 失敗:訊息紀錄錯誤代碼
				modRespLogService.createRespLogByModSen(modData, modSen, "02", String.valueOf(statusCode));
			}
		} catch (IOException e) {
			// 連線意外失敗:紀錄錯誤訊息
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			modRespLogService.createRespLogByModSen(modData, modSen, "99", "連線意外失敗:" + errors.toString());
		} finally {
			try {
				httpCilent.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return returnStatus;
	}
}
