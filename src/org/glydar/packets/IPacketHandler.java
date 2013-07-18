package org.glydar.packets;

public interface IPacketHandler
{
	
	void handlePacket(Packet packet) throws Exception;
	
	int getPacketId();
	
}
