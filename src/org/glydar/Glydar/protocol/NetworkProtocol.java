package org.glydar.Glydar.protocol;

import java.io.IOException;
import java.net.Socket;
import java.nio.channels.SocketChannel;

import org.glydar.Glydar.Server;
import org.glydar.Glydar.packets.Packet;

public interface NetworkProtocol {

	// Server instances
	public Server getServer();
	public void setServer(Server s);
	
	// Handler Wrappers
	public void handleIncomingPacket(Packet p);
	public void sendOutgoingPacket(Packet p) throws IOException;
	
	// Incoming packets
	public void handleReadPacket0(Packet p);
	public void handleReadPacket6(Packet p); // Unknown
	public void handleReadPacket7(Packet p); // Unknown
	public void handleReadPacket8(Packet p); // Unknown
	public void handleReadPacket9(Packet p); // Unknown
	public void handleReadPacket10(Packet p); // Chat message
	public void handleReadPacket11(Packet p); // Unknown
	public void handleReadPacket12(Packet p); // Unknown
	public void handleReadPacket17(Packet p); // Handshake
	
	// Outgoing packets 
	public void handleSendPacket1(SocketChannel context); // Unknown
	public void handleSendPacket2(SocketChannel context); // Unknown
	public void handleSendPacket3(SocketChannel context); // Unknown
	public void handleSendPacket4(SocketChannel context); // Unknown
	public void handleSendPacket5(SocketChannel context); // Unknown
	public void handleSendPacket10(SocketChannel context); // Chat Message
	public void handleSendPacket15(SocketChannel context); // Seed Data
	public void handleSendPacket16(SocketChannel context); // Connection Data
	public void handleSendPacket17(SocketChannel context); // Server full error
	public void handleSendPacket18(SocketChannel context); // Version mismatch error
}
