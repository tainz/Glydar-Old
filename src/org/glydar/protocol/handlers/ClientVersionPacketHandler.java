package org.glydar.protocol.handlers;

import org.glydar.network.GlydarClient;
import org.glydar.packets.ClientPacketType;
import org.glydar.packets.Packet;
import org.glydar.packets.PacketHandler;
import org.glydar.protocol.clientpackets.ClientVersionPacket;
import org.glydar.protocol.serverpackets.ServerDataPacket;

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
		
		client.getSocketChannel().write(new ServerDataPacket().setEntityId(1));
		
	}
	
}
