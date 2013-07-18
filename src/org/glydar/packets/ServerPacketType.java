package org.glydar.packets;


public enum ServerPacketType
{
	
	EntityUpdate(0), MultipleEntityUpdate(1), UpdateFinished(2), Unknown3(3), Unknown4(4), CurrentTime(5), ServerChatMessage(10), SeedData(15), ServerData(16), ServerMismatch(17), ServerFull(18);
	
	private final int id;
	
	ServerPacketType(int id)
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