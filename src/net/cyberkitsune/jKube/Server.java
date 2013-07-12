package net.cyberkitsune.jKube;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

import net.cyberkitsune.jCube.protocol.NetworkProtocol;
import net.cyberkitsune.jCube.protocol.Version3;

public class Server {

	private static final Logger log = Logger.getLogger(Server.class.getName());
	public NetworkProtocol activeProto = new Version3(this);
	boolean running = true;
	int numConnections = -1;
	
	static {
		LogFormatter formatter = new LogFormatter();
		ConsoleHandler cH = new ConsoleHandler();
		cH.setFormatter(formatter);
		getLog().setUseParentHandlers(false);
		getLog().addHandler(cH);
	}
	
	public void run() throws IOException {
		getLog().info("Starting Server on Port 12345");
		ServerSocket listener = new ServerSocket(12345);
		try {
			getLog().info("Server running. Waiting for connection...");
			while(running) {
				new ClientConnection(listener.accept(), this, numConnections++).start();
			}
		} catch (Exception e) {
			
		} finally {
			listener.close();
		}
	}

	public static Logger getLog() {
		return log;
	}
	
}
