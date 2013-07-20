package org.glydar.packets.creators;

import org.glydar.packets.ClientPacketType;
import org.glydar.packets.IPacketCreator;
import org.glydar.packets.Packet;
import org.glydar.packets.PacketStructure;
import org.glydar.protocol.clientpackets.ClientChatPacket;

import java.util.ArrayList;
import java.util.Arrays;

public class ClientChatPacketCreator implements IPacketCreator {

    @Override
    public ArrayList<PacketStructure> getStructures() {
        return ClientChatPacket.getStructures();
    }

    @Override
    public int getPacketId() {
        return ClientPacketType.ClientChatMessage.getId();
    }

    @Override
    public Packet createPacket(byte[] data) throws Exception {
        return new ClientChatPacket(data);
    }
}
