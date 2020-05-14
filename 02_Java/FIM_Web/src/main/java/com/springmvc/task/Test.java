package com.springmvc.task;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class Test {

	public void testMethod() {
		System.out.println("show method 1");
	}

	public void getHttpClient() {

		CloseableHttpClient httpCilent = HttpClients.createDefault();

		RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(5000)
				.setConnectionRequestTimeout(5000).setSocketTimeout(5000).setRedirectsEnabled(true).build();
		// URL�O "http://localhost/NewSys/login.aspx"
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
