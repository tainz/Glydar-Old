package org.glydar.protocol.clientpackets;

import io.netty.buffer.ByteBuf;
import org.glydar.packets.*;
import org.glydar.util.MiscUtil;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;

public class ClientEntityEchoPacket extends Packet {
    private static ArrayList<PacketStructure> structures = new ArrayList<PacketStructure>();
    static {
        PacketStructure structure = new PacketStructure();
        structure.addDataType(new PacketDataType(Integer.class));
        structure.addDataType(new PacketDataType(Byte.class, true));
        structures.add(structure);
    }


    public ClientEntityEchoPacket(byte[] data) {
        super(ClientPacketType.EntityUpdate.getId(), null);
        this.data = new RawPacketData(data);

    }

    public ClientEntityEchoPacket convertToEncode() {
        this.data = new RawPacketData(ByteBuffer.wrap(this.data.getByteData()).order(ByteOrder.BIG_ENDIAN).array());
        return this;
    }

    public static ArrayList<PacketStructure> getStructures() {
        return structures;
    }
}
