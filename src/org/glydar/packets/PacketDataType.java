package org.glydar.packets;

public class PacketDataType {
	
	public static final int IntegerSize = 4;
	public static final int FloatSize = 4;
	public static final int LongSize = 8;
	public static final int ByteSize = 1;
	
	private Class<?> dataType;
	
	private int length;
	
	private boolean dynamicLength;
	
	public PacketDataType(Class<?> dataType) {
		this.dataType = dataType;
		this.length = determinePacketLength(dataType);
		this.dynamicLength = false;
	}
	
	public PacketDataType(Class<?> dataType, int len) {
		this.dataType = dataType;
		this.length = len;
		this.dynamicLength = false;
	}
	
	public PacketDataType(Class<?> dataType, boolean isDynamic) {
		this.dataType = dataType;
		this.length = 0;
		this.dynamicLength = isDynamic;
	}
	
	private static int determinePacketLength(Class<?> clazz) {
		
		int len = 0;
		
		if (clazz.isAssignableFrom(Float.class)) {
			len += FloatSize;
		}
		else if (clazz.isAssignableFrom(Long.class)) {
			len += LongSize;
		}
		else if (clazz.isAssignableFrom(Integer.class)) {
			len += IntegerSize;
		}
		else if (clazz.isAssignableFrom(Byte.class)) {
			len += ByteSize;
		}
		
		return len;
		
	}
	
	public Class<?> getDataType() {
		return dataType;
	}
	
	public int getLength() {
		return length;
	}
	
	public boolean isDynamicLength() {
		return dynamicLength;
	}
	
	public void setLength(int newLength) {
		length = newLength;
	}
	
	public void setDynamicLength(boolean isDynamic) {
		dynamicLength = isDynamic;
	}
	
}
