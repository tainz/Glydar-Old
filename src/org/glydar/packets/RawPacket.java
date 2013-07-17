package org.glydar.packets;

/**
 * Class for raw packets
 * @author JohSketch
 */
public class RawPacket extends Packet
{
	public RawPacket(int id, byte[] dat) throws Exception
	{
		super(id, new PacketData(dat));
	}
}
