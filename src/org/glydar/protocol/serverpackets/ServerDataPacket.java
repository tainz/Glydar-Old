package org.glydar.protocol.serverpackets;

import org.glydar.packets.*;

public class ServerDataPacket extends Packet {

    private static PacketStructure structure;

    static {

        structure = new PacketStructure();
        structure.addDataType(new PacketDataType(Integer.class));
        structure.addDataType(new PacketDataType(Long.class));
        structure.addDataType(new PacketDataType(Byte.class, 4456));


    }

    public ServerDataPacket() {

        super(ServerPacketType.ServerData.getId(), null);

        data = new PacketData(structure);
        data.setDataAtIndex(structure.getLengthFromIndex(0), 0);

    }

    public ServerDataPacket setEntityId(long entId) {

        data.setDataAtIndex(structure.getLengthFromIndex(1), entId);
        data.setDataAtIndex(structure.getLengthFromIndex(2), 0);

        return this;

    }

}
