package net.cyberkitsune.jCube;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Logger;

import net.cyberkitsune.jCube.protocol.NetworkProtocol;
import net.cyberkitsune.jCube.protocol.Version3;

public class Server {

	public Logger log = Logger.getLogger("jCube");
	public NetworkProtocol activeProto = new Version3(this);
	boolean running = true;
	int numConnections = -1;
	
	public void run() throws IOException {
		log.info("Starting Server on Port 12345");
		ServerSocket listener = new ServerSocket(12345);
		try {
			log.info("Server running. Waiting for connection...");
			while(running) {
				new ClientConnection(listener.accept(), this, numConnections++).start();
			}
		} catch (Exception e) {
			
		} finally {
			listener.close();
		}
	}
	
}
