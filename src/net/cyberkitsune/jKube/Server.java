package net.cyberkitsune.jKube;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

import net.cyberkitsune.jKube.protocol.NetworkProtocol;
import net.cyberkitsune.jKube.protocol.Version3;

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
		ListenThread lT = new ListenThread(this);
		getLog().info("Starting Server on Port 12345");
		lT.start();
		getLog().info("Server running. Enter Q to quit.");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(running) {
			handleCommand(br.readLine());
		}
		lT.interrupt(); // Stops the listener thread.
		//TODO: Save map, cleanup, etc.
		return;
	}
	
	//TODO Make this bool.
	public void handleCommand(String cmd) {
		switch (cmd.toLowerCase()) {
		case "q":
			getLog().info("Quitting server...");
			running = false;
			break;

		default:
			getLog().info("Unknown Command: "+cmd);
			break;
		}
	}

	public static Logger getLog() {
		return log;
	}
	
}
