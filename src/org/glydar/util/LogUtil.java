package org.glydar.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class LogUtil {
	public void output(String message) {
		String mes = message;
		String time = "";
		String name = "";

		Calendar cal = Calendar.getInstance();
		cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		time = sdf.format(cal.getTime());

		if (name == "") {
			System.out.println("[" + time + "][Glydar] " + mes);

		} else {
			System.out.println("[" + time + "][" + name + "] " + mes);
		}
	}
}
