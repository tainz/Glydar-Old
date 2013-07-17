package org.glydar.network;

import java.nio.ByteOrder;

import org.glydar.packets.Packet;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class PacketEncoder extends MessageToByteEncoder<Packet>
{

	@Override
	protected void encode(ChannelHandlerContext ctx, Packet msg, ByteBuf out) throws Exception
	{
		
		out.order(ByteOrder.LITTLE_ENDIAN).writeInt(msg.getId());
		out.order(ByteOrder.LITTLE_ENDIAN).writeBytes(msg.getData().getByteData());
		
	}
	
}
