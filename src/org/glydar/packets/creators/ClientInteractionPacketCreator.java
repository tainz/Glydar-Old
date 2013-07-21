package org.glydar.packets.creators;

import org.glydar.packets.ClientPacketType;
import org.glydar.packets.IPacketCreator;
import org.glydar.packets.Packet;
import org.glydar.packets.PacketStructure;
import org.glydar.protocol.clientpackets.ClientInteractionPacket;

import java.util.ArrayList;

public class ClientInteractionPacketCreator implements IPacketCreator{
    @Override
    public ArrayList<PacketStructure> getStructures() {
        return ClientInteractionPacket.getStructures();
    }

    @Override
    public int getPacketId() {
        return ClientPacketType.InteractPacket.getId();
    }

    @Override
    public Packet createPacket(byte[] data) throws Exception {
        return new ClientInteractionPacket(data);
    }
}
