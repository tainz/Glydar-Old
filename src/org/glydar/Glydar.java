package org.glydar;

/**
 * JKube - Java CubeWorld Server
 * TODO List (In order of importance)
 * * Make Packet Operations less stupid. (Migrate to JavaNIO)
 * * Structures
 * * Packet Logic / Connection Management
 * * Server... stuff? (Map simulation, entity management, etc)
 * * World Generation
 * * World parsing / saving
 * * Event Management
 * * APIs / Plugins
 * @author Sean McClenaghan, Doridian
 *
 */

public class Glydar
{
	
	public static void main(String[] args)
	{
		
		//TODO Argument Processing
		
		CWServer srv = new CWServer();
		
		try
		{
			srv.run();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return;
	}
	
}
