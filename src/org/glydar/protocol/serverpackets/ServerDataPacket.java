package org.glydar.protocol.serverpackets;

import org.glydar.packets.*;
import org.glydar.packets.annotations.PacketStruct;

import java.util.ArrayList;

public class ServerDataPacket extends WritablePacket {

    @PacketStruct(index = 0)
    private int unknown0;

    @PacketStruct(index = 1)
    private long entityId;

    @PacketStruct(index = 2, length = 4456)
    private byte emptyData;

    public ServerDataPacket() {
        super(ServerPacketType.ServerData.getId());
    }

    public int getUnknown0() {
        return unknown0;
    }

    public void setUnknown0(int unknown0) {
        this.unknown0 = unknown0;
    }

    public long getEntityId() {
        return entityId;
    }

    public void setEntityId(long entityId) {
        this.entityId = entityId;
    }

    public byte getEmptyData() {
        return emptyData;
    }

    public void setEmptyData(byte emptyData) {
        this.emptyData = emptyData;
    }
}
