package org.glydar.protocol.clientpackets;

import org.glydar.packets.*;

import java.util.ArrayList;

public class ClientChunkDiscoveryPacket extends Packet{
    private static ArrayList<PacketStructure> structures = new ArrayList<PacketStructure>();
    static {
        PacketStructure structure = new PacketStructure();
        structure.addDataType(new PacketDataType(Byte.class, 8)); //TODO Split, etc
        structures.add(structure);
    }
    public ClientChunkDiscoveryPacket(byte[] data) {
        super(ClientPacketType.ChunkDiscovered.getId(), null);
    }

    public static ArrayList<PacketStructure> getStructures() {
        return structures;
    }

}
