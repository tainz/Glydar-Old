package org.glydar.packets.creators;

import java.util.ArrayList;

import org.glydar.packets.ClientPacketType;
import org.glydar.packets.IPacketCreator;
import org.glydar.packets.Packet;
import org.glydar.packets.PacketStructure;
import org.glydar.protocol.clientpackets.ClientEntityUpdatePacket;

public class ClientEntityUpdatePacketCreator implements IPacketCreator {

	@Override
	public ArrayList<PacketStructure> getStructures() {
		return ClientEntityUpdatePacket.getCompressedStructures();
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
