package org.glydar.packets;

import java.util.ArrayList;
import java.util.List;

public class PacketHandlerList
{
	
	private List<IPacketHandler> handlers;
	
	public PacketHandlerList()
	{
		this.handlers = new ArrayList<IPacketHandler>();
	}
	
	public PacketHandlerList(List<IPacketHandler> handlers)
	{
		this.handlers = handlers;
	}
	
	public void addHandler(IPacketHandler handler) throws Exception
	{
		
		if (containsHandlerWithId(handler.getPacketId()))
			throw new Exception("A handler with that id already exists!");
		
		handlers.add(handler);
		
	}
	
	public void removeHandler(IPacketHandler handler)
	{
		handlers.remove(handler);
	}
	
	public boolean containsHandlerWithId(int id)
	{
		return getHandlerWithId(id) != null;
	}
	
	public IPacketHandler getHandlerWithId(int id)
	{
		
		IPacketHandler pHandler = null;
		
		for (IPacketHandler handler : handlers)
		{
			if (handler.getPacketId() == id)
			{
				
				pHandler = handler;
				
				break;
			
			}
		}
		
		return pHandler;
		
	}
	
}
