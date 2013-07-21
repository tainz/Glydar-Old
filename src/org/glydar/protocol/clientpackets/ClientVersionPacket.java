package org.glydar.protocol.clientpackets;

import java.util.ArrayList;

import org.glydar.packets.ClientPacketType;
import org.glydar.packets.PacketDataType;
import org.glydar.packets.PacketStructure;
import org.glydar.packets.StructuredPacket;
import org.glydar.packets.StructuredPacketData;

public class ClientVersionPacket extends StructuredPacket {

	private static ArrayList<PacketStructure> structures;

	static {

		structures = new ArrayList<PacketStructure>();

		PacketStructure structure = new PacketStructure();
		structure.addDataType(new PacketDataType(Integer.class));

		structures.add(structure);

	}

	public ClientVersionPacket(byte[] data) {

		super(ClientPacketType.ClientVersion.getId());

		this.data = new StructuredPacketData(structures);

		StructuredPacketData spd = (StructuredPacketData) this.data;

		spd.setDataAtStructureIndex(0, spd.getPacketStructure(0)
				.getLengthFromIndex(0), data);

	}

	public int getVersion() throws Exception {

		StructuredPacketData spd = (StructuredPacketData) data;

		return spd.getStructuredDataAtIndex(Integer.class, 0, 0);

	}

	public void setVersion(int version) {
		((StructuredPacketData) data).setDataAtStructureIndex(0, 0, version);
	}

	public static ArrayList<PacketStructure> getStructures() {
		return structures;
	}

}
