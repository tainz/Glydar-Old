package org.glydar.protocol.clientpackets;

import org.glydar.packets.*;
import org.glydar.packets.annotations.PacketStruct;

import java.util.ArrayList;

public class ClientVersionPacket extends Packet {

    @PacketStruct(index = 0)
    private int version;

    public ClientVersionPacket() {
        super(ClientPacketType.ClientVersion.getId());
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getVersion() {
        return version;
    }

}
