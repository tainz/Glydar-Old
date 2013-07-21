package org.glydar.protocol.serverpackets;

import org.glydar.packets.*;

import java.util.ArrayList;

public class ServerChatPacket extends StructuredPacket {

    private static ArrayList<PacketStructure> structures;

    static {

        structures = new ArrayList<PacketStructure>();

        PacketStructure structure = new PacketStructure();

        structure.addDataType(new PacketDataType(Integer.class)); //EntityID
        structure.addDataType(new PacketDataType(Integer.class)); //Length
        structure.addDataType(new PacketDataType(String.class)); //Message

        structures.add(structure);

    }

    public ServerChatPacket() {

        super(ServerPacketType.ServerChatMessage.getId());

        data = new StructuredPacketData(structures);

    }

    public ServerChatPacket setMessage(String message, int entId) throws Exception{

        StructuredPacketData spd = (StructuredPacketData)data;
        //String message2 = new String(message.getBytes(), "UTF-16LE");
        spd.setDataAtStructureIndex(0, 0, entId);
        spd.setDataAtStructureIndex(0, 1, message.length());
        spd.setDataAtStructureIndex(0, 2, message);

        return this;

    }

    public static ArrayList<PacketStructure> getStructures() {
        return structures;
    }

}
