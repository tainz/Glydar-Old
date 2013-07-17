package org.glydar.packets;

public class RawPacket extends Packet
{

	public RawPacket(int id, byte[] dat) throws Exception
	{
		super(id, new PacketData(dat));
	}
	
}
