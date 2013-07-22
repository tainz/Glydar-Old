package org.glydar.protocol.serverpackets;

import org.glydar.packets.Packet;
import org.glydar.packets.ServerPacketType;
import org.glydar.packets.annotations.PacketStruct;

public class ServerSeedPacket extends Packet {

    public ServerSeedPacket() {
        super(ServerPacketType.SeedData.getId());
    }

    @PacketStruct(index = 0)
    private int seed;

    public int getSeed() {
        return seed;
    }

    public void setSeed(int seed) {
        this.seed = seed;
    }
}
