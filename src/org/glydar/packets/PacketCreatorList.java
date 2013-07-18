package org.glydar.packets;

import java.util.ArrayList;
import java.util.List;

public class PacketCreatorList
{
	
	private List<IPacketCreator> creators;
	
	public PacketCreatorList()
	{
		this.creators = new ArrayList<IPacketCreator>();
	}
	
	public PacketCreatorList(PacketCreatorList creatorList)
	{
		this.creators = new ArrayList<IPacketCreator>(creatorList.creators);
	}
	
	public PacketCreatorList(List<IPacketCreator> creators)
	{
		this.creators = creators;
	}
	
	public void addPacketCreator(IPacketCreator creator) throws Exception
	{
		
		if (containsCreatorWithId(creator.getPacketId()))
		{
			throw new Exception("Already have a packet creator with that packet id!");
		}
		
		creators.add(creator);
		
	}
	
	public void removePacketCreator(IPacketCreator creator)
	{
		creators.remove(creator);
	}
	
	public boolean containsCreatorWithId(int id)
	{
		return getCreatorWithId(id) != null;
	}
	
	public IPacketCreator getCreatorWithId(int id)
	{
		
		IPacketCreator pCreator = null;
		
		for (IPacketCreator creator : creators)
		{
			if (creator.getPacketId() == id)
			{
				
				pCreator = creator;
				
				break;
				
			}
		}
		
		return pCreator;
		
	}
	
	public List<IPacketCreator> getPacketCreators()
	{
		return this.creators;
	}
	
}
