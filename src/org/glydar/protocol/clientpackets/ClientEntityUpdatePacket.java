package org.glydar.protocol.clientpackets;

import org.glydar.packets.ClientPacketType;
import org.glydar.packets.Packet;
import org.glydar.packets.PacketDataType;
import org.glydar.packets.PacketStructure;
import org.glydar.packets.StructuredPacketData;

public class ClientEntityUpdatePacket extends Packet {
	
	private static PacketStructure structure;
	
	static {
		
		structure = new PacketStructure();
		structure.addDataType(new PacketDataType(Byte[].class, true));
		
	}
	
	public ClientEntityUpdatePacket() {
		super(ClientPacketType.EntityUpdate.getId(), new StructuredPacketData(structure));
	}
	
	public ClientEntityUpdatePacket(byte[] data) {
		super(ClientPacketType.EntityUpdate.getId(), new StructuredPacketData(structure).addData(data));
	}
	
	public static PacketStructure getStructure() {
		return structure;
	}
	
}
