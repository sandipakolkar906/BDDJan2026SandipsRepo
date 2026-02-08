package com.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Admin
 *  This class has methods to read the properties from congig.prop file
 *
 */


public class ReadProperties {


	/**
	 * @return this method will laod the properties and return the object of properties class
	 */
	public static Properties loadProperties() {

		// Open the File
		File objfile = new File("AppConfig/config.properties");
		// Read the File

		FileInputStream objfis = null;
		try {
			objfis = new FileInputStream(objfile);
		} catch (FileNotFoundException e1) {

			e1.printStackTrace();
		}

		Properties objprop = new Properties();
		try {
			objprop.load(objfis);
		} catch (IOException e) {

			e.printStackTrace();
		}
		return objprop;

	}
	
	
	/**
	 * @return this method will read app url from config file and return app url
	 */
	public static String getappURL() {

		return loadProperties().getProperty("appURL");

	}

	/**
	 * @return
	 */
	public static long implicitWaitTime() {

		return Long.parseLong(loadProperties().getProperty("implicitWaitTime"));
	}

	/**
	 * @return
	 */
	public static String getAppUserName() {
		return loadProperties().getProperty("userName");
	}

	/**
	 * @return
	 */
	public static String getAppPassword() {
		return loadProperties().getProperty("password");
	}

	/**
	 * @return
	 */
	public static long getFluentWaitTime() {

		return Long.parseLong(loadProperties().getProperty("fluentWaitTime"));
	}
	
}
