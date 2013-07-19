package org.glydar.protocol.clientpackets;

import org.glydar.Glydar;
import org.glydar.packets.*;

import java.nio.ByteBuffer;

public class ClientChatPacket extends Packet{

    private static PacketStructure structure;
    static {
        structure = new PacketStructure();
        structure.addDataType(new PacketDataType(Integer.class));
        structure.addDataType(new PacketDataType(String.class, true));
    }

    public ClientChatPacket(byte[] data) throws Exception {
        super(ClientPacketType.ClientChatMessage.getId(), new StructuredPacketData(structure).setDataAtStructureIndex(0 , data));
    }

    public String getMessage() {
        try {
            return new String(data.getDataAtIndex(1, ByteBuffer.wrap(data.getDataAtIndex(1, structure.getLengthFromIndex(0))).getInt()));
        } catch (Exception e) {
            Glydar.getServer().getLogger().info("ERROR Getting string!");
            e.printStackTrace();
        }

        return null;
    }

    public static PacketStructure getStructure() {
        return structure;
    }
}
