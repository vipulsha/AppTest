package com.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Utility class to perform data read operations for test & config data 
 */
public class DataReader {
	
	private static Properties configProp;
	
	private static Properties getConfigPropertiesObject() {
		if (configProp == null) {
			configProp = new Properties();
			try {
				configProp.load(new FileInputStream(new File(System.getProperty("user.dir")+"\\src\\test\\resources\\Config\\Config.properties")));
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		} 
		return configProp;
	}
	
	public static String getConfigData(String key) {
		return getConfigPropertiesObject().get(key).toString().trim();
	}
	
	public static Properties getTestDataPropertiesObject(String fileName) {
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(new File(System.getProperty("user.dir")+"\\src\\test\\resources\\DataRepository\\"+fileName+".properties")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}
}