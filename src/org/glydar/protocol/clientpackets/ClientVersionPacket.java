package org.glydar.protocol.clientpackets;

import org.glydar.packets.Packet;
import org.glydar.packets.PacketData;
import org.glydar.packets.PacketDataType;
import org.glydar.packets.PacketStructure;
import org.glydar.protocol.Version3.ClientPacketType;

public class ClientVersionPacket extends Packet
{
	
	private static PacketStructure structure;
	
	static
	{
		structure = new PacketStructure();
		structure.addDataType(new PacketDataType(Integer.class));
	}
	
	public ClientVersionPacket(byte[] data)
	{
		super(ClientPacketType.ClientVersion.getId(), new PacketData(data));
	}
	
	public int getVersion()
	{
		return data.getDataAtIndex(Integer.class, 0);
	}
	
	public static PacketStructure getStructure()
	{
		return structure;
	}
	
}
