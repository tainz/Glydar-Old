package org.glydar.protocol.clientpackets;

import org.glydar.packets.*;

public class ClientVersionPacket extends Packet {

    private static PacketStructure structure;

    static {
        structure = new PacketStructure();
        structure.addDataType(new PacketDataType(Integer.class));
    }

    public ClientVersionPacket(byte[] data) {
        super(ClientPacketType.ClientVersion.getId(), new PacketData(data));
    }

    public int getVersion() {
        return data.getDataAtIndex(Integer.class, 0);
    }

    public static PacketStructure getStructure() {
        return structure;
    }

}
