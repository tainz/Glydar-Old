package org.glydar.network;

import java.nio.ByteOrder;

import org.glydar.Glydar;
import org.glydar.packets.IPacketCreator;
import org.glydar.packets.PacketDataType;
import org.glydar.packets.PacketStructure;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.MessageList;
import io.netty.handler.codec.ByteToMessageDecoder;

public class PacketDecoder extends ByteToMessageDecoder
{
	
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, MessageList<Object> out) throws Exception
	{
		
		if (in.readableBytes() < 4)
			return;
		
		in.markReaderIndex();
		
		int id = in.readInt();
		id = ByteBufUtil.swapInt(id);
		
		IPacketCreator creator = Glydar.getServer().getPacketCreatorList().getCreatorWithId(id);
		
		PacketStructure structure = creator.getStructure();
		
		if (structure == null)
		{
			
			in.resetReaderIndex();
			
			return;
			
		}
		
		int len = structure.getTotalLength();
		
		if (in.readableBytes() < len)
		{
			
			in.resetReaderIndex();
			
			return;
			
		}
		
		for (PacketDataType pdt : structure.getDataTypes())
		{
			
			if (pdt.isDynamicLength())
			{
				
				/*
				 * Read next integer, which *should* contain the size of the dynamic data type. This would, for example, be used for chat messages
				 */
				int dLen = in.order(ByteOrder.LITTLE_ENDIAN).readInt();
				
				if (!pdt.getDataType().isAssignableFrom(String.class))
					len += dLen;
				else
					len += dLen * 2;
				
			}
			
		}
		
		byte[] data = new byte[len];
		
		in.order(ByteOrder.LITTLE_ENDIAN).readBytes(data);
		
		out.add(creator.createPacket(data));
		
	}
	
}
