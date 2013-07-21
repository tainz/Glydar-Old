package org.glydar.packets;

import java.nio.ByteBuffer;

public abstract class PacketReader {

    public abstract Class<?> getPacketClass();

    public abstract int getPacketId();

    public abstract Packet readPacket(ByteBuffer buffer);

}
