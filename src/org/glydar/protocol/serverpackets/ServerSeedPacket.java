package org.glydar.protocol.serverpackets;

import org.glydar.packets.*;

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
        //TODO Wrap seed if too big
        ((StructuredPacketData)data).setDataAtStructureIndex(0, seed);
        return this;
    }

}
