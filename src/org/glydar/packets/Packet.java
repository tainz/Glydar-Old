package org.glydar.packets;

public abstract class Packet {

	protected int id;
	protected PacketData data;

	public Packet(int id, PacketData data) {
		this.id = id;
		this.data = data;
	}

	public int getId() {
		return id;
	}

	public PacketData getData() {
		return data;
	}

}