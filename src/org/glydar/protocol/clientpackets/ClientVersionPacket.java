package org.glydar.protocol.clientpackets;

import org.glydar.packets.Packet;
import org.glydar.packets.PacketData;
import org.glydar.packets.PacketDataType;
import org.glydar.packets.PacketStructure;
import org.glydar.protocol.Version3.ClientPacketType;


public class ClientVersionPacket extends Packet
{
	
	public static final int PacketSize = 4;
	
	public ClientVersionPacket(byte[] data) throws Exception
	{
		super(ClientPacketType.ClientVersion.getId(), new PacketData(data));
	}
	
	public int getVersion()
	{
		return data.getDataAtIndex(Integer.class, 0);
	}

	@Override
	public PacketStructure getStructure() throws Exception
	{
		
		PacketStructure structure = new PacketStructure();
		structure.addDataType(new PacketDataType(Integer.class));
		
		return structure;
		
	}
	
}
