package com.springmvc.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.modle.service.BaseServiceImpl;
import com.modle.util.ObjectMapperUtils;
import com.springmvc.dao.ModMainDao;
import com.springmvc.dto.ModMainDto;
import com.springmvc.dto.SenDht11Dto;
import com.springmvc.entity.ModMain;
import com.springmvc.entity.ModSen;

/**
 * 感應裝置主檔Service實作
 * 
 * @author hrne
 *
 */
@Service("modMainService")
public class ModMainServiceImpl extends BaseServiceImpl<ModMain> implements ModMainService {

	@Autowired
	private ModSenService modSenService;

	@Autowired
	private ModRespLogService modRespLogService;

	@Autowired
	private SenDht11Service senDht11Service;

	@Autowired
	private SenHx711Service senHx711Service;

	@Autowired
	private SenSwitchService senSwitchService;

	@Autowired
	private SenFireAlmService senFireAlmService;

	@Autowired
	private ModMainDao modMainDao;

	public List<ModMain> find_modEnabled() {
		return modMainDao.find_modEnabled();
	}

	public void save_modMainDto(ModMainDto modMainDto) {

		ModMain modMain = new ModMain();

		// 暫存感應模組
		Set<ModSen> modSenSet = new HashSet<ModSen>();

		for (Integer modSenId : modMainDto.getModSenIdList()) {
			// 依據id查詢感應模組
			ModSen modSen = modSenService.findByPK(modSenId);
			// 將資料感應模組資料放入暫存
			modSenSet.add(modSen);
		}

		// 將dto資料轉換回entity
		modMain = ObjectMapperUtils.map(modMainDto, ModMain.class);

		// 將暫存感應模組放入
		modMain.setModSenSet(modSenSet);

		// 寫入DB
		saveOrUpdate(modMain);
	}

	public void scan_MainMod() {

		//System.out.println("start scan");

		// 查詢所有啟用感應裝置
		List<ModMain> scan_modMainList = find_modEnabled();

		// 掃描每一台感應裝置
		for (ModMain modMain : scan_modMainList) {
			// 連線Arduino
			String respJson = getArduinoData(modMain);

			if (respJson != null) {
				for (ModSen modSen : modMain.getModSenSet()) {
					// 將讀取的資料依不同感應模組寫入
					switch (modSen.getSenCode()) {
					case "dht11":
						// 儲存溫濕度dht11感應資料資料
						senDht11Service.save_respJson(modMain, modSen, respJson);
						break;
					case "hx711":
						// 儲存重量感應hx711資料
						senHx711Service.save_respJson(modMain, modSen, respJson);
						break;
					case "switch":
						// 電源開關感應資料
						senSwitchService.save_respJson(modMain, modSen, respJson);
						break;
					case "fireAlm":
						// 火災警報感應資料
						senFireAlmService.save_respJson(modMain, modSen, respJson);
						break;
					}
				}
			}
		}
	}

	/**
	 * 連線Arduino讀取資料
	 * 
	 * @param modMain 要掃描感應裝置
	 * @return Json格式資料
	 */
	public String getArduinoData(ModMain modMain) {

		// 設定連線
		CloseableHttpClient httpCilent = HttpClients.createDefault();

		RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(5000)
				.setConnectionRequestTimeout(5000).setSocketTimeout(5000).setRedirectsEnabled(true).build();

		// 設定網址
		HttpPost httpPost = new HttpPost("http://" + modMain.getIpAddress() + "/sensor");

		httpPost.setConfig(requestConfig);

		String respJsonStr = null;
		try {

			// 設定要查詢的感應模組
			StringEntity entity = new StringEntity(getSenJson(modMain));
			httpPost.setEntity(entity);

			// 讀取Arduino資料
			HttpResponse httpResponse = httpCilent.execute(httpPost);
			int statusCode = httpResponse.getStatusLine().getStatusCode();

			// 接受Arduino回傳資料
			// status code:200 代表成功
			if (statusCode == 200) {
				// 轉換格式Json
				respJsonStr = EntityUtils.toString(httpResponse.getEntity());
			} else {
				// 失敗:寫入錯誤訊息，01:感應裝置連線失敗
				modRespLogService.save_modData_statusCode(modMain, "01", String.valueOf(statusCode));
			}

			//System.out.println("status code:    " + statusCode + "   content:   " + respJsonStr);

		} catch (IOException e) {
			// 連線意外失敗:紀錄錯誤訊息，99:未知原因
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			modRespLogService.save_modData_statusCode(modMain, "99", "連線意外失敗:" + errors.toString());
		} finally {
			try {
				httpCilent.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return respJsonStr;
	}

	/**
	 * 將感應裝置使用的感應模組轉換成json格式
	 * 
	 * @return Json String
	 */
	public String getSenJson(ModMain modMain) {

		JSONObject obj = new JSONObject();

		for (ModSen modSen : modMain.getModSenSet()) {
			// 將要讀取資料的感應模組設為1
			obj.put(modSen.getSenCode(), "1");
		}

		//System.out.println(obj.toString());
		return obj.toString();
	}

//	public List<ModMainDto> find_chart_init() {
//
//		// 查詢所有啟用感應裝置
//		List<ModMain> modMainList = find_modEnabled();
//
//		List<ModMainDto> modMainDtoList = new ArrayList<ModMainDto>();
//
//		for (ModMain modMain : modMainList) {
//
//			ModMainDto modMainDto = ObjectMapperUtils.map(modMain, ModMainDto.class);
//
//			List<SenDht11Dto> senDht11Dto = senDht11Service.find_show_chart(modMain.getId());
//			
//			//modMainDto.setDht11Json(senDht11Dto);
//
//			modMainDtoList.add(modMainDto);
//
//		}
//		return modMainDtoList;
//
//	}

}
