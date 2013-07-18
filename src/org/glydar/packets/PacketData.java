package org.glydar.packets;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.glydar.util.Util;

public abstract class PacketData {
	
	protected List<Byte> data;
	
	protected PacketData() {
		data = new ArrayList<Byte>();
	}
	
	protected PacketData(byte[] dat) {
		data = Util.toByteList(dat);
	}
	
	public <T> PacketData addData(T dat) {
		
		data.addAll(getDataList(dat));
		
		return this;
		
	}
	
	public <T> List<Byte> getDataList(T dat) {
		
		List<Byte> tmpDat = new ArrayList<Byte>();
		
		if (dat instanceof Float) {
			tmpDat.addAll(Util.toByteList(ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putFloat((Float) dat).array()));
		}
		else if (dat instanceof Long) {
			tmpDat.addAll(Util.toByteList(ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN).putLong((Long) dat).array()));
		}
		if (dat instanceof Integer) {
			tmpDat.addAll(Util.toByteList(ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt((Integer) dat).array()));
		}
		else if (dat instanceof String) {
			
			byte[] dt = ((String) dat).getBytes(Charset.forName("UTF-16LE"));
			
			tmpDat.addAll(Util.toByteList(ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(dt.length / 2).array()));
			tmpDat.addAll(Util.toByteList(dt));
			
		}
		else if (dat instanceof byte[]) {
			tmpDat.addAll(Util.toByteList((byte[]) dat));
		}
		else if (dat instanceof Byte) {
			tmpDat.add((Byte) dat);
		}
		
		return tmpDat;
		
	}
	
	public void setDataAtIndex(int index, byte[] dat) {
		
		for (int i = 0; i < dat.length; i++) {
			data.set(index, dat[i]);
		}
		
	}
	
	public <T> void setDataAtIndex(int index, T dat) {
		
		List<Byte> tmp = getDataList(dat);
		
		for (int i = 0; i < tmp.size(); i++) {
			data.set(index + i, tmp.get(i));
		}
		
	}
	
	public byte[] getDataAtIndex(int index, int len) throws Exception {
		
		byte[] dat = new byte[len];
		
		if (dat.length > data.size()) {
			throw new Exception("Out of bounds!");
		}
		
		for (int i = index; i < len; i++) {
			dat[i] = data.get(i);
		}
		
		return dat;
		
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getDataAtIndex(Class<T> dataType, int index) {
		
		if (dataType.isAssignableFrom(Float.class)) {
			
			byte[] dt = getPrimByteData(data, index, 4);
			
			return (T) new Double(ByteBuffer.wrap(dt).order(ByteOrder.LITTLE_ENDIAN).getFloat());
			
		}
		else if (dataType.isAssignableFrom(Long.class)) {
			
			byte[] dt = getPrimByteData(data, index, 8);
			
			return (T) new Long(ByteBuffer.wrap(dt).order(ByteOrder.LITTLE_ENDIAN).getLong());
			
		}
		if (dataType.isAssignableFrom(Integer.class)) {
			
			byte[] dt = getPrimByteData(data, index, 4);
			
			return (T) new Integer(ByteBuffer.wrap(dt).order(ByteOrder.LITTLE_ENDIAN).getInt());
			
		}
		else if (dataType.isAssignableFrom(String.class)) {
			
			byte[] sizeDt = getPrimByteData(data, 0, 4);
			
			int size = ByteBuffer.wrap(sizeDt).order(ByteOrder.LITTLE_ENDIAN).getInt();
			
			byte[] dt = getPrimByteData(data, 4, size);
			
			return (T) new String(dt);
			
		}
		
		return null;
		
	}
	
	public byte[] getByteData() {
		
		byte[] bData = new byte[data.size()];
		
		for (int i = 0; i < data.size(); i++) {
			bData[i] = data.get(i).byteValue();
		}
		
		return bData;
		
	}
	
	private static byte[] getPrimByteData(List<Byte> data, int index, int size) {
		
		byte[] dt = new byte[size];
		
		int ctr = 0;
		for (int i = index; i < size; i++) {
			
			dt[ctr] = data.get(i).byteValue();
			
			ctr++;
			
		}
		
		return dt;
		
	}
	
}
