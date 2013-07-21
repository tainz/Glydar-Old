package org.glydar;

import java.util.Arrays;

import org.glydar.network.CWServer;
import org.glydar.util.ArgumentParser;

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
        try {
            server = new CWServer();
            server.startServer();
            
            ArgumentParser.parse(args);
            
            if (debugmode = true) {
    			System.out.println("Debug mode enabled. Only intended for developers");
            }
            
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
