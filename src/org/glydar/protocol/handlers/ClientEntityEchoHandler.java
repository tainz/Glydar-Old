package org.glydar.protocol.handlers;

import org.glydar.network.GlydarClient;
import org.glydar.packets.ClientPacketType;
import org.glydar.packets.Packet;
import org.glydar.packets.PacketHandler;
import org.glydar.protocol.clientpackets.ClientEntityEchoPacket;

public class ClientEntityEchoHandler extends PacketHandler {

    public ClientEntityEchoHandler() {
        super(ClientPacketType.EntityUpdate.getId());
    }

    @Override
    public void handlePacket(GlydarClient client, Packet packet) throws Exception {
        super.handlePacket(client, packet);
        client.getSocketChannel().write(packet); //Don't even bother with serverside packet structures.
    }
}
