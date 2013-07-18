package org.glydar.protocol.serverpackets;

import org.glydar.packets.Packet;
import org.glydar.packets.PacketDataType;
import org.glydar.packets.PacketStructure;
import org.glydar.packets.ServerPacketType;
import org.glydar.packets.StructuredPacketData;

public class ServerTimePacket extends Packet {
	
	private static PacketStructure structure;
	
	static {
		
		structure = new PacketStructure();
		structure.addDataType(new PacketDataType(Integer.class));
		structure.addDataType(new PacketDataType(Integer.class));
		
	}
	
	public ServerTimePacket() {
		super(ServerPacketType.CurrentTime.getId(), new StructuredPacketData(structure));
	}
	
	public PacketStructure getStructure() {
		return structure;
	}
	
	public int getDay() {
		return ((StructuredPacketData)data).getDataAtIndex(Integer.class, structure.getLengthFromIndex(0));
	}
	
	public int getTime() {
		return ((StructuredPacketData)data).getDataAtIndex(Integer.class, structure.getLengthFromIndex(1));
	}
	
	public ServerTimePacket setDay(int day) {
		
		((StructuredPacketData)data).setDataAtStructureIndex(0, day);
	
		return this;
		
	}
	
	public ServerTimePacket setTime(int time) {
		
		((StructuredPacketData)data).setDataAtStructureIndex(1, time);
	
		return this;
		
	}
	
}
