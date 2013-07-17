package org.glydar.packets;

/**
 * Class for packets and what they are
 * @author JohSketch
 */
public abstract class Packet
{
	
	protected int id;
	protected PacketData data;
	
	public Packet(int id, PacketData data)
	{
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
	
}