package org.glydar.protocol.handlers;

import org.glydar.Glydar;
import org.glydar.network.GlydarClient;
import org.glydar.packets.ClientPacketType;
import org.glydar.packets.Packet;
import org.glydar.packets.PacketHandler;
import org.glydar.protocol.clientpackets.ClientVersionPacket;
import org.glydar.protocol.serverpackets.ServerChatPacket;
import org.glydar.protocol.serverpackets.ServerDataPacket;
import org.glydar.protocol.serverpackets.ServerFullPacket;
import org.glydar.protocol.serverpackets.ServerMismatchPacket;
import org.glydar.protocol.serverpackets.ServerSeedPacket;
import org.glydar.util.LogUtil;

public class ClientVersionPacketHandler extends PacketHandler {
	public LogUtil log;

	public ClientVersionPacketHandler() {
		super(ClientPacketType.ClientVersion.getId());
	}

	@Override
	public void handlePacket(GlydarClient client, Packet packet)
			throws Exception {
		log = new LogUtil();
		super.handlePacket(client, packet);

		// System.out.println("Client Version Packet Received!");
		System.out.println("Version: "
				+ ((ClientVersionPacket) packet).getVersion());

		if (Glydar.getServer().getCurrentProtocolVersion() != ((ClientVersionPacket) packet)
				.getVersion()) {
			log.output("Wrong version");
			client.getSocketChannel().write(
					new ServerMismatchPacket().setVersion(Glydar.getServer()
							.getCurrentProtocolVersion()));
		} else if (Glydar.getServer().getClients().size() > Glydar.getServer()
				.getMaxPlayers()) {
			log.output("Server full");
			client.getSocketChannel().write(new ServerFullPacket());
		} else {
			client.setConnected(true);
			client.getSocketChannel().write(
					new ServerDataPacket().setEntityId(client.getId()));
			client.getSocketChannel().write(
					new ServerSeedPacket()
							.setSeed(Glydar.getServer().getSeed()));
			client.getSocketChannel().write(
					new ServerChatPacket().setMessage("Welcome to the server!",
							0));
		}

	}

}
