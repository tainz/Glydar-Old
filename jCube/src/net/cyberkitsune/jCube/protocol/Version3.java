package net.cyberkitsune.jCube.protocol;

import net.cyberkitsune.jCube.Server;
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
	public Packet createOutgoingPacket(int id, PacketArgs args) {
		// TODO Auto-generated method stub
		return null;
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
	public Packet handleSendPacket1(PacketArgs a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Packet handleSendPacket2(PacketArgs a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Packet handleSendPacket3(PacketArgs a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Packet handleSendPacket4(PacketArgs a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Packet handleSendPacket5(PacketArgs a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Packet handleSendPacket10(PacketArgs a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Packet handleSendPacket15(PacketArgs a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Packet handleSendPacket16(PacketArgs a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Packet handleSendPacket17() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Packet handleSendPacket18() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void handleReadPacket17(Packet p) {
		// TODO Auto-generated method stub
		
	}

}
