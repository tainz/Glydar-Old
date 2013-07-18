package org.glydar.packets;

import java.util.Arrays;
import java.util.List;

public class StructuredPacketData extends PacketData {
	
	private PacketStructure packetStructure;
	
	public StructuredPacketData(PacketStructure structure) {
		
		super();
		
		packetStructure = new PacketStructure(structure);
		
		for (PacketDataType dType : packetStructure.getDataTypes()) {
			
			Byte[] emptyDat = new Byte[dType.getLength()];
			
			for (int i = 0; i < emptyDat.length; i++) {
				emptyDat[i] = 0;
			}
			
			data.addAll(Arrays.asList(emptyDat));
			
		}
		
	}
	
	public <T> StructuredPacketData setDataAtStructureIndex(int index, T dat) {
		
		List<Byte> bytes = getDataList(dat);
		
		int bIndex = packetStructure.getLengthFromIndex(index);
		
		PacketDataType dType = packetStructure.getDataTypeAtIndex(index);
		
		if (dType.isDynamicLength()) {
			
			data.addAll(bIndex, bytes);
			
			dType.setLength(bytes.size());
			dType.setDynamicLength(false);
			
		} else {
			
			setDataAtIndex(bIndex, dat);
			
		}
		
		return this;
		
	}
	
}
