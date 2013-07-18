package org.glydar.protocol.serverpackets;

import org.glydar.packets.Packet;
import org.glydar.packets.ServerPacketType;

public class ServerFullPacket extends Packet{

	public ServerFullPacket() throws Exception {
		super(ServerPacketType.ServerFull.getId(), null);
	}

}
