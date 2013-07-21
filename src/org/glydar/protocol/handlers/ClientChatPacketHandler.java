package org.glydar.protocol.handlers;

import org.glydar.Glydar;
import org.glydar.network.GlydarClient;
import org.glydar.packets.ClientPacketType;
import org.glydar.packets.IPacketHandler;
import org.glydar.packets.Packet;
import org.glydar.packets.PacketHandler;
import org.glydar.protocol.clientpackets.ClientChatPacket;
import org.glydar.protocol.serverpackets.ServerChatPacket;

public class ClientChatPacketHandler extends PacketHandler {

    public ClientChatPacketHandler() {
        super(ClientPacketType.ClientChatMessage.getId());
    }

    @Override
    public void handlePacket(GlydarClient client, Packet packet) throws Exception {
        super.handlePacket(client, packet);
        String message = ((ClientChatPacket)packet).getMessage();
        if(message != null) {
            Glydar.getServer().getLogger().info("(CHAT " + client.getId() + ") "+message);
            for(GlydarClient g : Glydar.getServer().getClients()) {
                g.getSocketChannel().write(new ServerChatPacket().setMessage(message, client.getId()));
            }
        } else {
            Glydar.getServer().getLogger().info("Got null packet!");
        }
    }
}
