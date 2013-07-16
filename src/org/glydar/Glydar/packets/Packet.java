package org.glydar.Glydar.packets;

import java.net.Socket;
import java.nio.channels.SocketChannel;

public class Packet {
	
	public SocketChannel context;
	
	public Packet(SocketChannel context) {
		this.context = context;
	}
	
	public Byte[] raw;
	public int id; //4B
	public byte[] data;
}