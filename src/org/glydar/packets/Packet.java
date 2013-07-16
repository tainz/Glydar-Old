package org.glydar.packets;

import org.glydar.exceptions.StructureMismatchException;

public abstract class Packet
{
	
	protected int id;
	protected PacketData data;
	
	public abstract PacketStructure getStructure();
	
	public Packet(int id, PacketData data) throws StructureMismatchException
	{
		
		if (getStructure() != null && !getStructure().matchesStructureLength(data.getByteData()))
		{
			throw new StructureMismatchException("Data length does not match the structure length!");
		}
		
		this.id = id;
		this.data = data;
	
	}
	
	public Packet(int id, byte[] dat) throws StructureMismatchException
	{
		this(id, new PacketData(dat));
	}
	
	public int getId()
	{
		return this.id;
	}
	
	public PacketData getData()
	{
		return this.data;
	}
	
}