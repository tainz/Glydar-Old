package org.glydar.protocol.serverpackets;


import org.glydar.packets.Packet;
import org.glydar.packets.PacketData;
import org.glydar.packets.ServerPacketType;

public class ServerEnitityUpdateFinishedPacket extends Packet{

    public ServerEnitityUpdateFinishedPacket() {
        super(ServerPacketType.UpdateFinished.getId(), null);
    }
}
