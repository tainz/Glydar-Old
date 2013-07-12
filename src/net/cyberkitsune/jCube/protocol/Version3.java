package net.cyberkitsune.jCube.protocol;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;

import net.cyberkitsune.jCube.Server;
import net.cyberkitsune.jCube.Util;
import net.cyberkitsune.jCube.packets.Packet;
import net.cyberkitsune.jCube.packets.PacketArgs;

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
			getServer().log.info("Unknown Packet ID! Received: "+p.id); //TODO Dump packet for debug
			break;
		}
		
	}

	@Override
	public void sendOutgoingPacket(Packet p) {
		try {
		Socket packSkt = p.context;
		DataOutputStream pout = new DataOutputStream(packSkt.getOutputStream());
		StringBuilder outputString = new StringBuilder();
		outputString.append(Integer.toHexString(p.id));
		for(int n = 0; n <= (8-(Integer.toHexString(p.id).length())); n++) {
			outputString.append("0");
		}
		if(p.data != null) {
			outputString.append(Util.byteArrayToString(p.data));
		}
		byte[] packetToSend = Util.hexStringToByteArray(outputString.toString());
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
		if((new Random().nextInt() % 2) == 1) {
			handleSendPacket17(p.context);
		} else {
			handleSendPacket18(p.context);
		}
	}

	@Override
	public void handleSendPacket1(Socket context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleSendPacket2(Socket context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleSendPacket3(Socket context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleSendPacket4(Socket context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleSendPacket5(Socket context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleSendPacket10(Socket context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleSendPacket15(Socket context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleSendPacket16(Socket context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleSendPacket17(Socket context) { // Version Mismatch
		Packet reply = new Packet(context);
		reply.id = 17;
		reply.data = Util.hexStringToByteArray("000000"+Integer.toHexString(new Random().nextInt(225)));
		sendOutgoingPacket(reply);
	}

	@Override
	public void handleSendPacket18(Socket context) { // Full
		Packet reply = new Packet(context);
		reply.id = 18;
		sendOutgoingPacket(reply);
		
	}

}