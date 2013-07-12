package net.cyberkitsune.jCube;

import java.io.DataInputStream;
import java.net.Socket;
import java.util.ArrayList;

import net.cyberkitsune.jCube.packets.Packet;

public class ClientConnection extends Thread {
	
	private Socket skt;
	private Server context;
	private int connectionID;
	
	public ClientConnection(Socket skt, Server s, int id) {
		this.skt = skt;
		this.context = s;
		this.connectionID = id;
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				DataInputStream dis = new DataInputStream(skt.getInputStream());
				context.log.info("Incoming Packet from "+skt.getRemoteSocketAddress());
				context.log.info("Reading Packet.");
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
				context.log.info("Packet ID: "+pktIn.id);
				context.activeProto.handleIncomingPacket(pktIn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
