package org.glydar.packets;

import java.util.ArrayList;

public interface IPacketCreator {

	ArrayList<PacketStructure> getStructures();

	int getPacketId();

	Packet createPacket(byte[] data) throws Exception;

}
