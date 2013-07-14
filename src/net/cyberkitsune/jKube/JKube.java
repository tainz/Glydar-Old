package net.cyberkitsune.jKube;

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
