package org.glydar.Glydar.packets;

import java.net.Socket;

public class Packet {
	
	public Socket context;
	
	public Packet(Socket context) {
		this.context = context;
	}
	
	public Byte[] raw;
	public int id; //4B
	public byte[] data;
}