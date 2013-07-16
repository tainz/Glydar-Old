package org.glydar.protocol.clientpackets;

import org.glydar.packets.Packet;
import org.glydar.protocol.Version3.ClientPacketType;


public class ClientVersionPacket extends Packet
{
	
	public static final int PacketSize = 4;
	
	public ClientVersionPacket(byte[] data)
	{
		super(ClientPacketType.ClientVersion.getId(), data);
	}
	
	public int getVersion()
	{
		return data.getDataAtIndex(Integer.class, 0);
	}
	
}
