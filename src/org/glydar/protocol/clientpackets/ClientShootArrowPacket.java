package org.glydar.protocol.clientpackets;

import java.util.ArrayList;

import org.glydar.packets.ClientPacketType;
import org.glydar.packets.Packet;
import org.glydar.packets.PacketDataType;
import org.glydar.packets.PacketStructure;

public class ClientShootArrowPacket extends Packet {
	private static ArrayList<PacketStructure> structures = new ArrayList<PacketStructure>();
	static {
		PacketStructure structure = new PacketStructure();
		structure.addDataType(new PacketDataType(Byte.class, 112)); // TODO
																	// Proper
																	// Structure
		structures.add(structure);
	}

	public ClientShootArrowPacket(byte[] data) {
		super(ClientPacketType.ShootPacket.getId(), null);
	}

	public static ArrayList<PacketStructure> getStructures() {
		return structures;
	}
}
