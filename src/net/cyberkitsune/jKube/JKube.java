package net.cyberkitsune.jKube;

public class JKube {

	public static void main(String[] args) {
	
		Server srv = new Server();
		try {
			srv.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
