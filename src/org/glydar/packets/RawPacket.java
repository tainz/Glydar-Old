package org.glydar.packets;

import org.glydar.exceptions.StructureMismatchException;

public class RawPacket extends Packet
{

	public RawPacket(int id, byte[] dat) throws StructureMismatchException
	{
		super(id, dat);
	}

	@Override
	public PacketStructure getStructure()
	{
		return null;
	}
	
}
