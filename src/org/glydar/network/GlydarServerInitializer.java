package org.glydar.network;

import java.util.ArrayList;
import java.util.List;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class GlydarServerInitializer extends ChannelInitializer<SocketChannel>
{
	
	private List<GlydarClient> clients;
	
	public GlydarServerInitializer()
	{
		clients = new ArrayList<GlydarClient>();
	}
	
	@Override
	protected void initChannel(SocketChannel ch) throws Exception
	{
		
		ChannelPipeline pipeline = ch.pipeline();
		
		pipeline.addLast("decoder", new PacketDecoder());
		pipeline.addLast("encoder", new PacketEncoder());
		
		pipeline.addLast("handler", new GlydarServerHandler());
		
		clients.add(new GlydarClient(clients.size() + 1, ch.remoteAddress()));
		
	}
	
	public List<GlydarClient> getClients()
	{
		return this.clients;
	}
	
}
