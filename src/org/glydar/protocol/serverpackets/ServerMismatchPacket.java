package org.glydar.protocol.serverpackets;

import org.glydar.exceptions.StructureMismatchException;
import org.glydar.packets.Packet;
import org.glydar.packets.PacketData;
import org.glydar.packets.PacketStructure;
import org.glydar.protocol.Version3.ServerPacketType;

public class ServerMismatchPacket extends Packet
{

	public ServerMismatchPacket(int version) throws StructureMismatchException
	{
		super(ServerPacketType.ServerMismatch.getId(), new PacketData().addData(version));
	}

	@Override
	public PacketStructure getStructure()
	{
		
		PacketStructure structure = new PacketStructure();
		structure.addDataType(Integer.class);
		
		return structure;
		
	}
	
}
