package org.glydar.packets;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.glydar.Util;


public class PacketData
{
	
	private List<Byte> data;
	
	public PacketData()
	{
		data = new ArrayList<Byte>();
	}
	
	public PacketData(byte[] dat)
	{
		data = Util.toByteList(dat);
	}
	
	public <T> void addData(T dat)
	{
		
		if (dat instanceof Integer)
		{
			data.addAll(Util.toByteList(ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt((Integer) dat).array()));
		}
		else if (dat instanceof Float)
		{
			data.addAll(Util.toByteList(ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putFloat((Float) dat).array()));
		}
		else if (dat instanceof String)
		{
			
			byte[] dt = ((String) dat).getBytes(Charset.forName("UTF-16LE"));
			
			data.addAll(Util.toByteList(ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(dt.length / 2).array()));
			data.addAll(Util.toByteList(dt));
			
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getDataAtIndex(Class<T> dataType, int index)
	{
		
		if (dataType.isAssignableFrom(Integer.class))
		{
			
			byte[] dt = getPrimByteData(data, index, 4);
			
			return (T) new Integer(ByteBuffer.wrap(dt).order(ByteOrder.LITTLE_ENDIAN).getInt());
			
		}
		else if (dataType.isAssignableFrom(Float.class))
		{
			
			byte[] dt = getPrimByteData(data, index, 4);
			
			return (T) new Double(ByteBuffer.wrap(dt).order(ByteOrder.LITTLE_ENDIAN).getFloat());
			
		}
		else if (dataType.isAssignableFrom(String.class))
		{
			
			byte[] sizeDt = getPrimByteData(data, 0, 4);
			
			int size = ByteBuffer.wrap(sizeDt).order(ByteOrder.LITTLE_ENDIAN).getInt();
			
			byte[] dt = getPrimByteData(data, 4, size);
			
			return (T) new String(dt);
			
		}
		
		return null;
		
	}
	
	public byte[] getByteData()
	{
		
		byte[] bData = new byte[data.size()];
		
		for (int i = 0; i < data.size(); i++)
		{
			bData[i] = data.get(i).byteValue();
		}
		
		return bData;
		
	}
	
	private static byte[] getPrimByteData(List<Byte> data, int index, int size)
	{
		
		byte[] dt = new byte[size];
		
		int ctr = 0;
		for (int i = index; i < size; i++)
		{
			
			dt[ctr] = data.get(i).byteValue();
			
			ctr++;
			
		}
		
		return dt;
		
	}
	
}
