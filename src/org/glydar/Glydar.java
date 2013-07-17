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
	static CWServer SERVER;
	
	public static void main(String[] args)
	{
		
		//TODO Argument Processing
		
		SERVER = new CWServer();
		
		try
		{
			SERVER.run();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return;
	}
	
	public static CWServer getServer() 
	{
		return SERVER;
	}
	
}
