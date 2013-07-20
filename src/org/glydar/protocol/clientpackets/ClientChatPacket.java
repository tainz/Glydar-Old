package org.glydar.protocol.clientpackets;

import org.glydar.Glydar;
import org.glydar.packets.*;

import java.nio.ByteBuffer;
import java.util.ArrayList;

public class ClientChatPacket extends StructuredPacket {

    private static ArrayList<PacketStructure> structures;

    static {

        structures = new ArrayList<PacketStructure>();

        PacketStructure structure = new PacketStructure();
        structure.addDataType(new PacketDataType(Integer.class));
        structure.addDataType(new PacketDataType(String.class, true));

        structures.add(structure);

    }

    public ClientChatPacket(byte[] data) throws Exception {

        super(ClientPacketType.ClientChatMessage.getId());

        this.data = new StructuredPacketData(structures);

        this.data.addData(data);

    }

    public String getMessage() {
//        try {
//            return new String(data.getDataAtIndex(1, ByteBuffer.wrap(data.getDataAtIndex(1, structure.getLengthFromIndex(0))).getInt()));
//        } catch (Exception e) {
//            Glydar.getServer().getLogger().info("ERROR Getting string!");
//            e.printStackTrace();
//        }

        return "";
    }

    public static ArrayList<PacketStructure> getStructures() {
        return structures;
    }

}
