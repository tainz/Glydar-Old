package org.glydar.protocol.clientpackets;

import org.glydar.packets.*;

import java.util.ArrayList;

public class ClientShootArrowPacket extends Packet{
    private static ArrayList<PacketStructure> structures = new ArrayList<PacketStructure>();
    static {
        PacketStructure structure = new PacketStructure();
        structure.addDataType(new PacketDataType(Byte.class, 112)); //TODO Proper Structure
        structures.add(structure);
    }
    public ClientShootArrowPacket(byte[] data) {
        super(ClientPacketType.ShootPacket.getId(), null);
    }

    public static ArrayList<PacketStructure> getStructures() {
        return structures;
    }
}
