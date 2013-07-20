package org.glydar.protocol.serverpackets;

import org.glydar.packets.*;
import org.glydar.util.MiscUtil;

import java.util.ArrayList;

public class ServerDataPacket extends StructuredPacket {

    private static ArrayList<PacketStructure> structures;

	static {

        structures = new ArrayList<PacketStructure>();

		PacketStructure structure = new PacketStructure();
		structure.addDataType(new PacketDataType(Integer.class));
		structure.addDataType(new PacketDataType(Long.class));
		structure.addDataType(new PacketDataType(Byte.class, 4456));

        structures.add(structure);

	}

	public ServerDataPacket() {

		super(ServerPacketType.ServerData.getId());

        this.data = new StructuredPacketData(structures);
	}

	public ServerDataPacket setEntityId(long entId) {

        StructuredPacketData spd = (StructuredPacketData)data;

        spd.setDataAtStructureIndex(0, 1, entId);
//        spd.setDataAtStructureIndex(0, 1, MiscUtil.getEmptyByteArray(4456));

		return this;

	}

    public static ArrayList<PacketStructure> getStructures() {
        return structures;
    }

}
