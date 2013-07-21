package org.glydar;

import org.glydar.network.CWServer;

/**
 * Glydar - Java CubeWorld Server
 * TODO List (In order of importance)
 * See development board on private trello
 *
 * @author Glydar Team
 */

public class Glydar {

    private static CWServer server;
    public static boolean debugmode;

    public static void main(String[] args) {

		if (args.length > 0 && args[0].equals("-debug")) {
			debugmode = true;
            server.getLogger().info("Debug output enabled. Only for developer use.");
		}
		
        try {
            server = new CWServer();

            server.startServer();


            while (server.isRunning()) {

            }

            server.getLogger().info("Server shutting down.");
            System.exit(0);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static CWServer getServer() {
        return server;
    }

}
