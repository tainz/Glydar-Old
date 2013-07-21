package org.glydar.packets.creators;

import java.util.ArrayList;

import org.glydar.packets.ClientPacketType;
import org.glydar.packets.IPacketCreator;
import org.glydar.packets.Packet;
import org.glydar.packets.PacketStructure;
import org.glydar.protocol.clientpackets.ClientChunkDiscoveredPacket;

public class ClientChunkDiscoveredPacketCreator implements IPacketCreator {
	@Override
	public ArrayList<PacketStructure> getStructures() {
		return ClientChunkDiscoveredPacket.getStructures();
	}

	@Override
	public int getPacketId() {
		return ClientPacketType.ChunkDiscovered.getId();
	}

	@Override
	public Packet createPacket(byte[] data) throws Exception {
		return new ClientChunkDiscoveredPacket(data);
	}
}
