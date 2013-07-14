package net.cyberkitsune.jKube;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;

public class ListenThread extends Thread {
	
	Server s;
	
	public ListenThread(Server s) {
		this.s = s;
	}
	
	@Override
	public void run() {
		try {
			ServerSocketChannel listenerChannel = ServerSocketChannel.open();
			listenerChannel.socket().bind(new InetSocketAddress(12345));
			try {
				Server.getLog().info("Server running. Waiting for connection...");
				while (!Thread.interrupted()) {
					new ClientConnection(listenerChannel.accept().socket(), s, s.numConnections++)
							.start();
				}
			} catch (Exception e) {

			} finally {
				listenerChannel.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
