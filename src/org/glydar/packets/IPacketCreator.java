package org.glydar.packets;

/**
 * Creates and checks packets
 * @author JohSketch
 */
public interface IPacketCreator
{
	/***
	 * 
	 * Get the length of the packet (in bytes)
	 * 
	 * @param id The id of the packet
	 * @return The packet length (in bytes)
	 */
	int getPacketLength(int id);
	
	/**
	 * 
	 * Create a packet with the specified id and data
	 * 
	 * @param id The id of the packet
	 * @param data The packet data
	 * @return A Packet Object
	 */
	Packet createPacket(int id, byte[] data) throws Exception;
}
