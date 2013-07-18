package org.glydar.packets;

import java.util.ArrayList;

public class PacketStructure {
	
	private ArrayList<PacketDataType> dataTypes;
	
	public PacketStructure() {
		dataTypes = new ArrayList<PacketDataType>();
	}
	
	public PacketStructure(PacketStructure structure) {
		dataTypes = new ArrayList<PacketDataType>(structure.getDataTypes());
	}
	
	public void addDataType(PacketDataType type) {
		dataTypes.add(type);
	}
	
	public void removeDataType(PacketDataType type) {
		dataTypes.remove(type);
	}
	
	public ArrayList<PacketDataType> getDataTypes() {
		return dataTypes;
	}
	
	public boolean matchesStructureLength(byte[] data) {
		
		int len = 0;
		
		for (PacketDataType dType : dataTypes) {
			len += dType.getLength();
		}
		
		return data.length == len;
		
	}
	
	public PacketDataType getDataTypeAtIndex(int index) {
		return dataTypes.get(index);
	}
	
	public int getLengthFromIndex(int index) {
		
		int len = 0;
		
		for (int i = 0; i < dataTypes.size(); i++) {
			
			if (i == index)
				break;
			
			PacketDataType dType = dataTypes.get(i);
			
			len += dType.getLength();
			
		}
		
		return len;
		
	}
	
	public int getTotalLength() {
		
		int len = 0;
		
		for (int i = 0; i < dataTypes.size(); i++) {
			
			PacketDataType dType = dataTypes.get(i);
			
			len += dType.getLength();
			
		}
		
		return len;
		
	}
	
}
