package org.glydar.protocol.serverpackets;

import org.glydar.packets.Packet;
import org.glydar.packets.PacketData;
import org.glydar.packets.PacketDataType;
import org.glydar.packets.PacketStructure;
import org.glydar.packets.ServerPacketType;

public class ServerMismatchPacket extends Packet
{
	
	private static PacketStructure structure;
	
	static
	{
		structure = new PacketStructure();
		structure.addDataType(new PacketDataType(Integer.class));
	}
	
	public ServerMismatchPacket() throws Exception
	{
		
		super(ServerPacketType.ServerMismatch.getId(), null);
		
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
	
	public static PacketStructure getStructure()
	{
		return structure;
	}
	
}
