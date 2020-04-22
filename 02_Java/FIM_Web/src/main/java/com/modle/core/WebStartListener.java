package com.modle.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



@WebListener
public class WebStartListener implements ServletContextListener {

	public static Properties settings = new Properties();

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Override
	public void contextInitialized(ServletContextEvent arg0) {

		String settingFilePath = 
				new StringBuilder()
				.append(arg0.getServletContext().getRealPath("/"))
				.append("WEB-INF").append(File.separator)
				.append("classes").append(File.separator)
				.toString();

		String onlineSettings = settingFilePath + "settings.properties";
		
		logger.info(onlineSettings);

		try (FileInputStream fis = new FileInputStream(onlineSettings);) {
			settings.load(fis);
		} catch (IOException e) {
			logger.error("Can't get correct ENV setting file!!", e);
			System.exit(0);
		}
		
		arg0.getServletContext().setAttribute("settings", settings);

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		

	}

}
