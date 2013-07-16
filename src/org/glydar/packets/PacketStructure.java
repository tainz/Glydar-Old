package org.glydar.packets;

import java.nio.ByteBuffer;
import java.util.ArrayList;

public class PacketStructure
{
	
	private static final int IntegerSize = 4;
	private static final int FloatSize = 4;
	private static final int LongSize = 8;
	
	private ArrayList<Class<?>> dataTypes;
	
	public PacketStructure()
	{
		dataTypes = new ArrayList<Class<?>>();
	}
	
	public void addDataType(Class<?> dataType)
	{
		dataTypes.add(dataType);
	}
	
	public boolean matchesStructureLength(byte[] data)
	{
		
		int len = 0;
		
		for (Class<?> clazz : dataTypes)
		{
			
			if (clazz.isAssignableFrom(Float.class))
			{
				len += FloatSize;
			}
			else if (clazz.isAssignableFrom(Long.class))
			{
				len += LongSize;
			}
			else if (clazz.isAssignableFrom(Integer.class))
			{
				len += IntegerSize;
			}
			else if (clazz.isAssignableFrom(String.class))
			{
				
				ByteBuffer buf = ByteBuffer.wrap(data);
				
				int size = buf.getInt();
				
				len += size;
				
			}
			
		}
		
		return data.length == len;
		
	}
	
}
