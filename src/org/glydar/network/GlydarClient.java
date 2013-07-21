package org.glydar.network;

import io.netty.channel.socket.SocketChannel;

import java.net.InetSocketAddress;

public class GlydarClient {

	private int id;
	private boolean connected;

	private SocketChannel channel;

	public GlydarClient(int id, SocketChannel channel) {
		this.id = id;
		this.channel = channel;
		connected = false;
	}

	public int getId() {
		return id;
	}

	public InetSocketAddress getAddress() {
		return channel.remoteAddress();
	}

	public SocketChannel getSocketChannel() {
		return channel;
	}

	public boolean isConnected() {
		return connected;
	}

	public void setConnected(boolean c) {
		connected = c;
	}

	public void disconnect() {
		if (connected == true) {
			channel.close();
			setConnected(false);
		}
	}
}
