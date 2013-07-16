package org.glydar.Glydar;

import java.io.DataInputStream;
import java.net.Socket;
import java.nio.channels.SocketChannel;

import org.glydar.Glydar.packets.Packet;

public class ClientConnection extends Thread {
	
	private SocketChannel skt;
	private Server context;
	//private int connectionID;
	
	public ClientConnection(SocketChannel skt, Server s, int id) {
		this.skt = skt;
		this.context = s;
		//this.connectionID = id;
	}
	
	@Override
	public void run() {
		try {
			while(skt.isOpen()) {
				DataInputStream dis = new DataInputStream(skt.socket().getInputStream());
				Server.getLog().info("Incoming Packet from "+skt.getRemoteAddress());
				//context.log.info("Reading Packet.");
				byte[] idByteArray = new byte[4];
				dis.read(idByteArray); // ID
				//byte[] restByteBuf = {0};
				//ArrayList<Byte> dataBytes = new ArrayList<Byte>();
				//while(dis.read(restByteBuf) != 0) {
				//	dataBytes.add(restByteBuf[0]);
				//}
				Packet pktIn = new Packet(skt);
				pktIn.id = Integer.parseInt(Util.byteArrayToString(idByteArray),16);
				//Byte[] data = (Byte[]) dataBytes.toArray();
				//pktIn.data = data;
				Server.getLog().info("Packet ID: "+pktIn.id);
				context.activeProto.handleIncomingPacket(pktIn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Server.getLog().info("Connection Closed.");
		}
	}

}
