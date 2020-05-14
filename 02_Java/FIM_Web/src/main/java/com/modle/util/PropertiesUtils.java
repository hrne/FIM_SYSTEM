package com.modle.util;

import java.io.IOException;
import java.util.Properties;

/**
 * 用來讀取property相關文件
 * @author hrne
 *
 */
public class PropertiesUtils {

	static Properties property = new Properties();

	public static boolean loadFile(String fileName) {
		try {
			//讀取resources底下properties
			property.load(PropertiesUtils.class.getClassLoader().getResourceAsStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static String getPropertyValue(String key) {
		return property.getProperty(key);
	}
}
