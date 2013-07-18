package org.glydar.protocol.serverpackets;

import org.glydar.packets.Packet;
import org.glydar.packets.PacketDataType;
import org.glydar.packets.PacketStructure;
import org.glydar.packets.ServerPacketType;

public class ServerFullPacket extends Packet{
	
	private static PacketStructure structure;
	static {
		structure = new PacketStructure();
	}

	public ServerFullPacket() throws Exception {
		super(ServerPacketType.ServerFull.getId(), null);
	}

}
