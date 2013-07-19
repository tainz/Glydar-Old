package org.glydar.protocol.handlers;

import org.glydar.network.GlydarClient;
import org.glydar.packets.ClientPacketType;
import org.glydar.packets.Packet;
import org.glydar.packets.PacketHandler;
import org.glydar.packets.StructuredPacketData;
import org.glydar.protocol.clientpackets.ClientEntityUpdatePacket;
import org.glydar.protocol.serverpackets.ServerEnitityUpdateFinishedPacket;
import org.glydar.protocol.serverpackets.ServerEntityUpdatePacket;
import org.glydar.util.ZLibUtil;

public class ClientEntityUpdatePacketHandler extends PacketHandler {
	
	public ClientEntityUpdatePacketHandler() {
		super(ClientPacketType.EntityUpdate.getId());
	}
	
	@Override
	public void handlePacket(GlydarClient client, Packet packet) throws Exception {
		
		byte[] deData = ZLibUtil.decompress(packet.getData().getByteData());
		int deDataLength = deData.length;
		
		Long conId = 1L;
		byte conByte = conId.byteValue();
		
		byte[] newData = new byte[deDataLength + 1];
		for (int i = 0; i < deDataLength ; i++){
			newData[i] = deData[i];
		}
		newData[deDataLength] = conByte;
		
		client.getSocketChannel().write(new ServerEntityUpdatePacket().setData(newData));
		client.getSocketChannel().write(new ServerEnitityUpdateFinishedPacket());
		
	}
	
}
