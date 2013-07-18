package org.glydar.packets;

import org.glydar.network.GlydarClient;

public interface IPacketHandler {

    void handlePacket(GlydarClient client, Packet packet) throws Exception;

    int getPacketId();

}
