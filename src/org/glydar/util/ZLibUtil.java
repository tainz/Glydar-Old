package org.glydar.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class ZLibUtil {
	
	public static byte[] compress(byte[] data) throws IOException {
		
		Deflater def = new Deflater();
		def.setInput(data);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream(data.length);
		
		def.finish();
		
		byte[] buffer = new byte[1024];
		
		while (!def.finished()) {
			
			int compressed = def.deflate(buffer);
			
			baos.write(buffer, 0, compressed);
			
		}
		
		baos.close();
		
		return baos.toByteArray();
		
	}
	
	public static byte[] decompress(byte[] data) throws DataFormatException, IOException {
		
		if (data.length == 0)
			return null;
		
		//System.out.println("START DECOMPRESS");
		
		Inflater inf = new Inflater();
		inf.setInput(data);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream(data.length);
		
		byte[] buffer = new byte[1024];
		
		while (!inf.finished()) {
			
			int decompressed = inf.inflate(buffer);
			
			baos.write(buffer, 0, decompressed);
			
		}
		
		inf.end();
		baos.close();
		
		//System.out.println("FINISHED DECOMPRESS");
		
		return baos.toByteArray();
		
	}
	
}
