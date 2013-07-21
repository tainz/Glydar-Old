package org.glydar.packets.creators;

import org.glydar.packets.ClientPacketType;
import org.glydar.packets.IPacketCreator;
import org.glydar.packets.Packet;
import org.glydar.packets.PacketStructure;
import org.glydar.protocol.clientpackets.ClientShootArrowPacket;

import java.util.ArrayList;

public class ClientShootArrowPacketCreator implements IPacketCreator{
    @Override
    public ArrayList<PacketStructure> getStructures() {
        return ClientShootArrowPacket.getStructures();
    }

    @Override
    public int getPacketId() {
        return ClientPacketType.ShootPacket.getId();
    }

    @Override
    public Packet createPacket(byte[] data) throws Exception {
        return new ClientShootArrowPacket(data);
    }
}
