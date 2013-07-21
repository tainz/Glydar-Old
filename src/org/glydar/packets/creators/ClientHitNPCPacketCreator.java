package org.glydar.packets.creators;

import java.util.ArrayList;

import org.glydar.packets.ClientPacketType;
import org.glydar.packets.IPacketCreator;
import org.glydar.packets.Packet;
import org.glydar.packets.PacketStructure;
import org.glydar.protocol.clientpackets.ClientHitNPCPacket;

public class ClientHitNPCPacketCreator implements IPacketCreator {
	@Override
	public ArrayList<PacketStructure> getStructures() {
		return ClientHitNPCPacket.getStructures();
	}

	@Override
	public int getPacketId() {
		return ClientPacketType.HitPacket.getId();
	}

	@Override
	public Packet createPacket(byte[] data) throws Exception {
		return new ClientHitNPCPacket(data);
	}
}
