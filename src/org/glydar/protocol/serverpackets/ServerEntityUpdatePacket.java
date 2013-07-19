package org.glydar.protocol.serverpackets;

import org.glydar.packets.*;

public class ServerEntityUpdatePacket extends Packet {
    private static PacketStructure structure;
    static {
        structure = new PacketStructure();
        structure.addDataType(new PacketDataType(Integer.class)); //Length
        structure.addDataType(new PacketDataType(Byte.class, true)); //Data
    }

    public ServerEntityUpdatePacket() {
        super(ServerPacketType.EntityUpdate.getId(), null);
        data = new StructuredPacketData(structure);
    }

    public ServerEntityUpdatePacket setCompressedData(byte[] compressedData) {
        ((StructuredPacketData)data).setDataAtStructureIndex(0, compressedData.length);
        ((StructuredPacketData)data).setDataAtStructureIndex(1, compressedData);
        return this;
    }
}
