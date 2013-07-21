package org.glydar.network;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.util.AttributeKey;

import java.util.ArrayList;
import java.util.List;

import org.glydar.Glydar;

public class GlydarServerInitializer extends ChannelInitializer<SocketChannel> {

	private List<GlydarClient> clients;

	private static AttributeKey<GlydarClient> clientKey;

	static {
		clientKey = new AttributeKey<GlydarClient>("GlydarClient");
	}

	public GlydarServerInitializer() {
		clients = new ArrayList<GlydarClient>();
	}

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {

		GlydarClient client = new GlydarClient(Glydar.getServer()
				.incrementConId(), ch);

		ch.attr(clientKey).set(client);

		clients.add(client);

		ChannelPipeline pipeline = ch.pipeline();

		pipeline.addLast("decoder", new PacketDecoder());
		pipeline.addLast("encoder", new PacketEncoder());

		pipeline.addLast("handler", new GlydarServerHandler());

	}

	public List<GlydarClient> getClients() {
		return clients;
	}

	public static AttributeKey<GlydarClient> getClientAttrbKey() {
		return clientKey;
	}

}
