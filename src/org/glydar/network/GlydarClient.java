package org.glydar.network;

import java.net.InetSocketAddress;

public class GlydarClient
{
	
	private int id;
	
	private InetSocketAddress address;
	
	public GlydarClient(int id, InetSocketAddress addr)
	{
		this.id = id;
		this.address = addr;
	}
	
	public int getId()
	{
		return this.id;
	}
	
	public InetSocketAddress getAddress()
	{
		return this.address;
	}
	
}
