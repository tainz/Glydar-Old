package org.glydar.protocol.clientpackets;

import java.util.ArrayList;

import org.glydar.packets.ClientPacketType;
import org.glydar.packets.Packet;
import org.glydar.packets.PacketDataType;
import org.glydar.packets.PacketStructure;

public class ClientInteractionPacket extends Packet {
	private static ArrayList<PacketStructure> structures = new ArrayList<PacketStructure>();
	static {
		PacketStructure structure = new PacketStructure();
		structure.addDataType(new PacketDataType(Byte.class, 298)); // TODO
																	// Properly
																	// fix
																	// datatype
		structures.add(structure);
	}

	public ClientInteractionPacket(byte[] data) {
		super(ClientPacketType.InteractPacket.getId(), null);
	}

	public static ArrayList<PacketStructure> getStructures() {
		return structures;
	}
}
