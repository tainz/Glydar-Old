package org.glydar.protocol.handlers;

import org.glydar.network.GlydarClient;
import org.glydar.packets.ClientPacketType;
import org.glydar.packets.Packet;
import org.glydar.packets.PacketHandler;
import org.glydar.packets.StructuredPacketData;
import org.glydar.protocol.clientpackets.ClientEntityUpdatePacket;
import org.glydar.util.ZLibUtil;

public class ClientEntityUpdatePacketHandler extends PacketHandler {
	
	public ClientEntityUpdatePacketHandler() {
		super(ClientPacketType.EntityUpdate.getId());
	}
	
	@Override
	public void handlePacket(GlydarClient client, Packet packet) throws Exception {
		
		byte[] deData = ZLibUtil.decompress(packet.getData().getByteData());
		
	}
	
}
