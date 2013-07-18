package org.glydar.packets;

public enum ClientPacketType
{
	
	EntityUpdate(0), InteractPacket(6), HitPacket(7), Unknown8(8), ShootPacket(9), ClientChatMessage(10), ChunkDiscovered(11), SectorDiscovered(12), ClientVersion(17);
	
	private final int id;
	
	ClientPacketType(int id)
	{
		this.id = id;
	}
	
	public int getId()
	{
		return this.id;
	}
	
	public static ClientPacketType getPacketType(int id)
	{
		
		ClientPacketType[] types = ClientPacketType.values();
		
		for (ClientPacketType type : types)
		{
			if (type.getId() == id)
				return type;
		}
		
		return null;
		
	}
	
}