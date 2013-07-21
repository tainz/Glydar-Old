package org.glydar.protocol.clientpackets;

import java.util.ArrayList;

import org.glydar.packets.ClientPacketType;
import org.glydar.packets.Packet;
import org.glydar.packets.PacketDataType;
import org.glydar.packets.PacketStructure;
import org.glydar.packets.RawPacketData;

public class ClientEntityEchoPacket extends Packet {
	private static ArrayList<PacketStructure> structures = new ArrayList<PacketStructure>();
	static {
		PacketStructure structure = new PacketStructure();
		structure.addDataType(new PacketDataType(Byte.class, true));
		structures.add(structure);
	}

	public ClientEntityEchoPacket(byte[] data) {
		super(ClientPacketType.EntityUpdate.getId(), null);
		this.data = new RawPacketData(data);

	}

	public static ArrayList<PacketStructure> getStructures() {
		return structures;
	}
}
