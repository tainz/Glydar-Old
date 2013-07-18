package org.glydar.protocol.clientpackets;

import org.glydar.packets.*;

public class ClientVersionPacket extends Packet {

    private static PacketStructure structure;

    static {
        structure = new PacketStructure();
        structure.addDataType(new PacketDataType(Integer.class));
    }

    public ClientVersionPacket(byte[] data) {
        super(ClientPacketType.ClientVersion.getId(), new StructuredPacketData(structure).setDataAtStructureIndex(0, data));
    }

    public int getVersion() {
        return data.getDataAtIndex(Integer.class, structure.getLengthFromIndex(0));
    }
    
    public void setVersion(int version) {
    	((StructuredPacketData)data).setDataAtStructureIndex(0, version);
    }

    public static PacketStructure getStructure() {
        return structure;
    }

}
