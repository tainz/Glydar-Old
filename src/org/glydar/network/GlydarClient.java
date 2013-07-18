package org.glydar.network;

import io.netty.channel.socket.SocketChannel;

import java.net.InetSocketAddress;

public class GlydarClient {

    private int id;

    private SocketChannel channel;

    public GlydarClient(int id, SocketChannel channel) {
        this.id = id;
        this.channel = channel;
    }

    public int getId() {
        return this.id;
    }

    public InetSocketAddress getAddress() {
        return channel.remoteAddress();
    }

    public SocketChannel getSocketChannel() {
        return channel;
    }

}
