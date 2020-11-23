package com.springmvc.task;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.modle.util.ApplicationContextUtil;
import com.springmvc.entity.ModMain;
import com.springmvc.entity.ModSen;
import com.springmvc.entity.SysExpRecord;
import com.springmvc.service.SenDht11Service;
import com.springmvc.service.SenHx711Service;
import com.springmvc.service.SenSwitchService;
import com.springmvc.service.SysExpRecordService;
import com.springmvc.service.ModMainService;
import com.springmvc.service.ModRespLogService;

/**
 * 掃描感應裝置
 * 
 * @author hrne
 *
 */
@Component
public class SensorClient {

	// 感應裝置主檔service
	ModMainService modMainService;

	SysExpRecordService sysExpRecordService;

	// 每5秒掃描一次
	@Scheduled(cron = "0/1 * * * * ? ")
	public void startClient() {
		System.out.println("Start Scn");
		modMainService = (ModMainService) ApplicationContextUtil.getBean("modMainService");
		modMainService.scan_MainMod();
	}

	// 每5秒掃描一次
	//@Scheduled(cron = "0/10 * * * * ? ")
	public void startClientTest() {

		sysExpRecordService = (SysExpRecordService) ApplicationContextUtil.getBean("sysExpRecordService");

		// 讀取目前實驗次數
		SysExpRecord expRecord = sysExpRecordService.findByPK(1);
		BigDecimal serNumber = expRecord.getSerNumber();
		BigDecimal limit = new BigDecimal(100);

		// 設定實驗次數
		if (serNumber.compareTo(limit) <= 0) {
			System.out.println("Start Scn");
			// 開始時間
			Date start_date = new Date();

			modMainService = (ModMainService) ApplicationContextUtil.getBean("modMainService");

			modMainService.scan_MainMod();

			// 結束時間
			Date end_date = new Date();

			// 經過時間
			BigDecimal costTime = new BigDecimal(end_date.getTime() - start_date.getTime());

			// 紀錄實驗結果
			SysExpRecord sysExpRecord = new SysExpRecord();
			sysExpRecord.setExpContent("實驗50台100次");
			sysExpRecord.setSerNumber(serNumber);
			sysExpRecord.setCostTime(costTime);
			sysExpRecordService.create(sysExpRecord);
			System.out.println("實驗次數:"+serNumber+"/"+limit +"。  經過時間: " + String.valueOf(sysExpRecord.getCostTime()));

			// 回寫實驗次數
			expRecord.setSerNumber(expRecord.getSerNumber().add(new BigDecimal(1)));
			sysExpRecordService.saveOrUpdate(expRecord);

		}

	}

}
