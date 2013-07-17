package org.glydar.network;

import org.glydar.packets.Packet;
import org.glydar.protocol.serverpackets.ServerMismatchPacket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class GlydarServerHandler extends SimpleChannelInboundHandler<Packet>
{
	
	@Override
	public void handlerAdded(ChannelHandlerContext ctx)
	{
	}
	
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx)
	{
	}
	
	@Override
	protected void messageReceived(ChannelHandlerContext ctx, Packet msg) throws Exception
	{
		
		System.out.println("Packet id: " + msg.getId());
		
		ctx.write(new ServerMismatchPacket().setVersion(3));
		
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception
	{
		ctx.close();
	}
	
}
