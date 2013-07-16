package org.glydar.packets;

public class Packet
{
	
	protected int id;
	protected PacketData data;
	
	public Packet(int id)
	{
		this.id = id;
	}
	
	public Packet(int id, byte[] dat)
	{
		this.id = id;
		this.data = new PacketData(dat);
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