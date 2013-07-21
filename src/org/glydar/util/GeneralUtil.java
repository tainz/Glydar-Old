package org.glydar.util;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Random;

/**
 * General methods 
 */
public class GeneralUtil {
	public void download(String URL, String Filename) {
	    try {
		    URL website = new URL(URL);
		    ReadableByteChannel rbc = Channels.newChannel(website.openStream());
		    FileOutputStream fos = new FileOutputStream(Filename);
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int randomInt(int max) {		
		Random rand = new Random();

		int number = rand.nextInt(max + 1);
		
		return number;
	}
	
	public String convertIntToString(int integer, String string) {
		String stringe = Integer.toString(integer);
		
		return stringe;
	}
	
	public int convertStringtoInt(String string, int integer) {
		int inte = Integer.parseInt(string);
		
		return inte;
	}
}
