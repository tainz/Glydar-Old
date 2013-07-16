package org.glydar.packets;

import org.glydar.protocol.Version3.ClientPacketType;
import org.glydar.protocol.clientpackets.ClientVersionPacket;


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
				
				size = 4;
				
				break;
			
			default:
				
				size = 0;
				
				break;
		
		}
		
		return size;
		
	}
	
	@Override
	public Packet createPacket(int id, byte[] data)
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
