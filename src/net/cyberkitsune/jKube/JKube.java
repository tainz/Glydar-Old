package net.cyberkitsune.jKube;

/**
 * JKube - Java CubeWorld Server
 * TODO List (In order of importance)
 * * Structures
 * * Packet Logic
 * * Server... stuff? (Map simulation, entity management, etc)
 * * World Generation
 * * World parsing / saving
 * * Event Management
 * * APIs / Plugins
 * @author Sean McClenaghan, Doridian
 *
 */

public class JKube {

	public static void main(String[] args) {
	
		//TODO Argument Processing
		
		
		Server srv = new Server();
		try {
			srv.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}

}
