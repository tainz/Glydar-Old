package org.glydar;

import java.util.Scanner;

import org.glydar.network.CWServer;

/**
 * Glydar - Java CubeWorld Server
 * TODO List (In order of importance)
 * See development board on private trello
 * @author Glydar Team
 *
 */

public class Glydar
{
	
	private static CWServer server;
	
	public static void main(String[] args)
	{
		
		//TODO Argument Processing
		
		server = new CWServer();
		
		server.startServer();
		
		String commandLine = null;
		
		Scanner in = new Scanner(System.in);
		
		while ((commandLine = in.nextLine()) != null)
		{
			
		}
		
		in.close();
		
	}
	
	public static CWServer getServer()
	{
		return server;
	}
	
}
