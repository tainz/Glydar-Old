package net.cyberkitsune.jCube;

public class JCube {

	public static void main(String[] args) {
	
		Server srv = new Server();
		try {
			srv.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
