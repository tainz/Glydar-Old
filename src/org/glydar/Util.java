package org.glydar;

import java.util.ArrayList;
import java.util.List;

public final class Util
{
	
	public static int fromByteArray(byte[] bytes)
	{
		return bytes[0] << 24 | (bytes[1] & 0xFF) << 16 | (bytes[2] & 0xFF) << 8 | (bytes[3] & 0xFF);
	}
	
	public static String byteArrayToString(byte[] bA)
	{
		StringBuilder sw = new StringBuilder();
		for (byte b : bA)
		{
			sw.insert(0, String.format("%02X", b));
		}
		return sw.toString();
	}
	
	public static byte[] hexStringToByteArray(String s)
	{
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2)
		{
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
		}
		return data;
	}
	
	public static String createZeros(String s, int length)
	{
		StringBuilder b = new StringBuilder();
		for (int n = 0; n < (length - (s.length())); n++)
		{
			b.append("0");
		}
		return b.toString();
	}
	
	public static List<Byte> toByteList(byte[] arr)
	{
		
		List<Byte> byteList = new ArrayList<Byte>();
		
		for (byte b : arr)
		{
			byteList.add(new Byte(b));
		}
		
		return byteList;
		
	}
	
}
