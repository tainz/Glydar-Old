package org.glydar.util;

import java.util.ArrayList;
import java.util.List;

public final class MiscUtil {
	
	public static List<Byte> toByteList(byte[] arr) {
		
		List<Byte> byteList = new ArrayList<Byte>();
		
		for (byte b : arr) {
			byteList.add(new Byte(b));
		}
		
		return byteList;
		
	}
	
	public static byte[] getEmptyByteArray(int size) {
		
		byte[] empty = new byte[size];
		
		for (int i = 0; i < size; i++) {
			empty[i] = 0;
		}
		
		return empty;
		
	}
	
}
