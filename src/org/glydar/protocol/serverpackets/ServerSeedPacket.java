package org.glydar.protocol.serverpackets;

import org.glydar.packets.Packet;
import org.glydar.packets.PacketDataType;
import org.glydar.packets.PacketStructure;
import org.glydar.packets.ServerPacketType;

public class ServerSeedPacket extends Packet{
	
	private static PacketStructure structure;
	static {
		structure = new PacketStructure();
		structure.addDataType(new PacketDataType(Integer.class));
	}

	public ServerSeedPacket(int seed) throws Exception {
		super(ServerPacketType.SeedData.getId(), null);
		data.getDataAtIndex(structure.getLengthFromIndex(0), seed);
	}

}
