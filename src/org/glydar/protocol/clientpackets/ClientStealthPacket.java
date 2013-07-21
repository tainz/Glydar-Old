package org.glydar.protocol.clientpackets;

import org.glydar.packets.*;

import java.util.ArrayList;

public class ClientStealthPacket extends Packet{
    private static ArrayList<PacketStructure> structures = new ArrayList<PacketStructure>();
    static {
        PacketStructure structure = new PacketStructure();
        structure.addDataType(new PacketDataType(Byte.class, 40));
        structures.add(structure);
    }
    public ClientStealthPacket(byte[] data) {
        super(ClientPacketType.Unknown8.getId(), null);
    }

    public static ArrayList<PacketStructure> getStructures() {
        return structures;
    }
}
