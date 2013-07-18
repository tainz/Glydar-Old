package org.glydar.protocol.serverpackets;

import org.glydar.packets.Packet;
import org.glydar.packets.PacketData;
import org.glydar.packets.PacketDataType;
import org.glydar.packets.PacketStructure;
import org.glydar.packets.ServerPacketType;

public class ServerDataPacket extends Packet
{
	
	private static PacketStructure structure;
	
	static
	{
		
		structure = new PacketStructure();
		structure.addDataType(new PacketDataType(Integer.class));
		structure.addDataType(new PacketDataType(Long.class));
		
		for (int i = 0; i < 5736; i++)
		{
			structure.addDataType(new PacketDataType(Byte.class));
		}
		
	}
	
	public ServerDataPacket()
	{
		
		super(ServerPacketType.ServerData.getId(), null);
		
		data = new PacketData(structure);
		data.setDataAtIndex(structure.getLengthFromIndex(0), 0);
		
	}
	
	public ServerDataPacket setEntityId(long entId)
	{
		
		data.setDataAtIndex(structure.getLengthFromIndex(1), entId);
	
		return this;
		
	}
	
}
