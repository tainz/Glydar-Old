package org.glydar.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class LogUtil {
	public void output(String message) {
		String mes = message;
		String time = "";
		
		Calendar cal = Calendar.getInstance();
    	cal.getTime();
    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    	time = sdf.format(cal.getTime());
    	
    	System.out.println("[" + time + "][GLYDAR] " + mes);
	}
}
