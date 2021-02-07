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
import com.springmvc.dao.SenSwitchDao;
import com.springmvc.entity.ModMain;
import com.springmvc.entity.ModSen;
import com.springmvc.entity.SenSwitch;

/**
 * 電源開關感應資料Service實作
 * 
 * @author hrne
 *
 */
@Service("senSwitchService")
public class SenSwitchServiceImpl extends BaseServiceImpl<SenSwitch> implements SenSwitchService {

	@Autowired
	private ModMainService modMainService;

	@Autowired
	private ModSenService modSenService;

	@Autowired
	private ModRespLogService modRespLogService;

	@Autowired
	private SenSwitchDao senSwitchDao;

	public void save_respJson(ModMain modMain, ModSen modSen, String respJson) {

		// 將回傳資料轉成json
		JSONObject obj = new JSONObject(respJson);

		try {
			// 取出電源開關狀態
			Integer powStatus = obj.getInt("pow_status");
			// 取出電池電力(v)
			BigDecimal batteryVolt = obj.getBigDecimal("battery_volt");

			// 產生資料
			SenSwitch senSwitch = new SenSwitch();
			senSwitch.setModMain(modMain);
			if (powStatus == 1) {
				senSwitch.setPowStatus(true);
			} else {
				senSwitch.setPowStatus(false);
			}
			senSwitch.setBatteryVolt(batteryVolt);

			// 將資料寫入DB
			create(senSwitch);

			// 儲存成功紀錄，00:連線正常
			modRespLogService.save_modData_modSen(modMain, modSen, "00", obj.toString());

		} catch (Exception e) {
			// 回傳資料若其中有一筆空值，則不寫入並儲存錯誤訊息紀錄，02:讀取不到感應模組資料
			modRespLogService.save_modData_modSen(modMain, modSen, "02", obj.toString() + e.toString());
		}
	}

	public List<SenSwitch> find_latest_modMain() {

		// 查詢所有啟用的感應裝置主檔
		List<ModMain> modMainList = modMainService.find_modEnabled();

		List<SenSwitch> senSwitchList = new ArrayList<SenSwitch>();

		List<SenSwitch> results = new ArrayList<SenSwitch>();

		for (ModMain modMain : modMainList) {

			// 判斷感應裝置是否有設定電源控制感應模組
			for (ModSen modSen : modMain.getModSenSet()) {
				if (modSen.getSenCode().equals("switch")) {
					// 查詢感應裝置電源開關列表，依據更新日期排序
					senSwitchList = senSwitchDao.find_modMainId_desc(modMain.getId());
					// 判斷是否有資料
					if (!CollectionUtils.isEmpty(senSwitchList)) {
						// 取最新一筆放入
						results.add(senSwitchList.get(0));
					}
				}
			}
		}
		return results;
	}

	public boolean turn_senSwitchId(Integer modMainId, boolean state) {

		// 紀錄是否成功
		boolean returnStatus = false;

		// 查詢感應裝置主檔
		ModMain modMain = modMainService.findByPK(modMainId);

		// 開始傳送資料
		CloseableHttpClient httpCilent = HttpClients.createDefault();

		RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(5000)
				.setConnectionRequestTimeout(5000).setSocketTimeout(5000).setRedirectsEnabled(true).build();

		// 找出對應ip
		HttpPost httpPost = new HttpPost("http://" + modMain.getIpAddress() + "/sensor");
		httpPost.setConfig(requestConfig);

		// 讀取電源開關感應模組
		ModSen modSen = modSenService.find_senCode("switch");

		try {
			JSONObject obj = new JSONObject();

			// 如果現行的狀態是0關閉，則打開，反之則關閉。
			if (!state) {
				obj.put("pow_enabled", "1");
			} else {
				obj.put("pow_enabled", "0");
			}

			StringEntity entity = new StringEntity(obj.toString());
			httpPost.setEntity(entity);

			// 傳送Arduino資料
			HttpResponse httpResponse = httpCilent.execute(httpPost);

			// 回傳值
			int statusCode = httpResponse.getStatusLine().getStatusCode();

			// 接受Arduino回傳資料
			// status code:200 代表成功
			if (statusCode == 200) {
				// 取得回傳值
				String respJsonStr = EntityUtils.toString(httpResponse.getEntity());

				// 將回傳資料轉成json
				JSONObject respJson = new JSONObject(respJsonStr);
				// 取出電池電力(v)
				boolean respPowerStatus = respJson.getBoolean("resp_power_status");
				returnStatus = respPowerStatus;
				if (respPowerStatus) {
					// 儲存成功紀錄，00:連線正常
					modRespLogService.save_modData_modSen(modMain, modSen, "00", "開關:" + state);
					
					// 產生資料
					SenSwitch senSwitch = new SenSwitch();
					senSwitch.setModMain(modMain);
					senSwitch.setPowStatus(!state);
					BigDecimal batteryVolt = new BigDecimal(6);
					senSwitch.setBatteryVolt(batteryVolt);

					// 將資料寫入DB
					create(senSwitch);
					
				} else {
					// 回傳資料若其中有一筆空值，則不寫入並儲存錯誤訊息紀錄，01:感應裝置連線失敗
					modRespLogService.save_modData_modSen(modMain, modSen, "01", String.valueOf(statusCode));
				}
			} else {
				returnStatus = false;
				// 回傳資料若其中有一筆空值，則不寫入並儲存錯誤訊息紀錄，01:感應裝置連線失敗
				modRespLogService.save_modData_modSen(modMain, modSen, "01", String.valueOf(statusCode));
			}
		} catch (IOException e) {
			// 連線意外失敗:紀錄錯誤訊息
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			modRespLogService.save_modData_modSen(modMain, modSen, "99", "連線意外失敗:" + errors.toString());
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
