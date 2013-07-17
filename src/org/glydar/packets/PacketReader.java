package org.glydar.packets;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.SocketChannel;

/**
 * Class for reading and determining packet contents
 * @author JohSketch
 */
public class PacketReader
{
	private SocketChannel channel;
	
	private IPacketCreator packetCreator;
	
	public PacketReader(SocketChannel channel, IPacketCreator packetCreator)
	{
		this.channel = channel;
		this.packetCreator = packetCreator;
	}
	
	public Packet readPacket() throws Exception
	{
		ByteBuffer buf = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN);
		
		channel.read(buf); //read the first integer (the id of the packet)
		
		buf.flip();
		
		if (!buf.hasRemaining())
			return null;
			
		int id = buf.getInt();
		
		int len = packetCreator.getPacketLength(id);
		
		ByteBuffer datBuf = ByteBuffer.allocate(len).order(ByteOrder.LITTLE_ENDIAN);
		
		channel.read(datBuf); //read the packet data
		
		datBuf.flip();
		
		Packet packet = packetCreator.createPacket(id, datBuf.array());
		
		return packet;
	}
}
