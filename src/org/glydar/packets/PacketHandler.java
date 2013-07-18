package org.glydar.packets;

public abstract class PacketHandler implements IPacketHandler
{
	
	protected int packetId;
	
	public PacketHandler(int packetId)
	{
		this.packetId = packetId;
	}
	
	@Override
	public void handlePacket(Packet packet) throws Exception
	{
		
		if (packet.getId() != packetId)
			throw new Exception("Packet id does not match handler id!");
		
	}
	
	@Override
	public int getPacketId()
	{
		return this.packetId;
	}
	
}
