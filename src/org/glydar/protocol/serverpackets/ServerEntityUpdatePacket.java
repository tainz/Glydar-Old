package org.glydar.protocol.serverpackets;

import java.io.IOException;

import org.glydar.packets.Packet;
import org.glydar.packets.PacketDataType;
import org.glydar.packets.PacketStructure;
import org.glydar.packets.RawPacketData;
import org.glydar.packets.ServerPacketType;
import org.glydar.packets.StructuredPacketData;
import org.glydar.util.ZLibUtil;

public class ServerEntityUpdatePacket extends Packet {
	private static PacketStructure structure;
	static {
		structure = new PacketStructure();
		structure.addDataType(new PacketDataType(Byte[].class, true)); //Data
	}
	
	public ServerEntityUpdatePacket() {
		super(ServerPacketType.EntityUpdate.getId(), null);
		data = new StructuredPacketData(structure);
	}
	
	public ServerEntityUpdatePacket compress() throws IOException {
		
		data = new RawPacketData(ZLibUtil.compress(data.getByteData()));
		
		return this;
		
	}
	
	//temporary
	public ServerEntityUpdatePacket setData(byte[] data) throws IOException {
		
		this.data = new RawPacketData(ZLibUtil.compress(data));
		
		return this;
		
	}
	
}
