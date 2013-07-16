package org.glydar.packets;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.SocketChannel;

public class PacketWriter
{
	
	private SocketChannel channel;
	
	public PacketWriter(SocketChannel channel)
	{
		this.channel = channel;
	}
	
	public void writePacket(Packet packet) throws IOException
	{
		
		ByteBuffer buf1 = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(packet.getId());
		ByteBuffer buf2 = ByteBuffer.allocate(packet.getData().getByteData().length).order(ByteOrder.LITTLE_ENDIAN).put(packet.getData().getByteData());
		
		buf1.flip();
		buf2.flip();
		
		channel.write(buf1);
		channel.write(buf2);
		
	}
	
}
