package org.glydar.packets.creators;

import org.glydar.packets.ClientPacketType;
import org.glydar.packets.IPacketCreator;
import org.glydar.packets.Packet;
import org.glydar.packets.PacketStructure;
import org.glydar.protocol.clientpackets.ClientEntityEchoPacket;
import org.glydar.protocol.clientpackets.ClientEntityUpdatePacket;

import java.util.ArrayList;

public class ClientEntityUpdateEchoCreator implements IPacketCreator {
    @Override
    public ArrayList<PacketStructure> getStructures() {
        return ClientEntityEchoPacket.getStructures();
    }

    @Override
    public int getPacketId() {
        return ClientPacketType.EntityUpdate.getId();
    }

    @Override
    public Packet createPacket(byte[] data) throws Exception {
        return new ClientEntityUpdatePacket(data);
    }
}
