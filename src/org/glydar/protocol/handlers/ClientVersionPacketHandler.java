package org.glydar.protocol.handlers;

import org.glydar.Glydar;
import org.glydar.network.GlydarClient;
import org.glydar.packets.ClientPacketType;
import org.glydar.packets.Packet;
import org.glydar.packets.PacketHandler;
import org.glydar.protocol.clientpackets.ClientVersionPacket;
import org.glydar.protocol.serverpackets.ServerDataPacket;
import org.glydar.protocol.serverpackets.ServerMismatchPacket;
import org.glydar.protocol.serverpackets.ServerSeedPacket;

public class ClientVersionPacketHandler extends PacketHandler
{
	
	public ClientVersionPacketHandler()
	{
		super(ClientPacketType.ClientVersion.getId());
	}
	
	@Override
	public void handlePacket(GlydarClient client, Packet packet) throws Exception
	{
		
		super.handlePacket(client, packet);
		
		System.out.println("Client Version Packet Received!");
		System.out.println("Version: " + ((ClientVersionPacket)packet).getVersion());
		
		if(Glydar.getServer().getCurrentProtocolVersion() != ((ClientVersionPacket)packet).getVersion()) {
			client.getSocketChannel().write(new ServerDataPacket().setEntityId(1));
            client.getSocketChannel().write(new ServerSeedPacket(6969));
		} else {
			client.getSocketChannel().write(new ServerMismatchPacket().setVersion(3));
		}
		
	}
	
}
