package com.qa.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Admin
 *
 */
public class DateUtils {
	
	/**
	 * @return
	 * 
	 */
	public static String getFormattedDate() {
	    return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}

}
