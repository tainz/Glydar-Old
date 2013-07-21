package org.glydar.protocol.serverpackets;

import org.glydar.packets.Packet;
import org.glydar.packets.PacketDataType;
import org.glydar.packets.PacketStructure;
import org.glydar.packets.ServerPacketType;
import org.glydar.packets.StructuredPacketData;

public class ServerSeedPacket extends Packet {

	private static PacketStructure structure;

	static {
		structure = new PacketStructure();
		structure.addDataType(new PacketDataType(Integer.class));
	}

	public ServerSeedPacket() throws Exception {
		super(ServerPacketType.SeedData.getId(), null);
		data = new StructuredPacketData(structure);
	}

	public ServerSeedPacket setSeed(int seed) {
		// TODO Wrap seed if too big

		StructuredPacketData spd = (StructuredPacketData) data;

		spd.setDataAtStructureIndex(0, 0, seed);

		return this;

	}

}
