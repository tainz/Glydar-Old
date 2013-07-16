package org.glydar.packets;

import org.glydar.exceptions.StructureMismatchException;

public class RawPacket extends Packet
{

	public RawPacket(int id, byte[] dat) throws Exception
	{
		super(id, new PacketData(dat));
	}

	@Override
	public PacketStructure getStructure()
	{
		return null;
	}
	
}
