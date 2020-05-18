package com.springmvc.task;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
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
import com.springmvc.service.SenDht11Service;
import com.springmvc.service.SenHx711Service;
import com.springmvc.service.SenSwitchService;
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

	// 每5秒掃描一次
	@Scheduled(cron = "0/1 * * * * ? ")
	public void startClient() {
		System.out.println("Start Scn");
		modMainService = (ModMainService) ApplicationContextUtil.getBean("modMainService");
		modMainService.scan_MainMod();
		
	}

}
