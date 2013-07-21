package org.glydar.protocol.readers;

import io.netty.buffer.ByteBuf;
import org.glydar.packets.ClientPacketType;
import org.glydar.packets.Packet;
import org.glydar.packets.PacketReader;
import org.glydar.protocol.clientpackets.ClientVersionPacket;

import java.nio.ByteBuffer;

public class ClientVersionPacketReader extends PacketReader {

    @Override
    public int getPacketId() {
        return ClientPacketType.ClientVersion.getId();
    }

    @Override
    public ClientVersionPacket readPacket(ByteBuffer buffer) {

        ClientVersionPacket cvp = new ClientVersionPacket();

        cvp.setVersion(buffer.getInt()); //client version

        return cvp;

    }

    @Override
    public Class<?> getPacketClass() {
        return ClientVersionPacket.class;
    }

}
