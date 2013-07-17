package org.glydar.protocol.clientpackets;

import org.glydar.packets.IPacketCreator;
import org.glydar.packets.Packet;
import org.glydar.protocol.Version3.ClientPacketType;

public class ClientPacketCreator implements IPacketCreator
{
	
	@Override
	public int getPacketLength(int id)
	{
		
		ClientPacketType type = ClientPacketType.getPacketType(id);
		
		int size = 0;
		
		switch (type)
		{
		
			case ClientVersion:
				
				size = ClientVersionPacket.getStructure().getTotalLength();
				
				break;
			
			default:
				
				size = 0;
				
				break;
		
		}
		
		return size;
		
	}
	
	@Override
	public Packet createPacket(int id, byte[] data) throws Exception
	{
		
		ClientPacketType type = ClientPacketType.getPacketType(id);
		
		Packet packet = null;
		
		switch (type)
		{
		
			case ClientVersion:
				
				packet = new ClientVersionPacket(data);
				
				break;
			
			default:
				break;
		
		}
		
		return packet;
		
	}
	
}
