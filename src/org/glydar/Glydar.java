package org.glydar;

import org.glydar.network.CWServer;
import org.glydar.util.ArgumentParser;
import org.glydar.util.LogUtil;

/**
 * Glydar: Java CubeWorld Server
 * @author Glydar Team
 */
public class Glydar {
	private static CWServer server;
	private static LogUtil log;
	public static boolean debugmode;

	public static void main(String[] args) {
		try {
			server = new CWServer();
			server.startServer();
			log = new LogUtil();

			ArgumentParser.parse(args);

			if (debugmode = true) {
				log.output("Debug mode enabled. Only intended for developers");
			}

			while (server.isRunning()) {
			}
			log.output("Server shutting down");
			System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static CWServer getServer() {
		return server;
	}
}
