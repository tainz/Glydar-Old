package net.cyberkitsune.jCube;

import java.net.Socket;

public class ClientConnection extends Thread {
	
	private Socket skt;
	private Server context;
	private int connectionID;
	
	public ClientConnection(Socket skt, Server s, int id) {
		this.skt = skt;
		this.context = s;
		this.connectionID = id;
	}
	
	@Override
	public void run() {
		
	}

}
