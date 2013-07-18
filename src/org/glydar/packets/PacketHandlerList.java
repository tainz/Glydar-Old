package org.glydar.packets;

import java.util.ArrayList;
import java.util.List;

public class PacketHandlerList
{
	
	private List<IPacketHandler> handlers;
	
	public PacketHandlerList()
	{
		handlers = new ArrayList<IPacketHandler>();
	}
	
	public void addHandler(IPacketHandler handler)
	{
		handlers.add(handler);
	}
	
	public List<IPacketHandler> getHandlers()
	{
		return this.handlers;
	}
	
	public List<IPacketHandler> getHandlersWithId(int id)
	{
		
		List<IPacketHandler> pHandlers = new ArrayList<IPacketHandler>();
		
		for (IPacketHandler handler : handlers)
		{
			if (handler.getPacketId() == id)
			{
				pHandlers.add(handler);
			}
		}
		
		return pHandlers;
		
	}
	
}
