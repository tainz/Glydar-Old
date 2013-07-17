package org.glydar.packets;

public interface IPacketHandler
{
	
	PacketStructure getStructure();
	
	int getPacketId();
	
	Packet handlePacketData(byte[] packetData) throws Exception;
	
}
