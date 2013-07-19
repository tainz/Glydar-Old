package org.glydar.protocol.serverpackets;

import org.glydar.packets.*;
import org.glydar.util.MiscUtil;

public class ServerDataPacket extends Packet {
	
	private static PacketStructure structure;
	
	static {
		
		structure = new PacketStructure();
		structure.addDataType(new PacketDataType(Integer.class));
		structure.addDataType(new PacketDataType(Long.class));
		structure.addDataType(new PacketDataType(Byte.class, 4456));
		
	}
	
	public ServerDataPacket() {
		
		super(ServerPacketType.ServerData.getId(), null);
		
		data = new StructuredPacketData(structure);
		((StructuredPacketData) data).setDataAtStructureIndex(0, 0);
		
	}
	
	public ServerDataPacket setEntityId(long entId) {
		
		((StructuredPacketData) data).setDataAtStructureIndex(1, entId);
		((StructuredPacketData) data).setDataAtStructureIndex(2, MiscUtil.getEmptyByteArray(4456));
		
		return this;
		
	}
	
}
