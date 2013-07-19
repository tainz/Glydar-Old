package org.glydar.protocol.serverpackets;

import org.glydar.packets.*;

public class ServerChatPacket extends Packet {

    private static PacketStructure structure = new PacketStructure();

    static {
        structure.addDataType(new PacketDataType(Integer.class)); //EntityID
        structure.addDataType(new PacketDataType(Integer.class)); //Length
        structure.addDataType(new PacketDataType(String.class, true)); //Message
    }

    public ServerChatPacket() {
        super(ServerPacketType.ServerChatMessage.getId(), null);
        data = new StructuredPacketData(structure);
    }

    public ServerChatPacket setMessage(String message, int entId) {
        ((StructuredPacketData)data).setDataAtStructureIndex(0, entId);
        ((StructuredPacketData)data).setDataAtStructureIndex(1, message.length());
        ((StructuredPacketData)data).setDataAtStructureIndex(2, message);
        return this;
    }
}
