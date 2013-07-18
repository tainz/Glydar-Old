package org.glydar.packets;

public interface IPacketCreator
{
	
	PacketStructure getStructure();
	
	int getPacketId();
	
	Packet createPacket(byte[] data) throws Exception;
	
}
