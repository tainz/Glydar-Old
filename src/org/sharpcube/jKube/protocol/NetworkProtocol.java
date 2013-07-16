package org.sharpcube.jKube.protocol;

import java.io.IOException;
import java.net.Socket;

import org.sharpcube.jKube.Server;
import org.sharpcube.jKube.packets.Packet;

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
	public void handleSendPacket1(Socket context); // Unknown
	public void handleSendPacket2(Socket context); // Unknown
	public void handleSendPacket3(Socket context); // Unknown
	public void handleSendPacket4(Socket context); // Unknown
	public void handleSendPacket5(Socket context); // Unknown
	public void handleSendPacket10(Socket context); // Chat Message
	public void handleSendPacket15(Socket context); // Seed Data
	public void handleSendPacket16(Socket context); // Connection Data
	public void handleSendPacket17(Socket context); // Server full error
	public void handleSendPacket18(Socket context); // Version mismatch error
}
