package org.glydar.protocol.handlers;

import org.glydar.packets.Packet;
import org.glydar.packets.PacketHandler;
import org.glydar.packets.PacketStructure;
import org.glydar.protocol.Version3.ClientPacketType;
import org.glydar.protocol.clientpackets.ClientVersionPacket;

public class ClientVersionPacketHandler extends PacketHandler
{

	@Override
	public Packet handlePacketData(byte[] data) throws Exception
	{
		return new ClientVersionPacket(data);
	}

	@Override
	public int getPacketId()
	{
		return ClientPacketType.ClientVersion.getId();
	}

	@Override
	public PacketStructure getStructure()
	{
		return ClientVersionPacket.getStructure();
	}
	
}
