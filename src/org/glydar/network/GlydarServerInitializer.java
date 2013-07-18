package org.glydar.network;

import java.util.ArrayList;
import java.util.List;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.util.AttributeKey;

public class GlydarServerInitializer extends ChannelInitializer<SocketChannel>
{
	
	private List<GlydarClient> clients;
	
	private static AttributeKey<GlydarClient> clientKey;
	
	static
	{
		clientKey = new AttributeKey<GlydarClient>("GlydarClient");
	}
	
	public GlydarServerInitializer()
	{
		clients = new ArrayList<GlydarClient>();
	}
	
	@Override
	protected void initChannel(SocketChannel ch) throws Exception
	{
		
		GlydarClient client = new GlydarClient(clients.size() + 1, ch);
		
		ch.attr(clientKey).set(client);
		
		clients.add(client);
		
		ChannelPipeline pipeline = ch.pipeline();
		
		pipeline.addLast("decoder", new PacketDecoder());
		pipeline.addLast("encoder", new PacketEncoder());
		
		pipeline.addLast("handler", new GlydarServerHandler());
		
	}
	
	public List<GlydarClient> getClients()
	{
		return this.clients;
	}
	
	public static AttributeKey<GlydarClient> getClientAttrbKey()
	{
		return clientKey;
	}
	
}
