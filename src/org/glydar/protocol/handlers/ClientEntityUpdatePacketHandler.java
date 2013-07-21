package org.glydar.protocol.handlers;

import org.glydar.network.GlydarClient;
import org.glydar.packets.ClientPacketType;
import org.glydar.packets.Packet;
import org.glydar.packets.PacketHandler;
import org.glydar.protocol.clientpackets.ClientEntityUpdatePacket;
import org.glydar.protocol.serverpackets.ServerEntityUpdatePacket;

public class ClientEntityUpdatePacketHandler extends PacketHandler {

	public ClientEntityUpdatePacketHandler() {
		super(ClientPacketType.EntityUpdate.getId());
	}

	@Override
	public void handlePacket(GlydarClient client, Packet packet)
			throws Exception {

		try {

			ClientEntityUpdatePacket clientEntityUpdatePacket = (ClientEntityUpdatePacket) packet;

			clientEntityUpdatePacket.decompress();

			clientEntityUpdatePacket.getData();

			new ServerEntityUpdatePacket();

		} catch (Exception e) {
		}

	}

}
