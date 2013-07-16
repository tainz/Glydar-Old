package org.glydar;

/**
 * Glydar - Java CubeWorld Server
 * TODO List (In order of importance)
 * See development board on private trello
 * @author Glydar Team
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
