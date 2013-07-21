package org.glydar.network;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import org.glydar.Glydar;
import org.glydar.packets.IPacketHandler;
import org.glydar.packets.Packet;

public class GlydarServerHandler extends SimpleChannelInboundHandler<Packet> {

	@Override
	protected void messageReceived(ChannelHandlerContext ctx, Packet msg)
			throws Exception {

		GlydarClient client = ctx.channel()
				.attr(GlydarServerInitializer.getClientAttrbKey()).get();

		if (client == null) {

			ctx.close().sync();

			return;

		}

		// System.out.println("Packet id: " + msg.getId());

		for (IPacketHandler handler : Glydar.getServer().getPacketHandlerList()
				.getHandlersWithId(msg.getId())) {
			handler.handlePacket(client, msg);
		}

	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

}
