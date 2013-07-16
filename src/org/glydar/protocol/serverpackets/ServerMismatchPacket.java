package org.glydar.protocol.serverpackets;

import org.glydar.packets.Packet;
import org.glydar.packets.PacketData;
import org.glydar.packets.PacketDataType;
import org.glydar.protocol.Version3.ServerPacketType;

public class ServerMismatchPacket extends Packet
{
	
	public final int PacketSize = 4;
	
	public ServerMismatchPacket() throws Exception
	{
		
		super(ServerPacketType.ServerMismatch.getId(), null);
		
		structure.addDataType(new PacketDataType(Integer.class));
		
		data = new PacketData(structure);
		
	}
	
	public ServerMismatchPacket setVersion(int newVersion)
	{
		
		data.setDataAtIndex(structure.getLengthFromIndex(0), newVersion);
		
		return this;
		
	}
	
	public int getVersion()
	{
		return data.getDataAtIndex(Integer.class, structure.getLengthFromIndex(0));
	}
	
}
