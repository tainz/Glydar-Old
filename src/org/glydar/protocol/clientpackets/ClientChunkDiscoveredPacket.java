package org.glydar.protocol.clientpackets;

import java.util.ArrayList;

import org.glydar.packets.ClientPacketType;
import org.glydar.packets.Packet;
import org.glydar.packets.PacketDataType;
import org.glydar.packets.PacketStructure;

public class ClientChunkDiscoveredPacket extends Packet {
	private static ArrayList<PacketStructure> structures = new ArrayList<PacketStructure>();
	static {
		PacketStructure structure = new PacketStructure();
		structure.addDataType(new PacketDataType(Byte.class, 8)); // TODO Split,
																	// etc
		structures.add(structure);
	}

	public ClientChunkDiscoveredPacket(byte[] data) {
		super(ClientPacketType.ChunkDiscovered.getId(), null);
	}

	public static ArrayList<PacketStructure> getStructures() {
		return structures;
	}

}
