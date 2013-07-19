package org.glydar.protocol.handlers;

import org.glydar.Glydar;
import org.glydar.network.GlydarClient;
import org.glydar.packets.ClientPacketType;
import org.glydar.packets.Packet;
import org.glydar.packets.PacketHandler;
import org.glydar.protocol.clientpackets.ClientVersionPacket;
import org.glydar.protocol.serverpackets.*;

public class ClientVersionPacketHandler extends PacketHandler {

    public ClientVersionPacketHandler() {
        super(ClientPacketType.ClientVersion.getId());
    }

    @Override
    public void handlePacket(GlydarClient client, Packet packet) throws Exception {

        super.handlePacket(client, packet);

        //System.out.println("Client Version Packet Received!");
        //System.out.println("Version: " + ((ClientVersionPacket) packet).getVersion());

        if (Glydar.getServer().getCurrentProtocolVersion() != ((ClientVersionPacket) packet).getVersion()) {
            System.out.println("Wrong Version");
            client.getSocketChannel().write(new ServerMismatchPacket().setVersion(Glydar.getServer().getCurrentProtocolVersion()));
        } else if (Glydar.getServer().getClients().size() > Glydar.getServer().getMaxPlayers()) {
            System.out.println("Server Full");
            client.getSocketChannel().write(new ServerFullPacket());
        } else {
            client.getSocketChannel().write(new ServerDataPacket().setEntityId(1));
            client.getSocketChannel().write(new ServerSeedPacket().setSeed(Glydar.getServer().getSeed()));
            client.getSocketChannel().write(new ServerChatPacket().setMessage("Welcome to the server!",0));
        }

    }

}
