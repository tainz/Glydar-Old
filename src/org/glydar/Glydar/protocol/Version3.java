package org.glydar.Glydar.protocol;

import java.io.DataOutputStream;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.util.Random;

import org.glydar.Glydar.Server;
import org.glydar.Glydar.Util;
import org.glydar.Glydar.packets.Packet;

public class Version3 implements NetworkProtocol{

	private Server sInst;
	
	public Version3(Server s) {
		this.setServer(s);
	}
	
	@Override
	public Server getServer() {
		return sInst;
	}

	@Override
	public void setServer(Server s) {
		this.sInst = s;
		
	}

	@Override
	public void handleIncomingPacket(Packet p) {
		switch (p.id) {
		case 0:
			handleReadPacket0(p);
			break;
		case 6:
			handleReadPacket6(p);
			break;
		case 7:
			handleReadPacket7(p);
			break;
		case 8: 
			handleReadPacket8(p);
			break;
		case 9:
			handleReadPacket9(p);
			break;
		case 10:
			handleReadPacket10(p);
			break;
		case 11:
			handleReadPacket11(p);
			break;
		case 12:
			handleReadPacket12(p);
			break;
		case 17:
			handleReadPacket17(p);
			break;
		default:
			Server.getLog().info("Unknown Packet ID! Received: "+p.id); //TODO Dump packet for debug
			break;
		}
		
	}

	@Override
	public void sendOutgoingPacket(Packet p) {
		try {
		SocketChannel packSkt = p.context;
		DataOutputStream pout = new DataOutputStream(packSkt.socket().getOutputStream());
		StringBuilder outputString = new StringBuilder();
		outputString.append(Integer.toHexString(p.id));
		outputString.append(Util.createZeros(Integer.toHexString(p.id), 8));
		if(p.data != null) {
			outputString.append(Util.byteArrayToString(p.data));
		}
		byte[] packetToSend = Util.hexStringToByteArray(outputString.toString());
		Server.getLog().info("Sending packet id: "+p.id+" data: "+Util.byteArrayToString(packetToSend));
		pout.write(packetToSend);
		pout.close();
		pout.flush();
		} catch (Exception ex) {
			
		}
		
	}

	@Override
	public void handleReadPacket0(Packet p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleReadPacket6(Packet p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleReadPacket7(Packet p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleReadPacket8(Packet p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleReadPacket9(Packet p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleReadPacket10(Packet p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleReadPacket11(Packet p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleReadPacket12(Packet p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleReadPacket17(Packet p) {
		// TODO actually handle this correctly
		// For now, we just complain that the server is full.
		if(new Random().nextBoolean()) {
			handleSendPacket17(p.context);
		} else {
			handleSendPacket18(p.context);
		}
	}

	@Override
	public void handleSendPacket1(SocketChannel context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleSendPacket2(SocketChannel context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleSendPacket3(SocketChannel context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleSendPacket4(SocketChannel context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleSendPacket5(SocketChannel context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleSendPacket10(SocketChannel context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleSendPacket15(SocketChannel context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleSendPacket16(SocketChannel context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleSendPacket17(SocketChannel context) { // Version Mismatch
		Packet reply = new Packet(context);
		reply.id = 17;
		int vNum = new Random().nextInt(9001);
		Server.getLog().info("Random version number: "+vNum+" or "+Integer.toHexString(vNum));
		reply.data = Util.hexStringToByteArray(Util.createZeros(Integer.toHexString(vNum), 8)+Integer.toHexString(vNum));
		sendOutgoingPacket(reply);
	}

	@Override
	public void handleSendPacket18(SocketChannel context) { // Full
		Packet reply = new Packet(context);
		reply.id = 18;
		sendOutgoingPacket(reply);
		
	}

}
