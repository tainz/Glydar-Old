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

    public static void main(String[] args) {

        //TODO Argument Processing

        try {
            server = new CWServer();

            server.startServer();


            while (server.isRunning()) {

            }

            server.getLogger().info("Server shutting down.");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static CWServer getServer() {
        return server;
    }

}
