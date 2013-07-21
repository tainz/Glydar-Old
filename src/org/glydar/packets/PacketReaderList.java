package org.glydar.packets;

import java.util.ArrayList;
import java.util.List;

public class PacketReaderList {

    private List<PacketReader> readers;

    public PacketReaderList() {
        this.readers = new ArrayList<PacketReader>();
    }

    public PacketReaderList(PacketReaderList readerList) {
        this.readers = new ArrayList<PacketReader>(readerList.readers);
    }

    public PacketReaderList(List<PacketReader> readers) {
        this.readers = readers;
    }

    public void addPacketReader(PacketReader reader) throws Exception {

        if (containsReadersWithId(reader.getPacketId())) {
            throw new Exception("Already have a packet reader with that packet id!");
        }

        readers.add(reader);

    }

    public void removePacketReader(PacketReader reader) {
        readers.remove(reader);
    }

    public boolean containsReadersWithId(int id) {
        return getReaderWithId(id) != null;
    }

    public PacketReader getReaderWithId(int id) {

        PacketReader reader = null;

        for (PacketReader r : readers) {

            if (r.getPacketId() == id) {

                reader = r;

                break;

            }

        }

        return reader;

    }

    public List<PacketReader> getPacketReaders() {
        return this.readers;
    }

}
