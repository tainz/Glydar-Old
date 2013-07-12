package net.cyberkitsune.jCube.protocol;

import net.cyberkitsune.jCube.Server;
import net.cyberkitsune.jCube.packets.Packet;
import net.cyberkitsune.jCube.packets.PacketArgs;

public interface NetworkProtocol {

	// Server instances
	public Server getServer();
	public void setServer(Server s);
	
	// Handler Wrappers
	public void handleIncomingPacket(Packet p);
	public Packet createOutgoingPacket(int id, PacketArgs args);
	
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
	public Packet handleSendPacket1(PacketArgs a); // Unknown
	public Packet handleSendPacket2(PacketArgs a); // Unknown
	public Packet handleSendPacket3(PacketArgs a); // Unknown
	public Packet handleSendPacket4(PacketArgs a); // Unknown
	public Packet handleSendPacket5(PacketArgs a); // Unknown
	public Packet handleSendPacket10(PacketArgs a); // Chat Message
	public Packet handleSendPacket15(PacketArgs a); // Seed Data
	public Packet handleSendPacket16(PacketArgs a); // Connection Data
	public Packet handleSendPacket17(); // Server full error
	public Packet handleSendPacket18(); // Version mismatch error
}
