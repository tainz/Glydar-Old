package org.glydar.protocol.clientpackets;

import java.util.ArrayList;

import org.glydar.Glydar;
import org.glydar.packets.ClientPacketType;
import org.glydar.packets.PacketDataType;
import org.glydar.packets.PacketStructure;
import org.glydar.packets.StructuredPacket;
import org.glydar.packets.StructuredPacketData;

public class ClientChatPacket extends StructuredPacket {

	private static ArrayList<PacketStructure> structures;

	static {

		structures = new ArrayList<PacketStructure>();

		PacketStructure structure = new PacketStructure();
		structure.addDataType(new PacketDataType(String.class, true));

		structures.add(structure);

	}

	public ClientChatPacket(byte[] data) throws Exception {

		super(ClientPacketType.ClientChatMessage.getId());

		this.data = new StructuredPacketData(structures);

		this.data.addData(data);

	}

	public String getMessage() {
		try {
			return new String(
					data.getDataAtIndex(0, data.getByteData().length),
					"UTF-16LE");
		} catch (Exception e) {
			Glydar.getServer().getLogger().info("ERROR Getting string!");
			e.printStackTrace();
		}

		return "";
	}

	public static ArrayList<PacketStructure> getStructures() {
		return structures;
	}

}
