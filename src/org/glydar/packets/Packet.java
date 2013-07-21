package org.glydar.packets;

public abstract class Packet {

    protected int id;

    public Packet(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

}