package org.glydar.protocol.clientpackets;

import org.glydar.packets.*;

import java.util.ArrayList;

public class ClientInteractionPacket extends Packet{
    private static ArrayList<PacketStructure> structures = new ArrayList<PacketStructure>();
    static {
        PacketStructure structure = new PacketStructure();
        structure.addDataType(new PacketDataType(Byte.class, 298)); //TODO Properly fix datatype
        structures.add(structure);
    }


    public ClientInteractionPacket(byte[] data) {
        super(ClientPacketType.InteractPacket.getId(), null);
    }

    public static ArrayList<PacketStructure> getStructures() {
        return structures;
    }
}
