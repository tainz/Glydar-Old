package org.glydar.packets;

import org.glydar.exceptions.StructureMismatchException;

public abstract class Packet
{
	
	protected int id;
	protected PacketData data;
	
	protected PacketStructure structure;
	
	public Packet(int id, PacketData data) throws Exception
	{
		
		PacketStructure struct = getStructure();
		
		if (struct != null && data != null && !getStructure().matchesStructureLength(data.getByteData()))
		{
			throw new StructureMismatchException("Data length does not match the structure length!");
		}
		
		if (struct != null && data == null)
		{
			data = new PacketData(structure);
		}
		else if (structure == null)
		{
			this.structure = new PacketStructure();
		}
		
		this.id = id;
		this.data = data;
		
	}
	
	public int getId()
	{
		return this.id;
	}
	
	public PacketData getData()
	{
		return this.data;
	}
	
	public PacketStructure getStructure() throws Exception
	{
		if (structure != null)
			return new PacketStructure(structure);
		else
			return null;
	}
	
}