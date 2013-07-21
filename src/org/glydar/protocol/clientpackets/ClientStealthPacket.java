package org.glydar.protocol.clientpackets;

import java.util.ArrayList;

import org.glydar.packets.ClientPacketType;
import org.glydar.packets.Packet;
import org.glydar.packets.PacketDataType;
import org.glydar.packets.PacketStructure;

public class ClientStealthPacket extends Packet {
	private static ArrayList<PacketStructure> structures = new ArrayList<PacketStructure>();
	static {
		PacketStructure structure = new PacketStructure();
		structure.addDataType(new PacketDataType(Byte.class, 40));
		structures.add(structure);
	}

	public ClientStealthPacket(byte[] data) {
		super(ClientPacketType.Unknown8.getId(), null);
	}

	public static ArrayList<PacketStructure> getStructures() {
		return structures;
	}
}
