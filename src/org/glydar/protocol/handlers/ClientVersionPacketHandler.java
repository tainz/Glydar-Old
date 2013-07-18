package org.glydar.protocol.handlers;

import org.glydar.packets.ClientPacketType;
import org.glydar.packets.Packet;
import org.glydar.packets.PacketHandler;
import org.glydar.protocol.clientpackets.ClientVersionPacket;

public class ClientVersionPacketHandler extends PacketHandler
{
	
	public ClientVersionPacketHandler()
	{
		super(ClientPacketType.ClientVersion.getId());
	}
	
	@Override
	public void handlePacket(Packet packet) throws Exception
	{
		
		super.handlePacket(packet);
		
		System.out.println("Client Version Packet Received!");
		System.out.println("Version: " + ((ClientVersionPacket)packet).getVersion());
		
	}
	
}
