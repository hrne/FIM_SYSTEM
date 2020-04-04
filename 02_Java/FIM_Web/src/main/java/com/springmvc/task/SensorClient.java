package com.springmvc.task;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.modle.util.ApplicationContextUtil;
import com.springmvc.dao.SenMachDao;
import com.springmvc.entity.SenMach;
import com.springmvc.service.SenMachService;

/**
 * 開始掃描工具機
 * 
 * @author hrne
 *
 */
@Component
public class SensorClient {
	
	SenMachService senMachService;

	@Scheduled(cron = "0/5 * * * * ? ")
	public void startClient() {

		System.out.println("start");

		senMachService = (SenMachService) ApplicationContextUtil.getBean("senMachService");
		
		List<SenMach> dtoList = senMachService.findByMachEnable();
		
		for(SenMach dto : dtoList ) {
			System.out.println(dto.getId());
		}

	}

	public void getHttpClient() {

		CloseableHttpClient httpCilent = HttpClients.createDefault();

		RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(5000)
				.setConnectionRequestTimeout(5000).setSocketTimeout(5000).setRedirectsEnabled(true).build();
		HttpGet httpGet = new HttpGet("http://192.168.50.102/send");
		httpGet.setConfig(requestConfig);
		try {

			HttpResponse httpResponse = httpCilent.execute(httpGet);

			System.out.println("status code:    " + httpResponse.getStatusLine().getStatusCode() + "   content:   "
					+ EntityUtils.toString(httpResponse.getEntity()));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				httpCilent.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
